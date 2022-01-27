package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	public AddPlace addPlacePayload(String name, String address, String language) {
		AddPlace place = new AddPlace();
		Location location = new Location();
		location.setLat(-33);
		location.setLng(-33);
		place.setLocation(location);
		place.setAccuracy(50);
		place.setName(name);
		place.setPhone_number("(+00) 000 000 0000");
		place.setAddress(address);
		List<String> types = new ArrayList<String>();
		types.add("shop zero");
		types.add("shop one");
		place.setTypes(types);
		place.setWebsite("http://google.com");
		place.setLanguage(language);
		
		return place;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\"place_id\":\"" + placeId + "\"}";
	}
}