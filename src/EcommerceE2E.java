import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import POJO.LoginResponse;
import POJO.Orders;
import POJO.orderDetail;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;


public class EcommerceE2E {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Login Application
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification loginReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
		
		POJO.LoginRequest Login = new POJO.LoginRequest();
		Login.setUserEmail("testing0412@gmail.com");
		Login.setUserPassword("Test@123");
		
		RequestSpecification loginReqe = given().log().all().spec(loginReq).body(Login);
		
		LoginResponse logresp = loginReqe.when().post("api/ecom/auth/login")
				.then().log().all().extract().response().as(LoginResponse.class);
		
		String Token = logresp.getToken();
		String UserID = logresp.getUserId();
		
		System.out.println(Token);
		System.out.println(UserID);
		
		// Create product
		
		RequestSpecification createProd = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", Token).build();
		
		RequestSpecification ProdCreate = given().log().all().spec(createProd).param("productName", "Mouse")
		.param("productAddedBy", UserID).param("productCategory", "Electonic")
		.param("productSubCategory", "Computer").param("productPrice", "500")
		.param("productDescription", "Dell").param("productFor", "All")
		.multiPart("productImage", new File("C://Users//SHABID//Downloads//Mouse.jpeg"));
		
		String CreateProdRes = ProdCreate.when().post("api/ecom/product/add-product").then().log().all()
		.extract().response().asString();
		
		JsonPath JsPath = new JsonPath(CreateProdRes);
		String productId = JsPath.get("productId");
		System.out.println(productId);
		
		//Create Order
		orderDetail orderDetail = new orderDetail();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(productId);
		
		List<orderDetail> OrderList = new ArrayList<orderDetail>();
		OrderList.add(orderDetail);
		
		Orders createOrder = new Orders();
		createOrder.setOrders(OrderList);
		
		RequestSpecification CreateOrder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addHeader("Authorization", Token).setContentType(ContentType.JSON).build();
		
		RequestSpecification createOrd = given().spec(CreateOrder).log().all()
				.body(createOrder);
		
		String CreatedOrder = createOrd.when().post("api/ecom/order/create-order").then().log().all()
		.extract().response().asString();
		
		System.out.println(CreatedOrder);
		
		//View Order
//		Created_Orders CreatedOrders = new Created_Orders();
//			
//		List<Created_Orders> Created_Orders = new ArrayList<Created_Orders>();
//		Created_Orders.add(CreatedOrders);
//		ViewOrder viewOrder = new ViewOrder();
//		viewOrder.getOrders();
		
		
		
//		RequestSpecification ViewOrder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
//		.addQueryParam(CreatedOrder, OrderList)
		
		
		
		
		//Delete Order
		RequestSpecification deleteproduct = given().log().all().spec(createProd).pathParam("productId", productId);
		
		String DeleteResponse = deleteproduct.when().delete("api/ecom/product/delete-product/{productId}")
		.then().log().all().extract().response().asString();
		
		JsonPath JsPath1 = new JsonPath(DeleteResponse);
		String DeleteMessage = JsPath1.get("message");
		
		System.out.println(DeleteMessage);
		Assert.assertEquals("Product Deleted Successfully", DeleteMessage);
		
	}


}
