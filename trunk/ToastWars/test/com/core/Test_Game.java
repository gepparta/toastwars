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
import toastwars.server.datamodel.user.Status;
import toastwars.server.datamodel.user.UserFactory;
/*
 * @ author Michael Klein
 */
public class Test_Game extends TestCase {

	private Toaster toaster1;
	private Toaster toaster2;
	private Toaster toaster3;
	private Toaster toaster4;


	private Company com1;
	private Company com2;
	private Company com3;
	private Company com4;
	private Master master;
	private ArrayList<Toaster> meineToaster1;
	private ArrayList<Toaster> meineToaster2;
	private ArrayList<Toaster> meineToaster3;
	private ArrayList<Toaster> meineToaster4;
	private Stock meinLager1;
	private Stock meinLager2;
	private Stock meinLager3;
	private Stock meinLager4;

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
		meineToaster1 = new ArrayList<Toaster>();
		meineToaster2 = new ArrayList<Toaster>();
		meineToaster3 = new ArrayList<Toaster>();
		meineToaster4 = new ArrayList<Toaster>();


		
		toaster1  = new Toaster(10.0, 9.00, 0.00,0.00,0.00,0,Type.TYPE1,3.00,0.00,0.00,0.00,0.00,0.00,0.00,3.00,0.00,0.00,0.00,0.00,0.00,0.00, 0);
		toaster2  = new Toaster(10.0, 9.00, 0.00,0.00,0.00,0,Type.TYPE1,3.00,0.00,0.00,0.00,0.00,0.00,0.00,3.00,0.00,0.00,0.00,0.00,0.00,0.00, 0);
		toaster3  = new Toaster(10.0, 9.00, 0.00,0.00,0.00,0,Type.TYPE1,3.00,0.00,0.00,0.00,0.00,0.00,0.00,3.00,0.00,0.00,0.00,0.00,0.00,0.00, 0);
		toaster4  = new Toaster(10.0, 9.00, 0.00,0.00,0.00,0,Type.TYPE1,3.00,0.00,0.00,0.00,0.00,0.00,0.00,3.00,0.00,0.00,0.00,0.00,0.00,0.00, 0);


		meineToaster1.add(toaster1);
		meineToaster2.add(toaster2);
		meineToaster3.add(toaster3);
		meineToaster4.add(toaster4);

		meinLager1 = new Stock();
		meinLager2 = new Stock();
		meinLager3 = new Stock();
		meinLager4 = new Stock();
		
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
		
		com1 = new Company(0, 0, 0, 100000.00, 0, meinLager1, meineToaster1);
		com2 = new Company(0, 0, 0, 100000.00, 0, meinLager2, meineToaster2);
		com3 = new Company(0, 0, 0, 100000.00, 0, meinLager3, meineToaster3);
		com4 = new Company(0, 0, 0, 100000.00, 0, meinLager4, meineToaster4);

		Game.getInstance(3);
		Game.getInstance().addGroup(group1);
		Game.getInstance().addGroup(group2);
		Game.getInstance().addGroup(group3);
		Game.getInstance().getGroupList().get(0).setCompany(com1);
		Game.getInstance().getGroupList().get(1).setCompany(com2);
		Game.getInstance().getGroupList().get(2).setCompany(com3);


		Game.getInstance().setSortedIndexListTyp1(sortedIndexListTyp1);
		Game.getInstance().setSortedIndexListTyp2(sortedIndexListTyp2);
		Game.getInstance().setSortedIndexListTyp3(sortedIndexListTyp3);
		
