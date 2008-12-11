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
		toaster1 = new Toaster(10, 1000, 8155, 1.00, 10000, 5000, 2.58, 500, Type.TYPE1);
		toaster2 = new Toaster(10, 1000, 8155, 1.00, 10000, 5000, 2.58, 500, Type.TYPE1);
		toasterList1 = new ArrayList<Toaster>();
		toasterList2 = new ArrayList<Toaster>();
		toasterList1.add(toaster1);
		toasterList2.add(toaster2);
		company1 = new Company("Unternehmen des ersten Spielers", 10000.00, 10000.00, 10000.00,
				100000.00, 50, toasterList1);
		company2 = new Company("Unternehmen des zweiten Spielers", 10000.00, 10000.00, 10000.00,
				100000.00, 50, toasterList2);
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
		System.out.println("Daten für Unternehmen 1: ");
		System.out.println("Company ID: " + group1.getCompany().getCompanyID());
		System.out.println("Turnover: " + group1.getCompany().getTurnover());
		System.out.println("Cost: " + group1.getCompany().getCost());
		System.out.println("Profit: " + group1.getCompany().getProfit());
		System.out.println("Capital: " + group1.getCompany().getCapital());
		System.out.println("Marketshare: " + group1.getCompany().getMarketShare());
		System.out.println();

		System.out.println("Daten für Unternehmen 2: ");
		System.out.println("Company ID: " + group2.getCompany().getCompanyID());
		System.out.println("Turnover: " + group2.getCompany().getTurnover());
		System.out.println("Cost: " + group2.getCompany().getCost());
		System.out.println("Profit: " + group2.getCompany().getProfit());
		System.out.println("Capital: " + group2.getCompany().getCapital());
		System.out.println("Marketshare: " + group2.getCompany().getMarketShare());
		System.out.println();

		System.out.println("Preisänderung:");
		System.out.println("Alter Preis des Toasters von Unternehmen 1: "
				+ group1.getCompany().getToasterList().get(0).getPrice());
		group1.getCompany().getToasterList().get(0).setPrice(11.00);
		System.out.println("Neuer Preis des Toasters von Unternehmen 1: "
				+ group1.getCompany().getToasterList().get(0).getPrice());
		System.out.println("Alter Preis des Toasters von Unternehmen 2: "
				+ group2.getCompany().getToasterList().get(0).getPrice());
		group2.getCompany().getToasterList().get(0).setPrice(15.00);
		System.out.println("Neuer Preis des Toasters von Unternehmen 2: "
				+ group2.getCompany().getToasterList().get(0).getPrice());
		System.out.println();

		System.out.println("Start der Simulation");
		master.simulate();
		System.out.println("Ende der Simulation");

		
		System.out.println("Ende der " + Game.getInstance().getCurrentRound() + ". Runde");
		System.out.println();
		System.out.println("Daten für Unternehmen 1: ");
		System.out.println("Company ID: " + group1.getCompany().getCompanyID());
		System.out.println("Turnover: " + group1.getCompany().getTurnover());
		System.out.println("Cost: " + group1.getCompany().getCost());
		System.out.println("Profit: " + group1.getCompany().getProfit());
		System.out.println("Capital: " + group1.getCompany().getCapital());
		System.out.println("Marketshare: " + group1.getCompany().getMarketShare());
		System.out.println("Index: "+ group1.getCompany().getToasterList().get(0).getIndex());
		System.out.println();

		System.out.println("Daten für Unternehmen 2: ");
		System.out.println("Company ID: " + group2.getCompany().getCompanyID());
		System.out.println("Turnover: " + group2.getCompany().getTurnover());
		System.out.println("Cost: " + group2.getCompany().getCost());
		System.out.println("Profit: " + group2.getCompany().getProfit());
		System.out.println("Capital: " + group2.getCompany().getCapital());
		System.out.println("Marketshare: " + group2.getCompany().getMarketShare());
		System.out.println("Index: "+ group2.getCompany().getToasterList().get(0).getIndex());
		System.out.println();

	}

}
