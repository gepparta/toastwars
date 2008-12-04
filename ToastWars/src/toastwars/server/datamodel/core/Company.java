package toastwars.server.datamodel.core;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Company implements IsSerializable {

	private String	description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	private double	turnover;

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	private double	marketShare;

	public double getMarketShare() {
		return marketShare;

	}

	public void setMarketShare(double marketShare) {
		this.marketShare = marketShare;
	}

	private double	asset;

	public double getAsset() {
		return asset;
	}

	public void setAsset(double asset) {
		this.asset = asset;
	}

	// @gwt.typeArgs <toastwars.server.datamodel.core.Toaster>
	private ArrayList<Toaster>	toasterList;

	public ArrayList<Toaster> getToasterList() {
		return toasterList;
	}

	public void setToasterList(ArrayList<Toaster> toasterList) {
		this.toasterList = toasterList;
	}

	// ////////////KONSTRUKTOR////////////////////

	public Company() {
	}

	public Company(String description, double turnover, double marketShare,
			double asset, ArrayList<Toaster> toasterList) {
		this.description = description;
		this.turnover = turnover;
		this.marketShare = marketShare;
		this.asset = asset;
		this.toasterList = toasterList;

	}

	/*
	 * 
	 * public void changePrice(Toaster toaster, double price){
	 * 
	 * 
	 * price = price/10; Toaster T =
	 * toasterList.get(toasterList.indexOf(toaster)); T.setPrice(price); }
	 */
}
