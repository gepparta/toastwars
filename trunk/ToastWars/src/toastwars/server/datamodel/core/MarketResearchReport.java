package toastwars.server.datamodel.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import toastwars.server.datamodel.user.Group;

public class MarketResearchReport implements Comparator

{
	private static MarketResearchReport instance;

	public static MarketResearchReport getInstance()
	{
		if (instance == null)
		{
			instance = new MarketResearchReport();
		}
		return instance;
	}

	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> capitalRankingInternList;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> profitRankingInternList;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> radioRankingInternList;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> tvRankingInternList;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> newspaperRankingInternList;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> designRankingInternList;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> ecologyRankingInternList;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> qualityRankingInternList;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> priceType1RankingInternList;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> marketshareType1RankingInternList;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> priceType2RankingInternList;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> marketshareType2RankingInternList;

	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> priceType3RankingInternList;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> marketshareType3RankingInternList;
	// @gwt.typeArgs <java.lang.String>
	private Collection<String> values;
	// @gwt.typeArgs <java.lang.String>
	private List<String> capitalRankingList;
	// @gwt.typeArgs <java.lang.String>
	private List<String> profitRankingList;
	// @gwt.typeArgs <java.lang.String>
	private List<String> radioRankingList;
	// @gwt.typeArgs <java.lang.String>
	private List<String> tvRankingList;
	// @gwt.typeArgs <java.lang.String>
	private List<String> newspaperRankingList;
	// @gwt.typeArgs <java.lang.String>
	private List<String> designRankingList;
	// @gwt.typeArgs <java.lang.String>
	private List<String> ecologyRankingList;
	// @gwt.typeArgs <java.lang.String>
	private List<String> qualityRankingList;
	// @gwt.typeArgs <java.lang.String>
	private List<String> priceType1RankingList;
	// @gwt.typeArgs <java.lang.String>
	private List<String> priceType2RankingList;
	// @gwt.typeArgs <java.lang.String>
	private List<String> priceType3RankingList;
	// @gwt.typeArgs <java.lang.String>
	private List<String> marketshareType1RankingList;

	// @gwt.typeArgs <java.lang.String>
	private List<String> marketshareType2RankingList;

	// @gwt.typeArgs <java.lang.String>
	private List<String> marketshareType3RankingList;

	private HashMap<String, Number> hashMap;

	private MarketResearchReport()
	{
		capitalRankingInternList = new TreeMap<Number, String>(this);
		profitRankingInternList = new TreeMap<Number, String>(this);
		radioRankingInternList = new TreeMap<Number, String>(this);
		tvRankingInternList = new TreeMap<Number, String>(this);
		newspaperRankingInternList = new TreeMap<Number, String>(this);
		designRankingInternList = new TreeMap<Number, String>(this);
		ecologyRankingInternList = new TreeMap<Number, String>(this);
		qualityRankingInternList = new TreeMap<Number, String>(this);

		priceType1RankingInternList = new TreeMap<Number, String>(this);
		marketshareType1RankingInternList = new TreeMap<Number, String>(this);

		priceType2RankingInternList = new TreeMap<Number, String>(this);
		marketshareType2RankingInternList = new TreeMap<Number, String>(this);

		priceType3RankingInternList = new TreeMap<Number, String>(this);
		marketshareType3RankingInternList = new TreeMap<Number, String>(this);

	}

	public int compare(Object arg0, Object arg1)
	{
		double dd1 = (double) Double.parseDouble(arg0.toString());
		double dd2 = (double) Double.parseDouble(arg1.toString());
		return (dd1 > dd2) ? -1 : (dd1 < dd2) ? 1 : (dd1 == dd2) ? 1 : (arg1.toString()).compareTo(arg0.toString());
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
				if (com.getToasterList().get(a).getType() == Type.TYPE2)
				{
					priceType2RankingInternList.put(com.getToasterList().get(a).getPrice(), "Group" + com.getCompanyID());
					marketshareType2RankingInternList.put(com.getToasterList().get(a).getMarketShare(), "Group" + com.getCompanyID());

				}
				if (com.getToasterList().get(a).getType() == Type.TYPE3)
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

			radioInvestment = 0;
			tvInvestment = 0;
			newspaperInvestment = 0;
			qualityInvestment = 0;
			designInvestment = 0;
			ecologyInvestment = 0;
		}
		// mit .values wird nur das zweite String-Attibut der TreeMap zur
		// ArrayList gemacht
		capitalRankingList = new ArrayList(Arrays.asList(generateStringArray(capitalRankingInternList)));

		profitRankingList = new ArrayList(Arrays.asList(generateStringArray(profitRankingInternList)));

		values = radioRankingInternList.values();
		radioRankingList = new ArrayList<String>(values);

		values = tvRankingInternList.values();
		tvRankingList = new ArrayList<String>(values);

		values = newspaperRankingInternList.values();
		newspaperRankingList = new ArrayList<String>(values);

		values = designRankingInternList.values();
		designRankingList = new ArrayList<String>(values);

		values = ecologyRankingInternList.values();
		ecologyRankingList = new ArrayList<String>(values);

		values = qualityRankingInternList.values();
		qualityRankingList = new ArrayList<String>(values);

		priceType1RankingList = new ArrayList<String>(Arrays.asList(generateStringArray(priceType1RankingInternList)));
		priceType2RankingList = new ArrayList<String>(Arrays.asList(generateStringArray(priceType2RankingInternList)));
		priceType3RankingList = new ArrayList<String>(Arrays.asList(generateStringArray(priceType3RankingInternList)));

