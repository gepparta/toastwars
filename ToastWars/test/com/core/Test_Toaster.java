package com.core;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;

public class Test_Toaster extends TestCase
{

	private Toaster toaster1;
	private Toaster toaster2;
	private Toaster toaster3;

	@Before
	protected void setUp() throws Exception
	{		
		//  price,  index, turnover, cost, profit, marketShare,type,  marketing, tvInvestment,  newspaperInvestment,radioInvestment, tvInvestmentKum,
		//	newspaperInvestmentKum, radioInvestmentKum, research,  qualityInvestment, designInvestment, ecologyInvestment, qualityInvestmentKum, designInvestmentKum,  ecologyInvestmentKum, production
			
		toaster1 = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE1,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		toaster2 = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE2,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		toaster3 = new Toaster(10.0, 9.00, 100000.00,50000.00,50000.00,10000,Type.TYPE3,3.00,1.00,1.00,1.00,0.00,0.00,0.00,3.00,1.00,1.00,1.00,0.00,0.00,0.00, 1000);
		}
		



	@After
	protected void tearDown() throws Exception
	{
		toaster1 = null;
		toaster2 = null;
		toaster3 = null;
		System.gc();
	}

	// ////////////GETTER//////////////
	@Test
	public void testGetPrice()
	{
		assertNotNull(toaster1.getPrice());
		assertEquals(10.0, toaster1.getPrice());
	}

	@Test
	public void testGetMarketing()

	{
		assertNotNull(toaster1.getMarketing());
		assertEquals(toaster1.getMarketing(), 3.00);
	}

	@Test
	public void testGetTvInvestment()

	{
		assertNotNull(toaster1.getTvInvestment());
		assertEquals(toaster1.getTvInvestment(), 1.0);
	}

	@Test
	public void testGetNewsPaperInvestment()

	{
		assertNotNull(toaster1.getNewspaperInvestment());
		assertEquals(toaster1.getNewspaperInvestment(), 1.0);
	}

	@Test
	public void testRadioInvestment()

	{
		assertNotNull(toaster1.getRadioInvestment());
		assertEquals(toaster1.getRadioInvestment(), 1.0);
	}

	@Test
	public void testGetResearch()
	{
		assertNotNull(toaster1.getResearch());
		assertEquals(toaster1.getResearch(), 3.0);
	}

	@Test
	public void testGetQuality()
	{
		assertNotNull(toaster1.getQualityInvestment());
		assertEquals(toaster1.getQualityInvestment(), 1.0);
	}

	@Test
	public void testGetDesign()
	{
		assertNotNull(toaster1.getDesignInvestment());
		assertEquals(toaster1.getDesignInvestment(),1.0);
	}

	@Test
	public void testGetEfficiency()
	{
		assertNotNull(toaster1.getEcologyInvestment());
		assertEquals(toaster1.getEcologyInvestment(),1.0);
	}

	@Test
	public void testGetIndex()
	{
		assertNotNull(toaster1.getIndex());
		assertEquals(toaster1.getIndex(), 9.00);
	}

	@Test
	public void testGetTurnover()
	{
		assertNotNull(toaster1.getTurnover());
		assertEquals(toaster1.getTurnover(),100000.0);
	}

	@Test
	public void testGetCost()
	{
		assertNotNull(toaster1.getCost());
		assertEquals(toaster1.getCost(),50000.0);
	}

	@Test
	public void testGetProfit()
	{
		assertNotNull(toaster1.getProfit());
		assertEquals(toaster1.getProfit(),50000.00);
	}

	@Test
	public void testGetMarketShare()
	{
		assertNotNull(toaster1.getMarketShare());
		assertEquals(toaster1.getMarketShare(),10000);
	}

	@Test
	public void testGetType()
	{
		assertNotNull(toaster1.getType());
		assertEquals(toaster1.getType(), Type.TYPE1);
	}
	
	@Test
	public void testGetTvInvestmentKum()
	{
		assertNotNull(toaster1.getTvInvestmentKum());
		assertEquals(0.00, toaster1.getTvInvestmentKum());
	}
	
