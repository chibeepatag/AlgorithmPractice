/**
 * 
 */
package au.edu.cmu.algorithm;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author ChibeePatag
 * 
 */
public class ArmyCounter {

	public static void main(String[] args) {
		ArmyCounter ac = new ArmyCounter();
		List<Kingdom> kingdoms = ac.getInput(System.in);
		for (int i = 0; i < kingdoms.size(); i++) {
			Kingdom kingdom = kingdoms.get(i);
			ac.assignRegion(kingdom.map);
			Set<Region> regions = new HashSet<Region>(
					ac.createCollectionOfRegions(kingdom.map));
			int contested = ac.setRulingArmy(regions);
			kingdom.contested = contested;
			
			System.out.printf("Case %d: \n", i+1);
			Map<Character, Integer> armyMap = ac.getCountOfRegionsPerArmy(regions);
			List<Character> armies = new ArrayList<>( armyMap.keySet())  ;
			Collections.sort(armies);
			for (Character character : armies) {
				if(character >= 97 && character <= 122){
					System.out.printf("%c %d\n", character.charValue(), armyMap.get(character));					
				}
			}
			System.out.printf("contested %d\n", kingdom.contested);
			
		}
	}
	
	public Map<Character, Integer> getCountOfRegionsPerArmy(Set<Region> regions){
		Map<Character, Integer> armyMap = new HashMap<Character, Integer>();
		for(Region region : regions){
			Integer count = armyMap.get(region.rulingArmy);
			if(null != count){
				int newCount = count.intValue();
				newCount++;				
				armyMap.put(region.rulingArmy, newCount);
			}else{
				armyMap.put(region.rulingArmy, 1);
			}
		}
		return armyMap;
	}
	
	public int setRulingArmy(Set<Region> regions) {
		int contested = 0;
		for (Region region : regions) {
			for (Point point : region.points) {
				if (point.label >= 97 && point.label <= 122) {
					region.armies.add(point.label);
				}
			}
			if (region.armies.size() > 1) {								
				contested++;
				region.rulingArmy = 0;
			} else if (region.armies.size() == 0) {
				region.rulingArmy = 0;
			} else{
				region.rulingArmy = new ArrayList<>(region.armies).get(0); 				
			}
		}

		return contested;
	}

	public void assignRegion(Point[][] map) {
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
							if (westRegion < northRegion) { // take the smaller
															// region
								map[i][j].region = westRegion;
								mergePrecedingRegion(map, j, i);
							} else {
								map[i][j].region = northRegion;
								mergePrecedingRegion(map, j, i);
							}
						}
					} else if (j > 0 && map[i][j - 1].region > 0) { // west
																	// region
						map[i][j].region = map[i][j - 1].region;
					} else if (i > 0 && map[i - 1][j].region > 0) { // north
																	// region
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

	}

	private Point[][] mergePrecedingRegion(Point[][] map, int column, int row) {
		while (column > 0) {
			if (map[row][column - 1].region > 0) {
				map[row][column - 1].region = map[row][column].region;
				column--;
			} else {
				break;
			}
		}
		return map;
	}

	public Collection<Region> createCollectionOfRegions(Point[][] map) {
		Map<Integer, Region> mapOfRegions = new HashMap<Integer, Region>();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				Integer regionInt = map[i][j].region;
				if(regionInt > 0){
					Region region = mapOfRegions.get(regionInt);
					if (null == region) {
						region = new Region(regionInt);
						region.points = new HashSet<Point>();
						region.armies = new HashSet<Character>();						
						mapOfRegions.put(regionInt, region);
					}
					region.points.add(map[i][j]);			
				}
			}
		}

		Collection<Region> regions = mapOfRegions.values();
		return regions;
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
					Point point = new Point(pointChar, j, k);
					kingdom.map[j][k] = point;
				}
				input.nextLine();
			}
			
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
//	Map<String, Integer> armies;
	int contested;

}

class Point {
	char label; // # - mountain, [a-c], .
	int region;
	int x, y;

	public Point(char label, int x, int y) {
		super();
		this.label = label;
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return String.valueOf(label);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}

class Region implements Comparable<Region>{
	int region;
	Set<Character> armies;
	Set<Point> points;

	char rulingArmy;

	public Region(int region) {
		this.region = region;
	}
	
	@Override
	public int compareTo(Region o) {
		return this.rulingArmy - o.rulingArmy;		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Region other = (Region) obj;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		return true;
	}

}