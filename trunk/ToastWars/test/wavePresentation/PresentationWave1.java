package wavePresentation;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.gwtext.client.widgets.layout.AccordionLayout;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.UserFactory;
import toastwars.util.NumberUtil;

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

	}

	@Test
	public void testChangePriceAndSimulate()
	{
		System.out.println("Start der " + Game.getInstance().getCurrentRound() + ". Runde");
		System.out.println();
		int companyID;
		int marketshare;
		double turnover;
		double cost;
		double profit;
		double capital;
		
		printData();
		
		double alterPreis = group1.getCompany().getToasterList().get(0).getPrice();
//		int alterMarktanteil = marketshare;
		System.out.println("Preisänderung:");
		System.out.print("Unternehmen 1 => von: " + group1.getCompany().getToasterList().get(0).getPrice() + " €");
		group1.getCompany().getToasterList().get(0).setPrice(7.00);
		System.out.println(" auf: " + group1.getCompany().getToasterList().get(0).getPrice() + " €");
		double neuerPreis = group1.getCompany().getToasterList().get(0).getPrice();
		assertNotSame(alterPreis, neuerPreis);

		System.out.print("Unternehmen 2 => von: " + group2.getCompany().getToasterList().get(0).getPrice() + " €");
		group2.getCompany().getToasterList().get(0).setPrice(5.00);
		System.out.println(" auf: " + group2.getCompany().getToasterList().get(0).getPrice() + " €");
		System.out.println();

		System.out.println("Start der Simulation");
		master.simulate();
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
