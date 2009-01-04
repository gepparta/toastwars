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

	private Toaster toaster1;
	private Type myType;

	@Before
	protected void setUp() throws Exception {

		myType = Type.TYPE1;
		toaster1 = new Toaster(10.00, 3.00, 20000.00, 5000.00, 10000.00, 3.00, 5000.00, 5000.00,
				5000.00, 9.00, 1000.00, 1000.00, 1000.00, 10000, myType);

	}

	@After
	protected void tearDown() throws Exception {
		toaster1 = null;
		myType = null;
	}
//////////////GETTER//////////////
	@Test
	public void testGetPrice() {
		assertNotNull(toaster1.getPrice());
		assertEquals(toaster1.getPrice(), 10.0);
	}

	@Test
	public void testGetMarketing()

	{
		assertNotNull(toaster1.getMarketing());
		assertEquals(3.00, toaster1.getMarketing());
	}

	@Test
	public void testGetTvInvestment()

	{
		assertNotNull(toaster1.getTvInvestment());
		assertEquals(20000.0, toaster1.getTvInvestment());
	}

	@Test
	public void testGetNewsPaperInvestment()

	{
		assertNotNull(toaster1.getNewsPaperInvestment());
		assertEquals(5000.0, toaster1.getNewsPaperInvestment());
	}

	@Test
	public void testRadioInvestment()

	{
		assertNotNull(toaster1.getRadioInvestment());
		assertEquals(10000.0, toaster1.getRadioInvestment());
	}

	@Test
	public void testGetResearch() {
		assertNotNull(toaster1.getResearch());
		assertEquals(3.0, toaster1.getResearch());
	}
	
	@Test
	public void testGetQuality() {
		assertNotNull(toaster1.getQuality());
		assertEquals(5000.0, toaster1.getQuality());
	}
	
	@Test
	public void testGetDesign() {
		assertNotNull(toaster1.getDesign());
		assertEquals(5000.0, toaster1.getDesign());
	}	
	
	@Test
	public void testGetEfficiency() {
		assertNotNull(toaster1.getEfficiency());
		assertEquals(5000.0, toaster1.getEfficiency());
	}
	
	
	@Test
	public void testGetIndex() {
		assertNotNull(toaster1.getIndex());
		assertEquals(9.00, toaster1.getIndex());
	}

	@Test
	public void testGetTurnover() {
		assertNotNull(toaster1.getTurnover());
		assertEquals(1000.0, toaster1.getTurnover());
	}

	@Test
	public void testGetCost() {
		assertNotNull(toaster1.getCost());
		assertEquals(1000.0, toaster1.getCost());
	}

	@Test
	public void testGetProfit() {
		assertNotNull(toaster1.getProfit());
		assertEquals(1000.00, toaster1.getProfit());
	}

	@Test
	public void testGetMarketShare() {
		assertNotNull(toaster1.getMarketShare());
		assertEquals(10000, toaster1.getMarketShare());
	}

	@Test
	public void testGetType() {
		assertNotNull(toaster1.getType());
		assertEquals(toaster1.getType(), myType);
	}

	
	////////////SETTER/////////////////////
	@Test
	public void testSetPrice() {
		assertNotSame(15.00, toaster1.getPrice());
		try{toaster1.setPrice(15.00);}
		catch (Exception e){System.err.print(e);}
		assertEquals(15.00, toaster1.getPrice());
	}

	@Test
	public void testSetMarketing() {
		assertNotSame(6.00, toaster1.getMarketing());
		toaster1.setMarketing(6.00);
		assertEquals(6.00, toaster1.getMarketing());
	}
	
	@Test
	public void testSetTvInvestment() {
		assertNotSame(21000.00, toaster1.getTvInvestment());
		try{toaster1.setTvInvestment(21000.00);}
		catch (Exception e){System.err.print(e);}
		assertEquals(21000.00, toaster1.getTvInvestment());
	}
	
	@Test
	public void testSetNewsPaperInvestment() {
		assertNotSame(5000.00, toaster1.getNewsPaperInvestment());
		try{toaster1.setNewsPaperInvestment(5000.00);}
		catch (Exception e){System.err.print(e);}
		assertEquals(5000.00, toaster1.getNewsPaperInvestment());
	}
	
	@Test
	public void testSetRadioInvestment() {
		assertNotSame(12000.00, toaster1.getRadioInvestment());
		try{toaster1.setRadioInvestment(12000.00);}
		catch (Exception e){System.err.print(e);}
		assertEquals(12000.00, toaster1.getRadioInvestment());
	}

	@Test
	public void testSetResearch() {
		assertNotSame(1000.0, toaster1.getResearch());
		toaster1.setResearch(1000);
		assertEquals(1000.0, toaster1.getResearch());
	}

	@Test
	public void testSetIndex() {
		assertNotSame(2.00, toaster1.getIndex());
		toaster1.setIndex(2.00);
		assertEquals(2.00, toaster1.getIndex());
	}

	@Test
	public void testSetTurnover() {
		assertNotSame(6.17, toaster1.getTurnover());
		toaster1.setTurnover(6.17);
		assertEquals(6.17, toaster1.getTurnover());
	}

	@Test
	public void testSetCost() {
		assertNotSame(11.11, toaster1.getCost());
		toaster1.setCost(11.11);
		assertEquals(11.11, toaster1.getCost());
	}

	@Test
	public void testSetProfit() {
		assertNotSame(1.00, toaster1.getProfit());
		toaster1.setProfit(1.00);
		assertEquals(1.00, toaster1.getProfit());
	}

	@Test
	public void testSetMarketShare() {
		assertNotSame(205, toaster1.getMarketShare());
		toaster1.setMarketShare(205);
		assertEquals(205, toaster1.getMarketShare());
	}

	@Test
	public void testSetType() {
		assertNotSame(Type.TYPE3, toaster1.getType());
		toaster1.setType(Type.TYPE3);
		assertEquals(Type.TYPE3, toaster1.getType());
	}

	
	////Calculation///////////////////////////
	@Test
	public void testCalculateMarketShares() {

		assertNotSame(2000, toaster1.getMarketShare());
		toaster1.setIndex(2.00);
		toaster1.calculateMarketShare(5.00);
		assertEquals(4000, toaster1.getMarketShare());
	}

	@Test
	public void testCalculateTurnover() {

		assertNotSame(100000.00, toaster1.getTurnover());
		toaster1.setMarketShare(10000);
		try{toaster1.setPrice(10.00);}
		catch (Exception  e) {System.err.println(e);}
		toaster1.calculateTurnover();
		assertEquals(100000.00, toaster1.getTurnover());
	}

	@Test
	public void testCalculateCost() {
		//Fehler liegt bei den nicht berechtene Step Cost von 1000
		assertNotSame(14000.00, toaster1.getCost());
		toaster1.setMarketShare(1000);
		toaster1.calculateCost();
		assertEquals(14000.00, toaster1.getCost());
	}

	@Test
	public void testCalculateProfit() {
		assertNotSame(20000.0, toaster1.getProfit());
		toaster1.setTurnover(30000.00);
		toaster1.setCost(10000.00);
		toaster1.calculateProfit();
		assertEquals(20000.0, toaster1.getProfit());
	}
//TODO
	@Test
	public void testCalculateResearch() {

		assertEquals(3.00, toaster1.calculateResearch());

	}
//TODO
	@Test
	public void testCalculateMarketing() {
		assertEquals(3.0, toaster1.calculateMarketing());
	}

	@Test
	public void testCalculateIndex() {
		assertNotSame(11.79, toaster1.getIndex());
		toaster1.calculateIndexWithOutRandom();
		assertEquals(11.79, toaster1.getIndex());
	}
}
