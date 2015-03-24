/**
 * 
 */
package au.edu.cmu.algorithm;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author ChibeePatag
 * 
 */
public class WordSearch {

	Map<String, GridAndCount> words;

	Set<String> keys;

	public static void main(String[] args) {
		WordSearch ws = new WordSearch();

		ws.getInput(System.in);
		ws.keys = ws.words.keySet();

		for (String key : ws.keys) {
			GridAndCount gc = ws.words.get(key);
			gc.count = gc.count + ws.horizontal(gc.grid.grid, key);
			gc.count = gc.count + ws.vertical(gc.grid, key);
			gc.count = gc.count + ws.diagonal(gc.grid, key);
		}

		ws.printResults();
	}

	private void printResults() {
		List<GridAndCount> wordList = new ArrayList<GridAndCount>(
				words.values());
		Collections.sort(wordList);
		for (GridAndCount gc : wordList) {
			System.out.println("Case " + gc.caseNo + ": " + gc.count);
		}
	}

	public int horizontal(List<String> grid, String key) {
		int count = 0;
		for (String row : grid) {
			count = countOccurenceInstring(key, row, count);
			StringBuffer sb = new StringBuffer(row);
			count = countOccurenceInstring(key, sb.reverse().toString(), count);
		}
		return count;

	}

	public int vertical(Grid grid, String key) {
		grid.transposeGrid();
		return horizontal(grid.transposedGrid, key);

	}

	public int diagonal(Grid grid, String key) {
		int count = 0;
		// west side NW to SE
		for (int i = 0; i < grid.rows; i++) {
			StringBuffer findIn = new StringBuffer();
			for (int j = i, k = 0; j < grid.rows && k < grid.columns; j++, k++) {
				findIn.append(grid.grid.get(j).charAt(k));
			}
			count = countOccurenceInstring(key, findIn.toString(), count);
			count = countOccurenceInstring(key, findIn.reverse().toString(), count);
		}
		// north side NW to SE
		for (int i = 1; i < grid.columns; i++) {
			StringBuffer findIn = new StringBuffer();
			for (int j = 0, k = i; j < grid.rows && k < grid.columns; j++, k++) {
				findIn.append(grid.grid.get(j).charAt(k));
			}
			count = countOccurenceInstring(key, findIn.toString(), count);
			count = countOccurenceInstring(key, findIn.reverse().toString(), count);
		}
		//north side NE to SW
		for (int i = grid.columns-1; i >= 0; i--){
			StringBuffer findIn = new StringBuffer();
			for (int j = 0, k = i; j < grid.rows && k >= 0; j++, k--){//j - row, k column
				findIn.append(grid.grid.get(j).charAt(k));
			}
			count = countOccurenceInstring(key, findIn.toString(), count);
			count = countOccurenceInstring(key, findIn.reverse().toString(), count);
			
		}
		
		//east side NE to SW
		for (int i = 1; i < grid.rows; i++){
			StringBuffer findIn = new StringBuffer();
			for(int j = i, k = grid.columns -1; j < grid.rows && k >=0; j++, k--){
				findIn.append(grid.grid.get(j).charAt(k));
			}
			count = countOccurenceInstring(key, findIn.toString(), count);
			count = countOccurenceInstring(key, findIn.reverse().toString(), count);
		}
		return count;

	}
	
	

	public void getInput(InputStream in) {
		Scanner input = new Scanner(in);
		int noInput = input.nextInt();
		input.nextLine();

		words = new HashMap<String, GridAndCount>();
		for (int i = 0; i < noInput; i++) {
			int noOfRows = input.nextInt();
			input.nextLine();
			int noOfColumns = input.nextInt();
			input.nextLine();
			List<String> rows = new ArrayList<String>();
			for (int j = 0; j < noOfRows; j++) {
				rows.add(input.nextLine());
			}
			GridAndCount gc = new GridAndCount();
			gc.grid = new Grid();
			gc.grid.rows = noOfRows;
			gc.grid.columns = noOfColumns;
			gc.caseNo = i + 1;
			gc.grid.grid = rows;
			words.put(input.nextLine(), gc);

		}

		input.close();
	}

	int countOccurenceInstring(String key, String findIn, int count) {
		String reducedFindIn = findIn;
		int index = reducedFindIn.indexOf(key);
		while (index >= 0) {
			count++;
			reducedFindIn = reducedFindIn.substring(++index);
			index = reducedFindIn.indexOf(key);
		}
		return count;
	}

	class GridAndCount implements Comparable<GridAndCount> {
		Grid grid;
		int caseNo;
		int count;

		@Override
		public int compareTo(GridAndCount o) {
			return this.caseNo - o.caseNo;
		}
	}

}

class Grid {
	List<String> grid;
	List<String> transposedGrid;

	int rows;
	int columns;

	public void transposeGrid() {
		transposedGrid = new ArrayList<>();

		for (int i = 0; i < columns; i++) {
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < rows; j++) {
				sb.append(grid.get(j).charAt(i));
			}
			transposedGrid.add(sb.toString());
		}

	}
}
