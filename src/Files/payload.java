package Files;

public class payload {

	
	public static String AddPlace()
	{
		return "{\r\n"
				+ "\"location\": {\r\n"
				+ "\"lat\": -38.383494,\r\n"
				+ "\"lng\": 33.427362\r\n"
				+ "    },\r\n"
				+ "\"accuracy\": 50,\r\n"
				+ "\r\n"
				+ "\"name\": \"Frontlines house\",\r\n"
				+ "\"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "\"address\": \"29, side layout, cohen 09\",\r\n"
				+ "\"types\": [\r\n"
				+ "\"shoe park\",\r\n"
				+ "\"shop\"\r\n"
				+ "    ],\r\n"
				+ "\"website\": \"http: //google.com\",\r\n"
				+ "\"language\": \"French-IN\"\r\n"
				+ "}";
		}
	
	String Place_ID;
	
	public static String UpdatePlace()
	{
		return "{\r\n"
				+ "\"place_id\":\"cc92f44c054c78a1573d0c101609f094\",\r\n"
				+ "\"address\":\"70 winter walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}";
	
	}
	
	public static String CourseDetails()
	{
		return "{\r\n"
		+ "\r\n"
		+ "\"dashboard\": {\r\n"
		+ "\r\n"
		+ "\"purchaseAmount\": 910,\r\n"
		+ "\r\n"
		+ "\"website\": \"rahulshettyacademy.com\"\r\n"
		+ "\r\n"
		+ "},\r\n"
		+ "\r\n"
		+ "\"courses\": [\r\n"
		+ "\r\n"
		+ "{\r\n"
		+ "\r\n"
		+ "\"title\": \"Selenium Python\",\r\n"
		+ "\r\n"
		+ "\"price\": 50,\r\n"
		+ "\r\n"
		+ "\"copies\": 6\r\n"
		+ "\r\n"
		+ "},\r\n"
		+ "\r\n"
		+ "{\r\n"
		+ "\r\n"
		+ "\"title\": \"Cypress\",\r\n"
		+ "\r\n"
		+ "\"price\": 40,\r\n"
		+ "\r\n"
		+ "\"copies\": 4\r\n"
		+ "\r\n"
		+ "},\r\n"
		+ "\r\n"
		+ "{\r\n"
		+ "\r\n"
		+ "\"title\": \"RPA\",\r\n"
		+ "\r\n"
		+ "\"price\": 45,\r\n"
		+ "\r\n"
		+ "\"copies\": 10\r\n"
		+ "\r\n"
		+ "}\r\n"
		+ "\r\n"
		+ "]\r\n"
		+ "\r\n"
		+ "}\r\n"
		+ "\r\n"
		+ "";
		
	}
	
	
	public static String AddBook(String isbn, String aisle)
	{
		String Body = "{\r\n"
				+ "\"name\": \"Learn Java\",\r\n"
				+ "\"isbn\": \""+isbn+"\",\r\n"
				+ "\"aisle\": \""+aisle+"\",\r\n"
				+ "\"author\": \"John Foe\"\r\n"
				+ "\r\n"
				+ "}";
		
		return Body;
		
				
	}
	
	public static String Add_Object()
	{
		return "{\r\n"
				+ "   \"name\": \"Apple MacBook Pro 18\",\r\n"
				+ "   \"data\": {\r\n"
				+ "      \"year\": 2025,\r\n"
				+ "      \"price\": 1849.99,\r\n"
				+ "      \"CPU model\": \"Intel Core i9\",\r\n"
				+ "      \"Hard disk size\": \"1 TB\"\r\n"
				+ "   }\r\n"
				+ "}";
		
	}
	
	public static String Update_Object()
	{
		return "{\r\n"
				+ "   \"name\": \"Apple MacBook Pro 16\",\r\n"
				+ "   \"data\": {\r\n"
				+ "      \"year\": 2019,\r\n"
				+ "      \"price\": 2049.99,\r\n"
				+ "      \"CPU model\": \"Intel Core i9\",\r\n"
				+ "      \"Hard disk size\": \"1 TB\",\r\n"
				+ "      \"color\": \"silver\"\r\n"
				+ "   }\r\n"
				+ "}";
				
	}
	
	public static String Partial_update()
	{
		return "{\r\n"
				+ "   \"name\": \"Apple MacBook Pro 20\"\r\n"
				+ "}";
		
	}
	
}
