package toastwars.server.datamodel.core;

import java.util.ArrayList;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;
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

	// @gwt.typeArgs <Number>
	private ArrayList<Number> sortedIndexListTyp1 = null;
	// @gwt.typeArgs <Number>
	private ArrayList<Number> sortedIndexListTyp2 = null;
	// @gwt.typeArgs <Number>
	private ArrayList<Number> sortedIndexListTyp3 = null;

	// *************************Constructor*****************************************************
	public Game()
	{
	}

	private Game(int userAmount)
	{
		this.userAmount = userAmount;
		this.setCurrentRound(1);
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
	public void setCurrentRound(int currentRound)
	{
		this.currentRound = currentRound;
	}

	public void setGroupList(ArrayList<Group> groupList)
	{
		this.groupList = groupList;
	}

	public void setUserAmount(int userAmount)
	{
		this.userAmount = userAmount;
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

	/*
	 * Diese Methode berechnet die tatsächlichen Marktanteile unter
	 * Berücksichtigung und Aktualisierung der Lagerkapazitäten
	 */
	public void correctMarketShares()
	{
		boolean again = true;
		while (again)
		{
			again = false;
			for (int i = 0; i < groupList.size(); i++)
			{
				for (int j = 0; j < groupList.get(i).getCompany().getToasterList().size(); j++)
				{

					// Bei Überproduktion Einlagern
					if (groupList.get(i).getCompany().getToasterList().get(j).getProduction() > groupList.get(i).getCompany().getToasterList().get(j)
							.getMarketShare())
					{
						if (groupList.get(i).getCompany().getToasterList().get(j).getType() == Type.TYPE1)
						{
							int differenz = groupList.get(i).getCompany().getToasterList().get(j).getProduction()
									- groupList.get(i).getCompany().getToasterList().get(j).getMarketShare();

							groupList.get(i).getCompany().getStock().StockUp(Type.TYPE1, differenz);
							groupList.get(i).getCompany().getToasterList().get(j).setProduction(
									groupList.get(i).getCompany().getToasterList().get(j).getMarketShare());
						}

						if (groupList.get(i).getCompany().getToasterList().get(j).getType() == Type.TYPE2)
						{
							int differenz = groupList.get(i).getCompany().getToasterList().get(j).getProduction()
									- groupList.get(i).getCompany().getToasterList().get(j).getMarketShare();

							groupList.get(i).getCompany().getStock().StockUp(Type.TYPE2, differenz);
							groupList.get(i).getCompany().getToasterList().get(j).setProduction(
									groupList.get(i).getCompany().getToasterList().get(j).getMarketShare());
						}

						if (groupList.get(i).getCompany().getToasterList().get(j).getType() == Type.TYPE3)
						{
							int differenz = groupList.get(i).getCompany().getToasterList().get(j).getProduction()
									- groupList.get(i).getCompany().getToasterList().get(j).getMarketShare();

							groupList.get(i).getCompany().getStock().StockUp(Type.TYPE3, differenz);
							groupList.get(i).getCompany().getToasterList().get(j).setProduction(
									groupList.get(i).getCompany().getToasterList().get(j).getMarketShare());
						}
					}// if Überproduktion

					// Wenn Unterproduktion vorliegt

					if (groupList.get(i).getCompany().getToasterList().get(j).getProduction() < groupList.get(i).getCompany().getToasterList().get(j)
							.getMarketShare())
					{
						int missing = groupList.get(i).getCompany().getToasterList().get(j).getMarketShare()
								- groupList.get(i).getCompany().getToasterList().get(j).getProduction();

						int stock = 0;

						if (groupList.get(i).getCompany().getToasterList().get(j).getType() == Type.TYPE1)
						{
							stock = groupList.get(i).getCompany().getStock().getStockTT1();
						}
						if (groupList.get(i).getCompany().getToasterList().get(j).getType() == Type.TYPE2)
						{
							stock = groupList.get(i).getCompany().getStock().getStockTT2();
						}
						if (groupList.get(i).getCompany().getToasterList().get(j).getType() == Type.TYPE3)
						{
							stock = groupList.get(i).getCompany().getStock().getStockTT3();
						}

						if (stock >= missing)
						{
							groupList.get(i).getCompany().getStock().ReduceStock(groupList.get(i).getCompany().getToasterList().get(j).getType(),
									missing);
						}

						if (stock < missing)

						{
							int unavailable = missing - stock;

							groupList.get(i).getCompany().getStock().ReduceStock(groupList.get(i).getCompany().getToasterList().get(j).getType(),
									stock);
							// FIXE PROGRAMMIERUNG IN TOASTERLIST
							if (groupList.get(i).getCompany().getToasterList().get(j).getType() == Type.TYPE1)
							{
								groupList.get(i).getCompany().getToasterList().get(j).setMarketShare(
										groupList.get(i).getCompany().getToasterList().get(j).getMarketShare() - unavailable);
							}
							if (groupList.get(i).getCompany().getToasterList().get(j).getType() == Type.TYPE2)
							{
								groupList.get(i).getCompany().getToasterList().get(j).setMarketShare(
										groupList.get(i).getCompany().getToasterList().get(j).getMarketShare() - unavailable);
							}
							if (groupList.get(i).getCompany().getToasterList().get(j).getType() == Type.TYPE3)
							{
								groupList.get(i).getCompany().getToasterList().get(j).setMarketShare(
										groupList.get(i).getCompany().getToasterList().get(j).getMarketShare() - unavailable);
							}

							if (groupList.get(i).getCompany().getToasterList().get(j).getType() == Type.TYPE1)
							{
								for (int k = 0; k < sortedIndexListTyp1.size(); k++)
								{
									if (groupList.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany().getStock().getStockTT1()
											+ (groupList.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany().getToasterList().get(j)
													.getProduction() - groupList.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany()
													.getToasterList().get(j).getMarketShare()) >= unavailable)
									{

										groupList.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany().getToasterList().get(j).setMarketShare(
												unavailable
														+ groupList.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany().getToasterList().get(
																j).getMarketShare());
										unavailable = 0;

										again = true;
										break;
									}

									else if (groupList.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany().getStock().getStockTT1()
											+ groupList.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany().getToasterList().get(j)
													.getProduction()
											- groupList.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany().getToasterList().get(j)
													.getMarketShare() > 0

									)

									{
										unavailable = unavailable
												- (groupList.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany().getStock().getStockTT1() + (groupList
														.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany().getToasterList().get(j)
														.getProduction() - groupList.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany()
														.getToasterList().get(j).getMarketShare()));

										groupList.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany().getToasterList().get(j).setMarketShare(
												groupList.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany().getToasterList().get(j)
														.getMarketShare()
														+ (groupList.get(sortedIndexListTyp1.get(k).intValue() - 1).getCompany().getStock()
																.getStockTT1() + (groupList.get(sortedIndexListTyp1.get(k).intValue() - 1)
																.getCompany().getToasterList().get(j).getProduction() - groupList.get(
																sortedIndexListTyp1.get(k).intValue() - 1).getCompany().getToasterList().get(j)
																.getMarketShare())));

										again = true;

									}// else

								}// for

							}// if ToasterTyp

							if (groupList.get(i).getCompany().getToasterList().get(j).getType() == Type.TYPE2)
							{
								for (int k = 0; k < sortedIndexListTyp2.size(); k++)
								{
									if (groupList.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany().getStock().getStockTT2()
											+ (groupList.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany().getToasterList().get(j)
													.getProduction() - groupList.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany()
													.getToasterList().get(j).getMarketShare()) >= unavailable)
									{

										groupList.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany().getToasterList().get(j).setMarketShare(
												unavailable
														+ groupList.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany().getToasterList().get(
																j).getMarketShare());
										unavailable = 0;

										again = true;
										break;
									}

									else if (groupList.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany().getStock().getStockTT2()
											+ groupList.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany().getToasterList().get(j)
													.getProduction()
											- groupList.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany().getToasterList().get(j)
													.getMarketShare() > 0

									)

									{
										unavailable = unavailable
												- (groupList.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany().getStock().getStockTT2() + (groupList
														.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany().getToasterList().get(j)
														.getProduction() - groupList.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany()
														.getToasterList().get(j).getMarketShare()));

										groupList.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany().getToasterList().get(j).setMarketShare(
												groupList.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany().getToasterList().get(j)
														.getMarketShare()
														+ (groupList.get(sortedIndexListTyp2.get(k).intValue() - 1).getCompany().getStock()
																.getStockTT2() + (groupList.get(sortedIndexListTyp2.get(k).intValue() - 1)
																.getCompany().getToasterList().get(j).getProduction() - groupList.get(
																sortedIndexListTyp2.get(k).intValue() - 1).getCompany().getToasterList().get(j)
																.getMarketShare())));

										again = true;

									}// else

								}// for

							}// if ToasterTyp

							if (groupList.get(i).getCompany().getToasterList().get(j).getType() == Type.TYPE3)
							{
								for (int k = 0; k < sortedIndexListTyp3.size(); k++)
								{
									if (groupList.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany().getStock().getStockTT3()
											+ (groupList.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany().getToasterList().get(j)
													.getProduction() - groupList.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany()
													.getToasterList().get(j).getMarketShare()) >= unavailable)
									{

										groupList.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany().getToasterList().get(j).setMarketShare(
												unavailable
														+ groupList.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany().getToasterList().get(
																j).getMarketShare());
										unavailable = 0;

										again = true;
										break;
									}

									else if (groupList.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany().getStock().getStockTT3()
											+ groupList.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany().getToasterList().get(j)
													.getProduction()
											- groupList.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany().getToasterList().get(j)
													.getMarketShare() > 0

									)

									{
										unavailable = unavailable
												- (groupList.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany().getStock().getStockTT3() + (groupList
														.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany().getToasterList().get(j)
														.getProduction() - groupList.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany()
														.getToasterList().get(j).getMarketShare()));

										groupList.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany().getToasterList().get(j).setMarketShare(
												groupList.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany().getToasterList().get(j)
														.getMarketShare()
														+ (groupList.get(sortedIndexListTyp3.get(k).intValue() - 1).getCompany().getStock()
																.getStockTT3() + (groupList.get(sortedIndexListTyp3.get(k).intValue() - 1)
																.getCompany().getToasterList().get(j).getProduction() - groupList.get(
																sortedIndexListTyp3.get(k).intValue() - 1).getCompany().getToasterList().get(j)
																.getMarketShare())));

										again = true;

									}// else

								}// for

							}// if ToasterTyp

						}// If Gesamt

					}// if Unterproduktion
				}// for innen

			}// for außen

		}
	}// calculate MarketShares

	// @by Alex
	public void simulate()
	{
		double[] indexSums = new double[3];

		indexSums = calculateIndexSums();

		for (int a = 0; a < groupList.size(); a++)
		{
			if (groupList.get(a).getStatus() != Status.INACTIVE)
				groupList.get(a).getCompany().calculateMarketShares(indexSums);
		}

		this.correctMarketShares();

		for (int a = 0; a < groupList.size(); a++)
		{
			if (groupList.get(a).getStatus() != Status.INACTIVE)
			{
				groupList.get(a).getCompany().calculateTurnover();
				groupList.get(a).getCompany().calculateCost();
				groupList.get(a).getCompany().calculateProfit();
				groupList.get(a).getCompany().calculateCapital();

				if (groupList.get(a).getCompany().getCapital() <= 0)
				{
					groupList.get(a).setStatus(Status.INACTIVE);
					this.killGroup(groupList.get(a));
				} else
					groupList.get(a).setStatus(Status.STARTED);
			}
		}

		int round = Game.getInstance().getCurrentRound();
		Game.getInstance().setCurrentRound(round + 1);

	}

	public String toString()
	{
		String s = "Game Eigenschaften: \n user amount: \t \t" + this.getUserAmount() + "\n current round: \t" + this.getCurrentRound();
		return s;
	}

	public ArrayList<Number> getSortedIndexListTyp1()
	{
		return sortedIndexListTyp1;
	}

	public void setSortedIndexListTyp1(ArrayList<Number> sortedIndexListTyp1)
	{
		this.sortedIndexListTyp1 = sortedIndexListTyp1;
	}

	public ArrayList<Number> getSortedIndexListTyp2()
	{
		return sortedIndexListTyp2;
	}

	public void setSortedIndexListTyp2(ArrayList<Number> sortedIndexListTyp2)
	{
		this.sortedIndexListTyp2 = sortedIndexListTyp2;
	}

	public ArrayList<Number> getSortedIndexListTyp3()
	{
		return sortedIndexListTyp3;
	}

	public void setSortedIndexListTyp3(ArrayList<Number> sortedIndexListTyp3)
	{
		this.sortedIndexListTyp3 = sortedIndexListTyp3;
	}

	public void killGroup(Group group)
	{
		group.getCompany().setTurnover(0.0);
		group.getCompany().setProfit(0.0);
		group.getCompany().setCost(0.0);
		group.getCompany().setMarketShare(0);
		for (int i = 0; i < group.getCompany().getToasterList().size(); i++)
		{
			group.getCompany().getToasterList().get(i).setCost(0.0);
			group.getCompany().getToasterList().get(i).setDesignInvestment(0);
			group.getCompany().getToasterList().get(i).setEfficiencyInvestment(0.0);
			group.getCompany().getToasterList().get(i).setIndex(0.0);
			group.getCompany().getToasterList().get(i).setMarketing(0.0);
			group.getCompany().getToasterList().get(i).setMarketShare(0);
			group.getCompany().getToasterList().get(i).setNewspaperInvestment(0.0);
			group.getCompany().getToasterList().get(i).setProduction(0);
			group.getCompany().getToasterList().get(i).setProfit(0.0);
			group.getCompany().getToasterList().get(i).setQualityInvestment(0.0);
			group.getCompany().getToasterList().get(i).setRadioInvestment(0.0);
			group.getCompany().getToasterList().get(i).setResearch(0.0);
			group.getCompany().getToasterList().get(i).setTurnover(0.0);
			group.getCompany().getToasterList().get(i).setTvInvestment(0.0);
		}
	}
}
