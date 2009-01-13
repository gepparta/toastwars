package toastwars.server.datamodel.core;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Company implements IsSerializable
{

	private static int nextCompanyID = 1;

	private int companyID;

	private double turnover;
	private double cost;
	private double profit;
	private double capital;
	private int marketShare;
	// @gwt.typeArgs <toastwars.server.datamodel.core.Toaster>
	private ArrayList<Toaster> toasterList = new ArrayList<Toaster>();

	public Company()
	{
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

	}

	public static int getNextCompanyID()
	{
		return nextCompanyID;
	}

	public static void setNextCompanyID(int nextCompanyID)
	{
		Company.nextCompanyID = nextCompanyID;
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

	public void setCapital(double capital)
	{
		this.capital = capital;
	}

	// //// METHODS ///////

	public void setCompanyID(int companyID)
	{
		this.companyID = companyID;
	}

	public void setCost(double cost)
	{
		this.cost = cost;
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

}

// public int calculateAndSetMarketShare(int marketvolume1, int
// marketvolume2,
// int marketvolume3, double sumIndex) {
// int calcMarketShare = 0;
// int buffer;
// int marketvolume;
// int size = toasterList.size();
// for (int i = 0; i <= (size - 1); i++) {
//
// if (i == 0)
// marketvolume = marketvolume1;
// else if (i == 1)
// marketvolume = marketvolume2;
// else
// marketvolume = marketvolume3;
//
// buffer = (int) Math.round(marketvolume / sumIndex // ///ACHTUNG!
// // Runden der
// // Marktanteile!////
// * toasterList.get(i).getIndex());
//
// toasterList.get(i).setMarketShare(buffer);
//
// calcMarketShare = calcMarketShare + buffer;
//
// }
// this.setMarketShare(calcMarketShare);
// return calcMarketShare;
// }
//
// public double calculateAndSetTurnover() {
// double calcTurnover = 0;
// double buffer;
// int size = toasterList.size();
//
// for (int i = 0; i <= (size - 1); i++) {
//
// buffer = (toasterList.get(i).getMarketShare() * toasterList.get(i)
// .getPrice());
// toasterList.get(i).setTurnover(buffer);
// calcTurnover = calcTurnover + buffer;
// }
// this.setTurnover(calcTurnover);
// return calcTurnover;
// }
//
// public double calculateAndSetCost(Double fixCost1, Double variableCost1,
// Double fixCost2, Double variableCost2, Double fixCost3,
// Double variableCost3) {
// double calcCost = 0;
// double buffer;
// double fixCost;
// double variableCost;
//
// int size = toasterList.size();
//
// for (int i = 0; i <= (size - 1); i++) {
//
// if (i == 0) {
// fixCost = fixCost1;
// variableCost = variableCost1;
// } else if (i == 1) {
// fixCost = fixCost2;
// variableCost = variableCost2;
// } else {
// fixCost = fixCost2;
// variableCost = variableCost2;
// }
//
// buffer = (fixCost + (variableCost * toasterList.get(i)
// .getMarketShare()));
// toasterList.get(i).setCost(buffer);
// calcCost = calcCost + buffer;
// }
// this.setCost(calcCost);
// return calcCost;
// }
//
// public double calculateAndSetProfit() {
// // muss mit for gemacht werden, um werte einzeln in die toaster objekte
// // zu schreiben
// return this.turnover - this.cost;
// }
//
// public double calculateAndSetCapital() {
// // CAPITAL aus Toaster entfernen!!!
// return this.capital = capital + this.profit;
//
// }

