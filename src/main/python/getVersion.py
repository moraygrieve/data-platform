import requests, sys
from requests.packages.urllib3.exceptions import SubjectAltNameWarning

# disable the 'Certificate for localhost has no subjectAltName' warnings
requests.packages.urllib3.disable_warnings(SubjectAltNameWarning)

if __name__ == "__main__":
	user, password = sys.argv[1:3]
	r = requests.get('https://localhost:8443/api/health/getVersion',
				 auth=(user, password), verify='../resources/blade.pem')
	print r.text



