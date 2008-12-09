package toastwars.server.datamodel.core;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum Type implements IsSerializable {
	TYPE1("Der 1. Typ", 10000,0.05,5000.00,10.00), 
	TYPE2("Der 2. Typ", 20000,0.08,5000.00,10.00), 
	TYPE3("Der 3. Typ",30000,0.1,5000.00,10.00);

	private String description;
	private int marketVolume;
	private double random;
	private double fixCost;
	private double variableCost;

	
	Type(String description, int marketVolume, double random, double fixCost, double variableCost) {
		this.description = description;
		this.marketVolume = marketVolume;
		this.random = random;
		this.fixCost = fixCost;
		this.variableCost = variableCost;
	}
	
	public double getFixCost() {
		return fixCost;
	}

	public void setFixCost(double fixCost) {
		this.fixCost = fixCost;
	}

	public double getVariableCost() {
		return variableCost;
	}

	public void setVariableCost(double variableCost) {
		this.variableCost = variableCost;
	}



	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public int getMarketVolume() {
		return marketVolume;
	}

	public void setMarketVolume(int marketVolume) {
		this.marketVolume = marketVolume;
	}

	public double getRandom() {
		return random;
	}

	public void setRandom(double random) {
		this.random = random;
	}

}