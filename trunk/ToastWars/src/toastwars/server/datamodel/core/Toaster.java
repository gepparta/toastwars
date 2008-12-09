package toastwars.server.datamodel.core;

import toastwars.util.NumberUtil;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Toaster implements IsSerializable
{

	private static int nextToasterID = 0;
	private int toasterID;
	private double price;
	private double marketing;
	private double research;
	private double index;
	private double turnover;
	private double cost;
	private double profit;
	private int marketShare;
	private Type type;

	// //////////////////Konstruktor//////////////////////////

	public Toaster(int toasterID, double price, double marketing, double research, double index,
			double turnover, double cost, double profit, int marketShare, Type type)
	{
		this.toasterID = toasterID;
		this.price = price;
		this.marketing = marketing;
		this.research = research;
		this.index = index;
		this.turnover = turnover;
		this.cost = cost;
		this.profit = profit;
		this.marketShare = marketShare;
		this.type = type;
	}

	public Toaster(double price, double marketing, double research, double index, double turnover,
			double cost, double profit, int marketShare, Type type)
	{

		this.toasterID = nextToasterID;
		nextToasterID++;
		this.price = price;
		this.marketing = marketing;
		this.research = research;
		this.index = index;
		this.turnover = turnover;
		this.cost = cost;
		this.profit = profit;
		this.marketShare = marketShare;
		this.type = type;

	}

	// /////////////////GETTER & SETTER ///////////////////////////////////

	public static int getNextToasterID()
	{
		return nextToasterID;
	}

	public static void setNextToasterID(int nextToasterID)
	{
		Toaster.nextToasterID = nextToasterID;
	}

	public int getToasterID()
	{
		return toasterID;
	}

	public void setToasterID(int toasterID)
	{
		this.toasterID = toasterID;
	}

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

	// @by Alex
	// Das Random f�r den jeweiligen ToasterTyp mit den verschiedenen
	// Abweichungen:
	// Type1 : 5% --> wird im Enum Konstruktor definiert
	// Type2 : 8%
	// Type3 : 10%
	// z.b. NumberUtil.roundDouble(Math.random() * (1.05 - 0.95)) + 0.95;
	public void calculateIndex()
	{
		double marketingIndex = calculateMarketing();
		double researchIndex = calculateResearch();
		double random = this.getType().getRandom();
		double i = researchIndex * (1 / (price / 10)) * marketingIndex
				* NumberUtil.roundDouble(Math.random() * (random * 2)) + (1 - random);
		// runden auf zwei Stellen hinter dem Komma
		setIndex(NumberUtil.roundDouble(i));
	}
	// @by Alex extra for testing
	public void calculateIndexWithOutRandom()
	{
		double marketingIndex = calculateMarketing();
		double researchIndex = calculateResearch();
		double random = this.getType().getRandom();
		double i = researchIndex * (1 / (price / 10)) * marketingIndex * random;
		// runden auf zwei Stellen hinter dem Komma
		setIndex(NumberUtil.roundDouble(i));
	}

	// @by Alex
	public double calculateMarketing()
	{
		double d = 6.48 + ((Math.pow(this.marketing / 10000 - 8, 3)
				+ Math.pow(this.marketing / 10000 - 8, 2) + Math.pow(this.marketing / 10000 - 8, 1)) / 80);
		return NumberUtil.roundDouble(d);
	}

	// @by Alex
	public double calculateResearch()
	{
		double d = Math.log(this.research / 3000);
		return NumberUtil.roundDouble(d);
	}

	public void calculateMarketShare(double IndexSum)
	{

		this.setMarketShare((int) Math.round(this.type.getMarketVolume() / IndexSum * this.index));

	}

	public void calculateTurnover()
	{

		this.setTurnover(this.getMarketShare() * this.getPrice());
	}

	public void calculateCost()
	{
		this
				.setCost((this.getMarketShare() * this.type.getVariableCost())
						+ this.type.getFixCost());
	}

	public void calculateProfit()
	{
		this.setProfit(this.getTurnover() - this.getCost());
	}

}