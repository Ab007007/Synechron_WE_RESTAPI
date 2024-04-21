package com.synechron.restapi.Training.rootpath;

import static io.restassured.RestAssured.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.synechron.restapi.Training.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RootPathDemo {

	
	
	@Test
	public void getAllUrlsFrombackgroundImageScaled()
	{
		
		System.out.println("Test to Print All URLs");
		System.out.println("API Call Startd !!!");
		baseURI = "https://api.trello.com";
		rootPath = "prefs.backgroundImageScaled";
		 
			 given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).
			when().
				get("/1/boards/"+GlobalVariables.boardID).
			then().
				body("width", hasItem(130003));
			 
		reset();
		
	
	}
}
