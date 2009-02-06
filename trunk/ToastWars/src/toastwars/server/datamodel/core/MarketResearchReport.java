package toastwars.server.datamodel.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import toastwars.server.datamodel.user.Group;
/*
 * @ author Alexander Geppart
 */
public class MarketResearchReport implements Comparator

{
	private static MarketResearchReport instance;

	// *********For Standard Report
	// @gwt.typeArgs <java.lang.Number>
	private ArrayList<Number> capitalList = new ArrayList<Number>();
	// @gwt.typeArgs <java.lang.Number>
	private ArrayList<Number> profitList = new ArrayList<Number>();

	// ****************************************************
	// *********For Extra Report
	// @gwt.typeArgs <java.lang.String>
	private ArrayList<String> priceType1List = new ArrayList<String>();
	// @gwt.typeArgs <java.lang.String>
	private ArrayList<String> priceType2List = new ArrayList<String>();
	// @gwt.typeArgs <java.lang.String>
	private ArrayList<String> priceType3List = new ArrayList<String>();

	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> radioRankingInternListType1;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> radioRankingInternListType2;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> radioRankingInternListType3;

	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> tvRankingInternListType1;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> tvRankingInternListType2;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> tvRankingInternListType3;

	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> newspaperRankingInternListType1;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> newspaperRankingInternListType2;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> newspaperRankingInternListType3;

	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> designRankingInternListType1;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> designRankingInternListType2;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> designRankingInternListType3;

	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> ecologyRankingInternListType1;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> ecologyRankingInternListType2;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> ecologyRankingInternListType3;

	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> qualityRankingInternListType1;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> qualityRankingInternListType2;
	// @gwt.typeArgs <java.lang.Number,java.lang.String>
	private Map<Number, String> qualityRankingInternListType3;

	// @gwt.typeArgs <java.lang.String>
	private Collection<String> values;
	// @gwt.typeArgs <java.lang.Number>
	private Collection<Number> numberValues;

	// @gwt.typeArgs <java.lang.String>
	private List<String> radioRankingListType1;
	// @gwt.typeArgs <java.lang.String>
	private List<String> tvRankingListType1;
	// @gwt.typeArgs <java.lang.String>
	private List<String> newspaperRankingListType1;
	// @gwt.typeArgs <java.lang.String>
	private List<String> designRankingListType1;
	// @gwt.typeArgs <java.lang.String>
	private List<String> ecologyRankingListType1;
	// @gwt.typeArgs <java.lang.String>
	private List<String> qualityRankingListType1;

	// @gwt.typeArgs <java.lang.String>
	private List<String> radioRankingListType2;
	// @gwt.typeArgs <java.lang.String>
	private List<String> tvRankingListType2;
	// @gwt.typeArgs <java.lang.String>
	private List<String> newspaperRankingListType2;
	// @gwt.typeArgs <java.lang.String>
	private List<String> designRankingListType2;
	// @gwt.typeArgs <java.lang.String>
	private List<String> ecologyRankingListType2;
	// @gwt.typeArgs <java.lang.String>
	private List<String> qualityRankingListType2;

	// @gwt.typeArgs <java.lang.String>
	private List<String> radioRankingListType3;
	// @gwt.typeArgs <java.lang.String>
	private List<String> tvRankingListType3;
	// @gwt.typeArgs <java.lang.String>
	private List<String> newspaperRankingListType3;
	// @gwt.typeArgs <java.lang.String>
	private List<String> designRankingListType3;
	// @gwt.typeArgs <java.lang.String>
	private List<String> ecologyRankingListType3;
	// @gwt.typeArgs <java.lang.String>
	private List<String> qualityRankingListType3;

