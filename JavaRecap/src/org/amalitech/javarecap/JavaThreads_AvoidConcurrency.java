package org.amalitech.javarecap;

public class JavaThreads_AvoidConcurrency extends Thread {
  public static int fig = 0;

  public static void main(String[] args) {
    
	  JavaThreads_AvoidConcurrency thread = new JavaThreads_AvoidConcurrency();
    
	  thread.start();
	  
	    // Wait for the thread to finish
	    while(thread.isAlive()) {
	    	System.out.println("While still running Wait...");
	    	fig++;
	    	System.out.println("Current value of Total is : "+fig);
	    	
	    }
	    
		  // Update figure and print its value
		  System.out.println("Total(1) : " + fig);
	  	
		  fig++;
	  	  
		  System.out.println("Total(2) : " + fig);
	  }
	  public void run() {
		  System.out.println("Total(3) within run() : " + fig);
	    fig++;
	  }
}