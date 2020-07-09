package org.amalitech.javarecap;

public class JavaLambda {

	public static void main(String[] args) {

		System.out.println("Testing Lambda now in Java 8");
		
		//applying the concept of lambda
		//String x = ("macbook", 2011) -> machineNameAndVersion;
		
	}
	
	public String machineNameAndVersion(String nm, int version_i){
		return "The machine name is "+nm+ ", version : "+Integer.toString(version_i);
	} 

	public static void x_static(String str, JavaInterface format) {
		String rslt=format.x_str(str);
		System.out.println(rslt);
	}

	interface JavaInterface {

		String x_str(String str);
	
	
	}
	
	/*
	 //Notes sharelessly borrowed from w3schools.com
	 
	  Lambda Expressions were added in Java 8.

		A lambda expression is a short block of code which 
		takes in parameters and returns a value. Lambda expressions 
		are similar to methods, but they do not need a name and they
		 can be implemented right in the body of a method.
		 
		 The simplest lambda expression contains a single parameter and an expression:
		 
		 variable -> expression
		 
		   
		 To use more than one parameter, wrap them in parentheses:
		 
		 (var1, var2) -> expression
		 
		 Expressions are limited. They have to immediately return a value,
		  they cannot contain variables, assignments or statements such as
		   if or for. In order to do more complex operations, a code block can be used with curly braces. 
		 If the lambda expression needs to return a value then the code block should have a return statement.
	  
	  
	  

		Lambda expressions are usually passed as parameters to a function:
	  
	  
	  	BENEFITS OF LAMBDA EXPRESSION : 
	  	
	  	
	    * Conciseness
	    * Reduction in code bloat
	    * Readability
	    * Elimination of shadow variables
	    * Encouragement of functional programming
	    * Code reuse
	    * Enhanced iterative syntax
	    * Simplified variable scope
	    * Less boilerplate code
	    * JAR file size reductions
	    * Parallel processing opportunities

	  
	 */
	
}
