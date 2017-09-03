import sys, requests, json, os.path
from requests.packages.urllib3.exceptions import SubjectAltNameWarning


# disable the 'Certificate for localhost has no subjectAltName' warnings
requests.packages.urllib3.disable_warnings(SubjectAltNameWarning)

# define the meta-data
data = {
    'analytic': 'dog_bark',
    'set': 'test',
    'category': 'target',
	'metaInfoEntry': {
		'entry_date': '2017-03-02',
		'recording_date' : '2017-03-02',
		'device_type': 'dayton'
	},
	'metaLocationEntry': {
		'primary': '',
		'companions': []
	},
	'metaAudioEntry': {
		'format': 'wav',
		'sample_rate': '16000',
		'encoding': 'int16',
		'endian': 'litle',
		'channel': 'mono',
		'dc_offset': '-15',
		'clipped': 'true'
	}
}

def addMetaData(user, password):
	headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
	response = requests.post('https://localhost:8443/api/meta/add', data=json.dumps(data), headers=headers,
				  	   auth=(user, password), verify='../resources/blade.pem')
	return response

def addObject(user, password, uuid, primary, companions):
	data = {'uuid':uuid}
	files = [ ('file', (os.path.basename(primary), open(primary, 'rb'))) ]
	for companion in companions:
		files.append(('file', (os.path.basename(companion), open(companion, 'rb'))) )

	response = requests.post('https://localhost:8443/api/object/add', data=data, files=files,
					  auth=(user, password), verify='../resources/blade.pem')
	return response

if __name__ == "__main__":
	user, password, primary, companions = sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4:]
	response = addMetaData(user, password)
	if response.status_code == 200:
		response = addObject(user, password, response.text, primary, companions)
