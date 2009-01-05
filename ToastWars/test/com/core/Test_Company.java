package com.core;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;

public class Test_Company extends TestCase
{
	private Toaster toaster1;
	private Toaster toaster2;
	private Toaster toaster3;
	private ArrayList<Toaster> toasterList1;
	private ArrayList<Toaster> toasterList2;
	private Company company1;
	private Company company2;

	@Before
	public void setUp() throws Exception
	{
		

		toaster1 = new Toaster(10.00, 3.00, 20000.00, 5000.00, 10000.00, 3.00, 5000.00, 5000.00, 5000.00, 9.00, 1000.00, 1000.00, 1000.00, 10000, Type.TYPE1);
		toaster2 = new Toaster(30.00, 3.00, 30000.00, 10000.00, 20000.00, 3.00, 10000.00, 10000.00, 10000.00, 9.00, 1000.00, 1000.00, 1000.00, 10000, Type.TYPE2);
		toaster3 = new Toaster(150.00, 3.00, 40000.00, 20000.00, 30000.00, 3.00, 15000.00, 15000.00, 15000.00, 9.00, 1000.00, 1000.00, 1000.00, 10000, Type.TYPE3);


		toasterList1 = new ArrayList<Toaster>();
		toasterList1.add(toaster1);
		toasterList2 = new ArrayList<Toaster>();
		toasterList2.add(toaster2);
		toasterList2.add(toaster3);
		company1 = new Company( 1.05, 1.07, 2.10, 10000.00, 50, toasterList1);
		company2 = new Company( 1.05, 1.07, 2.10, 20000.00, 50, toasterList2);
	}

	@After
	public void tearDown() throws Exception
	{
		toaster1 = null;
		toaster2 = null;
		toaster3 = null;
		toasterList1 = null;
		toasterList2 = null;
		company1 = null;
		company2 = null;
		System.gc();
	}



	@Test
	public void testGetTurnover()
	{
		assertNotNull(company1.getTurnover());
		assertEquals(company1.getTurnover(), 1.05);
	}



	@Test
	public void testGetCost()
	{
		assertNotNull(company1.getCost());
		assertEquals(company1.getCost(), 1.07);
	}
	@Test
	public void testGetProfit()
	{
		assertNotNull(company1.getProfit());
		assertEquals(company1.getProfit(), 2.10);
	}
	@Test
	public void testGetCapital()
	{
		assertNotNull(company1.getCapital());
		assertEquals(company1.getCapital(), 10000.00);
	}
	
	@Test
	public void testgetMarketShare()
	{
		assertNotNull(company1.getMarketShare());
		assertEquals(company1.getMarketShare(), 50);
	}
	@Test
	public void testGetToasterList()
	{
		assertNotNull(company1.getToasterList());
		assertEquals(toasterList1, company1.getToasterList());
	}
	
	@Test
	public void testSetTurnover()
	{
		assertNotSame(company1.getTurnover(), 9.09);
		company1.setTurnover(9.09);
		assertEquals(company1.getTurnover(), 9.09);
	}
	@Test
	public void testSetCost()
	{
		assertNotSame(company1.getCost(), 1.67);
		company1.setCost(1.67);
		assertEquals(company1.getCost(), 1.67);

	}



	@Test
	public void testSetProfit()
	{
		assertNotSame(company1.getProfit(), 2.33);
		company1.setProfit(2.33);
		assertEquals(company1.getProfit(), 2.33);
	}



	@Test
	public void testSetCapital()
	{
		assertNotSame(company1.getCapital(), 2.09);
		company1.setCapital(2.09);
		assertEquals(company1.getCapital(), 2.09);
	}



	@Test
	public void testSetMarketShare()
	{
		assertNotSame(company1.getMarketShare(), 9);
		company1.setMarketShare(9);
		assertEquals(company1.getMarketShare(), 9);
	}



	@Test
	public void testSetToasterList()
	{
		assertNotSame(toasterList2, company1.getToasterList());
		company1.setToasterList(toasterList2);
		assertEquals(toasterList2, company1.getToasterList());
	}

	@Test
	public void testCalaculateIndex()
	{
		assertEquals(company1.getToasterList().size(), 1);
		assertNotSame(9.0, company1.getToasterList().get(0).getIndex());

		company1.calculateIndexWithOutRandom();

		assertEquals(9.0, company1.getToasterList().get(0).getIndex());

		assertNotSame(company2.getToasterList().get(0).getIndex(), 1.28);
		assertNotSame(company2.getToasterList().get(1).getIndex(), 1.28);

		company2.calculateIndexWithOutRandom();

		assertEquals(company2.getToasterList().size(), 2);
		assertEquals(17.95,company2.getToasterList().get(0).getIndex());
		assertEquals(9.0,company2.getToasterList().get(1).getIndex());

	}

