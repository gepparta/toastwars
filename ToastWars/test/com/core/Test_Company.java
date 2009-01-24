

//TODO
//@Alex...
//bitte Teste die accumulate Methode und die getter und setter f�r die 3 ArrayLists



package com.core;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Stock;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;

public class Test_Company extends TestCase
{
	private Toaster toaster1;
	private Toaster toaster2;
	private Toaster toaster3;
	private ArrayList<Toaster> toasterList1;
	private ArrayList<Toaster> toasterList2;
	private Stock stock1;
	private Stock stock2;
	private Company company1;
	private Company company2;

	

	@Before
	public void setUp() throws Exception
	{


		toaster1 = new Toaster(10.0, 7.00, 100000.00,50000.00,50000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		toaster2 = new Toaster(10.0, 7.00, 100000.00,50000.00,50000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		toaster3 = new Toaster(10.0, 7.00, 100000.00,50000.00,50000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		
		toasterList1 = new ArrayList<Toaster>();
		toasterList1.add(toaster1);
		toasterList2 = new ArrayList<Toaster>();
		toasterList2.add(toaster1);
		toasterList2.add(toaster2);
		toasterList2.add(toaster3);
		
		stock1 = new Stock(0,0,0,0.00);
		stock2 = new Stock(0,0,0,0.00);
		
		company1 = new Company(100000.00,20000.00, 60000.00,100000.00, 10000,stock1,toasterList1);
		company2 = new Company(100000.00, 30000.00, 60000.00,100000.00,10000,stock2,toasterList2);


	}

	@After
	public void tearDown() throws Exception
	{
		toaster1 = null;
		toaster2 = null;
		toaster3 = null;
		toasterList1 = null;
		toasterList2 = null;
		stock1 = null;
		stock2 = null;
		company1 = null;
		company2 = null;

		System.gc();
	}

	//Test der Get-Methoden
	@Test
	public void testGetTurnover()
	{
		assertNotNull(company1.getTurnover());
		assertEquals(100000.00, company1.getTurnover());
	}

	@Test
	public void testGetCost()
	{
		assertNotNull(company1.getCost());
		assertEquals(20000.00, company1.getCost());
	}

	@Test
	public void testGetProfit()
	{
		assertNotNull(company1.getProfit());
		assertEquals(60000.00, company1.getProfit());
	}

	@Test
	public void testGetCapital()
	{
		assertNotNull(company1.getCapital());
		assertEquals(100000.00,company1.getCapital());
	}

	@Test
	public void testgetMarketShare()
	{
		assertNotNull(company1.getMarketShare());
		assertEquals(10000, company1.getMarketShare());
	}

	@Test
	public void testGetToasterList()
	{
		assertNotNull(company1.getToasterList());
		assertEquals(toasterList1, company1.getToasterList());
	}
	
	@Test
	public void testGetCompanyID()
	{
		assertNotNull(company1.getCompanyID());
		// an dieser Stelle kann nicht mehr �berpr�ft werden, weil nicht absehbar ist wieviel ID bisher beim Testen vergeben wurden.
	}
	
	@Test
	public void testIsMarketResearchReportON()
	{
		assertNotNull(company1.isMarketResearchReportON());
		assertEquals(false, company1.isMarketResearchReportON());
	}
	
	@Test
	public void testGetStock()
	{
		assertNotNull(company1.getStock());
		assertEquals(stock1, company1.getStock());
	}
	

	

	
	// Tests der Set-Methoden
	@Test
	public void testSetTurnover()
	{
		assertNotSame( 9.09,company1.getTurnover());
		company1.setTurnover(9.09);
		assertEquals( 9.09,company1.getTurnover());
	}

	@Test
	public void testSetCost()
	{
		assertNotSame(1.67,company1.getCost());
		company1.setCost(1.67);
		assertEquals( 1.67,company1.getCost());

	}

	@Test
	public void testSetProfit()
	{
		assertNotSame( 2.33,company1.getProfit());
		company1.setProfit(2.33);
		assertEquals(2.33,company1.getProfit());
	}

	@Test
	public void testSetCapital()
	{
		assertNotSame( 2.09, company1.getCapital());
		company1.setCapital(2.09);
		assertEquals( 2.09, company1.getCapital());
	}

	@Test
	public void testSetMarketShare()
	{
		assertNotSame(9,company1.getMarketShare());
		company1.setMarketShare(9);
		assertEquals(9, company1.getMarketShare());
	}

	@Test
	public void testSetToasterList()
	{
		assertNotSame(toasterList2, company1.getToasterList());
		company1.setToasterList(toasterList2);
		assertEquals(toasterList2, company1.getToasterList());
	}
	
	@Test
	public void testSetStock()
	{
		assertNotSame(stock2, company1.getStock());
		company1.setStock(stock2);
		assertEquals(stock2, company1.getStock());
	}
	
	@Test
	public void testSetMarketResearchReportON()
	{
		assertNotSame(true, company1.isMarketResearchReportON());
		company1.setMarketResearchReportON(true);
		assertEquals(true, company1.isMarketResearchReportON());
	}
	
	@Test
	public void testSetCompanyID()
	{
		assertNotSame(200, company1.getCompanyID());
		company1.setCompanyID(200);
		assertEquals(200, company1.getCompanyID());
	}
	
	
	
	

	@Test
	public void testCalaculateIndex()
	{
		//Test f�r einen Toaster in der Liste
		assertEquals(1, company1.getToasterList().size());

		assertNotSame(9.0, company1.getToasterList().get(0).getIndex());
		company1.calculateIndex();
		assertEquals(9.0, company1.getToasterList().get(0).getIndex());

		
		
		assertNotSame(9.00,company2.getToasterList().get(0).getIndex());
		assertNotSame(9.00,company2.getToasterList().get(1).getIndex());
		assertNotSame(9.00, company2.getToasterList().get(2).getIndex() );

		company2.calculateIndex();

		assertEquals(company2.getToasterList().size(), 3);
		assertEquals(9.0, company2.getToasterList().get(0).getIndex());
		assertEquals(9.0, company2.getToasterList().get(1).getIndex());
		assertEquals(9.0, company2.getToasterList().get(2).getIndex());

	}

	@Test
	public void testCalculateMarketShare()
	{

		//Definition der speziellen Variablen f�r diese Testmethode:
		//Array das die Summe der Indizes enth�lt
		double[] d = new double[3];
		d[0] = 14;
		d[1] = 14;
		d[2] = 14;
		//Setzen des Marktvolumens
		Type.TYPE1.setMarketVolume(10000);
		
		//Test f�r die erste Company mit einem Toaster
		assertNotSame(5000, company1.getToasterList().get(0).getMarketShare());
		assertNotSame(5000, company1.getMarketShare());
		company1.calculateMarketShares(d);
		assertEquals(5000, company1.getToasterList().get(0).getMarketShare());
		assertEquals(5000, company1.getMarketShare());
		

		//Test f�r die zweite Company mit drei Toaster
		assertNotSame(5000, company2.getToasterList().get(0).getMarketShare());
		assertNotSame(5000, company2.getToasterList().get(1).getMarketShare());
		assertNotSame(5000, company2.getToasterList().get(2).getMarketShare());
		assertNotSame(15000, company2.getMarketShare());
		company2.calculateMarketShares(d);
		assertEquals(5000, company2.getToasterList().get(0).getMarketShare());
		assertEquals(5000, company2.getToasterList().get(1).getMarketShare());
		assertEquals(5000, company2.getToasterList().get(2).getMarketShare());

		assertEquals(15000, company2.getMarketShare());
	}

	@Test
	public void testCalculateTurnover()
	{
		//Test f�r eine Company mit einem Toaster
		assertNotSame(100000.00, company1.getToasterList().get(0).getTurnover());
		assertNotSame(100000.00, company1.getTurnover());
		company1.calculateTurnover();
		assertEquals(100000.00, company1.getToasterList().get(0).getTurnover());
		assertEquals(100000.00, company1.getTurnover());
		
		//Test f�r eine Company mit drei Toastern
		assertNotSame(100000.00, company2.getToasterList().get(0).getTurnover());
		assertNotSame(100000.00, company2.getToasterList().get(1).getTurnover());
		assertNotSame(100000.00, company2.getToasterList().get(2).getTurnover());
		assertNotSame(300000.00, company2.getTurnover());
		company2.calculateTurnover();
		assertEquals(100000.00, company2.getToasterList().get(0).getTurnover());
		assertEquals(100000.00, company2.getToasterList().get(1).getTurnover());
		assertEquals(100000.00, company2.getToasterList().get(2).getTurnover());

		assertEquals(300000.00, company2.getTurnover());
	}

	public void testCalculateCost()
	{
		//Test f�r eine Company mit einem Toaster
		assertNotSame(33000.00, company1.getToasterList().get(0).getCost());
		assertNotSame(33000.00, company1.getCost());
		company1.calculateCost();
		assertEquals(33000.00, company1.getToasterList().get(0).getCost());
		assertEquals(33000.00, company1.getCost());

		//Test f�r eine Company mit drei Toastern
		assertNotSame(330000.00, company2.getToasterList().get(0).getCost());
		assertNotSame(330000.00, company2.getToasterList().get(1).getCost());
		assertNotSame(330000.00, company2.getToasterList().get(2).getCost());
		assertNotSame(99000.00, company2.getCost());
		company2.calculateCost();
		assertEquals(33000.00, company2.getToasterList().get(0).getCost());
		assertEquals(33000.00, company2.getToasterList().get(1).getCost());
		assertEquals(33000.00, company2.getToasterList().get(2).getCost());

		assertEquals(99000.00, company2.getCost());
	}

	public void testCalculateProfit()
	{
		//Test f�r eine Company mit einem Toaster
		assertNotSame(50000.00, company1.getToasterList().get(0).getProfit());
		assertNotSame(80000.00, company1.getProfit());
		company1.calculateProfit();
		assertEquals(50000.00, company1.getToasterList().get(0).getProfit());
		assertEquals(80000.00, company1.getProfit());
		
		//Test f�r eine Company mit drei Toastern
		assertNotSame(70000.00, company2.getProfit());
		assertNotSame(50000.00, company2.getToasterList().get(0).getProfit());
		assertNotSame(50000.00, company2.getToasterList().get(1).getProfit());
		assertNotSame(50000.00, company2.getToasterList().get(2).getProfit());
		company2.calculateProfit();
		assertEquals(50000.00, company2.getToasterList().get(0).getProfit());
		assertEquals(50000.00, company2.getToasterList().get(1).getProfit());
		assertEquals(50000.00, company2.getToasterList().get(2).getProfit());

		assertEquals(70000.00, company2.getProfit());
	}

	public void testCalculateCapital()
	{
		double buffer = company2.getCapital() + company2.getProfit();
		company2.calculateCapital();
		assertEquals(buffer, company2.getCapital());
	}


}
