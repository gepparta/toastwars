package com.user;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.UserFactory;

public class UserFactoryTest extends TestCase
{
	UserFactory factory;
	@Before
	public void setUp() throws Exception
	{
		factory = new UserFactory();
	}

	@After
	public void tearDown() throws Exception
	{
		factory = null;
	}
	
	@Test
	public void testCreateUser()
	{
		Master master1 = Master.getInstance("ADMIN","ADMIN");
		Master master2 = (Master)factory.createUser("Master", "ADMIN","ADMIN");
		assertSame(master1,master2);
		Group group = (Group) factory.createUser("Group", "Group1","Group1");
		assertNotSame(master1, group);
		System.out.println(master1.isMaster());
		System.out.println(master2.isMaster());
		System.out.println(group.isMaster());
	}

}