	@Test
	public void testGetNewspaperInvestmentKum()
	{
		assertNotNull(toaster1.getNewspaperInvestmentKum());
		assertEquals(0.00, toaster1.getNewspaperInvestmentKum());
	}

	
	@Test
	public void testGetRadioInvestmentKum()
	{
		assertNotNull(toaster1.getRadioInvestmentKum());
		assertEquals(0.00, toaster1.getRadioInvestmentKum());
	}

	
	@Test
	public void testGetQualityInvestmentKum()
	{
		assertNotNull(toaster1.getQualityInvestmentKum());
		assertEquals(0.00, toaster1.getQualityInvestmentKum());
	}

	
	@Test
	public void testGetDesignInvestmentKum()
	{
		assertNotNull(toaster1.getDesignInvestmentKum());
		assertEquals(0.00, toaster1.getDesignInvestmentKum());
	}

	
	@Test
	public void testGetEcologyInvestmentKum()
	{
		assertNotNull(toaster1.getEcologyInvestmentKum());
		assertEquals(0.00, toaster1.getEcologyInvestmentKum());
	}
	
	@Test
	public void testGetProduction()
	{
		assertNotNull(toaster1.getProduction());
		assertEquals(1000, toaster1.getProduction());
	}


	// //////////SETTER/////////////////////
	@Test
	public void testSetPrice()
	{
		assertNotSame(15.00, toaster1.getPrice());
		try
		{
			toaster1.setPrice(15.00);
		} catch (Exception e)
		{
			System.err.print(e);
		}
		assertEquals(15.00, toaster1.getPrice());
	}

	@Test
	public void testSetMarketing()
	{
		assertNotSame(6.00, toaster1.getMarketing());
		toaster1.setMarketing(6.00);
		assertEquals(6.00, toaster1.getMarketing());
	}

	@Test
	public void testSetTvInvestment()
	{
		assertNotSame(21000.00, toaster1.getTvInvestment());
		try
		{
			toaster1.setTvInvestment(21000.00);
		} catch (Exception e)
		{
			System.err.print(e);
		}
		assertEquals(21000.00, toaster1.getTvInvestment());
	}

	@Test
	public void testSetNewsPaperInvestment()
	{
		assertNotSame(5000.00, toaster1.getNewspaperInvestment());
		try
		{
			toaster1.setNewspaperInvestment(5000.00);
		} catch (Exception e)
		{
			System.err.print(e);
		}
		assertEquals(5000.00, toaster1.getNewspaperInvestment());
	}

	@Test
	public void testSetRadioInvestment()
	{
		assertNotSame(12000.00, toaster1.getRadioInvestment());
		try
		{
			toaster1.setRadioInvestment(12000.00);
		} catch (Exception e)
		{
			System.err.print(e);
		}
		assertEquals(12000.00, toaster1.getRadioInvestment());
	}

	@Test
	public void testSetResearch()
	{
		assertNotSame(1000.0, toaster1.getResearch());
		toaster1.setResearch(1000);
		assertEquals(1000.0, toaster1.getResearch());
	}

	@Test
	public void testSetIndex()
	{
		assertNotSame(2.00, toaster1.getIndex());
		toaster1.setIndex(2.00);
		assertEquals(2.00, toaster1.getIndex());
	}

	@Test
	public void testSetTurnover()
	{
		assertNotSame(6.17, toaster1.getTurnover());
		toaster1.setTurnover(6.17);
		assertEquals(6.17, toaster1.getTurnover());
	}

	@Test
	public void testSetCost()
	{
		assertNotSame(11.11, toaster1.getCost());
		toaster1.setCost(11.11);
		assertEquals(11.11, toaster1.getCost());
	}

	@Test
	public void testSetProfit()
	{
		assertNotSame(1.00, toaster1.getProfit());
		toaster1.setProfit(1.00);
		assertEquals(1.00, toaster1.getProfit());
	}

	@Test
	public void testSetMarketShare()
	{
		assertNotSame(205, toaster1.getMarketShare());
		toaster1.setMarketShare(205);
		assertEquals(205, toaster1.getMarketShare());
	}

