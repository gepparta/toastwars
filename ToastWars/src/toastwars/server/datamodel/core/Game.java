package toastwars.server.datamodel.core;

import java.util.ArrayList;
import util.NumberUtil;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Game implements IsSerializable {
	private int userAmount;

	// @gwt.typeArgs <toastwars.server.datamodel.core.Company>
	private ArrayList<Company> companyList = new ArrayList<Company>();

	private int currentRound;

	// Eine (versteckte) Klassenvariable vom Typ der eigenen Klasse
	private static Game instance;

	// *************************Constructor*****************************************************
	public Game(int userAmount) // zum testen auf public gesetzt
	{
		this.userAmount = userAmount;
		try {
			this.setCurrentRound(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.companyList = createInitialData(userAmount, 1.00, 1, 1, 1.00, 1,
		// 1, 1.00, 1, 1, "Comp", 1.00, 1.00, 1.00);
	}

	// **********************Methods************************************************************
	public static Game getInstance(int userAmount) {
		if (instance == null)
			instance = new Game(userAmount);
		return instance;
	}

	public static Game getInstance() {
		return instance;
	}

	public int getUserAmount() {
		return userAmount;
	}

	public void setUserAmount(int userAmount) {
		this.userAmount = userAmount;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	// @ by Alex
	public void setCurrentRound(int currentRound) throws Exception {
		if (currentRound <= this.currentRound) {
			throw new Exception(
					"Invalid Input. Current round is bigger than the requested");
		} else {
			this.currentRound = currentRound;
		}
	}

	public ArrayList<Company> getCompanyList() {
		return companyList;
	}

	// @ by Alex
	public void addCompany(Company com) {
		this.companyList.add(com);
	}

	public void setCompanyList(ArrayList<Company> companyList) {
		this.companyList = companyList;

	}

	// @by Alex
	public void simulate() {
		double[] indexSums = new double[3];

		for (int i = 0; i < companyList.size(); i++) {
			companyList.get(i).calculateIndex();
		}

		indexSums = calculateIndexSums();

		for (int i = 0; i < companyList.size(); i++) {
			companyList.get(i).calculateMarketShares(indexSums);
			companyList.get(i).calculateTurnover();
			companyList.get(i).calculateCost();
			companyList.get(i).calculateProfit();
			companyList.get(i).calculateCapital();
		}
	}

	// @by Alex
	public double[] calculateIndexSums() {
		double[] indexSums = new double[3];
		for (int i = 0; i < companyList.size(); i++) {
			for (int a = 0; a < companyList.get(i).getToasterList().size(); a++) {
				if (companyList.get(i).getToasterList().get(a).getType() == Type.TYPE1)
					indexSums[0] += companyList.get(i).getToasterList().get(a)
							.getIndex();
				if (companyList.get(i).getToasterList().get(a).getType() == Type.TYPE2)
					indexSums[1] += companyList.get(i).getToasterList().get(a)
							.getIndex();
				if (companyList.get(i).getToasterList().get(a).getType() == Type.TYPE3)
					indexSums[2] += companyList.get(i).getToasterList().get(a)
							.getIndex();
			}
		}
		// runden auf zwei Stellen
		for (int i = 0; i < indexSums.length; i++)
			indexSums[i] = NumberUtil.roundDouble(indexSums[i]);

		return indexSums;
	}

	private ArrayList<Company> createInitialData(int userAmount,
			double initialPrice1, int initialMarketing1, int initialResearch1,
			double initialPrice2, int initialMarketing2, int initialResearch2,
			double initialPrice3, int initialMarketing3, int initialResearch3,
			String initialDescription)

	{
		Type Typ1 = Type.TYPE1;
		Type Typ2 = Type.TYPE2;
		Type Typ3 = Type.TYPE3;
		Toaster Toaster1;
		Toaster Toaster2;
		Toaster Toaster3;
		ArrayList<Toaster> toasterList;
		ArrayList<Company> companyList = new ArrayList<Company>();

		for (int i = 1; i == userAmount; i++) {

			Toaster1 = new Toaster(initialPrice1, initialMarketing1,
					initialResearch1, 0.00, 0.00, 0.00, 0.00, 0, Typ1);
			Toaster2 = new Toaster(initialPrice2, initialMarketing2,
					initialResearch2, 0.00, 0.00, 0.00, 0.00, 0, Typ2);
			Toaster3 = new Toaster(initialPrice3, initialMarketing3,
					initialResearch3, 0.00, 0.00, 0.00, 0.00, 0, Typ3);

			toasterList = new ArrayList<Toaster>();
			toasterList.add(Toaster1);
			toasterList.add(Toaster2);
			toasterList.add(Toaster3);

			companyList.add(new Company(initialDescription, 0.00, 0.00, 0.00,
					0.00, 0, toasterList));

		}

		return companyList;
	}

	public void changePrice(Company company, Toaster toaster, double price) {

		company.getToasterList().get(company.getToasterList().indexOf(toaster))
				.setPrice(price);

	}

}
