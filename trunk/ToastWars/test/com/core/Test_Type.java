package com.core;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toastwars.server.datamodel.core.Type;

public class Test_Type extends TestCase {

	private Type T1;
	private Type T2;
	private Type T3;

	@Before
	public void setUp() throws Exception {
		T1 = Type.TYPE1;
		T2 = Type.TYPE2;
		T3 = Type.TYPE3;

	}

	@After
	public void tearDown() throws Exception {

		T1 = null;
		T2 = null;
		T3 = null;

	}

	@Test
	public void testGetDescription() {
		assertNotNull(T1.getDescription());
		assertEquals("Der 1. Typ", T1.getDescription());

	}

	@Test
	public void testGetRandom() {
		assertNotNull(T1.getRandom());
		assertEquals(0.05, T1.getRandom());

	}

	@Test
	public void testGetMarketVolume() {
		assertNotNull(T1.getMarketVolume());
		assertEquals(10000, T1.getMarketVolume());

	}
	
	@Test
	public void testGetFixCost() {
		assertNotNull(T1.getFixCost());
		assertEquals(5000.00, T1.getFixCost());

	}
	
	@Test
	public void testGetVariableCost() {
		assertNotNull(T1.getVariableCost());
		assertEquals(10.00, T1.getVariableCost());

	}

	@Test
	public void testConstructor() {
		
		assertEquals("TYPE1", T1.name());
		assertEquals("TYPE2", T2.name());
		assertEquals("TYPE3", T3.name());
		
		assertEquals(0, T1.ordinal());
		assertEquals(1, T2.ordinal());
		assertEquals(2, T3.ordinal());
		
		assertEquals("Der 1. Typ", T1.getDescription());
		assertEquals("Der 2. Typ", T2.getDescription());
		assertEquals("Der 3. Typ", T3.getDescription());
		
		assertEquals(0.05, T1.getRandom());
		assertEquals(0.07, T2.getRandom());
		assertEquals(0.1, T3.getRandom());
		
		assertEquals(10000, T1.getMarketVolume());
		assertEquals(20000, T2.getMarketVolume());
		assertEquals(30000, T3.getMarketVolume());
		
		assertEquals(5000.00, T1.getFixCost());
		assertEquals(5000.00, T2.getFixCost());
		assertEquals(5000.00, T3.getFixCost());
		
		assertEquals(10.00, T1.getVariableCost());
		assertEquals(10.00, T2.getVariableCost());
		assertEquals(10, T3.getVariableCost());

	}

	@Test
	public void testSetDescription() {
		assertNotSame(T1.getDescription(), "Test");
		T1.setDescription("Test");
		assertEquals(T1.getDescription(), "Test");

	}

	@Test
	public void testSetRandom() {
		assertNotSame(T1.getRandom(), 0.08);
		T1.setRandom(0.08);
		assertEquals(T1.getRandom(), 0.08);
	}

	@Test
	public void testSetMarketVolume() {
		assertNotSame(T1.getMarketVolume(), 15000);
		T1.setMarketVolume(15000);
		assertEquals(T1.getMarketVolume(), 15000);
	}
	
	@Test
	public void testSetFixCost() {
		assertNotSame(T1.getFixCost(), 12000.00);
		T1.setFixCost(12000.00);
		assertEquals(T1.getFixCost(), 12000.00);
	}
	
	@Test
	public void testSetVariableCost() {
		assertNotSame(T1.getVariableCost(), 12.00);
		T1.setVariableCost(12.00);
		assertEquals(T1.getVariableCost(), 12.00);
	}

}
