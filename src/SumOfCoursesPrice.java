import org.testng.annotations.Test;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class SumOfCoursesPrice {

	@Test
	public void sumOfCourse()
	{
//		6. Verify if Sum of all Course prices matches with Purchase Amount
		
			int sum = 0;
			JsonPath JsPath = new JsonPath(payload.CourseDetails());
			int count = JsPath.getInt("courses.size()");
			
			for (int i = 0; i<count; i++)
			{
				int price = JsPath.get("courses["+i+"].price");
				int copies = JsPath.get("courses["+i+"].copies");
				
				int amounts = price * copies;
				//System.out.println(amounts);	
				sum = amounts + sum;
				
				System.out.println(sum);							
			}

	}
	
}
