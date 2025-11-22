package tripleo.vendor.batoull22;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import tripleo.elijah.util.Mode;
import tripleo.elijah.util.Operation2;

import java.io.InputStream;

import static org.junit.Assert.assertNotSame;

public class ExpertSystemTest {

	@Test
	public void testOne() {
		final EK_ExpertSystem i = new EK_ExpertSystem();

		final Operation2<EK_Reader> ovo2 = openfile_2(i);
		assertNotSame(ovo2.mode(), Mode.FAILURE);


		final EK_Reader reader = ovo2.success();

		reader.readfile();
		// reader.print();
		reader.closefile();

		// System.out.println("------------------------");
		boolean f = i.Forwardchaining();
		// System.out.println(" ");
		System.out.println("Result of Forwardchaining: " + f);

		// System.out.println(" ");
		// i.print();

		// System.out.println("------------------------");
		boolean b = i.Backwardchaining();
		System.out.println("Result of Backwardchaining: " + b);
		System.out.println(" ");
	}

	public @NotNull Operation2<EK_Reader> openfile_2(EK_ExpertSystem aSystem) {
		try {
			final InputStream stream = getClass().getResourceAsStream("KB3.txt");
			assert stream != null;
			return Operation2.success(new EK_Reader1(aSystem, stream));
		} catch (Exception ex) {
			System.out.println("Error:the input file dose not exist");
			return Operation2.failure(ex);
		}
	}
}

//
//
//
