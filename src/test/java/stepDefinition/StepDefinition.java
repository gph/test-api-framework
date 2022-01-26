package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.AddPlace;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	
	TestDataBuild data = new TestDataBuild();
	AddPlace place;
	Response response;
	
	@Given("API Payload with {string} {string} {string}")
	public void api_payload_with(String name, String address, String language) {
		place = data.addPlacePayload(name, address, language);
	}

	@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String string) throws IOException {
		response = 
				given().spec(requestSpecification()).body(place)
				.when().post("/maps/api/place/add/json")
				.then().spec(responseSpecification()).extract().response();
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer code) {
		Assert.assertEquals(new BigDecimal(code), new BigDecimal(response.getStatusCode()));
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		Assert.assertEquals(value, js.get(key));
	}
}