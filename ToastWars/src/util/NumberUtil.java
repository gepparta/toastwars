package util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberUtil
{
	public static double roundDouble(double d)
	{
		DecimalFormat df = new DecimalFormat("0.00");
		df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
		return Double.parseDouble(df.format(d));
	}
}
