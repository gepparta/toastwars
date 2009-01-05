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
	// Faktoren f�r marketing**************************************
	private double marketing;
	private double tvInvestment;
	private double newsPaperInvestment;
	private double radioInvestment;
	// Faktoren f�r research**************************************
	private double research;
	private double quality;
	private double design;
	private double efficiency;

	public Toaster()
	{
	}

	public Toaster(double price, double marketing, double tvInvestment, double newsPaperInvestment, double radioInvestment, double research, double quality, double design,
			double efficiency, double index, double turnover, double cost, double profit, int marketShare, Type type)
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

	public Toaster(int toasterID, double price, double marketing, double tvInvestment, double newsPaperInvestment, double radioInvestment, double research, double quality,
			double design, double efficiency, double index, double turnover, double cost, double profit, int marketShare, Type type)
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

		this.setCost((this.getMarketShare() * this.type.getVariableCosts()) + (Math.ceil((double) this.getMarketShare() // DAS
				// (DOUBLE
				// ist
				// MUSS
				/ this.getType().getCapacity()) * this.getType().getStepCosts()) + (this.type.getFixCosts()));
	}

	// @by Alex
	// Das Random f�r den jeweiligen ToasterTyp mit den verschiedenen
	// Abweichungen:
	// Type1 : 5% --> wird im Enum Konstruktor definiert
	// Type2 : 8%
	// Type3 : 10%
	// z.b. NumberUtil.roundDouble(Math.random() * (1.05 - 0.95)) + 0.95;

	// Random ist wird nicht eingerechnet!!!
	public void calculateIndex()
	{
		double marketingIndex = calculateMarketing();
		double researchIndex = calculateResearch();
		double random = this.getType().getRandom();
		double priceIndex = 0;
		if (this.type == Type.TYPE1)
			priceIndex = (1 / Math.pow((price / 10), 1.2));
		if (this.type == Type.TYPE2)
			priceIndex = (1 / Math.pow((price / 40), 2.4));
		if (this.type == Type.TYPE3)
			priceIndex = (1 / Math.pow((price / 150), 5));
		double i = researchIndex * marketingIndex * priceIndex;
		// * NumberUtil.roundDouble(Math.random() * (random * 2))+ (1 - random);
		// runden auf zwei Stellen hinter dem Komma
		setIndex(NumberUtil.roundDouble(i));
	}

	// @by Alex extra for testing
	public void calculateIndexWithOutRandom()
	{
		double marketingIndex = calculateMarketing();
		double researchIndex = calculateResearch();
		double random = this.getType().getRandom();
		double priceIndex = 0;
		if (this.type == Type.TYPE1)
			priceIndex = (1 / Math.pow((price / 10), 1.2));
		if (this.type == Type.TYPE2)
			priceIndex = (1 / Math.pow((price / 40), 2.4));
		if (this.type == Type.TYPE3)
			priceIndex = (1 / Math.pow((price / 150), 5));
		double i = researchIndex * marketingIndex * priceIndex;
		// * NumberUtil.roundDouble(Math.random() * (random * 2))+ (1 - random);
		// runden auf zwei Stellen hinter dem Komma
		setIndex(NumberUtil.roundDouble(i));
	}

	// @by Alex
	public double calculateMarketing()
	{
		double tvInvestmentIndex = 0;
		double radioInvestmentIndex = 0;
		double newsPaperInvestmentIndex = 0;
		if (this.type == Type.TYPE1)
		{
			tvInvestmentIndex = NumberUtil.roundDouble(((Math.pow(this.tvInvestment / 10000 - 8, 3) + Math.pow(this.tvInvestment / 10000 - 8, 2) + Math.pow(
					                   this.tvInvestment / 10000 - 8, 1)) / 150) + 2.24);

			radioInvestmentIndex = NumberUtil.roundDouble((Math.log(this.radioInvestment / 3000)) * 
					            (this.radioInvestment / Math.pow(this.radioInvestment, 1.03)) + 0.09);
			newsPaperInvestmentIndex = NumberUtil.roundDouble((Math.log(this.newsPaperInvestment / 3000)) * (this.newsPaperInvestment / Math.pow(this.newsPaperInvestment, 1.09))
					+ 0.76);
		}
		if (this.type == Type.TYPE2)
		{
			tvInvestmentIndex = NumberUtil.roundDouble(((Math.pow(this.tvInvestment / 10000 - 8, 3) + Math.pow(this.tvInvestment / 10000 - 8, 2) + Math.pow(
					this.tvInvestment / 10000 - 8, 1)) / 150) + 1.7);
			radioInvestmentIndex = NumberUtil.roundDouble((Math.log(this.radioInvestment / 3000)) * 
					(this.radioInvestment / Math.pow(this.radioInvestment, 1.03)) - 0.41);

			newsPaperInvestmentIndex = NumberUtil.roundDouble((Math.log(this.newsPaperInvestment / 3000))
					* (this.newsPaperInvestment / Math.pow(this.newsPaperInvestment, 1.09)) + 0.47);

		}
		if (this.type == Type.TYPE3)
		{
			tvInvestmentIndex = NumberUtil.roundDouble(((Math.pow(this.tvInvestment / 10000 - 8, 3) + Math.pow(this.tvInvestment / 10000 - 8, 2) + Math.pow(
					this.tvInvestment / 10000 - 8, 1)) / 150) + 1.35);
			radioInvestmentIndex = NumberUtil.roundDouble((Math.log(this.radioInvestment / 3000)) * 
					(this.radioInvestment / Math.pow(this.radioInvestment, 1.03)) - 0.69);

			newsPaperInvestmentIndex = NumberUtil.roundDouble((Math.log(this.newsPaperInvestment / 3000))
					* (this.newsPaperInvestment / Math.pow(this.newsPaperInvestment, 1.09) + 0.22));

		}
		double d = tvInvestmentIndex + radioInvestmentIndex + newsPaperInvestmentIndex;

//		System.out.println(this.getNewsPaperInvestment());
//		System.out.println(newsPaperInvestmentIndex);
//		System.out.println(this.getRadioInvestment());
//		System.out.println(radioInvestmentIndex);
//		System.out.println(this.getTvInvestment());
//		System.out.println(tvInvestmentIndex);

		// Michi --> das sollte doch auch noch rein
		this.setMarketing(NumberUtil.roundDouble(d));
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
		double qualityIndex = 0;
		double designIndex = 0;
		double efficiencyIndex = 0;
		if (this.type == Type.TYPE1)
		{
			qualityIndex = NumberUtil.roundDouble((Math.log(this.quality / 3000)) * (this.quality / Math.pow(this.quality, 1.04)) + 0.635);
			designIndex = NumberUtil.roundDouble((Math.log(this.design / 2000)) * (this.design / Math.pow(this.design, 1.0658)) + 0.48);
			efficiencyIndex = NumberUtil.roundDouble((Math.log(this.efficiency / 1000)) * (this.efficiency / Math.pow(this.efficiency, 1.0992)) + 0.31);

		}
		if (this.type == Type.TYPE2)
		{
			qualityIndex = NumberUtil.roundDouble((Math.log(this.quality / 3000)) * (this.quality / Math.pow(this.quality, 1.04)) + 0.165);
			designIndex = NumberUtil.roundDouble((Math.log(this.design / 2000)) * (this.design / Math.pow(this.design, 1.0658)) + 0.12);
			efficiencyIndex = NumberUtil.roundDouble((Math.log(this.efficiency / 1000)) * (this.efficiency / Math.pow(this.efficiency, 1.0992)) + 0.08);

		}

		if (this.type == Type.TYPE3)
		{
			qualityIndex = NumberUtil.roundDouble((Math.log(this.quality / 3000)) * (this.quality / Math.pow(this.quality, 1.04)) - 0.1);
			designIndex = NumberUtil.roundDouble((Math.log(this.design / 2000)) * (this.design / Math.pow(this.design, 1.0658)) - 0.07);
			efficiencyIndex = NumberUtil.roundDouble((Math.log(this.efficiency / 1000)) * (this.efficiency / Math.pow(this.efficiency, 1.0992)) - 0.04);
		}

//		System.out.println(this.getQuality());
//		System.out.println(qualityIndex);
//		System.out.println(this.getDesign());
//		System.out.println(designIndex);
//		System.out.println(this.getEfficiency());
//		System.out.println(efficiencyIndex);

		double d = qualityIndex + designIndex + efficiencyIndex;
		this.setResearch(NumberUtil.roundDouble(d));
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

	public double getDesign()
	{
		return design;
	}

	public double getEfficiency()
	{
		return efficiency;
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

	public double getNewsPaperInvestment()
	{
		return newsPaperInvestment;
	}

	public double getPrice()
	{
		return price;
	}

	public double getProfit()
	{
		return profit;
	}

	public double getQuality()
	{
		return quality;
	}

	public double getRadioInvestment()
	{
		return radioInvestment;
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

	public double getTvInvestment()
	{
		return tvInvestment;
	}

	public Type getType()
	{
		return type;
	}

	public void setCost(double cost)
	{
		this.cost = cost;
	}

	public void setDesign(double design) throws Exception
	{
		if (this.type == Type.TYPE1)
		{
			if (design >= 5.000)
				this.design = design;
			else
				throw new Exception("Ung�ltige Eingabe f�r Design");
		}
		if (this.type == Type.TYPE2)
		{
			if (design >= 10.000)
				this.design = design;
			else
				throw new Exception("Ung�ltige Eingabe f�r Design");
		}
		if (this.type == Type.TYPE3)
		{
			if (design >= 20.000)
				this.design = design;
			else
				throw new Exception("Ung�ltige Eingabe f�r Design");
		}
	}

	public void setEfficiency(double efficiency) throws Exception
	{
		if (this.type == Type.TYPE1)
		{
			if (efficiency >= 5.000)
				this.efficiency = efficiency;
			else
				throw new Exception("Ung�ltige Eingabe f�r �kologie");
		}
		if (this.type == Type.TYPE2)
		{
			if (efficiency >= 10.000)
				this.efficiency = efficiency;
			else
				throw new Exception("Ung�ltige Eingabe f�r �kologie");
		}
		if (this.type == Type.TYPE3)
		{
			if (efficiency >= 20.000)
				this.efficiency = efficiency;
			else
				throw new Exception("Ung�ltige Eingabe f�r �kologie");
		}
	}

	public void setIndex(double index)
	{
		this.index = index;
	}

	public void setMarketing(double marketing)
	{
		this.marketing = marketing;
	}

	public void setMarketShare(int marketShare)
	{
		this.marketShare = marketShare;
	}

	public void setNewsPaperInvestment(double newsPaperInvestment) throws Exception
	{
		if (this.type == Type.TYPE1)
		{
			if (newsPaperInvestment >= 5.000)
				this.newsPaperInvestment = newsPaperInvestment;
			else
				throw new Exception("Ung�ltige Eingabe f�r Zeitung-Werbung");
		}
		if (this.type == Type.TYPE2)
		{
			if (newsPaperInvestment >= 10.000)
				this.newsPaperInvestment = newsPaperInvestment;
			else
				throw new Exception("Ung�ltige Eingabe f�r Zeitung-Werbung");
		}
		if (this.type == Type.TYPE3)
		{
			if (newsPaperInvestment >= 20.000)
				this.newsPaperInvestment = newsPaperInvestment;
			else
				throw new Exception("Ung�ltige Eingabe f�r Zeitung-Werbung");
		}
	}

	public void setPrice(double price) throws Exception
	{
		if (this.type == Type.TYPE1)
		{
			if (price <= 20 && price >= 5)
				this.price = price;
			else
				throw new Exception("Ung�ltiger Preis");
		}
		if (this.type == Type.TYPE2)
		{
			if (price <= 60 && price >= 30)
				this.price = price;
			else
				throw new Exception("Ung�ltiger Preis");
		}
		if (this.type == Type.TYPE3)
		{
			if (price <= 200 && price >= 130)
				this.price = price;
			else
				throw new Exception("Ung�ltiger Preis");
		}
	}

	public void setProfit(double profit)
	{
		this.profit = profit;
	}

	public void setQuality(double quality) throws Exception
	{
		if (this.type == Type.TYPE1)
		{
			if (quality >= 5.000)
				this.quality = quality;
			else
				throw new Exception("Ung�ltige Eingabe f�r Qualit�t");
		}
		if (this.type == Type.TYPE2)
		{
			if (quality >= 10.000)
				this.quality = quality;
			else
				throw new Exception("Ung�ltige Eingabe f�r Qualit�t");
		}
		if (this.type == Type.TYPE3)
		{
			if (quality >= 20.000)
				this.quality = quality;
			else
				throw new Exception("Ung�ltige Eingabe f�r Qualit�t");
		}
	}

	public void setRadioInvestment(double radioInvestment) throws Exception
	{
		if (this.type == Type.TYPE1)
		{
			if (radioInvestment >= 10.000)
				this.radioInvestment = radioInvestment;
			else
				throw new Exception("Ung�ltige Eingabe f�r Radio-Werbung");
		}
		if (this.type == Type.TYPE2)
		{
			if (radioInvestment >= 20.000)
				this.radioInvestment = radioInvestment;
			else
				throw new Exception("Ung�ltige Eingabe f�r Radio-Werbung");
		}
		if (this.type == Type.TYPE3)
		{
			if (radioInvestment >= 30.000)
				this.radioInvestment = radioInvestment;
			else
				throw new Exception("Ung�ltige Eingabe f�r Radio-Werbung");
		}
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

	public void setTvInvestment(double tvInvestment) throws Exception
	{
		if (this.type == Type.TYPE1)
		{
			if (tvInvestment >= 20.000)
				this.tvInvestment = tvInvestment;
			else
				throw new Exception("Ung�ltige Eingabe f�r TV-Werbung");
		}
		if (this.type == Type.TYPE2)
		{
			if (tvInvestment >= 30.000)
				this.tvInvestment = tvInvestment;
			else
				throw new Exception("Ung�ltige Eingabe f�r TV-Werbung");
		}
		if (this.type == Type.TYPE3)
		{
			if (tvInvestment >= 40.000)
				this.tvInvestment = tvInvestment;
			else
				throw new Exception("Ung�ltige Eingabe f�r TV-Werbung");
		}
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	// Ist das so wie es ist sinnvoll?
	public String toString()
	{

		String s = "Toaster Eigenschaften: " + "\n ToasterID \t \t" + this.getToasterID() + "\n price: \t" + this.getPrice() + "\n index: \t" + this.getIndex() + "\n turnover: \t"
				+ this.getTurnover() + "\n cost: \t" + this.getCost() + "\n profit: \t" + this.getProfit() + "\n marketShare: \t" + this.getMarketShare() + "\n type: \t"
				+ this.getType().name() + "\n Marketingfaktoren: \t" + "\n marketing: \t" + this.getMarketing() + "\n TV Investment: \t" + this.getTvInvestment()
				+ "\n Newspaper Investment: \t" + this.getNewsPaperInvestment() + "\n Radio Investment: \t" + this.getRadioInvestment() + "\n Forschungsfaktoren: \t"
				+ "\n research: \t" + this.getResearch() + "\n quality: \t" + this.getQuality() + "\n design: \t" + this.getDesign() + "\n efficiency: \t" + this.getEfficiency();
		return s;
	}

}