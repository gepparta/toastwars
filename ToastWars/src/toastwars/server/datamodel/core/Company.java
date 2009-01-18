package toastwars.server.datamodel.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import toastwars.server.datamodel.user.Group;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Company implements IsSerializable
{

	private static int nextCompanyID = 1;

	public static int getNextCompanyID()
	{
		return nextCompanyID;
	}

	public static void setNextCompanyID(int nextCompanyID)
	{
		Company.nextCompanyID = nextCompanyID;
	}

	private int companyID;
	private double turnover;
	private double cost;
	private double profit;
	private double capital;
	private int marketShare;
	private boolean marketResearchReportON;
	// @gwt.typeArgs <Number>
	private ArrayList<Number> profitRankingList = null;
	// @gwt.typeArgs <Number>
	private ArrayList<Number> capitalRankingInternList = null;

	// @gwt.typeArgs
	// java.util.ArrayList<java.util.ArrayList<java.util.List<java.lang.String>>>
	public ArrayList<List<String>> reportListe;

	// @gwt.typeArgs <toastwars.server.datamodel.core.Toaster>
	private ArrayList<Toaster> toasterList = new ArrayList<Toaster>();

	public Company()
	{
	}

	public Company(double turnover, double cost, double profit, double capital, int marketShare, ArrayList<Toaster> toasterList)
	{
		this.companyID = nextCompanyID;
		nextCompanyID++;

		this.turnover = turnover;
		this.cost = cost;
		this.profit = profit;
		this.capital = capital;
		this.marketShare = marketShare;
		this.toasterList = toasterList;
		reportListe = new ArrayList<List<String>>();
	}

	public Company(int companyID, double turnover, double cost, double profit, double capital, int marketShare, ArrayList<Toaster> toasterList)
	{
		this.companyID = companyID;

		this.turnover = turnover;
		this.cost = cost;
		this.profit = profit;
		this.capital = capital;
		this.marketShare = marketShare;
		this.toasterList = toasterList;
		reportListe = new ArrayList<List<String>>();

	}

	public void calculateCapital()
	{

		this.capital = this.capital + this.profit;

	}

	public void calculateCost()
	{
		this.cost = 0;
		for (int i = 0; i < this.toasterList.size(); i++)
		{

			toasterList.get(i).calculateCost();
			this.cost = this.cost + toasterList.get(i).getCost();

		}
	}

	// @by Alex
	public void calculateIndex()
	{
		for (int i = 0; i < toasterList.size(); i++)
		{
			toasterList.get(i).calculateIndex();
		}
	}

	// @by Alex extra for testing
	public void calculateIndexWithOutRandom()
	{
		for (int i = 0; i < toasterList.size(); i++)
		{
			toasterList.get(i).calculateIndexWithOutRandom();
		}
	}

	public void calculateMarketShares(double[] indexSums)
	{
		this.marketShare = 0;
		for (int i = 0; i < this.toasterList.size(); i++)
		{

			toasterList.get(i).calculateMarketShare(indexSums[i]);
			this.marketShare = this.marketShare + toasterList.get(i).getMarketShare();
		}
	}

	public void calculateProfit()
	{
		this.profit = 0;
		for (int i = 0; i < this.toasterList.size(); i++)
		{

			toasterList.get(i).calculateProfit();
			this.profit = this.profit + toasterList.get(i).getProfit();

		}

	}

	public void calculateTurnover()
	{
		this.turnover = 0;
		for (int i = 0; i < this.toasterList.size(); i++)
		{

			toasterList.get(i).calculateTurnover();
			this.turnover = this.turnover + toasterList.get(i).getTurnover();
		}
	}

	public void accumulateToasterValues()
	{
		// kommulieren der investitionen des users
		// wird f�r die exakte berechnung beim simulieren ben�tigt
		for (int i = 0; i < this.getToasterList().size(); i++)
		{
			this.getToasterList().get(i).setDesignInvestmentKum();
			this.getToasterList().get(i).setEcologyInvestmentKum();
			this.getToasterList().get(i).setNewspaperInvestmentKum();
			this.getToasterList().get(i).setQualityInvestmentKum();
			this.getToasterList().get(i).setRadioInvestmentKum();
			this.getToasterList().get(i).setTvInvestmentKum();
		}
	}

	public double getCapital()
	{
		return capital;
	}

	public int getCompanyID()
	{
		return companyID;
	}

	public double getCost()
	{
		return cost;
	}

	public int getMarketShare()
	{
		return marketShare;
	}

	public double getProfit()
	{
		return profit;
	}

	public ArrayList<Toaster> getToasterList()
	{
		return toasterList;
	}

	public double getTurnover()
	{
		return turnover;
	}

	// //// METHODS ///////

	public boolean isMarketResearchReportON()
	{
		return marketResearchReportON;
	}

	public void setCapital(double capital)
	{
		this.capital = capital;
	}

	public void setCompanyID(int companyID)
	{
		this.companyID = companyID;
	}

	public void setCost(double cost)
	{
		this.cost = cost;
	}

	public void setMarketResearchReportON(boolean marketResearchReportON)
	{
		this.marketResearchReportON = marketResearchReportON;
	}

	public void setMarketShare(int marketShare)
	{
		this.marketShare = marketShare;
	}

	public void setProfit(double profit)
	{
		this.profit = profit;
	}

	public void setToasterList(ArrayList<Toaster> toasterList)
	{
		this.toasterList = toasterList;
	}

	public void setTurnover(double turnover)
	{
		this.turnover = turnover;
	}

	public String toString()
	{

		String s = "Company Eigenschaften: \n companyID \t \t" + this.getCompanyID() + "\n turnover: \t" + this.getTurnover() + "\n cost: \t" + this.getCost() + "\n profit: \t"
				+ this.getProfit() + "\n capital: \t" + this.getCapital() + "\n market share: \t" + this.getMarketShare();
		return s;
	}

	public ArrayList<List<String>> getReportListe()
	{
		return reportListe;
	}

	public void setReportListe(ArrayList<List<String>> reportListe)
	{
		this.reportListe = reportListe;
	}



	/*
	 * Diese Methode bereitet die Daten f�r die Kapitalanzeige im Analysebericht
	 * vor Dabei wird der Gruppenname durch "Ihre Gruppe" ersetzt
	 */
	public ArrayList<Number> getProfitOverview(Game game)
	{
		ArrayList<Number> liste = new ArrayList<Number>();
		ArrayList<Group> grListe = game.getGroupList();
		for (int i = 0; i < grListe.size(); i++)
		{
			liste = this.getProfitRankingList();
		}
		return liste;
	}

	public ArrayList<Number> getCapitalOverview(Game game)
	{
		ArrayList<Number> liste = new ArrayList<Number>();
		ArrayList<Group> grListe = game.getGroupList();
		for (int i = 0; i < grListe.size(); i++)
		{
			liste = this.getCapitalRankingInternList();
		}
		return liste;
	}

	// @gwt.typeArgs <Number>
	public ArrayList<Number> getProfitRankingList()
	{
		return profitRankingList;
	}

	public void setProfitRankingList(ArrayList<Number> profitRankingList)
	{
		this.profitRankingList = profitRankingList;
	}

	public ArrayList<Number> getCapitalRankingInternList()
	{
		return capitalRankingInternList;
	}

	public void setCapitalRankingInternList(ArrayList<Number> capitalRankingInternList)
	{
		this.capitalRankingInternList = capitalRankingInternList;
	}

}