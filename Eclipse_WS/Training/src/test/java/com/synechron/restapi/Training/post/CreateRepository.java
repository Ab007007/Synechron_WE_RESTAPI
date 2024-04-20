package com.synechron.restapi.Training.post;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.synechron.restapi.Training.global.GlobalVariables;
import com.synechron.restapi.Training.pojo.CreateRepositoryPOJO;

import io.restassured.RestAssured;

public class CreateRepository {

	public String project_Name = null;
	
	@BeforeClass
	public void initGlobals()
	{
		RestAssured.baseURI = "https://api.github.com";
		project_Name = "API_Demo_" + new Date().toString().replaceAll(" ", "_").replaceAll(":", "_");
		
	}
	
	//@Test
	public void createRepository()
	{
		
		
		String payloadAsString = "{\r\n"
				+ "    \"name\" : \""+project_Name+"\",\r\n"
				+ "    \"description \":\""+project_Name+"\",\r\n"
				+ "    \"homepage\" : \"https://github.com/Ab007007/\" ,\r\n"
				+ "    \"private\" : false\r\n"
				+ "\r\n"
				+ "}";
		
		
		RestAssured.
			given().
				headers("Authorization", "Bearer " + GlobalVariables.git_token).
				headers("content-type","application/json").
				body(payloadAsString).log().all().
			when().
				post("/user/repos").
			then().
				assertThat().statusCode(201)
				.body("name", CoreMatchers.equalTo(project_Name));
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
				headers("Authorization", "Bearer " + GlobalVariables.git_token).
				headers("content-type","application/json").
				body(cp).log().all().
			when().
				post("/user/repos").
			then().
				assertThat().statusCode(201)
				.body("name", CoreMatchers.equalTo(project_Name));
	}
	
	
	
	@Test(priority = 2)
	public void getRepository()
	{
		RestAssured.
			given().
				headers("Authorization", "Bearer " + GlobalVariables.git_token).
				headers("content-type","application/json").
			when().
				get("/repos/Ab007007/" + project_Name).then().log().all();
		
	}
	
	
	@Test(priority = 3)
	public void getAllRepositories()
	{
		RestAssured.
			given().
				headers("Authorization", "Bearer " + GlobalVariables.git_token).
				headers("content-type","application/json").
				queryParam("per_page", 100).
				
			when().
				get("/users/Ab007007/repos").then().log().all();
		
	}
	
	
	@Test(priority = 4)
	public void updateRepository()
	{
		
		CreateRepositoryPOJO repoObj = new CreateRepositoryPOJO();
		repoObj.setName("API_Demo_Sat_Apr_Updated");
		RestAssured.
			given().
				headers("Authorization", "Bearer " + GlobalVariables.git_token).
				headers("content-type","application/json").
				body(repoObj).
			when().
				patch("/repos/Ab007007/" + project_Name).
			then().
				log().all().and().
				statusCode(200);
		
	}
	
	@Test(priority = 5)
	public void deleteRepository()
	{
		
		CreateRepositoryPOJO repoObj = new CreateRepositoryPOJO();
		repoObj.setName("API_Demo_Sat_Apr_Updated");
		RestAssured.
			given().
				headers("Authorization", "Bearer " + GlobalVariables.git_token).
				headers("content-type","application/json").
			when().
				delete("/repos/Ab007007/API_Demo_Sat_Apr_Updated").
			then().
				log().all().and().
				statusCode(204);
			
		
	}
	
	
	
	
	
}
