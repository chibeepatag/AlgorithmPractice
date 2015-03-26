package au.edu.cmu.algorithm;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

public class ArmyCounterTest {

	@Test
	public void getInputTest() {
		try {
			InputStream in = new FileInputStream("ArmyCounterTestData");
			ArmyCounter ac = new ArmyCounter();
			List<Kingdom> kingdoms = ac.getInput(in);
			assertEquals(1, kingdoms.size());

			for (Kingdom kingdom : kingdoms) {
				assertEquals(12, kingdom.rows);
				assertEquals(15, kingdom.columns);
			}
			in.close();
		} catch (FileNotFoundException e) {
			fail("File not found exception.");
			e.printStackTrace();
		} catch (IOException e) {
			fail("IOException.");
			e.printStackTrace();
		}
	}

	@Test
	public void testAssignLabel() {
		try {
			InputStream in = new FileInputStream("ArmyCounterTestData");
			ArmyCounter ac = new ArmyCounter();
			List<Kingdom> kingdoms = ac.getInput(in);
			
			for (Kingdom kingdom : kingdoms) {
				ac.assignLabel(kingdom.map);
			}
			
			Point[][] map = kingdoms.get(0).map;
			for(int i = 0; i < map.length; i++){
				Point[] pointArr = map[i];
				for(int j = 0; j < pointArr.length; j++){
					System.out.printf("%3d", pointArr[j].region);
				}
				System.out.println("");
			}
			in.close();
		} catch (FileNotFoundException e) {
			fail("File not found exception.");
			e.printStackTrace();
		} catch (IOException e) {
			fail("IOException.");
			e.printStackTrace();
		}
	}

}
