package toastwars.util;

public class NumberUtil
{
	// by Waldi
	public static double roundDouble(double d)
	{
		int i = Math.round((float) (d * 100));
		return i / 100.00;
	}

}
