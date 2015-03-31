package au.edu.cmu.debug;

import org.junit.Test;
import static org.junit.Assert.*;
public class BinarySearchTest {

	@Test
	public void binarySearchTest() {
		int[] array = {1, 3, 5, 7, 9, 11};
		int value = 11;
		int actual = Main.binarySearch(array, value, 0, 5);
		assertEquals(5, actual);		
	}

}
