package org.amalitech.javarecap;

public class Java_2D_PathWithLargestSum {

	public static void main(String[] args) {
		
		int arr_size_x = 4;
		int arr_size_y = 4;
		
		int [][] arr = new int [arr_size_x][arr_size_y];
		
		int box = 1;//load , injection, 
		for(int x=0; x<arr.length; x++){
			for(int y=0; y<arr.length; y++){
				arr[x][y] = box;
				String output = Integer.toString(arr[x][y]);
				
				if( y!=0 && y%(arr_size_y-1)==0 ){
					output = output + "\n";
				}else{
					output = output + ", ";
				}
				System.out.print(output);
				box++;
			}
		}
		
		System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - \n");
		
		for(int x=0; x<arr.length; x++){
			for(int y=0; y<arr.length; y++){
				System.out.println(
					" Key : ["+Integer.toString(x)+", "+Integer.toString(y)+"] =>"
					+" Value : "+Integer.toString(arr[x][y])
				);
			}
		}
		
		System.out.println("\n\n - - - - - - - - - - - - - - - - - - - - - - - \n");
		System.out.println(" - - - - - - - INVERTED KEYING - - - - - - - - ");
		System.out.println("\n - - - - - - - - - - - - - - - - - - - - - - - \n");
		
		for(int x=0; x<arr.length; x++){
			for(int y=0; y<arr.length; y++){
				int m = y;
				int n = Math.abs(x-3);
				int [] givenTarget_x_y = new int [2];
				givenTarget_x_y [0] = m;
				givenTarget_x_y [1] = n;
				int [] true_position = getTruePoint_A_B(m, n);
				System.out.println(
					" Key : [y : "+Integer.toString( m )+", x : "+Integer.toString( n )+"] =>"
					+" Value : "+Integer.toString(arr[x][y])
					+" --> Pos : ["+getTruePoint_A(givenTarget_x_y)+", "+getTruePoint_B(givenTarget_x_y)+"]"
					+" ==> ValidatePos : {"+true_position[0]+","+true_position[1]+"}"
				);
			}
		}
		
	}
	
	
	public static int [] getTruePoint_A_B(int givenTarget_x, int givenTarget_y) {
		
		int [] true_position = new int [2];
		
		//P(x,y) -> arr[a][b]
		
		//b = x
		int b = givenTarget_x;
		
		//a = inverseOf | y - 3 |
		
		//a = -(y - 3), for y > 0;   a = (3 - y), for y > 0
		
		//a = (y + 3), for y < 0; 
		
		int a;
		if(givenTarget_y > 0) {
			a = 3 - givenTarget_y;
		}else {
			a = givenTarget_y + 3;
		}
		
		true_position[0] = a;
		true_position[1] = b;
		
		return true_position;
		
	}
	
	public static int getTruePoint_A(int [] givenTarget_x_y) {
		int [] true_position = getTruePoint_A_B(givenTarget_x_y[0], givenTarget_x_y[1]);
		return true_position[0];
	}
	
	public static int getTruePoint_B(int [] givenTarget_x_y) {
		int [] true_position = getTruePoint_A_B(givenTarget_x_y[0], givenTarget_x_y[1]);
		return true_position[1];
	}
	
	
	
	
}
