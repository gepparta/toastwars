package com.core;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.datamodel.core.Type;

public class Test_Type extends TestCase {
	// Definition der Testobjekte
	private Type type1;
	private Type type2;
	private Type type3;

	// Setup
	@Before
	public void setUp() throws Exception {
		type1 = Type.TYPE1;
		type2 = Type.TYPE2;
		type3 = Type.TYPE3;
	}

	// TearDown
	@After
	public void tearDown() throws Exception {

		type1 = null;
		type2 = null;
		type3 = null;
		System.gc();
	}

	// Start der Test

	// Test der Get-Methoden
	@Test
	public void testGetDescription() {
		assertNotNull(type1.getDescription());
		assertEquals("Millenniums-Toaster", type1.getDescription());

	}

	@Test
	public void testGetRandom() {
		assertNotNull(type1.getRandom());
		assertEquals(0.05, type1.getRandom());

	}

	@Test
	public void testGetMarketVolume() {
		assertNotNull(type1.getMarketVolume());
		assertEquals(0, type1.getMarketVolume());

	}

	@Test
	public void testGetFixCost() {
		assertNotNull(type1.getFixCosts());
		assertEquals(10000.00, type1.getFixCosts());

	}

	@Test
	public void testGetVariableCost() {
		assertNotNull(type1.getVariableCosts());
		assertEquals(2.00, type1.getVariableCosts());

	}

	@Test
	public void testGetStepCost() {
		assertNotNull(type1.getStepCosts());
		assertEquals(1000.00, type1.getStepCosts());

	}

	@Test
	public void testGetCapacity() {
		assertNotNull(type1.getCapacity());
		assertEquals(4000, type1.getCapacity());

	}
	
	@Test
	public void testGetMinPrice() {
		assertNotNull(type1.getMinPrice());
		assertEquals(5, type1.getMinPrice());

	}
	
	@Test
	public void testGetMaxPrice() {
		assertNotNull(type1.getMaxPrice());
		assertEquals(20, type1.getMaxPrice());

	}
	
	@Test
	public void testGetTvInvestmentPlus() {
		assertNotNull(type1.getTvInvestmentPlus());
		assertEquals(20000.00, type1.getTvInvestmentPlus());

	}
	
	@Test
	public void testGetRadioInvestmentPlus() {
		assertNotNull(type1.getRadioInvestmentPlus());
		assertEquals(10000.00, type1.getRadioInvestmentPlus());

	}
	
	@Test
	public void testGetNewspaperInvestmentPlus() {
		assertNotNull(type1.getNewspaperInvestmentPlus());
		assertEquals(5000.00, type1.getNewspaperInvestmentPlus());

	}
	
	@Test
	public void testGetQualityInvestmentPlus() {
		assertNotNull(type1.getQualityInvestmentPlus());
		assertEquals(5000.00, type1.getQualityInvestmentPlus());

	}
	
	@Test
	public void testGetDesignInvestmentPlus() {
		assertNotNull(type1.getDesignInvestmentPlus());
		assertEquals(5000.00, type1.getDesignInvestmentPlus());

	}
	
	@Test
	public void testGetEcologyInvestmentPlus() {
		assertNotNull(type1.getEcologyInvestmentPlus());
		assertEquals(5000.00, type1.getEcologyInvestmentPlus());

	}
	


	// Test der Klassenwerte
	@Test
	public void testValues() {

		assertEquals("TYPE1", type1.name());
		assertEquals("TYPE2", type2.name());
		assertEquals("TYPE3", type3.name());

		assertEquals(0, type1.ordinal());
		assertEquals(1, type2.ordinal());
		assertEquals(2, type3.ordinal());

		assertEquals("Millenniums-Toaster", type1.getDescription());
		assertEquals("TIE-Toaster", type2.getDescription());
		assertEquals("Star-Toaster", type3.getDescription());

		assertEquals(0.05, type1.getRandom());
		assertEquals(0.08, type2.getRandom());
		assertEquals(0.1, type3.getRandom());

		assertEquals(0, type1.getMarketVolume());
		assertEquals(0, type2.getMarketVolume());
		assertEquals(0, type3.getMarketVolume());

		assertEquals(10000.00, type1.getFixCosts());
		assertEquals(20000.00, type2.getFixCosts());
		assertEquals(25000.00, type3.getFixCosts());

		assertEquals(2.00, type1.getVariableCosts());
		assertEquals(10.00, type2.getVariableCosts());
		assertEquals(40.00, type3.getVariableCosts());

		assertEquals(1000.00, type1.getStepCosts());
		assertEquals(5000.00, type2.getStepCosts());
		assertEquals(8000.00, type3.getStepCosts());

		assertEquals(4000, type1.getCapacity());
		assertEquals(2000, type2.getCapacity());
		assertEquals(500, type3.getCapacity());
		
		assertEquals(5, type1.getMinPrice());
		assertEquals(30, type2.getMinPrice());
		assertEquals(130, type3.getMinPrice());
		
		assertEquals(20, type1.getMaxPrice());
		assertEquals(60, type2.getMaxPrice());
		assertEquals(200, type3.getMaxPrice());
		
		
		assertEquals(20000.00, type1.getTvInvestmentPlus());
		assertEquals(30000.00, type2.getTvInvestmentPlus());
		assertEquals(40000.00, type3.getTvInvestmentPlus());
		
		assertEquals(10000.00, type1.getRadioInvestmentPlus());
		assertEquals(20000.00, type2.getRadioInvestmentPlus());
		assertEquals(30000.00, type3.getRadioInvestmentPlus());
		
		assertEquals(5000.00, type1.getNewspaperInvestmentPlus());
		assertEquals(10000.00, type2.getNewspaperInvestmentPlus());
		assertEquals(20000.00, type3.getNewspaperInvestmentPlus());
		
		assertEquals(5000.00, type1.getQualityInvestmentPlus());
		assertEquals(10000.00, type2.getQualityInvestmentPlus());
		assertEquals(15000.00, type3.getQualityInvestmentPlus());
		
		assertEquals(5000.00, type1.getDesignInvestmentPlus());
		assertEquals(10000.00, type2.getDesignInvestmentPlus());
		assertEquals(15000.00, type3.getDesignInvestmentPlus());
		
		assertEquals(5000.00, type1.getEcologyInvestmentPlus());
		assertEquals(10000.00, type2.getEcologyInvestmentPlus());
		assertEquals(15000.00, type3.getEcologyInvestmentPlus());
		

	}

