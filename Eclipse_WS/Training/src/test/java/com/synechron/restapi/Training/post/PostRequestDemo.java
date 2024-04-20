package com.synechron.restapi.Training.post;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.synechron.restapi.Training.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostRequestDemo {

	static int counter = 1;
	@BeforeMethod
	public void initGlobals() {
		RestAssured.baseURI = "https://api.trello.com";
	}
	
	//@Test
	public void createBoard() {
		Response response = 
			RestAssured.
				given().
					queryParam("key", GlobalVariables.key).
					queryParam("token", GlobalVariables.token).
					queryParam("name", "Eclipse-Created-Board").
					header("Content-type","application/json").
				when().
					post("/1/boards");
		response.prettyPrint();
		
		response.
			then().statusCode(200);
		//6623423c64a8d29b37c7a08d
		
	}
	
	//@Test
	public void createList() {
		Response response = 
				RestAssured.
					given().
						queryParam("key", GlobalVariables.key).
						queryParam("token", GlobalVariables.token).
						queryParam("idBoard", "6623423c64a8d29b37c7a08d").
						queryParam("name", "Eclipse-Created-List").
						header("Content-type","application/json").
					when().
						post("/1/lists");
			response.prettyPrint();
			
			response.
				then().statusCode(200);
			
//			6623431cca5d0f45a2059f9e
	}
	
	//@Test(invocationCount = 10)
	public void createCard() {
		Response response = 
				RestAssured.
					given().
						queryParam("key", GlobalVariables.key).
						queryParam("token", GlobalVariables.token).
						queryParam("idList", "6623431cca5d0f45a2059f9e").
						queryParam("name", "Eclipse-Created-Card"+ counter++).
						header("Content-type","application/json").
					when().
						post("/1/cards");
			response.prettyPrint();
			
			response.
				then().statusCode(200);
			
	}
	
	@Test(invocationCount = 10)
	public void createCardUsingFakerAPI() {
		Response response = 
				RestAssured.
					given().
						queryParam("key", GlobalVariables.key).
						queryParam("token", GlobalVariables.token).
						queryParam("idList", "6623431cca5d0f45a2059f9e").
						queryParam("name", new Faker().animal().name()).
						header("Content-type","application/json").
					when().
						post("/1/cards");
			response.prettyPrint();
			
			response.
				then().statusCode(200);
			
	}
	
}
