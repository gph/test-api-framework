package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public RequestSpecification requestSpecification() throws IOException {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		RequestSpecification req = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseURL"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.build();
		return req;
	}
	
	public ResponseSpecification responseSpecification() {
		ResponseSpecification res = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		return res;
	}
	
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		
		FileInputStream is = new FileInputStream("C:\\Users\\gph19\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(is);
		return prop.getProperty(key);
	}
}