package com;
import com.user.AllUserTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase
{
	public static Test suite()
	{
		TestSuite suite = new TestSuite("Alle Tests");
		suite.addTest(AllUserTests.suite());
		//TODO: addTestSuite(UserTest.class) and CoreTest.class
		return suite;
	}
}
