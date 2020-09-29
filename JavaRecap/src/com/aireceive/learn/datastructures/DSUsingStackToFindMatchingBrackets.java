package com.aireceive.learn.datastructures;

import java.util.Scanner;
import java.util.Stack;

public class DSUsingStackToFindMatchingBrackets {

	public static void main(String [] arg) {
		
		Scanner kybd = new Scanner(System.in);
		
		String text_str = "(()) {} ([]) {}";
		
		bracketsMatch(text_str);
		
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
	
}
