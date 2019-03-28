package com.om.test;

import com.om.data.RallyQueryRequestData;
import org.apache.log4j.Logger;

import org.testng.annotations.Test;
import com.om.lib.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.transform.*; 
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;


/** 
 * 
 * @author KhaderKhan
 * 
 * Integration of Rally integration into test Automation suite.
 *
 */

public class WebServiceRally {
	
	public Utils util = new Utils();
	public String ownerID=null;
	public String testCaseID=null;
	public String priority=null;
	public String payload=null;
	public String testMethod=null;
	
	public  Map<String, String> TestResult;
	
//	@Test(priority = 1, groups = { "smoke9999" }, description = "Rally Integration Bulk Update")
	public void updateBulkResult() throws InterruptedException
    {
			
			//System.out.println("BULK Update -- testID  :"+testCaseID);
			//System.out.println("ownerID :"+ownerID);
			
			/******************************** For BULK update ********************/
			createTestResultSet(); 
			//print the test table from hashmap
			String currDate = getCurrentDate();
			String buildName = "Sprint 21";
			
			for ( String testID : TestResult.keySet() )
			{
			     String testStat = TestResult.get( testID );
			     System.out.println("Test case ID ="+testID+"\t ->"+testStat);
			     String testStatus = testStat;
			     testStatus = "PASS";
			     getTestInfo(testID);
			     if (testStatus.equals("PASS"))
			     {
			    	 System.out.println("Test case ID ="+testID+"\t ->"+testStat);
				     String payLoad = createPayloadXML(testCaseID, ownerID, priority, currDate, buildName, testStatus);
				     //System.out.println("\npayload =\n"+payLoad);
				     postWeb(payLoad);
			     }
			 }
			
			/***************************** BULK Update *****************************/
    }
	
	
	@Test(priority = 1, groups = { "smoke9999" }, description = "Rally Integration")
	public void updateTestResult() throws InterruptedException
    {
		
			//String strIds[] = {"TC849","TC855","TC862","TC933","TC1278","TC1374","TC931","TC1135"}; //not automated
			
			//vinod's
			//String strIds[] = {"TC1160","TC1161","TC1162","TC1029","TC1032","TC1119","TC1049","TC1050","TC1051","TC1126","TC1268"}; // pass
			
			//String strIds[] = {"TC1110", "TC1093", "TC901", "TC1136", "TC700", "TC1155", "TC883", "TC1411", "TC887"}; // passed
			
			//manual execution
			//String strIds[] = {"TC1286","TC1287", "TC1326", "TC1338", "TC1368",  "TC1385", "TC1389", "TC1398",  "TC1400", "TC1404", "TC1424", "TC1431","TC1435","TC1442","TC1443","TC1495","TC1599"};
			
			//manual execution
			//String strIds[] = {"TC1294","TC1339", "TC1343", "TC1513", "TC1514"}; // failed
			
			//String strIds[] = {"TC1051","TC726","TC733","TC1127","TC1128","TC995","TC994","TC1382","TC1383","TC1384","TC1377","TC1376","TC1143","TC1253","TC1221","TC1223","TC1222","TC700","TC1372","TC801","TC701","TC911","TC909","TC942"};
			
			//String strIds1[] = {"TC2245","TC2200","TC2100","TC2101","TC2147","TC2146"};
		
			Utils ut = new Utils();
try {
			List<String> strList = ut.readTestData("c:\\temp\\pass1.txt");
			String[] strIds = new String[strList.size()];
			strList.toArray(strIds);
			
			String currDate = getCurrentDate();
			String buildName = "OM47PatchGA Build";
			//String testStatus = "Fail";
			String testStatus = "Pass";
			
			//System.out.println("testID  :"+testCaseID);
			//System.out.println("ownerID :"+ownerID);
			
			for (String id : Arrays.asList(strIds))
			{
				//System.out.println("test case id:"+id);
				getTestInfo(id);
				/*System.out.println("********** tid="+testCaseID);
				System.out.println("owner="+ownerID);
				System.out.println("priority="+priority);
				System.out.println("testMethod="+testMethod);
				System.out.println("verdict="+testStatus);*/
				
				System.out.println(id+","+priority+","+testMethod);
				
				
				if (testMethod.equals("Automated") && priority.equals("Smoke"))
				{
					String payLoad = createPayloadXML(testCaseID, ownerID, priority, currDate, buildName, testStatus);
					//System.out.println("\npayload =\n"+payLoad);
					postWeb(payLoad);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			}	
			 //TC767 // not auto TC849, TC855, TC862, TC933,TC1278,TC1374,TC931,TC1135
    }
	
	public String getCurrentDate()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public void createTestResultSet()
	{
		// Parse testNG XML file and create hash table testid = status
		try{
		     
			ownerID=null;
			testCaseID=null;

			System.out.println("Parse n Read TESTNG XML");
			//File file = new File("C:\\ficoworkspace\\QE\\OM45AutomationFramework\\target\\surefire-reports\\testng-results.xml");
			File file = new File("C:\\Temp\\testng-results.xml");
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
	       
			doc.getDocumentElement().normalize();
	       
			NodeList nodes = doc.getElementsByTagName("test-method");
			//System.out.println("no of tests ="+nodes.getLength());
			
			String Desc=null;
			TestResult = new HashMap<String, String>();
			for (int i = 0; i < nodes.getLength(); i++) 
			{
			      Element element = (Element) nodes.item(i);
			      Desc=element.getAttribute("description");
			      String tid[]=Desc.split(":");
			      System.out.println(""+tid[0].trim()+","+element.getAttribute("name")+","+element.getAttribute("status"));
				
			      //System.out.println("test description="+Desc);
			      //System.out.println("test stat="+element.getAttribute("status"));
			      
			      TestResult.put( tid[0].trim(), element.getAttribute("status"));
			}

	   }
	   catch (Exception e){
		   	System.out.println("Caught XML Exception !!!" + e.getMessage() );
		   	e.printStackTrace();

	   }

	}
	
	public void getTestInfo(String tcID) throws InterruptedException
    {
			// TODO Auto-generated method stub
		try {
			
				//System.out.println("Get API Details");
				
				String wsURL = constructQuery(tcID);
				
				HttpClient client = new HttpClient();
				client.getState().setCredentials(
					new AuthScope("rally1.rallydev.com", 443),
					new UsernamePasswordCredentials( util.getTestData("usercred.txt", "USERNAME"), util.getTestData("usercred.txt", "PASSWORD"))
					);
				
				GetMethod getMethod = new GetMethod(wsURL);
	
				int statusCode=client.executeMethod(getMethod);
				String result = getMethod.getResponseBodyAsString();
//				System.out.println("GET Status code:"+statusCode+"\n");
//				System.out.println("XML GET response:"+result+"\n");
				
				
				//Extract the OBject ID and Owner ID of specified test case
				
				parseXMLObject(result);
				
				// *************************************** END OF GET **************************************************************************
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
		
	public String constructQuery(String tcID)
	{
		
		// "https://rally1.rallydev.com/slm/webservice/1.41/testcase?query=(FormattedID%20=%20TC767)&fetch=ObjectID,Owner";
		String temp = RallyQueryRequestData.queryURL+"query=("+RallyQueryRequestData.formatID+"%20=%20"+tcID+")&fetch="+RallyQueryRequestData.objID+","+RallyQueryRequestData.owner+","+"Priority"+","+"Method";
		//System.out.println("QueryURL ="+temp);
		return temp;
		
	}
	
	public void parseXMLObject(String XMLObjectRally)
	{
		
		try{
		     	
				ownerID=null;
				testCaseID=null;
				priority=null;

				//System.out.println("Parse n Read XML");
				//File file = new File("C:\\Users\\khaderkhan\\workspace\\XmlConversion\\src\\2.xml");
			   
				InputSource source = new InputSource(new StringReader(XMLObjectRally));
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(source);
				// Document doc = builder.parse(file);
				XPathFactory xpathFact = XPathFactory.newInstance();
				XPath xpath = xpathFact.newXPath();
		       
				doc.getDocumentElement().normalize();
		       
				testCaseID = (String) xpath.evaluate("/QueryResult/Results/Object/ObjectID", doc, XPathConstants.STRING);
				ownerID = (String) xpath.evaluate("/QueryResult/Results/Object/Owner/ObjectID", doc, XPathConstants.STRING);
				String tmp = (String) xpath.evaluate("/QueryResult/Results/Object/Priority", doc, XPathConstants.STRING);
				testMethod = (String) xpath.evaluate("/QueryResult/Results/Object/Method", doc, XPathConstants.STRING);
//				System.out.println("xpath of priority ="+tmp);
				if (tmp.contains("Smoke"))
					priority = "Smoke";
				else 
					if (tmp.contains("Regression"))
						priority = "Regression";
					else
						priority = "FullCycle";
		
		   }
		   catch (Exception e){
			   	System.out.println("Caught XML Exception !!!" + e.getMessage() );
			   	e.printStackTrace();

		   }
	}
	
	
	public void postWeb(String payLoad) throws InterruptedException
    {
			// TODO Auto-generated method stub
		try {

				//System.out.println("POST Test RESULTS ************");
			
				//String XMLpayload = util.getFileContents("/payload.xml");
				String XMLpayload = payLoad;
				
				//System.out.println("Payload:"+XMLpayload);
				
				String strUrl = "https://rally1.rallydev.com/slm/webservice/1.41/testcaseresult/create";
				
				PostMethod post = new PostMethod(strUrl);
				StringRequestEntity entity = new StringRequestEntity(XMLpayload);
				post.setRequestEntity(entity);
				HttpClient client = new HttpClient();
				client.getState().setCredentials(
						new AuthScope("rally1.rallydev.com", 443),
						new UsernamePasswordCredentials( util.getTestData("usercred.txt", "USERNAME1"), util.getTestData("usercred.txt", "PASSWORD1"))
						);
				
				int statusCode=client.executeMethod(post);
				System.out.println("post Status="+statusCode);
				
				String postresult = post.getResponseBodyAsString();
				System.out.println("post Result="+postresult);
				if (statusCode != 200)
				{
					System.out.println("post Status="+statusCode);
					System.out.println("post Result="+postresult);
				}

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
	
	public String createPayloadXML(String testCaseID, String ownerID, String testPrior, String currDate, String buildName, String testStatus)
	{
		String testSet=null;
		if (testStatus.equals("PASS"))
		{
			testStatus = "Pass";
		}
		if (testStatus.equals("FAIL"))
		{
			testStatus = "Fail";
		}
		
		// System.out.println("Create Payload priority:"+testPrior);
		if (testPrior.equals("Smoke"))
			testSet = RallyQueryRequestData.testSetSmoke;
		if (testPrior.equals("Regression"))
			testSet = RallyQueryRequestData.testSetRegression;
		
		String payload = "<TestCaseResult>"+"\n" 
						 +"<Build>"+buildName+"</Build>"+"\n"
						 +"<Date>"+currDate+"</Date>"+"\n"
						 +"<Notes>"+"Result updated by Automation"+"</Notes>"+"\n"
						 +"<TestCase ref=\"/testcase/"+testCaseID+"\"/>"+"\n"
						 +"<Tester ref=\"/user/"+ownerID+"\"/>"+"\n"						 
						 +"<Verdict>"+testStatus+"</Verdict>"+"\n";
		
		if (testSet != null)
			payload = payload+"<TestSet ref=\""+testSet+"\"/>"+"\n";
		payload = payload+"</TestCaseResult>";
		
		return payload;
		
	}
	
}
