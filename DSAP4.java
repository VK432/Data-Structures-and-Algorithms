import java.util.*;
import java.io.*;

public class DSAP4{

	static int n;
	static int[] b, m;
	static HashMap<String,Integer> mem = new HashMap<String,Integer>();



	public static void main(String args[]){
		//final long startTime = System.currentTimeMillis();
		
		read(args[1]);	

		switch(args[0]){
			case "-r":
				System.out.println(recursive(0,0));
				break;
			case "-m":
				System.out.println(memoized(0,0));
				break;
			case "-i":
				for(int i = n-1; i>=0; i--){
					for(int j = i; j>=0; j--){
						iterative(i,j);
					}
				}
				System.out.println(mem.get("i-0j-0"));
				
				break;
				
		}
				
		//final long endTime = System.currentTimeMillis();
		//System.out.println("Total execution time: " + (endTime - startTime) );

	} 


	public static void read(String input){

		File file = new File(input);
		try{
			
			Scanner in = new Scanner(file);
			n = Integer.parseInt(in.nextLine());
			 
			//System.out.println(n);
			int[] bs = new int[n];
			int[] ms = new int[n];

			String line = in.nextLine();
			//System.out.println(line);
			String[] bs_text = line.split(" ");

			int a = 0;
			for(int i =0; i<bs_text.length; i++){
				if(!(bs_text[i].equals(" ")) && !(bs_text[i].equals(""))){
					bs[a] = Integer.parseInt(bs_text[i]);
					a++;
					
				}
			}
			
			b = bs;

			line = in.nextLine();
			String[] ms_text = line.split(" ");
			int b = 0;
			for(int i =0; i<ms_text.length; i++){
				if(!(ms_text[i].equals(" ")) && !(ms_text[i].equals(""))){
					ms[b] = Integer.parseInt(ms_text[i]);
					b++;
				}
			}
			
			m = ms;
			

		}catch(Exception e){
			System.out.println("\nNo File");}

	}

	public static int recursive(int i, int j){

		if(i == n-1){
			if(b[i]<m[j]){
				return b[i];
			}
			else{
				return m[j];
			}
		}
		int a = 0;
		if(b[i]<m[j]){
			a += b[i];
		}
		else{
			a += m[j];
		}
		a += recursive(i+1,j+1);
		int b = recursive(i+1,0);

		if(a>b){
			return a;
		}
		else{
			return b;
		}
		
		
		
	}


	public static int memoized(int i, int j){
		String key = ("i-"+Integer.toString(i) + "j-" + Integer.toString(j));
		

		if(!(mem.get(key) == null)){

			return mem.get(key);
		}
		
		if(i == n-1){
			if(b[i]<m[j]){
				mem.put(key,b[i]);
				return b[i];
			}
			else{
				mem.put(key,m[j]);
				return m[j];
			}
		}
		int a = 0;
		if(b[i]<m[j]){
			a += b[i];
		}
		else{
			a += m[j];
		}
		a += memoized(i+1,j+1);
		int b = memoized(i+1,0);

		if(a>b){
			mem.put(key,a);
			return a;
		}
		else{
			mem.put(key,b);
			return b;
		}
		
	}

	public static int iterative(int i, int j){
		String key = ("i-"+Integer.toString(i) + "j-" + Integer.toString(j));
		

		if(!(mem.get(key) == null)){
			return mem.get(key);
		}
		
		if(i == n-1){
			if(b[i]<m[j]){
				mem.put(key,b[i]);
				return b[i];
			}
			else{
				mem.put(key,m[j]);
				return m[j];
			}
		}
		int a = 0;
		if(b[i]<m[j]){
			a += b[i];
		}
		else{
			a += m[j];
		}
		a += mem.get("i-"+(i+1)+"j-"+(j+1));
		int b = mem.get("i-"+(i+1)+"j-0");

		if(a>b){
			mem.put(key,a);
			return a;
		}
		else{
			mem.put(key,b);
			return b;
		}

	}




	
}