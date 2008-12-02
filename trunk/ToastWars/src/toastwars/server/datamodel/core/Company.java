package toastwars.server.datamodel.core;

public class Company {

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	private double turnover;

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	private double marketShare;

	public double getMarketShare() {
		return marketShare;

	}

	public void setMarketShare(double marketShare) {
		this.marketShare = marketShare;
	}

	private double asset;

	public double getAsset() {
		return asset;
	}

	public void setAsset(double asset) {
		this.asset = asset;
	}
	
	
	
	//////////////KONSTRUKTOR////////////////////
	
	public Company (String description, double turnover, double marketShare, double asset){
		this.description = description;
		this.turnover = turnover;
		this.marketShare = marketShare;
		this.asset = asset;
		
	}

	
	
}
