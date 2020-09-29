package org.amalitech.javarecap;

import java.util.Scanner;

public class JosWheel {

	public static void main(String[] args) {
		
		//Initialisations 
		boolean program_running_bool=false;
		String code_trigger_starter_str="start";
		String code_trigger_ender_str="end";
		
		re_run_kybd_inpt(code_trigger_starter_str);
		
	}

	private static void re_run_kybd_inpt(String code_trigger_starter_str) {
		boolean program_running_bool;
		Scanner kybd_inpt = new Scanner(System.in);
		String kybd_inpt_str = kybd_inpt.nextLine();
		
		if(kybd_inpt_str.equalsIgnoreCase(code_trigger_starter_str)){
		
			program_running_bool=true;
			
			int x=0;
			int y=5;
			
			//1. we need a trigger to start the code
			while(program_running_bool==true){
			//the immediate code above is the same as
			//this very code below
				//while(program_running_bool) {
				if(x<y){
					System.out.println(
						"We are still in the while : "
						//+x
						//the best way to do the above is :
						+Integer.toString(x)
					);
					x = x+1;//can also be written as x++;
				}
				//x = x+1;
				//we needed to move the code above
				//into the if(x<y){...} code block
				else{
					program_running_bool=false;
					System.out.println("We are still in the while loop.");
				}
				
				//2. we need a trigger to end the code
			
			}//END OF while(){...}
			
			System.out.println("Hurrayyy! we are out of the while loop.");
		
		}else {//the user entered the wrong start command
			
			System.out.println("Please restart "
					+ "and enter start to run code"
			);
			re_run_kybd_inpt(code_trigger_starter_str);
		}
		
	}

}
