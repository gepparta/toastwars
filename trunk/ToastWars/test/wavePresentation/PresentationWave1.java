package wavePresentation;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import toastwars.server.ToastWarsServiceImpl;
import toastwars.server.dao.DAOGame;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.UserFactory;

public class PresentationWave1 extends TestCase
{
	private Toaster toaster1;
	private Toaster toaster2;
	private ArrayList<Toaster> toasterList1;
	private ArrayList<Toaster> toasterList2;
	private Company company1;
	private Company company2;
	private Game game;
	private Group group1;
	private Group group2;
	private Master master;
	private ToastWarsServiceImpl impl;

	@Before
	public void setUp() throws Exception
	{		
		impl = new ToastWarsServiceImpl();
		DAOGame.resetGame();
		// price, marketing, tvInvestment, newsPaperInvestment, radioInvestment,
		// research, quality, design, efficiency, index,
		// turnover, cost, profit, marketShare, type
		toaster1 = new Toaster(10.00, 3.00, 20000.00, 5000.00, 10000.00, 3.00, 5000.00, 5000.00, 5000.00, 9.00, 100000.00, 43000.00, 1000.00, 10000, Type.TYPE1);
		toaster2 = new Toaster(40.00, 3.00, 30000.00, 10000.00, 20000.00, 3.00, 10000.00, 10000.00, 10000.00, 9.00, 240000.00, 130000.00, 1000.00, 6000, Type.TYPE1);

		toasterList1 = new ArrayList<Toaster>();
		toasterList2 = new ArrayList<Toaster>();
		toasterList1.add(toaster1);
		toasterList2.add(toaster2);
		company1 = new Company(1.05, 1.07, 2.10, 10000.00, 50, toasterList1);
		company2 = new Company(1.05, 1.07, 2.10, 20000.00, 50, toasterList2);
		master = (Master) UserFactory.createUser("Master", "ADMIN", "ADMIN");
		group1 = (Group) UserFactory.createUser("Group", "GROUP1", "GROUP1");
		group2 = (Group) UserFactory.createUser("Group", "GROUP2", "GROUP2");
		impl.startGame(2);
		
		Game.getInstance().getGroupList().clear();
		Game.getInstance().addGroup(group1);
		Game.getInstance().addGroup(group2);
		group1.setCompany(company1);
		group2.setCompany(company2);
		Game.getInstance().getGroupList().get(0).getCompany().setToasterList(toasterList1);
		Game.getInstance().getGroupList().get(1).getCompany().setToasterList(toasterList2);

	}

	@After
	public void tearDown() throws Exception
	{
		toaster1 = null;
		toaster2 = null;
		toasterList1 = null;
		toasterList2 = null;
		company1 = null;
		company2 = null;
		group1 = null;
		group2 = null;
		master = null;
		
		}

