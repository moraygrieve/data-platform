# Data Platform (Spring Boot + AWS DynamoDB/S3)

A small Spring Boot service for storing metadata in DynamoDB and binary objects in S3, secured with HTTP Basic Auth backed by a DynamoDB `UserDetails` table.

## Features
- Metadata API to add/delete `MetaModel` entries in DynamoDB
- Object upload/delete to S3, with S3 locations recorded alongside metadata
- Health API to return built application version
- HTTP Basic authentication with role-based access control (`admin`, `user`, `super_user`)

## Tech Stack
- Java 8, Spring Boot 2.7
- AWS SDK v1 (DynamoDB, S3)
- Maven build with assembly packaging (zip containing `lib/`, `etc/`, `bin/`)

## Project Structure
- `src/main/java/com/riekon/aws/` — application code
  - `Application.java` — entrypoint
  - `controller/` — REST controllers
  - `service/`, `repository/` — business logic and AWS access
  - `config/` — AWS clients and Spring Security config
  - `model/` — DynamoDB annotated entity models
  - `util/` — utilities for environment/runtime and DynamoDB setup
- `src/main/resources/` — config, packaging descriptor, scripts

## Configuration
Application config is in `src/main/resources/application.properties` (filtered at package time):

```properties
# Application metadata (auto-filled from Maven)
app.name=${name}
app.version=${version}
app.groupId=${groupId}
app.artifactId=${artifactId}

# AWS creds (used for DynamoDB and S3 clients)
amazon.aws.accesskey=
amazon.aws.secretkey=

# DynamoDB
amazon.dynamodb.endpoint=

# S3
amazon.s3.bucket=
amazon.s3.region=

# HTTPS (Tomcat)
server.port=8443
server.ssl.key-store=
server.ssl.key-store-password=
server.ssl.keyAlias=
```

Notes:
- For local DynamoDB, set `amazon.dynamodb.endpoint=http://localhost:8000` (or your local endpoint).
- Provide a real S3 bucket name in `amazon.s3.bucket`. The legacy `AmazonS3Client` will use your default AWS region/credentials; configure region via environment or default profile as needed.
- HTTPS requires a valid keystore. For development you can generate a self‑signed keystore:

```bash
keytool -genkeypair -alias local-ssl -keyalg RSA -keysize 2048 -storetype JKS -keystore local.jks -validity 3650
```

Then set:

```properties
server.ssl.key-store=/absolute/path/to/local.jks
server.ssl.key-store-password=changeit
server.ssl.keyAlias=local-ssl
```

## Build

```bash
mvn clean package
```

This produces a distribution zip under `target/` named `${artifactId}-${version}.zip` containing:
- `lib/platform-${version}.jar` and all dependencies
- `etc/` with filtered `application.properties` and `log4j.properties`
- `bin/start.sh` and `bin/stop.sh`

## Run
Unzip the distribution, update `etc/application.properties`, then:

```bash
./bin/start.sh
```

Logs and PID file are written to `logs/` and `var/` respectively. To stop:

```bash
./bin/stop.sh
```

Alternatively, you can run from the unpacked zip using Java directly:

```bash
java -Dlog4j.configuration=file:///$(pwd)/etc/log4j.properties -jar lib/platform-<version>.jar
```

## Security and Roles
HTTP Basic is enforced globally. Roles are loaded from the DynamoDB `UserDetails` table via `UserDetailsServiceHandler`.

- `admin`: access to health/version
- `user`: create metadata, upload objects
- `super_user`: delete metadata and objects

Seed users are not created automatically. See DynamoDB setup below.

## API
Base path: `/api`

### Health
- GET `/api/health/getVersion` — returns application version
  - Auth: `admin`

```bash
curl -k -u admin:password "https://localhost:8443/api/health/getVersion"
```

### Metadata
- POST `/api/meta/add` — create a new metadata entry; server assigns a UUID
  - Auth: `user`
  - Body (example):

```json
{
  "analytic": "example-analytic",
  "set": "training",
  "category": "audio",
  "metaInfoEntry": {
    "entry_date": "2024-12-01",
    "recording_date": "2024-11-30",
    "device_type": "mic-xyz"
  },
  "metaLocationEntry": {},
  "metaAudioEntry": {
    "format": "wav",
    "sample_rate": 44100,
    "encoding": "PCM_16",
    "endian": "LITTLE",
    "channel": "mono",
    "dc_offset": 0.0,
    "clipped": false
  }
}
```

Response: `200 OK` with UUID string.

- POST `/api/meta/delete?uuid=<UUID>` — delete metadata by UUID
  - Auth: `super_user`

```bash
curl -k -u super:password -X POST "https://localhost:8443/api/meta/delete?uuid=<UUID>"
```

### Objects (S3)
- POST `/api/object/add` — upload primary file and optional companions
  - Auth: `user`
  - Multipart form fields:
    - `uuid`: UUID returned from metadata creation
    - `file`: one or more file parts; first is primary, subsequent are companions

```bash
curl -k -u user:password -X POST \
  -F "uuid=<UUID>" \
  -F "file=@/path/to/primary.wav" \
  -F "file=@/path/to/companion.json" \
  "https://localhost:8443/api/object/add"
```

Response: `200 OK` with success message. S3 keys are date‑partitioned (`yyyy-MM-dd/<uuid>.<ext>`) and recorded in `MetaLocationEntry`.

- POST `/api/object/delete?key=<S3_KEY>` — delete an object by S3 key
  - Auth: `super_user`

```bash
curl -k -u super:password -X POST "https://localhost:8443/api/object/delete?key=2025-01-01/<uuid>.wav"
```

## DynamoDB Setup (tables and users)
Utilities are provided to create tables and seed users. After `mvn package` and extracting the zip:

```bash
# Create tables (e.g., UserDetails)
java -cp "lib/*" com.riekon.aws.util.dynamodb.CreateTables

# Seed a user (example user with role 'user')
java -cp "lib/*" com.riekon.aws.util.dynamodb.CreateUsers
```

Default example in `CreateUsers` creates user `moray.grieve` with role `user` (password is BCrypt encoded there). Adjust and re-run as needed to create `admin` and `super_user` accounts.

## Local Development Tips
- Use DynamoDB Local and set `amazon.dynamodb.endpoint` accordingly.
- Ensure `amazon.aws.accesskey` and `amazon.aws.secretkey` match what your local DynamoDB expects (can be dummy values for local).
- For S3 integration tests, use a real bucket and AWS credentials with access to it.

## License
Proprietary/Internal (update as appropriate).
