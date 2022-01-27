package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AddPlace;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	
	TestDataBuild data = new TestDataBuild();
	AddPlace place;
	Response response;
	RequestSpecification req;
	static String place_id;
	
	@Given("API Payload with {string} {string} {string}")
	public void api_payload_with(String name, String address, String language) throws IOException {
		place = data.addPlacePayload(name, address, language);
		req = given().spec(requestSpecification()).body(place);
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		if(httpMethod.equalsIgnoreCase("POST"))
		{
			response = req.when().post(resourceAPI.getResource());
		}
		else if(httpMethod.equalsIgnoreCase("GET"))
		{
			response =	req.when().get(resourceAPI.getResource());
		}
		else if(httpMethod.equalsIgnoreCase("DELETE"))
		{
			response = req.when().delete(resourceAPI.getResource());
		}
		response = response.then().spec(responseSpecification()).extract().response();
	}
	
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer code) {
		Assert.assertEquals(new BigDecimal(code), new BigDecimal(response.getStatusCode()));
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		Assert.assertEquals(value, getJsonPath(response, key));
	}
	
	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(response, "place_id");
		req = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "get");
		String name = getJsonPath(response, "name");
		Assert.assertEquals(expectedName, name);
	}
	
	@Given("API Payload DeletePlace")
	public void api_payload_delete_place() throws IOException {
		req = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
}