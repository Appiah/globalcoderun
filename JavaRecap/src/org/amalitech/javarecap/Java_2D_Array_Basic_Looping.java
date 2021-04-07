package org.amalitech.javarecap;

public class Java_2D_Array_Basic_Looping {

	public static void main(String[] args) {
		
		int arr_size_x = 4;
		int arr_size_y = 4;
		
		int [][] arr = new int [arr_size_x][arr_size_y];
		
		int box = 0;
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
		
	}

}
