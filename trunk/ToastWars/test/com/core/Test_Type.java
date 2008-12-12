package com.core;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.datamodel.core.Type;

public class Test_Type extends TestCase
{

	private Type type1;
	private Type type2;
	private Type type3;

	@Before
	public void setUp() throws Exception
	{
		type1 = Type.TYPE1;
		type2 = Type.TYPE2;
		type3 = Type.TYPE3;
	}

	@After
	public void tearDown() throws Exception
	{

		type1 = null;
		type2 = null;
		type3 = null;

	}

	@Test
	public void testGetDescription()
	{
		assertNotNull(type1.getDescription());
		assertEquals("Der 1. Typ", type1.getDescription());

	}

	@Test
	public void testGetRandom()
	{
		assertNotNull(type1.getRandom());
		assertEquals(0.05, type1.getRandom());

	}

	@Test
	public void testGetMarketVolume()
	{
		assertNotNull(type1.getMarketVolume());
		assertEquals(10000, type1.getMarketVolume());

	}

	@Test
	public void testGetFixCost()
	{
		assertNotNull(type1.getFixCostPerMachine());
		assertEquals(1000.00, type1.getFixCostPerMachine());

	}

	@Test
	public void testGetVariableCost()
	{
		assertNotNull(type1.getVariableCost());
		assertEquals(3.00, type1.getVariableCost());

	}

	@Test
	public void testConstructor()
	{

		assertEquals("TYPE1", type1.name());
		assertEquals("TYPE2", type2.name());
		assertEquals("TYPE3", type3.name());

		assertEquals(0, type1.ordinal());
		assertEquals(1, type2.ordinal());
		assertEquals(2, type3.ordinal());

		assertEquals("Der 1. Typ", type1.getDescription());
		assertEquals("Der 2. Typ", type2.getDescription());
		assertEquals("Der 3. Typ", type3.getDescription());

		assertEquals(0.05, type1.getRandom());
		assertEquals(0.08, type2.getRandom());
		assertEquals(0.1, type3.getRandom());

		assertEquals(10000, type1.getMarketVolume());
		assertEquals(20000, type2.getMarketVolume());
		assertEquals(30000, type3.getMarketVolume());

		assertEquals(1000.00, type1.getFixCostPerMachine());
		assertEquals(5000.00, type2.getFixCostPerMachine());
		assertEquals(15000.00, type3.getFixCostPerMachine());

		assertEquals(3.00, type1.getVariableCost());
		assertEquals(15.00, type2.getVariableCost());
		assertEquals(60.00, type3.getVariableCost());

	}

	@Test
	public void testSetRandom()
	{
		assertNotSame(type1.getRandom(), 0.08);
		type1.setRandom(0.08);
		assertEquals(type1.getRandom(), 0.08);
	}

	@Test
	public void testSetMarketVolume()
	{
		assertNotSame(type1.getMarketVolume(), 15000);
		type1.setMarketVolume(15000);
		assertEquals(type1.getMarketVolume(), 15000);
	}

	@Test
	public void testSetFixCost()
	{
		assertNotSame(type1.getFixCostPerMachine(), 12000.00);
		type1.setFixCostPerMachine(12000.00);
		assertEquals(type1.getFixCostPerMachine(), 12000.00);

	}

	@Test
	public void testSetVariableCost()
	{
		assertNotSame(type1.getVariableCost(), 12.00);
		type1.setVariableCost(12.00);
		assertEquals(type1.getVariableCost(), 12.00);
	}
}
