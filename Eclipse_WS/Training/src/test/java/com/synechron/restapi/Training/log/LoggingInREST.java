package com.synechron.restapi.Training.log;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import com.synechron.restapi.Training.global.GlobalVariables;

import io.restassured.RestAssured;

public class LoggingInREST {

	
	@Test
	public void getLogsFromGiven()
	{
		System.out.println("Rest Assured Call Started");
		RestAssured.
			given().log().headers().log().parameters().
				queryParam("key", GlobalVariables.key).
				queryParam("token", GlobalVariables.token).
				header("Accept", "application/json").
			when().
				get("https://api.trello.com/1/boards/"+GlobalVariables.boardID).
			then().log().all().			
				assertThat().statusCode(200).
				body("name"	, CoreMatchers.equalTo("PostMan-Dashboard-New")).and().
				body("id", CoreMatchers.equalTo("661b5b3982192b7a94938f42"));
		System.out.println("Rest Assured Call Ended");
	}
	
	
	@Test
	public void getAllLogsFromGiven()
	{
		System.out.println("Rest Assured Call Started*******************************");
		RestAssured.
			given().log().all().
				queryParam("key", GlobalVariables.key).
				queryParam("token", GlobalVariables.token).
				header("Accept", "application/json").
			when().
				get("https://api.trello.com/1/boards/"+GlobalVariables.boardID).
			then().log().ifStatusCodeIsEqualTo(200).			
				assertThat().statusCode(200).
				body("name"	, CoreMatchers.equalTo("PostMan-Dashboard-New")).and().
				body("id", CoreMatchers.equalTo("661b5b3982192b7a94938f42"));
		System.out.println("Rest Assured Call Ended");
	}
	
}
