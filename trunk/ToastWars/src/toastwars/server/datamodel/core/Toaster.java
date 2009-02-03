package toastwars.server.datamodel.core;

import toastwars.util.NumberUtil;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Toaster implements IsSerializable {
	// Deklaration der Attribute
	private double	price;
	private double	index;
	private double	turnover;
	private double	cost;
	private double	profit;
	private int		marketShare;
	private int		production;
	private int	 	tmpProduction;
	private Type	type;
	// Faktoren für marketing**************************************
	private double	marketing;
	private double	tvInvestment;
	private double	newspaperInvestment;
	private double	radioInvestment;
	private double	tvInvestmentKum;
	private double	newspaperInvestmentKum;
	private double	radioInvestmentKum;
	// Faktoren für research**************************************
	private double	research;
	private double	qualityInvestment;
	private double	designInvestment;
	private double	ecologyInvestment;
	private double	qualityInvestmentKum;
	private double	designInvestmentKum;
	private double	ecologyInvestmentKum;

	// Konstruktoren
	public Toaster() {
	}

	

	public Toaster(double price, double index, double turnover, double cost,
			double profit, int marketShare, Type type, double marketing,
			double tvInvestment, double newspaperInvestment,
			double radioInvestment, double tvInvestmentKum,
			double newspaperInvestmentKum, double radioInvestmentKum,
			double research, double qualityInvestment, double designInvestment,
			double ecologyInvestment, double qualityInvestmentKum,
			double designInvestmentKum, double ecologyInvestmentKum,int production) {
		this.price = price;
		this.index = index;
		this.turnover = turnover;
		this.cost = cost;
		this.profit = profit;
		this.marketShare = marketShare;
		this.type = type;
		this.marketing = marketing;
		this.tvInvestment = tvInvestment;
		this.newspaperInvestment = newspaperInvestment;
		this.radioInvestment = radioInvestment;
		this.tvInvestmentKum = tvInvestmentKum;
		this.newspaperInvestmentKum = newspaperInvestmentKum;
		this.radioInvestmentKum = radioInvestmentKum;
		this.research = research;
		this.qualityInvestment = qualityInvestment;
		this.designInvestment = designInvestment;
		this.ecologyInvestment = ecologyInvestment;
		this.qualityInvestmentKum = qualityInvestmentKum;
		this.designInvestmentKum = designInvestmentKum;
		this.ecologyInvestmentKum = ecologyInvestmentKum;
		this.production = production;
	}

//Get Methoden

	public void calculateCost()
	{

		this.setCost((this.getProduction() * this.type.getVariableCosts())
				+ (Math.ceil((double) this.getMarketShare() / this.getType().getCapacity()) * this.getType().getStepCosts()) + (this.type.getFixCosts()));
	}

	// In dieser Methode wird der Gesamtindex des Toasters anhand der Toasterattributwerte berechnet.
	public void calculateIndex()
	{
		double marketingIndex = calculateMarketing();
		double researchIndex = calculateResearch();
		//Umrechnung des Euro-Preises in Indexwert für die weitere Berechnung.
		double priceIndex = 0;
		if (this.type == Type.TYPE1)
			priceIndex = (1 / Math.pow((price / 10), 1.5));
		if (this.type == Type.TYPE2)
			priceIndex = (1 / Math.pow((price / 40), 2.0));
		if (this.type == Type.TYPE3)
			priceIndex = (1 / Math.pow((price / 150), 3));
		double i = researchIndex * marketingIndex * priceIndex;

		// Runden auf zwei Stellen hinter dem Komma und setzen des eigenen Objektattributs auf errechneten Wert.
		setIndex(NumberUtil.roundDouble(i));
	}

	// In dieser Methode wird der neue Marketingindex abhängig vom Type ermittelt und gesetzt.
// Hierzu werden zunächst die eingegebenen Investitionen der drei Faktoren tv,radio und newspaper verarbeitet und eingerechnet.
// Daraus wird anhand von Formeln der Index berechnet und das eigene Objektattribut gesetzt.
	public double calculateMarketing()
	{
		double tvInvestmentIndex = 0;
		double radioInvestmentIndex = 0;
		double newsPaperInvestmentIndex = 0;
		if (this.type == Type.TYPE1)
		{
			double tv = this.tvInvestmentKum + Type.TYPE1.getTvInvestmentPlus();
			double radio = this.radioInvestmentKum + Type.TYPE1.getRadioInvestmentPlus();
			double newspaper = this.newspaperInvestmentKum + Type.TYPE1.getNewspaperInvestmentPlus();
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
			double tv = this.tvInvestmentKum + Type.TYPE3.getTvInvestmentPlus();
			double radio = this.radioInvestmentKum + Type.TYPE3.getRadioInvestmentPlus();
			double newspaper = this.getNewspaperInvestmentKum() + Type.TYPE3.getNewspaperInvestmentPlus();
			tvInvestmentIndex = NumberUtil.roundDouble(((Math.pow(tv / 10000 - 8, 3) + Math.pow(tv / 10000 - 8, 2) + Math.pow(tv / 10000 - 8, 1)) / 150) + 1.35);
			radioInvestmentIndex = NumberUtil.roundDouble((Math.log(radio / 3000)) * (radio / Math.pow(radio, 1.03)) - 0.69);

			newsPaperInvestmentIndex = NumberUtil.roundDouble((Math.log(newspaper / 3000)) * (newspaper / Math.pow(newspaper, 1.09)) + 0.22);

		}
		double d = tvInvestmentIndex + radioInvestmentIndex + newsPaperInvestmentIndex;
		

		
		this.setMarketing(NumberUtil.roundDouble(d));
		return NumberUtil.roundDouble(d);
	}

	// In der folgenden Methode werden die Marktanteile der einzelen Toaster berechnet. Hierzu wird der Gesamtindex aller Toaster 
// eines Types benötigt. Dieser wird als Parameter übergeben. Nach der Berechnung wird das entsprechende Objektattribut gesetzt.
	public void calculateMarketShare(double IndexSum)
	{

		this.setMarketShare((int) Math.round(this.type.getMarketVolume() / IndexSum * this.index));

	}

	// In dieser Methode wird der Profit/Gewinn errechnet und das entsprechende Objektattribut gesetzt.
	public void calculateProfit()
	{
		this.setProfit(this.getTurnover() - this.getCost());
	}

	// In dieser Methode wird der neue Forschungsindex abhängig vom Type ermittelt und gesetzt.
	// Hierzu werden zunächst die eingegebenen Investitionen der drei Faktoren quality, design, efficiency verarbeitet und eingerechnet.
	// Daraus wird anhand von Formeln der Index berechnet und das eigene Objektattribut gesetzt.
	public double calculateResearch()
	{
		double qualityIndex = 0;
		double designIndex = 0;
		double ecologyIndex = 0;
		if (this.type == Type.TYPE1)
		{

			double quality = this.qualityInvestmentKum + Type.TYPE1.getQualityInvestmentPlus();
			double design = this.designInvestmentKum + Type.TYPE1.getDesignInvestmentPlus();
			double ecology = this.ecologyInvestmentKum + Type.TYPE1.getEcologyInvestmentPlus();
					
			qualityIndex = NumberUtil.roundDouble((Math.log(quality / 3000)) * (quality / Math.pow(quality, 1.04)) + 0.635);
			designIndex = NumberUtil.roundDouble((Math.log(design / 2000)) * (design / Math.pow(design, 1.0658)) + 0.48);
			ecologyIndex = NumberUtil.roundDouble((Math.log(ecology / 1000)) * (ecology / Math.pow(ecology, 1.0992)) + 0.31);

		}
		if (this.type == Type.TYPE2)
		{

			double quality = this.qualityInvestmentKum + Type.TYPE2.getQualityInvestmentPlus();
			double design = this.designInvestmentKum + Type.TYPE2.getDesignInvestmentPlus();
			double ecology = this.ecologyInvestmentKum + Type.TYPE2.getEcologyInvestmentPlus();
			
			qualityIndex = NumberUtil.roundDouble((Math.log(quality / 3000)) * (quality / Math.pow(quality, 1.04)) + 0.165);
			designIndex = NumberUtil.roundDouble((Math.log(design / 2000)) * (design / Math.pow(design, 1.0658)) + 0.12);
			ecologyIndex = NumberUtil.roundDouble((Math.log(ecology / 1000)) * (ecology / Math.pow(ecology, 1.0992)) + 0.08);

		}

		if (this.type == Type.TYPE3)
		{

			double quality = this.qualityInvestmentKum + Type.TYPE3.getQualityInvestmentPlus();
			double design = this.designInvestmentKum + Type.TYPE3.getDesignInvestmentPlus();
			double ecology = this.ecologyInvestmentKum + Type.TYPE3.getEcologyInvestmentPlus();
			
			qualityIndex = NumberUtil.roundDouble((Math.log(quality / 3000)) * (quality / Math.pow(quality, 1.04)) - 0.1);
			designIndex = NumberUtil.roundDouble((Math.log(design / 2000)) * (design / Math.pow(design, 1.0658)) - 0.07);
			ecologyIndex = NumberUtil.roundDouble((Math.log(ecology / 1000)) * (ecology / Math.pow(ecology, 1.0992)) - 0.04);
		}
		double d = qualityIndex + designIndex + ecologyIndex;
		

		
		this.setResearch(NumberUtil.roundDouble(d));
		return NumberUtil.roundDouble(d);
	}

	// In dieser Methode wird der Umsatz errechnet und das entsprechende Objektattribut gesetzt.
	public void calculateTurnover()
	{

		this.setTurnover(this.getMarketShare() * this.getPrice());
	}

	private void changePriceWithOutCheck(double price)
	{
		this.price = price;
	}

	public void destroyToaster()
	{
		this.changePriceWithOutCheck(0.0);
		this.setCost(0.0);
		this.setDesignInvestment(0);
		this.setEcologyInvestment(0.0);
		this.setIndex(0.0);
		this.setMarketing(0.0);
		this.setMarketShare(0);
		this.setNewspaperInvestment(0.0);
		this.setProduction(0);
		this.setProfit(0.0);
		this.setQualityInvestment(0.0);
		this.setRadioInvestment(0.0);
		this.setResearch(0.0);
		this.setTurnover(0.0);
		this.setTvInvestment(0.0);
	}

	public double getCost()
	{
		return cost;
	}

	public double getDesignInvestment()
	{
		return designInvestment;
	}

	public double getDesignInvestmentKum()
	{
		return designInvestmentKum;
	}

	public double getEcologyInvestment()
	{
		return ecologyInvestment;
	}

	public double getEcologyInvestmentKum()
	{
		return ecologyInvestmentKum;
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


	public double getNewspaperInvestmentKum()
	{
		return newspaperInvestmentKum;
	}

	public double getPrice()
	{
		return price;
	}

	public int getProduction() {
		return production;
	}

	public double getProfit()
	{
		return profit;
	}

	public double getQualityInvestment()
	{
		return qualityInvestment;
	}

	public double getQualityInvestmentKum()
	{
		return qualityInvestmentKum;
	}

	public double getRadioInvestment()
	{
		return radioInvestment;
	}

	public double getRadioInvestmentKum()
	{
		return radioInvestmentKum;
	}

	public double getResearch()
	{
		return research;
	}

	public double getTurnover()
	{
		return turnover;
	}

	public double getTvInvestment()
	{
		return tvInvestment;
	}

	public double getTvInvestmentKum()
	{
		return tvInvestmentKum;
	}

	public Type getType()
	{
		return type;
	}

	// In dieser Methode werden alle Attribute in denen Investitionen getätigt wurden auf 0 zurückgesetzt.
	public void resetUserInput() {
			
			tvInvestment = 0;
			newspaperInvestment = 0;
			radioInvestment = 0;
			qualityInvestment = 0;
			designInvestment = 0;
			ecologyInvestment = 0;
			production = 0;
		}

	public void setCost(double cost)
	{
		this.cost = cost;
	}
	public void setDesignInvestment(double design) 
	{

		this.designInvestment = design;
	}
	
	public void setDesignInvestmentKum()
	{
		this.designInvestmentKum += this.designInvestment;
	}

	public void setEcologyInvestment(double ecology) 
	{
		this.ecologyInvestment = ecology;
	}

	public void setEcologyInvestmentKum()
	{
		this.ecologyInvestmentKum += this.ecologyInvestment;
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

	public void setNewspaperInvestment(double newsPaperInvestment) 
	{
		this.newspaperInvestment = newsPaperInvestment;
	}

	public void setNewspaperInvestmentKum()
	{
		this.newspaperInvestmentKum += this.newspaperInvestment;
	}
	
	public void setPrice(double price) throws Exception
	{
		if (this.type == Type.TYPE1)
		{
			if (price <= 20 && price >= 5)
				this.price = price;
			else
				throw new Exception("Ungültiger Preis");
		}
		if (this.type == Type.TYPE2)
		{
			if (price <= 60 && price >= 30)
				this.price = price;
			else
				throw new Exception("Ungültiger Preis");
		}
		if (this.type == Type.TYPE3)
		{
			if (price <= 200 && price >= 130)
				this.price = price;
			else
				throw new Exception("Ungültiger Preis");
		}
	}

	public void setProduction(int production) {
		this.production = production;
	}

	public void setProfit(double profit)
	{
		this.profit = profit;
	}
	public void setQualityInvestment(double quality) 
	{

		this.qualityInvestment = quality;

	}
	
	//Berechnende Methoden
	
	
	// In dieser Methode werden die Kosten des Toasters kalkuliert und das entsprechende Attribut gesetzt. 
	// Dazu werden die variablen Kosten, die sprungfixen Kosten und die fixen Kosten berechnet und addiert.

	public void setQualityInvestmentKum()
	{
		this.qualityInvestmentKum += this.qualityInvestment ;
	}

	public void setRadioInvestment(double radioInvestment)
	{

		this.radioInvestment = radioInvestment;

	}



public void setRadioInvestmentKum()
	{
		this.radioInvestmentKum += this.radioInvestment;
	}
public void setResearch(double research)
	{
		this.research = research;
	}
public void setTurnover(double turnover)
	{
		this.turnover = turnover;
	}

	public void setTvInvestment(double tvInvestment) 
	{

		this.tvInvestment = tvInvestment;

	}
	public void setTvInvestmentKum()
	{
		this.tvInvestmentKum += this.tvInvestment;
	}

	public void setType(Type type)
{
	this.type = type;
}


//Methoden nur für die Tests
	
	public String toString()
	{

		String s = "Toaster Eigenschaften: " + "\n price: \t" + this.getPrice()
				+ "\n index: \t" + this.getIndex() + "\n turnover: \t"
				+ this.getTurnover() + "\n cost: \t" + this.getCost()
				+ "\n profit: \t" + this.getProfit() + "\n marketShare: \t"
				+ this.getMarketShare() + "\n type: \t" + this.getType().name()
				+ "\n Marketingfaktoren: \t" + "\n marketing: \t"
				+ this.getMarketing() + "\n TV Investment: \t"
				+ this.getTvInvestment() + "\n Newspaper Investment: \t"
				+ this.getNewspaperInvestment() + "\n Radio Investment: \t"
				+ this.getRadioInvestment() + "\n Forschungsfaktoren: \t"
				+ "\n research: \t" + this.getResearch() + "\n quality: \t"
				+ this.getQualityInvestment() + "\n design: \t"
				+ this.getDesignInvestment() + "\n efficiency: \t"
				+ this.getEcologyInvestment();
		return s;
	}



	public int getTmpProduction()
	{
		return tmpProduction;
	}



	public void setTmpProduction(int tmpProduction)
	{
		this.tmpProduction = tmpProduction;
	}
	}//Toaster