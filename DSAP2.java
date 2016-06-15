import java.util.*;
import java.io.*;

public class DSAP2{

	static int n, m;
	static int[] u, s;
	static HashMap<String,Integer> mem = new HashMap<String,Integer>();



	public static void main(String args[]){
		//final long startTime = System.currentTimeMillis();

		int max = 0;
		int op_1 = 0;
		int op_2 = 0;
		read(args[1]);

		switch(args[0]){
			case "-r":
				op_1 = recursive(n-1,'s');
				op_2 = recursive(n-1,'u');
				if(op_1>=op_2){
					max = op_1;
				}
				else{
					max = op_2;
				}
				break;
			case "-m":
				op_1 = memoized(n-1,'s');
				op_2 = memoized(n-1,'u');
				if(op_1>=op_2){
					max = op_1;
				}
				else{
					max = op_2;
				}
				break;
			case "-i":
				for(int i=0; i<n; i++){
					op_1 = iterative(i,'u');
					op_2 = iterative(i,'s');
					
				}
				if(op_1>=op_2){
					max = op_1;
				}
				else{
					max = op_2;
				}
				break;
		}
				
		System.out.println(max);
		//final long endTime = System.currentTimeMillis();
		//System.out.println("Total execution time: " + (endTime - startTime) );

	} 


	public static void read(String input){

		File file = new File(input);
		try{
			
			Scanner in = new Scanner(file);
			n = Integer.parseInt(in.next());
			m = Integer.parseInt(in.next());

			int[] us = new int[n];
			int[] ss = new int[n];

			for(int i =0; i<n; i++){
				us[i] = Integer.parseInt(in.next());
			}
			u = us;
			for(int i =0; i<n; i++){
				ss[i] = Integer.parseInt(in.next());
			}
			s = ss;

		}catch(Exception e){
			System.out.println("\nNo File");}

	}

	public static int recursive(int i, char x){

		
		if(x == 's'){
			if(i == 0){
				return s[0];
			}
			int a = recursive(i-1,'u')+s[i]-m;
			int b = recursive(i-1,'s')+s[i];
			if(a>=b){
				return a;
			}
			else{
				return b;
			}
		}

		else if(x == 'u'){
			if(i == 0){
				return u[0];
			}
			int c = recursive(i-1,'s')+u[i]-m;
			int d = recursive(i-1,'u')+u[i];
			if(c>=d){
				return c;
			}
			else{
				return d;
			}
		}
		
		return 0;
	}


	public static int memoized(int i, char x){
		String key = (Integer.toString(i) + '-' + x);
		

		if(!(mem.get(key) == null)){
			return mem.get(key);
		}
		
		if(x == 's'){
			if(i == 0){
				mem.put(key,s[0]);
				return s[0];
			}
			int a = memoized(i-1,'u')+s[i]-m;
			int b = memoized(i-1,'s')+s[i];
			if(a>=b){
				mem.put(key,a);
				return a;
			}
			else{
				mem.put(key,b);
				return b;
			}
		}

		else if(x == 'u'){
			if(i == 0){
				mem.put(key,u[0]);
				return u[0];
			}
			int c = memoized(i-1,'s')+u[i]-m;
			int d = memoized(i-1,'u')+u[i];
			if(c>=d){
				mem.put(key,c);
				return c;
			}
			else{
				mem.put(key,d);
				return d;
			}
		}
		
		return 0;
	}

	public static int iterative(int i, char x){
		String key = (Integer.toString(i) + '-' + x);
		String key_p = (Integer.toString(i-1) + '-' + 'u');
		String key_q = (Integer.toString(i-1) + '-' + 's');
		
		if(x == 's'){
			if(i == 0){
				mem.put(key,s[0]);
				return s[0];
			}
			
			int a = mem.get(key_p)+s[i]-m;
			int b = mem.get(key_q)+s[i];
			if(a>=b){
				mem.put(key,a);
				return a;
			}
			else{
				mem.put(key,b);
				return b;
			}
		}

		else if(x == 'u'){
			if(i == 0){
				mem.put(key,u[0]);
				return u[0];
			}
			int c = mem.get(key_q)+u[i]-m;
			int d = mem.get(key_p)+u[i];
			if(c>=d){
				mem.put(key,c);
				return c;
			}
			else{
				mem.put(key,d);
				return d;
			}
		}
		
		return 0;
	}


	


	
}