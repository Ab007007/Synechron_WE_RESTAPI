package com.synechron.restapi.Training.fomat;

import com.synechron.restapi.Training.global.GlobalVariables;

import io.restassured.RestAssured;

public class GivenExpectWhenFormat {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://api.trello.com";
		
		RestAssured.
			given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).
			expect().
				statusCode(200).
			when().
				get("1/boards/"+GlobalVariables.boardID);
	}
}
