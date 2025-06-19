package com.ninza.hrm.api.genericUtility;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonUtility {
	FileUtility fLib=new FileUtility();
	
	
	//get the JsonData Based on Complex xpath
	public String getDataONJsonPath(Response resp,String jsonxpath) {
	List<Object> list=JsonPath.read(resp.asString(), jsonxpath);
	return list.get(0).toString();
		
	}
	
	//get the xml data Based on xml Complex xpath
	public String getDataONXpathPath(Response resp,String XmlXpath) {
	return resp.xmlPath().get(XmlXpath);
	}
	
	//verify the data in jsonbody based on jsonPath
	public boolean verifyDataOnJsonPath(Response resp,String jsonxpath ,String expectedData) {
	List<String> list=JsonPath.read(resp.asString(),jsonxpath);	
	  boolean flag = false;
	for(String str : list) {
		if(str.equals(expectedData)) {
			System.out.println(expectedData + "is available===pass");
			flag=true;		
		}		
	}
	if(flag==false)
	{
	System.out.println(expectedData + "is not available===pass");	
	}
	return false;	
	}
	
	//to get the acess token
	public String getAccessToken() throws IOException {
	Response resp = given()
				.formParam("client_id",fLib.getDataFromPropertyFile("ClientID") )
				.formParam("client_secret", fLib.getDataFromPropertyFile("ClientSecret"))
				.formParam("grant_type", "client_credentials")
				.when()
				.post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
				resp.then().log().all();
	//to capture the data from the response
	String token=resp.jsonPath().get("access_token");
	return token;
	}

}
