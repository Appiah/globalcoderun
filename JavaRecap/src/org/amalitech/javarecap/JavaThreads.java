package org.amalitech.javarecap;

public class JavaThreads extends Thread {
  public static void main(String[] args) {
    JavaThreads thread = new JavaThreads();
    thread.start();
    System.out.println("Code runs outside of the thread");//this displays 2nd
  }
  public void run() {
    System.out.println("Code runs inside of the thread");//this displays 1st
  }
}