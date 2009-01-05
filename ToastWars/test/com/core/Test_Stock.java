package com.core;


import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toastwars.server.datamodel.core.Stock;

public class Test_Stock extends TestCase {
	
private Stock stock1;
	
	
	@Before
	public void setUp() throws Exception {
		stock1= new Stock(1000,100,10,1.00, 10.00, 100.00, 0);
	}

	@After
	public void tearDown() throws Exception {
		
		stock1 = null;
		System.gc();
	}
	
///////////GET //////////////////////	
	
	@Test
	public void testGetStockTT1()
	{
		assertNotNull(stock1.getStockTT1());
		assertEquals(1000, stock1.getStockTT1());
	}
	
	@Test
	public void testGetStockTT2()
	{
		assertNotNull(stock1.getStockTT2());
		assertEquals(100, stock1.getStockTT2());
	}
	@Test
	public void testGetStockTT3()
	{
		assertNotNull(stock1.getStockTT3());
		assertEquals(10, stock1.getStockTT3());
	}
	@Test
	public void testGetStockCostsTT1()
	{
		assertNotNull(stock1.getStockCostsTT1());
		assertEquals(1.00, stock1.getStockCostsTT1());
	}
	
	@Test
	public void testGetStockCostsTT2()
	{
		assertNotNull(stock1.getStockCostsTT2());
		assertEquals(10.00, stock1.getStockCostsTT2());
	}
	
	@Test
	public void testGetStockCostsTT3()
	{
		assertNotNull(stock1.getStockCostsTT3());
		assertEquals(100.00, stock1.getStockCostsTT3());
	}
	
	@Test
	public void testGetTotalStockCosts()
	{
		assertNotNull(stock1.getStockCostsTT3());
		assertEquals(100.00, stock1.getStockCostsTT3());
	}
	
	
////////////////////SETTER////////////////////////////	
	
	
	@Test
	public void testSetStockTT1()
	{
		assertNotSame(2000, stock1.getStockTT1());
		stock1.setStockTT1(2000);
		assertEquals(2000, stock1.getStockTT1());
	}
	@Test
	public void testSetStockTT2()
	{
		assertNotSame(200, stock1.getStockTT2());
		stock1.setStockTT2(200);
		assertEquals(200, stock1.getStockTT2());
	}	
	@Test
	public void testSetStockTT3()
	{
		assertNotSame(20, stock1.getStockTT3());
		stock1.setStockTT3(20);
		assertEquals(20, stock1.getStockTT3());
	}
	
	@Test
	public void testSetStockCostsTT1()
	{
		assertNotSame(2.00, stock1.getStockCostsTT1());
		stock1.setStockCostsTT1(2.00);
		assertEquals(2.00, stock1.getStockCostsTT1());
	}
	
	@Test
	public void testSetStockCostsTT2()
	{
		assertNotSame(20.00, stock1.getStockCostsTT2());
		stock1.setStockCostsTT2(20.00);
		assertEquals(20.00, stock1.getStockCostsTT2());
	}
	@Test
	public void testSetStockCostsTT3()
	{
		assertNotSame(200.00, stock1.getStockCostsTT3());
		stock1.setStockCostsTT3(200.00);
		assertEquals(200.00, stock1.getStockCostsTT3());
	}
	@Test
	public void testSetTotalStockCosts()
	{
		assertNotSame(200.00, stock1.getTotalStockCosts());
		stock1.setTotalStockCosts(200.00);
		assertEquals(200.00, stock1.getTotalStockCosts());
	}
	
	
	
	
	
	
	
	

}
