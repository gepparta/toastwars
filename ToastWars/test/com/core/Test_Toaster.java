package com.core;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;



public class Test_Toaster extends TestCase {

	private Toaster T1;
	private Type type1;
	
@Before
	  protected void setUp() throws Exception {
			type1 = Type.type1;
			T1 = new Toaster(15.37,3,7,17,type1);
		  }

@After
	  protected void tearDown()throws Exception  {
			T1 = null;
		  }

@Test	
	public void testGetPrice() {
		assertNotNull(T1.getPrice());
		assertEquals(T1.getPrice(),15.37);	
}

@Test
	public void testSetPrice(){
		assertNotSame(T1.getPrice(),9.09);
		T1.setPrice(9.09);
		assertEquals(T1.getPrice(),9.09);
}

@Test	
public void testGetMarketing() {
	assertNotNull(T1.getMarketing());
	assertSame(T1.getMarketing(),3);	
}

@Test
public void testSetMarketing(){
	assertNotSame(T1.getMarketing(),6);
	T1.setMarketing(6);
	assertSame(T1.getMarketing(),6);
}
@Test	
public void testGetResearch() {
	assertNotNull(T1.getResearch());
	assertSame(T1.getResearch(),7);	
}

@Test
public void testSetResearch(){
	assertNotSame(T1.getResearch(),6);
	T1.setResearch(6);
	assertSame(T1.getResearch(),6);
}

@Test	
public void testGetRound() {
	assertNotNull(T1.getRound());
	assertSame(T1.getRound(),17);	
}

@Test
public void testSetRound(){
	assertNotSame(T1.getRound(),6);
	T1.setRound(6);
	assertSame(T1.getRound(),6);
}
@Test
public void testGetType(){
	assertNotNull(T1.getType());
	assertSame(T1.getType(), Type.type1);
}
@Test
public void testSetType(){
	assertNotSame(T1.getType(),Type.type3);
	T1.setType(Type.type3);
	assertSame(T1.getType(),Type.type3);
}
}
