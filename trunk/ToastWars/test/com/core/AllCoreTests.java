package com.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllCoreTests extends TestCase
{
	public static Test suite()
	{
		TestSuite suite = new TestSuite("Alle Core Tests");
		suite.addTestSuite(Test_Type.class);
		suite.addTestSuite(Test_Stock.class);
		suite.addTestSuite(Test_Toaster.class);
		suite.addTestSuite(Test_Company.class);


		
		suite.addTestSuite(Test_MarketResearchReport.class);
		suite.addTestSuite(Test_Game.class);
		return suite;
	} //TestSuite
}//AllCoreTests

