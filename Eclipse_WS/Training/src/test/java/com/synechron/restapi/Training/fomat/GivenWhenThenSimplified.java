package com.synechron.restapi.Training.fomat;

import com.synechron.restapi.Training.global.GlobalVariables;

import io.restassured.RestAssured;

public class GivenWhenThenSimplified {

	
	public static void main(String[] args) 
	{
		System.out.println("Rest Assured Call Started");
		RestAssured.baseURI = "https://api.trello.com/";
		
		RestAssured.
			given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).
			when().
				get("1/boards/"+GlobalVariables.boardID).
			then().
				assertThat().statusCode(200);
		System.out.println("Rest Assured Call Ended");
		
	}
}
