package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	public AddPlace addPlacePayload() {
		AddPlace place = new AddPlace();
		Location location = new Location();
		location.setLat(-33);
		location.setLng(-33);
		place.setLocation(location);
		place.setAccuracy(50);
		place.setName("New house");
		place.setPhone_number("(+00) 000 000 0000");
		place.setAddress("4321, below, somewhere 1");
		List<String> types = new ArrayList<String>();
		types.add("shop zero");
		types.add("shop one");
		place.setTypes(types);
		place.setWebsite("http://google.com");
		place.setLanguage("US");
		
		return place;
	}
}