	@Test
	public void testSetType()
	{
		assertNotSame(Type.TYPE3, toaster1.getType());
		toaster1.setType(Type.TYPE3);
		assertEquals(Type.TYPE3, toaster1.getType());
	}
	
	@Test
	public void testSetInvestmentQuality()
	{
		assertNotSame(10.00, toaster1.getQualityInvestment());
		toaster1.setQualityInvestment(10.00);
		assertEquals(10.00, toaster1.getQualityInvestment());
	}
	
	@Test
	public void testSetInvestmentDesign()
	{
		assertNotSame(10.00, toaster1.getDesignInvestment());
		toaster1.setDesignInvestment(10.00);
		assertEquals(10.00, toaster1.getDesignInvestment());
	}
	
	@Test
	public void testSetInvestmentEcology()
	{
		assertNotSame(10.00, toaster1.getEcologyInvestment());
		toaster1.setEcologyInvestment(10.00);
		assertEquals(10.00, toaster1.getEcologyInvestment());
	}
	
	@Test
	public void testSetQualityInvestmentKum()
	{
		assertNotSame(1.00, toaster1.getQualityInvestmentKum());
		toaster1.setQualityInvestmentKum();
		assertEquals(1.00, toaster1.getQualityInvestmentKum());
	}
	
	@Test
	public void testSetDesignInvestmentKum()
	{
		assertNotSame(1.00, toaster1.getDesignInvestmentKum());
		toaster1.setDesignInvestmentKum();
		assertEquals(1.00, toaster1.getDesignInvestmentKum());
	}
	
	@Test
	public void testSetEcologyInvestmentKum()
	{
		assertNotSame(1.00, toaster1.getEcologyInvestmentKum());
		toaster1.setEcologyInvestmentKum();
		assertEquals(1.00, toaster1.getEcologyInvestmentKum());
	}
	
	
	@Test
	public void testSetTvInvestmentKum()
	{
		assertNotSame(1.00, toaster1.getTvInvestmentKum());
		toaster1.setTvInvestmentKum();
		assertEquals(1.00, toaster1.getTvInvestmentKum());
	}
	
	@Test
	public void testSetNewspaperInvestmentKum()
	{
		assertNotSame(1.00, toaster1.getNewspaperInvestmentKum());
		toaster1.setNewspaperInvestmentKum();
		assertEquals(1.00, toaster1.getNewspaperInvestmentKum());
	}
	
	@Test
	public void testSetRadioInvestmentKum()
	{
		assertNotSame(1.00, toaster1.getRadioInvestmentKum());
		toaster1.setRadioInvestmentKum();
		assertEquals(1.00, toaster1.getRadioInvestmentKum());
	}
	
	@Test
	public void testSetProduction()
	{
		assertNotSame(10, toaster1.getProduction());
		toaster1.setProduction(10);
		assertEquals(10, toaster1.getProduction());
	}
	


	// //Calculation///////////////////////////
	@Test
	public void testCalculateMarketShares()
	{
		//Typ 1
		Type.TYPE1.setMarketVolume(10000);
		assertNotSame(5000, toaster1.getMarketShare());
		toaster1.calculateMarketShare(18.00);
		assertEquals(5000, toaster1.getMarketShare());
		
		//Typ 2
		Type.TYPE2.setMarketVolume(10000);
		assertNotSame(5000, toaster2.getMarketShare());
		toaster2.calculateMarketShare(18.00);
		assertEquals(5000, toaster2.getMarketShare());
		
		//Typ 3
		Type.TYPE3.setMarketVolume(10000);
		assertNotSame(5000, toaster3.getMarketShare());
		toaster3.calculateMarketShare(18.00);
		assertEquals(5000, toaster3.getMarketShare());
	}

	@Test
	public void testCalculateTurnover()
	{
		//Typ 1
		toaster1.setTurnover(1000);
		assertNotSame(100000.00, toaster1.getTurnover());
		toaster1.calculateTurnover();
		assertEquals(100000.00, toaster1.getTurnover());
		//Typ 2
		toaster2.setTurnover(1000);
		assertNotSame(100000.00, toaster2.getTurnover());
		toaster2.calculateTurnover();
		assertEquals(100000.00, toaster2.getTurnover());
		//Typ 3
		toaster3.setTurnover(1000);
		assertNotSame(100000.00, toaster3.getTurnover());
		toaster3.calculateTurnover();
		assertEquals(100000.00, toaster3.getTurnover());
	}

