package toastwars.util;

public class NumberUtil {
	// public static double roundDouble(double d) {
	// DecimalFormat df = new DecimalFormat("0.00");
	// df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
	// return Double.parseDouble(df.format(d));
	// }

	// by Waldi
	public static double roundDouble(double d) {
		int i = Math.round((float) (d * 100));
		System.out.println(i);
		return i / 100.00;
	}

}