	// ****************************************************
	private MarketResearchReport()
	{
		radioRankingInternListType1 = new TreeMap<Number, String>(this);
		tvRankingInternListType1 = new TreeMap<Number, String>(this);
		newspaperRankingInternListType1 = new TreeMap<Number, String>(this);
		designRankingInternListType1 = new TreeMap<Number, String>(this);
		ecologyRankingInternListType1 = new TreeMap<Number, String>(this);
		qualityRankingInternListType1 = new TreeMap<Number, String>(this);

		radioRankingInternListType2 = new TreeMap<Number, String>(this);
		tvRankingInternListType2 = new TreeMap<Number, String>(this);
		newspaperRankingInternListType2 = new TreeMap<Number, String>(this);
		designRankingInternListType2 = new TreeMap<Number, String>(this);
		ecologyRankingInternListType2 = new TreeMap<Number, String>(this);
		qualityRankingInternListType2 = new TreeMap<Number, String>(this);

		radioRankingInternListType3 = new TreeMap<Number, String>(this);
		tvRankingInternListType3 = new TreeMap<Number, String>(this);
		newspaperRankingInternListType3 = new TreeMap<Number, String>(this);
		designRankingInternListType3 = new TreeMap<Number, String>(this);
		ecologyRankingInternListType3 = new TreeMap<Number, String>(this);
		qualityRankingInternListType3 = new TreeMap<Number, String>(this);

		radioRankingListType1 = new ArrayList<String>();
		radioRankingListType2 = new ArrayList<String>();
		radioRankingListType3 = new ArrayList<String>();

		tvRankingListType1 = new ArrayList<String>();
		tvRankingListType2 = new ArrayList<String>();
		tvRankingListType3 = new ArrayList<String>();

		newspaperRankingListType1 = new ArrayList<String>();
		newspaperRankingListType2 = new ArrayList<String>();
		newspaperRankingListType3 = new ArrayList<String>();

		designRankingListType1 = new ArrayList<String>();
		designRankingListType2 = new ArrayList<String>();
		designRankingListType3 = new ArrayList<String>();

		ecologyRankingListType1 = new ArrayList<String>();
		ecologyRankingListType2 = new ArrayList<String>();
		ecologyRankingListType3 = new ArrayList<String>();

		qualityRankingListType1 = new ArrayList<String>();
		qualityRankingListType2 = new ArrayList<String>();
		qualityRankingListType3 = new ArrayList<String>();
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

		capitalList.clear();
		profitList.clear();

		priceType1List.clear();
		priceType2List.clear();
		priceType3List.clear();

		radioRankingInternListType1.clear();
		tvRankingInternListType1.clear();
		newspaperRankingInternListType1.clear();
		designRankingInternListType1.clear();
		ecologyRankingInternListType1.clear();
		qualityRankingInternListType1.clear();

		radioRankingInternListType2.clear();
		tvRankingInternListType2.clear();
		newspaperRankingInternListType2.clear();
		designRankingInternListType2.clear();
		ecologyRankingInternListType2.clear();
		qualityRankingInternListType2.clear();

		radioRankingInternListType3.clear();
		tvRankingInternListType3.clear();
		newspaperRankingInternListType3.clear();
		designRankingInternListType3.clear();
		ecologyRankingInternListType3.clear();
		qualityRankingInternListType3.clear();

		radioRankingListType1.clear();
		radioRankingListType2.clear();
		radioRankingListType3.clear();

		tvRankingListType1.clear();
		tvRankingListType2.clear();
		tvRankingListType3.clear();

		newspaperRankingListType1.clear();
		newspaperRankingListType2.clear();
		newspaperRankingListType3.clear();

		designRankingListType1.clear();
		designRankingListType2.clear();
		designRankingListType3.clear();

		ecologyRankingListType1.clear();
		ecologyRankingListType2.clear();
		ecologyRankingListType3.clear();

		qualityRankingListType1.clear();
		qualityRankingListType2.clear();
		qualityRankingListType3.clear();

		for (int i = 0; i < grouplist.size(); i++)
		{
			String groupName = grouplist.get(i).getUsername();
			ArrayList<Toaster> toasterList = grouplist.get(i).getCompany().getToasterList();
			capitalList.add(grouplist.get(i).getCompany().getCapital());
			profitList.add(grouplist.get(i).getCompany().getProfit());
			for (int a = 0; a < toasterList.size(); a++)
			{
				if (toasterList.get(a).getType() == Type.TYPE1)
				{
					priceType1List.add(String.valueOf(toasterList.get(a).getPrice()));
					priceType1List.add(groupName);
					radioRankingInternListType1.put(toasterList.get(a).getRadioInvestmentKum(), groupName);
					tvRankingInternListType1.put(toasterList.get(a).getTvInvestmentKum(), groupName);
					newspaperRankingInternListType1.put(toasterList.get(a).getNewspaperInvestmentKum(), groupName);
					qualityRankingInternListType1.put(toasterList.get(a).getQualityInvestmentKum(), groupName);
					designRankingInternListType1.put(toasterList.get(a).getDesignInvestmentKum(), groupName);
					ecologyRankingInternListType1.put(toasterList.get(a).getEcologyInvestmentKum(), groupName);
				} else if (toasterList.get(a).getType() == Type.TYPE2)
				{
					priceType2List.add(String.valueOf(toasterList.get(a).getPrice()));
					priceType2List.add(groupName);
					radioRankingInternListType2.put(toasterList.get(a).getRadioInvestmentKum(), groupName);
					tvRankingInternListType2.put(toasterList.get(a).getTvInvestmentKum(), groupName);
					newspaperRankingInternListType2.put(toasterList.get(a).getNewspaperInvestmentKum(), groupName);
					qualityRankingInternListType2.put(toasterList.get(a).getQualityInvestmentKum(), groupName);
					designRankingInternListType2.put(toasterList.get(a).getDesignInvestmentKum(), groupName);
					ecologyRankingInternListType2.put(toasterList.get(a).getEcologyInvestmentKum(), groupName);
				} else if (toasterList.get(a).getType() == Type.TYPE3)
				{
					priceType3List.add(String.valueOf(toasterList.get(a).getPrice()));
					priceType3List.add(groupName);
					radioRankingInternListType3.put(toasterList.get(a).getRadioInvestmentKum(), groupName);
					tvRankingInternListType3.put(toasterList.get(a).getTvInvestmentKum(), groupName);
					newspaperRankingInternListType3.put(toasterList.get(a).getNewspaperInvestmentKum(), groupName);
					qualityRankingInternListType3.put(toasterList.get(a).getQualityInvestmentKum(), groupName);
					designRankingInternListType3.put(toasterList.get(a).getDesignInvestmentKum(), groupName);
					ecologyRankingInternListType3.put(toasterList.get(a).getEcologyInvestmentKum(), groupName);
				}

			}
		}

		values = radioRankingInternListType1.values();
		radioRankingListType1 = new ArrayList<String>(values);
		values = radioRankingInternListType2.values();
		radioRankingListType2 = new ArrayList<String>(values);
		values = radioRankingInternListType3.values();
		radioRankingListType3 = new ArrayList<String>(values);

		values = tvRankingInternListType1.values();
		tvRankingListType1 = new ArrayList<String>(values);
		values = tvRankingInternListType2.values();
		tvRankingListType2 = new ArrayList<String>(values);
		values = tvRankingInternListType3.values();
		tvRankingListType3 = new ArrayList<String>(values);

		values = newspaperRankingInternListType1.values();
		newspaperRankingListType1 = new ArrayList<String>(values);
		values = newspaperRankingInternListType2.values();
		newspaperRankingListType2 = new ArrayList<String>(values);
		values = newspaperRankingInternListType3.values();
		newspaperRankingListType3 = new ArrayList<String>(values);

		values = qualityRankingInternListType1.values();
		qualityRankingListType1 = new ArrayList<String>(values);
		values = qualityRankingInternListType2.values();
		qualityRankingListType2 = new ArrayList<String>(values);
		values = qualityRankingInternListType3.values();
		qualityRankingListType3 = new ArrayList<String>(values);

		values = designRankingInternListType1.values();
		designRankingListType1 = new ArrayList<String>(values);
		values = designRankingInternListType2.values();
		designRankingListType2 = new ArrayList<String>(values);
		values = designRankingInternListType3.values();
		designRankingListType3 = new ArrayList<String>(values);

		values = ecologyRankingInternListType1.values();
		ecologyRankingListType1 = new ArrayList<String>(values);
		values = ecologyRankingInternListType2.values();
		ecologyRankingListType2 = new ArrayList<String>(values);
		values = ecologyRankingInternListType3.values();
		ecologyRankingListType3 = new ArrayList<String>(values);

	}



