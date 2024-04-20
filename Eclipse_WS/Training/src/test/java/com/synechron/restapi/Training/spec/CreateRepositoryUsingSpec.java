package com.synechron.restapi.Training.spec;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.synechron.restapi.Training.global.GlobalVariables;
import com.synechron.restapi.Training.pojo.CreateRepositoryPOJO;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class CreateRepositoryUsingSpec {

	public String project_Name = null;
	public RequestSpecBuilder reqBuilder = null;
	public ResponseSpecBuilder resBuilder = null;
	
	public RequestSpecification reqSpec = null;
	public ResponseSpecification resSpec = null;
	
	@BeforeClass
	public void initGlobals()
	{
		project_Name = "API_Demo_" + new Date().toString().replaceAll(" ", "_").replaceAll(":", "_");
		
		RestAssured.baseURI = "https://api.github.com";
		
		reqBuilder = new RequestSpecBuilder();
		reqBuilder.addHeader("Authorization", "Bearer " + GlobalVariables.git_token);
		reqBuilder.addHeader("content-type","application/json");
		reqBuilder.log(LogDetail.ALL);
		
		reqSpec = reqBuilder.build();
		
		
		resBuilder = new ResponseSpecBuilder();
		resBuilder.expectHeader("Server", "GitHub.com");
		resBuilder.log(LogDetail.ALL);
		resSpec = resBuilder.build();
	}
	
	@Test(priority = 1)
	public void createRepositoryUsingPOJO()
	{
		System.out.println("Creating repository using POJO");
		
		project_Name = "API_Demo_" + new Date().toString().replaceAll(" ", "_").replaceAll(":", "_");
		
		CreateRepositoryPOJO cp = new CreateRepositoryPOJO();
		cp.setName(project_Name);
		cp.setDescription(project_Name + "_Description");
		cp.setHomePage("https://github.com/Ab007007/");
		cp.setType(true);
		
		RestAssured.
			given().
				spec(reqSpec).
				body(cp).
			when().
				post("/user/repos").
			then().
				spec(resSpec).
				assertThat().statusCode(201)
				.body("name", CoreMatchers.equalTo(project_Name));
	}
	
	
	@Test(priority = 2)
	public void getRepository()
	{
		RestAssured.
			given().
				spec(reqSpec).
			when().
				get("/repos/Ab007007/" + project_Name).
			then().
				spec(resSpec);
		
	}
	
	
	@Test(priority = 3)
	public void getAllRepositories()
	{
		RestAssured.
			given().
				spec(reqSpec).
				queryParam("per_page", 100).
			when().
				get("/users/Ab007007/repos").
				then().
				spec(resSpec);
		
	}
	
	
	@Test(priority = 4)
	public void updateRepository()
	{
		
		CreateRepositoryPOJO repoObj = new CreateRepositoryPOJO();
		repoObj.setName("API_Demo_Sat_Apr_Updated");
		RestAssured.
			given().
				spec(reqSpec).
				body(repoObj).
			when().
				patch("/repos/Ab007007/" + project_Name).
			then().
			spec(resSpec).
				statusCode(200);
		
	}
	
	@Test(priority = 5)
	public void deleteRepository()
	{
		
		CreateRepositoryPOJO repoObj = new CreateRepositoryPOJO();
		repoObj.setName("API_Demo_Sat_Apr_Updated");
		RestAssured.
			given().
				spec(reqSpec).
			when().
				delete("/repos/Ab007007/API_Demo_Sat_Apr_Updated").
			then().
				spec(resSpec).
				statusCode(204);
			
		
	}
	
	
	
	
	
}
