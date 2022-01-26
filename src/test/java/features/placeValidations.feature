Feature: API validation

Scenario: Verify if Place is being succesfully added using "AddPlaceAPI"
Given API Payload
When user calls "AddPlaceAPI" with post http request 
Then the API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"