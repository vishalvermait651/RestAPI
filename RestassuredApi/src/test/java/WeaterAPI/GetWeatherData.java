package WeaterAPI;
import org.apache.http.util.Asserts;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class GetWeatherData {

	@Test(priority=1)                                          // Status code validation
	public void getResponseCode() {

		int code = RestAssured.get(
				"http://samples.openweathermap.org/data/2.5/weather?=London&appid=9d50450a48809637b4862bdcb125927d&units=imperial")
				.getStatusCode();

		System.out.println("Status code is :" + code);

		Assert.assertEquals(code, 200);
		System.out.println(".............................................");
	}

	@Test(priority=2)                                // Response time  validation
	public void getBody() {

		String data = RestAssured.get(
				"api.openweathermap.org/data/2.5/weather?q=London&appid=9d50450a48809637b4862bdcb125927d&units=imperial")
				.asString();

		long Time = RestAssured.get(
				"api.openweathermap.org/data/2.5/weather?q=London&appid=9d50450a48809637b4862bdcb125927d&units=imperial")
				.getTime();
	

		System.out.println("Response time :" + Time);
		System.out.println(".............................................");

	}
	
	@Test(priority=0)                        //validate body
	public void getDetalis() {

		Response response = RestAssured.get("http://api.openweathermap.org/data/2.5/weather?q=London&appid=9d50450a48809637b4862bdcb125927d&units=imperial");
		
		JsonPath jsonpath= response.jsonPath();
		
		System.out.println(response.jsonPath().prettyPrint());
		System.out.println(jsonpath.get("name"));
		System.out.println(jsonpath.get("id"));
		System.out.println(jsonpath.get("timezone"));
		System.out.println(jsonpath.get("cod"));
	
		Assert.assertEquals(jsonpath.get("name"),"London");
		Assert.assertEquals(jsonpath.get("id"),2643743);
		Assert.assertEquals(jsonpath.get("timezone"),0);
		Assert.assertEquals(jsonpath.get("cod"),200);
		System.out.println(".............................................");
		
	}
	
	@Test(priority=3)                          // Header Validation
	public void getContentType() {

		String contentType = RestAssured.get("http://samples.openweathermap.org/data/2.5/weather?=London&appid=9d50450a48809637b4862bdcb125927d&units=imperial").header("Content-Type");

		System.out.println("Content-Type is"+ contentType);

		Assert.assertEquals(contentType, "application/json; charset=utf-8");

		System.out.println(".............................................");
	}
	
	@Test(priority=4)                     // Print all Headers and values 
	public void getAllHeaders() {

		Response response = RestAssured.get("http://samples.openweathermap.org/data/2.5/weather?=London&appid=9d50450a48809637b4862bdcb125927d&units=imperial");
		
		Headers header= response.headers();
		
		
		for( Header hd:header) {
			System.out.println(hd.getName() +"" + hd.getValue());
			

	}
		System.out.println(".............................................");

}

}
