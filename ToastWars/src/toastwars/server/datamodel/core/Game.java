package toastwars.server.datamodel.core;

import java.util.ArrayList;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;
import toastwars.util.NumberUtil;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Game implements IsSerializable
{
	// Attribute

	private static Game instance;

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

	public static void destroyGame()
	{
		instance = null;
	}

	public static Game getInstance()
	{
		return instance;
	}

	private Game(int userAmount)
	{
		this.userAmount = userAmount;
		this.setCurrentRound(1);
	}

	// Getter

	public static Game getInstance(int userAmount)
	{
		if (instance == null)
		{
			instance = new Game(userAmount);
		}
		return instance;
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

	// Setter
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

	// Other Methods

	// Hinzufügen einer Gruppe zum Spiel
	public void addGroup(Group gr)
	{
		groupList.add(gr);
	}

	// In dieser Methode werden die Indexsummen der einzelnen Toaster berechnet.
	// Diese werden zur
	// der einzelnen Marktanteile benötigt.
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

	// Hier wird die Runde eines Spiels abgeschlossen.
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
		Toaster toaster;
		Company company;
		int idx = 0;

		while (again)
		{
			again = false;
			// Schleife über alle Companies
			for (int i = 0; i < groupList.size(); i++)
			{
				company = groupList.get(i).getCompany();

				// Schleife über alle Toaster der Company
				for (int j = 0; j < company.getToasterList().size(); j++)
				{
					toaster = company.getToasterList().get(j);

					if (idx == 0)
						toaster.setTmpProduction(toaster.getProduction());

					// Wenn weniger produziert wurde als abgesetzt werden kann,
					// muss zunächst geprüft werden, ob die entsprechende Firma
					// den Bedarf aus ihrem Lager decken kann. Ist dich nicht
					// der Fall, wird versucht den anderen Firmen die
					// Marktanteile
					// an dem jeweiligen Toaster zu geben.

					if (toaster.getProduction() < toaster.getMarketShare())
					{
						int missing = toaster.getMarketShare() - toaster.getProduction();

						int stock = 0;

						// edited by Waldi
						stock = company.getStock().getStock(toaster.getType());

						// Wenn der Lagerbestand ausreicht um den Bedarf zu
						// decken, wird das benötigte aus dem Lager entfernt
						// und das "production" Attribut entsprechend angepasst.

						if (stock >= missing)
						{
							company.getStock().ReduceStock(toaster.getType(), missing);
							toaster.setProduction(toaster.getMarketShare());
						}

						// Wenn der Lagerbstand nun nicht ausreicht, um den
						// Bedarf zu decken, so wird soviel wie möglich aus dem
						// Lagerbedarft gedeckt.
						// Die restlichen Toaster, die auf dem Markt abgesetzt
						// werden können, werden versucht an die anderen Firmen
						// weiterzugeben.
						// Dies geschieht der Reihenfolge nach dem Index der
						// Unternehmen sortiert, welche sortiert in der
						// "sortedIndexList" zu finden sind.

						if (stock < missing)

						{
							ArrayList<Number> sortedIndexListTyp = null;

							int unavailable = missing - stock;

							company.getStock().ReduceStock(toaster.getType(), stock);
							toaster.setMarketShare(toaster.getProduction() + stock);
							toaster.setProduction(toaster.getMarketShare());

							// Es wird versucht die Marktanteile auf andere
							// Companies zu verteilen, sortiert nach Index
							// (sortedIndexList).
							// Nun muss zwischen Typen differenziert werden.

							if (toaster.getType() == Type.TYPE1)
							{
								sortedIndexListTyp = sortedIndexListTyp1;
							}
							if (toaster.getType() == Type.TYPE2)
							{
								sortedIndexListTyp = sortedIndexListTyp2;
							}
							if (toaster.getType() == Type.TYPE3)
							{
								sortedIndexListTyp = sortedIndexListTyp3;
							}

							// Schleife über alle Companies
							for (int k = 0; k < sortedIndexListTyp.size(); k++)
							{

								// TODO
								Company company2 = groupList.get(sortedIndexListTyp.get(k).intValue() - 1).getCompany();
								Toaster toaster2 = groupList.get(sortedIndexListTyp.get(k).intValue() - 1).getCompany().getToasterList().get(j);

								// edited by Waldi
								int stockValue = company2.getStock().getStock(toaster.getType());
								int marketShare = toaster2.getMarketShare();
								int production = toaster2.getProduction();

								// Wenn die folgende Company alle Toaster
								// die noch fehlen zusätzlich absetzten
								// kann.
								if (stockValue + (production - marketShare) >= unavailable)
								{

									toaster2.setMarketShare(unavailable + marketShare);
									unavailable = 0;

									again = true;
									break;
								}
								// Falls diese einen Teil übernehmen kann.
								else if (stockValue + production - marketShare > 0)
								{
									unavailable = unavailable - (stockValue + (production - marketShare));
									toaster2.setMarketShare(stockValue + production);
									// Wenn eine derartige Umverteilung des
									// Marketshares statt findet, muss
									// garantiert werden, dass
									// die Company die den erhöhten
									// MarketShare hat und die Toaster
									// gegebenenfalls auf Lager noch einmal
									// von diesem Algortihmus geprüft wird.
									again = true;

								}// else

							}// for

						}// If Gesamt
					}// if Unterproduktion
				}// for innen
			}// for außen
			idx++;
		}// while

		// Wenn mehr produziert wurde als abgesetzt werden kann,
		// wird dies in dieser Abfrage festgetellt und die entsprechende Menge
		// eingelagert
		// und das Attribut der "production" auf den Wert von des "marketshare"
		// gesetzt.
		// Schleife über alle Companies
		for (int i = 0; i < groupList.size(); i++)
		{
			// Schleife über alle Toaster der Company
			for (int j = 0; j < groupList.get(i).getCompany().getToasterList().size(); j++)
			{
				company = groupList.get(i).getCompany();
				toaster = company.getToasterList().get(j);

				if (toaster.getProduction() > toaster.getMarketShare())
				{
					int differenz = toaster.getProduction() - toaster.getMarketShare();
					company.getStock().StockUp(toaster.getType(), differenz);
					toaster.setProduction(toaster.getMarketShare());
				}// if Überproduktion

				toaster.setProduction(toaster.getTmpProduction());
			}// for innen
		}// for außen
	}// calculate MarketShares

	// Hier wird eine Spielrunde simuliert. Hierzu werden alle Berechnungen
	// angestoßen
	// und alle erforderlichen Objektattribute der Toaster und Companys gesetzt.
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
					groupList.get(a).setInactive();
					groupList.get(a).killGroup();
				} else
					groupList.get(a).setStarted();
			}
		}
	}

	public String toString()
	{
		String s = "Game Eigenschaften: \n user amount: \t \t" + this.getUserAmount() + "\n current round: \t" + this.getCurrentRound();
		return s;
	}

	public Game clone()
	{
		Game game = new Game();
		game.setCurrentRound(currentRound);
		game.setUserAmount(userAmount);
		return game;
	}
}
