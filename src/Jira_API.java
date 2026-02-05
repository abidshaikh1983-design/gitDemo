import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class Jira_API {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		RestAssured.baseURI = "https://abidshaikhin1983.atlassian.net";
		String CreateBug = given().header("Authorization", "Basic YWJpZHNoYWlraF9pbjE5ODNAeWFob28uY28uaW46QVRBVFQzeEZmR0YwS0pPZEN4bnB4VUVhY0dzMURhTGJzNTc0SHZPekVDNG8zMHZuZnRhYll5UjB0T2VobjVIMWpsaHpJcEtwRGFPN2Z5cS1odFZrRXlEVjlILTVxQkltLTZISUZXdDN1Nm9ieGMxX0VWcUNhTWxicTJERnZRaHpmSUtVLUlWbzlIdDZUeW5HNHAydVNYVGx3eUhjSXVsRV8zdGd1b19nM0hLbGx2TzhSVGhYRkRnPTI0NDA4OEEx")
		.header("Content-Type", "Application/json")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Dropdowns are not working for request Form - Rest\",\r\n"
				+ "      \r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}").when().post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath JsPath = new JsonPath(CreateBug);
		
		String ID = JsPath.getString("id");
		System.out.println(ID);
		String attach = given().log().all().header("X-Atlassian-Token","no-check")
		.header("Authorization", "Basic YWJpZHNoYWlraF9pbjE5ODNAeWFob28uY28uaW46QVRBVFQzeEZmR0YwS0pPZEN4bnB4VUVhY0dzMURhTGJzNTc0SHZPekVDNG8zMHZuZnRhYll5UjB0T2VobjVIMWpsaHpJcEtwRGFPN2Z5cS1odFZrRXlEVjlILTVxQkltLTZISUZXdDN1Nm9ieGMxX0VWcUNhTWxicTJERnZRaHpmSUtVLUlWbzlIdDZUeW5HNHAydVNYVGx3eUhjSXVsRV8zdGd1b19nM0hLbGx2TzhSVGhYRkRnPTI0NDA4OEEx")
		.pathParam("key", ID).multiPart("file", new File("C://Users//SHABID//OneDrive - Capgemini//Desktop//Valmet//Screenshots//Cisco Any Connect.jpg"))
		.log().all().post("rest/api/3/issue/{key}/attachments").then().log().all()
		.assertThat().statusCode(200).extract().response().asString();
		
		JsonPath JS1  = new JsonPath(attach);
		String FileName = JS1.getString("filename");
		
		System.out.println(FileName);
		
	}

}
