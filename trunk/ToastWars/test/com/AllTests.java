package com;
import com.core.AllCoreTests;
import com.user.AllUserTests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase
{
	public static Test suite()
	{
		TestSuite suite = new TestSuite("Alle Tests");
		suite.addTest(AllCoreTests.suite());
		suite.addTest(AllUserTests.suite());
		return suite;
	}
}
