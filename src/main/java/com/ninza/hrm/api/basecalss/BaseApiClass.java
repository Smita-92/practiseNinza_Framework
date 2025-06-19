package com.ninza.hrm.api.basecalss;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericUtility.DataBaseUtility;
import com.ninza.hrm.api.genericUtility.FileUtility;
import com.ninza.hrm.api.genericUtility.JavaUtility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseApiClass {
	 public JavaUtility jLib = new JavaUtility();
	 public FileUtility fLib = new FileUtility();
	  public DataBaseUtility dbLib = new DataBaseUtility();
	  public static RequestSpecification ReqSpecObj;
	  public static ResponseSpecification RespSpecObj;
	

	@BeforeSuite
	public void configBS() throws SQLException, IOException {
		dbLib.getDBConnection();
		System.out.println("=========CONNECTED=======");
		
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		//builder.setAuth(basic("username", "password"));
		//builder.addHeader("", "");
		builder.setBaseUri(fLib.getDataFromPropertyFile("BaseURI"));
		ReqSpecObj = builder.build();
		
		ResponseSpecBuilder SpecObj= new ResponseSpecBuilder();
		SpecObj.expectContentType(ContentType.JSON);
		RespSpecObj = SpecObj.build();
	}

	@AfterSuite
	public void configAS() {
		dbLib.closeDBConnection();
		System.out.println("=========DISCONNECTED=======");

	}
}
