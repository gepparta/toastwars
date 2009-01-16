package com.core;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.ToastWarsServiceImpl;
import toastwars.server.dao.DAOGame;
import toastwars.server.dao.DAOUser;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.UserFactory;

public class Test_MarketResearchReport extends TestCase
{
	private Toaster toaster1;
	private Toaster toaster2;
	private Toaster toaster3;
	private Toaster toaster4;
	private Toaster toaster5;
	private Toaster toaster6;
	private ArrayList<Toaster> toasterList1;
	private ArrayList<Toaster> toasterList2;
	private ArrayList<Toaster> toasterList3;
	private Company company1;
	private Company company2;
	private Company company3;
	private Master master;
	
	private ToastWarsServiceImpl impl;

	@Before
	public void setUp() throws Exception
	{
		impl = new ToastWarsServiceImpl();
		DAOGame.resetGame();
		// price, 
		// marketing, tvInvestment, newsPaperInvestment, radioInvestment,
		// research, quality, design, efficiency, 
		// index, turnover, cost, profit, marketShare, type
		toaster1 = new Toaster(10.00, 3.00, 20000.00, 5000.00, 10000.00, 3.00, 5000.00, 5000.00, 5000.00, 9.00, 100000.00, 43000.00, 1000.00, 10000, Type.TYPE1);
		toaster2 = new Toaster(40.00, 3.00, 30000.00, 10000.00, 20000.00, 3.00, 10000.00, 10000.00, 10000.00, 9.00, 240000.00, 130000.00, 1000.00, 6000, Type.TYPE2);
		toaster3 = new Toaster(150.00, 3.00, 40000.00, 20000.00, 30000.00, 3.00, 15000.00, 15000.00, 15000.00, 9.00, 375000.00, 155000.00, 1000.00, 2500, Type.TYPE3);

		toaster4 = new Toaster(12.00, 3.00, 20000.00, 5000.00, 10000.00, 3.00, 5000.00, 5000.00, 5000.00, 9.00, 100000.00, 43000.00, 1000.00, 10000, Type.TYPE1);
		toaster5 = new Toaster(42.00, 3.00, 30000.00, 10000.00, 20000.00, 3.00, 10000.00, 10000.00, 10000.00, 9.00, 240000.00, 130000.00, 1000.00, 6000, Type.TYPE2);
		toaster6 = new Toaster(152.00, 3.00, 40000.00, 20000.00, 30000.00, 3.00, 15000.00, 15000.00, 15000.00, 9.00, 375000.00, 155000.00, 1000.00, 2500, Type.TYPE3);
		
		toasterList1 = new ArrayList<Toaster>();
		toasterList1.add(toaster1);
		toasterList2 = new ArrayList<Toaster>();
		toasterList2.add(toaster2);
		toasterList2.add(toaster3);
		
		toasterList3 = new ArrayList<Toaster>();
		toasterList3.add(toaster4);
		toasterList3.add(toaster5);
		toasterList3.add(toaster6);
		
		// turnover, cost, profit, capital, marketShare, toasterList
		company1 = new Company(1.05, 1.07, 2.10, 100000.00, 50, toasterList1);
		company2 = new Company(1.05, 1.07, 2.10, 100000.00, 50, toasterList2);
		company3 = new Company(1.05, 1.07, 2.10, 100000.00, 50, toasterList3);

		master = (Master) UserFactory.createUser("Master", "ADMIN", "ADMIN");
		impl.startGame(3);
		Game.getInstance().getGroupList().get(0).setCompany(company1);
		Game.getInstance().getGroupList().get(1).setCompany(company2);
		Game.getInstance().getGroupList().get(2).setCompany(company3);
		Game.getInstance().getGroupList().get(0).getCompany().setToasterList(toasterList1);
		Game.getInstance().getGroupList().get(1).getCompany().setToasterList(toasterList2);
		Game.getInstance().getGroupList().get(2).getCompany().setToasterList(toasterList3);
	}

	@After
	public void tearDown() throws Exception
	{
		toaster1 = null;
		toaster2 = null;
		toaster3 = null;
		toasterList1 = null;
		toasterList2 = null;
		company1 = null;
		company2 = null;
		System.gc();
	}

	@Test
	public void testGenerateMarketResearchReport()
	{
		Game.getInstance().getGroupList().get(0).getCompany().setMarketResearchReportON(true);
		assertNull(Game.getInstance().getGroupList().get(0).getCompany().getMarketResearchReport());
		impl.simulate();
		assertNotNull(Game.getInstance());
		assertNotNull(Game.getInstance().getGroupList());
		assertNotNull(Game.getInstance().getGroupList().get(0).getCompany());
		assertFalse(Game.getInstance().getGroupList().get(0).getCompany().isMarketResearchReportON());
		assertNotNull(Game.getInstance().getGroupList().get(0).getCompany().getMarketResearchReport());
		System.out.println(Game.getInstance().getGroupList().get(0).getCompany().getMarketResearchReport().toString());
		System.out.println(Game.getInstance().getGroupList().get(0).getCompany().toString());
		System.out.println(Game.getInstance().getGroupList().get(0).getCompany().getToasterList().get(0).toString());

	}
}
