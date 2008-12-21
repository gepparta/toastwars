package wavePresentation;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

	@Before
	public void setUp() throws Exception
	{
		toaster1 = new Toaster(10, 10000.00, 12923.00, 1.00, 50000.00, 30000.00, 20000.00, 5000, Type.TYPE1);
		toaster2 = new Toaster(10, 10000.00, 12923.00, 1.00, 50000.00, 30000.00, 20000.00, 5000, Type.TYPE1);
		toasterList1 = new ArrayList<Toaster>();
		toasterList2 = new ArrayList<Toaster>();
		toasterList1.add(toaster1);
		toasterList2.add(toaster2);
		company1 = new Company("Unternehmen des ersten Spielers", 50000.00, 30000.00, 20000.00, 100000.00, 5000, toasterList1);
		company2 = new Company("Unternehmen des zweiten Spielers", 50000.00, 30000.00, 20000.00, 100000.00, 5000, toasterList2);
		master = (Master) UserFactory.createUser("Master", "ADMIN", "ADMIN");
		group1 = (Group) UserFactory.createUser("Group", "GROUP1", "GROUP1");
		group2 = (Group) UserFactory.createUser("Group", "GROUP2", "GROUP2");
		master.startGame(2);
		group1.setCompany(company1);
		group2.setCompany(company2);
		Game.getInstance().addCompany(company1);
		Game.getInstance().addCompany(company2);
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
		 double TypeOldfixCostPerMachine1 = 1000.00;
		 double TypeOldVariableCost1 = 3.00;
		 int TypeOldCapacity1 = 4000;
		 

			
			//Type 2 Old
		 String TypeOldDescription2 = "Der 1. Typ";;
		 int TypeOldmarketVolume2 = 10000;
		 double TypeOldrandom2 = 0.05;
		 double TypeOldfixCostPerMachine2 = 1000.00;
		 double TypeOldVariableCost2 = 3.00;
		 int TypeOldCapacity2 = 4000;
		 
		 // Toaster 1  Old
		 

			int OldToasterToasterID1 = 1;
			double OldToasterPrice1  = 10;
			double OldToasterMarketing1 = 10000.00;
			double OldToasterResearch1 = 12923.00;
			double OldToasterIndex1 = 1.00;
			double OldToasterTurnover1 = 50000.00;
			double OldToasterCost1 = 30000.00;
			double OldToasterProfit1 = 20000.00;
			int OldToasterMarketShare1 = 5000;
			Type OldToasterType1 = Type.TYPE1;
			
			
		
			 // Toaster 2  Old
			 

			int OldToasterToasterID2 = 2;
			double OldToasterPrice2 = 10;
			double OldToasterMarketing2= 10000.00;
			double OldToasterResearch2= 12923.00;
			double OldToasterIndex2= 1.00;
			double OldToasterTurnover2= 50000.00;
			double OldToasterCost2 = 30000.00;
			double OldToasterProfit2= 20000.00;
			int OldToasterMarketShare2= 5000;
			Type OldToasterType2= Type.TYPE1;
			

			
			//Company 1 Old

			int OldCompanyCompanyID1 = 1;
			String OldCompanyDescription1= "Unternehmen des ersten Spielers";
			double OldCompanyTurnover1=50000.00;
			double OldCompanyCost1= 30000.00;
			double OldCompanyProfit1=20000.00;
			double OldCompanyCapital1 = 100000.00;
			int OldCompanyMarketShare1 = 5000;


			
			//Company 2 Old

			int OldCompanyCompanyID2 = 2;
			String OldCompanyDescription2= "Unternehmen des zweiten Spielers";
			double OldCompanyTurnover2 = 50000.00;
			double OldCompanyCost2 = 30000.00;
			double OldCompanyProfit2 = 20000.00;
			double OldCompanyCapital2= 100000.00;
			int OldCompanyMarketShare2 = 5000;

			
			//Game Old
			int oldGameCurrentRound = 1;
			int oldGameUserAmount = 2;
			
////////////////////////////////////////////////////////////////////////////////////			
			
			//Type 1 New
			 String TypeNewDescription1 = "Der 1. Typ";
			 int TypeNewmarketVolume1 = 10000;
			 double TypeNewrandom1 = 0.05;
			 double TypeNewfixCostPerMachine1 = 1000.00;
			 double TypeNewVariableCost1 = 3.00;
			 int TypeNewCapacity1 = 4000;
			 

				
				//Type 2 New
			 String TypeNewDescription2 = "Der 1. Typ";;
			 int TypeNewmarketVolume2 = 10000;
			 double TypeNewrandom2 = 0.05;
			 double TypeNewfixCostPerMachine2 = 1000.00;
			 double TypeNewVariableCost2 = 3.00;
			 int TypeNewCapacity2 = 4000;
			 
			 // Toaster 1  New
			 

				int NewToasterToasterID1 = 1;
				double NewToasterPrice1  = 7;
				double NewToasterMarketing1 = 10000.00;
				double NewToasterResearch1 = 12923.00;
				double NewToasterIndex1 = 2.44;
				double NewToasterTurnover1 = 57701.00;
				double NewToasterCost1 = 27729.00;
				double NewToasterProfit1 = 29972.00;
				int NewToasterMarketShare1 = 8243;
				Type NewToasterType1 = Type.TYPE1;
				
				
			
				 // Toaster 2  New
				 

				int NewToasterToasterID2 = 2;
				double NewToasterPrice2 = 13;
				double NewToasterMarketing2= 10000.00;
				double NewToasterResearch2= 12923.00;
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

		
		
		//Type 1 Old
		 assertEquals(TypeOldDescription1, Type.TYPE1.getDescription());
		 assertEquals(TypeOldmarketVolume1, Type.TYPE1.getMarketVolume());
		 assertEquals(TypeOldrandom1, Type.TYPE1.getRandom());
		  assertEquals(TypeOldfixCostPerMachine1, Type.TYPE1.getFixCostPerMachine());
		 assertEquals(TypeOldVariableCost1, Type.TYPE1.getVariableCost());
		 assertEquals(TypeOldCapacity1, Type.TYPE1.getCapacity());
		 
		 
			//Type 2 Old
		 assertEquals(TypeOldDescription2, Type.TYPE1.getDescription());
		 assertEquals(TypeOldmarketVolume2, Type.TYPE1.getMarketVolume());
		 assertEquals(TypeOldrandom2, Type.TYPE1.getRandom());
		  assertEquals(TypeOldfixCostPerMachine2, Type.TYPE1.getFixCostPerMachine());
		 assertEquals(TypeOldVariableCost2, Type.TYPE1.getVariableCost());
		 assertEquals(TypeOldCapacity2, Type.TYPE1.getCapacity());
		 
		 // Toaster 1  Old
		 
		
		 assertEquals(OldToasterToasterID1, toaster1.getToasterID());
		 assertEquals(OldToasterPrice1, toaster1.getPrice());
		 assertEquals(OldToasterMarketing1, toaster1.getMarketing());
		 assertEquals(OldToasterResearch1, toaster1.getResearch());
		 assertEquals(OldToasterIndex1, toaster1.getIndex());
		 assertEquals(OldToasterTurnover1, toaster1.getTurnover());
		 assertEquals(OldToasterCost1, toaster1.getCost());
		 assertEquals(OldToasterProfit1, toaster1.getProfit());
		 assertEquals(OldToasterMarketShare1, toaster1.getMarketShare());
		 assertEquals(OldToasterType1, toaster1.getType());
			
			 // Toaster 2  Old
			 
		 
		 assertEquals(OldToasterToasterID2, toaster2.getToasterID());
		 assertEquals(OldToasterPrice2, toaster2.getPrice());
		 assertEquals(OldToasterMarketing2, toaster2.getMarketing());
		 assertEquals(OldToasterResearch2, toaster2.getResearch());
		 assertEquals(OldToasterIndex2, toaster2.getIndex());
		 assertEquals(OldToasterTurnover2, toaster2.getTurnover());
		 assertEquals(OldToasterCost2, toaster2.getCost());
		 assertEquals(OldToasterProfit2, toaster2.getProfit());
		 assertEquals(OldToasterMarketShare2, toaster2.getMarketShare());
		 assertEquals(OldToasterType2, toaster2.getType());
			
	
			
			//Company 1 Old
		 
		 assertEquals(OldCompanyCompanyID1, company1.getCompanyID());
		 assertEquals(OldCompanyDescription1, company1.getDescription());
		 assertEquals(OldCompanyTurnover1, company1.getTurnover());
		 assertEquals(OldCompanyCost1, company1.getCost());
		 assertEquals(OldCompanyProfit1, company1.getProfit());
		 assertEquals(OldCompanyCapital1, company1.getCapital());
		 assertEquals(OldCompanyMarketShare1, company1.getMarketShare());

			
			//Company 2 Old
		 
		 assertEquals(OldCompanyCompanyID2, company2.getCompanyID());
		 assertEquals(OldCompanyDescription2, company2.getDescription());
		 assertEquals(OldCompanyTurnover2, company2.getTurnover());
		 assertEquals(OldCompanyCost2, company2.getCost());
		 assertEquals(OldCompanyProfit2, company2.getProfit());
		 assertEquals(OldCompanyCapital2, company2.getCapital());
		 assertEquals(OldCompanyMarketShare2, company2.getMarketShare());

			
			//Game Old
		 
		 //assertEquals(oldGameCurrentRound, game.getCurrentRound());
		 //assertEquals(oldGameUserAmount,game.getUserAmount());
		
		
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
		  assertEquals(TypeNewfixCostPerMachine1, Type.TYPE1.getFixCostPerMachine());
		 assertEquals(TypeNewVariableCost1, Type.TYPE1.getVariableCost());
		 assertEquals(TypeNewCapacity1, Type.TYPE1.getCapacity());
		 
		 
			//Type 2 New
		 assertEquals(TypeNewDescription2, Type.TYPE1.getDescription());
		 assertEquals(TypeNewmarketVolume2, Type.TYPE1.getMarketVolume());
		 assertEquals(TypeNewrandom2, Type.TYPE1.getRandom());
		  assertEquals(TypeNewfixCostPerMachine2, Type.TYPE1.getFixCostPerMachine());
		 assertEquals(TypeNewVariableCost2, Type.TYPE1.getVariableCost());
		 assertEquals(TypeNewCapacity2, Type.TYPE1.getCapacity());
		 
		 // Toaster 1  New
		 
		 
		 assertEquals(NewToasterToasterID1, toaster1.getToasterID());
		 assertEquals(NewToasterPrice1, toaster1.getPrice());
		 assertEquals(NewToasterMarketing1, toaster1.getMarketing());
		 assertEquals(NewToasterResearch1, toaster1.getResearch());
		 assertEquals(NewToasterIndex1, toaster1.getIndex());
		 assertEquals(NewToasterTurnover1, toaster1.getTurnover());
		 assertEquals(NewToasterCost1, toaster1.getCost());
		 assertEquals(NewToasterProfit1, toaster1.getProfit());
		 assertEquals(NewToasterMarketShare1, toaster1.getMarketShare());
		 assertEquals(NewToasterType1, toaster1.getType());
			
			 // Toaster 2  New
			 
		 
		 assertEquals(NewToasterToasterID2, toaster2.getToasterID());
		 assertEquals(NewToasterPrice2, toaster2.getPrice());
		 assertEquals(NewToasterMarketing2, toaster2.getMarketing());
		 assertEquals(NewToasterResearch2, toaster2.getResearch());
		 assertEquals(NewToasterIndex2, toaster2.getIndex());
		 assertEquals(NewToasterTurnover2, toaster2.getTurnover());
		 assertEquals(NewToasterCost2, toaster2.getCost());
		 assertEquals(NewToasterProfit2, toaster2.getProfit());
		 assertEquals(NewToasterMarketShare2, toaster2.getMarketShare());
		 assertEquals(NewToasterType2, toaster2.getType());
			
	
			
			//Company 1 New
		 
		 assertEquals(NewCompanyCompanyID1, company1.getCompanyID());
		 assertEquals(NewCompanyDescription1, company1.getDescription());
		 assertEquals(NewCompanyTurnover1, company1.getTurnover());
		 assertEquals(NewCompanyCost1, company1.getCost());
		 assertEquals(NewCompanyProfit1, company1.getProfit());
		 assertEquals(NewCompanyCapital1, company1.getCapital());
		 assertEquals(NewCompanyMarketShare1, company1.getMarketShare());

			
			//Company 2 New
		 
		 assertEquals(NewCompanyCompanyID2, company2.getCompanyID());
		 assertEquals(NewCompanyDescription2, company2.getDescription());
		 assertEquals(NewCompanyTurnover2, company2.getTurnover());
		 assertEquals(NewCompanyCost2, company2.getCost());
		 assertEquals(NewCompanyProfit2, company2.getProfit());
		 assertEquals(NewCompanyCapital2, company2.getCapital());
		 assertEquals(NewCompanyMarketShare2, company2.getMarketShare());

			
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
		for (int i = 0; i < Group.getGroupList().size(); i++)
		{
			companyID = Group.getGroupList().get(i).getCompany().getCompanyID();
			marketshare = Group.getGroupList().get(i).getCompany().getMarketShare();
			marketsharePercent = marketshare / (double)Type.TYPE1.getMarketVolume() * 100;
			turnover = Group.getGroupList().get(i).getCompany().getTurnover();
			cost = Group.getGroupList().get(i).getCompany().getCost();
			profit = Group.getGroupList().get(i).getCompany().getProfit();
			capital = Group.getGroupList().get(i).getCompany().getCapital();
			System.out.printf("%1$,15d | %2$,18d | %3$,13.2f %% | %4$,13.2f € | %5$,13.2f € | %6$,13.2f € | %7$,13.2f € | \n", companyID, marketshare, marketsharePercent, turnover, cost, profit, capital);
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
	}
}
