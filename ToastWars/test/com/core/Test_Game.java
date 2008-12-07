package com.core;

import java.util.ArrayList;
import static org.easymock.EasyMock.*;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.UserFactory;

public class Test_Game extends TestCase
{
	Master master;
	Game game;
	ArrayList<Toaster> meineToaster;
	@Before
	protected void setUp() throws Exception
	{
		master = (Master) UserFactory.createUser("Master","ADMIN","ADMIN");
		master.startGame(2);
		game = master.getCurrentGame();
		meineToaster = new ArrayList<Toaster>();
	}

	@After
	protected void tearDown() throws Exception
	{
		game = null;
	}

	@Test
	public void testSetCurrentRound()
	{
		double d = game.getRandom();
		assertNotNull(d);
		assertEquals(1, game.getCurrentRound());
		try
		{
			game.setCurrentRound(2);
			assertEquals(2, game.getCurrentRound());
			assertNotSame(d, game.getRandom());
			game.setCurrentRound(1);
			fail("Runde darf nicht eine niedrigere Nummer kriegen!");
		} catch (Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}
	@Test
	public void testSimulate()
	{
		Type myType = Type.TYPE1;
		Toaster meinToaster = new Toaster(8, 1000, 8155, 1.00, 2.37, 11.53, 9.58, 2.58, 500, myType);
		meineToaster.add(meinToaster);
		Company com = new Company("Meine erste",500,100,400,800,20,meineToaster);
		game.addCompany(com);
		game.setRandom(1.02); //wie in ppt angegeben
		game.simulate();
		assertNotSame(1.00,meinToaster.getIndex());
		assertEquals(1.28, meinToaster.getIndex()); //1.28 wird erwartet
	}
}
