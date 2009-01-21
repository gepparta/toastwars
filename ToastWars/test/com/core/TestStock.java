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

public class TestStock extends TestCase {

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

	private Group group1;
	private Group group2;
	private Group group3;

	@Before
	protected void setUp() throws Exception {

		master = (Master) UserFactory.createUser("Master", "ADMIN", "ADMIN");
		meineToaster = new ArrayList<Toaster>();
		meineToaster2 = new ArrayList<Toaster>();
		meineToaster3 = new ArrayList<Toaster>();

		meinToaster = new Toaster(10.00, 3.00, 20000.00, 5000.00, 10000.00,
				3.00, 5000.00, 5000.00, 5000.00, 9.00, 1000.00, 1000.00,
				1000.00, 10000, Type.TYPE3);
		meinToaster2 = new Toaster(10.00, 3.00, 20000.00, 5000.00, 10000.00,
				3.00, 5000.00, 5000.00, 5000.00, 9.00, 1000.00, 1000.00,
				1000.00, 10000, Type.TYPE3);
		meinToaster3 = new Toaster(10.00, 3.00, 20000.00, 5000.00, 10000.00,
				3.00, 5000.00, 5000.00, 5000.00, 9.00, 1000.00, 1000.00,
				1000.00, 10000, Type.TYPE3);

		meineToaster.add(meinToaster);
		meineToaster2.add(meinToaster2);
		meineToaster3.add(meinToaster3);

		meinLager = new Stock();
		meinLager2 = new Stock();
		meinLager3 = new Stock();
		sortedIndexListTyp3 = new ArrayList<Number>();
		sortedIndexListTyp3.add(3);
		sortedIndexListTyp3.add(2);
		sortedIndexListTyp3.add(1);

		group1 = new Group();
		group2 = new Group();
		group3 = new Group();
		// turnover, cost, profit, capital, marketShare, stock,
		// ArrayList<Toaster> toasterList
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

		Game.getInstance().setSortedIndexListTyp3(sortedIndexListTyp3);
	}

	@After
	protected void tearDown() throws Exception {
	Toaster meinToaster = null;
	Toaster meinToaster2= null;
	Toaster meinToaster3= null;
	Company com= null;
	Company com2= null;
	Company com3= null;
	Master master= null;
	ArrayList<Toaster> meineToaster= null;
	ArrayList<Toaster> meineToaster2= null;
	ArrayList<Toaster> meineToaster3= null;
	Stock meinLager= null;
	Stock meinLager2= null;
	Stock meinLager3= null;

	ArrayList<Number> sortedIndexListTyp1 = null;

	ArrayList<Number> sortedIndexListTyp2 = null;

	ArrayList<Number> sortedIndexListTyp3 = null;

	Group group1= null;
	Group group2= null;
	Group group3= null;
	System.gc();
	}

	// ////////////GETTER//////////////
	@Test
	public void testCorrectMarketshares_1() {
		// Game.getInstance().getGroupList().get(0).getCompany().getToasterList().get(0).setIndex(1);
		meinToaster.setIndex(2);
		meinToaster2.setIndex(3);
		meinToaster3.setIndex(5);

		meinToaster.setMarketShare(1000);
		meinToaster2.setMarketShare(4000);
		meinToaster3.setMarketShare(10000);

		meinToaster.setProduction(7000);
		meinToaster2.setProduction(8000);
		meinToaster3.setProduction(2);



		Game.getInstance().correctMarketShares();

		
		
		assertEquals(6998, meinToaster.getMarketShare());
		assertEquals(8000, meinToaster2.getMarketShare());
		assertEquals(2, meinToaster3.getMarketShare());
		
		
		assertEquals(2, com.getStock().getStockTT3());
		assertEquals(0, com2.getStock().getStockTT3());
		assertEquals(0, com3.getStock().getStockTT3());
	}
	
//	@Test
//	public void testCorrectMarketshares_2() {
//		// Game.getInstance().getGroupList().get(0).getCompany().getToasterList().get(0).setIndex(1);
//		meinToaster.setIndex(2);
//		meinToaster2.setIndex(3);
//		meinToaster3.setIndex(5);
//
//		meinToaster.setMarketShare(1000);
//		meinToaster2.setMarketShare(4000);
//		meinToaster3.setMarketShare(10000);
//
//		meinToaster.setProduction(2000);
//		meinToaster2.setProduction(8000);
//		meinToaster3.setProduction(2);
//
//
//
//		Game.getInstance().correctMarketShares();
//
//		
//		
//		assertEquals(2000, meinToaster.getMarketShare());
//		assertEquals(8000, meinToaster2.getMarketShare());
//		assertEquals(2, meinToaster3.getMarketShare());
//		
//		
//		assertEquals(0, com.getStock().getStockTT1());
//		assertEquals(0, com2.getStock().getStockTT1());
//		assertEquals(0, com3.getStock().getStockTT1());
//	}
//	
//	@Test
//	public void testCorrectMarketshares_3() {
//		// Game.getInstance().getGroupList().get(0).getCompany().getToasterList().get(0).setIndex(1);
//		meinToaster.setIndex(1);
//		meinToaster2.setIndex(4);
//		meinToaster3.setIndex(5);
//
//		meinToaster.setMarketShare(0);
//		meinToaster2.setMarketShare(4000);
//		meinToaster3.setMarketShare(0);
//
//		meinToaster.setProduction(0);
//		meinToaster2.setProduction(3000);
//		meinToaster3.setProduction(0);
//
//
//
//		Game.getInstance().correctMarketShares();
//
//		
//		
//		assertEquals(0, meinToaster.getMarketShare());
//		assertEquals(3000, meinToaster2.getMarketShare());
//		assertEquals(0, meinToaster3.getMarketShare());
//		
//		
//		assertEquals(0, com.getStock().getStockTT1());
//		assertEquals(0, com2.getStock().getStockTT1());
//		assertEquals(0, com3.getStock().getStockTT1());
//	}
}
