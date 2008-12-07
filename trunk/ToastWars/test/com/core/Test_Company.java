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
	private Toaster T1;
	private Toaster T2;
	private Toaster T3;
	private ArrayList<Toaster> A1;
	private ArrayList<Toaster> A2;
	private Company C1;


	@Before
	public void setUp() throws Exception {
		myType1 = Type.TYPE1;
		myType2 = Type.TYPE2;
		myType3 = Type.TYPE3;
		T1 = new Toaster(8, 1000, 8155, 1.00, 2.37,  9.58, 2.58, 500, myType1);
		T2 = new Toaster(8, 1000, 8155, 1.00, 2.37,  9.58, 2.58, 500, myType2);
		T3 = new Toaster(8, 1000, 8155, 1.00, 2.37,  9.58, 2.58, 500, myType3);
		A1 = new ArrayList<Toaster>();
		A1.add(T1);
		A2 = new ArrayList<Toaster>();
		A2.add(T2);
		A2.add(T3);
		C1 = new Company("Test1", 1.05, 1.07, 2.10, 2.13, 50, A1);

	}

	@After
	public void tearDown() throws Exception {
		myType1 =null;
		myType2 =null;
		myType3 =null;
		T1 = null;
		T2 = null;
		T3 = null;
		A1 = null;
		A2 = null;
		C1 = null;

	}

	@Test
	public void testGetDescription() {

		String t = C1.getDescription();
		assertNotNull(t);
		assertEquals(t, "Test1");
	}

	@Test
	public void testSetDescription() {

		assertNotSame(C1.getDescription(), "hallo");
		C1.setDescription("hallo");
		assertEquals("hallo", C1.getDescription());
	}

	@Test
	public void testGetTurnover() {
		assertNotNull(C1.getTurnover());
		assertEquals(C1.getTurnover(), 1.05);
	}

	@Test
	public void testSetTurnover() {
		assertNotSame(C1.getTurnover(), 9.09);
		C1.setTurnover(9.09);
		assertEquals(C1.getTurnover(), 9.09);
	}

	@Test
	public void testGetCost() {
		assertNotNull(C1.getCost());
		assertEquals(C1.getCost(), 1.07);
	}

	@Test
	public void testSetCost() {
		assertNotSame(C1.getCost(), 1.67);
		C1.setCost(1.67);
		assertEquals(C1.getCost(), 1.67);

	}

	@Test
	public void testGetProfit() {
		assertNotNull(C1.getProfit());
		assertEquals(C1.getProfit(), 2.10);
	}

	@Test
	public void testSetProfit() {
		assertNotSame(C1.getProfit(), 2.33);
		C1.setProfit(2.33);
		assertEquals(C1.getProfit(), 2.33);
	}

	@Test
	public void testGetCapital() {
		assertNotNull(C1.getCapital());
		assertEquals(C1.getCapital(), 2.13);
	}

	@Test
	public void testSetCapital() {
		assertNotSame(C1.getCapital(), 2.09);
		C1.setCapital(2.09);
		assertEquals(C1.getCapital(), 2.09);
	}

	@Test
	public void testgetMarketShare() {
		assertNotNull(C1.getMarketShare());
		assertEquals(C1.getMarketShare(), 50);
	}

	@Test
	public void testSetMarketShare() {
		assertNotSame(C1.getMarketShare(), 9);
		C1.setMarketShare(9);
		assertEquals(C1.getMarketShare(), 9);
	}

	@Test
	public void testGetToasterList() {
		assertNotNull(C1.getToasterList());
		assertEquals(A1, C1.getToasterList());
	}

	@Test
	public void testSetToasterList() {
		assertNotSame(A2, C1.getToasterList());
		C1.setToasterList(A2);
		assertEquals(A2, C1.getToasterList());
	}

	
	@Test
	public void testCalaculateIndex(){
		Type.TYPE1.setRandom(1.02);
		Type.TYPE2.setRandom(1.02);
		Type.TYPE3.setRandom(1.02);
		
		C1.calculateIndex();
		assertEquals(C1.getToasterList().size(), 1);
		assertEquals(C1.getToasterList().get(0).getIndex(),1.28);
		
		C1 = new Company("Test1", 1.05, 1.07, 2.10, 2.13, 50, A2);
		
		C1.calculateIndex();
		assertEquals(C1.getToasterList().size(), 2);
		assertEquals(C1.getToasterList().get(0).getIndex(),1.28);
		assertEquals(C1.getToasterList().get(1).getIndex(),1.28);

		
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
