import static io.restassured.RestAssured.given;

import Files.payload;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		RequestSpecification request = new RequestSpecBuilder().setBaseUri("http://216.10.245.166")
				.setContentType(ContentType.JSON).build();
		
		ResponseSpecification ResponseSpec = new ResponseSpecBuilder()
			.expectStatusCode(200).build();
		
		RequestSpecification req = given().spec(request).header("Content-Type", "application/json")
		.body(payload.AddBook("cdjd","99865"));
		
		Response addBook = req.when().post("Library/Addbook.php");
		
		
		Response resp = addBook.then().spec(ResponseSpec).extract().response();
			
		System.out.println(resp.getBody().asString());
		
	}

}
