package au.edu.cmu.algorithm;

import java.util.Scanner;

public class Construction {
	
	Island islands[];	
	
	public static void main(String[] args) {
		Construction construction = new Construction();
		Input input = construction.getInput();
		construction.islands = new Island[input.noOfIslands];
		construction.runQueries(input);
	}
	
	void runQueries(Input input){
		//Create the islands
		for(int i = 0; i < input.noOfIslands; i++){
			islands[i] = new Island();	
			islands[i].islandNo = i + 1;
			islands[i].destinations = new int[input.noOfIslands];
		}
		
		for(int i = 0; i < input.noOfDays; i++){
			Query query = input.queries[i];
			if(query.type == 'b'){
				islands[query.source - 1].destinations[query.destination - 1] = query.destination;
			}else{
				Island source = islands[query.source-1];
				checkIfCanTravel(source, query.destination);
			}
		}
		
	}
	
	void checkIfCanTravel(Island source, int destination){
		//yes date OR NO
		if(!checkIfIslandHasBridges(source)){
			System.out.println("N0");
		}else{
			if(source.destinations[destination-1] > 0){
				System.out.println("YES");
			}
		}
	}
	
	boolean checkIfIslandHasBridges(Island island){
		boolean result = false;
		for(int i = 0 ; i < island.destinations.length; i++){
			if(island.destinations[i] > 0){
				result = true;
				break;
			}
		}
		return result;
	}
	
	Input getInput(){
		Input input = new Input();
		
		Scanner scan = new Scanner(System.in);
		String line1[] = scan.nextLine().split(" ");
		input.noOfIslands = Integer.parseInt(line1[0]);
		input.noOfDays = Integer.parseInt(line1[1]);
		Query queries[] = new Query[input.noOfDays];
		input.queries = queries;
		for(int i = 0; i < input.noOfDays;  i++ ){
			String line[]  = scan.nextLine().split(" ");
			Query query = new Query();
			if(line[0].equals("build")){
				query.type = 'b';
			}else{
				query.type = 'c';
			}
			query.source = Integer.parseInt(line[1]);
			query.destination = Integer.parseInt(line[2]);
			queries[i] = query;
		}
		
		scan.close();
		return input;
	}
	
	class Island{
		int islandNo;
		int destinations[];
		
	}
	
	class Input{
		int noOfIslands;
		int noOfDays;
		
		Query queries[];
		
	}
	
	class Query{
		char type;
		int source;
		int destination;
	}
}
