package com.core;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;

public class Test_Toaster extends TestCase
{

	private Toaster t1;
	private Toaster t2;
	private Toaster t3;

	@Before
	protected void setUp() throws Exception
	{		
		//  price,  index, turnover, cost, profit, marketShare,type,  marketing, tvInvestment,  newspaperInvestment,radioInvestment, tvInvestmentKum,
		//	newspaperInvestmentKum, radioInvestmentKum, research,  qualityInvestment, designInvestment, ecologyInvestment, qualityInvestmentKum, designInvestmentKum,  ecologyInvestmentKum, production
			
		t1 = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		t2 = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE2,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		t3 = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE3,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		}
		



	@After
	protected void tearDown() throws Exception
	{
		t1 = null;
		t2 = null;
		t3 = null;
		System.gc();
	}

	// ////////////GETTER//////////////
	@Test
	public void testGetPrice()
	{
		assertNotNull(t1.getPrice());
		assertEquals(10.0, t1.getPrice());
	}

	@Test
	public void testGetMarketing()

	{
		assertNotNull(t1.getMarketing());
		assertEquals(t1.getMarketing(), 3.00);
	}

	@Test
	public void testGetTvInvestment()

	{
		assertNotNull(t1.getTvInvestment());
		assertEquals(t1.getTvInvestment(), 1.0);
	}

	@Test
	public void testGetNewsPaperInvestment()

	{
		assertNotNull(t1.getNewspaperInvestment());
		assertEquals(t1.getNewspaperInvestment(), 1.0);
	}

	@Test
	public void testRadioInvestment()

	{
		assertNotNull(t1.getRadioInvestment());
		assertEquals(t1.getRadioInvestment(), 1.0);
	}

	@Test
	public void testGetResearch()
	{
		assertNotNull(t1.getResearch());
		assertEquals(t1.getResearch(), 3.0);
	}

	@Test
	public void testGetQuality()
	{
		assertNotNull(t1.getQualityInvestment());
		assertEquals(t1.getQualityInvestment(), 1.0);
	}

	@Test
	public void testGetDesign()
	{
		assertNotNull(t1.getDesignInvestment());
		assertEquals(t1.getDesignInvestment(),1.0);
	}

	@Test
	public void testGetEfficiency()
	{
		assertNotNull(t1.getEcologyInvestment());
		assertEquals(t1.getEcologyInvestment(),1.0);
	}

	@Test
	public void testGetIndex()
	{
		assertNotNull(t1.getIndex());
		assertEquals(t1.getIndex(), 9.00);
	}

	@Test
	public void testGetTurnover()
	{
		assertNotNull(t1.getTurnover());
		assertEquals(t1.getTurnover(),100000.0);
	}

	@Test
	public void testGetCost()
	{
		assertNotNull(t1.getCost());
		assertEquals(t1.getCost(),50000.0);
	}

	@Test
	public void testGetProfit()
	{
		assertNotNull(t1.getProfit());
		assertEquals(t1.getProfit(),50000.00);
	}

	@Test
	public void testGetMarketShare()
	{
		assertNotNull(t1.getMarketShare());
		assertEquals(t1.getMarketShare(),10000);
	}

	@Test
	public void testGetType()
	{
		assertNotNull(t1.getType());
		assertEquals(t1.getType(), Type.TYPE1);
	}
	
	@Test
	public void testGetTvInvestmentKum()
	{
		assertNotNull(t1.getTvInvestmentKum());
		assertEquals(0.00, t1.getTvInvestmentKum());
	}
	
	@Test
	public void testGetNewspaperInvestmentKum()
	{
		assertNotNull(t1.getNewspaperInvestmentKum());
		assertEquals(0.00, t1.getNewspaperInvestmentKum());
	}

	
	@Test
	public void testGetRadioInvestmentKum()
	{
		assertNotNull(t1.getRadioInvestmentKum());
		assertEquals(0.00, t1.getRadioInvestmentKum());
	}

	
	@Test
	public void testGetQualityInvestmentKum()
	{
		assertNotNull(t1.getQualityInvestmentKum());
		assertEquals(0.00, t1.getQualityInvestmentKum());
	}

	
	@Test
	public void testGetDesignInvestmentKum()
	{
		assertNotNull(t1.getDesignInvestmentKum());
		assertEquals(0.00, t1.getDesignInvestmentKum());
	}

	
	@Test
	public void testGetEcologyInvestmentKum()
	{
		assertNotNull(t1.getEcologyInvestmentKum());
		assertEquals(0.00, t1.getEcologyInvestmentKum());
	}
	
	@Test
	public void testGetProduction()
	{
		assertNotNull(t1.getProduction());
		assertEquals(1000, t1.getProduction());
	}


	// //////////SETTER/////////////////////
	@Test
	public void testSetPrice()
	{
		assertNotSame(15.00, t1.getPrice());
		try
		{
			t1.setPrice(15.00);
		} catch (Exception e)
		{
			System.err.print(e);
		}
		assertEquals(15.00, t1.getPrice());
	}

