Feature: API validation

@addplace
Scenario Outline: Verify if Place is being succesfully added using AddPlaceAPI
Given API Payload with "<name>" "<address>" "<language>" 
When user calls "AddPlaceAPI" with "POST" http request 
Then the API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And Verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples: # Every row is a new dataset
	|name 		| language | address 					|
	|Old House 	| Japanese | 321, below, somewhere 1 	|
	#|Airbnb		| Spanish  | 431213 eulaksjka			|
	
@deleteplace
Scenario: Verify if Delete Place functionality is working
Given API Payload DeletePlace
When user calls "DeletePlaceAPI" with "DELETE" http request
Then the API call is success with status code 200
And "status" in response body is "OK"