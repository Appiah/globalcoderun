package com.aireceive.learn.datastructures;

import java.util.*;

public class DSFindMatchingBracketsStacks {

	public static void main(String [] args) {
		
		Scanner kybd = new Scanner(System.in);
		
		String text_str = "(()) {} ([]) {}";
		
		System.out.println( 
				"Does -> "+text_str
				+" have proper matching brackets ? "
				+ bracketsMatch(text_str)
		);
		
		Stack<Integer> stack = new Stack<Integer>(); 
  
        stack_push(stack); 
        stack_pop(stack); 
        stack_push(stack); 
        stack_peek(stack); 
        stack_search(stack, 2);
        stack_search(stack, 6);
		
	}
	
	public static boolean bracketsMatch(String brackets) {
		Stack<Character> stack = new Stack<>();
		
		//Iterate over each bracket
		for(char bracket : brackets.toCharArray()) {
			//Left bracket
			if(isLeftBracket(bracket))
				stack.add(bracket);
			
			//Right bracket
			else if(stack.isEmpty() || stack.pop() != getMatchingBracket(bracket))
				return false;//no matching bracket
		}
		
		//The brackets match if the stack is empty
		return stack.isEmpty();
		
	}
	
	public static boolean isLeftBracket(char ch) {
		return ch == '(' || ch == '{' || ch == '[';
	}
	
	public static char getMatchingBracket(char ch) {
		switch(ch) {
			case '(': return ')';
			case '{': return '}';
			case '[': return ']';
			case ')': return '(';
			case '}': return '{';
			case ']': return '[';
		}
		return '?';
	}
	
	// Pushing element on the top of the stack 
    static void stack_push(Stack<Integer> stack) 
    { 
        for(int i = 0; i < 5; i++) 
        { 
            stack.push(i); 
        } 
    } 
      
    // Popping element from the top of the stack 
    static void stack_pop(Stack<Integer> stack) 
    { 
        System.out.println("Pop Operation:"); 
  
        for(int i = 0; i < 5; i++) 
        { 
            Integer y = (Integer) stack.pop(); 
            System.out.println(y); 
        } 
    } 
  
    // Displaying element on the top of the stack 
    static void stack_peek(Stack<Integer> stack) 
    { 
        Integer element = (Integer) stack.peek(); 
        System.out.println("Element on stack top: " + element); 
    } 
      
    // Searching element in the stack 
    static void stack_search(Stack<Integer> stack, int element) 
    { 
        Integer pos = (Integer) stack.search(element); 
  
        if(pos == -1) 
            System.out.println("Element not found"); 
        else
            System.out.println("Element is found at position: " + pos); 
    } 
	
}
