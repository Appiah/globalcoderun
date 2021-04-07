package org.amalitech.javarecap;

public class Java_2D_INVERTED_KEYING {

	public static void main(String[] args) {
		
		int arr_size_x = 4;
		int arr_size_y = 4;
		
		int [][] arr = new int [arr_size_x][arr_size_y];
		
		int box = 1;
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
				System.out.println(
					" Key : [y : "+Integer.toString( y )+", x : "+Integer.toString( Math.abs(x-3) )+"] =>"
					+" Value : "+Integer.toString(arr[x][y])
				);
			}
		}
		
		
	}
	
}
