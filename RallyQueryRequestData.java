
package com.om.data;

/**
 * 
 * @author KhaderKhan
 * 
 * Interface to define the Rally Query Request parameters
 * 
 */

public interface RallyQueryRequestData {
	
		public static String queryURL = "https://rally1.rallydev.com/slm/webservice/1.41/testcase?";
		public static String postURL = "https://rally1.rallydev.com/slm/webservice/1.41/testcaseresult/create"; 
		public static String formatID = "FormattedID";
		public static String fetch = "fetch";
		public static String objID = "ObjectID";
		public static String owner = "Owner";
		public static String space = "%20";
		/*public static String testSetSmoke = "/testset/13994532943";
		public static String testSetRegression = "/testset/13994736092";*/
		
		//public static String testSetSmoke = "/testset/14193682659";
		//public static String testSetRegression = "/testset/14193683225";
		
		//public static String testSetSmoke = "/testset/14301108841";
		//public static String testSetRegression = "/testset/14301120201";
		
		//public static String testSetSmoke = "testset/14471363694";
		//public static String testSetRegression = "testset/14471364185";
		
		//public static String testSetSmoke = "testset/14737711185";
		//public static String testSetRegression = "testset/14737710015";
		//public static String testSetRegression = "testset/17465528392"; // Regression Test SB (Cycle-1)
		//public static String testSetRegression = "testset/17465526271"; //Regression Test Revolving (Cycle-1)
		
		//public static String testSetSmoke = "testset/14781030907";  
		//public static String testSetSmoke = "testset/17465527280";  // Smoke Test SB (Cycle-1)
		//public static String testSetSmoke = "testset/17465757995"; // Smoke Test Revolving (Cycle-1)
		
		//public static String testSetSmoke = "testset/17774330494"; // Smoke Test Revolving (Cycle-2)
		//public static String testSetRegression = "testset/17774329024"; // regression Test Revolving (Cycle-2)
		//public static String testSetSmoke = "testset/17774329909"; // Smoke Test SMB (Cycle-2)
		//public static String testSetRegression = "testset/17774331114"; // regression Test SMB (Cycle-2)
		
		
		
		//public static String testSetSmoke = "testset/18367277021"; // Smoke Test Revolving (Cycle-3)
		//public static String testSetRegression = "testset/18367277437"; // regression Test Revolving (Cycle-3)
		//public static String testSetSmoke = "testset/18367277932"; // Smoke Test SMB (Cycle-3)
		//public static String testSetRegression = "testset/18367276686"; // regression Test SMB (Cycle-3)
		
		
		//public static String testSetSmoke = null; 
		//public static String testSetRegression = null; 
		
		//public static String testSetSmoke = "testset/26713475944";  	//	Smoke Test_DM47 on RHEL
		//public static String testSetRegression = "testset/26713399987"; //	refObjectName="Regression Test_DM47 on RHEL"
		
	//	public static String testSetSmoke = "testset/27764067636";  	//	Smoke Test_DM47 on Windows-8
	//	public static String testSetRegression = "testset/27764066704"; //	Regression Test_DM47 on Windows-8
		
	//	public static String testSetSmoke = "testset/27763981367";  	//	Automated Smoke Test SB (Cycle-1)_Oracle
	//	public static String testSetRegression = "testset/27763980682"; //	Automated Regression Test SB (Cycle-1)_Oracle
		
	//	public static String testSetSmoke = "testset/27764063596";  	//	Automated Smoke Test Revolving (Cycle-1)_Oracle
	//	public static String testSetRegression = "testset/27764062744"; //	Automated Regression Test Revolving (Cycle-1)_Oracle
		
	//	public static String testSetSmoke = "testset/27764089016";  	//	Smoke Test_DM47 on Windows-2012
	//	public static String testSetRegression = "testset/27764070320"; //	Regression Test_DM47 on Windows-2012
				
	//	public static String testSetSmoke = "testset/31020811297";  	//	Smoke Test_DM47 on Windows-2012_RC_build
	//	public static String testSetRegression = "testset/31020943838"; //	Regression Test_DM47 on Windows-2012_RC_build
		
	//	public static String testSetSmoke = "testset/31020945616";  	//	Smoke Test_DM47 on Windows-8_RC_build
	//	public static String testSetRegression = "testset/31020946436"; //	Regression Test_DM47 on Windows-8_RC_build
		
	//	public static String testSetSmoke = "testset/31020290416";  	//	Smoke Test SB_Oracle_RC_build
	//	public static String testSetRegression = "testset/31019847306"; //	Regression Test SB_Oracle_RC_build
		
	//	public static String testSetSmoke = "testset/31019851461";  	//	Smoke Test Revolving_Oracle_RC_build
	//	public static String testSetRegression = "testset/31020001248"; //	Regression Test Revolving_Oracle_RC_build
		
	//	public static String testSetSmoke = "testset/31020809599";  	//	Smoke Test_DM47 on AIX_RC_build
	//	public static String testSetRegression = "testset/31020802822"; //	Regression Test_DM47 on AIX_RC_build
		
	//	public static String testSetSmoke = "testset/31479756458";  	//	Smoke Test SB_RC_build_ORACLE11g
	//	public static String testSetRegression = "testset/31479592610"; //	Regression Test SB_RC_build_ORACLE11g
		
	//	public static String testSetSmoke = "testset/31479748882";  	//	Smoke Test Revolving_RC_build_ORACLE11g
	//	public static String testSetRegression = "testset/31479753699"; //	Regression Test Revolving_RC_build_ORACLE11g
		
	//	public static String testSetSmoke = "testset/31480263742";  	//	Smoke Test_DM47 on RHEL_RC_build_ORACLE11g ts78
	//	public static String testSetRegression = "testset/31480257826"; //	Regression Test_DM47 on RHEL_RC_build_ORACLE11g ts77
		
	//	public static String testSetSmoke = "testset/33052213651";  	//	Smoke Test SB_Oracle_RC3_build TS85
	//	public static String testSetRegression = "testset/33052213208"; //	
		
		public static String testSetSmoke = "testset/38803736502";    //  Smoke OM45 TS135
        public static String testSetRegression = "testset/38945875929"; // Regression OM45 TS136
		
}
