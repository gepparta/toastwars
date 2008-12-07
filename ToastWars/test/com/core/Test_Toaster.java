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

public class Test_Toaster extends TestCase {

	private Toaster T1;
	private Type myType;
	private Master master;

	@Before
	protected void setUp() throws Exception {
		//price    = 8	
		//marketing = 1	=> 1000€	!!! 
		// Formel geändert: 6.49+((Math.pow(this.marketing/(10000-8),3)+Math.pow(this.marketing/(10000-8),2)+Math.pow(this.marketing/(10000-8),1))/80)		
		// damit man mit einer 1000 € Invetstition nicht unter +1 fällt
		//research = 1 => 8155 €
		//random   = 1.02 (variable in Game.class)
		master = (Master)UserFactory.createUser("Master", "ADMIN","ADMIN");
		master.startGame(2);
		myType = Type.TYPE1;
		T1 = new Toaster(8, 1000, 8155, 1.00, 2.37,  9.58, 2.58, 500, myType);
	}

	@After
	protected void tearDown() throws Exception {
		T1 = null;
		master = null;
		myType = null;
	}
	
	@Test
	public void testCalculateResearch()
	{
		assertEquals(1.0,T1.calculateResearch());
	}
	@Test
	public void testCalculateMarketing()
	{
		assertEquals(1.0,T1.calculateMarketing());
	}
	@Test
	public void testCalculateIndex()
	{
		assertNotNull(Game.getInstance());
		Type.TYPE1.setRandom(1.02);
		T1.getType().getRandom();
		T1.calculateIndex();
	//laut PPT von Bülow muss 1,28 rauskommen		
		assertEquals(1.28, T1.getIndex());
		Type.TYPE1.setRandom(1.05);
	}

	@Test
	public void testGetPrice() {
		assertNotNull(T1.getPrice());
		assertEquals(T1.getPrice(), 8.0);
	}

	@Test
	public void testSetPrice() {
		assertNotSame(T1.getPrice(), 9.09);
		T1.setPrice(9.09);
		assertEquals(T1.getPrice(), 9.09);
	}

	@Test
	public void testGetMarketing() {
		assertNotNull(T1.getMarketing());
		assertEquals(T1.getMarketing(), 1000.0);
	}

	@Test
	public void testSetMarketing() {
		assertNotSame(T1.getMarketing(), 6.00);
		T1.setMarketing(6.00);
		assertEquals(T1.getMarketing(), 6.00);
	}

	@Test
	public void testGetResearch() {
		assertNotNull(T1.getResearch());
		assertEquals(T1.getResearch(), 8155.0);
	}

	@Test
	public void testSetResearch() {
		assertNotSame(T1.getResearch(), 1000.0);
		T1.setResearch(1000);
		assertEquals(T1.getResearch(), 1000.0);
	}

	@Test
	public void testGetIndex() {
		assertNotNull(T1.getIndex());
		assertEquals(T1.getIndex(), 1.00);
	}

	@Test
	public void testSetIndex() {
		assertNotSame(T1.getIndex(), 2.00);
		T1.setIndex(2.00);
		assertEquals(T1.getIndex(), 2.00);
	}

	@Test
	public void testGetTurnover() {
		assertNotNull(T1.getTurnover());
		assertEquals(T1.getTurnover(), 2.37);
	}

	@Test
	public void testSetTurnover() {
		assertNotSame(T1.getTurnover(), 6.17);
		T1.setTurnover(6.17);
		assertEquals(T1.getTurnover(), 6.17);
	}



	@Test
	public void testGetCost() {
		assertNotNull(T1.getCost());
		assertEquals(T1.getCost(), 9.58);
	}

	@Test
	public void testSetCost() {
		assertNotSame(T1.getCost(), 11.11);
		T1.setCost(11.11);
		assertEquals(T1.getCost(), 11.11);
	}

	@Test
	public void testGetProfit() {
		assertNotNull(T1.getProfit());
		assertEquals(T1.getProfit(), 2.58);
	}

	@Test
	public void testSetProfit() {
		assertNotSame(T1.getProfit(), 1.00);
		T1.setProfit(1.00);
		assertEquals(T1.getProfit(), 1.00);
	}

	@Test
	public void testGetMarketShare() {
		assertNotNull(T1.getMarketShare());
		assertEquals(T1.getMarketShare(), 500);
	}

	@Test
	public void testSetMarketShare() {
		assertNotSame(T1.getMarketShare(), 205);
		T1.setMarketShare(205);
		assertEquals(T1.getMarketShare(), 205);
	}

	@Test
	public void testGetType() {
		assertNotNull(T1.getType());
		assertEquals(T1.getType(), Type.TYPE1);
	}

	@Test
	public void testSetType() {
		assertNotSame(T1.getType(), Type.TYPE3);
		T1.setType(Type.TYPE3);
		assertEquals(T1.getType(), Type.TYPE3);
	}

//	@Test
//	public void testCalculateAndSetIndex() {
//		double random = 0.95 + (Math.random() * 0.1);
//		double ergebnis = (1 / T1.getPrice()) * T1.getResearch()
//				* T1.getMarketing() * random;
//		assertEquals(T1.calculateAndSetIndex(random), ergebnis);
//
//	}
}
