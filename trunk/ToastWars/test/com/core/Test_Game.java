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
	private Master master;
	private Game game;
	private ArrayList<Toaster> meineToaster;
	private Type myType1;
	private Toaster t1;
	private ArrayList<Toaster> toasterList;
	private Company c1;
	private ArrayList <Company> companyList; 
	
	@Before
	protected void setUp() throws Exception
	{
		master = (Master) UserFactory.createUser("Master","ADMIN","ADMIN");
		master.startGame(2);
		game = master.getCurrentGame();
		meineToaster = new ArrayList<Toaster>();

/* um später mal das get/set CompanyList zu testen
		myType1 = Type.TYPE1;
		t1 = new Toaster(8, 1000, 8155, 1.00, 2.37,  9.58, 2.58, 500, myType1);
		toasterList = new ArrayList<Toaster>();
		toasterList.add(t1);
		c1 = new Company("Test1", 1.05, 1.07, 2.10, 2.13, 50, toasterList);
		companyList=new ArrayList<Company>();
		companyList.add(c1);
*/
	}

	@After
	protected void tearDown() throws Exception
	{
		master = null;
		game = null;
		meineToaster = null;
	}
 
	@Test
	public void testGetCurrentRound(){
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
	public void testGetUserAmount(){
		assertNotNull(game.getUserAmount());
		assertEquals(2, game.getUserAmount());
	}
	@Test
	public void testSetUserAmount() {
		assertNotSame(game.getUserAmount(),5);
		game.setUserAmount(5);
		assertEquals(game.getUserAmount(), 5);
	}
	@Test
	public void testGetCompanyList(){

	}
	@Test
	public void testSetCompanyList() {

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

		
	}
}
