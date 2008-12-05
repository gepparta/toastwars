package toastwars.server.datamodel.core;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Company implements IsSerializable {

	private String description;
	private double turnover;
	private double cost;
	private double profit;
	private double capital;
	private int marketShare;
	private ArrayList<Toaster> toasterList;

	// ////////////KONSTRUKTOR////////////////////
	public Company(String description, double turnover, double cost,
			double profit, double capital, int marketShare,
			ArrayList<Toaster> toasterList) {
		this.description = description;
		this.turnover = turnover;
		this.cost = cost;
		this.profit = profit;
		this.capital = capital;
		this.marketShare = marketShare;
		this.toasterList = toasterList;

	}

	// //GETTER & SETTER ///////////
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public int getMarketShare() {
		return marketShare;
	}

	public void setMarketShare(int marketShare) {
		this.marketShare = marketShare;
	}

	public ArrayList<Toaster> getToasterList() {
		return toasterList;
	}

	public void setToasterList(ArrayList<Toaster> toasterList) {
		this.toasterList = toasterList;
	}

	// //// METHODS ///////

	public int calculateAndSetMarketShare(int marketvolume1,int  marketvolume2, int marketvolume3, double sumIndex) {
		int calcMarketShare = 0;
		int buffer;
		int marketvolume;
		int size = toasterList.size();
		for (int i = 0; i <= (size - 1); i++) {
			
			
			if (i==0)  marketvolume=marketvolume1;
				else if (i==1)  marketvolume = marketvolume2;
					 else marketvolume = marketvolume3;
			
			buffer = (int) Math.round(marketvolume / sumIndex   /////ACHTUNG! Runden der Marktanteile!////
					* toasterList.get(i).getIndex());
			
			toasterList.get(i).setMarketShare(buffer);
			
			calcMarketShare = calcMarketShare + buffer;
								
		}
		this.setMarketShare(calcMarketShare);
		return calcMarketShare;
	}

	public double calculateAndSetTurnover() {
		double calcTurnover = 0;
		double buffer;
		int size = toasterList.size();

		for (int i = 0; i <= (size - 1); i++) {
			
					buffer= (toasterList.get(i).getMarketShare() * toasterList.get(i)
							.getPrice());
					toasterList.get(i).setTurnover(buffer);
					calcTurnover = calcTurnover + buffer;
		}
		this.setTurnover(calcTurnover);
		return calcTurnover;
	}

	public double calculateAndSetCost(Double fixCost1, Double variableCost1,Double fixCost2, Double variableCost2,Double fixCost3, Double variableCost3) {
		double calcCost = 0;
		double buffer;
		double fixCost;
		double variableCost;
		
		int size = toasterList.size();

		for (int i = 0; i <= (size - 1); i++) {
			
			if (i==0)  {fixCost = fixCost1; variableCost = variableCost1;}
			else if (i==1)  {fixCost = fixCost2; variableCost = variableCost2;}
				 else {fixCost = fixCost2; variableCost = variableCost2;}
			
			
					buffer = (fixCost + (variableCost * toasterList.get(i)
							.getMarketShare()));
					toasterList.get(i).setCost(buffer);
					calcCost = calcCost + buffer;
		}
		this.setCost(calcCost);
		return calcCost;
	}

	public double calculateAndSetProfit() {
//muss mit for gemacht werden, um werte einzeln in die toaster objekte zu schreiben
		return this.turnover - this.cost;
	}

	public double calculateAndSetCapital() {
//CAPITAL aus Toaster entfernen!!!
		return this.capital = capital + this.profit;

	}

}
