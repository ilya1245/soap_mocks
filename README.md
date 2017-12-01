# soap_mocks
Based on https://spring.io/guides/gs/producing-web-service/
Usage
1. run Application class's main method

2. Testing
run curl --header "content-type: text/xml" -d @request.xml http://localhost:8080/mock
run curl --header "content-type: text/xml" -d @GetCarrierBooking_Availability_Request.xml http://localhost:8080/mock

3. Getting wsdl
http://localhost:8080/mock/countries.wsdl
http://localhost:8080/mock/carrier.wsdl
http://localhost:8080/mock/pfs.wsdl
