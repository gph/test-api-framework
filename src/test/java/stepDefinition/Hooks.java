package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		// write a code  that will give you a place id
		// execute this code only when place id is null
		if(StepDefinition.place_id != null)
			return;
		
		StepDefinition step = new StepDefinition();
		step.api_payload_with("Test1", "street 1 2 3", "Mandarim");
		System.out.print("Hooked -> ");
		step.user_calls_with_http_request("AddPlaceAPI", "POST");
		System.out.print("Hooked -> ");
		step.verify_place_id_created_maps_to_using("Test1", "GetPlaceAPI");
	}
}
