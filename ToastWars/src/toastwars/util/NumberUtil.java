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

	public static String formatToEuro(Number n) {
		StringBuffer s = new StringBuffer(n.toString());

		int i = s.lastIndexOf(".");

		// replace dot with comma or append zeros
		if (i != -1)
			s.replace(i, i + 1, ",");
		else
			s.append(",00");

		// insert thousand separator
		i = s.lastIndexOf(",");
		StringBuffer left = new StringBuffer(s.substring(0, i));

		for (int j = left.length() - 3; j > 0; j -= 3) {
			s.insert(j, '.');
		}

		// cut after 2
		i = s.lastIndexOf(",");
		if ((s.length() - i) > 3)
			s = s.delete(i + 3, s.length());
		if ((s.length() - i) < 3)
			s = s.append('0');

		s.append(" &euro;");

		return s.toString();
	}
}