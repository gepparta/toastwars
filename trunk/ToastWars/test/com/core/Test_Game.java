package com.core;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.ToastWarsServiceImpl;
import toastwars.server.dao.DAOGame;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.UserFactory;

public class Test_Game extends TestCase
{
	private Toaster meinToaster;
	private Company com;
	private Master master;
	private ArrayList<Toaster> meineToaster;
	private ToastWarsServiceImpl impl;

	@Before
	protected void setUp() throws Exception
	{
		impl = new ToastWarsServiceImpl();

		master = (Master) UserFactory.createUser("Master", "ADMIN", "ADMIN");
		impl.startGame(1);
		meineToaster = new ArrayList<Toaster>();
		meinToaster = new Toaster(10.00, 3.00, 20000.00, 5000.00, 10000.00, 3.00, 5000.00, 5000.00, 5000.00, 9.00, 1000.00, 1000.00, 1000.00, 10000, Type.TYPE1);
		meineToaster.add(meinToaster);
		com = new Company(500, 100, 400, 800, 20, meineToaster);
		Game.getGroupList().get(0).setCompany(com);
	}

	@After
	protected void tearDown() throws Exception
	{
		Game.getGroupList().clear();
		master = null;
		meineToaster = null;
		meinToaster = null;
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
		try
		{
			Game.getInstance().setCurrentRound(2);
			Game.getInstance().setCurrentRound(1);
			fail("Runde darf nicht eine niedrigere Nummer kriegen!");
		} catch (Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Test
	public void testGetUserAmount()
	{
		assertNotNull(Game.getInstance().getUserAmount());
		assertEquals(1, Game.getInstance().getUserAmount());
	}

	@Test
	public void testSetUserAmount()
	{
		assertNotSame(Game.getInstance().getUserAmount(), 5);
		Game.getInstance().setUserAmount(5);
		assertEquals(Game.getInstance().getUserAmount(), 5);
	}

	@Test
	public void testGetCompanyList()
	{

	}

	@Test
	public void testSetCompanyList()
	{
	}

	@Test
	public void testSimulate()
	{
		try
		{
			master.simulate();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		assertEquals(9.0, meinToaster.getIndex());
	}

	@Test
	public void testCalculateIndexSums()
	{
		// Index Werte der beiden Toaster wie in ppt für Runde 1 angegeben
		Toaster toaster1 = new Toaster(10.00, 3.00, 20000.00, 5000.00, 10000.00, 3.00, 5000.00, 5000.00, 5000.00, 9.00, 1000.00, 1000.00, 1000.00, 10000, Type.TYPE1);
		Toaster toaster2 = new Toaster(10.00, 3.00, 20000.00, 5000.00, 10000.00, 3.00, 5000.00, 5000.00, 5000.00, 9.00, 1000.00, 1000.00, 1000.00, 10000, Type.TYPE2);
		Toaster toaster3 = new Toaster(10.00, 3.00, 20000.00, 5000.00, 10000.00, 3.00, 5000.00, 5000.00, 5000.00, 9.00, 1000.00, 1000.00, 1000.00, 10000, Type.TYPE3);
		ArrayList<Toaster> toasterList1 = new ArrayList<Toaster>();
		toasterList1.add(toaster1);
		toasterList1.add(toaster2);
		toasterList1.add(toaster3);
		Game.getGroupList().get(0).getCompany().setToasterList(toasterList1);
		assertEquals(1, Game.getGroupList().size());

		double[] indexSums = Game.getInstance().calculateIndexSums();
		assertEquals(9.00, indexSums[0]);
		assertEquals(9.00, indexSums[1]);
		assertEquals(9.0, indexSums[2]);
	}

	@Test
	public void testToString()
	{
		System.out.println(Game.getInstance().toString());
	}
}
