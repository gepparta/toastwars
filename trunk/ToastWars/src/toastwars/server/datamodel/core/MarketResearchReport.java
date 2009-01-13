package toastwars.server.datamodel.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import toastwars.server.datamodel.user.Group;

public class MarketResearchReport
{
	private Map<Number, String> capitalRankingInternList;
	private Map<Number, String> profitRankingInternList;
	private Map<Number, String> radioRankingInternList;
	private Map<Number, String> tvRankingInternList;
	private Map<Number, String> newspaperRankingInternList;
	private Map<Number, String> designRankingInternList;
	private Map<Number, String> ecologyRankingInternList;
	private Map<Number, String> qualityRankingInternList;

	private Map<Number, String> priceType1RankingInternList;
	private Map<Number, String> marketshareType1RankingInternList;

	private Map<Number, String> priceType2RankingInternList;
	private Map<Number, String> marketshareType2RankingInternList;

	private Map<Number, String> priceType3RankingInternList;
	private Map<Number, String> marketshareType3RankingInternList;

	private Collection values;
	private List capitalRankingList;

	// String[] profitRankingList;
	// String[] radioRankingList;
	// String[] tvRankingList;
	// String[] newspaperRankingList;
	// String[] designRankingList;
	// String[] ecologyRankingList;
	// String[] qualityRankingList;
	//
	// String[] priceType1RankingList;
	// String[] priceType2RankingList;
	// String[] priceType3RankingList;
	//
	// String[] marketshareType1RankingList;
	// String[] marketshareType2RankingList;
	// String[] marketshareType3RankingList;

	public MarketResearchReport()
	{
		capitalRankingInternList = new TreeMap<Number, String>();
		profitRankingInternList = new TreeMap<Number, String>();
		radioRankingInternList = new TreeMap<Number, String>();
		tvRankingInternList = new TreeMap<Number, String>();
		newspaperRankingInternList = new TreeMap<Number, String>();
		designRankingInternList = new TreeMap<Number, String>();
		ecologyRankingInternList = new TreeMap<Number, String>();
		qualityRankingInternList = new TreeMap<Number, String>();

		priceType1RankingInternList = new TreeMap<Number, String>();
		marketshareType1RankingInternList = new TreeMap<Number, String>();

		priceType2RankingInternList = new TreeMap<Number, String>();
		marketshareType2RankingInternList = new TreeMap<Number, String>();

		priceType3RankingInternList = new TreeMap<Number, String>();
		marketshareType3RankingInternList = new TreeMap<Number, String>();

	}

	public void generateMarketResearchReport(ArrayList<Group> grouplist)
	{
		double radioInvestment = 0;
		double tvInvestment = 0;
		double newspaperInvestment = 0;
		double qualityInvestment = 0;
		double designInvestment = 0;
		double ecologyInvestment = 0;
		for (int i = 0; i < grouplist.size(); i++)
		{
			Company com = grouplist.get(i).getCompany();
			capitalRankingInternList.put(com.getCapital(), "Group" + com.getCompanyID());
			profitRankingInternList.put(com.getProfit(), "Group" + com.getCompanyID());
			for (int a = 0; a < com.getToasterList().size(); a++)
			{
				// Investments einer Company zusammenrechnen
				radioInvestment += com.getToasterList().get(a).getRadioInvestment();
				tvInvestment += com.getToasterList().get(a).getTvInvestment();
				newspaperInvestment += com.getToasterList().get(a).getNewspaperInvestment();
				qualityInvestment += com.getToasterList().get(a).getQualityInvestment();
				designInvestment += com.getToasterList().get(a).getDesignInvestment();
				ecologyInvestment += com.getToasterList().get(a).getEfficiencyInvestment();

				if (com.getToasterList().get(a).getType() == Type.TYPE1)
				{
					priceType1RankingInternList.put(com.getToasterList().get(a).getPrice(), "Group" + com.getCompanyID());
					marketshareType1RankingInternList.put(com.getToasterList().get(a).getMarketShare(), "Group" + com.getCompanyID());
				}
				if (com.getToasterList().get(a).getType() == Type.TYPE1)
				{
					priceType2RankingInternList.put(com.getToasterList().get(a).getPrice(), "Group" + com.getCompanyID());
					marketshareType2RankingInternList.put(com.getToasterList().get(a).getMarketShare(), "Group" + com.getCompanyID());

				}
				if (com.getToasterList().get(a).getType() == Type.TYPE1)
				{
					priceType3RankingInternList.put(com.getToasterList().get(a).getPrice(), "Group" + com.getCompanyID());
					marketshareType3RankingInternList.put(com.getToasterList().get(a).getMarketShare(), "Group" + com.getCompanyID());

				}
			}
			radioRankingInternList.put(radioInvestment, "Group" + com.getCompanyID());
			tvRankingInternList.put(tvInvestment, "Group" + com.getCompanyID());
			newspaperRankingInternList.put(newspaperInvestment, "Group" + com.getCompanyID());
			designRankingInternList.put(designInvestment, "Group" + com.getCompanyID());
			ecologyRankingInternList.put(ecologyInvestment, "Group" + com.getCompanyID());
			qualityRankingInternList.put(qualityInvestment, "Group" + com.getCompanyID());
		}
		// mit .values wird nur das zweite String-Attibut der TreeMap zum Array
		// gemacht

		values = capitalRankingInternList.values();
		capitalRankingList = new ArrayList(values);

		// profitRankingList = (String[])
		// profitRankingInternList.values().toArray();
		// radioRankingList = (String[])
		// radioRankingInternList.values().toArray();
		// tvRankingList = (String[]) tvRankingInternList.values().toArray();
		// newspaperRankingList = (String[])
		// newspaperRankingInternList.values().toArray();
		// designRankingList = (String[])
		// designRankingInternList.values().toArray();
		// ecologyRankingList = (String[])
		// ecologyRankingInternList.values().toArray();
		// qualityRankingList = (String[])
		// qualityRankingInternList.values().toArray();
		//
		// priceType1RankingList = (String[])
		// priceType1RankingInternList.values().toArray();
		// priceType2RankingList = (String[])
		// priceType2RankingInternList.values().toArray();
		// priceType3RankingList = (String[])
		// priceType3RankingInternList.values().toArray();
		//
		// marketshareType1RankingList = (String[])
		// marketshareType1RankingInternList.values().toArray();
		// marketshareType2RankingList = (String[])
		// marketshareType2RankingInternList.values().toArray();
		// marketshareType3RankingList = (String[])
		// marketshareType3RankingInternList.values().toArray();
	}

	public List getCapitalRankingList()
	{
		return capitalRankingList;
	}
}
