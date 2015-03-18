/**
 * 
 */
package au.edu.cmu.algorithm;

/**
 * @author ChibeePatag
 * 
 */
public class Histogram {
	public static void main(String[] args) {
		Histogram histogram = new Histogram();
				
		int data[] = histogram.getData(); 
		
		histogram.drawHistogram(data);
	}
	
	private void drawHistogram(int[] data){
		int max = findMax(data);
		int min = findMin(data);
		int range = max - min;
		int noOfIntervals = (int)Math.ceil(Math.sqrt(data.length));
		
		int intervalWidth = range / noOfIntervals;
		Interval[] intervals = new Interval[noOfIntervals];
		double lowerbound = min - 0.5;
		for (int i = 0 ; i < noOfIntervals; i++){
			Interval interval = new Interval();
			interval.lowerbound = lowerbound - 0.5;
			interval.upperbound = lowerbound + intervalWidth;
			lowerbound = interval.upperbound;
			intervals[i] = interval;				
		}
		intervals[intervals.length - 1].upperbound++;
		
		setFrequencies(data, intervals);
		printHistogram(intervals);
	}
	
	private Interval[] setFrequencies(int[] data, Interval[] histogram){
		for(int i = 0; i < data.length; i++){
			int currentData = data[i];
			for(Interval interval : histogram){
				double lowerbound = interval.lowerbound;
				double upperbound = interval.upperbound;
				
				if(currentData >= lowerbound && currentData < upperbound){
					interval.frequency++;
					break;
				}
			}
		}
		
		
		return histogram;
	}
	
	private void printHistogram(Interval[] histogram){
		
		for(Interval interval : histogram){
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < interval.frequency; i++){
				sb.append("*");
			}
			System.out.printf("%5.2f - %5.2f", interval.lowerbound, interval.upperbound);
			System.out.print(" ");
			System.out.println(sb.toString());
		}
	}
	
	private int findMax(int[] data){
		int max = data[0];
		for(int i = 1; i < data.length; i++){
			if (max < data[i]){
				max = data[i];
			}
		}		
		return max;
	}
	
	private int findMin(int[] data){
		int min = data[0];
		for(int i = 1; i < data.length; i++){
			if (min > data[i]){
				min = data[i];
			}
		}		
		return min;
	}
	
	
	private int[] getData(){
//		int data[] = {8820, 10800, 12000, 12500, 13000, 14000, 15000, 16000, 16500, 16600, 16700, 16900, 16900, 17000, 17000, 17600, 17880, 18000, 18000, 18000, 18000, 18000, 18000, 18000, 18500, 18680, 19100, 20000, 20000, 20000, 20000, 20000, 20300, 20900, 22000, 23000, 23000, 23000, 23000, 23400, 24000, 25000, 25000, 26000, 26000, 27000, 30000, 30000, 32500, 37000, 48000};
		int data[] = {34, 2, 20, 26, 19, 30, 2,	17,	50,	36};
  
		return data;
	}
	class Interval{
		double lowerbound;
		double upperbound;
		int frequency = 0;
		
	}
	
}
