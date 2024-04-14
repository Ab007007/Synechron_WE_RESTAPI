package com.synechron.restapi.Training.fomat;

import com.synechron.restapi.Training.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredClassFormat {

	public static void main(String[] args) {

		System.out.println("Rest Assured Call Started");
		RequestSpecification reqSpec = RestAssured.given();
			reqSpec.param("key", GlobalVariables.key);
			reqSpec.param("token", GlobalVariables.token);
		
		Response res = reqSpec.get("https://api.trello.com/1/boards/"+GlobalVariables.boardID);
		
		res.prettyPrint();
		
		res.then().statusCode(200);
		System.out.println("Rest Assured Call Ended");
		
	}

}
