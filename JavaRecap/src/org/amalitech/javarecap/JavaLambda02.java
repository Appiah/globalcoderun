package org.amalitech.javarecap;

public class JavaLambda02 {

   public static void main(String args[]) {
      JavaLambda02 javaLambda02 = new JavaLambda02();
		
      //with type declaration
      MathOperation add = (int a, int b) -> a + b;
		
      //with out type declaration
      MathOperation subtract = (a, b) -> a - b;
		
      //with return statement along with curly braces
      MathOperation multiply = (int a, int b) -> { return a * b; };
		
      //without return statement and without curly braces
      MathOperation divide = (int a, int b) -> a / b;
		
      System.out.println("10 + 5 = " + javaLambda02.operate(10, 5, add));
      System.out.println("10 - 5 = " + javaLambda02.operate(10, 5, subtract));
      System.out.println("10 x 5 = " + javaLambda02.operate(10, 5, multiply));
      System.out.println("10 / 5 = " + javaLambda02.operate(10, 5, divide));
		
      //without parenthesis
      GreetingService greetService1 = message ->
      System.out.println("Hello " + message);
		
      //with parenthesis
      GreetingService greetService2 = (message) ->
      System.out.println("Hello " + message);
		
      greetService1.sayMessage("Jane");
      greetService2.sayMessage("Smith");
   }
	
   interface MathOperation {
      int operation(int a, int b);
   }
	
   interface GreetingService {
      void sayMessage(String message);
   }
	
   private int operate(int a, int b, MathOperation mathOperation) {
      return mathOperation.operation(a, b);
   }
}