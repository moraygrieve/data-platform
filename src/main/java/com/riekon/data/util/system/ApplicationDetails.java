package com.riekon.data.util.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class ApplicationDetails {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.groupId}")
    private String appGroupId;

    @Value("${app.artifactId}")
    private String appArtifactId;

    public String getAppName() { return appName; }
    public String getAppVersion() { return appVersion; }
    public String getAppGroupId() { return appGroupId;}
    public String getAppArtifactId() { return appArtifactId; }
}
