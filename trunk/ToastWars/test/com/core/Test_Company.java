package com.core;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toastwars.server.datamodel.core.Company;


public class Test_Company extends TestCase {

	private Company C1;

	@Before
	public void setUp() throws Exception {

		C1 = new Company("Test1", 2.54, 3.44, 5.88);
	}

	@After
	public void tearDown() throws Exception {

		C1 = null;
	}

	@Test	
	public void testGetDescription() {
		
		String t = C1.getDescription();
		assertNotNull(t);
		assertEquals(t,"Test1");	
	}
@Test	
	public void testSetName() {
		
		assertNotSame(C1.getDescription(),"hallo");
		C1.setDescription("hallo");
		assertEquals("hallo", C1.getDescription());
}
	
	@Test
	public void testGetTurnover() {
		assertNotNull(C1.getTurnover());
		assertEquals(C1.getTurnover(), 2.54);
	}

	@Test
	public void testSetTurnover() {
		assertNotSame(C1.getTurnover(), 9.09);
		C1.setTurnover(9.09);
		assertEquals(C1.getTurnover(), 9.09);
	}

	@Test
	public void testgetMarketShare() {
		assertNotNull(C1.getMarketShare());
		assertEquals(C1.getMarketShare(), 3.44);
	}

	@Test
	public void testSetMarketShare() {
		assertNotSame(C1.getMarketShare(), 9.09);
		C1.setMarketShare(9.09);
		assertEquals(C1.getMarketShare(), 9.09);
	}

	@Test
	public void testGetAsset() {
		assertNotNull(C1.getAsset());
		assertEquals(C1.getAsset(), 5.88);
	}

	@Test
	public void testSetAsset() {
		assertNotSame(C1.getAsset(), 9.09);
		C1.setAsset(9.09);
		assertEquals(C1.getAsset(), 9.09);
	}
	
}
