package com.core;

import java.util.ArrayList;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;

public class Test_Company extends TestCase {

	private Type myType1;
	private Type myType2;
	private Type myType3;
	private Toaster t1;
	private Toaster t2;
	private Toaster t3;
	private ArrayList<Toaster> a1;
	private ArrayList<Toaster> a2;
	private Company c1;
	private Company c2;


	@Before
	public void setUp() throws Exception {
		myType1 = Type.TYPE1;
		myType2 = Type.TYPE2;
		myType3 = Type.TYPE3;
		t1 = new Toaster(8, 1000, 8155, 1.00, 10000,  5000, 2.58, 500, myType1);
		t2 = new Toaster(8, 1000, 8155, 1.00, 10000,  5000, 2.58, 500, myType2);
		t3 = new Toaster(8, 1000, 8155, 2.00, 10000,  5000, 2.58, 500, myType3);
		a1 = new ArrayList<Toaster>();
		a1.add(t1);
		a2 = new ArrayList<Toaster>();
		a2.add(t2);
		a2.add(t3);
		c1 = new Company("Test1", 1.05, 1.07, 2.10, 10000.00, 50, a1);
		c2 = new Company("Test1", 1.05, 1.07, 2.10, 20000.00, 50, a2);

	}

	@After
	public void tearDown() throws Exception {
		myType1 =null;
		myType2 =null;
		myType3 =null;
		t1 = null;
		t2 = null;
		t3 = null;
		a1 = null;
		a2 = null;
		c1 = null;
		c2=null;

	}

	@Test
	public void testGetDescription() {

		String t = c1.getDescription();
		assertNotNull(t);
		assertEquals(t, "Test1");
	}

	@Test
	public void testSetDescription() {

		assertNotSame(c1.getDescription(), "hallo");
		c1.setDescription("hallo");
		assertEquals("hallo", c1.getDescription());
	}

	@Test
	public void testGetTurnover() {
		assertNotNull(c1.getTurnover());
		assertEquals(c1.getTurnover(), 1.05);
	}

	@Test
	public void testSetTurnover() {
		assertNotSame(c1.getTurnover(), 9.09);
		c1.setTurnover(9.09);
		assertEquals(c1.getTurnover(), 9.09);
	}

	@Test
	public void testGetCost() {
		assertNotNull(c1.getCost());
		assertEquals(c1.getCost(), 1.07);
	}

	@Test
	public void testSetCost() {
		assertNotSame(c1.getCost(), 1.67);
		c1.setCost(1.67);
		assertEquals(c1.getCost(), 1.67);

	}

	@Test
	public void testGetProfit() {
		assertNotNull(c1.getProfit());
		assertEquals(c1.getProfit(), 2.10);
	}

	@Test
	public void testSetProfit() {
		assertNotSame(c1.getProfit(), 2.33);
		c1.setProfit(2.33);
		assertEquals(c1.getProfit(), 2.33);
	}

	@Test
	public void testGetCapital() {
		assertNotNull(c1.getCapital());
		assertEquals(c1.getCapital(), 10000.00);
	}

	@Test
	public void testSetCapital() {
		assertNotSame(c1.getCapital(), 2.09);
		c1.setCapital(2.09);
		assertEquals(c1.getCapital(), 2.09);
	}

	@Test
	public void testgetMarketShare() {
		assertNotNull(c1.getMarketShare());
		assertEquals(c1.getMarketShare(), 50);
	}

	@Test
	public void testSetMarketShare() {
		assertNotSame(c1.getMarketShare(), 9);
		c1.setMarketShare(9);
		assertEquals(c1.getMarketShare(), 9);
	}

	@Test
	public void testGetToasterList() {
		assertNotNull(c1.getToasterList());
		assertEquals(a1, c1.getToasterList());
	}

	@Test
	public void testSetToasterList() {
		assertNotSame(a2, c1.getToasterList());
		c1.setToasterList(a2);
		assertEquals(a2, c1.getToasterList());
	}

	
	@Test
	public void testCalaculateIndex(){
		Type.TYPE1.setRandom(1.02);
		Type.TYPE2.setRandom(1.02);
		Type.TYPE3.setRandom(1.02);
		
		assertEquals(c1.getToasterList().size(), 1);
		assertNotSame(c1.getToasterList().get(0).getIndex(),1.28);
		
		c1.calculateIndex();
	
		assertEquals(c1.getToasterList().get(0).getIndex(),1.28);
		
		

		
		assertNotSame(c2.getToasterList().get(0).getIndex(),1.28);
		assertNotSame(c2.getToasterList().get(1).getIndex(),1.28);
		
		c2.calculateIndex();
		
		assertEquals(c2.getToasterList().size(), 2);
		assertEquals(c2.getToasterList().get(0).getIndex(),1.28);
		assertEquals(c2.getToasterList().get(1).getIndex(),1.28);

		
	}
	
