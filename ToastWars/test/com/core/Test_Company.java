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

	private Type myType1;
	private Type myType2;
	private Type myType3;
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
		myType1 = Type.TYPE1;
		myType2 = Type.TYPE2;
		myType3 = Type.TYPE3;
		toaster1 = new Toaster(8, 1000, 8155, 1.00, 10000, 5000, 2.58, 500, myType1);
		toaster2 = new Toaster(8, 1000, 8155, 1.00, 10000, 5000, 2.58, 500, myType2);
		toaster3 = new Toaster(8, 1000, 8155, 2.00, 10000, 5000, 2.58, 500, myType3);
		toasterList1 = new ArrayList<Toaster>();
		toasterList1.add(toaster1);
		toasterList2 = new ArrayList<Toaster>();
		toasterList2.add(toaster2);
		toasterList2.add(toaster3);
		company1 = new Company("Test1", 1.05, 1.07, 2.10, 10000.00, 50, toasterList1);
		company2 = new Company("Test1", 1.05, 1.07, 2.10, 20000.00, 50, toasterList2);

	}

	@After
	public void tearDown() throws Exception
	{
		myType1 = null;
		myType2 = null;
		myType3 = null;
		toaster1 = null;
		toaster2 = null;
		toaster3 = null;
		toasterList1 = null;
		toasterList2 = null;
		company1 = null;
		company2 = null;

	}

	@Test
	public void testGetDescription()
	{

		String t = company1.getDescription();
		assertNotNull(t);
		assertEquals(t, "Test1");
	}

	@Test
	public void testSetDescription()
	{

		assertNotSame(company1.getDescription(), "hallo");
		company1.setDescription("hallo");
		assertEquals("hallo", company1.getDescription());
	}

	@Test
	public void testGetTurnover()
	{
		assertNotNull(company1.getTurnover());
		assertEquals(company1.getTurnover(), 1.05);
	}

	@Test
	public void testSetTurnover()
	{
		assertNotSame(company1.getTurnover(), 9.09);
		company1.setTurnover(9.09);
		assertEquals(company1.getTurnover(), 9.09);
	}

	@Test
	public void testGetCost()
	{
		assertNotNull(company1.getCost());
		assertEquals(company1.getCost(), 1.07);
	}

	@Test
	public void testSetCost()
	{
		assertNotSame(company1.getCost(), 1.67);
		company1.setCost(1.67);
		assertEquals(company1.getCost(), 1.67);

	}

	@Test
	public void testGetProfit()
	{
		assertNotNull(company1.getProfit());
		assertEquals(company1.getProfit(), 2.10);
	}

	@Test
	public void testSetProfit()
	{
		assertNotSame(company1.getProfit(), 2.33);
		company1.setProfit(2.33);
		assertEquals(company1.getProfit(), 2.33);
	}

	@Test
	public void testGetCapital()
	{
		assertNotNull(company1.getCapital());
		assertEquals(company1.getCapital(), 10000.00);
	}

	@Test
	public void testSetCapital()
	{
		assertNotSame(company1.getCapital(), 2.09);
		company1.setCapital(2.09);
		assertEquals(company1.getCapital(), 2.09);
	}

	@Test
	public void testgetMarketShare()
	{
		assertNotNull(company1.getMarketShare());
		assertEquals(company1.getMarketShare(), 50);
	}

	@Test
	public void testSetMarketShare()
	{
		assertNotSame(company1.getMarketShare(), 9);
		company1.setMarketShare(9);
		assertEquals(company1.getMarketShare(), 9);
	}

	@Test
	public void testGetToasterList()
	{
		assertNotNull(company1.getToasterList());
		assertEquals(toasterList1, company1.getToasterList());
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
		Type.TYPE1.setRandom(1.02);
		Type.TYPE2.setRandom(1.02);
		Type.TYPE3.setRandom(1.02);

		assertEquals(company1.getToasterList().size(), 1);
		assertNotSame(company1.getToasterList().get(0).getIndex(), 1.28);

		company1.calculateIndex();

		assertEquals(company1.getToasterList().get(0).getIndex(), 1.28);

		assertNotSame(company2.getToasterList().get(0).getIndex(), 1.28);
		assertNotSame(company2.getToasterList().get(1).getIndex(), 1.28);

		company2.calculateIndex();

		assertEquals(company2.getToasterList().size(), 2);
		assertEquals(company2.getToasterList().get(0).getIndex(), 1.28);
		assertEquals(company2.getToasterList().get(1).getIndex(), 1.28);

	}

	@Test
	public void testCalculateMarketShare()
	{
		double[] d = new double[3];
		d[0] = 5;
		d[1] = 10;

		assertNotSame(2000, company1.getToasterList().get(0).getMarketShare());
		company1.calculateMarketShares(d);
		assertEquals(2000, company1.getToasterList().get(0).getMarketShare());

		assertNotSame(2000, company2.getToasterList().get(0).getMarketShare());
		assertNotSame(2000, company2.getToasterList().get(1).getMarketShare());
		company2.calculateMarketShares(d);
		assertEquals(4000, company2.getToasterList().get(0).getMarketShare());
		assertEquals(6000, company2.getToasterList().get(1).getMarketShare());

		assertEquals(10000, company2.getMarketShare());
	}

	@Test
	public void testCalculateTurnover()
	{

		assertNotSame(4000.00, company1.getToasterList().get(0).getTurnover());
		company1.calculateTurnover();
		assertEquals(4000.00, company1.getToasterList().get(0).getTurnover());

		assertNotSame(4000.00, company2.getToasterList().get(0).getTurnover());
		assertNotSame(4000.00, company2.getToasterList().get(1).getTurnover());
		company2.calculateTurnover();
		assertEquals(4000.00, company2.getToasterList().get(0).getTurnover());
		assertEquals(4000.00, company2.getToasterList().get(1).getTurnover());

		assertEquals(8000.00, company2.getTurnover());
	}

	public void testCalculateCost()
	{

		assertNotSame(10000.00, company1.getToasterList().get(0).getCost());
		company1.calculateCost();
		assertEquals(10000.00, company1.getToasterList().get(0).getCost());

		Type.TYPE3.setFixCost(15000.00);
		assertNotSame(10000.00, company2.getToasterList().get(0).getCost());
		assertNotSame(20000.00, company2.getToasterList().get(1).getCost());
		company2.calculateCost();
		assertEquals(10000.00, company2.getToasterList().get(0).getCost());
		assertEquals(20000.00, company2.getToasterList().get(1).getCost());

		assertEquals(30000.00, company2.getCost());
	}

	public void testCalculateProfit()
	{

		assertNotSame(5000.00, company1.getToasterList().get(0).getProfit());
		company1.calculateProfit();
		assertEquals(5000.00, company1.getToasterList().get(0).getProfit());

		assertNotSame(5000.00, company2.getToasterList().get(0).getProfit());
		assertNotSame(5000.00, company2.getToasterList().get(1).getProfit());
		company2.calculateProfit();
		assertEquals(5000.00, company2.getToasterList().get(0).getProfit());
		assertEquals(5000.00, company2.getToasterList().get(1).getProfit());

		assertEquals(10000.00, company2.getProfit());
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