	@Test
	public void testChangePriceAndSimulate() throws Exception
	{
		//Type 1 Old
		 String TypeOldDescription1 = "Der 1. Typ";
		 int TypeOldmarketVolume1 = 10000;
		 double TypeOldrandom1 = 0.05;
		 double TypeOldfixCostPerMachine1 = 10000.00;
		 double TypeOldVariableCost1 = 3.00;
		 int TypeOldCapacity1 = 4000;
		 

			
			//Type 2 Old
		 String TypeOldDescription2 = "Der 1. Typ";;
		 int TypeOldmarketVolume2 = 10000;
		 double TypeOldrandom2 = 0.05;
		 double TypeOldfixCostPerMachine2 = 10000.00;
		 double TypeOldVariableCost2 = 3.00;
		 int TypeOldCapacity2 = 4000;
		 
		 // Toaster 1  Old
		 

			int OldToasterToasterID1 = 1;
			double OldToasterPrice1  = 10;
			double OldToasterMarketing1 = 3.00;
			double OldToasterResearch1 = 3.00;
			double OldToasterIndex1 = 9.00;
			double OldToasterTurnover1 = 100000.00;
			double OldToasterCost1 = 43000.00;
			double OldToasterProfit1 = 1000.00;
			int OldToasterMarketShare1 = 10000;
			Type OldToasterType1 = Type.TYPE1;
			
			
		
			 // Toaster 2  Old
			 

			int OldToasterToasterID2 = 2;
			double OldToasterPrice2 = 40;
			double OldToasterMarketing2= 3.00;
			double OldToasterResearch2= 3.00;
			double OldToasterIndex2= 9.00;
			double OldToasterTurnover2= 240000.00;
			double OldToasterCost2 = 130000.00;
			double OldToasterProfit2= 1000.00;
			int OldToasterMarketShare2= 6000;
			Type OldToasterType2= Type.TYPE1;
			

			
			//Company 1 Old

			int OldCompanyCompanyID1 = 1;
			String OldCompanyDescription1= "Unternehmen des ersten Spielers";
			double OldCompanyTurnover1=1.05;
			double OldCompanyCost1= 1.07;
			double OldCompanyProfit1=2.1;
			double OldCompanyCapital1 = 10000.00;
			int OldCompanyMarketShare1 = 50;


			
			//Company 2 Old

			int OldCompanyCompanyID2 = 2;
			String OldCompanyDescription2= "Unternehmen des zweiten Spielers";
			double OldCompanyTurnover2 = 1.05;
			double OldCompanyCost2 = 1.07;
			double OldCompanyProfit2 = 2.1;
			double OldCompanyCapital2= 20000.00;
			int OldCompanyMarketShare2 = 50;

			
			//Game Old
			int oldGameCurrentRound = 1;
			int oldGameUserAmount = 2;
			
////////////////////////////////////////////////////////////////////////////////////			
			
			//Type 1 New
			 String TypeNewDescription1 = "Der 1. Typ";
			 int TypeNewmarketVolume1 = 10000;
			 double TypeNewrandom1 = 0.05;
			 double TypeNewfixCostPerMachine1 = 10000.00;
			 double TypeNewVariableCost1 = 3.00;
			 int TypeNewCapacity1 = 2000;
			 

				
				//Type 2 New
			 String TypeNewDescription2 = "Der 1. Typ";;
			 int TypeNewmarketVolume2 = 10000;
			 double TypeNewrandom2 = 0.05;
			 double TypeNewfixCostPerMachine2 = 10000.00;
			 double TypeNewVariableCost2 = 3.00;
			 int TypeNewCapacity2 = 2000;
			 
			 // Toaster 1  New
			 

				int NewToasterToasterID1 = 1;
				double NewToasterPrice1  = 7;
				double NewToasterMarketing1 = 3.00;
				double NewToasterResearch1 = 3.00;
				double NewToasterIndex1 = 13.81;
				double NewToasterTurnover1 = 18144.00;
				double NewToasterCost1 = 19776.00;
				double NewToasterProfit1 = -1632.00;
				int NewToasterMarketShare1 = 2592;
				Type NewToasterType1 = Type.TYPE1;
				
				
			
				 // Toaster 2  New
				 

				int NewToasterToasterID2 = 2;
				double NewToasterPrice2 = 13;
				double NewToasterMarketing2= 4.33;
				double NewToasterResearch2= 3.00;
				double NewToasterIndex2= 0.52;
				double NewToasterTurnover2= 22841.00;
				double NewToasterCost2 = 6271.00;
				double NewToasterProfit2= 16570.00;
				int NewToasterMarketShare2= 1757;
				Type NewToasterType2= Type.TYPE1;
				

				
				//Company 1 New

				int NewCompanyCompanyID1 = 1;
				String NewCompanyDescription1= "Unternehmen des ersten Spielers";
				double NewCompanyTurnover1=57701.00;
				double NewCompanyCost1= 27729.00;
				double NewCompanyProfit1=29972.00;
				double NewCompanyCapital1 = 129972.00;
				int NewCompanyMarketShare1 = 8243;


				
				//Company 2 New

				int NewCompanyCompanyID2 = 2;
				String NewCompanyDescription2= "Unternehmen des zweiten Spielers";
				double NewCompanyTurnover2 = 22841.00;
				double NewCompanyCost2 = 6271.00;
				double NewCompanyProfit2 = 16570.00;
				double NewCompanyCapital2= 116570.00;
				int NewCompanyMarketShare2 = 1757;

				
				//Game New
				int NewGameCurrentRound = 1;
				int NewGameUserAmount = 2;
		
		System.out.println("Start der " + Game.getInstance().getCurrentRound() + ". Runde");
		System.out.println();

		
//		
//		//Type 1 Old
//		 assertEquals(TypeOldDescription1, Type.TYPE1.getDescription());
//		 assertEquals(TypeOldmarketVolume1, Type.TYPE1.getMarketVolume());
//		 assertEquals(TypeOldrandom1, Type.TYPE1.getRandom());
//		  assertEquals(TypeOldfixCostPerMachine1, Type.TYPE1.getFixCosts());
//		 assertEquals(TypeOldVariableCost1, Type.TYPE1.getVariableCosts());
//		 assertEquals(TypeOldCapacity1, Type.TYPE1.getCapacity());
//		 
//		 
//			//Type 2 Old
//		 assertEquals(TypeOldDescription2, Type.TYPE1.getDescription());
//		 assertEquals(TypeOldmarketVolume2, Type.TYPE1.getMarketVolume());
//		 assertEquals(TypeOldrandom2, Type.TYPE1.getRandom());
//		  assertEquals(TypeOldfixCostPerMachine2, Type.TYPE1.getFixCosts());
//		 assertEquals(TypeOldVariableCost2, Type.TYPE1.getVariableCosts());
//		 assertEquals(TypeOldCapacity2, Type.TYPE1.getCapacity());
//		 
//		 // Toaster 1  Old
//		 
//		
//		 assertEquals(OldToasterToasterID1, toaster1.getToasterID());
//		 assertEquals(OldToasterPrice1, toaster1.getPrice());
//		 assertEquals(OldToasterMarketing1, toaster1.getMarketing());
//		 assertEquals(OldToasterResearch1, toaster1.getResearch());
//		 assertEquals(OldToasterIndex1, toaster1.getIndex());
//		 assertEquals(OldToasterTurnover1, toaster1.getTurnover());
//		 assertEquals(OldToasterCost1, toaster1.getCost());
//		 assertEquals(OldToasterProfit1, toaster1.getProfit());
//		 assertEquals(OldToasterMarketShare1, toaster1.getMarketShare());
//		 assertEquals(OldToasterType1, toaster1.getType());
//			
//			 // Toaster 2  Old
//			 
//		 
//		 assertEquals(OldToasterToasterID2, toaster2.getToasterID());
//		 assertEquals(OldToasterPrice2, toaster2.getPrice());
//		 assertEquals(OldToasterMarketing2, toaster2.getMarketing());
//		 assertEquals(OldToasterResearch2, toaster2.getResearch());
//		 assertEquals(OldToasterIndex2, toaster2.getIndex());
//		 assertEquals(OldToasterTurnover2, toaster2.getTurnover());
//		 assertEquals(OldToasterCost2, toaster2.getCost());
//		 assertEquals(OldToasterProfit2, toaster2.getProfit());
//		 assertEquals(OldToasterMarketShare2, toaster2.getMarketShare());
//		 assertEquals(OldToasterType2, toaster2.getType());
//			
//	
//			
//			//Company 1 Old
//		 
//		 assertEquals(OldCompanyCompanyID1, company1.getCompanyID());
//		 assertEquals(OldCompanyTurnover1, company1.getTurnover());
//		 assertEquals(OldCompanyCost1, company1.getCost());
//		 assertEquals(OldCompanyProfit1, company1.getProfit());
//		 assertEquals(OldCompanyCapital1, company1.getCapital());
//		 assertEquals(OldCompanyMarketShare1, company1.getMarketShare());
//
//			
//			//Company 2 Old
//		 
//		 assertEquals(OldCompanyCompanyID2, company2.getCompanyID());
//		 assertEquals(OldCompanyTurnover2, company2.getTurnover());
//		 assertEquals(OldCompanyCost2, company2.getCost());
//		 assertEquals(OldCompanyProfit2, company2.getProfit());
//		 assertEquals(OldCompanyCapital2, company2.getCapital());
//		 assertEquals(OldCompanyMarketShare2, company2.getMarketShare());
//
//			
//			//Game Old
//		 
//		 //assertEquals(oldGameCurrentRound, game.getCurrentRound());
//		 //assertEquals(oldGameUserAmount,game.getUserAmount());
//		
		
		printData();
		
		double alterPreis = group1.getCompany().getToasterList().get(0).getPrice();
		System.out.println("Preisänderung:");
		System.out.print("Unternehmen 1 => von: " + group1.getCompany().getToasterList().get(0).getPrice() + " €");
		group1.getCompany().getToasterList().get(0).setPrice(7.00);
		System.out.println(" auf: " + group1.getCompany().getToasterList().get(0).getPrice() + " €");
		double neuerPreis = group1.getCompany().getToasterList().get(0).getPrice();
		assertNotSame(alterPreis, neuerPreis);

		System.out.print("Unternehmen 2 => von: " + group2.getCompany().getToasterList().get(0).getPrice() + " €");
		group2.getCompany().getToasterList().get(0).setPrice(13.00);
		System.out.println(" auf: " + group2.getCompany().getToasterList().get(0).getPrice() + " €");
		System.out.println();

		System.out.println("Start der Simulation");
		master.simulate();
		
		//Type 1 New
		 assertEquals(TypeNewDescription1, Type.TYPE1.getDescription());
		 assertEquals(TypeNewmarketVolume1, Type.TYPE1.getMarketVolume());
		 assertEquals(TypeNewrandom1, Type.TYPE1.getRandom());
		  assertEquals(TypeNewfixCostPerMachine1, Type.TYPE1.getFixCosts());
		 assertEquals(TypeNewVariableCost1, Type.TYPE1.getVariableCosts());
		 assertEquals(TypeNewCapacity1, Type.TYPE1.getCapacity());
		 
		 
			//Type 2 New
		 assertEquals(TypeNewDescription2, Type.TYPE1.getDescription());
		 assertEquals(TypeNewmarketVolume2, Type.TYPE1.getMarketVolume());
		 assertEquals(TypeNewrandom2, Type.TYPE1.getRandom());
		  assertEquals(TypeNewfixCostPerMachine2, Type.TYPE1.getFixCosts());
		 assertEquals(TypeNewVariableCost2, Type.TYPE1.getVariableCosts());
		 assertEquals(TypeNewCapacity2, Type.TYPE1.getCapacity());
		 
		 // Toaster 1  New
		 
		 
		 assertEquals(NewToasterToasterID1, toaster1.getToasterID());
		 assertEquals(NewToasterPrice1, toaster1.getPrice());
		 assertEquals(NewToasterMarketing1, toaster1.getMarketing());
		 assertEquals(NewToasterResearch1, toaster1.getResearch());
		 assertEquals(NewToasterIndex1, toaster1.getIndex());
		 assertEquals(36288.0, toaster1.getTurnover());
		 assertEquals(28552.0, toaster1.getCost());
		 assertEquals(7736.0, toaster1.getProfit());
		 assertEquals(5184, toaster1.getMarketShare());
		 assertEquals(NewToasterType1, toaster1.getType());
			
			 // Toaster 2  New
			 
		 
		 assertEquals(NewToasterToasterID2, toaster2.getToasterID());
		 assertEquals(NewToasterPrice2, toaster2.getPrice());
		 assertEquals(4.33, toaster2.getMarketing());
		 assertEquals(4.06, toaster2.getResearch());
		 assertEquals(12.83, toaster2.getIndex());
		 assertEquals(62608.0, toaster2.getTurnover());
		 assertEquals(27448.0, toaster2.getCost());
		 assertEquals(35160.0, toaster2.getProfit());
		 assertEquals(4816, toaster2.getMarketShare());
		 assertEquals(NewToasterType2, toaster2.getType());
			
	
			
			//Company 1 New
		 
		 assertEquals(NewCompanyCompanyID1, company1.getCompanyID());
		 assertEquals(36288.0, company1.getTurnover());
		 assertEquals(28552.0, company1.getCost());
		 assertEquals(7736.0, company1.getProfit());
		 assertEquals(17736.0, company1.getCapital());
		 assertEquals(5184, company1.getMarketShare());

			
			//Company 2 New
		 
		 assertEquals(NewCompanyCompanyID2, company2.getCompanyID());
		 assertEquals(62608.0, company2.getTurnover());
		 assertEquals(27448.0, company2.getCost());
		 assertEquals(35160.0, company2.getProfit());
		 assertEquals(55160.0, company2.getCapital());
		 assertEquals(4816, company2.getMarketShare());

			
			//Game New
		 
		 //assertEquals(NewGameCurrentRound, game.getCurrentRound());
		 //assertEquals(NewGameUserAmount,game.getUserAmount());
		 
		 
		
		
		System.out.println("Ende der Simulation");
		System.out.println();

		System.out.println("Ende der " + Game.getInstance().getCurrentRound() + ". Runde");
		System.out.println();
		
		printData();
	}

