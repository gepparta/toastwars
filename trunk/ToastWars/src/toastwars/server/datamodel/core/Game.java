package toastwars.server.datamodel.core;

import java.util.ArrayList;
import toastwars.server.dao.DAOGame;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.IUser;
import toastwars.util.NumberUtil;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Game implements IsSerializable
{
	private static Game instance;
	private int userAmount;
	private int currentRound;
	// private ArrayList<Company> companyList = new ArrayList<Company>();
	// @gwt.typeArgs <toastwars.server.datamodel.user.Group>
	private static ArrayList<Group> groupList = new ArrayList<Group>();

	// *************************Constructor*****************************************************
	public Game()
	{
	}

	private Game(int userAmount)
	{
		this.userAmount = userAmount;
		try
		{
			this.setCurrentRound(1);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// **********************Methods************************************************************
	public static Game getInstance()
	{
		return instance;
	}

	public static Game getInstance(int userAmount)
	{
		if (instance == null)
		{
			instance = new Game(userAmount);
			DAOGame.createInitialData(userAmount);

			ArrayList<IUser> tmpList = DAOGame.getAllUsers();
			for (int i = 0; i < tmpList.size(); i++)
				Game.addGroup((Group) tmpList.get(i));
		}
		return instance;
	}

	// by Alex
	public static void addGroup(Group gr)
	{
		Game.groupList.add(gr);
	}

	// by Alex
	public double[] calculateIndexSums()
	{
		double[] indexSums = new double[3];
		for (int i = 0; i < groupList.size(); i++)
		{
			Company com = groupList.get(i).getCompany();
			for (int a = 0; a < com.getToasterList().size(); a++)
			{
				if (com.getToasterList().get(a).getType() == Type.TYPE1)
					indexSums[0] += com.getToasterList().get(a).getIndex();
				if (com.getToasterList().get(a).getType() == Type.TYPE2)
					indexSums[1] += com.getToasterList().get(a).getIndex();
				if (com.getToasterList().get(a).getType() == Type.TYPE3)
					indexSums[2] += com.getToasterList().get(a).getIndex();
			}
		}
		// runden auf zwei Stellen
		for (int i = 0; i < indexSums.length; i++)
			indexSums[i] = NumberUtil.roundDouble(indexSums[i]);

		return indexSums;
	}

	// public void changePrice(Company company, Toaster toaster, double price)
	// throws Exception
	// {
	//
	// company.getToasterList().get(company.getToasterList().indexOf(toaster)).setPrice(price);
	//
	// }

	// Methode Create Initial Data
	/*
	 * private ArrayList<Company> createInitialData(int userAmount, double
	 * initialPrice1, int initialMarketing1, int initialResearch1, double
	 * initialPrice2, int initialMarketing2, int initialResearch2, double
	 * initialPrice3, int initialMarketing3, int initialResearch3, String
	 * initialDescription) { Type Typ1 = Type.TYPE1; Type Typ2 = Type.TYPE2;
	 * Type Typ3 = Type.TYPE3; Toaster Toaster1; Toaster Toaster2; Toaster
	 * Toaster3; ArrayList<Toaster> toasterList; ArrayList<Company>
	 * companyList = new ArrayList<Company>(); for (int i = 1; i == userAmount;
	 * i++) { Toaster1 = new Toaster(initialPrice1, initialMarketing1,
	 * initialResearch1, 0.00, 0.00, 0.00, 0.00, 0, Typ1); Toaster2 = new
	 * Toaster(initialPrice2, initialMarketing2, initialResearch2, 0.00, 0.00,
	 * 0.00, 0.00, 0, Typ2); Toaster3 = new Toaster(initialPrice3,
	 * initialMarketing3, initialResearch3, 0.00, 0.00, 0.00, 0.00, 0, Typ3);
	 * toasterList = new ArrayList<Toaster>(); toasterList.add(Toaster1);
	 * toasterList.add(Toaster2); toasterList.add(Toaster3); companyList.add(new
	 * Company(initialDescription, 0.00, 0.00, 0.00, 0.00, 0, toasterList)); }
	 * return companyList; }
	 */

	public int getCurrentRound()
	{
		return currentRound;
	}

	public int getUserAmount()
	{
		return userAmount;
	}

	public static ArrayList<Group> getGroupList()
	{
		return groupList;
	}

	public static void setGroupList(ArrayList<Group> groupList)
	{
		Game.groupList = groupList;
	}

	// @ by Alex
	public void setCurrentRound(int currentRound) throws Exception
	{
		if (currentRound <= this.currentRound)
		{
			throw new Exception("Invalid Input. Current round is bigger than the requested");
		} else
		{
			this.currentRound = currentRound;
		}
	}

	public void setUserAmount(int userAmount)
	{
		this.userAmount = userAmount;
	}

	// @by Alex
	public void simulate()
	{
		double[] indexSums = new double[3];

		for (int a = 0; a < groupList.size(); a++)
		{
			groupList.get(a).getCompany().calculateIndex();
		}
		indexSums = calculateIndexSums();

		for (int a = 0; a < groupList.size(); a++)
		{
			groupList.get(a).getCompany().calculateMarketShares(indexSums);
			groupList.get(a).getCompany().calculateTurnover();
			groupList.get(a).getCompany().calculateCost();
			groupList.get(a).getCompany().calculateProfit();
			groupList.get(a).getCompany().calculateCapital();
		}
		// ermittle marktforschungsbericht und gebe ihn als objectref
		// zurück
		MarketResearchReport report = new MarketResearchReport();
		report.generateMarketResearchReport(groupList);
		for (int a = 0; a < groupList.size(); a++)
		{
			groupList.get(a).getCompany().setMarketResearchReport(null);
			// Erstelle Markforschungsbericht wenn erwünscht
			if (groupList.get(a).getCompany().isMarketResearchReportON() == true)
			{
				// weise der gruppe die referenz zu
				groupList.get(a).getCompany().setMarketResearchReport(report);
			}
			// Marktforschungsbericht grundsätzlich auschecken
			groupList.get(a).getCompany().setMarketResearchReportON(false);
		}
	}

	public static boolean isGameStarted()
	{
		return DAOGame.isGameStarted();
	}

	public String toString()
	{
		String s = "Game Eigenschaften: \n user amount: \t \t" + this.getUserAmount() + "\n current round: \t" + this.getCurrentRound();
		return s;
	}

}
