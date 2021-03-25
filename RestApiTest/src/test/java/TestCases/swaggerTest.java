package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

public class swaggerTest {
	
//	@Test
	public void test_getallpet(){
		
		given()
		
		.when()
			.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
			
		.then().statusCode(200);
			
	}
	
//	add new pet to the store
	public void test_AddNewPet(){
		String jsonSt = "{\"id\": 0,\"category\": {\"id\": 0,\"name\": \"string\"},\"name\": \"doggie\",\"photoUrls\": [\"string\"],\"tags\": [{\"id\": 0,\"name\": \"string\"}],\"status\": \"available\"}"; 
		Response res = 
				given()
					.contentType("application/json")
					.body(jsonSt)
				.when()
					.post("https://petstore.swagger.io/v2/pet")
					
				.then()
					.statusCode(200)
					.log().body()
					.extract().response();
		String jsonString = res.asString();
		Assert.assertEquals(jsonString.contains("name"), true);
	}
	
//update an existing pet 
	public void test_UpdateExistingPet(){
//		HashMap data = new HashMap();
//		data.put("id", "0");
//		data.put("category", "");
		String jsonSt = "{\"id\": 0,\"category\": {\"id\": 0,\"name\": \"string\"},\"name\": \"doggie\",\"photoUrls\": [\"string\"],\"tags\": [{\"id\": 0,\"name\": \"string\"}],\"status\": \"available\"}"; 
		Response res = 
				given()
					.contentType("application/json")
					.body(jsonSt)
				.when()
					.put("https://petstore.swagger.io/v2/pet")
					
				.then()
					.statusCode(200)
					.log().body()
					.extract().response();
		String jsonString = res.asString();
		Assert.assertEquals(jsonString.contains("name"), true);
	}
	
//	find pets by status
	@Test
	public void test_findPetBystatusAvailable(){
		Response res = 
				given()
					.contentType("application/json")
				.when()
					.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
					
				.then()
					.statusCode(200)
					.log().body()
					.extract().response();
		String jsonString = res.asString();
		Assert.assertEquals(jsonString.contains("name"), true);
//		Assert.assertEquals(jsonString.contains("9222968140497533000"), true);
	}
	@Test
	public void test_findPetByStatusPending(){
		Response res = 
				given()
					.contentType("application/json")
				.when()
					.get("https://petstore.swagger.io/v2/pet/findByStatus?status=pending")
					
				.then()
					.statusCode(200)
					.log().body()
					.extract().response();
		String jsonString = res.asString();
		Assert.assertEquals(jsonString.contains("name"), true);
//		Assert.assertEquals(jsonString.contains("9222968140497533000"), true);
	}
	@Test
	public void test_findPetByStatusSold(){
		Response res = 
				given()
					.contentType("application/json")
				.when()
					.get("https://petstore.swagger.io/v2/pet/findByStatus?status=sold")
					
				.then()
					.statusCode(200)
					.log().body()
					.extract().response();
		String jsonString = res.asString();
		Assert.assertEquals(jsonString.contains("name"), true);
//		Assert.assertEquals(jsonString.contains("70595026"), true);
	}
	
	@Test(priority=1)
	public void test_findPetByIdtrue(){
		Response res = 
				given()
					.contentType("application/json")
				.when()
					.get("https://petstore.swagger.io/v2/pet/1")
					
				.then()
					.statusCode(200)
					.log().body()
					.extract().response();
		String jsonString = res.asString();
//		System.out.print(jsonString);
		Assert.assertEquals(jsonString.contains("name"), true);
//		name is varying for every request
//		Assert.assertEquals(jsonString, "{\"id\":1,\"category\":{\"id\":0},\"name\":\"Zeus\",\"photoUrls\":[],\"tags\":[],\"status\":\"Vivo\"}");
		
	}
	
	@Test(priority=2)
	public void test_findPetByIdfalse(){
//		HashMap data = new HashMap();
//		data.put("petId", "0");
		Response res = 
				given()
					.contentType("application/json")
				.when()
					.get("https://petstore.swagger.io/v2/pet/0")
					
				.then()
					.statusCode(404)
					.log().body()
					.extract().response();
		String jsonString = res.asString();
		Assert.assertEquals(jsonString.contains("Pet not found"), true);
		
	}
	
//	updates a pet in the store with form data
	
	@Test(priority=3)
	public void test_updatePetByIdTrue(){
//		HashMap data = new HashMap();
		String jsonSt ="name=ruby&status=availabe";
		Response res = 
				given()
					.contentType("application/json")
					.body(jsonSt)
				.when()
					.post("https://petstore.swagger.io/v2/pet/1")
					
				.then()
					.statusCode(200)
					.log().body()
					.extract().response();
//		String jsonString = res.asString();
//		System.out.print(jsonString);
	}
	
	@Test(priority=4)
	public void test_updatePetByIdFalse(){
//		HashMap data = new HashMap();
//		data.put("name", "test");
//		data.put("status", "available");
		String jsonSt ="name=ruby&status=availabe";
		Response res = 
				given()
					.contentType("application/json")
					.body(jsonSt)
				.when()
					.post("https://petstore.swagger.io/v2/pet/0")
					
				.then()
					.statusCode(404)
					.log().body()
					.extract().response();
		String jsonString = res.asString();
		Assert.assertEquals(jsonString.contains("error"), true);
//		Assert.assertEquals(jsonString, "{\"code\":1,\"type\":\"error\",\"message\":\"Pet not found\"}");

		
	}
//	delete pet using petId
	
	@Test(priority=5)
	public void test_deletePetByIdTrue(){
		Response res = 
				given()
					.contentType("application/json")
				.when()
					.delete("https://petstore.swagger.io/v2/pet/1")
					
				.then()
					.statusCode(200)
					.log().body()
					.extract().response();
		String jsonString = res.asString();
		System.out.print(jsonString);
	}
	
	@Test(priority=6)
	public void test_deletePetByIdFalse(){
		Response res = 
				given()
					.contentType("application/json")
				.when()
					.delete("https://petstore.swagger.io/v2/pet/0")
					
				.then()
					.statusCode(404)
					.log().body()
					.extract().response();
		String jsonString = res.asString();
		Assert.assertEquals(jsonString, "");

		
	}

}
