package toastwars.server.datamodel.core;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum Type implements IsSerializable
{
	// description, marketVolume, random, fixCost, stepCosts, variableCost,
	// capacity,
	// tvInvestmentPlus, radioInvestmentPlus newspaperInvestmentPlus
	// qualityInvestmentPlus
	// designInvestmentPlus ecologyInvestmentPlus

	TYPE1("Der 1. Typ", 10000, 0.05, 10000.00, 1000.00, 3.00, 2000, 20000, 10000, 5000, 5000, 5000, 5000), 
	TYPE2("Der 2. Typ", 6000, 0.08, 20000.00, 5000.00, 15.00, 1000, 30000, 20000, 10000, 10000, 10000, 10000), 
	TYPE3("Der 3. Typ", 2500, 0.10, 25000.00, 8000.00, 40.00, 500, 40000, 30000, 20000, 15000, 15000, 15000);

	private String description;
	private int marketVolume;
	private double random;
	private double fixCosts;
	private double stepCosts;
	private double variableCosts;
	private int capacity;
	private double tvInvestmentPlus;
	private double radioInvestmentPlus;
	private double newspaperInvestmentPlus;
	private double qualityInvestmentPlus;
	private double designInvestmentPlus;
	private double ecologyInvestmentPlus;

	private Type(String description, int marketVolume, double random, double fixCosts, double stepCosts, double variableCosts, int capacity, double tvInvestmentPlus,
			double radioInvestmentPlus, double newspaperInvestmentPlus, double qualityInvestmentPlus, double designInvestmentPlus, double ecologyInvestmentPlus)
	{
		this.description = description;
		this.marketVolume = marketVolume;
		this.random = random;
		this.fixCosts = fixCosts;
		this.stepCosts = stepCosts;
		this.variableCosts = variableCosts;
		this.capacity = capacity;
		this.tvInvestmentPlus = tvInvestmentPlus;
		this.radioInvestmentPlus = radioInvestmentPlus;
		this.newspaperInvestmentPlus = newspaperInvestmentPlus;
		this.qualityInvestmentPlus = qualityInvestmentPlus;
		this.designInvestmentPlus = designInvestmentPlus;
		this.ecologyInvestmentPlus = ecologyInvestmentPlus;
	}

	public int getCapacity()
	{
		return capacity;
	}

	public String getDescription()
	{
		return this.description;
	}

	public double getFixCosts()
	{
		return fixCosts;
	}

	public int getMarketVolume()
	{
		return marketVolume;
	}

	public double getRandom()
	{
		return random;
	}

	public double getStepCosts()
	{
		return stepCosts;
	}

	public double getVariableCosts()
	{
		return variableCosts;
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setFixCosts(double fixCosts)
	{
		this.fixCosts = fixCosts;
	}

	public void setMarketVolume(int marketVolume)
	{
		this.marketVolume = marketVolume;
	}

	public void setRandom(double random)
	{
		this.random = random;
	}

	public void setVariableCosts(double variableCosts)
	{
		this.variableCosts = variableCosts;
	}

	public void setStepCosts(double stepCosts)
	{
		this.stepCosts = stepCosts;
	}

	public String toString()
	{

		String s = "Type Eigenschaften: " + "\n description \t \t" + this.getDescription() + "\n marketVolume: \t" + this.getMarketVolume() + "\n random: \t" + this.getRandom()
				+ "\n fixCost: \t" + this.getFixCosts() + "\n stepCosts: \t" + this.getStepCosts() + "\n variableCost: \t" + this.getVariableCosts() + "\n capacity: \t"
				+ this.getCapacity();

		return s;
	}

	public double getTvInvestmentPlus()
	{
		return tvInvestmentPlus;
	}

	public void setTvInvestmentPlus(double tvInvestmentPlus)
	{
		this.tvInvestmentPlus = tvInvestmentPlus;
	}

	public double getRadioInvestmentPlus()
	{
		return radioInvestmentPlus;
	}

	public void setRadioInvestmentPlus(double radioInvestmentPlus)
	{
		this.radioInvestmentPlus = radioInvestmentPlus;
	}

	public double getNewspaperInvestmentPlus()
	{
		return newspaperInvestmentPlus;
	}

	public double getQualityInvestmentPlus()
	{
		return qualityInvestmentPlus;
	}

	public double getDesignInvestmentPlus()
	{
		return designInvestmentPlus;
	}

	public double getEcologyInvestmentPlus()
	{
		return ecologyInvestmentPlus;
	}

	public void setNewspaperInvestmentPlus(double newspaperInvestmentPlus)
	{
		this.newspaperInvestmentPlus = newspaperInvestmentPlus;
	}

	public void setQualityInvestmentPlus(double qualityInvestmentPlus)
	{
		this.qualityInvestmentPlus = qualityInvestmentPlus;
	}

	public void setDesignInvestmentPlus(double designInvestmentPlus)
	{
		this.designInvestmentPlus = designInvestmentPlus;
	}

	public void setEcologyInvestmentPlus(double ecologyInvestmentPlus)
	{
		this.ecologyInvestmentPlus = ecologyInvestmentPlus;
	}

}