	private void printData()
	{
		System.out.println("Daten der Unternehmen: ");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s | %15s |%16s | %15s |%16s |%16s | %15s |\n", "Unternehmens-ID", "Marktanteil (Stk.)", "Marktanteil (%)", "Umsatz", "Kosten", "Gewinn", "Kapital");
		int companyID;
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
		int marketshare;
		double turnover;
		double cost;
		double profit;
		double capital;
		double marketsharePercent;
		for (int i = 0; i < Game.getInstance().getGroupList().size(); i++)
		{
			companyID = Game.getInstance().getGroupList().get(i).getCompany().getCompanyID();
			marketshare = Game.getInstance().getGroupList().get(i).getCompany().getMarketShare();
			marketsharePercent = marketshare / (double)Type.TYPE1.getMarketVolume() * 100;
			turnover = Game.getInstance().getGroupList().get(i).getCompany().getTurnover();
			cost = Game.getInstance().getGroupList().get(i).getCompany().getCost();
			profit = Game.getInstance().getGroupList().get(i).getCompany().getProfit();
			capital = Game.getInstance().getGroupList().get(i).getCompany().getCapital();
			System.out.printf("%1$,15d | %2$,18d | %3$,13.2f %% | %4$,13.2f € | %5$,13.2f € | %6$,13.2f € | %7$,13.2f € | \n", companyID, marketshare, marketsharePercent, turnover, cost, profit, capital);
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
	}
}
