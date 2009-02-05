package com.core;

import junit.framework.Test;
import junit.framework.TestSuite;
/*
 * @ author Michael Klein
 */
public class AllCoreTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.core");
		//$JUnit-BEGIN$
		suite.addTestSuite(Test_Company.class);
		suite.addTestSuite(Test_Game.class);
//		suite.addTestSuite(Test_MarketResearchReport.class);
		suite.addTestSuite(Test_Stock.class);
		suite.addTestSuite(Test_Toaster.class);
		suite.addTestSuite(Test_Type.class);
		//$JUnit-END$
		return suite;
	}

}
