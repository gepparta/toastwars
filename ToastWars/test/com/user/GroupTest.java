package com.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Master;
import junit.framework.TestCase;


public class GroupTest extends TestCase
{
	Group group;
	@Before
	public void setUp() throws Exception
	{
		group = new Group("GROUP1","GROUP1");
	}

	@After
	public void tearDown() throws Exception
	{
		group = null;
	}
	
	@Test
	public void testGetUsername()
	{
		assertEquals("GROUP1", group.getUsername());
	}
	
	public void testGetPassword()
	{
		assertEquals("GROUP1", group.getPassword());
	}
	public void testSetUsername()
	{
		assertNotSame("SOMEONE",group.getUsername());
		group.setUsername("SOMEONE");
		assertEquals("SOMEONE",group.getUsername());
	}
	
	public void testSetPassword()
	{
		assertNotSame("SOMEONE",group.getPassword());
		group.setPassword("SOMEONE");
		assertEquals("SOMEONE",group.getPassword());
	}
	
	public void testIsMaster()
	{
		assertFalse(group.isMaster() == true);
		assertTrue(group.isMaster() == false);
	}
		
}
