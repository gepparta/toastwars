package toastwars.server.datamodel.core;

import java.util.ArrayList;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;
import toastwars.util.NumberUtil;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Game implements IsSerializable
{
	private static Game instance;
	// @gwt.typeArgs <Number>
	private ArrayList<Number> sortedIndexListTyp1 = null;
	// @gwt.typeArgs <Number>
	private ArrayList<Number> sortedIndexListTyp2 = null;
	// @gwt.typeArgs <Number>
	private ArrayList<Number> sortedIndexListTyp3 = null;

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
		this.setCurrentRound(1);
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
		}
		return instance;
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

	public void completeRound(Group group)
	{

		for (Group g : groupList)
		{
			if (g.getUsername().equals(group.getUsername()))
			{
				group.getCompany().accumulateToasterValues();
				return;
			}
		}
	}

	public int getCurrentRound()
	{
		return currentRound;
	}

	public ArrayList<Group> getGroupList()
	{
		return groupList;
	}

	public ArrayList<Number> getSortedIndexListTyp1()
	{
		return sortedIndexListTyp1;
	}

	public ArrayList<Number> getSortedIndexListTyp2()
	{
		return sortedIndexListTyp2;
	}

	public ArrayList<Number> getSortedIndexListTyp3()
	{
		return sortedIndexListTyp3;
	}

	public int getUserAmount()
	{
		return userAmount;
	}

	// @ by Alex
	public void setCurrentRound(int currentRound)
	{
		this.currentRound = currentRound;
	}

	public void setGroupList(ArrayList<Group> groupList)
	{
		this.groupList = groupList;
	}

	public void setSortedIndexListTyp1(ArrayList<Number> sortedIndexListTyp1)
	{
		this.sortedIndexListTyp1 = sortedIndexListTyp1;
	}

	public void setSortedIndexListTyp2(ArrayList<Number> sortedIndexListTyp2)
	{
		this.sortedIndexListTyp2 = sortedIndexListTyp2;
	}

	public void setSortedIndexListTyp3(ArrayList<Number> sortedIndexListTyp3)
	{
		this.sortedIndexListTyp3 = sortedIndexListTyp3;
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

			groupList.get(a).setStatus(Status.STARTED);
		}

		int round = Game.getInstance().getCurrentRound();
		Game.getInstance().setCurrentRound(round + 1);

	}

	public String toString()
	{
		String s = "Game Eigenschaften: \n user amount: \t \t" + this.getUserAmount() + "\n current round: \t" + this.getCurrentRound();
		return s;
	}
	
}
