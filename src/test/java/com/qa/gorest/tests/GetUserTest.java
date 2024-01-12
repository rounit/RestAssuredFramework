package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

public class GetUserTest extends BaseTest
{
	@BeforeMethod
	public void getUSerSetup()
	{
		restClient = new RestClient(prop,baseURI);
	}
	
	
	@Test(enabled = true, priority = 1)
	public void getAllUsers()
	{
		restClient.get(GOREST_ENDPOINT, true,true)
		           .then().log().all()
		                 .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
	}
	
	@Test(priority = 2)
	public void getUserTest()
	{
		restClient = new RestClient(prop,baseURI);
		 restClient.get(GOREST_ENDPOINT+"/"+5889194, true,true)
		           .then().log().all()
		                 .assertThat().statusCode(APIHttpStatus.OK_200.getCode())
		                      .and().body("id", equalTo(5889194));
	}
	
	@Test
	public void getUserWithQueryParamsTest()
	{
		RestClient restClient1 = new RestClient(prop,baseURI);
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("name", "Kamla");
		queryParams.put("status", "active");
		
	restClient1.get(GOREST_ENDPOINT, queryParams, null, true,true)
		           .then().log().all()
		                 .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
		                   
	}

}
