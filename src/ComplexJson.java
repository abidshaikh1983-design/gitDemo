import Files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath JsPath = new JsonPath(payload.CourseDetails());
		
//		1. Print No of courses returned by API
		
		int count = JsPath.getInt("courses.size()");
		System.out.println(count);

//		2.Print Purchase Amount
		
		int amount = JsPath.getInt("dashboard.purchaseAmount");
		
		System.out.println(amount);
		
//		3. Print Title of the first course
		
		String title = JsPath.get("courses[0].title");
		System.out.println(title);
		
//		4. Print All course titles and their respective Prices
		for (int i = 0; i<count; i++)
		{
			String titles = JsPath.get("courses["+i+"].title");
			System.out.println(JsPath.get("courses["+i+"].price").toString());
					
			System.out.println(titles);
			//System.out.println(Price);
			
		}

//		5. Print no of copies sold by RPA Course
		
		for (int i = 0; i<count; i++)
		{
			String titles = JsPath.get("courses["+i+"].title");
			if(titles.equalsIgnoreCase("Cypress"))
			{
				
				System.out.println(JsPath.get("courses["+i+"].copies").toString());
				break;
				
			}
			
	}

}}

