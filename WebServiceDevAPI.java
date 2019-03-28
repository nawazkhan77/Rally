package com.om.test;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.om.lib.Utils;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import org.apache.commons.httpclient.methods.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/*
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
 */
/** 
 * 
 * @author KhaderKhan
 * 
 * Integration of Rally integration into test Automation suite.
 *
 */

public class WebServiceTest {
	
	public Utils util = new Utils();
	
	public void RallyIntegration() throws URISyntaxException, IOException
	{
		/*

		        String host = "https://rally1.rallydev.com";
		            String username = "user@co.com";
		            String password = "secret";
		            String projectRef = "/project/2222";
		            String workspaceRef = "/workspace/1111"; 
		            String applicationName = "RESTExampleStoriesChildren";


		        RallyRestApi restApi = new RallyRestApi(
		                new URI(host),
		                username,
		                password);
		        restApi.setApplicationName(applicationName); 

		        System.out.println(restApi.getWsapiVersion()); //v.2.0 by default when using 2.0.2 jar


		        QueryRequest storyRequest = new QueryRequest("HierarchicalRequirement");
		        storyRequest.setFetch(new Fetch("Name","Owner","UserName", "EmailAddress"));
		        storyRequest.setLimit(1000);
		        storyRequest.setScopedDown(false);
		        storyRequest.setScopedUp(false);
		        storyRequest.setWorkspace(workspaceRef);
		        storyRequest.setProject(projectRef);
		        storyRequest.setQueryFilter(new QueryFilter("FormattedID", "=", "US16"));

		        QueryResponse storyQueryResponse = restApi.query(storyRequest);
		        JsonObject storyJsonObject = storyQueryResponse.getResults().get(0).getAsJsonObject();

		        System.out.println("Name: " + storyJsonObject.get("Name"));

		        JsonObject userObject = storyJsonObject.get("Owner").getAsJsonObject().getAsJsonObject();
		        System.out.println(userObject.get("UserName"));
		        System.out.println(userObject.get("EmailAddress")); 
		
		*/
		
	}	
	
	
	
	
	@Test(priority = 1, groups = { "smoke" }, description = "simple webservice test")
	public void testWeb() throws InterruptedException
    {
			// TODO Auto-generated method stub
		try {
			
				System.out.println("Webservice test case 1");
				
				
				// Extract the OBject ID and Owner ID of specified test case
				
				
				//String strUrl = "https://rally1.rallydev.com/slm/webservice/v2.0/testcase?query=(FormattedID = TC1499)&fetch=true"; // wont work due to URL not encoded
				
				String strUrl = "https://rally1.rallydev.com/slm/webservice/1.41/testcase?query=%28FormattedID%20=%20TC734%29&fetch=true";

				//String strUrl = "https://rally1.rallydev.com/slm/webservice/1.41/testcaseresult/13639652648";
				HttpClient client = new HttpClient();
				client.getState().setCredentials(
					new AuthScope("rally1.rallydev.com", 443),
					new UsernamePasswordCredentials( util.getTestData("usercred.txt", "USERNAME"), util.getTestData("usercred.txt", "PASSWORD"))
					);
				
				GetMethod getMethod = new GetMethod(strUrl);
	
				client.executeMethod(getMethod);
				String result = getMethod.getResponseBodyAsString();
				
				System.out.println("XML GET response:"+result);
				
				// ***************************************END OF GET ******************************************************************************************
				
				System.out.println("POST Test RESULTS ************");
			
				String XMLpayload = util.getFileContents("/payload.xml");
				
				System.out.println("Payload:"+XMLpayload);
				
				strUrl = "https://rally1.rallydev.com/slm/webservice/1.41/testcaseresult/create";
				
				PostMethod post = new PostMethod(strUrl);
				StringRequestEntity entity = new StringRequestEntity(XMLpayload);
				post.setRequestEntity(entity);
				client = new HttpClient();
				client.getState().setCredentials(
						new AuthScope("rally1.rallydev.com", 443),
						new UsernamePasswordCredentials( util.getTestData("usercred.txt", "USERNAME"), util.getTestData("usercred.txt", "PASSWORD"))
						);
				
				int statusCode=client.executeMethod(post);

				String postresult = post.getResponseBodyAsString();
				
				System.out.println("post Status="+statusCode);
				System.out.println("post Result="+postresult);
				
				
			}
		
			catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
}
