package au.edu.cmu.algorithm;

import java.util.Scanner;

/**
 * @author ChibeePatag
 *
 */
public class RandomNumberGenerator {

	public static int seed;
	public static int numberOfRns;
	
	public static void main(String[] args) {
		getUserInput();		
		generateRandomNumbers();
				
	}

	private static void generateRandomNumbers() {
		int index = 0;
		int randomNumber = seed;
		int square;
		int rightMost;
		int lastTwoDigits;
		
		while(index < numberOfRns){
			square = randomNumber * randomNumber;
			rightMost = square % 1000000;
			lastTwoDigits = rightMost % 100;
			randomNumber = (rightMost - lastTwoDigits)/100;
			System.out.println(randomNumber);
			index++;
		}
	}

	private static void getUserInput() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the seed: ");
		seed = in.nextInt();
		
		System.out.print("Enter the number of random numbers to generate: ");
		numberOfRns = in.nextInt();
		in.close();
	}
	
}
