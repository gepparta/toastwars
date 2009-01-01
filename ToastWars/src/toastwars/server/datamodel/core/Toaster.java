package toastwars.server.datamodel.core;

import toastwars.util.NumberUtil;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Toaster implements IsSerializable
{

	private static int nextToasterID = 1;
	private int toasterID;
	private double price;
	private double index;
	private double turnover;
	private double cost;
	private double profit;
	private int marketShare;
	private Type type;
//	Faktoren f¸r marketing**************************************
	private double marketing;
	private double tvInvestment;
	private double newsPaperInvestment;
	private double radioInvestment;
//	Faktoren f¸r  research**************************************
	private double research;
	private double quality;
	private double design;
	private double efficiency;

	public Toaster()
	{
	}

	public Toaster(double price, double marketing, double tvInvestment, double newsPaperInvestment, double radioInvestment, double research,
			double quality, double design, double efficiency, double index, double turnover, double cost, double profit, int marketShare, Type type)
	{

		this.toasterID = nextToasterID;
		nextToasterID++;
		this.price = price;
		this.marketing = marketing;
		this.tvInvestment = tvInvestment;
		this.newsPaperInvestment = newsPaperInvestment;
		this.radioInvestment = radioInvestment;
		this.research = research;
		this.quality = quality;
		this.design = design;
		this.efficiency = efficiency;
		this.index = index;
		this.turnover = turnover;
		this.cost = cost;
		this.profit = profit;
		this.marketShare = marketShare;
		this.type = type;

	}

	public Toaster(int toasterID, double price, double marketing, double tvInvestment, double newsPaperInvestment, double radioInvestment,
			double research, double quality, double design, double efficiency, double index, double turnover, double cost, double profit,
			int marketShare, Type type)
	{
		this.toasterID = toasterID;
		this.price = price;
		this.marketing = marketing;
		this.tvInvestment = tvInvestment;
		this.newsPaperInvestment = newsPaperInvestment;
		this.radioInvestment = radioInvestment;
		this.research = research;
		this.quality = quality;
		this.design = design;
		this.efficiency = efficiency;
		this.index = index;
		this.turnover = turnover;
		this.cost = cost;
		this.profit = profit;
		this.marketShare = marketShare;
		this.type = type;
	}

	public static int getNextToasterID()
	{
		return nextToasterID;
	}

	public static void setNextToasterID(int nextToasterID)
	{
		Toaster.nextToasterID = nextToasterID;
	}

	public void calculateCost()
	{
		this.setCost((this.getMarketShare() * this.type.getVariableCost())
				+ (this.type.getFixCostPerMachine() * NumberUtil.roundDoubleUp((double) this.getMarketShare(), this.type.getCapacity()) / this.type
						.getCapacity()));
	}

	// @by Alex
	// Das Random f¸r den jeweiligen ToasterTyp mit den verschiedenen
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
		double i = researchIndex * marketingIndex * (1 / Math.pow((price / 10), 2.5));
//				  * NumberUtil.roundDouble(Math.random() * (random * 2))+ (1 - random);
		// runden auf zwei Stellen hinter dem Komma
		setIndex(NumberUtil.roundDouble(i));
	}

	// @by Alex extra for testing
	public void calculateIndexWithOutRandom()
	{
		double marketingIndex = calculateMarketing();
		double researchIndex = calculateResearch();
		double random = this.getType().getRandom();
		double i = researchIndex * marketingIndex * (1 / Math.pow((price / 10), 2.5));
//				  * NumberUtil.roundDouble(Math.random() * (random * 2))+ (1 - random);
		// runden auf zwei Stellen hinter dem Komma
		setIndex(NumberUtil.roundDouble(i));
	}

	// @by Alex
	public double calculateMarketing()
	{
		this.marketing = this.tvInvestment + this.newsPaperInvestment + this.radioInvestment;
//		4.67 ==> Startwert 10.000Ä damit Index =1
		double d = 4.7625 + ((Math.pow(this.marketing / 10000 - 8, 3) + Math.pow(this.marketing / 10000 - 8, 2) + Math.pow(
				this.marketing / 10000 - 8, 1)) / 80);
		return NumberUtil.roundDouble(d);
	}

	public void calculateMarketShare(double IndexSum)
	{

		this.setMarketShare((int) Math.round(this.type.getMarketVolume() / IndexSum * this.index));

	}

	public void calculateProfit()
	{
		this.setProfit(this.getTurnover() - this.getCost());
	}

	// @by Alex
	public double calculateResearch()
	{
		this.research = this.quality + this.design + this.efficiency;
		double d = Math.log(this.research / 3000) * (this.research / (Math.pow(this.research, 1.04)));
		return NumberUtil.roundDouble(d);
	}

	public void calculateTurnover()
	{

		this.setTurnover(this.getMarketShare() * this.getPrice());
	}

	public double getCost()
	{
		return cost;
	}

	public double getIndex()
	{
		return index;
	}

	public double getMarketing()
	{
		return marketing;
	}

	public int getMarketShare()
	{
		return marketShare;
	}

	public double getPrice()
	{
		return price;
	}

	public double getProfit()
	{
		return profit;
	}

	public double getResearch()
	{
		return research;
	}

	public int getToasterID()
	{
		return toasterID;
	}

	public double getTurnover()
	{
		return turnover;
	}

	public Type getType()
	{
		return type;
	}

	public void setCost(double cost)
	{
		this.cost = cost;
	}

	public void setIndex(double index)
	{
		this.index = index;
	}

	// ////////////METHODS ////////////////

	public void setMarketing(double marketing)
	{
		this.marketing = marketing;
	}

	public void setMarketShare(int marketShare)
	{
		this.marketShare = marketShare;
	}

	public void setPrice(double price) throws Exception
	{
		if (this.type == Type.TYPE1)
		{
			if (price <= 20 && price >= 5)
				this.price = price;
			else
				throw new Exception("Ung√ºltiger Preis");
		}
		if (this.type == Type.TYPE2)
		{
			if (price <= 60 && price >= 30)
				this.price = price;
			else
				throw new Exception("Ung√ºltiger Preis");
		}
		if (this.type == Type.TYPE3)
		{
			if (price <= 200 && price >= 130)
				this.price = price;
			else
				throw new Exception("Ung√ºltiger Preis");
		}
	}

	public void setProfit(double profit)
	{
		this.profit = profit;
	}

	public void setResearch(double research)
	{
		this.research = research;
	}

	public void setToasterID(int toasterID)
	{
		this.toasterID = toasterID;
	}

	public void setTurnover(double turnover)
	{
		this.turnover = turnover;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public double getTvInvestment()
	{
		return tvInvestment;
	}

	public double getNewsPaperInvestment()
	{
		return newsPaperInvestment;
	}

	public double getRadioInvestment()
	{
		return radioInvestment;
	}

	public double getQuality()
	{
		return quality;
	}

	public double getDesign()
	{
		return design;
	}

	public double getEfficiency()
	{
		return efficiency;
	}

	public void setTvInvestment(double tvInvestment)
	{
		this.tvInvestment = tvInvestment;
	}

	public void setNewsPaperInvestment(double newsPaperInvestment)
	{
		this.newsPaperInvestment = newsPaperInvestment;
	}

	public void setRadioInvestment(double radioInvestment)
	{
		this.radioInvestment = radioInvestment;
	}

	public void setQuality(double quality)
	{
		this.quality = quality;
	}

	public void setDesign(double design)
	{
		this.design = design;
	}

	public void setEfficiency(double efficiency)
	{
		this.efficiency = efficiency;
	}

}