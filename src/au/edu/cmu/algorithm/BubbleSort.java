package au.edu.cmu.algorithm;

import java.util.Scanner;

public class BubbleSort {

	private int[] arrToSort;
	
	public static void main(String[] args) {
		BubbleSort bubbleSort = new BubbleSort();
		bubbleSort.arrToSort = getArrayToSort();
		
		boolean switched = true;
		while(switched){
			switched = bubbleSort.bubbleSort(switched);			
		}
		
		printArray(bubbleSort.arrToSort);
	}
	
	public boolean bubbleSort(boolean switched){
		switched = false;
		for(int i = 0; i < arrToSort.length-1; i++){
			if(arrToSort[i] > arrToSort[i+1]){
				swapLeftToRight(i, i+1);
				switched = true;
			}
		}
		return switched;
		
	}
	
	private static void printArray(int array[]) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			System.out.print(" ");
		}
		System.out.println("");
	}
	
	private void swapLeftToRight(int indexLeft, int indexRight){
		int holder = arrToSort[indexLeft];
		arrToSort[indexLeft] = arrToSort[indexRight];
		arrToSort[indexRight] = holder;
	}

	private static int[] getArrayToSort() {
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		String[] inputArr = input.split(" ");
		int intArr[] = new int[inputArr.length];
		for (int i = 0; i < inputArr.length; i++) {
			intArr[i] = Integer.parseInt(inputArr[i]);
		}

		in.close();

//		int intArr[] = { 7, 4, 1, 8, 6, 9, 2, 3, 5 };
//		int intArr [] = {6, 5, 1, 3, 8, 4, 7, 9, 2};
//		int intArr [] = {9, 2, 4, 1, 6, 8, 7, 3, 5};
		return intArr;
	}
}