		marketshareType1RankingList = new ArrayList<String>(Arrays.asList(generateStringArray(marketshareType1RankingInternList)));
		marketshareType2RankingList = new ArrayList<String>(Arrays.asList(generateStringArray(marketshareType2RankingInternList)));
		marketshareType3RankingList = new ArrayList<String>(Arrays.asList(generateStringArray(marketshareType3RankingInternList)));

	}

	private String[] generateStringArray(Map map)
	{
		String[] werte = new String[map.size()];
		Iterator it = map.entrySet().iterator();
		int i = 0;
		while (it.hasNext())
		{
			Map.Entry me = (Map.Entry) it.next();
			werte[i] = me.getValue().toString() + " " + me.getKey();
			i++;
		}
		return werte;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getCapitalRankingList()
	{
		return capitalRankingList;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getDesignRankingList()
	{
		return designRankingList;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getEcologyRankingList()
	{
		return ecologyRankingList;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getMarketshareType1RankingList()
	{
		return marketshareType1RankingList;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getMarketshareType2RankingList()
	{
		return marketshareType2RankingList;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getMarketshareType3RankingList()
	{
		return marketshareType3RankingList;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getNewspaperRankingList()
	{
		return newspaperRankingList;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getPriceType1RankingList()
	{
		return priceType1RankingList;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getPriceType2RankingList()
	{
		return priceType2RankingList;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getPriceType3RankingList()
	{
		return priceType3RankingList;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getProfitRankingList()
	{
		return profitRankingList;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getQualityRankingList()
	{
		return qualityRankingList;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getRadioRankingList()
	{
		return radioRankingList;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> generateHeaders()
	{
		List<String> liste = new ArrayList<String>();
		liste.add("Kapital");
		liste.add("Gewinn");
		liste.add("Radio");
		liste.add("TV");
		liste.add("Zeitung");
		liste.add("Design");
		liste.add("Ökologie");
		liste.add("Qualität");
		liste.add("Marktanteile : Markt 1");
		if (MarketResearchReport.getInstance().getMarketshareType2RankingList().size() != 0)
			liste.add("Marktanteile : Markt 2");
		if (MarketResearchReport.getInstance().getMarketshareType3RankingList().size() != 0)
			liste.add("Marktanteile : Markt 3");
		liste.add("Preisrangliste für Markt 1");
		if (MarketResearchReport.getInstance().getPriceType2RankingList().size() != 0)
			liste.add("Preisrangliste für Markt 2");
		if (MarketResearchReport.getInstance().getPriceType3RankingList().size() != 0)
			liste.add("Preisrangliste für Markt 3");
		return liste;
	}

	// @gwt.typeArgs
	// java.util.ArrayList<java.util.ArrayList<java.util.List<java.lang.String>>>
	public ArrayList<List<String>> getReports()
	{
		ArrayList<List<String>> liste = new ArrayList<List<String>>();
		liste.add(generateHeaders());
		liste.add(MarketResearchReport.getInstance().getCapitalRankingList());
		liste.add(MarketResearchReport.getInstance().getProfitRankingList());
		liste.add(MarketResearchReport.getInstance().getRadioRankingList());
		liste.add(MarketResearchReport.getInstance().getTvRankingList());
		liste.add(MarketResearchReport.getInstance().getNewspaperRankingList());
		liste.add(MarketResearchReport.getInstance().getDesignRankingList());
		liste.add(MarketResearchReport.getInstance().getEcologyRankingList());
		liste.add(MarketResearchReport.getInstance().getQualityRankingList());

		liste.add(MarketResearchReport.getInstance().getMarketshareType1RankingList());
		if (MarketResearchReport.getInstance().getMarketshareType2RankingList().size() != 0)
			liste.add(MarketResearchReport.getInstance().getMarketshareType2RankingList());
		if (MarketResearchReport.getInstance().getMarketshareType3RankingList().size() != 0)
			liste.add(MarketResearchReport.getInstance().getMarketshareType3RankingList());

		liste.add(MarketResearchReport.getInstance().getPriceType1RankingList());
		if (MarketResearchReport.getInstance().getPriceType2RankingList().size() != 0)
			liste.add(MarketResearchReport.getInstance().getPriceType2RankingList());
		if (MarketResearchReport.getInstance().getPriceType3RankingList().size() != 0)
			liste.add(MarketResearchReport.getInstance().getPriceType3RankingList());
		return liste;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getTvRankingList()
	{
		return tvRankingList;
	}

	public String toString()
	{
		String a;
		a = "Kapital \t" + getCapitalRankingList().toString() + "\n" + "Profit \t" + getProfitRankingList().toString() + "\n" +

		"Radio \t" + getRadioRankingList().toString() + "\n" + "Tv \t" + getTvRankingList().toString() + "\n" + "Newspaper \t"
				+ getNewspaperRankingList().toString() + "\n" +

				"Design \t" + getDesignRankingList().toString() + "\n" + "Ökologie \t" + getEcologyRankingList().toString() + "\n" + "Qualität \t"
				+ getQualityRankingList().toString() + "\n" +

				"Marktanteil Typ1 \t" + getMarketshareType1RankingList().toString() + "\n" + "Marktanteil Typ2  \t"
				+ getMarketshareType2RankingList().toString() + "\n" + "Marktanteil Typ3  \t" + getMarketshareType3RankingList().toString() + "\n" +

				"PriceType1 \t" + getPriceType1RankingList().toString() + "\n" + "PriceType2 \t" + getPriceType2RankingList().toString() + "\n"
				+ "PriceType3 \t" + getPriceType3RankingList().toString() + "\n";
		return a;
	}
}
