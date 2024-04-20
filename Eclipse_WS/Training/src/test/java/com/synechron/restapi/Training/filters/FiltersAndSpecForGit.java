package com.synechron.restapi.Training.filters;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Date;

import org.apache.commons.io.output.WriterOutputStream;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.synechron.restapi.Training.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FiltersAndSpecForGit {

	public PrintStream reqPrinter = null;
	public PrintStream resPrinter = null;

	public StringWriter reqWriter = null;
	public StringWriter resWriter = null;

	
	public RequestSpecBuilder reqBuilder = null;
	public ResponseSpecBuilder resBuilder = null;
	
	public RequestSpecification reqSpec = null;
	public ResponseSpecification resSpec = null;
	
	
	@BeforeMethod
	public void initConstants()
	{
		RestAssured.baseURI = "https://api.github.com";
		reqWriter = new StringWriter();
		resWriter = new StringWriter();
		reqPrinter = new PrintStream(new WriterOutputStream(reqWriter), true);
		resPrinter = new PrintStream(new WriterOutputStream(resWriter), true);
		
		
		reqBuilder = new RequestSpecBuilder();
		reqBuilder.addHeader("Authorization", "Bearer " + GlobalVariables.git_token);
		reqBuilder.addHeader("content-type","application/json");
		reqBuilder.log(LogDetail.ALL);
		reqBuilder.addFilter(new RequestLoggingFilter(reqPrinter));
		reqBuilder.addFilter(new ResponseLoggingFilter(resPrinter));
		
		reqSpec = reqBuilder.build();
		
		
		resBuilder = new ResponseSpecBuilder();
		resBuilder.expectHeader("Server", "GitHub.com");
		resBuilder.log(LogDetail.ALL);
		resSpec = resBuilder.build();
		
		
	}
	
	@Test
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
	@Test
	public void getRepository()
	{
		RestAssured.
			given().
				spec(reqSpec).
			when().
				get("/repos/Ab007007/Synechron_WE_RESTAPI").
				then().
				spec(resSpec);
		
	}
	
	@AfterMethod
	public void saveLogsToFile(ITestResult result) throws IOException
	{
		String log_name = result.getName() + "_" + new Date().toString().replaceAll(" ", "_").replaceAll(":", "_");
		
		File f = new File("logs/"+log_name + ".log");
		System.out.println(f.toString());
		if(!f.exists())
		{
			System.out.println("File Created : " + f.getAbsolutePath());
			f.createNewFile();
		}
		
		
		FileWriter fw = new FileWriter(f);
		fw.write(reqWriter.toString());
		System.out.println("#####################################");
		fw.write("#####################################");
	
		fw.write(resWriter.toString());
		
		fw.flush();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
