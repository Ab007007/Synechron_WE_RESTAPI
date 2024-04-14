package com.synechron.restapi.Training.fomat;

import org.hamcrest.CoreMatchers;

import com.synechron.restapi.Training.global.GlobalVariables;

import io.restassured.RestAssured;

public class GivenWhenThenDemo {

	public static void main(String[] args) 
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
}
