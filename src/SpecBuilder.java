import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import POJO.addPlace;
import POJO.location;


public class SpecBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		addPlace ap = new addPlace();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");
		ap.setName("Frontlines house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http: //google.com");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		
		location loc = new location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		ap.setLocation(loc);
	
		RequestSpecification requ = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		ResponseSpecification res = (ResponseSpecification) new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).expectBody("scope", equalTo("APP")).build();
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification request = given().spec(requ)
		.body(ap);
		
		Response response = request.when().post("/maps/api/place/add/json")
				.then().spec(res).extract().response();
		
		String responseString = response.asString();
				
		System.out.println(responseString);
	}

}
