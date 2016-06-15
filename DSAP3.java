import java.util.*;
import java.io.*;

public class DSAP3{

	static int n;
	static HashMap<Integer,String[]> children = new HashMap<Integer,String[]>();
	static HashMap<Integer,Integer> score = new HashMap<Integer,Integer>();


	public static void main(String args[]){
		//final long startTime = System.currentTimeMillis();

		int max = 0;

		read(args[0]);

		System.out.println(recursive(1));

		//final long endTime = System.currentTimeMillis();
		//System.out.println("Total execution time: " + (endTime - startTime) );

	} 


	public static void read(String input){

		File file = new File(input);
		try{

			int id = 0;
			int magic_score = 0;
			
			Scanner in = new Scanner(file);

			int n = Integer.parseInt(in.nextLine());

			while(in.hasNextLine()){
				
				String line2 = in.nextLine();

				String[] half_line = line2.split(":");
				half_line[0] = half_line[0].replaceAll("\\s+","-");
				String[] magic = half_line[0].split("-");
				id = Integer.parseInt(magic[0]);
				magic_score = Integer.parseInt(magic[1]);
				score.put(id,magic_score);
				
				
				if(!(half_line.length == 1) && !(half_line[1].equals(" ")) && !(half_line[1].equals(""))){
					
					half_line[1] = half_line[1].replaceAll("\\s+","-");
				
					String[] aprentices = half_line[1].split("-");

					String[] clean_apprentices = new String[aprentices.length-1];

					for (int i=1;i<aprentices.length ;i++ ){
						//System.out.println(aprentices[i]);
						clean_apprentices[i-1] = aprentices[i];
					}
					children.put(id,clean_apprentices);	

				}				
				else{
					String[] empty = {};
					children.put(id,empty);
				}
				

			}

		}catch(Exception e){
			System.out.println("\nNo File");}

	}

	public static int recursive(Integer id){
		Integer children_score = 0;
		Integer grand_score = 0;
		int my_score = score.get(id);
		String[] aprentices = children.get(id);


		if(aprentices.length == 0){
			return my_score;
		}
		for(int i=0; i<aprentices.length; i++){
			children_score += recursive(Integer.parseInt(aprentices[i]));
			String[] grand_apprentices = children.get(Integer.parseInt(aprentices[i]));
			for(int a = 0; a<grand_apprentices.length; a++){
			 	grand_score += recursive(Integer.parseInt(grand_apprentices[a]));
			}
		}
		if(my_score+grand_score>=children_score){
			return my_score+grand_score;
		}
		else {
			return children_score;
		}
	}




	


	


	
}