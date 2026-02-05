import static io.restassured.RestAssured.given;

import org.testng.Assert;

import Files.payload;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Request_Specification<Apple> {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
//		Add Object
		RequestSpecification requ = new RequestSpecBuilder().setBaseUri("https://api.restful-api.dev/")
		.setContentType(ContentType.JSON).build();	
		RequestSpecification request = given().spec(requ).body(payload.Add_Object());
		Response response = request.when().post("objects");
		
		response.then().assertThat().statusCode(200)
		.extract().response();
		String responseString = response.asString();
		JsonPath jpath = new JsonPath(responseString);
		String ID = jpath.get("id");
			
//		Get Object
		RequestSpecification getReq = given().spec(requ).queryParam("id", ID);
		Response GetResponse = getReq.when().get("objects").then().assertThat().statusCode(200)
		.extract().response();
		String getRes = GetResponse.asString();
		System.out.println(getRes);
		
//		update Object
		RequestSpecification UpdateReq = given().spec(requ)
		.body(payload.Update_Object());
		String updateResponse = UpdateReq.when().put("objects/{id}",ID).then().assertThat()
		.statusCode(200).extract().asString();
		System.out.println(updateResponse);
		
//		Partial Update
		RequestSpecification PartReq = given().spec(requ)
		.body(payload.Partial_update());
		String PartialUpdate = PartReq.when().patch("objects/{id}" ,ID).then().assertThat()
		.statusCode(200).extract().asString();
		System.out.println(PartialUpdate);
		
//		Delete Object
		RequestSpecification Delete = given().spec(requ).header("Content-Type", "application/json");
		
		String message =	Delete.when().delete("objects/{id}" ,ID).then()
		.assertThat().statusCode(200).extract().asString();
		JsonPath jsonP = new JsonPath(message);
		String DeleMessage = jsonP.get("message");
		System.out.println(DeleMessage);
		
		Assert.assertEquals(DeleMessage, "Object with id = "+ID+" has been deleted.");
		
	}

}
