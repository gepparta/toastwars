package com.user;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toastwars.server.datamodel.user.Master;

public class MasterTest  extends TestCase 
{
	Master master;

	@Before
	public void setUp() throws Exception
	{
		master = new Master("ADMIN","ADMIN");
	}

	@After
	public void tearDown() throws Exception
	{
		master = null;
	}
	
	@Test
	public void testGetUsername()
	{
		assertEquals("ADMIN", master.getUsername());
	}
	
	public void testGetPassword()
	{
		assertEquals("ADMIN", master.getPassword());
	}
	public void testSetUsername()
	{
		assertNotSame("SOMEONE",master.getUsername());
		master.setUsername("SOMEONE");
		assertEquals("SOMEONE",master.getUsername());
	}
	
	public void testSetPassword()
	{
		assertNotSame("SOMEONE",master.getPassword());
		master.setPassword("SOMEONE");
		assertEquals("SOMEONE",master.getPassword());
	}
	
	public void testIsMaster()
	{
		assertFalse(master.isMaster() == false);
		assertTrue(master.isMaster() == true);
	}
}
