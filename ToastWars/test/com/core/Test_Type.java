package com.core;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toastwars.server.datamodel.core.Type;


public class Test_Type extends TestCase {

	private Type T1;
	 
	@Before
	public void setUp() throws Exception {
		T1 = Type.TYPE1;
	}

	@After
	public void tearDown() throws Exception {
		
		T1 = null;
	}
	
	@Test
	public void testGetDescription(){
		assertNotNull(T1.getDescription());
		assertEquals("Der 1. Typ", T1.getDescription());
		
	}
	
	@Test
	public void testConstructor(){
		assertEquals("type1",Type.TYPE1.name());
		assertEquals("type2",Type.TYPE2.name());
		assertEquals("type3",Type.TYPE3.name());
		assertEquals("Der 1. Typ",Type.TYPE1.getDescription());
		assertEquals("Der 2. Typ",Type.TYPE2.getDescription());
		assertEquals("Der 3. Typ",Type.TYPE3.getDescription());
		assertEquals(0,Type.TYPE1.ordinal());
		assertEquals(1,Type.TYPE2.ordinal());
		assertEquals(2,Type.TYPE3.ordinal());
		
	}

}
