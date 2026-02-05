import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import POJO.addPlace;
import POJO.location;


public class Serialization {

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
		RestAssured.useRelaxedHTTPSValidation();
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type","application/json" )
		.body(ap)
		.when().post("/maps/api/place/add/json").then().log().all()
		.assertThat().statusCode(200).body("scope", equalTo("APP")).extract().asString();
		
		
		System.out.println(response);
	}

}
