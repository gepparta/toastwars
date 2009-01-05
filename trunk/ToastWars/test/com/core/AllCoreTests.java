package com.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllCoreTests extends TestCase
{
	public static Test suite()
	{
		TestSuite suite = new TestSuite("Alle Core Tests");
		//suite.addTestSuite(Test_Type.class);
		suite.addTestSuite(Test_Company.class);
		suite.addTestSuite(Test_Game.class);
		suite.addTestSuite(Test_Toaster_Type1.class);
		suite.addTestSuite(Test_Toaster_Type2.class);
		suite.addTestSuite(Test_Type.class);
		return suite;
	}
}

