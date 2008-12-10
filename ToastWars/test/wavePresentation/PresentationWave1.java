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
	private Toaster toaster1;
	private Toaster toaster2;
	private ArrayList<Toaster> toasterList1;
	private ArrayList<Toaster> toasterList2;
	private Company company1;
	private Company company2;

	@Before
	public void setUp() throws Exception
	{
		toaster1 = new Toaster(8, 1000, 8155, 1.00, 10000, 5000, 2.58, 500, Type.TYPE1);
		toaster2 = new Toaster(8, 1000, 8155, 1.00, 10000, 5000, 2.58, 500, Type.TYPE1);
	}

	@After
	public void tearDown() throws Exception
	{
		
	}

	
}
