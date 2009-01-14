package toastwars.server.datamodel.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import toastwars.server.datamodel.user.Group;

public class MarketResearchReport implements Comparator

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

	private List profitRankingList;
	private List radioRankingList;
	private List tvRankingList;
	private List newspaperRankingList;
	private List designRankingList;
	private List ecologyRankingList;
	private List qualityRankingList;

	private List priceType1RankingList;
	private List priceType2RankingList;
	private List priceType3RankingList;

	private List marketshareType1RankingList;
	private List marketshareType2RankingList;
	private List marketshareType3RankingList;

	public MarketResearchReport()
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
		radioRankingList = new ArrayList(values);

		values = tvRankingInternList.values();
		tvRankingList = new ArrayList(values);

		values = newspaperRankingInternList.values();
		newspaperRankingList = new ArrayList(values);

		values = designRankingInternList.values();
		designRankingList = new ArrayList(values);

		values = ecologyRankingInternList.values();
		ecologyRankingList = new ArrayList(values);

		values = qualityRankingInternList.values();
		qualityRankingList = new ArrayList(values);

		priceType1RankingList = new ArrayList(Arrays.asList(generateStringArray(priceType1RankingInternList)));
		priceType2RankingList = new ArrayList(Arrays.asList(generateStringArray(priceType2RankingInternList)));
		priceType3RankingList = new ArrayList(Arrays.asList(generateStringArray(priceType3RankingInternList)));

		marketshareType1RankingList = new ArrayList(Arrays.asList(generateStringArray(marketshareType1RankingInternList)));
		marketshareType2RankingList = new ArrayList(Arrays.asList(generateStringArray(marketshareType2RankingInternList)));
		marketshareType3RankingList = new ArrayList(Arrays.asList(generateStringArray(marketshareType3RankingInternList)));

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

	public List getCapitalRankingList()
	{
		return capitalRankingList;
	}

	public List getDesignRankingList()
	{
		return designRankingList;
	}

	public List getEcologyRankingList()
	{
		return ecologyRankingList;
	}

	public List getMarketshareType1RankingList()
	{
		return marketshareType1RankingList;
	}

	public List getMarketshareType2RankingList()
	{
		return marketshareType2RankingList;
	}

	public List getMarketshareType3RankingList()
	{
		return marketshareType3RankingList;
	}

	public List getNewspaperRankingList()
	{
		return newspaperRankingList;
	}

	public List getPriceType1RankingList()
	{
		return priceType1RankingList;
	}

	public List getPriceType2RankingList()
	{
		return priceType2RankingList;
	}

	public List getPriceType3RankingList()
	{
		return priceType3RankingList;
	}

	public List getProfitRankingList()
	{
		return profitRankingList;
	}

	public List getQualityRankingList()
	{
		return qualityRankingList;
	}

	public List getRadioRankingList()
	{
		return radioRankingList;
	}

	public List getTvRankingList()
	{
		return tvRankingList;
	}
	
	public String toString()
	{
		String a;
		a = "Kapital \t" + getCapitalRankingList().toString()+"\n"+
			"Profit \t" + getProfitRankingList().toString()+"\n"+
			
			"Radio \t" + getRadioRankingList().toString()+"\n"+
			"Tv \t" + getTvRankingList().toString()+"\n"+
			"Newspaper \t" + getNewspaperRankingList().toString()+"\n"+
			
			"Design \t" + getDesignRankingList().toString()+"\n"+
			"Ökologie \t" + getEcologyRankingList().toString()+"\n"+
			"Qualität \t" + getQualityRankingList().toString()+"\n"+

			"Marktanteil Typ1 \t" + getMarketshareType1RankingList().toString()+"\n"+
			"Marktanteil Typ2  \t" + getMarketshareType2RankingList().toString()+"\n"+
			"Marktanteil Typ3  \t" + getMarketshareType3RankingList().toString()+"\n"+

			"PriceType1 \t" + getPriceType1RankingList().toString()+"\n"+
			"PriceType2 \t" + getPriceType2RankingList().toString()+"\n"+
			"PriceType3 \t" + getPriceType3RankingList().toString()+"\n";
		return a;
	}
}
