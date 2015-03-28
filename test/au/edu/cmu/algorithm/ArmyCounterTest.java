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
				ac.assignRegion(kingdom.map);
			}
			
			/*
			int expectedRegions[][] = {
										{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
										{0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 1},
										{0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 3, 0, 1, 1},
										{4, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 1, 1},
										{0, 0, 5, 5, 5, 0, 6, 6, 6, 0, 3, 3, 0, 1, 1},
										{0, 5, 5, 5, 0, 6, 6, 6, 0, 3, 3, 3, 0, 0, 0},
										{0, 0, 0, 0, 7, 0, 6, 0, 8, 0, 0, 0, 9, 9, 0},
										{7, 7, 7, 7, 7, 7, 0, 8, 8, 8, 8, 0, 9, 9, 0},
										{7, 0, 7, 7, 7, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0},
										{7, 0, 7, 7, 7, 7, 0, 10,10,0, 11,11,11,11,11},
										{7, 0, 0, 0, 0, 0, 0, 10,10,0, 11,11,11,11,11},
										{7, 7, 7, 7, 7, 7, 0, 0, 0, 0, 11,11,11,11,11}
			};
			*/
			int expectedRegions[][] = {
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 },
					{ 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 1 },
					{ 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 3, 0, 1, 1 },
					{ 4, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 1, 1 },
					{ 0, 0, 5, 5, 5, 0, 6, 6, 6, 0, 3, 3, 0, 1, 1 },
					{ 0, 5, 5, 5, 0, 6, 6, 6, 0, 3, 3, 3, 0, 0, 0 },
					{ 0, 0, 0, 0, 11,0, 6, 0,12, 0, 0, 0, 13,13,0 },
					{ 11, 11, 11, 11, 11, 11, 0, 12, 12, 12, 12, 0, 13, 13, 0 },
					{ 11, 0, 11, 11, 11, 11, 0, 0,  0,  0, 0,  0,  0,  0,  0 },
					{ 11, 0,  11, 11, 11, 11, 0, 16, 16, 0, 17, 17, 17, 17, 17 },
					{ 11, 0,  0,  0,  0,  0,  0, 16, 16, 0, 17, 17, 17, 17, 17 },
					{ 11, 11, 11, 11, 11, 11, 0,  0,  0, 0, 17, 17, 17, 17, 17 } };
			
			
			for(int i = 0; i < expectedRegions.length; i++){
				for(int j = 0; j < expectedRegions[0].length; j++){
					assertEquals(expectedRegions[i][j], kingdoms.get(0).map[i][j].region);
				}
			}			
			
			
			//printing to console
			Point[][] map = kingdoms.get(0).map;
			for(int i = 0; i < map.length; i++){
				Point[] pointArr = map[i];
				for(int j = 0; j < pointArr.length; j++){
					System.out.printf("%2d", pointArr[j].region);
				}
				System.out.println("");
			}//end of printing to console
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
	public void testCreateCollectionOfRegions(){
		ArmyCounter ac = new ArmyCounter();
		
		
		//Point[][] map 
		
//		ac.createCollectionOfRegions(map);
	}

}
