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
	private double newspaperInvestment;
	private double radioInvestment;
	private double tvInvestmentKum;
	private double newspaperInvestmentKum;
	private double radioInvestmentKum;
	// Faktoren f�r research**************************************
	private double research;
	private double qualityInvestment;
	private double designInvestment;
	private double ecologyInvestment;
	private double qualityInvestmentKum;
	private double designInvestmentKum;
	private double ecologyInvestmentKum;

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
		this.newspaperInvestment = newsPaperInvestment;
		this.radioInvestment = radioInvestment;
		this.research = research;
		this.qualityInvestment = quality;
		this.designInvestment = design;
		this.ecologyInvestment = efficiency;
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
		this.newspaperInvestment = newsPaperInvestment;
		this.radioInvestment = radioInvestment;
		this.research = research;
		this.qualityInvestment = quality;
		this.designInvestment = design;
		this.ecologyInvestment = efficiency;
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

	public void addDesignInvestment(double euro)
	{
		double design_Investment = this.getDesignInvestment() + euro;
		try
		{
			this.setDesignInvestment(design_Investment);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addEfficiencyInvestment(double euro)
	{
		double efficiency_Investment = this.getEfficiencyInvestment() + euro;
		try
		{
			this.setEfficiencyInvestment(efficiency_Investment);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addNewspaperInvestment(double euro)
	{
		double newspaper_Investment = this.getNewspaperInvestment() + euro;
		try
		{
			this.setNewspaperInvestment(newspaper_Investment);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addQualityInvestment(double euro)
	{
		double quality_Investment = this.getQualityInvestment() + euro;
		try
		{
			this.setQualityInvestment(quality_Investment);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addRadioInvestment(double euro)
	{
		double radio_Investment = this.getRadioInvestment() + euro;
		try
		{
			this.setRadioInvestment(radio_Investment);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void addTvInvestment(double euro)
	{
		double tv_Investment = this.getTvInvestment() + euro;
		try
		{
			this.setTvInvestment(tv_Investment);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void calculateCost()
	{

		this.setCost((this.getMarketShare() * this.type.getVariableCosts())
				+ (Math.ceil((double) this.getMarketShare() / this.getType().getCapacity()) * this.getType().getStepCosts()) + (this.type.getFixCosts()));
	}

	// TODO: Random einsetzten
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
			double tv = this.tvInvestmentKum + Type.TYPE1.getTvInvestmentPlus();
			double radio = this.radioInvestmentKum + Type.TYPE1.getRadioInvestmentPlus();
			double newspaper = this.getNewspaperInvestmentKum() + Type.TYPE1.getNewspaperInvestmentPlus();
			tvInvestmentIndex = NumberUtil.roundDouble(((Math.pow(tv / 10000 - 8, 3) + Math.pow(tv / 10000 - 8, 2) + Math.pow(tv / 10000 - 8, 1)) / 150) + 2.24);

			radioInvestmentIndex = NumberUtil.roundDouble((Math.log(radio / 3000)) * (radio / Math.pow(radio, 1.03)) + 0.09);
			newsPaperInvestmentIndex = NumberUtil.roundDouble((Math.log(newspaper / 3000)) * (newspaper / Math.pow(newspaper, 1.09)) + 0.76);
		}
		if (this.type == Type.TYPE2)
		{
			double tv = this.tvInvestmentKum + Type.TYPE2.getTvInvestmentPlus();
			double radio = this.radioInvestmentKum + Type.TYPE2.getRadioInvestmentPlus();
			double newspaper = this.getNewspaperInvestmentKum() + Type.TYPE2.getNewspaperInvestmentPlus();

			tvInvestmentIndex = NumberUtil.roundDouble(((Math.pow(tv / 10000 - 8, 3) + Math.pow(tv / 10000 - 8, 2) + Math.pow(tv / 10000 - 8, 1)) / 150) + 1.7);
			radioInvestmentIndex = NumberUtil.roundDouble((Math.log(radio / 3000)) * (radio / Math.pow(radio, 1.03)) - 0.41);

			newsPaperInvestmentIndex = NumberUtil.roundDouble((Math.log(newspaper / 3000)) * (newspaper / Math.pow(newspaper, 1.09)) + 0.47);

		}
		if (this.type == Type.TYPE3)
		{
			double tv = this.tvInvestmentKum + Type.TYPE2.getTvInvestmentPlus();
			double radio = this.radioInvestmentKum + Type.TYPE2.getRadioInvestmentPlus();
			double newspaper = this.getNewspaperInvestmentKum() + Type.TYPE2.getNewspaperInvestmentPlus();
			tvInvestmentIndex = NumberUtil.roundDouble(((Math.pow(tv / 10000 - 8, 3) + Math.pow(tv / 10000 - 8, 2) + Math.pow(tv / 10000 - 8, 1)) / 150) + 1.35);
			radioInvestmentIndex = NumberUtil.roundDouble((Math.log(radio / 3000)) * (radio / Math.pow(radio, 1.03)) - 0.69);

			newsPaperInvestmentIndex = NumberUtil.roundDouble((Math.log(newspaper / 3000)) * (newspaper / Math.pow(newspaper, 1.09)) + 0.22);

		}
		double d = tvInvestmentIndex + radioInvestmentIndex + newsPaperInvestmentIndex;
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
			qualityIndex = NumberUtil.roundDouble((Math.log(this.qualityInvestment / 3000)) * (this.qualityInvestment / Math.pow(this.qualityInvestment, 1.04)) + 0.635);
			designIndex = NumberUtil.roundDouble((Math.log(this.designInvestment / 2000)) * (this.designInvestment / Math.pow(this.designInvestment, 1.0658)) + 0.48);
			efficiencyIndex = NumberUtil.roundDouble((Math.log(this.ecologyInvestment / 1000)) * (this.ecologyInvestment / Math.pow(this.ecologyInvestment, 1.0992)) + 0.31);

		}
		if (this.type == Type.TYPE2)
		{
			qualityIndex = NumberUtil.roundDouble((Math.log(this.qualityInvestment / 3000)) * (this.qualityInvestment / Math.pow(this.qualityInvestment, 1.04)) + 0.165);
			designIndex = NumberUtil.roundDouble((Math.log(this.designInvestment / 2000)) * (this.designInvestment / Math.pow(this.designInvestment, 1.0658)) + 0.12);
			efficiencyIndex = NumberUtil.roundDouble((Math.log(this.ecologyInvestment / 1000)) * (this.ecologyInvestment / Math.pow(this.ecologyInvestment, 1.0992)) + 0.08);

		}

		if (this.type == Type.TYPE3)
		{
			qualityIndex = NumberUtil.roundDouble((Math.log(this.qualityInvestment / 3000)) * (this.qualityInvestment / Math.pow(this.qualityInvestment, 1.04)) - 0.1);
			designIndex = NumberUtil.roundDouble((Math.log(this.designInvestment / 2000)) * (this.designInvestment / Math.pow(this.designInvestment, 1.0658)) - 0.07);
			efficiencyIndex = NumberUtil.roundDouble((Math.log(this.ecologyInvestment / 1000)) * (this.ecologyInvestment / Math.pow(this.ecologyInvestment, 1.0992)) - 0.04);
		}
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

	public double getDesignInvestment()
	{
		return designInvestment;
	}

	public double getEfficiencyInvestment()
	{
		return ecologyInvestment;
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

	public double getNewspaperInvestment()
	{
		return newspaperInvestment;
	}

	public double getPrice()
	{
		return price;
	}

	public double getProfit()
	{
		return profit;
	}

	public double getQualityInvestment()
	{
		return qualityInvestment;
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

	public void setDesignInvestment(double design) throws Exception
	{
		if (this.type == Type.TYPE1)
		{
			if (design >= 5.000)
				this.designInvestment = design;
			else
				throw new Exception("Ung�ltige Eingabe f�r Design");
		}
		if (this.type == Type.TYPE2)
		{
			if (design >= 10.000)
				this.designInvestment = design;
			else
				throw new Exception("Ung�ltige Eingabe f�r Design");
		}
		if (this.type == Type.TYPE3)
		{
			if (design >= 20.000)
				this.designInvestment = design;
			else
				throw new Exception("Ung�ltige Eingabe f�r Design");
		}
	}

	public void setEfficiencyInvestment(double efficiency) throws Exception
	{
		if (this.type == Type.TYPE1)
		{
			if (efficiency >= 5.000)
				this.ecologyInvestment = efficiency;
			else
				throw new Exception("Ung�ltige Eingabe f�r �kologie");
		}
		if (this.type == Type.TYPE2)
		{
			if (efficiency >= 10.000)
				this.ecologyInvestment = efficiency;
			else
				throw new Exception("Ung�ltige Eingabe f�r �kologie");
		}
		if (this.type == Type.TYPE3)
		{
			if (efficiency >= 20.000)
				this.ecologyInvestment = efficiency;
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

	public void setNewspaperInvestment(double newsPaperInvestment) throws Exception
	{
		if (this.type == Type.TYPE1)
		{
			if (newsPaperInvestment >= 5.000)
				this.newspaperInvestment = newsPaperInvestment;
			else
				throw new Exception("Ung�ltige Eingabe f�r Zeitung-Werbung");
		}
		if (this.type == Type.TYPE2)
		{
			if (newsPaperInvestment >= 10.000)
				this.newspaperInvestment = newsPaperInvestment;
			else
				throw new Exception("Ung�ltige Eingabe f�r Zeitung-Werbung");
		}
		if (this.type == Type.TYPE3)
		{
			if (newsPaperInvestment >= 20.000)
				this.newspaperInvestment = newsPaperInvestment;
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

	public void setQualityInvestment(double quality) throws Exception
	{
		if (this.type == Type.TYPE1)
		{
			if (quality >= 5.000)
				this.qualityInvestment = quality;
			else
				throw new Exception("Ung�ltige Eingabe f�r Qualit�t");
		}
		if (this.type == Type.TYPE2)
		{
			if (quality >= 10.000)
				this.qualityInvestment = quality;
			else
				throw new Exception("Ung�ltige Eingabe f�r Qualit�t");
		}
		if (this.type == Type.TYPE3)
		{
			if (quality >= 20.000)
				this.qualityInvestment = quality;
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

	public String toString()
	{

		String s = "Toaster Eigenschaften: " + "\n ToasterID \t \t" + this.getToasterID() + "\n price: \t" + this.getPrice() + "\n index: \t" + this.getIndex() + "\n turnover: \t"
				+ this.getTurnover() + "\n cost: \t" + this.getCost() + "\n profit: \t" + this.getProfit() + "\n marketShare: \t" + this.getMarketShare() + "\n type: \t"
				+ this.getType().name() + "\n Marketingfaktoren: \t" + "\n marketing: \t" + this.getMarketing() + "\n TV Investment: \t" + this.getTvInvestment()
				+ "\n Newspaper Investment: \t" + this.getNewspaperInvestment() + "\n Radio Investment: \t" + this.getRadioInvestment() + "\n Forschungsfaktoren: \t"
				+ "\n research: \t" + this.getResearch() + "\n quality: \t" + this.getQualityInvestment() + "\n design: \t" + this.getDesignInvestment() + "\n efficiency: \t"
				+ this.getEfficiencyInvestment();
		return s;
	}

	public double getTvInvestmentKum()
	{
		return tvInvestmentKum;
	}

	public void setTvInvestmentKum(double tvInvestmentKum)
	{
		this.tvInvestmentKum = tvInvestmentKum;
	}

	public double getNewspaperInvestmentKum()
	{
		return newspaperInvestmentKum;
	}

	public double getRadioInvestmentKum()
	{
		return radioInvestmentKum;
	}

	public double getQualityInvestmentKum()
	{
		return qualityInvestmentKum;
	}

	public double getDesignInvestmentKum()
	{
		return designInvestmentKum;
	}

	public double getEcologyInvestmentKum()
	{
		return ecologyInvestmentKum;
	}

	public void setNewspaperInvestmentKum()
	{
		this.newspaperInvestmentKum += this.newspaperInvestment;
	}

	public void setRadioInvestmentKum()
	{
		this.radioInvestmentKum += this.radioInvestment;
	}

	public void setQualityInvestmentKum()
	{
		this.qualityInvestmentKum += this.qualityInvestment;
	}

	public void setDesignInvestmentKum()
	{
		this.designInvestmentKum += this.designInvestment;
	}

	public void setEcologyInvestmentKum()
	{
		this.ecologyInvestmentKum += this.ecologyInvestment;
	}

}