	// @gwt.typeArgs
	// java.util.ArrayList<java.util.ArrayList<java.util.ArrayList<java.util.List<java.lang.String>>>>
	public ArrayList<ArrayList<List<String>>>  getReports()
	{
		ArrayList<ArrayList<List<String>>> liste = new ArrayList<ArrayList<List<String>>> ();
		liste.add(getReports4Type1());
		liste.add(getReports4Type2());
		liste.add(getReports4Type3());
		return liste;
	}

	// @gwt.typeArgs
	// java.util.ArrayList<java.util.ArrayList<java.util.List<java.lang.String>>>
	public ArrayList<List<String>> getReports4Type1()
	{
		ArrayList<List<String>> liste = new ArrayList<List<String>>();
		liste.add(this.newspaperRankingListType1);
		liste.add(this.radioRankingListType1);
		liste.add(this.tvRankingListType1);
		liste.add(this.qualityRankingListType1);
		liste.add(this.designRankingListType1);
		liste.add(this.ecologyRankingListType1);
		liste.add(this.priceType1List);
		return liste;
	}

	// @gwt.typeArgs
	// java.util.ArrayList<java.util.ArrayList<java.util.List<java.lang.String>>>
	public ArrayList<List<String>> getReports4Type2()
	{
		ArrayList<List<String>> liste = new ArrayList<List<String>>();
		liste.add(this.newspaperRankingListType2);
		liste.add(this.radioRankingListType2);
		liste.add(this.tvRankingListType2);
		liste.add(this.qualityRankingListType2);
		liste.add(this.designRankingListType2);
		liste.add(this.ecologyRankingListType2);
		liste.add(this.priceType2List);
		return liste;
	}

	// @gwt.typeArgs
	// java.util.ArrayList<java.util.ArrayList<java.util.List<java.lang.String>>>
	public ArrayList<List<String>> getReports4Type3()
	{
		ArrayList<List<String>> liste = new ArrayList<List<String>>();
		liste.add(this.newspaperRankingListType3);
		liste.add(this.radioRankingListType3);
		liste.add(this.tvRankingListType3);
		liste.add(this.qualityRankingListType3);
		liste.add(this.designRankingListType3);
		liste.add(this.ecologyRankingListType3);
		liste.add(this.priceType3List);
		return liste;
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
	public ArrayList<Number> getCapitalRankingInternList()
	{
		return capitalList;
	}
	public ArrayList<Number> getProfitRankingInternList()
	{
		return profitList;
	}
}
