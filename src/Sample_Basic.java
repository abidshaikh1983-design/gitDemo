import static io.restassured.RestAssured.given;

import org.testng.Assert;

import Files.payload;
import POJO.AddObject;
import POJO.objectData;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Sample_Basic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Add Object
		RestAssured.baseURI ="https://api.restful-api.dev/";
		String addObject = given().header("Content-Type", "application/json").body(payload.Add_Object())
		.when().post("objects").then().log().all().assertThat().statusCode(200)
		.extract().asString();
		
		JsonPath jpath = new JsonPath(addObject);
		String ID = jpath.get("id");
			
//		Get Object
		given().queryParam("id",ID).log().all()
		.when().get("objects").then().assertThat().statusCode(200)
		.log().all();
		
//		update Object
		String updateObject = given().header("Content-Type", "application/json")
		.body(payload.Update_Object()).when().put("objects/{id}",ID).then().assertThat().statusCode(200)
		.log().all().extract().asString();
		
		JsonPath json1 = new JsonPath(updateObject);
		String name = json1.getString("name");
		System.out.println(name);
		
//		Partial Update
		given().header("Content-Type", "application/json")
		.body(payload.Partial_update()).when().patch("objects/{id}" ,ID).then().assertThat().statusCode(200)
		.log().all().extract().asString();
		
//		Delete Object
		String DeleteMessage = given().header("Content-Type", "application/json")
				.when().delete("objects/{id}" ,ID).then().log().all()
		.assertThat().statusCode(200).extract().asString();
		JsonPath json2 = new JsonPath(DeleteMessage);
		String message = json2.get("message");
		System.out.println(message);
		Assert.assertEquals(message, "Object with id = "+ID+" has been deleted.");
		
	}

}