	@Test
	public void testSetMarketing()
	{
		assertNotSame(6.00, t1.getMarketing());
		t1.setMarketing(6.00);
		assertEquals(6.00, t1.getMarketing());
	}

	@Test
	public void testSetTvInvestment()
	{
		assertNotSame(21000.00, t1.getTvInvestment());
		try
		{
			t1.setTvInvestment(21000.00);
		} catch (Exception e)
		{
			System.err.print(e);
		}
		assertEquals(21000.00, t1.getTvInvestment());
	}

	@Test
	public void testSetNewsPaperInvestment()
	{
		assertNotSame(5000.00, t1.getNewspaperInvestment());
		try
		{
			t1.setNewspaperInvestment(5000.00);
		} catch (Exception e)
		{
			System.err.print(e);
		}
		assertEquals(5000.00, t1.getNewspaperInvestment());
	}

	@Test
	public void testSetRadioInvestment()
	{
		assertNotSame(12000.00, t1.getRadioInvestment());
		try
		{
			t1.setRadioInvestment(12000.00);
		} catch (Exception e)
		{
			System.err.print(e);
		}
		assertEquals(12000.00, t1.getRadioInvestment());
	}

	@Test
	public void testSetResearch()
	{
		assertNotSame(1000.0, t1.getResearch());
		t1.setResearch(1000);
		assertEquals(1000.0, t1.getResearch());
	}

	@Test
	public void testSetIndex()
	{
		assertNotSame(2.00, t1.getIndex());
		t1.setIndex(2.00);
		assertEquals(2.00, t1.getIndex());
	}

	@Test
	public void testSetTurnover()
	{
		assertNotSame(6.17, t1.getTurnover());
		t1.setTurnover(6.17);
		assertEquals(6.17, t1.getTurnover());
	}

	@Test
	public void testSetCost()
	{
		assertNotSame(11.11, t1.getCost());
		t1.setCost(11.11);
		assertEquals(11.11, t1.getCost());
	}

	@Test
	public void testSetProfit()
	{
		assertNotSame(1.00, t1.getProfit());
		t1.setProfit(1.00);
		assertEquals(1.00, t1.getProfit());
	}

	@Test
	public void testSetMarketShare()
	{
		assertNotSame(205, t1.getMarketShare());
		t1.setMarketShare(205);
		assertEquals(205, t1.getMarketShare());
	}

	@Test
	public void testSetType()
	{
		assertNotSame(Type.TYPE3, t1.getType());
		t1.setType(Type.TYPE3);
		assertEquals(Type.TYPE3, t1.getType());
	}
	
	@Test
	public void testSetInvestmentQuality()
	{
		assertNotSame(10.00, t1.getQualityInvestment());
		t1.setQualityInvestment(10.00);
		assertEquals(10.00, t1.getQualityInvestment());
	}
	
	@Test
	public void testSetInvestmentDesign()
	{
		assertNotSame(10.00, t1.getDesignInvestment());
		t1.setDesignInvestment(10.00);
		assertEquals(10.00, t1.getDesignInvestment());
	}
	
	@Test
	public void testSetInvestmentEcology()
	{
		assertNotSame(10.00, t1.getEcologyInvestment());
		t1.setEcologyInvestment(10.00);
		assertEquals(10.00, t1.getEcologyInvestment());
	}
	
	@Test
	public void testSetQualityInvestmentKum()
	{
		assertNotSame(1.00, t1.getQualityInvestmentKum());
		t1.setQualityInvestmentKum();
		assertEquals(1.00, t1.getQualityInvestmentKum());
	}
	
	@Test
	public void testSetDesignInvestmentKum()
	{
		assertNotSame(1.00, t1.getDesignInvestmentKum());
		t1.setDesignInvestmentKum();
		assertEquals(1.00, t1.getDesignInvestmentKum());
	}
	
	@Test
	public void testSetEcologyInvestmentKum()
	{
		assertNotSame(1.00, t1.getEcologyInvestmentKum());
		t1.setEcologyInvestmentKum();
		assertEquals(1.00, t1.getEcologyInvestmentKum());
	}
	
	
	@Test
	public void testSetTvInvestmentKum()
	{
		assertNotSame(1.00, t1.getTvInvestmentKum());
		t1.setTvInvestmentKum();
		assertEquals(1.00, t1.getTvInvestmentKum());
	}
	
	@Test
	public void testSetNewspaperInvestmentKum()
	{
		assertNotSame(1.00, t1.getNewspaperInvestmentKum());
		t1.setNewspaperInvestmentKum();
		assertEquals(1.00, t1.getNewspaperInvestmentKum());
	}
	
	@Test
	public void testSetRadioInvestmentKum()
	{
		assertNotSame(1.00, t1.getRadioInvestmentKum());
		t1.setRadioInvestmentKum();
		assertEquals(1.00, t1.getRadioInvestmentKum());
	}
	
	@Test
	public void testSetProduction()
	{
		assertNotSame(10, t1.getProduction());
		t1.setProduction(10);
		assertEquals(10, t1.getProduction());
	}
	


