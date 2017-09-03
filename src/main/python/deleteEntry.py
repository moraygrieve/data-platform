import sys
import requests
from requests.packages.urllib3.exceptions import SubjectAltNameWarning


# disable the 'Certificate for localhost has no subjectAltName' warnings
requests.packages.urllib3.disable_warnings(SubjectAltNameWarning)

def deleteMetaData(user, password, uuid):
    payload = {'uuid': uuid}
    response = requests.post('https://localhost:8443/api/meta/delete', data=payload,
                             auth=(user, password), verify='../resources/blade.pem')
    return response

def deleteObject(user, password, uuid):
    payload = {'uuid': uuid}
    response = requests.post('https://localhost:8443/api/object/delete', data=payload,
                             auth=(user, password), verify='../resources/blade.pem')
    return response

if __name__ == "__main__":
    user, password, uuid = sys.argv[1:4]
    response = deleteObject(user, password, uuid)
    if response.status_code == 200:
        response = deleteMetaData(user, password, uuid)
