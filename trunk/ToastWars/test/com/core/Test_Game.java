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
		master = null;
		game = null;
		meineToaster = null;
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
	public void testSimulate()
	{
		Type myType = Type.TYPE1;
		Toaster meinToaster = new Toaster(8, 1000, 8155, 1.00, 2.37, 9.58, 2.58, 500, myType);
		meineToaster.add(meinToaster);
		Company com = new Company("Meine erste",500,100,400,800,20,meineToaster);
		game.addCompany(com);
		meinToaster.getType().setRandom(1.02); //wie in ppt angegeben
		game.simulate();
		assertEquals(1.28, meinToaster.getIndex()); //1.28 wird erwartet
	}
	
	@Test
	public void testCalculateIndexSums(){
		Type myType1 = Type.TYPE1;
		Type myType2 = Type.TYPE2;

		Toaster T1 = new Toaster(8, 1000, 8155, 1.00, 2.37,  9.58, 2.58, 500, myType1);
		Toaster T2 = new Toaster(8, 1000, 8155, 1.00, 2.37,  9.58, 2.58, 500, myType2);
		
		ArrayList<Toaster> A1 = new ArrayList<Toaster>();
		A1.add(T1);
		A1.add(T2);
		
		Company C1 = new Company("Test1", 1.05, 1.07, 2.10, 2.13, 50, A1);
		ArrayList<Company> CL1 = new ArrayList<Company>();
		CL1.add(C1);
		Game G1 = new Game(1);
		G1.setCompanyList(CL1);
		double [] d = new double[3];
		d[0]=2;
		d[1]=0;
		d[2]=0;
		
		
		assertEquals(G1.calculateIndexSums(),d);
		
	}
}
