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
		ws.horizontal();
		
		ws.vertical();
		ws.diagonal();
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

	public void horizontal() {
		Iterator<String> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();

			GridAndCount gc = words.get(key);
			for (String row : gc.grid.grid) {
				countOccurenceInstring(key, row, gc.count);
				StringBuffer sb = new StringBuffer(row);
				countOccurenceInstring(key, sb.reverse().toString(), gc.count);
			}
		}
	}

	public void vertical() {
		Iterator<String> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();

			GridAndCount gc = words.get(key);
			gc.grid.transposeGrid();
			for (String row : gc.grid.transposedGrid) {
				countOccurenceInstring(key, row, gc.count);
				StringBuffer sb = new StringBuffer(row);
				countOccurenceInstring(key, sb.reverse().toString(), gc.count);
			}
		}
	}

	public void diagonal() {
		for (String key : keys) {
			GridAndCount gc = words.get(key);
			// west side
			for (int i = 0; i < gc.grid.rows; i++) {
				StringBuffer findIn = new StringBuffer();
				for (int j = i, k = 0; j < gc.grid.rows && k < gc.grid.columns; j++, k++) {
					findIn.append(gc.grid.grid.get(j).charAt(k));
				}
				countOccurenceInstring(key, findIn.toString(), gc.count++);
			}
			// north side
			for (int i = 1; i < gc.grid.columns; i++) {
				StringBuffer findIn = new StringBuffer();
				for (int j = 0, k = i; j < gc.grid.rows && k < gc.grid.columns; j++, k++) {
					findIn.append(gc.grid.grid.get(j).charAt(k));
				}
				countOccurenceInstring(key, findIn.toString(), gc.count++);
			}
		}

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
			gc.grid.rows = noOfRows;
			gc.grid.columns = noOfColumns;
			gc.caseNo = i + 1;
			gc.grid.grid = rows;
			words.put(input.nextLine(), gc);

		}

		input.close();
	}

	private int countOccurenceInstring(String key, String findIn, int count) {
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

}
