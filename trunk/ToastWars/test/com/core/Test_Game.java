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
	Game gameMock = createMock(Game.class);
	
	@Before
	protected void setUp() throws Exception
	{

	}

	@After
	protected void tearDown() throws Exception
	{

	}

}
