import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import POJO.CourseDetails;
import POJO.api;
import POJO.webAutomation;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Authorization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RestAssured.useRelaxedHTTPSValidation();
		String AuthoCode = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust").when().post("oauthapi/oauth2/resourceOwner/token")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath JsPath = new JsonPath(AuthoCode);
		
		String access_token = JsPath.getString("access_token");
		System.out.println(access_token);
		
		CourseDetails cd = given().queryParam("access_token", access_token)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").then()
		.log().all().assertThat().statusCode(401).extract().as(CourseDetails.class);
		
		String instructor = cd.getInstructor();
		String courses = cd.getCourses().getWebAutomation().get(1).getCourseTitle();
		System.out.println(instructor);
		System.out.println(courses);
		List<webAutomation> courseWebAuto = cd.getCourses().getWebAutomation();
		for (int i=0; i<courseWebAuto.size(); i++)
		{
			if (courseWebAuto.get(i).getCourseTitle().equalsIgnoreCase("Selenium Webdriver Java"))
			{
				System.out.println(courseWebAuto.get(i).getPrice());
				
			}
		}
		
		List<api> courseApi = cd.getCourses().getApi();
		
		for (int i = 0; i<courseApi.size(); i++)
		{
			if (courseApi.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(courseApi.get(i).getCourseTitle());
				System.out.println(courseApi.get(i).getPrice());
				
			}
		}
		List<String> coursesTitle = new ArrayList<String>();
		
		for (int i = 0; i<courseWebAuto.size(); i++)
		{
			String courseTitle = courseWebAuto.get(i).getCourseTitle();
			System.out.println(courseTitle);
			coursesTitle.add(courseTitle);
		}
		String [] coursesTit = {"Selenium Webdriver Java", "Cypress", "Protractor"};
		
		List<String> expectedList = Arrays.asList(coursesTit);
		Assert.assertTrue(coursesTitle.equals(expectedList));
		
		
	}

}
