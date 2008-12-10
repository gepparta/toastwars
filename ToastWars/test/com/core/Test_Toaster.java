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

	private Toaster t1;
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
		t1 = new Toaster(8, 1000, 8155, 1.00, 10.58,  9.58, 2.58, 500, myType);
	
	}

	@After
	protected void tearDown() throws Exception {
		t1 = null;
		master = null;
		myType = null;
	}
	
	@Test
	public void testCalculateResearch()
	{
		assertEquals(1.0,t1.calculateResearch());
	}
	@Test
	public void testCalculateMarketing()
	{
		assertEquals(1.0,t1.calculateMarketing());
	}
	@Test
	public void testCalculateIndex()
	{
		assertNotNull(Game.getInstance());
		Type.TYPE1.setRandom(1.02);
		t1.calculateIndexWithOutRandom();
	//laut PPT von Bülow muss 1,28 rauskommen		
		assertEquals(1.28, t1.getIndex());
	}

	@Test
	public void testGetPrice() {
		assertNotNull(t1.getPrice());
		assertEquals(t1.getPrice(), 8.0);
	}

	@Test
	public void testSetPrice() {
		assertNotSame(t1.getPrice(), 9.09);
		t1.setPrice(9.09);
		assertEquals(t1.getPrice(), 9.09);
	}

	@Test
	public void testGetMarketing() {
		assertNotNull(t1.getMarketing());
		assertEquals(t1.getMarketing(), 1000.0);
	}

	@Test
	public void testSetMarketing() {
		assertNotSame(t1.getMarketing(), 6.00);
		t1.setMarketing(6.00);
		assertEquals(t1.getMarketing(), 6.00);
	}

	@Test
	public void testGetResearch() {
		assertNotNull(t1.getResearch());
		assertEquals(t1.getResearch(), 8155.0);
	}

	@Test
	public void testSetResearch() {
		assertNotSame(t1.getResearch(), 1000.0);
		t1.setResearch(1000);
		assertEquals(t1.getResearch(), 1000.0);
	}

	@Test
	public void testGetIndex() {
		assertNotNull(t1.getIndex());
		assertEquals(t1.getIndex(), 1.00);
	}

	@Test
	public void testSetIndex() {
		assertNotSame(t1.getIndex(), 2.00);
		t1.setIndex(2.00);
		assertEquals(t1.getIndex(), 2.00);
	}

	@Test
	public void testGetTurnover() {
		assertNotNull(t1.getTurnover());
		assertEquals(t1.getTurnover(), 10.58);
	}

	@Test
	public void testSetTurnover() {
		assertNotSame(t1.getTurnover(), 6.17);
		t1.setTurnover(6.17);
		assertEquals(t1.getTurnover(), 6.17);
	}



	@Test
	public void testGetCost() {
		assertNotNull(t1.getCost());
		assertEquals(t1.getCost(), 9.58);
	}

	@Test
	public void testSetCost() {
		assertNotSame(t1.getCost(), 11.11);
		t1.setCost(11.11);
		assertEquals(t1.getCost(), 11.11);
	}

	@Test
	public void testGetProfit() {
		assertNotNull(t1.getProfit());
		assertEquals(t1.getProfit(), 2.58);
	}

	@Test
	public void testSetProfit() {
		assertNotSame(t1.getProfit(), 1.00);
		t1.setProfit(1.00);
		assertEquals(t1.getProfit(), 1.00);
	}

	@Test
	public void testGetMarketShare() {
		assertNotNull(t1.getMarketShare());
		assertEquals(t1.getMarketShare(), 500);
	}

	@Test
	public void testSetMarketShare() {
		assertNotSame(t1.getMarketShare(), 205);
		t1.setMarketShare(205);
		assertEquals(t1.getMarketShare(), 205);
	}

	@Test
	public void testGetType() {
		assertNotNull(t1.getType());
		assertEquals(t1.getType(), Type.TYPE1);
	}

	@Test
	public void testSetType() {
		assertNotSame(t1.getType(), Type.TYPE3);
		t1.setType(Type.TYPE3);
		assertEquals(t1.getType(), Type.TYPE3);
	}

	@Test
	public void testCalculateMarketShares(){
		
		assertNotSame(2000,t1.getMarketShare());
		t1.calculateMarketShare(5.00);
		assertEquals(2000, t1.getMarketShare());
	}
	
	@Test
	public void testCalculateTurnover(){
		
		assertNotSame(4000.00,t1.getTurnover());
		t1.calculateTurnover();
		assertEquals(4000.00,t1.getTurnover());
	}
	
	@Test
	public void testCalculateCost(){
		assertNotSame(10000.00,t1.getCost());
		t1.calculateCost();
		assertEquals(10000.00,t1.getCost());
	}
	
	@Test
	public void testCalculateProfit(){
		assertNotSame(1.00,t1.getProfit());
		t1.calculateProfit();
		assertEquals(1.00,t1.getProfit());
	}
}
