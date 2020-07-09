package org.amalitech.javarecap;

public class JavaLambda02 {

   public static void main(String args[]) {
      JavaLambda02 javaLambda02 = new JavaLambda02();
		
      //with type declaration
      InterfaceMathOperation add = (int a, int b) -> a + b;
		
      //with out type declaration
      InterfaceMathOperation subtract = (a, b) -> a - b;
		
      //with return statement along with curly braces
      InterfaceMathOperation multiply = (int a, int b) -> { return a * b; };
		
      //without return statement and without curly braces
      InterfaceMathOperation divide = (int a, int b) -> a / b;
		
      System.out.println("10 + 5 = " + javaLambda02.operate(10, 5, add));
      System.out.println("10 - 5 = " + javaLambda02.operate(10, 5, subtract));
      System.out.println("10 x 5 = " + javaLambda02.operate(10, 5, multiply));
      System.out.println("10 / 5 = " + javaLambda02.operate(10, 5, divide));
		
      //without parenthesis
      MessagingService msgSrvc0 = message -> System.out.println("Hello " + message);
		
      //with parenthesis
      MessagingService msgSrvc1 = (message) -> System.out.println("Hello " + message);
		
      msgSrvc0.dsplyMsg("Jane");
      msgSrvc1.dsplyMsg("Smith");
   }
	
   interface InterfaceMathOperation {
      int operation(int a, int b);
   }
	
   interface MessagingService {
      void dsplyMsg(String message);
   }
	
   private int operate(int a, int b, InterfaceMathOperation mathOperation) {
      return mathOperation.operation(a, b);
   }
}