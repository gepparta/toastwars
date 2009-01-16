package toastwars.server.datamodel.core;

import java.util.ArrayList;
import toastwars.server.datamodel.user.Group;
import toastwars.util.NumberUtil;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Game implements IsSerializable
{
	private static Game instance;
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
		}
		return instance;
	}
	private int userAmount;

	private int currentRound = 1;

	// @gwt.typeArgs <toastwars.server.datamodel.user.Group>
	private ArrayList<Group> groupList = new ArrayList<Group>();

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

	// by Alex
	public void addGroup(Group gr)
	{
		groupList.add(gr);
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

	public int getCurrentRound()
	{
		return currentRound;
	}

	public ArrayList<Group> getGroupList()
	{
		return groupList;
	}

	public int getUserAmount()
	{
		return userAmount;
	}

	// @ by Alex
	public void setCurrentRound(int currentRound) throws Exception
	{
		if (currentRound < this.currentRound)
		{
			throw new Exception("Invalid Input. Current round is bigger than the requested");
		} else
		{
			this.currentRound = currentRound;
		}
	}

	public void setGroupList(ArrayList<Group> groupList)
	{
		this.groupList = groupList;
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

//	public static boolean isGameStarted()
//	{
//		return DAOGame.isGameStarted();
//	}

	public String toString()
	{
		String s = "Game Eigenschaften: \n user amount: \t \t" + this.getUserAmount() + "\n current round: \t" + this.getCurrentRound();
		return s;
	}
}
