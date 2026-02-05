import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Reuseable;
import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

	
	@Test (dataProvider = "Name")
	public void AddBook(String isbn, String asile)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String addBook = given().header("Content-Type", "application/json")
		.body(payload.AddBook(isbn,asile))
		.when().post("Library/Addbook.php")
		.then().log().all().extract().response().asString();
		JsonPath JsPath = Reuseable.rowToJson(addBook);
		String ID = JsPath.get("ID");
		
		System.out.println(ID);

	}
	
	@DataProvider (name = "Name")
	public Object [][] getData()
	{
		return new Object [][] {{"abed", "7654"}, {"ajed", "7954"},{"abeg", "7684"} };
		
	}
	
	
	@Test 
	public void AddBookfromFile() throws IOException
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String addBook = given().header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes(Paths.get("C://Users//SHABID//OneDrive - Capgemini//Desktop//Addbookdetails.json"))))
		.when().post("Library/Addbook.php")
		.then().log().all().extract().response().asString();
		JsonPath JsPath = Reuseable.rowToJson(addBook);
		String ID = JsPath.get("ID");
		
		System.out.println(ID);
	
	}
	
	
	@Test
	public void addPlace() throws IOException
	
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String addedPlace = given().queryParam("key", "qaclick123").header("Content-Type","application/json" )
		.body(Files.readAllBytes(Paths.get("C://Users//SHABID//OneDrive - Capgemini//Desktop//Place.json"))).when().post("/maps/api/place/add/json").then().log().all()
		.assertThat().statusCode(200).body("scope", equalTo("APP")).extract().asString();
		
		System.out.println(addedPlace);
	}
}
