package com.core;


import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.datamodel.core.Stock;
import toastwars.server.datamodel.core.Type;
/*
 * @ author Michael Klein
 */
public class Test_Stock extends TestCase {

//Definition der Testobjekte
private Stock stock1;
	
//Setup	
	@Before
	public void setUp() throws Exception {
		stock1= new Stock(1000,100,10, 0);
	}
//TearDown
	@After
	public void tearDown() throws Exception {
		
		stock1 = null;
		System.gc();
	}
	
//Test der Get-Methoden
	
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
	public void testGetMaxStockTT1()
	{
		assertNotNull(stock1.getMaxStockTT1());
		assertEquals(2500, stock1.getMaxStockTT1());
	}
	
	@Test
	public void testGetMaxStockTT2()
	{
		assertNotNull(stock1.getMaxStockTT2());
		assertEquals(1500, stock1.getMaxStockTT2());
	}
	@Test
	public void testGetMaxStockTT3()
	{
		assertNotNull(stock1.getMaxStockTT3());
		assertEquals(700, stock1.getMaxStockTT3());
	}
	@Test
	public void testGetStockCostsTT1()
	{
		assertNotNull(stock1.getStockCostsTT1());
		assertEquals(1.25, stock1.getStockCostsTT1());
	}
	
	@Test
	public void testGetStockCostsTT2()
	{
		assertNotNull(stock1.getStockCostsTT2());
		assertEquals(6.25, stock1.getStockCostsTT2());
	}
	
	@Test
	public void testGetStockCostsTT3()
	{
		assertNotNull(stock1.getStockCostsTT3());
		assertEquals(16.25, stock1.getStockCostsTT3());
	}
	
	@Test
	public void testGetTotalStockCosts()
	{
		assertNotNull(stock1.getTotalStockCosts());
		assertEquals(0.00, stock1.getTotalStockCosts());
	}
	
	
//Test der Set Methoden
	
	
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
	
	
	public void testSetMaxStockTT1()
	{
		assertNotSame(2000, stock1.getMaxStockTT1());
		stock1.setMaxStockTT1(2000);
		assertEquals(2000, stock1.getMaxStockTT1());
	}
	@Test
	public void testSetMaxStockTT2()
	{
		assertNotSame(200, stock1.getMaxStockTT2());
		stock1.setMaxStockTT2(200);
		assertEquals(200, stock1.getMaxStockTT2());
	}	
	@Test
	public void testSetMaxStockTT3()
	{
		assertNotSame(20, stock1.getMaxStockTT3());
		stock1.setMaxStockTT3(20);
		assertEquals(20, stock1.getMaxStockTT3());
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
	
//Test der berechnenden Methoden
	@Test
	
	public void testStockUp(){

		
		
		stock1.setStockTT1(100);
		stock1.setStockTT2(100);
		stock1.setStockTT3(100);
		
		stock1.setMaxStockTT1(200);
		stock1.setMaxStockTT2(200);
		stock1.setMaxStockTT3(200);
		
		stock1.StockUp(Type.TYPE1, 200);
		stock1.StockUp(Type.TYPE2, 200);
		stock1.StockUp(Type.TYPE3, 200);

		assertEquals(200, stock1.getStockTT1());
		assertEquals(200, stock1.getStockTT2());
		assertEquals(200, stock1.getStockTT3());


		stock1.StockUp(Type.TYPE1, 200);
		stock1.StockUp(Type.TYPE2, 200);
		stock1.StockUp(Type.TYPE3, 200);

		assertEquals(200, stock1.getStockTT1());
		assertEquals(200, stock1.getStockTT2());
		assertEquals(200, stock1.getStockTT3());	

		
		
	}
	
	public void testReduceStock(){

		stock1.setStockTT1(200);
		stock1.setStockTT2(200);
		stock1.setStockTT3(200);
		
		stock1.ReduceStock(Type.TYPE1,100);
		stock1.ReduceStock(Type.TYPE2,100);
		stock1.ReduceStock(Type.TYPE3,100);
		


		assertEquals(100, stock1.getStockTT1());
		assertEquals(100, stock1.getStockTT2());
		assertEquals(100, stock1.getStockTT3());


		assertEquals(100, stock1.ReduceStock(Type.TYPE1,200));
		assertEquals(100, stock1.ReduceStock(Type.TYPE2,200));
		assertEquals(100, stock1.ReduceStock(Type.TYPE3,200));


		assertEquals(0, stock1.getStockTT1());
		assertEquals(0, stock1.getStockTT2());
		assertEquals(0, stock1.getStockTT3());
	}
	
	@Test
	public void testCalculateTotalStockCost(){
		stock1.setStockTT1(100);
		stock1.setStockTT2(100);
		stock1.setStockTT3(100);
		stock1.setStockCostsTT1(1);
		stock1.setStockCostsTT2(1);
		stock1.setStockCostsTT3(1);
		assertEquals(300.00, stock1.calculateTotalStockCosts());
		assertEquals(300.00 ,stock1.getTotalStockCosts());
		
	}
	
	
	

}//Test Stock