	// //Calculation///////////////////////////
	@Test
	public void testCalculateMarketShares()
	{
		//Typ 1
		Type.TYPE1.setMarketVolume(10000);
		assertNotSame(5000, t1.getMarketShare());
		t1.calculateMarketShare(18.00);
		assertEquals(5000, t1.getMarketShare());
		
		//Typ 2
		Type.TYPE2.setMarketVolume(10000);
		assertNotSame(5000, t2.getMarketShare());
		t2.calculateMarketShare(18.00);
		assertEquals(5000, t2.getMarketShare());
		
		//Typ 3
		Type.TYPE3.setMarketVolume(10000);
		assertNotSame(5000, t3.getMarketShare());
		t3.calculateMarketShare(18.00);
		assertEquals(5000, t3.getMarketShare());
	}

	@Test
	public void testCalculateTurnover()
	{
		//Typ 1
		t1.setTurnover(1000);
		assertNotSame(100000.00, t1.getTurnover());
		t1.calculateTurnover();
		assertEquals(100000.00, t1.getTurnover());
		//Typ 2
		t2.setTurnover(1000);
		assertNotSame(100000.00, t2.getTurnover());
		t2.calculateTurnover();
		assertEquals(100000.00, t2.getTurnover());
		//Typ 3
		t3.setTurnover(1000);
		assertNotSame(100000.00, t3.getTurnover());
		t3.calculateTurnover();
		assertEquals(100000.00, t3.getTurnover());
	}

	@Test
	public void testCalculateCost()
	{
		//Typ 1
		assertNotSame(13000.00, t1.getCost());

		t1.setMarketShare(1000);
		t1.calculateCost();
		assertEquals(13000.00, t1.getCost());
		
		//Typ 2
		assertNotSame(35000.00, t2.getCost());
		t2.setMarketShare(1000);
		t2.calculateCost();
		assertEquals(35000.00, t2.getCost());
		
		//Typ 3
		assertNotSame(73000.00, t3.getCost());
		t3.setMarketShare(1000);
		t3.calculateCost();
		assertEquals(81000.00, t3.getCost());
	}

	@Test
	public void testCalculateProfit()
	{
		//Typ 1
		assertNotSame(50000.0, t1.getProfit());
		t1.calculateProfit();
		assertEquals(50000.0, t1.getProfit());
		
		//Typ 2
		assertNotSame(50000.0, t2.getProfit());
		t2.calculateProfit();
		assertEquals(50000.0, t2.getProfit());
		
		//Typ 3
		assertNotSame(50000.0, t3.getProfit());
		t3.calculateProfit();
		assertEquals(50000.0, t3.getProfit());
	}

	@Test
	public void testCalculateResearch()
	{
		//Typ 1
		assertEquals(3.00, t1.calculateResearch());
		
		//Typ 2
		assertEquals(3.00, t2.calculateResearch());
		
		//Typ 3
		assertEquals(3.00, t3.calculateResearch());

	}

	@Test
	public void testCalculateMarketing()
	{	//Typ 1
		assertEquals(3.0, t1.calculateMarketing());
		
		//Typ 2
		assertEquals(3.0, t2.calculateMarketing());
		
		//Typ 3
		assertEquals(3.0, t3.calculateMarketing());
	}

	@Test
	public void testCalculateIndex()
	{
		//Typ 1
		t1.setIndex(1.00);
		assertNotSame(9.00, t1.getIndex());
		t1.calculateIndex();
		assertEquals(9.00, t1.getIndex());
		
		//Typ 2
		t2.setIndex(1.00);
		try{t2.setPrice(40.00);}
		catch(Exception e){System.err.println(e);}
		assertNotSame(9.00, t2.getIndex());
		t2.calculateIndex();
		assertEquals(9.00, t2.getIndex());
		
		//Typ 3
		t3.setIndex(1.00);
		try{t3.setPrice(150.00);}
		catch(Exception e){System.err.println(e);}
		assertNotSame(9.00, t3.getIndex());
		t3.calculateIndex();
		assertEquals(9.00, t3.getIndex());
		
		
	}
	
	public void testResetUserInput()
	{
	
		
		
		assertEquals(1.00,t1.getTvInvestment());
		assertEquals(1.00,t1.getNewspaperInvestment());
		assertEquals(1.00,t1.getRadioInvestment());
		assertEquals(1.00,t1.getQualityInvestment());
		assertEquals(1.00,t1.getDesignInvestment());
		assertEquals(1.00,t1.getEcologyInvestment());
		assertEquals(1000,t1.getProduction());
		
		t1.resetUserInput();
		
		assertEquals(0.00,t1.getTvInvestment());
		assertEquals(0.00,t1.getNewspaperInvestment());
		assertEquals(0.00,t1.getRadioInvestment());
		assertEquals(0.00,t1.getQualityInvestment());
		assertEquals(0.00,t1.getDesignInvestment());
		assertEquals(0.00,t1.getEcologyInvestment());
		assertEquals(0,t1.getProduction());
		

		
	}
}