		list1 = new ArrayList<Number>();
	}

	@After
	protected void tearDown() throws Exception {
	toaster1 = null;
	toaster2= null;
	toaster3= null;
	toaster4 = null;
	com1= null;
	com2= null;
	com3= null;
	com4 = null;
	master= null;
	meineToaster1= null;
	meineToaster2= null;
	meineToaster3= null;
	meineToaster4=null;
	meinLager1= null;
	meinLager2= null;
	meinLager3= null;
	meinLager4= null;

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
		toaster1.setIndex(2);
		toaster2.setIndex(3);
		toaster3.setIndex(5);

		//Setzen der Marktanteile und der Produktion der einzelnen Toaster
		toaster1.setMarketShare(1000);
		toaster2.setMarketShare(4000);
		toaster3.setMarketShare(10000);
		toaster1.setProduction(17000);
		toaster2.setProduction(18000);
		toaster3.setProduction(1111112);

		//Ausführen der zu testenden Methode
		Game.getInstance().correctMarketShares();

	
		//Überprüfung der erwartenden Werte
		assertEquals(1000, toaster1.getMarketShare());
		assertEquals(4000, toaster2.getMarketShare());
		assertEquals(10000, toaster3.getMarketShare());		
		assertEquals(2500, com1.getStock().getStockTT1());
		assertEquals(2500, com2.getStock().getStockTT1());
		assertEquals(2500, com3.getStock().getStockTT1());
		
	//Test für den zweiten Toastertyp
		toaster1.setType(Type.TYPE2);
		toaster2.setType(Type.TYPE2);
		toaster3.setType(Type.TYPE2);
		//Setzen der Marktanteile und der Produktion der einzelnen Toaster
		toaster1.setMarketShare(1000);
		toaster2.setMarketShare(4000);
		toaster3.setMarketShare(10000);
		toaster1.setProduction(2000);
		toaster2.setProduction(8000);
		toaster3.setProduction(2);

		//Ausführen der zu testenden Methode
		Game.getInstance().correctMarketShares();

		//Überprüfung der erwartenden Werte
		assertEquals(2000, toaster1.getMarketShare());
		assertEquals(8000, toaster2.getMarketShare());
		assertEquals(2, toaster3.getMarketShare());
		assertEquals(0, com1.getStock().getStockTT2());
		assertEquals(0, com2.getStock().getStockTT2());
		assertEquals(0, com3.getStock().getStockTT2());
		
		
		
		//Test für den dritten Toastertyp
		toaster1.setType(Type.TYPE3);
		toaster2.setType(Type.TYPE3);
		toaster3.setType(Type.TYPE3);
		//Setzen der Marktanteile und der Produktion der einzelnen Toaster
		toaster1.setMarketShare(0);
		toaster2.setMarketShare(4000);
		toaster3.setMarketShare(0);
		toaster1.setProduction(0);
		toaster2.setProduction(3000);
		toaster3.setProduction(0);

		//Ausführen der zu testenden Methode
		Game.getInstance().correctMarketShares();

		//Überprüfung der erwartenden Werte
		assertEquals(0, toaster1.getMarketShare());
		assertEquals(3000, toaster2.getMarketShare());
		assertEquals(0, toaster3.getMarketShare());
		assertEquals(0, com1.getStock().getStockTT3());
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
	public void testSimulate()
	{
		
		//Setzen der Rundendaten
		Game.getInstance().addGroup(group4);
		Game.getInstance().getGroupList().get(3).setCompany(com4);
		
		sortedIndexListTyp1.add(2);
		sortedIndexListTyp1.add(1);
		sortedIndexListTyp1.add(3);
		sortedIndexListTyp1.add(4);

		Type.TYPE1.setMarketVolumeTT1(4);
		Type.TYPE1.setFixCosts(10000.00);
		Type.TYPE1.setVariableCosts(2.00);

		

		//Eingaben der ersten Gruppe
		try{toaster1.setPrice(5.00);} catch (Exception e){System.err.println(e);}
		toaster1.setProduction(7000);
		Game.getInstance().getGroupList().get(0).setStatus(Status.COMPLETED);
		
		//Eingaben der zweiten Gruppe
		try{toaster2.setPrice(10.00);} catch (Exception e){System.err.println(e);}
		toaster2.setProduction(3000);
		toaster2.setNewspaperInvestment(5000.00);
		toaster2.setRadioInvestment(10000.00);
		toaster2.setTvInvestment(20000.00);
		toaster2.setQualityInvestment(5000.00);
		toaster2.setDesignInvestment(5000.00);
		toaster2.setEcologyInvestment(5000.00);
		com2.setMarketResearchReportON(true);
		Game.getInstance().getGroupList().get(1).setStatus(Status.COMPLETED);
		
		//Eingaben der dritten Gruppe
		try{toaster3.setPrice(15.00);} catch (Exception e){System.err.println(e);}
		toaster3.setProduction(1600);
		toaster3.setQualityInvestment(80000.00);
		com3.setMarketResearchReportON(true);
		Game.getInstance().getGroupList().get(2).setStatus(Status.COMPLETED);
		
		
		//Eingaben der vierten Gruppe
		try{toaster4.setPrice(20.00);} catch (Exception e){System.err.println(e);}
		toaster4.setProduction(2000);
		toaster4.setTvInvestment(80000.00);
		Game.getInstance().getGroupList().get(3).setStatus(Status.COMPLETED);
		
		//Simulation
		
		for (int a = 0; a < Game.getInstance().getGroupList().size(); a++)
		{
			if (Game.getInstance().getGroupList().get(a).getStatus() != Status.INACTIVE)
				Game.getInstance().getGroupList().get(a).getCompany().calculateIndex();
		}
		Game.getInstance().simulate();
		
		//Auswertung der Simulation (Die Investitionen sind noch nicht vom Kapital abgezogen)
		
		assertEquals(7000, com1.getMarketShare());
		assertEquals(0.00, com1.getStock().getTotalStockCosts());
		assertEquals(35000.00, com1.getTurnover());
		assertEquals(26000.00, com1.getCost());
		assertEquals(109000.00, com1.getCapital());
		
		assertEquals(3000, com2.getMarketShare());
		assertEquals(0.00, com2.getStock().getTotalStockCosts());
		assertEquals(30000.00, com2.getTurnover());
		assertEquals(17000.00, com2.getCost());
		assertEquals(113000.00, com2.getCapital());
		
		
		assertEquals(1600, com3.getMarketShare());
		assertEquals(0.00, com3.getStock().getTotalStockCosts());
		assertEquals(24000.00, com3.getTurnover());
		assertEquals(14200.00, com3.getCost());
		assertEquals(109800.00, com3.getCapital());
		
		assertEquals(1840, com4.getMarketShare());
		assertEquals(160, com4.getStock().getStockTT1());
		assertEquals(36800.00, com4.getTurnover());
		assertEquals(15200.00, com4.getCost());
		assertEquals(121600.00, com4.getCapital());
		
		
	}
}//Test Game
		
		
		
		
		
		
		
		
		

