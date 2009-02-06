package com.core;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.ToastWarsServiceImpl;
import toastwars.server.dao.DAOGame;
import toastwars.server.dao.DAOUser;
import toastwars.server.dao.DBConnection;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Stock;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;
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
	
	private Group group1;
	private Group group2;
	private Group group3;
	
	private Stock stock1;
	private Stock stock2;
	private Stock stock3;
	
	private Master master;
	
	private ToastWarsServiceImpl impl;

	@Before
	public void setUp() throws Exception
	{
		impl = new ToastWarsServiceImpl();
//		Connection con = DBConnection.getInstance().connectToDB();
//		DAOGame.resetGame(con);
//		// close DB connection
//		DBConnection.getInstance().closeConnectionToDB(con);

// 	     price,  index,  turnover,  cost,
//		 profit,  marketShare,  type,  marketing,
//		 tvInvestment,  newspaperInvestment,
//		 radioInvestment,  tvInvestmentKum,
//		 newspaperInvestmentKum,  radioInvestmentKum,
//		 research,  qualityInvestment,  designInvestment,
//		 ecologyInvestment,  qualityInvestmentKum,
//		 designInvestmentKum,  ecologyInvestmentKum, production
		
		toaster1 = new Toaster(10.0, 9.00, 100000.00,50000.00,1000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		toaster2 = new Toaster(40.00, 9.00, 100000.00,50000.00,1000.00,6000,Type.TYPE2,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		toaster3 = new Toaster(150.00, 9.00, 100000.00,50000.00,1000.00,2500,Type.TYPE3,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);

		toaster4 = new Toaster(12.0, 9.00, 100000.00,50000.00,1000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		toaster5 = new Toaster(42.00, 9.00, 100000.00,50000.00,1000.00,6000,Type.TYPE2,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		toaster6 = new Toaster(152.00, 9.00, 100000.00,50000.00,1000.00,2500,Type.TYPE3,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);

				
		toasterList1 = new ArrayList<Toaster>();
		toasterList1.add(toaster1);
		toasterList2 = new ArrayList<Toaster>();
		toasterList2.add(toaster2);
		toasterList2.add(toaster3);
		
		toasterList3 = new ArrayList<Toaster>();
		toasterList3.add(toaster4);
		toasterList3.add(toaster5);
		toasterList3.add(toaster6);
		
		stock1 = new Stock(0,0,0,0.00);
		stock2 = new Stock(0,0,0,0.00);
		stock3 = new Stock(0,0,0,0.00);
		
		
//		 turnover,  cost,  profit,  capital,  marketShare,  stock,  toasterList

		company1 = new Company(100000.00,20000.00, 60000.00,100000.00, 10000,stock1,toasterList1);
		company2 = new Company(100000.00, 30000.00, 60000.00,100000.00,10000,stock2,toasterList2);
		company3 = new Company(100000.00, 30000.00, 60000.00,100000.00,10000,stock3,toasterList3);
		
		master = (Master) UserFactory.createUser("Master", "ADMIN", "ADMIN");
		group1 = (Group)UserFactory.createUser("Group", "group1","group1" );
		group2 = (Group)UserFactory.createUser("Group", "group2","group2" );
		group3 = (Group)UserFactory.createUser("Group", "group3","group3" );


		Game.getInstance(3);
		Game.getInstance().addGroup(group1);
		Game.getInstance().addGroup(group2);
		Game.getInstance().addGroup(group3);
		
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
		
		master = null;
		Game.destroyGame();
	}

	@Test
	public void testGenerateMarketResearchReport()
	{
		Game.getInstance().getGroupList().get(0).getCompany().setMarketResearchReportON(true);
		assertEquals(new ArrayList<ArrayList<List<String>>>(), Game.getInstance().getGroupList().get(0).getCompany().getReportListe());
		impl.simulate();

		assertFalse(Game.getInstance().getGroupList().get(0).getCompany().isMarketResearchReportON());
		assertNotNull(Game.getInstance().getGroupList().get(0).getCompany().getReportListe());
	}
}
