package com.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllCoreTests extends TestCase
{
	public static Test suite()
	{
		TestSuite suite = new TestSuite("Alle User Tests");
		suite.addTestSuite(Test_Company.class);
		suite.addTestSuite(Test_Game.class);
		suite.addTestSuite(Test_Toaster.class);
		return suite;
	}
}

