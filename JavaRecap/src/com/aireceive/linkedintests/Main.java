package com.aireceive.linkedintests;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

class Main {
    /*public static void main(String[] args){
       String message = "Hello";
       for (int i = 0; i<message.length(); i++){
          System.out.print(message.charAt(i+1));
       }
    }*/
    //Results
    /*
     elloException in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: 5
	at java.lang.String.charAt(String.java:658)
	at com.aireceive.linkedintests.Main.main(Main.java:7) 
     */
    
    
	
	
    /*public static void main(String[] args){
        int a = 123451234512345;
        System.out.println(a);
    }*/
    //Results 
    /*Would not compile*/
    
	
	
    /*public static void main(String [] args) {
    	System.out.println("falSe a parse-able boolean ? "+ String.valueOf(Boolean.parseBoolean("falSe")==false) );
    }*/
    //Results
    /*falSe a parse-able boolean ? true*/
    
    
    
    /*static int i = 1;
	public static void main(String args[]){
		
		System.out.println(i+",");
		m(i);
		System.out.println(i);
		
	}
	
	public static void m(int i){i += 2;}//clearly understood now. let i here be ix, when we pass
	//in any value to method "m(...)", it is only ix that would get changed and nothing more.
	*/
    //Results
	/*1,1*/
	
	
    //Results
	/*1,1*/
	
	
	/*public static void main(String [] args) {
		//System.out.println(-4 + 1/2 + 2*-3 + 5.0); //Results -5.0
		System.out.print("Hello,\nworld!");
	}*/
	//Results
	/*
	 Hello,
	 world!
	 */
	
	/*public static void main(String [] args) {
		Object result = LocalDate
				.of(2030,  Month.SEPTEMBER, 21)
				.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
				.plusDays(7)
				.minusWeeks(4).getDayOfWeek();
		System.out.print(result);
	}*///Results /*FRIDAY*/
    
}
