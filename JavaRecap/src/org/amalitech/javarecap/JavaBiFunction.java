package org.amalitech.javarecap;

// Java Program to demonstrate 
// BiFunction's apply() method 
  
import java.util.function.BiFunction; 
  
public class JavaBiFunction { 
    public static void main(String args[]){ 
        // BiFunction to add 2 numbers 
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b; 
  
        // Implement add using apply() 
        System.out.println("Sum a & b"
        		/*Integer.toString(a)+" & "+Integer.toString(b)*/
        		+" = " + add.apply(5, 7)
        ); 
  
        // BiFunction to multiply 2 numbers 
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b; 
  
        // Implement add using apply() 
        System.out.println("Multiply a & b"
        		/*Integer.toString(a)+" & "+Integer.toString(b)*/
        		+" = " + multiply.apply(9, 12)
        ); 
    } 
} 
