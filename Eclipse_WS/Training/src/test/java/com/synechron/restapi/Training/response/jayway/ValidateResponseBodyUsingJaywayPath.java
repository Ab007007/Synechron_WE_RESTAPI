package com.synechron.restapi.Training.response.jayway;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.synechron.restapi.Training.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ValidateResponseBodyUsingJaywayPath {

	
	Response response = null;
	
	@BeforeTest
	public void getResponse()
	{
		System.out.println("API Call Startd !!!");
		response =
				RestAssured.
					 given().
						param("key", GlobalVariables.key).
						param("token", GlobalVariables.token).
					when().
						get("https://api.trello.com/1/boards/"+GlobalVariables.boardID);
		//response.prettyPrint();
		
		System.out.println("Response is saved in a response object");
	
	}
	
	@BeforeMethod
	public void beforeTest()
	{
	
		System.out.println("---------------------------------------------");
		
	}
	
	@AfterMethod
	public void afterTest(ITestResult result)
	{
		System.out.println("Executed Test " + result.getName());
		System.out.println("#####################################################");
	}
	@Test(priority = 1)
	public void getID()
	{
		System.out.println("Test to Print ID");
		String id = JsonPath.read(response.asString(), "$.id");
		System.out.println("Printing ID " + id);
		Assert.assertEquals(GlobalVariables.boardID, id);
	}

	@Test(priority = 2)
	public void getName()
	{
		System.out.println("Test to Print Name");
		String name = JsonPath.read(response.asString(), "$.name");
		System.out.println("Printing Name " + name);
	}
	
	@Test(priority = 3)
	public void getAllUrlsFrombackgroundImageScaled()
	{
		System.out.println("Test to Print All URLs");
		
		
		
		
		List<String> urls =  JsonPath.read(response.asString(), "$.prefs.backgroundImageScaled.[*].url");
		for (String url : urls) {
			System.out.println("URL : " + url );
		}
	}
	
	@Test(priority = 4)
	public void getFirstElementInBGIScaled()
	{
		Map<String, ?> item = JsonPath.read(response.asString(), "$.prefs.backgroundImageScaled.[0]");
		Set<String> keys = item.keySet();
		for (String key : keys) {
			System.out.println("key is " + key + "  and the value is " + item.get(key));
		}
	}
	
	@Test
	public void getAllBGIScaled()
	{
		List<Map<String, ?>> allElements = JsonPath.read(response.asString(), "$.prefs.backgroundImageScaled");
		System.out.println("Total number of maps present in list " + allElements.size());
		
		for (int i = 0; i < allElements.size() ; i++) {
			Map<String, ?> mapElement = allElements.get(i);
			System.out.println("**********************Element at index " + i + " is ***************************************");
			Set<String> keys = mapElement.keySet();
			for (String key : keys) {
				System.out.println("key is " + key + "  and the value is " + mapElement.get(key));
			}
		}
	}
	
	@Test
	public void getAllWidthGreaterThan1000()
	{
		List<Map<String, ?>> allElements = JsonPath.read(response.asString(), "$.prefs.backgroundImageScaled.[?(@.width>1000)]");
		System.out.println("Total number of maps present in list " + allElements.size());
		
		for (int i = 0; i < allElements.size() ; i++) {
			Map<String, ?> mapElement = allElements.get(i);
			System.out.println("**********************Element at index " + i + " is ***************************************");
			Set<String> keys = mapElement.keySet();
			for (String key : keys) {
				System.out.println("key is " + key + "  and the value is " + mapElement.get(key));
			}
		}
	}
	
	@Test
	public void getAllUrlWithWidthGreaterThan1000()
	{
		List<String> allUrls = JsonPath.read(response.asString(), "$.prefs.backgroundImageScaled.[?(@.width>1000)].url");
		for (String url : allUrls) {
			System.out.println(url);
		}
		
	}
	
}
