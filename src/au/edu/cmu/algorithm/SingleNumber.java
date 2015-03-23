/**
 * 
 */
package au.edu.cmu.algorithm;

/**
 * @author ChibeePatag
 * 
 */
public class SingleNumber {
	public static void main(String[] args) {
		int arrInts[] = {2,2,2,5,5,5,7,7,7,1,1,1,2};
		SingleNumber sn = new SingleNumber();
		System.out.println(sn.findTheNot3(arrInts));
	}
	
	int findTheNot3(int[] arrInts){
		Entry entries[] = new Entry[arrInts.length];
		int result = -1;
		for(int i = 0; i < arrInts.length; i++){
			boolean contains = contains(entries, arrInts[i]);
			if(!contains){
				Entry entry = new Entry();
				entry.number = arrInts[i];
				for(int j = 0; j < entries.length; j++){
					if(null == entries[j]){
						entries[j] = entry;
					}
				}
			}
			

			for(Entry entry : entries){
				if(entry.frequency != 3){
					result = entry.number;
				}
			}
		}
		return result;
	}
	
	private boolean contains(Entry entries[], int number){
		boolean result = false;
		for(Entry entry : entries){
			if(null != entry && entry.number == number){
				result = true;
				entry.frequency++;
				break;
			}
		}
		return result;
	}
	
	private class Entry{
		int number;
		int frequency;
	}
	
	//
}
