package au.edu.cmu.algorithm;

import java.util.Scanner;

public class BetweenKalib {

	public static void main(String[] args) {
		printResult(getInput());
	}
	
	public static int[][] getInput(){
		Scanner input = new Scanner(System.in);
		int noInput = input.nextInt();
		input.nextLine();
		int bounds[][] = new int[noInput][3]; //row x column
		for(int i = 0; i<noInput; i++){
			int lower = input.nextInt();
			input.nextLine();
			int upper = input.nextInt();
			input.nextLine();
			int divisor = input.nextInt();
			int bound[] = {lower, upper, divisor};
			bounds[i] = bound;
		}
		input.close();
		return bounds;
	}
	
	public static void printResult(int[][]bounds){
		for(int i = 0; i < bounds.length; i++){
			int counter = 0;
			int dividend = bounds[i][0];
			for(int j = dividend; j <= bounds[i][1]; j++){								
				if((j % bounds[i][2]) == 0 ){
					counter++;
				}
			}
			
			System.out.println("Case " + (i+1) + ": " + counter);
		}
	}
}
