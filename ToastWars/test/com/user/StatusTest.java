package com.user;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.datamodel.user.Status;

public class StatusTest extends TestCase
{
	private Status status1;
	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}
	
	@Test
	public void testGetByName()
	{
		status1 = Status.valueOf("STARTED");
		assertEquals("STARTED",status1.name());
	}
}
