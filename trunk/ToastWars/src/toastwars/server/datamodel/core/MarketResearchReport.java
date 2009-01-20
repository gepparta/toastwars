package toastwars.server.datamodel.core;

import java.util.ArrayList;
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

	// @gwt.typeArgs <java.lang.Number>
	private ArrayList<Number> capitalRankingInternList = new ArrayList<Number>();
	// @gwt.typeArgs <java.lang.Number>
	private ArrayList<Number> profitRankingInternList = new ArrayList<Number>();

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
	// @gwt.typeArgs <java.lang.String>
	private Collection<String> values;
	// @gwt.typeArgs <java.lang.Number>
	private Collection<Number> numberValues;
	// // @gwt.typeArgs <java.lang.String>
	// private List<String> capitalRankingList;
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

	private MarketResearchReport()
	{
		radioRankingInternList = new TreeMap<Number, String>(this);
		tvRankingInternList = new TreeMap<Number, String>(this);
		newspaperRankingInternList = new TreeMap<Number, String>(this);
		designRankingInternList = new TreeMap<Number, String>(this);
		ecologyRankingInternList = new TreeMap<Number, String>(this);
		qualityRankingInternList = new TreeMap<Number, String>(this);
		radioRankingList = new ArrayList<String>();
		tvRankingList = new ArrayList<String>();
		newspaperRankingList = new ArrayList<String>();
		designRankingList = new ArrayList<String>();
		ecologyRankingList = new ArrayList<String>();
		qualityRankingList = new ArrayList<String>();
	}

	public static MarketResearchReport getInstance()
	{
		if (instance == null)
		{
			instance = new MarketResearchReport();
		}
		return instance;
	}

	public int compare(Object arg0, Object arg1)
	{
		double dd1 = (double) Double.parseDouble(arg0.toString());
		double dd2 = (double) Double.parseDouble(arg1.toString());
		return (dd1 > dd2) ? -1 : (dd1 < dd2) ? 1 : (dd1 == dd2) ? 1 : (arg1.toString()).compareTo(arg0.toString());
	}

	public void generateMarketResearchReport(ArrayList<Group> grouplist)
	{

		capitalRankingInternList.clear();
		profitRankingInternList.clear();
		radioRankingInternList.clear();
		tvRankingInternList.clear();
		newspaperRankingInternList.clear();
		designRankingInternList.clear();
		ecologyRankingInternList.clear();
		qualityRankingInternList.clear();

		radioRankingList.clear();
		tvRankingList.clear();
		newspaperRankingList.clear();
		designRankingList.clear();
		ecologyRankingList.clear();
		qualityRankingList.clear();

		double radioInvestment = 0;
		double tvInvestment = 0;
		double newspaperInvestment = 0;
		double qualityInvestment = 0;
		double designInvestment = 0;
		double ecologyInvestment = 0;
		for (int i = 0; i < grouplist.size(); i++)
		{

			ArrayList<Toaster> tList = grouplist.get(i).getCompany().getToasterList();
			capitalRankingInternList.add(grouplist.get(i).getCompany().getCapital());
			profitRankingInternList.add(grouplist.get(i).getCompany().getProfit());
			for (int a = 0; a < tList.size(); a++)
			{
				// Investments einer Company zusammenrechnen
				radioInvestment += tList.get(a).getRadioInvestmentKum();
				tvInvestment += tList.get(a).getTvInvestmentKum();
				newspaperInvestment += tList.get(a).getNewspaperInvestmentKum();
				qualityInvestment += tList.get(a).getQualityInvestmentKum();
				designInvestment += tList.get(a).getDesignInvestmentKum();
				ecologyInvestment += tList.get(a).getEcologyInvestmentKum();
			}

			String groupName = grouplist.get(i).getUsername();

			radioRankingInternList.put(radioInvestment, groupName);
			tvRankingInternList.put(tvInvestment, groupName);
			newspaperRankingInternList.put(newspaperInvestment, groupName);
			designRankingInternList.put(designInvestment, groupName);
			ecologyRankingInternList.put(ecologyInvestment, groupName);
			qualityRankingInternList.put(qualityInvestment, groupName);

			radioInvestment = 0;
			tvInvestment = 0;
			newspaperInvestment = 0;
			qualityInvestment = 0;
			designInvestment = 0;
			ecologyInvestment = 0;
		}

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
	}

