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

	
	//Berechnende Methoden

	
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
			this.stock.calculateTotalStockCosts();
			this.cost = this.cost + toasterList.get(i).getCost()+stock.getTotalStockCosts();

		}
	}


	public void calculateIndex()
	{
		for (int i = 0; i < toasterList.size(); i++)
		{
			toasterList.get(i).calculateIndex();
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
		// wird für die exakte berechnung beim simulieren benötigt
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
	
	
	public boolean isMarketResearchReportON()
	{
		return marketResearchReportON;
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