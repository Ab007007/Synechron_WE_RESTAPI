package com.synechron.restapi.Training.hamcrest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.synechron.restapi.Training.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class ValidationUsingHamCrest {

	ValidatableResponse response = null;
	@BeforeTest
	public void executeAPI() {
		baseURI = "https://api.trello.com";
		
		response = given().
			param("key", GlobalVariables.key).
			param("token", GlobalVariables.token).
		when().
			get("/1/boards/"+GlobalVariables.boardID).
		then().
			statusCode(200);
	}
	
	
	@Test
	public void validateBody()
	{
		response.
		body("id", equalTo("66248cc595c07429131a1121")).and().
		body("name",  equalTo("PostMan-Dashboard12"));
	}

	@Test
	public void verifyHeader()
	{
		response.
		header("Expires", "Thu, 01 Jan 1970 00:00:00").and().
		header("Content-Type", "application/json; charset=utf-8");
	}
	
	@Test
	public void verifyMultipleValues()
	{
		response.body("prefs.backgroundImageScaled.width", hasItems(133,480,960,1024,1280,1920)).and().
		body("prefs.backgroundImageScaled.height", hasItems(360,720,768,960,1440,1536));
	}

	@Test
	public void verifyMultipleValuesNew()
	{
		response.
		body("prefs.backgroundImageScaled.width", hasItems(133,480,960,1024,1280,1920)).and().
		body("prefs.backgroundImageScaled.height", hasItems(360,720,768,960,1440,1536)).and().
		body("prefs.backgroundImageScaled.size()", equalTo(10));
		
	}


}


