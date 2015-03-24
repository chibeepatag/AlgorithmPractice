package au.edu.cmu.algorithm;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class WordSearchTest {
	static WordSearch ws;
	
	static Grid gridCat;
	
	static Grid gridDog;
	
	static Grid gridNana;
	
	static String keyDog;
	
	static String keyCat;
	
	static String keyNana;
	
	
	@BeforeClass
	public static void setup() {		
		ws = new WordSearch();
		gridCat = new Grid();
		gridCat.columns = 4;
		gridCat.rows = 3;
		gridCat.grid = new ArrayList<String>();
		gridCat.grid.add("catt");
		gridCat.grid.add("aata");
		gridCat.grid.add("tatc");
		keyCat = "cat";
		
		gridDog = new Grid();
		gridDog.rows = 5;
		gridDog.columns = 5;
		gridDog.grid = new ArrayList<String>();
		gridDog.grid.add("gogog");
		gridDog.grid.add("ooooo");
		gridDog.grid.add("godog");
		gridDog.grid.add("ooooo");
		gridDog.grid.add("gogog");
		keyDog = "dog";
		
		gridNana = new Grid();
		gridNana.columns = 8;
		gridNana.rows = 2;
		gridNana.grid = new ArrayList<String>();
		gridNana.grid.add("bananana");
		gridNana.grid.add("kalibrrr");		
		keyNana = "nana";
	}
	
	@Test
	public void countOccurenceInstring(){		
		int result = ws.countOccurenceInstring("nana", "bananana", 0);
		assertEquals(2, result);
	}
	
	@Test
	public void testHorizontal() {
		assertEquals(1, ws.horizontal(gridCat.grid, keyCat));
		assertEquals(2, ws.horizontal(gridDog.grid, keyDog));
		assertEquals(2, ws.horizontal(gridNana.grid, keyNana));		
	}
	
	@Test
	public void testVertical(){
		assertEquals(2, ws.vertical(gridCat, keyCat));
		assertEquals(2, ws.vertical(gridDog, keyDog));
		assertEquals(0, ws.vertical(gridNana, keyNana));
	}

}
