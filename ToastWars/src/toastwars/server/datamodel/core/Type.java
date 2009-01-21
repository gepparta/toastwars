package toastwars.server.datamodel.core;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum Type implements IsSerializable {
	// description, marketVolume, random, fixCost, stepCosts, variableCost,
	// capacity,
	// tvInvestmentPlus, radioInvestmentPlus newspaperInvestmentPlus
	// qualityInvestmentPlus
	// designInvestmentPlus ecologyInvestmentPlus

	TYPE1("Millenniums-Toaster", 10000, 0.05, 10000.00, 1000.00, 3.00, 2000,
			20000, 10000, 5000, 5000, 5000, 5000, 5, 20),
	TYPE2("TIE-Toaster", 6000, 0.08, 20000.00, 5000.00, 15.00, 1000, 30000,
			20000, 10000, 10000, 10000, 10000, 30, 60),
	TYPE3("Star-Toaster", 2500, 0.10, 25000.00, 8000.00, 40.00, 500, 40000,
			30000, 20000, 15000, 15000, 15000, 130, 200);

	private String	description;
	private int		marketVolume;
	private double	random;
	private double	fixCosts;
	private double	stepCosts;
	private double	variableCosts;
	private int		capacity;
	private double	tvInvestmentPlus;
	private double	radioInvestmentPlus;
	private double	newspaperInvestmentPlus;
	private double	qualityInvestmentPlus;
	private double	designInvestmentPlus;
	private double	ecologyInvestmentPlus;
	private int		minPrice;
	private int		maxPrice;

	private Type(String description, int marketVolume, double random,
			double fixCosts, double stepCosts, double variableCosts,
			int capacity, double tvInvestmentPlus, double radioInvestmentPlus,
			double newspaperInvestmentPlus, double qualityInvestmentPlus,
			double designInvestmentPlus, double ecologyInvestmentPlus,
			int minPrice, int maxPrice) {
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
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getDescription() {
		return this.description;
	}

	public double getDesignInvestmentPlus() {
		return designInvestmentPlus;
	}

	public double getEcologyInvestmentPlus() {
		return ecologyInvestmentPlus;
	}

	public double getFixCosts() {
		return fixCosts;
	}

	public int getMarketVolume() {
		return marketVolume;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public double getNewspaperInvestmentPlus() {
		return newspaperInvestmentPlus;
	}

	public double getQualityInvestmentPlus() {
		return qualityInvestmentPlus;
	}

	public double getRadioInvestmentPlus() {
		return radioInvestmentPlus;
	}

	public double getRandom() {
		return random;
	}

	public double getStepCosts() {
		return stepCosts;
	}

	public double getTvInvestmentPlus() {
		return tvInvestmentPlus;
	}

	public double getVariableCosts() {
		return variableCosts;
	}

	
	public String toString() {

		String s = "Type Eigenschaften: " + "\n description \t \t"
				+ this.getDescription() + "\n marketVolume: \t"
				+ this.getMarketVolume() + "\n random: \t" + this.getRandom()
				+ "\n fixCost: \t" + this.getFixCosts() + "\n stepCosts: \t"
				+ this.getStepCosts() + "\n variableCost: \t"
				+ this.getVariableCosts() + "\n capacity: \t"
				+ this.getCapacity();

		return s;
	}

}