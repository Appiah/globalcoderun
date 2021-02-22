/**
 * 
 */
package org.amalitech.javarecap;

import java.util.StringTokenizer;

/**
 * @author emap
 *
 */
public class TestingWizard {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test of strings.");
		
		String s = "SPEED1ABaD";
		
		System.out.println(s + " isAlphaOnly : "+ isAlphaOnly(s));

		isAlphaOnlyTokenizer(s);
		
	}
	
	public static boolean isAlphaOnly(String str){
		
		//convert str to array
		char [] sca = str.toCharArray();
		
		//iterate over individual elements in the array
		for(int x=0; x<sca.length; x++ ) {
			//if the element is not an alpha or a letter, return false
			if( !Character.isLetter( sca[x] ) ) {
				return false;
			}
		}
		
		//else by default : return true;
		return true;
	}
	
	public static void isAlphaOnlyTokenizer(String str) {
		StringTokenizer st = new StringTokenizer(str);
		System.out.println(str+" : Tokenized : "+ st.toString());
	}

}