	// Test der Set-Methoden
	@Test
	public void testSetDescription() {
		assertNotSame("Test", type1.getDescription());
		type1.setDescription("Test");
		assertEquals("Test", type1.getDescription());
	} // testSetDescription

	@Test
	public void testSetRandom() {
		assertNotSame(0.08, type1.getRandom());
		type1.setRandom(0.08);
		assertEquals(0.08, type1.getRandom());
	}

	@Test
	public void testSetMarketVolume() {
		assertNotSame(15000, type1.getMarketVolume());
		type1.setMarketVolume(15000);
		assertEquals(15000, type1.getMarketVolume());
	}

	@Test
	public void testSetFixCost() {
		assertNotSame(12000.00, type1.getFixCosts());
		type1.setFixCosts(12000.00);
		assertEquals(12000.00, type1.getFixCosts());

	}

	@Test
	public void testSetVariableCost() {
		assertNotSame(12.00, type1.getVariableCosts());
		type1.setVariableCosts(12.00);
		assertEquals(12.00, type1.getVariableCosts());
	}

	@Test
	public void testSetStepCost() {
		assertNotSame(3000.00, type1.getStepCosts());
		type1.setStepCosts(3000.00);
		assertEquals(3000.00, type1.getStepCosts());
	}

	@Test
	public void testSetCapacity() {
		assertNotSame(8000, type1.getCapacity());
		type1.setCapacity(8000);
		assertEquals(8000, type1.getCapacity());
	}
	
	@Test
	public void testSetMinPrice() {
		assertNotSame(10, type1.getMinPrice());
		type1.setMinPrice(10);
		assertEquals(10, type1.getMinPrice());
	}
	@Test
	public void testSetMaxPrice() {
		assertNotSame(100, type1.getMaxPrice());
		type1.setMaxPrice(100);
		assertEquals(100, type1.getMaxPrice());
	}
	@Test
	public void testSetTvInvestmentPlus() {
		assertNotSame(100.00, type1.getTvInvestmentPlus());
		type1.setTvInvestmentPlus(100.00);
		assertEquals(100.00, type1.getTvInvestmentPlus());
	}
	
	@Test
	public void testSetRadioInvestmentPlus() {
		assertNotSame(100.00, type1.getRadioInvestmentPlus());
		type1.setRadioInvestmentPlus(100.00);
		assertEquals(100.00, type1.getRadioInvestmentPlus());
	}
	@Test
	public void testSetNewspaperInvestmentPlus() {
		assertNotSame(100.00, type1.getNewspaperInvestmentPlus());
		type1.setNewspaperInvestmentPlus(100.00);
		assertEquals(100.00, type1.getNewspaperInvestmentPlus());
	}
	@Test
	public void testSetQualityInvestmentPlus() {
		assertNotSame(100.00, type1.getQualityInvestmentPlus());
		type1.setQualityInvestmentPlus(100.00);
		assertEquals(100.00, type1.getQualityInvestmentPlus());
	}

	@Test
	public void testSetDesignInvestmentPlus() {
		assertNotSame(100.00, type1.getDesignInvestmentPlus());
		type1.setDesignInvestmentPlus(100.00);
		assertEquals(100.00, type1.getDesignInvestmentPlus());
	}
	@Test
	public void testSetEcologyInvestmentPlus() {
		assertNotSame(100.00, type1.getEcologyInvestmentPlus());
		type1.setEcologyInvestmentPlus(100.00);
		assertEquals(100.00, type1.getEcologyInvestmentPlus());
	}
	
	@Test
	public void testsetMarketVolumeTT1() {
		assertNotSame(10920, type1.getMarketVolume());
		type1.setMarketVolumeTT1(3);
		assertEquals(10920, type1.getMarketVolume());
	}
	@Test
	public void testSetMarketVolumeTT2() {
		assertNotSame(5440, type2.getMarketVolume());
		type2.setMarketVolumeTT2(3);
		assertEquals(5440, type2.getMarketVolume());
	}
	@Test
	public void testSetMarketVolumeTT3() {
		assertNotSame(2000, type3.getMarketVolume());
		type3.setMarketVolumeTT3(3);
		assertEquals(2000, type3.getMarketVolume());
	}
	


}// Test Type
