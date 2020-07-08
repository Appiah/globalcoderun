package org.amalitech.javarecap;

public class JavaThreads extends Thread {
  public static void main(String[] args) {
    JavaThreads thread = new JavaThreads();
    thread.start();
    System.out.println("This code is outside of the thread");
  }
  public void run() {
    System.out.println("This code is running in a thread");
  }
}