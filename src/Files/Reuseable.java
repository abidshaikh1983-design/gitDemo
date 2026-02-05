package Files;

import io.restassured.path.json.JsonPath;

public class Reuseable {

	
	public static JsonPath rowToJson(String response)
	{
		
		JsonPath JsPath = new JsonPath(response);
				return JsPath;
		
	}
}
