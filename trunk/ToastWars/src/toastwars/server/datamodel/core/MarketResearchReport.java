package toastwars.server.datamodel.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import toastwars.server.datamodel.user.Group;

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
					radioRankingInternListType1.put(toasterList.get(a).getRadioInvestmentKum(), groupName);
					tvRankingInternListType1.put(toasterList.get(a).getTvInvestmentKum(), groupName);
					newspaperRankingInternListType1.put(toasterList.get(a).getNewspaperInvestmentKum(), groupName);
					qualityRankingInternListType1.put(toasterList.get(a).getQualityInvestmentKum(), groupName);
					designRankingInternListType1.put(toasterList.get(a).getDesignInvestmentKum(), groupName);
					ecologyRankingInternListType1.put(toasterList.get(a).getEcologyInvestmentKum(), groupName);
				} else if (toasterList.get(a).getType() == Type.TYPE2)
				{
					priceType2List.add(String.valueOf(toasterList.get(a).getPrice()));
					priceType2List.add(String.valueOf(toasterList.get(a).getPrice()));
					radioRankingInternListType2.put(toasterList.get(a).getRadioInvestmentKum(), groupName);
					tvRankingInternListType2.put(toasterList.get(a).getTvInvestmentKum(), groupName);
					newspaperRankingInternListType2.put(toasterList.get(a).getNewspaperInvestmentKum(), groupName);
					qualityRankingInternListType2.put(toasterList.get(a).getQualityInvestmentKum(), groupName);
					designRankingInternListType2.put(toasterList.get(a).getDesignInvestmentKum(), groupName);
					ecologyRankingInternListType2.put(toasterList.get(a).getEcologyInvestmentKum(), groupName);
				} else if (toasterList.get(a).getType() == Type.TYPE3)
				{
					priceType3List.add(String.valueOf(toasterList.get(a).getPrice()));
					priceType3List.add(String.valueOf(toasterList.get(a).getPrice()));
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

	public ArrayList<Number> getCapitalRankingInternList()
	{
		return capitalList;
	}

	public List<String> getDesignRankingListType1()
	{
		return designRankingListType1;
	}

	public List<String> getDesignRankingListType2()
	{
		return designRankingListType2;
	}

	public List<String> getDesignRankingListType3()
	{
		return designRankingListType3;
	}

	public List<String> getEcologyRankingListType1()
	{
		return ecologyRankingListType1;
	}

	public List<String> getEcologyRankingListType2()
	{
		return ecologyRankingListType2;
	}

	public List<String> getEcologyRankingListType3()
	{
		return ecologyRankingListType3;
	}

	public List<String> getNewspaperRankingListType1()
	{
		return newspaperRankingListType1;
	}

	public List<String> getNewspaperRankingListType2()
	{
		return newspaperRankingListType2;
	}

	public List<String> getNewspaperRankingListType3()
	{
		return newspaperRankingListType3;
	}

	public ArrayList<String> getPriceType1List()
	{
		return priceType1List;
	}

	public ArrayList<String> getPriceType2List()
	{
		return priceType2List;
	}

	public ArrayList<String> getPriceType3List()
	{
		return priceType3List;
	}

	public ArrayList<Number> getProfitRankingInternList()
	{
		return profitList;
	}

	public List<String> getQualityRankingListType1()
	{
		return qualityRankingListType1;
	}

	public List<String> getQualityRankingListType2()
	{
		return qualityRankingListType2;
	}

	public List<String> getQualityRankingListType3()
	{
		return qualityRankingListType3;
	}

	public List<String> getRadioRankingListType1()
	{
		return radioRankingListType1;
	}

	public List<String> getRadioRankingListType2()
	{
		return radioRankingListType2;
	}

	public List<String> getRadioRankingListType3()
	{
		return radioRankingListType3;
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
		liste.add(getNewspaperRankingListType1());
		liste.add(getRadioRankingListType1());
		liste.add(getTvRankingListType1());
		liste.add(getQualityRankingListType1());
		liste.add(getDesignRankingListType1());
		liste.add(getEcologyRankingListType1());
		liste.add(getPriceType1List());
		return liste;
	}

	// @gwt.typeArgs
	// java.util.ArrayList<java.util.ArrayList<java.util.List<java.lang.String>>>
	public ArrayList<List<String>> getReports4Type2()
	{
		ArrayList<List<String>> liste = new ArrayList<List<String>>();
		liste.add(getNewspaperRankingListType2());
		liste.add(getRadioRankingListType2());
		liste.add(getTvRankingListType2());
		liste.add(getQualityRankingListType2());
		liste.add(getDesignRankingListType2());
		liste.add(getEcologyRankingListType2());
		liste.add(getPriceType2List());
		return liste;
	}

	// @gwt.typeArgs
	// java.util.ArrayList<java.util.ArrayList<java.util.List<java.lang.String>>>
	public ArrayList<List<String>> getReports4Type3()
	{
		ArrayList<List<String>> liste = new ArrayList<List<String>>();
		liste.add(getNewspaperRankingListType3());
		liste.add(getRadioRankingListType3());
		liste.add(getTvRankingListType3());
		liste.add(getQualityRankingListType3());
		liste.add(getDesignRankingListType3());
		liste.add(getEcologyRankingListType3());
		liste.add(getPriceType3List());
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

	public List<String> getTvRankingListType1()
	{
		return tvRankingListType1;
	}

	public List<String> getTvRankingListType2()
	{
		return tvRankingListType2;
	}

	public List<String> getTvRankingListType3()
	{
		return tvRankingListType3;
	}

	public void setCapitalRankingInternList(ArrayList<Number> capitalRankingInternList)
	{
		this.capitalList = capitalRankingInternList;
	}

	public void setDesignRankingListType1(List<String> designRankingListType1)
	{
		this.designRankingListType1 = designRankingListType1;
	}

	public void setDesignRankingListType2(List<String> designRankingListType2)
	{
		this.designRankingListType2 = designRankingListType2;
	}

	public void setDesignRankingListType3(List<String> designRankingListType3)
	{
		this.designRankingListType3 = designRankingListType3;
	}

	public void setEcologyRankingListType1(List<String> ecologyRankingListType1)
	{
		this.ecologyRankingListType1 = ecologyRankingListType1;
	}

	public void setEcologyRankingListType2(List<String> ecologyRankingListType2)
	{
		this.ecologyRankingListType2 = ecologyRankingListType2;
	}

	public void setEcologyRankingListType3(List<String> ecologyRankingListType3)
	{
		this.ecologyRankingListType3 = ecologyRankingListType3;
	}

	public void setNewspaperRankingListType1(List<String> newspaperRankingListType1)
	{
		this.newspaperRankingListType1 = newspaperRankingListType1;
	}

	public void setNewspaperRankingListType2(List<String> newspaperRankingListType2)
	{
		this.newspaperRankingListType2 = newspaperRankingListType2;
	}

	public void setNewspaperRankingListType3(List<String> newspaperRankingListType3)
	{
		this.newspaperRankingListType3 = newspaperRankingListType3;
	}

	public void setPriceType1List(ArrayList<String> priceType1List)
	{
		this.priceType1List = priceType1List;
	}

	public void setPriceType2List(ArrayList<String> priceType2List)
	{
		this.priceType2List = priceType2List;
	}

	public void setPriceType3List(ArrayList<String> priceType3List)
	{
		this.priceType3List = priceType3List;
	}

	public void setProfitRankingInternList(ArrayList<Number> profitRankingInternList)
	{
		this.profitList = profitRankingInternList;
	}

	public void setQualityRankingListType1(List<String> qualityRankingListType1)
	{
		this.qualityRankingListType1 = qualityRankingListType1;
	}

	public void setQualityRankingListType2(List<String> qualityRankingListType2)
	{
		this.qualityRankingListType2 = qualityRankingListType2;
	}

	public void setQualityRankingListType3(List<String> qualityRankingListType3)
	{
		this.qualityRankingListType3 = qualityRankingListType3;
	}

	public void setRadioRankingListType1(List<String> radioRankingListType1)
	{
		this.radioRankingListType1 = radioRankingListType1;
	}

	public void setRadioRankingListType2(List<String> radioRankingListType2)
	{
		this.radioRankingListType2 = radioRankingListType2;
	}

	public void setRadioRankingListType3(List<String> radioRankingListType3)
	{
		this.radioRankingListType3 = radioRankingListType3;
	}

	public void setTvRankingListType1(List<String> tvRankingListType1)
	{
		this.tvRankingListType1 = tvRankingListType1;
	}

	public void setTvRankingListType2(List<String> tvRankingListType2)
	{
		this.tvRankingListType2 = tvRankingListType2;
	}

	public void setTvRankingListType3(List<String> tvRankingListType3)
	{
		this.tvRankingListType3 = tvRankingListType3;
	}
}