	@Test
	public void testCalculateMarketShare(){
		double[] d = new double[3];
		d[0]=5;
		d[1]=10;
		
		assertNotSame(2000, c1.getToasterList().get(0).getMarketShare());
		c1.calculateMarketShares(d);
		assertEquals(2000, c1.getToasterList().get(0).getMarketShare());
		
		
		assertNotSame(2000, c2.getToasterList().get(0).getMarketShare());
		assertNotSame(2000, c2.getToasterList().get(1).getMarketShare());
		c2.calculateMarketShares(d);
		assertEquals(4000, c2.getToasterList().get(0).getMarketShare());
		assertEquals(6000, c2.getToasterList().get(1).getMarketShare());
		
		assertEquals(10000,c2.getMarketShare());
	}
	
	
	@Test
	public void testCalculateTurnover(){
		
		assertNotSame(4000.00, c1.getToasterList().get(0).getTurnover());
		c1.calculateTurnover();
		assertEquals(4000.00, c1.getToasterList().get(0).getTurnover());
		
		
		assertNotSame(4000.00, c2.getToasterList().get(0).getTurnover());
		assertNotSame(4000.00, c2.getToasterList().get(1).getTurnover());
		c2.calculateTurnover();
		assertEquals(4000.00, c2.getToasterList().get(0).getTurnover());
		assertEquals(4000.00, c2.getToasterList().get(1).getTurnover());
		
		assertEquals(8000.00, c2.getTurnover());
	}
	public void testCalculateCost(){
		
		assertNotSame(10000.00, c1.getToasterList().get(0).getCost());
		c1.calculateCost();
		assertEquals(10000.00, c1.getToasterList().get(0).getCost());
		
		Type.TYPE3.setFixCost(15000.00);
		assertNotSame(10000.00, c2.getToasterList().get(0).getCost());
		assertNotSame(20000.00, c2.getToasterList().get(1).getCost());
		c2.calculateCost();
		assertEquals(10000.00, c2.getToasterList().get(0).getCost());
		assertEquals(20000.00, c2.getToasterList().get(1).getCost());
		
		assertEquals(30000.00, c2.getCost());
	}
	
	public void testCalculateProfit(){
		
		assertNotSame(5000.00, c1.getToasterList().get(0).getProfit());
		c1.calculateProfit();
		assertEquals(5000.00, c1.getToasterList().get(0).getProfit());

		assertNotSame(5000.00, c2.getToasterList().get(0).getProfit());
		assertNotSame(5000.00, c2.getToasterList().get(1).getProfit());
		c2.calculateProfit();
		assertEquals(5000.00, c2.getToasterList().get(0).getProfit());
		assertEquals(5000.00, c2.getToasterList().get(1).getProfit());
		
		assertEquals(10000.00, c2.getProfit());
	}
	
	public void testCalculateCapital(){
		double buffer =c2.getCapital()+c2.getProfit();
		c2.calculateCapital();
		assertEquals(c2.getCapital(), buffer);
	}
	
//	@Test
//	public void testCalculateAndSetMarketShare() {
//		assertEquals(C1.calculateAndSetMarketShare(10000, 20000, 0, 2.00), 5000);
//		assertEquals(C1.getMarketShare(), 5000);
//		// Wenn dieser Test gut geht, wird es nicht nochmal für alle getestet.
//		// Es geht nur darum zu Testen, dass die set Funktion vorhanden ist.
//		assertEquals(C1.getToasterList().get(0).getMarketShare(), 5000);
//		// wenn toasterattribut auch gesetzt wird :
//		// C1.getToasterList().get(0).getMarketShare();
//
//		assertEquals(C2.calculateAndSetMarketShare(10000, 20000, 20000, 4.00),
//				7500);
//
//		assertEquals(C2.getMarketShare(), 7500);
//		// C2.getToasterList().get(0).getMarketShare());
//	}
//
//	@Test
//	public void testCalculatAndSetTurnover(){
//		C1.getToasterList().get(0).setMarketShare(5000);
//		assertEquals(C1.calculateAndSetTurnover(), 5000.00);
//		assertEquals(C1.getTurnover(), 5000.00);
//		
//		assertEquals(C1.getToasterList().get(0).getTurnover(), 5000.00);
//
//		C2.getToasterList().get(0).setMarketShare(5000);
//		C2.getToasterList().get(1).setMarketShare(2500);
//		assertEquals(C2.calculateAndSetTurnover(),7500.00);
//
//		assertEquals(C2.getTurnover(), 7500.00);
//	}
//	
//	@Test
//	public void testCalculateAndSetCost(){
//		C1.getToasterList().get(0).setMarketShare(5000);
//		assertEquals(C1.calculateAndSetCost(5000.00,2.00,0.00,0.00,0.00,0.00), 15000.00);
//		assertEquals(C1.getCost(), 15000.00);
//		
//		assertEquals(C1.getToasterList().get(0).getCost(), 15000.00);
//
//		C2.getToasterList().get(0).setMarketShare(5000);
//		C2.getToasterList().get(1).setMarketShare(2500);
//		assertEquals(C2.calculateAndSetCost(5000.00,2.00,5000.00,2.00,0.00,0.00),25000.00);
//
//		assertEquals(C2.getCost(), 25000.00);
//	}
//
//	@Test
//	public void testCalculateAndSetProfit(){
//		double buffer = C1.getTurnover() - C1.getCost();
//		C1.calculateAndSetProfit();
//	}
}
