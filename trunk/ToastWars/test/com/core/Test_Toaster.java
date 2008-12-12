package com.core;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.UserFactory;

public class Test_Toaster extends TestCase
{

	private Toaster toaster1;
	private Type myType;
	private Master master;

	@Before
	protected void setUp() throws Exception
	{
		master = (Master) UserFactory.createUser("Master", "ADMIN", "ADMIN");
		master.startGame(2);
		myType = Type.TYPE1;
		toaster1 = new Toaster(10, 10000.00, 12923.00, 1.00, 50000.00, 30000.00, 20000.00, 5000, myType);
	}

	@After
	protected void tearDown() throws Exception
	{
		toaster1 = null;
		master = null;
		myType = null;
	}

	@Test
	public void testCalculateResearch()
	{
		assertEquals(1.0, toaster1.calculateResearch());
	}

	@Test
	public void testCalculateMarketing()
	{
		assertEquals(1.0, toaster1.calculateMarketing());
	}

	@Test
	public void testCalculateIndex()
	{
		assertNotNull(Game.getInstance());
		toaster1.calculateIndexWithOutRandom();
		assertEquals(1.0, toaster1.getIndex());
	}

	@Test
	public void testGetPrice()
	{
		assertNotNull(toaster1.getPrice());
		assertEquals(toaster1.getPrice(), 10.0);
	}

	@Test
	public void testSetPrice()
	{
		assertNotSame(toaster1.getPrice(), 9.09);
		toaster1.setPrice(9.09);
		assertEquals(toaster1.getPrice(), 9.09);
	}

	@Test
	public void testGetMarketing()
	{
		assertNotNull(toaster1.getMarketing());
		assertEquals(10000.0,toaster1.getMarketing());
	}

	@Test
	public void testSetMarketing()
	{
		assertNotSame(toaster1.getMarketing(), 6.00);
		toaster1.setMarketing(6.00);
		assertEquals(toaster1.getMarketing(), 6.00);
	}

	@Test
	public void testGetResearch()
	{
		assertNotNull(toaster1.getResearch());
		assertEquals(12923.0,toaster1.getResearch());
	}

	@Test
	public void testSetResearch()
	{
		assertNotSame(toaster1.getResearch(), 1000.0);
		toaster1.setResearch(1000);
		assertEquals(toaster1.getResearch(), 1000.0);
	}

	@Test
	public void testGetIndex()
	{
		assertNotNull(toaster1.getIndex());
		assertEquals(toaster1.getIndex(), 1.00);
	}

	@Test
	public void testSetIndex()
	{
		assertNotSame(toaster1.getIndex(), 2.00);
		toaster1.setIndex(2.00);
		assertEquals(toaster1.getIndex(), 2.00);
	}

	@Test
	public void testGetTurnover()
	{
		assertNotNull(toaster1.getTurnover());
		assertEquals(50000.0,toaster1.getTurnover());
	}

	@Test
	public void testSetTurnover()
	{
		assertNotSame(toaster1.getTurnover(), 6.17);
		toaster1.setTurnover(6.17);
		assertEquals(toaster1.getTurnover(), 6.17);
	}

	@Test
	public void testGetCost()
	{
		assertNotNull(toaster1.getCost());
		assertEquals(30000.0,toaster1.getCost());
	}

	@Test
	public void testSetCost()
	{
		assertNotSame(toaster1.getCost(), 11.11);
		toaster1.setCost(11.11);
		assertEquals(toaster1.getCost(), 11.11);
	}

	@Test
	public void testGetProfit()
	{
		assertNotNull(toaster1.getProfit());
		assertEquals(20000.0,toaster1.getProfit());
	}

	@Test
	public void testSetProfit()
	{
		assertNotSame(toaster1.getProfit(), 1.00);
		toaster1.setProfit(1.00);
		assertEquals(toaster1.getProfit(), 1.00);
	}

	@Test
	public void testGetMarketShare()
	{
		assertNotNull(toaster1.getMarketShare());
		assertEquals(5000, toaster1.getMarketShare());
	}

	@Test
	public void testSetMarketShare()
	{
		assertNotSame(toaster1.getMarketShare(), 205);
		toaster1.setMarketShare(205);
		assertEquals(toaster1.getMarketShare(), 205);
	}

	@Test
	public void testGetType()
	{
		assertNotNull(toaster1.getType());
		assertEquals(toaster1.getType(), myType);
	}

	@Test
	public void testSetType()
	{
		assertNotSame(toaster1.getType(), Type.TYPE3);
		toaster1.setType(Type.TYPE3);
		assertEquals(toaster1.getType(), Type.TYPE3);
	}

	@Test
	public void testCalculateMarketShares()
	{

		assertNotSame(2000, toaster1.getMarketShare());
		toaster1.calculateMarketShare(5.00);
		assertEquals(2000, toaster1.getMarketShare());
	}

	@Test
	public void testCalculateTurnover()
	{

		assertNotSame(50000.00, toaster1.getTurnover());
		toaster1.calculateTurnover();
		assertEquals(50000.00, toaster1.getTurnover());
	}

	@Test
	public void testCalculateCost()
	{
		assertNotSame(17000.00, toaster1.getCost());
		toaster1.calculateCost();
		assertEquals(17000.00, toaster1.getCost());
	}

	@Test
	public void testCalculateProfit()
	{
		assertNotSame(20000.0, toaster1.getProfit());
		toaster1.calculateProfit();
		assertEquals(20000.0, toaster1.getProfit());
	}
}