//	private String[] generateStringArray(Map map)
//	{
//		String[] werte = new String[map.size()];
//		Iterator it = map.entrySet().iterator();
//		int i = 0;
//		while (it.hasNext())
//		{
//			Map.Entry me = (Map.Entry) it.next();
//			werte[i] = me.getValue().toString() + " " + me.getKey();
//			i++;
//		}
//		return werte;
//	}

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
	public List<String> getNewspaperRankingList()
	{
		return newspaperRankingList;
	}

	public ArrayList<Number> getProfitRankingInternList()
	{
		return profitRankingInternList;
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

	// @gwt.typeArgs
	// java.util.ArrayList<java.util.ArrayList<java.util.List<java.lang.String>>>
	public ArrayList<List<String>> getReports()
	{
		ArrayList<List<String>> liste = new ArrayList<List<String>>();
		liste.add(getNewspaperRankingList());
		liste.add(getRadioRankingList());
		liste.add(getTvRankingList());
		liste.add(getQualityRankingList());
		liste.add(getDesignRankingList());
		liste.add(getEcologyRankingList());
		return liste;
	}

	// @gwt.typeArgs <java.lang.String>
	public List<String> getTvRankingList()
	{
		return tvRankingList;
	}

	public void setProfitRankingInternList(ArrayList<Number> profitRankingInternList)
	{
		this.profitRankingInternList = profitRankingInternList;
	}

	public String toString()
	{

		String a;

		a = "Radio \t" + getRadioRankingList().toString() + "\n" + "Tv \t" + getTvRankingList().toString() + "\n" + "Newspaper \t" + getNewspaperRankingList().toString() + "\n"
				+ "Design \t" + getDesignRankingList().toString() + "\n" + "Ökologie \t" + getEcologyRankingList().toString() + "\n" + "Qualität \t"
				+ getQualityRankingList().toString() + "\n";
		return a;
	}

	public ArrayList<Number> getCapitalRankingInternList()
	{
		return capitalRankingInternList;
	}

	public void setCapitalRankingInternList(ArrayList<Number> capitalRankingInternList)
	{
		this.capitalRankingInternList = capitalRankingInternList;
	}

	// @gwt.typeArgs <java.lang.Number>
	public ArrayList<Number> getSortedIndexListTyp1()
	{
		if (numberValues != null)
			numberValues.clear();
		Map<Number, Number> sortedIndexList = new TreeMap<Number, Number>(this);
		ArrayList<Group> grouplist = Game.getInstance().getGroupList();

		for (int i = 0; i < grouplist.size(); i++)
		{
			for (int a = 0; a < grouplist.get(i).getCompany().getToasterList().size(); a++)
			{
				if (grouplist.get(i).getCompany().getToasterList().get(a).getType() == Type.TYPE1)
					sortedIndexList.put(grouplist.get(i).getCompany().getToasterList().get(a).getIndex(), grouplist.get(i).getCompany().getCompanyID());
			}
		}
		numberValues = sortedIndexList.values();
		return new ArrayList<Number>(numberValues);
	}

	// @gwt.typeArgs <java.lang.Number>
	public ArrayList<Number> getSortedIndexListTyp2()
	{
		if (numberValues != null)
			numberValues.clear();
		Map<Number, Number> sortedIndexList = new TreeMap<Number, Number>(this);
		ArrayList<Group> grouplist = Game.getInstance().getGroupList();

		for (int i = 0; i < grouplist.size(); i++)
		{
			for (int a = 0; a < grouplist.get(i).getCompany().getToasterList().size(); a++)
			{
				if (grouplist.get(i).getCompany().getToasterList().get(a).getType() == Type.TYPE2)
					sortedIndexList.put(grouplist.get(i).getCompany().getToasterList().get(a).getIndex(), grouplist.get(i).getCompany().getCompanyID());
			}
		}
		numberValues = sortedIndexList.values();
		return new ArrayList<Number>(numberValues);
	}

	// @gwt.typeArgs <java.lang.Number>
	public ArrayList<Number> getSortedIndexListTyp3()
	{
		if (numberValues != null)
			numberValues.clear();
		Map<Number, Number> sortedIndexList = new TreeMap<Number, Number>(this);
		ArrayList<Group> grouplist = Game.getInstance().getGroupList();

		for (int i = 0; i < grouplist.size(); i++)
		{
			for (int a = 0; a < grouplist.get(i).getCompany().getToasterList().size(); a++)
			{
				if (grouplist.get(i).getCompany().getToasterList().get(a).getType() == Type.TYPE3)
					sortedIndexList.put(grouplist.get(i).getCompany().getToasterList().get(a).getIndex(), grouplist.get(i).getCompany().getCompanyID());
			}
		}
		numberValues = sortedIndexList.values();
		return new ArrayList<Number>(numberValues);
	}

}
