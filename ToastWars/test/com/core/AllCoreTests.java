package com.core;

import toastwars.server.datamodel.core.Game;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllCoreTests extends TestCase
{
	public static Test suite()
	{
		TestSuite suite = new TestSuite("Alle Core Tests");
		suite.addTestSuite(Test_Type.class);
		Game.destroyGame();
		suite.addTestSuite(Test_Stock.class);
		Game.destroyGame();
		suite.addTestSuite(Test_Toaster.class);
		Game.destroyGame();
		suite.addTestSuite(Test_Company.class);
		Game.destroyGame();


		
		suite.addTestSuite(Test_MarketResearchReport.class);
		Game.destroyGame();
		suite.addTestSuite(New_Game_Test.class);
		return suite;
	} //TestSuite
}//AllCoreTests

