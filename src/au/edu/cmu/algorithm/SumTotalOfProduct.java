/**
 * 
 */
package au.edu.cmu.algorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author ChibeePatag
 *
 */
public class SumTotalOfProduct {

	int[] arrToSort;
	int[] arranged;
	boolean[] permanent;
	
	public static void main(String[] args) {
		SumTotalOfProduct stp = new SumTotalOfProduct();		
		stp.getInput();
		stp.mergeArrays();
		System.out.println("done");
	}
	
	void getInput(){
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		arrToSort = new int[size];
		arranged = new int[size];
		permanent = new boolean[size];
		in.nextLine();
		int arrToSortIndex = 0;		
		for (int i = 0; i < size; i++){			
			String[] line = in.nextLine().split(" ");
			int position = Integer.parseInt(line[1]);
			if(position < 0){
				arrToSort[arrToSortIndex] = Integer.parseInt(line[0]);
				arrToSortIndex++;
			}else{
				arranged[position-1] = Integer.parseInt(line[0]);
				permanent[position-1] = true;				
			}
		}
		arrToSort = Arrays.copyOfRange(arrToSort, 0, arrToSortIndex);
		Arrays.sort(arrToSort, 0 , arrToSortIndex);
	}
	
	void mergeArrays(){
		for(int i = 0; i < arrToSort.length; i++){
			int number = arrToSort[i];
			for(int j = 0; j < permanent.length; j++){
				if(!permanent[j]){
					arranged[j] = number;
					permanent[j] = true;
					break;
				}
			}
		}
	}
	
}
