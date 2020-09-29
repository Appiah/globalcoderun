package com.aireceive.learn.datastructures;

public class DSBasics {
	
	//modifier type name = initialiser;
	
	//a class, interface, enum : can have the following modifiers : 
	/*
	 
	  abstract
	  public
	  final
	  strictfp : its floating point results would be platform independent
	  
	 */
	
	
	
	//a constructor : can have the following modifiers :
	/*
	 
	  public
	  protected
	  private
	  
	 */
	
	
	
	//a field : can have the following modifiers :
	/*
	 
	  final
	  public
	  protected
	  private
	  static
	  transient : it is not part of the persistent state of an object
	  volatile : it may be modified by an asynchronous thread
	  
	 */
	
	
	
	//a method : can have the following modifiers :
	/*
	 
	  abstract
	  native : its body is implemented in another programming language
	  final
	  public
	  protected
	  private
	  static
	  strictfp
	  synchronized : it may be locked before it can be invoked by a thread
	  volatile
	  
	 */
	
	
	
	//a local variable : can have the following modifiers :
	/*
	 
		final
	  
	 */
	
	public class Ratio implements Comparable {
		
		protected int num, den;
		public static final Ratio ZERO = new Ratio();
		
		private Ratio(){
			this(0, 1);
		}
		
		public Ratio(int x, int y) {
			this.num=x;
			this.den=y;
		}
		
		public boolean equals(Object object) {
			if(object==this) {
				return true;
			}else if(!(object instanceof Ratio)) {
				return false;
			}
			Ratio that = (Ratio)object;
			return (this.num*that.den == that.num*this.den);
		}
		
		public int getNum() {
			return num;
		}
		
		public int getDen() {
			return den;
		}
		
		public String toString() {
			return String.format("%d/%d", num, den);
		}
		
		public double value() {
			return (double)num/den;
		}
		
		public int compareTo(Object object) {
			if(object == this) {
				return 0;
			}else if(!(object instanceof Ratio)) {
				throw new IllegalArgumentException("Ratio type expected");
			}
			Ratio that = (Ratio)object;
			normalize(this);
			normalize(that);
			return(this.num*that.den - that.num*this.den);
		}
		
		private void normalize(Ratio x) {
			if(x.num==0){
				x.den=1;
			}else if(x.den < 0){
				x.num *= -1;
				x.den *= -1;
			}
		}
		
	}

	public static void main (String [] args) {
		
		Ratio x;
		x = Ratio(22, 7);
		System.out.println("x.hasCode() : "+x.hashCode());
		
	}
	
}
