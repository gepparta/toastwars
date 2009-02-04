package toastwars.server.datamodel.core;

import toastwars.util.NumberUtil;

import com.google.gwt.user.client.rpc.IsSerializable;
/*
 * @ author Michael Klein, Alexander Geppart
 */
public enum Type implements IsSerializable {

	// description, random, fixCost, stepCosts,
	// variableCost,capacity,
	// tvInvestmentPlus, radioInvestmentPlus, newspaperInvestmentPlus
	// qualityInvestmentPlus, designInvestmentPlus, ecologyInvestmentPlus

	// Definition der drei verschiedenen Toastertypen und deren Eigenschaften.

	TYPE1("Millenniums-Toaster", 0.05, 10000.00, 1000.00, 2.00, 4000, 20000,
			10000, 5000, 5000, 5000, 5000, 5, 20),
	TYPE2("TIE-Toaster", 0.08, 20000.00, 5000.00, 10.00, 2000, 30000, 20000,
			10000, 10000, 10000, 10000, 30, 60),
	TYPE3("Star-Toaster", 0.10, 25000.00, 8000.00, 40.00, 500, 40000, 30000,
			20000, 15000, 15000, 15000, 130, 200);

	// Definition der Attribute

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

	// Konstruktoren

	private Type(String description, double random, double fixCosts,
			double stepCosts, double variableCosts, int capacity,
			double tvInvestmentPlus, double radioInvestmentPlus,
			double newspaperInvestmentPlus, double qualityInvestmentPlus,
			double designInvestmentPlus, double ecologyInvestmentPlus,
			int minPrice, int maxPrice) {
		this.description = description;
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

	// GET Methoden

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

	// Set-Methoden

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDesignInvestmentPlus(double designInvestmentPlus) {
		this.designInvestmentPlus = designInvestmentPlus;
	}

	public void setEcologyInvestmentPlus(double ecologyInvestmentPlus) {
		this.ecologyInvestmentPlus = ecologyInvestmentPlus;
	}

	public void setFixCosts(double fixCosts) {
		this.fixCosts = fixCosts;
	}

	public void setMarketVolume(int marketVolume) {
		this.marketVolume = marketVolume;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public void setNewspaperInvestmentPlus(double newspaperInvestmentPlus) {
		this.newspaperInvestmentPlus = newspaperInvestmentPlus;
	}

	public void setQualityInvestmentPlus(double qualityInvestmentPlus) {
		this.qualityInvestmentPlus = qualityInvestmentPlus;
	}

	public void setRadioInvestmentPlus(double radioInvestmentPlus) {
		this.radioInvestmentPlus = radioInvestmentPlus;
	}

	public void setRandom(double random) {
		this.random = random;
	}

	public void setStepCosts(double stepCosts) {
		this.stepCosts = stepCosts;
	}

	public void setTvInvestmentPlus(double tvInvestmentPlus) {
		this.tvInvestmentPlus = tvInvestmentPlus;
	}

	public void setVariableCosts(double variableCosts) {
		this.variableCosts = variableCosts;
	}

	public void setMarketVolumeTT1(int user) {
		int volume = (int) (Math.pow((user * 150000), (1 / 1.4)));
		volume = NumberUtil.roundIntUp(volume, 40);
		this.setMarketVolume(volume);
	}

	public void setMarketVolumeTT2(int user) {
		int volume = (int) (Math.pow((user * 12000), (1 / 1.22)));
		volume = NumberUtil.roundIntUp(volume, 40);
		this.setMarketVolume(volume);
	}

	public void setMarketVolumeTT3(int user) {
		int volume = (int) (Math.pow((user * 6000), (1 / 1.29)));
		volume = NumberUtil.roundIntUp(volume, 40);
		this.setMarketVolume(volume);
	}

	// Methoden nur für die JUNIT Tests

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

}// class Type
