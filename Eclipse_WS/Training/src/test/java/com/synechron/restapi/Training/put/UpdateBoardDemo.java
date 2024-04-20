package com.synechron.restapi.Training.put;

import org.testng.annotations.Test;

import com.synechron.restapi.Training.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateBoardDemo {

	@Test
	public void updateBoard()
	{
		RestAssured.baseURI = "https://api.trello.com";
			Response response = 
				RestAssured.
					given().
						queryParam("key", GlobalVariables.key).
						queryParam("token", GlobalVariables.token).
						queryParam("name", "Eclipse-Updated-Board").
						header("Content-type","application/json").
					when().
						put("/1/boards/6623423c64a8d29b37c7a08d");
			response.prettyPrint();
			
			response.
				then().statusCode(200).log().all();
			//6623423c64a8d29b37c7a08d
			
	
	}
	
}
