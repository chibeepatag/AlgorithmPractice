package au.edu.cmu.algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class SumTotalOfProductOld {

	Item[] arranged;
	int arrToSort[];

	public static void main(String[] args) {
		SumTotalOfProductOld stp = new SumTotalOfProductOld();
		stp.getInput();
		
	}

	void getInput() {
		Scanner scan = new Scanner(System.in);
		int lines = scan.nextInt();
		arrToSort = new int[lines];
		arranged = new Item[lines];

		for (int i = 0; i < lines; i++) {
			String line[] = scan.nextLine().split(" ");
			int permanentPosition = Integer.parseInt(line[1]);
			int number = Integer.parseInt(line[0]);
			Item item = new Item(number);
			if (permanentPosition > -1) {
				arranged[permanentPosition] = item;
				arranged[permanentPosition].permanentPosition = permanentPosition;
			} else {
				// place in non permenent position
				for (int j = 0; j < arranged.length; j++) {										
					arrToSort[i] = number;
				}
			}
		}

		scan.close();
	}

	void rearrange() {
		quickSort(0, arrToSort.length - 1);
		
		for(int i = arranged.length-1; i <=0; i--){
			Item item = arranged[i];
			if(item.permanentPosition != -1){
				if(item.permanentPosition == arranged.length-1){
					arrToSort[item.permanentPosition] = item.number;
				}else{
					for(int j = arranged.length-1; i <=item.permanentPosition; i--){
						arranged[j] = arranged[j-1];
					}
					arranged[item.permanentPosition] = item;
				}
			}
		}

		System.out.println(getSumProduct());

	}

	int getSumProduct() {
		int sumproduct = 0;
		for (int i = 0; i < arranged.length - 2; i++) {
			sumproduct = sumproduct
					+ (arranged[i].number * arranged[i + 1].number);
		}

		return sumproduct;
	}

	private void quickSort(int start, int end) {
		if (end <= start) {
			return;
		}
		// compare contents of array of size 2
		int size = end - start;
		if (size == 1) {
			if (arrToSort[end] < arrToSort[start]) {
				switchRightToLeft(start, end);
			}
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
		if (pivotIndex - start > 1) {
			quickSort(start, pivotIndex - 1);
		}
		quickSort(pivotIndex + 1, end);

	}

	private void switchRightToLeft(int left, int right) {
		int holder = arrToSort[left];
		arrToSort[left] = arrToSort[right];
		arrToSort[right] = holder;
	}

	class Item {
		int number;
		int permanentPosition;

		public Item(int number) {
			this.number = number;
		}
	}
}
