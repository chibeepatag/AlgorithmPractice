package au.edu.cmu.algorithm;

import java.util.Scanner;

public class QuickSort {

	private int[] arrToSort;

	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		qs.arrToSort = getArrayToSort();
		printArray(qs.arrToSort);
		qs.quickSort(0, qs.arrToSort.length - 1);
		printArray(qs.arrToSort);
	}

	private void quickSort(int start, int end) {
		if (end <= start) {
			return;
		}
		int pivotIndex = end;
		int pivot = arrToSort[pivotIndex];
		int wall = start;

		for (int i = start; i < end; i++) {
			if (pivot > arrToSort[i]) {
				switchRightToLeft(i, wall);
				wall++;
			}
		}
		// pivot should be at the wall
		switchRightToLeft(pivotIndex, wall);
		pivotIndex = wall;
		if (pivotIndex - start > 2) {
			quickSort(start, pivotIndex - 1);
		}
		quickSort(pivotIndex + 1, end);

	}

	private void switchRightToLeft(int left, int right) {
		int holder = arrToSort[left];
		arrToSort[left] = arrToSort[right];
		arrToSort[right] = holder;
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

		// int intArr[] = { 7, 4, 1, 8, 6, 9, 2, 3, 5 };
		// int intArr [] = {6, 5, 1, 3, 8, 4, 7, 9, 2};
		return intArr;
	}

	private static void printArray(int array[]) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			System.out.print(" ");
		}
		System.out.println("");
	}
}
