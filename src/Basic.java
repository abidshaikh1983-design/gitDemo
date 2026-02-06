import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Reuseable;
import Files.payload;


public class Basic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.useRelaxedHTTPSValidation();
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String addedPlace = given().queryParam("key", "qaclick123").header("Content-Type","application/json" )
		.body(payload.AddPlace()).when().post("/maps/api/place/add/json").then().log().all()
		.assertThat().statusCode(200).body("scope", equalTo("APP")).extract().asString();
		
		System.out.println(addedPlace);
		
		JsonPath JsPath = Reuseable.rowToJson(addedPlace);
		String place_id = JsPath.getString("place_id");
		
		System.out.println("placeID is " + place_id);
		
		String newAdd = "70 winter walk, France";
		
		given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+newAdd+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json").then().log().all()
		.assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		String getPlace = given().queryParam("key", "qaclick123")
				.queryParam("place_id", place_id)
		.when().get("maps/api/place/get/json").then().assertThat().statusCode(200)
		.log().all().extract().response().asString();
		
		JsonPath JS1 = Reuseable.rowToJson(getPlace);
		String Address = JS1.getString("address");
		System.out.println("New Updated Address is "+Address);
		Assert.assertEquals(newAdd, Address);
		String phone_number = JS1.getString("phone_number");
		System.out.println(phone_number);

	}

}
