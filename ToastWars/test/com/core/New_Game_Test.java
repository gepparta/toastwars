package com.core;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Stock;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.UserFactory;

public class New_Game_Test extends TestCase {

	private Toaster meinToaster;
	private Toaster meinToaster2;
	private Toaster meinToaster3;
	private Company com;
	private Company com2;
	private Company com3;
	private Master master;
	private ArrayList<Toaster> meineToaster;
	private ArrayList<Toaster> meineToaster2;
	private ArrayList<Toaster> meineToaster3;
	private Stock meinLager;
	private Stock meinLager2;
	private Stock meinLager3;

	private ArrayList<Number> sortedIndexListTyp1 = null;
	private ArrayList<Number> sortedIndexListTyp2 = null;
	private ArrayList<Number> sortedIndexListTyp3 = null;
	
	private ArrayList<Group> groupList1 = null;
	private ArrayList<Group> groupList2 = null;
	
	private Group group1;
	private Group group2;
	private Group group3;
	private Group group4;
	
	private ArrayList<Number> list1 = null;

	@Before
	protected void setUp() throws Exception {

		master = (Master) UserFactory.createUser("Master", "ADMIN", "ADMIN");
		meineToaster = new ArrayList<Toaster>();
		meineToaster2 = new ArrayList<Toaster>();
		meineToaster3 = new ArrayList<Toaster>();

		meinToaster  = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		meinToaster2  = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		meinToaster3  = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);


		meineToaster.add(meinToaster);
		meineToaster2.add(meinToaster2);
		meineToaster3.add(meinToaster3);

		meinLager = new Stock();
		meinLager2 = new Stock();
		meinLager3 = new Stock();
		
		sortedIndexListTyp1 = new ArrayList<Number>();
		sortedIndexListTyp1.add(3);
		sortedIndexListTyp1.add(2);
		sortedIndexListTyp1.add(1);
		
		sortedIndexListTyp2 = sortedIndexListTyp1;	
		sortedIndexListTyp3 =  sortedIndexListTyp1;

		group1 = (Group)UserFactory.createUser("Group", "group1","group1" );
		group2 = new Group();
		group3 = new Group();
		group4 = new Group();

		groupList1 = new ArrayList<Group>();
		groupList1.add(group1);
		
		groupList2 = new ArrayList<Group>();
		groupList2.add(group1);
		groupList2.add(group2);
		groupList2.add(group3);
		
		com = new Company(500, 100, 400, 800, 20, meinLager, meineToaster);
		com2 = new Company(500, 100, 400, 800, 20, meinLager2, meineToaster2);
		com3 = new Company(500, 100, 400, 800, 20, meinLager3, meineToaster3);

		Game.getInstance(3);
		Game.getInstance().addGroup(group1);
		Game.getInstance().addGroup(group2);
		Game.getInstance().addGroup(group3);
		Game.getInstance().getGroupList().get(0).setCompany(com);
		Game.getInstance().getGroupList().get(1).setCompany(com2);
		Game.getInstance().getGroupList().get(2).setCompany(com3);

		Game.getInstance().setSortedIndexListTyp1(sortedIndexListTyp1);
		Game.getInstance().setSortedIndexListTyp2(sortedIndexListTyp2);
		Game.getInstance().setSortedIndexListTyp3(sortedIndexListTyp3);
		
