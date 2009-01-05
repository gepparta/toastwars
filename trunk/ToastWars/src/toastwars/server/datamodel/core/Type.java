package toastwars.server.datamodel.core;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum Type implements IsSerializable
{
	TYPE1("Der 1. Typ", 10000, 0.05, 10000.00, 1000.00, 3.00, 4000), 
	TYPE2("Der 2. Typ", 6000, 0.08, 20000.00, 5000.00, 15.00, 1500), 
	TYPE3("Der 3. Typ", 2500, 0.10, 25000.00, 8000.00, 40.00, 500);

	private String description;
	private int marketVolume;
	private double random;
	private double fixCosts;
	private double stepCosts;
	private double variableCosts;
	private int capacity;

	Type(String description, int marketVolume, double random, double fixCost, double stepCosts, double variableCost, int capacity)
	{
		this.description = description;
		this.marketVolume = marketVolume;
		this.random = random;
		this.fixCosts = fixCost;
		this.stepCosts = stepCosts;
		this.variableCosts = variableCost;
		this.capacity = capacity;
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

}