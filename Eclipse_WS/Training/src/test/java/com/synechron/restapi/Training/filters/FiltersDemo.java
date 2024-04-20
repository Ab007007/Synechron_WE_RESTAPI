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
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class FiltersDemo {

	public PrintStream reqPrinter = null;
	public PrintStream resPrinter = null;

	public StringWriter reqWriter = null;
	public StringWriter resWriter = null;

	@BeforeMethod
	public void initConstants()
	{
		RestAssured.baseURI = "https://api.trello.com";
		reqWriter = new StringWriter();
		resWriter = new StringWriter();
		reqPrinter = new PrintStream(new WriterOutputStream(reqWriter), true);
		resPrinter = new PrintStream(new WriterOutputStream(resWriter), true);
	}
	
	@Test
	public void getInGivenExpectWhenFormat() {
		
		RestAssured.
		given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).log().all().
				filter(new RequestLoggingFilter(reqPrinter)).
				filter(new ResponseLoggingFilter(resPrinter)).
		expect()
				.statusCode(200).log().all().
		when().
				get("1/boards/" + GlobalVariables.boardID);
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
