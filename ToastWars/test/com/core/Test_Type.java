package com.core;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.datamodel.core.Type;

public class Test_Type extends TestCase {
	// Definition der Testobjekte
	private Type type1;
	private Type type2;
	private Type type3;

	// Setup
	@Before
	public void setUp() throws Exception {
		type1 = Type.TYPE1;
		type2 = Type.TYPE2;
		type3 = Type.TYPE3;
	}

	// TearDown
	@After
	public void tearDown() throws Exception {

		type1 = null;
		type2 = null;
		type3 = null;
		System.gc();
	}

	// Start der Test

	// Test der Get-Methoden
	@Test
	public void testGetDescription() {
		assertNotNull(type1.getDescription());
		assertEquals("Millenniums-Toaster", type1.getDescription());

	}

	@Test
	public void testGetRandom() {
		assertNotNull(type1.getRandom());
		assertEquals(0.05, type1.getRandom());

	}

	@Test
	public void testGetMarketVolume() {
		assertNotNull(type1.getMarketVolume());
		assertEquals(10000, type1.getMarketVolume());

	}

	@Test
	public void testGetFixCost() {
		assertNotNull(type1.getFixCosts());
		assertEquals(10000.00, type1.getFixCosts());

	}

	@Test
	public void testGetVariableCost() {
		assertNotNull(type1.getVariableCosts());
		assertEquals(3.00, type1.getVariableCosts());

	}

	@Test
	public void testGetStepCost() {
		assertNotNull(type1.getStepCosts());
		assertEquals(1000.00, type1.getStepCosts());

	}

	@Test
	public void testGetCapacity() {
		assertNotNull(type1.getCapacity());
		assertEquals(2000, type1.getCapacity());

	}

	// Test der Klassenwerte
	@Test
	public void testValues() {

		assertEquals("TYPE1", type1.name());
		assertEquals("TYPE2", type2.name());
		assertEquals("TYPE3", type3.name());

		assertEquals(0, type1.ordinal());
		assertEquals(1, type2.ordinal());
		assertEquals(2, type3.ordinal());

		assertEquals("Millenniums-Toaster", type1.getDescription());
		assertEquals("TIE-Toaster", type2.getDescription());
		assertEquals("Star-Toaster", type3.getDescription());

		assertEquals(0.05, type1.getRandom());
		assertEquals(0.08, type2.getRandom());
		assertEquals(0.1, type3.getRandom());

		assertEquals(10000, type1.getMarketVolume());
		assertEquals(6000, type2.getMarketVolume());
		assertEquals(2500, type3.getMarketVolume());

		assertEquals(10000.00, type1.getFixCosts());
		assertEquals(20000.00, type2.getFixCosts());
		assertEquals(25000.00, type3.getFixCosts());

		assertEquals(3.00, type1.getVariableCosts());
		assertEquals(15.00, type2.getVariableCosts());
		assertEquals(40.00, type3.getVariableCosts());

		assertEquals(1000.00, type1.getStepCosts());
		assertEquals(5000.00, type2.getStepCosts());
		assertEquals(8000.00, type3.getStepCosts());

		assertEquals(2000, type1.getCapacity());
		assertEquals(1000, type2.getCapacity());
		assertEquals(500, type3.getCapacity());
	}

	// Test der Set-Methoden
	@Test
	public void testSetDescription() {
		assertNotSame("Test", type1.getDescription());
		type1.setDescription("Test");
		assertEquals("Test", type1.getDescription());
	} // testSetDescription

	@Test
	public void testSetRandom() {
		assertNotSame(0.08, type1.getRandom());
		type1.setRandom(0.08);
		assertEquals(0.08, type1.getRandom());
	}

	@Test
	public void testSetMarketVolume() {
		assertNotSame(15000, type1.getMarketVolume());
		type1.setMarketVolume(15000);
		assertEquals(15000, type1.getMarketVolume());
	}

	@Test
	public void testSetFixCost() {
		assertNotSame(12000.00, type1.getFixCosts());
		type1.setFixCosts(12000.00);
		assertEquals(12000.00, type1.getFixCosts());

	}

	@Test
	public void testSetVariableCost() {
		assertNotSame(12.00, type1.getVariableCosts());
		type1.setVariableCosts(12.00);
		assertEquals(12.00, type1.getVariableCosts());
	}

	@Test
	public void testSetStepCost() {
		assertNotSame(3000.00, type1.getStepCosts());
		type1.setStepCosts(3000.00);
		assertEquals(3000.00, type1.getStepCosts());
	}

	@Test
	public void testSetCapacity() {
		assertNotSame(8000, type1.getCapacity());
		type1.setCapacity(8000);
		assertEquals(8000, type1.getCapacity());
	}

}// Test Type
