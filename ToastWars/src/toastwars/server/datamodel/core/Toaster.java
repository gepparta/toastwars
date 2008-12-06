package toastwars.server.datamodel.core;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Toaster implements IsSerializable
{

	private double price;

	private double marketing;

	private double research;

	private double index;

	private double turnover;

	private double capital;

	private double cost;

	private double profit;

	private int marketShare;

	private Type type;

	// //////////////////Konstruktor//////////////////////////

	public Toaster(double price, double marketing, double research,
			double index, double turnover, double capital, double cost,
			double profit, int marketShare, Type type)
	{

		this.price = price;
		this.marketing = marketing;
		this.research = research;
		this.index = index;
		this.turnover = turnover;
		this.capital = capital;
		this.cost = cost;
		this.profit = profit;
		this.marketShare = marketShare;
		this.type = type;

	}

	// /////////////////GETTER & SETTER ///////////////////////////////////

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public double getMarketing()
	{
		return marketing;
	}

	public void setMarketing(double marketing)
	{
		this.marketing = marketing;
	}

	public double getResearch()
	{
		return research;
	}

	public void setResearch(double research)
	{
		this.research = research;
	}

	public double getIndex()
	{
		return index;
	}

	public void setIndex(double index)
	{
		this.index = index;
	}

	public double getTurnover()
	{
		return turnover;
	}

	public void setTurnover(double turnover)
	{
		this.turnover = turnover;
	}

	public double getCapital()
	{
		return capital;
	}

	public void setCapital(double capital)
	{
		this.capital = capital;
	}

	public double getCost()
	{
		return cost;
	}

	public void setCost(double cost)
	{
		this.cost = cost;
	}

	public double getProfit()
	{
		return profit;
	}

	public void setProfit(double profit)
	{
		this.profit = profit;
	}

	public int getMarketShare()
	{
		return marketShare;
	}

	public void setMarketShare(int marketShare)
	{
		this.marketShare = marketShare;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	// ////////////METHODS ////////////////

	public double calculateAndSetIndex(double random)
	{
		double index = research * (1 / price) * marketing * random;
		this.setIndex(index);
		return index;
	}

	// TODO: Wie Random setzen?
	public void calculateIndex()
	{
		double marketingIndex = calculateMarketing();
		double researchIndex = calculateResearch();
		double random = 1.0;
		double i = researchIndex * (1 / price) * marketingIndex * random;
		setIndex(i);
	}

	public double calculateResearch()
	{
		double d = Math.log(this.research / 3000);
		return Math.round(d * 100) / 100;
	}

	public double calculateMarketing()
	{
		double d = 6.49 + ((Math.pow(this.marketing / 10000 - 8, 3)
				+ Math.pow(this.marketing / 10000 - 8, 2) + Math.pow(
				this.marketing / 10000 - 8, 1)) / 80);
		return Math.round(d * 100) / 100;
	}

}