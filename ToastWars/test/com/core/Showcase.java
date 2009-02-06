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
public class Showcase extends TestCase {

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

//		toaster1  = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
//		toaster2  = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
//		toaster3  = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
//		toaster3  = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
//		toaster4  = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);

		
		toaster1  = new Toaster(10.0, 9.00, 0.00,0.00,0.00,0,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 0);
		toaster2  = new Toaster(10.0, 9.00, 0.00,0.00,0.00,0,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 0);
		toaster3  = new Toaster(10.0, 9.00, 0.00,0.00,0.00,0,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 0);
		toaster4  = new Toaster(10.0, 9.00, 0.00,0.00,0.00,0,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 0);


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
		
		com1 = new Company(500, 100, 400, 800, 20, meinLager1, meineToaster1);
		com2 = new Company(500, 100, 400, 800, 20, meinLager2, meineToaster2);
		com3 = new Company(500, 100, 400, 800, 20, meinLager3, meineToaster3);
		com4 = new Company(500, 100, 400, 800, 20, meinLager4, meineToaster4);

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
	public void testShowcase()
	{
		
		//Setzen der Rundendaten
		Type.TYPE1.setMarketVolume(13440);
		Type.TYPE1.setFixCosts(10000.00);
		Type.TYPE1.setVariableCosts(2.00);
		Game.getInstance().addGroup(group4);
		sortedIndexListTyp1.clear();
		sortedIndexListTyp1.add(4);
		sortedIndexListTyp1.add(3);
		sortedIndexListTyp1.add(2);
		sortedIndexListTyp1.add(1);
		
		

		Game.getInstance().getGroupList().get(3).setCompany(com4);
		//Eingaben der ersten Gruppe
		try{toaster1.setPrice(5.00);} catch (Exception e){System.err.println(e);}
		toaster1.setProduction(5000);
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
		

		
		//Auswertung der Simulation
		
		assertEquals(7000, com1.getMarketShare());
		assertEquals(0.00, com1.getStock().getTotalStockCosts());
		assertEquals(35000.00, com1.getTurnover());
		assertEquals(26000.00, com1.getCost());
		assertEquals(109000.00, com1.getCapital());
		
		assertEquals(3000, com2.getMarketShare());
		assertEquals(0.00, com2.getStock().getTotalStockCosts());
		assertEquals(30000.00, com2.getTurnover());
		assertEquals(72000.00, com2.getCost());
		assertEquals(58000.00, com3.getCapital());
		
		
		assertEquals(1600, com3.getMarketShare());
		assertEquals(0.00, com3.getStock().getTotalStockCosts());
		assertEquals(24000.00, com3.getTurnover());
		assertEquals(99200.00, com3.getCost());
		assertEquals(24800.00, com3.getCapital());
		
		assertEquals(1840, com4.getMarketShare());
		assertEquals(160, com4.getStock().getStockTT1());
		assertEquals(36800.00, com4.getTurnover());
		assertEquals(95200.00, com4.getCost());
		assertEquals(41600.00, com4.getCapital());
		
		
	}
}//Test Game
		
		
		
		
		
		
		
		
		


