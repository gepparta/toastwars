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

public class Test_Game extends TestCase
{
	Game game;
	
	@Before
	protected void setUp() throws Exception
	{
		game = Game.getInstance(2);
	}

	@After
	protected void tearDown() throws Exception
	{
		game= null;
	}

	@Test
	public void testSetCurrentRound() throws Exception
	{
		assertEquals(1,game.getCurrentRound());
		game.setCurrentRound(2);
		assertEquals(2,game.getCurrentRound());
	}
}