	@Test
	public void testCalculateCost()
	{
		//Typ 1
		assertNotSame(13000.00, toaster1.getCost());
		toaster1.setMarketShare(1000);
		toaster1.calculateCost();
		assertEquals(13000.00, toaster1.getCost());
		
		//Typ 2
		assertNotSame(35000.00, toaster2.getCost());
		toaster2.setMarketShare(1000);
		toaster2.calculateCost();
		assertEquals(35000.00, toaster2.getCost());
		
		//Typ 3
		assertNotSame(73000.00, toaster3.getCost());
		toaster3.setMarketShare(1000);
		toaster3.calculateCost();
		assertEquals(81000.00, toaster3.getCost());
	}

	@Test
	public void testCalculateProfit()
	{
		//Typ 1
		assertNotSame(50000.0, toaster1.getProfit());
		toaster1.calculateProfit();
		assertEquals(50000.0, toaster1.getProfit());
		
		//Typ 2
		assertNotSame(50000.0, toaster2.getProfit());
		toaster2.calculateProfit();
		assertEquals(50000.0, toaster2.getProfit());
		
		//Typ 3
		assertNotSame(50000.0, toaster3.getProfit());
		toaster3.calculateProfit();
		assertEquals(50000.0, toaster3.getProfit());
	}

	@Test
	public void testCalculateResearch()
	{
		//Typ 1
		assertEquals(3.00, toaster1.calculateResearch());
		
		//Typ 2
		assertEquals(3.00, toaster2.calculateResearch());
		
		//Typ 3
		assertEquals(3.00, toaster3.calculateResearch());

	}

	@Test
	public void testCalculateMarketing()
	{	//Typ 1
		assertEquals(3.0, toaster1.calculateMarketing());
		
		//Typ 2
		assertEquals(3.0, toaster2.calculateMarketing());
		
		//Typ 3
		assertEquals(3.0, toaster3.calculateMarketing());
	}

	@Test
	public void testCalculateIndex()
	{
		//Typ 1
		toaster1.setIndex(1.00);
		assertNotSame(9.00, toaster1.getIndex());
		toaster1.calculateIndex();
		assertEquals(9.00, toaster1.getIndex());
		
		//Typ 2
		toaster2.setIndex(1.00);
		try{toaster2.setPrice(40.00);}
		catch(Exception e){System.err.println(e);}
		assertNotSame(9.00, toaster2.getIndex());
		toaster2.calculateIndex();
		assertEquals(9.00, toaster2.getIndex());
		
		//Typ 3
		toaster3.setIndex(1.00);
		try{toaster3.setPrice(150.00);}
		catch(Exception e){System.err.println(e);}
		assertNotSame(9.00, toaster3.getIndex());
		toaster3.calculateIndex();
		assertEquals(9.00, toaster3.getIndex());
		
		
	}
	
	public void testResetUserInput()
	{
	
		
		
		assertEquals(1.00,toaster1.getTvInvestment());
		assertEquals(1.00,toaster1.getNewspaperInvestment());
		assertEquals(1.00,toaster1.getRadioInvestment());
		assertEquals(1.00,toaster1.getQualityInvestment());
		assertEquals(1.00,toaster1.getDesignInvestment());
		assertEquals(1.00,toaster1.getEcologyInvestment());
		assertEquals(1000,toaster1.getProduction());
		
		toaster1.resetUserInput();
		
		assertEquals(0.00,toaster1.getTvInvestment());
		assertEquals(0.00,toaster1.getNewspaperInvestment());
		assertEquals(0.00,toaster1.getRadioInvestment());
		assertEquals(0.00,toaster1.getQualityInvestment());
		assertEquals(0.00,toaster1.getDesignInvestment());
		assertEquals(0.00,toaster1.getEcologyInvestment());
		assertEquals(0,toaster1.getProduction());
		

		
	}
}
