package org.amalitech.javarecap;

//shamelessly learnt from geeksforgeeks

public class JavaLambda01 {
	
    // operation is implemented using lambda expressions 
    interface Interface_x{ 
        int operation(int a, int b); 
    } 
  
    // sayMessage() is implemented using lambda expressions 
    // above 
    interface Interface_y{ 
        void sayMessage(String message); 
    } 
  
    // Performs Interface_x's operation on 'a' and 'b' 
    private int operate(int a, int b, Interface_x interface_x){ 
        return interface_x.operation(a, b); 
    } 
  
    public static void main(String args[]){ 
        // lambda expression for addition for two parameters 
        // data type for x and y is optional. 
        // This expression implements 'Interface_x' interface 
        Interface_x add = (int x, int y) -> x + y; 
  
        // lambda expression multiplication for two parameters 
        // This expression also implements 'Interface_x' interface 
        Interface_x multiply = (int x, int y) -> x * y; 
  
        // Creating an object of Test to call operate using 
        // different implementations using lambda Expressions 
        JavaLambda01 javaLambda01 = new JavaLambda01(); 
  
        // Add two numbers using lambda expression 
        System.out.println("Addition is " + 
                          javaLambda01.operate(6, 3, add)); 
  
        // Multiply two numbers using lambda expression 
        System.out.println("Multiplication is " + 
                          javaLambda01.operate(6, 3, multiply)); 
  
        // lambda expression for single parameter 
        // This expression implements 'Interface_y' interface 
        Interface_y interface_y = message ->System.out.println("Hello " + message); 
        interface_y.sayMessage("Geek"); 
        
    } 
} 