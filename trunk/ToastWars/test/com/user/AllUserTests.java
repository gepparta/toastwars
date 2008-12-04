package com.user;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllUserTests extends TestCase
{
	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		suite.addTestSuite(GroupTest.class);
		suite.addTestSuite(MasterTest.class);
		suite.addTestSuite(UserFactoryTest.class);
		return suite;
	}
}
