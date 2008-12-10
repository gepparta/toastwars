package wavePresentation;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.util.NumberUtil;

public class PresentationWave1 extends TestCase
{
	private Type myType1;
	private Toaster toaster1;
	private Toaster toaster2;
	private ArrayList<Toaster> toasterList1;
	private ArrayList<Toaster> toasterList2;
	private Company company1;
	private Company company2;

	@Before
	public void setUp() throws Exception
	{
		myType1 = Type.TYPE1;
		toaster1 = new Toaster(8, 1000, 8155, 1.00, 10000, 5000, 2.58, 500, myType1);
		toaster2 = new Toaster(8, 1000, 8155, 1.00, 10000, 5000, 2.58, 500, myType1);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test()
	{
		// double d = 0;
		// do
		// {
		// d = NumberUtil.roundDouble(Math.random() * (1.05 - 0.95)) + 0.95;
		// System.out.println(d);
		// } while ((d >= 0.95) && (d <= 1.05));
		// assertEquals("Falsch", 10, 8, 1);

		double random = toaster1.getType().getRandom();
		double d = 0;
		do
		{
			d = NumberUtil.roundDouble(Math.random() * (random * 2)) + (1 - random);
			System.out.println(d);
		} while ((d >= 0.95) && (d <= 1.05));
	}
}
