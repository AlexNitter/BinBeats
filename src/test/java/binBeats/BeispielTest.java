package test.java.binBeats;

import static org.junit.Assert.*;

import org.junit.Test;

public class BeispielTest {

	@Test
	public void test_1() {
		String text1 = "Hallo";
		String text2 = "Hallo";

		assertSame(text1, text2);
	}
	
	@Test
	public void test_2() {
		String text1 = "Hallo";
		String text2 = "Hallo_Unterschied";

		assertNotSame(text1, text2);
	}
}
