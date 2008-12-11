package toastwars.util;

public class NumberUtil {

	// by Waldi
	public static double roundDouble(double d) {
		int i = Math.round((float) (d * 100));
		return i / 100.00;
	}

	// by Waldi
	public static int roundDoubleUp(double d, int stellen) {

		if (d % stellen == 0)
			return (int) d;

		int i = Math.round((float) ((d + stellen / 2) / stellen));
		return i * stellen;
	}

	// by Waldi
	public static int roundDoubleDown(double d, int stellen) {

		if (d % stellen == 0)
			return (int) d;

		int i = Math.round((float) ((d - stellen / 2) / stellen));
		return i * stellen;
	}
}