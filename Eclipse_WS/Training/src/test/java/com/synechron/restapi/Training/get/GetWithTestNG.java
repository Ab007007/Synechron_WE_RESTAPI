package com.synechron.restapi.Training.get;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import com.synechron.restapi.Training.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetWithTestNG {

	@Test
	public void getInGivenExpectWhenFormat() {
		RestAssured.baseURI = "https://api.trello.com";

		RestAssured.
		given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).
		expect()
				.statusCode(200).
		when().
				get("1/boards/" + GlobalVariables.boardID);
	}
	
	@Test
	public void getRequestInGivenWhenFormat()
	{
		System.out.println("Rest Assured Call Started");
		RestAssured.
			given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).
			when().
				get("https://api.trello.com/1/boards/"+GlobalVariables.boardID).
			then().
				assertThat().statusCode(200).
				body("name"	, CoreMatchers.equalTo("PostMan-Dashboard-New")).and().
				body("id", CoreMatchers.equalTo("661b5b3982192b7a94938f42"));
		System.out.println("Rest Assured Call Ended");
	}
	
	@Test
	public void getInRestAssuredFormat()
	{
		System.out.println("Rest Assured Call Started");
		RequestSpecification reqSpec = RestAssured.given();
			reqSpec.param("key", GlobalVariables.key);
			reqSpec.param("token", GlobalVariables.token);
		
		Response res = reqSpec.get("https://api.trello.com/1/boards/"+GlobalVariables.boardID);
		
		res.prettyPrint();
		
		res.then().statusCode(200);
		System.out.println("Rest Assured Call Ended");
	}
	
	
	@Test
	public void printAllCards()
	{
		
		RestAssured.
		given().
			param("key", GlobalVariables.key).
			param("token", GlobalVariables.token).
		when().
			get("https://api.trello.com/1/lists/6623431cca5d0f45a2059f9e/cards").prettyPrint();
		
	}
}
