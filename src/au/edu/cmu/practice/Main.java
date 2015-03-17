package au.edu.cmu.practice;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = Integer.parseInt(in.nextLine());		
		String bc = in.nextLine();
		String text = in.nextLine();
		
		String [] splitBc = bc.split(" ");
		int b = Integer.parseInt(splitBc[0]);
		int c = Integer.parseInt(splitBc[1]);
		
		int sum = a + b + c;
		StringBuffer sb = new StringBuffer();
		sb.append(sum);
		sb.append(" ");
		sb.append(text);
		in.close();
		
		System.out.println(sb.toString());
		
		
	}
	
}
