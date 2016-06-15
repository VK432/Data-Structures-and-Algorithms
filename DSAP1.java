import java.util.*;
import java.io.*;

public class DSAP1{

	static int[][] grid;
	static int gridSize = 0;
	static HashMap<String,Integer> mem = new HashMap<String,Integer>();



	public static void main(String args[]){
		// final long startTime = System.currentTimeMillis();

		read(args[1]);
		int max = 0;
		switch(args[0]){
			case "-r":
				for(int y = 0; y<gridSize; y++){
					for(int x = 0; x<gridSize; x++){
						int result = recursive(x,y);
						if(result>max){
							max = result;
						}
					}
				}
				break;
			case "-m":
				for(int y = 0; y<gridSize; y++){
					for(int x = 0; x<gridSize; x++){
						int result = memoized(x,y);
						if(result>max){
							max = result;
						}
					}
				}
				break;
			case "-i":
				for(int y = 0; y<gridSize; y++){
					for(int x = 0; x<gridSize; x++){
						int result = iterative(x,y);
						if(result>max){
							max = result;
						}
					}
				}
				break;
		}
		System.out.println(max);		

		// final long endTime = System.currentTimeMillis();
		// System.out.println("Total execution time: " + (endTime - startTime) );

	} 


	public static void read(String input){

		File file = new File(input);
		try{
			Scanner in = new Scanner(file);
			gridSize = Integer.parseInt(in.nextLine());
			//System.out.println(gridSize);

			int[][] t_grid = new int[gridSize][gridSize];

			for(int i = 0; i<gridSize; i++ ){
				String line = in.nextLine();
				String[] pixels = line.split("");

				for(int x = 0; x<gridSize; x++ ){
					t_grid[x][i] = Integer.parseInt(pixels[x]);
					//System.out.print(t_grid[x][i]);
				}
				//System.out.println();
			}
			grid = t_grid;
		}catch(Exception e){
			System.out.println("\nNo File");}

	}



	public static int recursive(int x, int y){
		if(grid[x][y] == 1){
			return 0;
		}
		else if(x == 0 | y == 0){
			return 1;
		}
		else{
			int a = recursive(x-1,y-1);
			int b = recursive(x-1,y);
			int c = recursive(x, y-1);
			if(a<=b && a<=c){
				return a+1;
			}
			if(b<=a && b<=c){
				return b+1;
			}
			if(c<=a && c<=b) {
				return c+1;
			}
		}
		return 0;
	}


	public static int memoized(int x, int y){
		String key = (Integer.toString(x))+"-"+(Integer.toString(y));


		if(!(mem.get(key) == null)){
			return mem.get(key);
		}

		else if(grid[x][y] == 1){
			mem.put(key,0);
			return 0;
		}
		else if(x == 0 | y == 0){
			mem.put(key,1);
			return 1;
		}
		else{
			int a = memoized(x-1,y-1);
			int b = memoized(x-1,y);
			int c = memoized(x, y-1);
			if(a<=b && a<=c){
				mem.put(key,a+1);
				return a+1;
			}
			if(b<=a && b<=c){
				mem.put(key,b+1);
				return b+1;
			}
			if(c<=a && c<=b) {
				mem.put(key,c+1);
				return c+1;
			}
		}
		return 0;
	}


	public static int iterative(int x, int y){
		String key = (Integer.toString(x))+"-"+(Integer.toString(y));

		if(grid[x][y] == 1){
			mem.put(key,0);
			return 0;
		}
		else if(x == 0 | y == 0){
			mem.put(key,1);
			return 1;
		}
		else{
			String key_a = (Integer.toString(x-1))+"-"+(Integer.toString(y-1));
			String key_b = (Integer.toString(x-1))+"-"+(Integer.toString(y));
			String key_c = (Integer.toString(x))+"-"+(Integer.toString(y-1));
			int a = mem.get(key_a);
			int b = mem.get(key_b);
			int c = mem.get(key_c);
			
			if(a<=b && a<=c){
				mem.put(key,a+1);
				return a+1;
			}
			if(b<=a && b<=c){
				mem.put(key,b+1);
				return b+1;
			}
			if(c<=a && c<=b) {
				mem.put(key,c+1);
				return c+1;
			}
		}
		return 0;
	}


	
}