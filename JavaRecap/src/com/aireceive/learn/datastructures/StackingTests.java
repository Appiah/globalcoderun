package com.aireceive.learn.datastructures;

import java.util.Stack;

public class StackingTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start Stack running in test mode ...");
    
	    Stack<Character> s = new Stack<Character>();
	    
	    s.push(new Character('A'));
	    s.push(new Character('B'));
	    s.push(new Character('C'));
	    s.pop();
	    s.pop();
	    s.push(new Character('C'));
	    s.push(new Character('C'));
	    s.push(new Character('C'));
	    s.pop();
	    s.push(new Character('C'));
	    s.pop();
	    s.pop();
	    s.pop();
	    
	    System.out.print("Final stack (s) : "+s.toString());
	}

}
