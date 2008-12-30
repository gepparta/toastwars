package com.core;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	private Game game;
	private ArrayList<Toaster> meineToaster;
	private Group group1;

	@Before
	protected void setUp() throws Exception
	{
		master = (Master) UserFactory.createUser("Master", "ADMIN", "ADMIN");
		master.startGame(1);
		game = master.getCurrentGame();
		meineToaster = new ArrayList<Toaster>();
		meinToaster = new Toaster(10, 10000.00, 12923.00, 1.00, 50000.00, 30000.00, 20000.00, 5000, Type.TYPE1);
		meineToaster.add(meinToaster);
		com = new Company("Meine erste", 500, 100, 400, 800, 20, meineToaster);
		game.getGroupList().get(0).setCompany(com);
	}

	@After
	protected void tearDown() throws Exception
	{
		master = null;
		game = null;
		meineToaster = null;
		meinToaster = null;
	}

	@Test
	public void testGetCurrentRound()
	{
		assertNotNull(game.getCurrentRound());
		assertEquals(1, game.getCurrentRound());

	}

	@Test
	public void testSetCurrentRound()
	{

		assertEquals(1, game.getCurrentRound());
		try
		{
			game.setCurrentRound(2);
			game.setCurrentRound(1);
			fail("Runde darf nicht eine niedrigere Nummer kriegen!");
		} catch (Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Test
	public void testGetUserAmount()
	{
		assertNotNull(game.getUserAmount());
		assertEquals(1, game.getUserAmount());
	}

	@Test
	public void testSetUserAmount()
	{
		assertNotSame(game.getUserAmount(), 5);
		game.setUserAmount(5);
		assertEquals(game.getUserAmount(), 5);
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
		game.simulateWithOutRandom();
		assertEquals(1.0, meinToaster.getIndex());
	}

	@Test
	public void testCalculateIndexSums()
	{
		Type myType1 = Type.TYPE1;
		Type myType2 = Type.TYPE2;
		// Index Werte der beiden Toaster wie in ppt für Runde 1 angegeben
		Toaster toaster1 = new Toaster(8, 1000, 8155, 1.28, 2.37, 9.58, 2.58, 500, myType1);
		Toaster toaster2 = new Toaster(8, 1000, 8155, 2.57, 2.37, 9.58, 2.58, 500, myType1);

		ArrayList<Toaster> toasterList1 = new ArrayList<Toaster>();
		toasterList1.add(toaster1);
		toasterList1.add(toaster2);
		game.getGroupList().get(0).getCompany().setToasterList(toasterList1);

		double[] indexSums = game.calculateIndexSums();
		assertEquals(indexSums[0], 3.85);
		assertEquals(indexSums[1], 0.0);
		assertEquals(indexSums[2], 0.0);
	}
	@Test
	public void testToString()
	{
		System.out.println(game.toString());
	}
}
