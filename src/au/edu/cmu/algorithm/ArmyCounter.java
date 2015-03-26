/**
 * 
 */
package au.edu.cmu.algorithm;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ChibeePatag
 * 
 */
public class ArmyCounter {

	public static void main(String[] args) {
		ArmyCounter ac = new ArmyCounter();
		List<Kingdom> kingdoms = ac.getInput(System.in);
	}

	public void assignLabel(Point[][] map) {
		int i = 0;
		int j = 0;
		int region = 1;
		// first pass
		while (i < map.length) {
			while (j < map[0].length) {
				if (map[i][j].label == '.'
						|| (map[i][j].label >= 97 && map[i][j].label <= 122)) {
					if (i > 0 && j > 0 && map[i][j - 1].region > 0
							&& map[i - 1][j].region > 0) {
						int westRegion = map[i][j - 1].region;
						int northRegion = map[i - 1][j].region;
						if (westRegion > 0 && northRegion > 0) {
							if (westRegion < northRegion) {
								map[i][j].region = westRegion;
							} else {
								map[i][j].region = northRegion;
							}
						} else if (westRegion > 0) {
							map[i][j].region = westRegion;
						} else if (northRegion > 0) {
							map[i][j].region = northRegion;
						}
					} else if (j > 0 && map[i][j - 1].region > 0) {
						map[i][j].region = map[i][j - 1].region;
					} else if (i > 0 && map[i - 1][j].region > 0) {
						map[i][j].region = map[i - 1][j].region;
					} else {
						map[i][j].region = region++;
					}
					j++;
				} else {
					j++;
				}
			}
			j = 0;
			i++;
		}
		// second pass

		i = 0;
		j = 0;
		while (i < map.length) {
			while (j < map[0].length) {
				if (map[i][j].label == '.'
						|| (map[i][j].label >= 97 && map[i][j].label <= 122)) {
					if (i > 0 && j > 0 && map[i][j - 1].region > 0
							&& map[i - 1][j].region > 0) {
						int westRegion = map[i][j - 1].region;
						int northRegion = map[i - 1][j].region;
						if (westRegion > 0 && northRegion > 0) {
							if (westRegion < northRegion) {
								map[i][j].region = westRegion;
								map[i - 1][j].region = westRegion;
							} else {
								map[i][j].region = northRegion;
								map[i][j - 1].region = northRegion;
							}
						} 						
					}
				} j++;
			}
			j = 0;
			i++;
		}

	}

	public List<Kingdom> getInput(InputStream in) {
		Scanner input = new Scanner(in);
		int noInput = input.nextInt();
		input.nextLine();

		List<Kingdom> kingdoms = new ArrayList<Kingdom>();
		for (int i = 0; i < noInput; i++) {
			Kingdom kingdom = new Kingdom();
			kingdom.rows = input.nextInt();
			input.nextLine();
			kingdom.columns = input.nextInt();
			input.nextLine();
			//
			kingdom.map = new Point[kingdom.rows][kingdom.columns];
			for (int j = 0; j < kingdom.rows; j++) {
				for (int k = 0; k < kingdom.columns; k++) {
					// char pointChar = input.next(".").charAt(0); 91-122
					char pointChar = input.findInLine(".|[a-z]").charAt(0);
					Point point = new Point(pointChar);
					kingdom.map[j][k] = point;
				}
				input.nextLine();
			}
			kingdom.armies = new HashMap<String, Integer>();
			kingdoms.add(kingdom);
		}
		input.close();
		return kingdoms;
	}
}

class Kingdom {
	Point[][] map;
	int rows;
	int columns;
	Map<String, Integer> armies;

}

class Point {
	char label; // # - mountain
	// char army;
	int region;

	public Point(char label) {
		super();
		this.label = label;
	}

	@Override
	public String toString() {
		return String.valueOf(label);
	}

}

class Region {
	List<String> armies;
}