		list1 = new ArrayList<Number>();
	}

	@After
	protected void tearDown() throws Exception {
	meinToaster = null;
	meinToaster2= null;
	meinToaster3= null;
	com= null;
	com2= null;
	com3= null;
	master= null;
	meineToaster= null;
	meineToaster2= null;
	meineToaster3= null;
	meinLager= null;
	meinLager2= null;
	meinLager3= null;

	sortedIndexListTyp1 = null;
	sortedIndexListTyp2 = null;
	sortedIndexListTyp3 = null;

	groupList1 = null;
	groupList2 = null;
	
	group1= null;
	group2= null;
	group3= null;
	group4 = null;
	
	master = null;
	
	Game.destroyGame();
	
	list1 = null;
	
	System.gc();
	}



	
	
	@Test
	public void testGetCurrentRound()
	{
		assertNotNull(Game.getInstance().getCurrentRound());
		assertEquals(1, Game.getInstance().getCurrentRound());
	}

	@Test
	public void testSetCurrentRound()
	{

		assertEquals(1, Game.getInstance().getCurrentRound());
		Game.getInstance().setCurrentRound(2);
		assertEquals(2, Game.getInstance().getCurrentRound());	
		Game.getInstance().setCurrentRound(1);
	}
	
	@Test
	public void testGetUserAmount()
	{
		assertNotNull(Game.getInstance().getUserAmount());
		assertEquals(3, Game.getInstance().getUserAmount());
	}

	@Test
	public void testSetUserAmount()
	{
		assertNotSame(5,Game.getInstance().getUserAmount());
		Game.getInstance().setUserAmount(5);
		assertEquals(5,Game.getInstance().getUserAmount());
	}
	

	@Test
	public void testSetCompanyList()
	{
		assertNotSame(groupList1, Game.getInstance().getGroupList());
		Game.getInstance().setGroupList(groupList1);
		assertEquals(groupList1, Game.getInstance().getGroupList());
	}
	
	@Test
	public void testGetCompanyList()
	{
		Game.getInstance().setGroupList(groupList2);
		assertNotNull(Game.getInstance().getGroupList());
		assertEquals(groupList2, Game.getInstance().getGroupList());
	}
	
	
	// Get&Set Test
	@Test
	public void testGetSetSortedIndexListTyp1()
	{  
		Game.getInstance().setSortedIndexListTyp1(list1);
		assertNotNull(Game.getInstance().getSortedIndexListTyp1());
		assertEquals(list1, Game.getInstance().getSortedIndexListTyp1());
	}
	
	@Test
	public void testGetSetSortedIndexListTyp2()
	{  
		Game.getInstance().setSortedIndexListTyp2(list1);
		assertNotNull(Game.getInstance().getSortedIndexListTyp2());
		assertEquals(list1, Game.getInstance().getSortedIndexListTyp2());
	}
	
	@Test
	public void testGetSetSortedIndexListTyp3()
	{  
		Game.getInstance().setSortedIndexListTyp3(list1);
		assertNotNull(Game.getInstance().getSortedIndexListTyp3());
		assertEquals(list1, Game.getInstance().getSortedIndexListTyp3());
	}
	
	
	//Berechnende Methoden
	
	
	@Test
	public void testCorrectMarketshares() {
		
	//Test für den ersten Toastertyp	
		//Indizes passend zu der vorher erzeugten ArrayList setzen
		meinToaster.setIndex(2);
		meinToaster2.setIndex(3);
		meinToaster3.setIndex(5);

		//Setzen der Marktanteile und der Produktion der einzelnen Toaster
		meinToaster.setMarketShare(1000);
		meinToaster2.setMarketShare(4000);
		meinToaster3.setMarketShare(10000);
		meinToaster.setProduction(17000);
		meinToaster2.setProduction(18000);
		meinToaster3.setProduction(1111112);

		//Ausführen der zu testenden Methode
		Game.getInstance().correctMarketShares();

	
		//Überprüfung der erwartenden Werte
		assertEquals(1000, meinToaster.getMarketShare());
		assertEquals(4000, meinToaster2.getMarketShare());
		assertEquals(10000, meinToaster3.getMarketShare());		
		assertEquals(2500, com.getStock().getStockTT1());
		assertEquals(2500, com2.getStock().getStockTT1());
		assertEquals(2500, com3.getStock().getStockTT1());
		
	//Test für den zweiten Toastertyp
		meinToaster.setType(Type.TYPE2);
		meinToaster2.setType(Type.TYPE2);
		meinToaster3.setType(Type.TYPE2);
		//Setzen der Marktanteile und der Produktion der einzelnen Toaster
		meinToaster.setMarketShare(1000);
		meinToaster2.setMarketShare(4000);
		meinToaster3.setMarketShare(10000);
		meinToaster.setProduction(2000);
		meinToaster2.setProduction(8000);
		meinToaster3.setProduction(2);

		//Ausführen der zu testenden Methode
		Game.getInstance().correctMarketShares();

		//Überprüfung der erwartenden Werte
		assertEquals(2000, meinToaster.getMarketShare());
		assertEquals(8000, meinToaster2.getMarketShare());
		assertEquals(2, meinToaster3.getMarketShare());
		assertEquals(0, com.getStock().getStockTT2());
		assertEquals(0, com2.getStock().getStockTT2());
		assertEquals(0, com3.getStock().getStockTT2());
		
		
		
		//Test für den dritten Toastertyp
		meinToaster.setType(Type.TYPE3);
		meinToaster2.setType(Type.TYPE3);
		meinToaster3.setType(Type.TYPE3);
		//Setzen der Marktanteile und der Produktion der einzelnen Toaster
		meinToaster.setMarketShare(0);
		meinToaster2.setMarketShare(4000);
		meinToaster3.setMarketShare(0);
		meinToaster.setProduction(0);
		meinToaster2.setProduction(3000);
		meinToaster3.setProduction(0);

		//Ausführen der zu testenden Methode
		Game.getInstance().correctMarketShares();

		//Überprüfung der erwartenden Werte
		assertEquals(0, meinToaster.getMarketShare());
		assertEquals(3000, meinToaster2.getMarketShare());
		assertEquals(0, meinToaster3.getMarketShare());
		assertEquals(0, com.getStock().getStockTT3());
		assertEquals(0, com2.getStock().getStockTT3());
		assertEquals(0, com3.getStock().getStockTT3());
		
	}
	
	@Test
	public void testCalculateIndexSums()
	{
		

		double[] indexSums = Game.getInstance().calculateIndexSums();
		assertEquals(27.00, indexSums[0]);
		assertEquals(0.00, indexSums[1]);
		assertEquals(0.0, indexSums[2]);
	}

	
	@Test
	public void testCompleteRound()
	{
		double design = group1.getCompany().getToasterList().get(0).getDesignInvestmentKum();
		group1.getCompany().getToasterList().get(0).setDesignInvestment(1.00);
		Game.getInstance().completeRound(group1);
		assertEquals((design+1),group1.getCompany().getToasterList().get(0).getDesignInvestmentKum());

		
	}
	
	
	@Test
	public void testAddGroup()
	{
		assertEquals(3, Game.getInstance().getGroupList().size());
		Game.getInstance().addGroup(group4);
		assertEquals(4, Game.getInstance().getGroupList().size());
		
	}
	
	@Test
	public void testDestroyGame()
	{
		assertNotNull(Game.getInstance());
		Game.destroyGame();
		assertNull(Game.getInstance());
		
	}
	@Test
	public void testKillGroup()
	{

	Game.getInstance().killGroup(group1);
	
	
	assertEquals(0.0, group1.getCompany().getTurnover());
	assertEquals(0.0, group1.getCompany().getProfit());
	assertEquals(0.0, group1.getCompany().getCost());
	assertEquals(0, group1.getCompany().getMarketShare());
	for (int i = 0; i < group1.getCompany().getToasterList().size(); i++)
	{
		assertEquals(0.0, group1.getCompany().getToasterList().get(i).getCost());
		assertEquals(0.0, group1.getCompany().getToasterList().get(i).getDesignInvestment());
		assertEquals(0.0, group1.getCompany().getToasterList().get(i).getEcologyInvestment());
		assertEquals(0.0, group1.getCompany().getToasterList().get(i).getIndex());
		assertEquals(0.0, group1.getCompany().getToasterList().get(i).getMarketing());
		assertEquals(0, group1.getCompany().getToasterList().get(i).getMarketShare());
		assertEquals(0.0, group1.getCompany().getToasterList().get(i).getNewspaperInvestment());
		assertEquals(0, group1.getCompany().getToasterList().get(i).getProduction());
		assertEquals(0.0, group1.getCompany().getToasterList().get(i).getProfit());
		assertEquals(0.0, group1.getCompany().getToasterList().get(i).getQualityInvestment());
		assertEquals(0.0, group1.getCompany().getToasterList().get(i).getRadioInvestment());
		assertEquals(0.0, group1.getCompany().getToasterList().get(i).getResearch());
		assertEquals(0.0, group1.getCompany().getToasterList().get(i).getTurnover());
		assertEquals(0.0, group1.getCompany().getToasterList().get(i).getTvInvestment());
	}
	}
	@Test
	public void testSimulate()
	{
	}
}//Test Game
		
		
		
		
		
		
		
		
		