	@Test
	public void testCalculateMarketShare()
	{
		company1.getToasterList().get(0).setIndex(9.0);
		company2.getToasterList().get(0).setIndex(9.0);
		company2.getToasterList().get(1).setIndex(9.0);
		
		double[] d = new double[3];
		d[0] = 9;
		d[1] = 9;
		d[2] = 9;

		assertNotSame(10000, company1.getToasterList().get(0).getMarketShare());
		company1.calculateMarketShares(d);
		assertEquals(10000, company1.getToasterList().get(0).getMarketShare());

		assertNotSame(6000, company2.getToasterList().get(0).getMarketShare());
		assertNotSame(2500, company2.getToasterList().get(1).getMarketShare());
		company2.calculateMarketShares(d);
		assertEquals(6000, company2.getToasterList().get(0).getMarketShare());
		assertEquals(2500, company2.getToasterList().get(1).getMarketShare());

		assertEquals(8500, company2.getMarketShare());
	}

	@Test
	public void testCalculateTurnover()
	{
		assertNotSame(100000.00, company1.getToasterList().get(0).getTurnover());
		company1.calculateTurnover();
		assertEquals(100000.00, company1.getToasterList().get(0).getTurnover());

		assertNotSame(90000.00, company2.getToasterList().get(0).getTurnover());
		assertNotSame(50000.00, company2.getToasterList().get(1).getTurnover());
		company2.calculateTurnover();
		assertEquals(90000.00, company2.getToasterList().get(0).getTurnover());
		assertEquals(50000.00, company2.getToasterList().get(1).getTurnover());

		assertEquals(100000.00, company2.getTurnover());
	}

	public void testCalculateCost()
	{

		assertNotSame(17000.00, company1.getToasterList().get(0).getCost());
		company1.calculateCost();
		assertEquals(17000.00, company1.getToasterList().get(0).getCost());

		Type.TYPE3.setFixCosts(15000.00);
		assertNotSame(95000.00, company2.getToasterList().get(0).getCost());
		assertNotSame(450000.00, company2.getToasterList().get(1).getCost());
		company2.calculateCost();
		assertEquals(95000.00, company2.getToasterList().get(0).getCost());
		assertEquals(450000.00, company2.getToasterList().get(1).getCost());

		assertEquals(545000.00, company2.getCost());
	}

	public void testCalculateProfit()
	{

		assertNotSame(20000.00, company1.getToasterList().get(0).getProfit());
		company1.calculateProfit();
		assertEquals(20000.00, company1.getToasterList().get(0).getProfit());

		assertNotSame(20000.00, company2.getToasterList().get(0).getProfit());
		assertNotSame(20000.00, company2.getToasterList().get(1).getProfit());
		company2.calculateProfit();
		assertEquals(20000.00, company2.getToasterList().get(0).getProfit());
		assertEquals(20000.00, company2.getToasterList().get(1).getProfit());

		assertEquals(40000.00, company2.getProfit());
	}

	public void testCalculateCapital()
	{
		double buffer = company2.getCapital() + company2.getProfit();
		company2.calculateCapital();
		assertEquals(company2.getCapital(), buffer);
	}

	// @Test
	// public void testCalculateAndSetMarketShare() {
	// assertEquals(C1.calculateAndSetMarketShare(10000, 20000, 0, 2.00), 5000);
	// assertEquals(C1.getMarketShare(), 5000);
	// // Wenn dieser Test gut geht, wird es nicht nochmal für alle getestet.
	// // Es geht nur darum zu Testen, dass die set Funktion vorhanden ist.
	// assertEquals(C1.getToasterList().get(0).getMarketShare(), 5000);
	// // wenn toasterattribut auch gesetzt wird :
	// // C1.getToasterList().get(0).getMarketShare();
	//
	// assertEquals(C2.calculateAndSetMarketShare(10000, 20000, 20000, 4.00),
	// 7500);
	//
	// assertEquals(C2.getMarketShare(), 7500);
	// // C2.getToasterList().get(0).getMarketShare());
	// }
	//
	// @Test
	// public void testCalculatAndSetTurnover(){
	// C1.getToasterList().get(0).setMarketShare(5000);
	// assertEquals(C1.calculateAndSetTurnover(), 5000.00);
	// assertEquals(C1.getTurnover(), 5000.00);
	//		
	// assertEquals(C1.getToasterList().get(0).getTurnover(), 5000.00);
	//
	// C2.getToasterList().get(0).setMarketShare(5000);
	// C2.getToasterList().get(1).setMarketShare(2500);
	// assertEquals(C2.calculateAndSetTurnover(),7500.00);
	//
	// assertEquals(C2.getTurnover(), 7500.00);
	// }
	//	
	// @Test
	// public void testCalculateAndSetCost(){
	// C1.getToasterList().get(0).setMarketShare(5000);
	// assertEquals(C1.calculateAndSetCost(5000.00,2.00,0.00,0.00,0.00,0.00),
	// 15000.00);
	// assertEquals(C1.getCost(), 15000.00);
	//		
	// assertEquals(C1.getToasterList().get(0).getCost(), 15000.00);
	//
	// C2.getToasterList().get(0).setMarketShare(5000);
	// C2.getToasterList().get(1).setMarketShare(2500);
	// assertEquals(C2.calculateAndSetCost(5000.00,2.00,5000.00,2.00,0.00,0.00),25000.00);
	//
	// assertEquals(C2.getCost(), 25000.00);
	// }
	//
	// @Test
	// public void testCalculateAndSetProfit(){
	// double buffer = C1.getTurnover() - C1.getCost();
	// C1.calculateAndSetProfit();
	// }
}
