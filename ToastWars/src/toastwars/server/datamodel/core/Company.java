package toastwars.server.datamodel.core;

import java.util.ArrayList;
import java.util.List;
import toastwars.server.datamodel.user.Group;
import com.google.gwt.user.client.rpc.IsSerializable;

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
	// java.util.ArrayList<java.util.ArrayList<java.util.List<java.lang.String>>>
	public ArrayList<List<String>> reportListe;
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
		reportListe = new ArrayList<List<String>>();
	}

	public Company(int companyID, double turnover, double cost, double profit, double capital, int marketShare, Stock stock, ArrayList<Toaster> toasterList)
	{
		this.companyID = companyID;

		this.turnover = turnover;
		this.cost = cost;
		this.profit = profit;
		this.capital = capital;
		this.marketShare = marketShare;
		this.stock = stock;
		this.toasterList = toasterList;
		reportListe = new ArrayList<List<String>>();

	}

	
	
	
	//Get Methoden
	

	public static int getNextCompanyID()
	{
		return nextCompanyID;
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
	public Stock getStock() {
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

	public ArrayList<List<String>> getReportListe()
	{
		return reportListe;
	}
	// @gwt.typeArgs <Number>
	public ArrayList<Number> getProfitRankingList()
	{
		return profitRankingList;
	}
	public ArrayList<Number> getCapitalRankingInternList()
	{
		return capitalRankingInternList;
	}
	
	public boolean isMarketResearchReportON()
	{
		return marketResearchReportON;
	}
	
	//Set-Methoden
	
	public static void setNextCompanyID(int nextCompanyID)
	{
		Company.nextCompanyID = nextCompanyID;
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
	public void setStock(Stock stock) {
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

	public void setReportListe(ArrayList<List<String>> reportListe)
	{
		this.reportListe = reportListe;
	}


	public void setProfitRankingList(ArrayList<Number> profitRankingList)
	{
		this.profitRankingList = profitRankingList;
	}


	public void setCapitalRankingInternList(ArrayList<Number> capitalRankingInternList)
	{
		this.capitalRankingInternList = capitalRankingInternList;
	}
	
	
	//Berechnende Methoden

	
	
	// In dieser Methode wird der Gewinn zum Kapital addiert.
	public void calculateCapital()
	{

		this.capital = this.capital + this.profit;

	}
	// In dieser Methode werden alle Kosten der einzelnen Toaster ermittelt, diese addiert und das 
	// Attribut der Company gesetzt.
	public void calculateCost()
	{
		double tmpCost=0;
		this.stock.calculateTotalStockCosts();
		for (int i = 0; i < this.toasterList.size(); i++)
		{

			toasterList.get(i).calculateCost();
			tmpCost += toasterList.get(i).getCost();

		}
		this.setCost(tmpCost+stock.getTotalStockCosts());
	}

	// In dieser Methode wird die Indexberechnung aller Toaster dieser Company ausgef�hrt.
	public void calculateIndex()
	{
		for (int i = 0; i < toasterList.size(); i++)
		{
			toasterList.get(i).calculateIndex();
		}
	}


	// In dieser Methode wird der Marktanteil aller Toaster dieser Company errechnet, anschlie�end
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

	// In dieser Methode wird f�r jeden Toaster dieser Company die Errechnung des Gewinns ausgef�hrt
	// und anschlie�end das eigene Company Attribut gesetzt.
	public void calculateProfit()
	{
		for (Toaster toaster : this.getToasterList())
		{
			toaster.calculateProfit();
		} 
		this.setProfit(this.getTurnover()-this.getCost());
	}

	// In dieser Methode wird f�r jeden Toaster dieser Company die Errechnung des Umsatzes ausgef�hrt
	// und anschlie�end das eigene Company Attribut gesetzt.
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
// In dieser Methode werden die Investitionen dieser Company f�r alle Toaster kumuliert.
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
	
	


// Methoden f�r das Testen der anderen Methoden
	public String toString()
	{

		String s = "Company Eigenschaften: \n companyID \t \t" + this.getCompanyID() + "\n turnover: \t" + this.getTurnover() + "\n cost: \t" + this.getCost() + "\n profit: \t"
				+ this.getProfit() + "\n capital: \t" + this.getCapital() + "\n market share: \t" + this.getMarketShare();
		return s;
	}









}