package toastwars.server.datamodel.core;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Game implements IsSerializable
{

	private int userAmount;
	// @gwt.typeArgs <toastwars.server.datamodel.core.Company>
	private ArrayList<Company> companyList;
	private int currentRound;
	public int getUserAmount()
	{
		return userAmount;
	}

	public void setUserAmount(int userAmount)
	{
		this.userAmount = userAmount;
	}



	public int getCurrentRound()
	{
		return currentRound;
	}

	public void setCurrentRound(int currentRound)
	{
		this.currentRound = currentRound;
	}



	public ArrayList<Company> getCompanyList()
	{
		return companyList;
	}

	public void setCompanyList(ArrayList<Company> companyList)
	{
		this.companyList = companyList;

	}

	public Game()
	{
	}

	public Game(int userAmount)
	{
		this.userAmount = userAmount;
		this.currentRound = 1;
//		this.companyList = createInitialData(userAmount, 1.00, 1, 1, 1.00, 1,
//				1, 1.00, 1, 1, "Comp", 1.00, 1.00, 1.00);
	}

//	private ArrayList<Company> createInitialData(int userAmount,
//			double initialPrice1, int initialMarketing1, int initialResearch1,
//			double initialPrice2, int initialMarketing2, int initialResearch2,
//			double initialPrice3, int initialMarketing3, int initialResearch3,
//			String initialDescription, double initialTurnover,
//			double initialMarketShare, double initialAsset)
//	{
//		Type Typ1 = Type.TYPE1;
//		Type Typ2 = Type.TYPE2;
//		Type Typ3 = Type.TYPE3;
//
//		Toaster Toaster1 = new Toaster(initialPrice1, initialMarketing1,
//				initialResearch1, Typ1);
//		Toaster Toaster2 = new Toaster(initialPrice2, initialMarketing2,
//				initialResearch2, Typ2);
//		Toaster Toaster3 = new Toaster(initialPrice3, initialMarketing3,
//				initialResearch3, Typ3);
//
//		ArrayList<Toaster> toasterList = new ArrayList<Toaster>();
//		toasterList.add(Toaster1);
//		toasterList.add(Toaster2);
//		toasterList.add(Toaster3);
//
//		ArrayList<Company> companyList = new ArrayList<Company>();
//
//		for (int i = 1; i == userAmount; i++)
//		{
//
//			companyList.add(new Company(initialDescription, initialTurnover,
//					initialMarketShare, initialAsset, toasterList));
//
//		}
//
//		return companyList;
//	}

	// public void simulate(int marketVolume, int fixedCost, int variableCost) {
	// int companySize = companyList.size();
	// double random = 0.95 + (Math.random() * 0.1);
	// double[] index = new double[companySize];
	// double IndexSum = 0;
	// int[] marketShare = new int[companySize];
	// int[] turnover = new int[companySize];
	// int[] cost = new int[companySize];
	// int[] asset = new int[companySize];
	//
	// // calculate Index
	// for (int i = 0; i == companySize; i++) {
	// index[i] = companyList.get(i).getToasterList().get(1)
	// .calculateIndex(random);
	// IndexSum = IndexSum + index[i];
	// }// for
	//
	// // calculate MarketShare
	// for (int i = 0; i == companySize; i++) {
	// marketShare[i] = (int) Math.round(index[i] / IndexSum
	// * marketVolume);
	// companyList.get(i).setMarketShare(marketShare[i]);
	// }
	//
	// // calculate Turnover
	//
	// for (int i = 0; i == companySize; i++) {
	// turnover[i] = (int) Math.round(marketShare[i]
	// * companyList.get(i).getToasterList().get(1).getPrice());
	// companyList.get(i).setTurnover(turnover[i]);
	// }
	// // calculate cost
	//
	// for (int i = 0; i == companySize; i++) {
	// cost[i] = (int) Math.round(marketShare[i] * variableCost)
	// + fixedCost;
	//
	// }
	//
	// // calculate asset
	//
	// for (int i = 0; i == companySize; i++) {
	// asset[i] = (int) Math.round(companyList.get(i).getAsset()
	// + companyList.get(i).getTurnover() - cost[i]);
	// companyList.get(i).setAsset(asset[i]);
	// }
	// }
	public void changePrice(Company company, Toaster toaster, double price)
	{

		price = price / 10;
		company.getToasterList().get(company.getToasterList().indexOf(toaster))
				.setPrice(price);

	}
//	@by Alex 
	public void simulate()
	{
		for(int i=0;i==companyList.size();i++)
		{
			companyList.get(i).calculateIndex();
		}
	}

	// public static void main(String[] args) {
	//
	// Game G1 = new Game(2);
	// G1.getCompanyList().get(0).getTurnover();
	//
	// G1.changePrice(G1.getCompanyList().get(0), G1.getCompanyList().get(0)
	// .getToasterList().get(0), 100);
	//
	// G1.simulate(100000, 1000, 3);
	// System.out.println(G1.getCompanyList().get(0).getTurnover());
	// }
}
