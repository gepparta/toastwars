package toastwars.server.datamodel.core;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.user.client.rpc.IsSerializable;
/*
 * @ author Michael Klein, Alexander Geppart
 */
public class Company implements IsSerializable
{

	//Deklaration der Attribute

	private static int nextCompanyID = 1;

	private int companyID;
	private double turnover;
	private double cost;
	private double profit;
	private double capital;
	private int marketShare;
	private boolean marketResearchReportON;
	private Stock stock;
	// @gwt.typeArgs <Number>
	private ArrayList<Number> profitRankingList = null;
	// @gwt.typeArgs <Number>
	private ArrayList<Number> capitalRankingInternList = null;
	// @gwt.typeArgs
	// java.util.ArrayList<java.util.ArrayList<java.util.ArrayList<java.util.List<java.lang.String>>>>
	public ArrayList<ArrayList<List<String>>> reportListe;
	// @gwt.typeArgs <toastwars.server.datamodel.core.Toaster>
	private ArrayList<Toaster> toasterList = new ArrayList<Toaster>();

	//Konstruktoren
	public Company()
	{
	}

	public Company(double turnover, double cost, double profit, double capital, int marketShare, Stock stock, ArrayList<Toaster> toasterList)
	{
		this.companyID = nextCompanyID;
		nextCompanyID++;

		this.turnover = turnover;
		this.cost = cost;
		this.profit = profit;
		this.capital = capital;
		this.marketShare = marketShare;
		this.stock = stock;
		this.toasterList = toasterList;
		reportListe = new ArrayList<ArrayList<List<String>>>();
	}

	public Company(int companyID, double turnover, double cost, double profit, double capital, int marketShare, Stock stock,
			ArrayList<Toaster> toasterList)
	{
		this.companyID = companyID;

		this.turnover = turnover;
		this.cost = cost;
		this.profit = profit;
		this.capital = capital;
		this.marketShare = marketShare;
		this.stock = stock;
		this.toasterList = toasterList;
		reportListe = new ArrayList<ArrayList<List<String>>>();

	}



	public static int getNextCompanyID()
	{
		return nextCompanyID;
	}

	public static void setNextCompanyID(int nextCompanyID)
	{
		Company.nextCompanyID = nextCompanyID;
	}

	// In dieser Methode werden die Investitionen dieser Company für alle Toaster kumuliert.
	public void accumulateToasterValues()
	{

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

	// In dieser Methode wird der Gewinn zum Kapital addiert.
	public void calculateCapital()
	{

		this.capital = this.capital + this.profit;

	}

	// In dieser Methode werden alle Kosten der einzelnen Toaster ermittelt, diese addiert und das 
	// Attribut der Company gesetzt.
	public void calculateCost()
	{
		double tmpCost = 0;
		double totalInvestmentCosts = 0.0;

		this.stock.calculateTotalStockCosts();
		for (Toaster toaster : toasterList)
		{
			toaster.calculateCost();
			tmpCost += toaster.getCost();
			totalInvestmentCosts += toaster.getDesignInvestment()+toaster.getEcologyInvestment()+toaster.getNewspaperInvestment()+toaster.getQualityInvestment()+toaster.getRadioInvestment()+toaster.getTvInvestment();
		}
//		wenn ein Markforschungsbericht angefordert wurde, müssen hier die 
//		Kosten dafür richtig gebucht werden
		if (this.isMarketResearchReportON())
		{
			totalInvestmentCosts += 5000.0;
		}
		this.setCapital(this.getCapital()+totalInvestmentCosts);
		this.setCost(getCost()+tmpCost+totalInvestmentCosts+ stock.getTotalStockCosts());
	}

	// In dieser Methode wird die Indexberechnung aller Toaster dieser Company ausgeführt.
	public void calculateIndex()
	{
		for (int i = 0; i < toasterList.size(); i++)
		{
			toasterList.get(i).calculateIndex();
		}
	}

	// In dieser Methode wird der Marktanteil aller Toaster dieser Company errechnet, anschließend
	// addiert und das entsprechende Company Attribut gesetzt.
	public void calculateMarketShares(double[] indexSums)
	{
		this.marketShare = 0;
		for (int i = 0; i < this.toasterList.size(); i++)
		{

			toasterList.get(i).calculateMarketShare(indexSums[i]);
			this.marketShare = this.marketShare + toasterList.get(i).getMarketShare();
		}
	}

	// In dieser Methode wird für jeden Toaster dieser Company die Errechnung des Gewinns ausgeführt
	// und anschließend das eigene Company Attribut gesetzt.
	public void calculateProfit()
	{
		for (Toaster toaster : this.getToasterList())
		{
			toaster.calculateProfit();
		}
		this.setProfit(this.getTurnover() - this.getCost());
	}

	// In dieser Methode wird für jeden Toaster dieser Company die Errechnung des Umsatzes ausgeführt
	// und anschließend das eigene Company Attribut gesetzt.
	public void calculateTurnover()
	{
		double tmpTurnover = 0;
		for (int i = 0; i < this.toasterList.size(); i++)
		{

			toasterList.get(i).calculateTurnover();
			tmpTurnover = tmpTurnover + toasterList.get(i).getTurnover();
		}
		this.setTurnover(tmpTurnover);
	}

	public void destroyCompany()
	{
		this.setTurnover(0.0);
		this.setProfit(0.0);
		this.setCost(0.0);
		this.setMarketShare(0);
		for (int i = 0; i < this.getToasterList().size(); i++)
		{
			this.getToasterList().get(i).destroyToaster();
		}
	}

	public double getCapital()
	{
		return capital;
	}

	public ArrayList<Number> getCapitalRankingInternList()
	{
		return capitalRankingInternList;
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

	// @gwt.typeArgs <Number>
	public ArrayList<Number> getProfitRankingList()
	{
		return profitRankingList;
	}

	public ArrayList<ArrayList<List<String>>> getReportListe()
	{
		return reportListe;
	}

	public Stock getStock()
	{
		return stock;
	}

	public ArrayList<Toaster> getToasterList()
	{
		return toasterList;
	}

	public double getTurnover()
	{
		return turnover;
	}

	public boolean isMarketResearchReportON()
	{
		return marketResearchReportON;
	}

	public void setCapital(double capital)
	{
		this.capital = capital;
	}

	public void setCapitalRankingInternList(ArrayList<Number> capitalRankingInternList)
	{
		this.capitalRankingInternList = capitalRankingInternList;
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

	public void setProfitRankingList(ArrayList<Number> profitRankingList)
	{
		this.profitRankingList = profitRankingList;
	}

	public void setReportListe(ArrayList<ArrayList<List<String>>> reportListe)
	{
		this.reportListe = reportListe;
	}

	public void setStock(Stock stock)
	{
		this.stock = stock;
	}

public void setToasterList(ArrayList<Toaster> toasterList)
	{
		this.toasterList = toasterList;
	}

public void setTurnover(double turnover)
	{
		this.turnover = turnover;
	}
	
	// Methode für das Testen der anderen Methoden
	public String toString()
	{

		String s = "Company Eigenschaften: \n companyID \t \t" + this.getCompanyID() + "\n turnover: \t" + this.getTurnover() + "\n cost: \t"
				+ this.getCost() + "\n profit: \t" + this.getProfit() + "\n capital: \t" + this.getCapital() + "\n market share: \t"
				+ this.getMarketShare();
		return s;
	}

}