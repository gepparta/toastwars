package toastwars.util;

public class NumberUtil {

	// by Waldi
	public static double roundDouble(double d) {
		int i = Math.round((float) (d * 100));
		return i / 100.00;
	}

	public static double roundDoubleUp(double d) {
		return Math.ceil(d);
	}

	// by Waldi
	public static int roundIntUp(double d, int stellen) {
		int i = (int) Math.ceil(d / stellen);
		return i * stellen;
	}

	// by Waldi
	public static int roundIntDown(double d, int stellen) {
		int i = (int) Math.floor(d / stellen);
		return i * stellen;
	}
}