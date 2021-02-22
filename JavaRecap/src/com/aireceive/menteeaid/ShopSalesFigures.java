package com.aireceive.menteeaid;

public class ShopSalesFigures {

	
	
	
	public static void main(String[] args) {
		//System.out.println("testing shopping figures.");
		
		
		/*
		 High Street Branch
		 
		  42000, 48000, 50000
		  52000, 58000, 60000
		  46000, 49000, 58000
		  50000, 51000, 61000
		  
		  
	     Mall Branch
	     
	      57000, 63000, 60000
	      70000, 67000, 73000
	      67000, 65000, 62000
	      72000, 69000, 75000
	      
	     Write a program in Java to calculate the ff : 
	     i. monthly combined sales figures
	     ii. monthly combined quarterly sales figures
	     iii. respective shops annual sales figure
	     iv. grand annual combined total sales figures
		  
		*/
		
		
		double [] hsb = {
			42000, 48000, 50000,
			52000, 58000, 60000,
			46000, 49000, 58000,
			50000, 51000, 61000
		};
		
		double [] mb = {
			57000, 63000, 60000,
	        70000, 67000, 73000,
	        67000, 65000, 62000,
	        72000, 69000, 75000	
		};
		
		
		String [] mo = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		
		
		//Monthly combined sales figures
		double [] combined_sales_fig_mo = new double[12];
		System.out.println("The Monthly Combined Sales Figures of High Street Branch (HSB) & Mall Branch (MB) are;");
		for(int x=0; x<12; x++) {
			combined_sales_fig_mo[x] = hsb[x] + mb[x];
			System.out.println(mo[x]+" -> "+combined_sales_fig_mo[x]);
		}
		
		
		//Monthly combined quarterly sales figures
		double [] hsbQ = new double[4];
		System.out.println("\nQuarterly Sales of HSB are : ");
		
			
				hsbQ[0] = hsb[0]+hsb[1]+hsb[2];
				hsbQ[1] = hsb[3]+hsb[4]+hsb[5];
				hsbQ[2] = hsb[6]+hsb[7]+hsb[8];
				hsbQ[3] = hsb[9]+hsb[10]+hsb[11];
			
		
		for(int i=0; i<4; i++) {
			System.out.println("Q"+Integer.toString(i+1)+" -> "+hsbQ[i]);
		}
		
		
		double [] mbQ = new double[4];
		System.out.println("\nQuarterly Sales MB are : ");
		
			
				mbQ[0] = mb[0]+mb[1]+mb[2];
				mbQ[1] = mb[3]+mb[4]+mb[5];
				mbQ[2] = mb[6]+mb[7]+mb[8];
				mbQ[3] = mb[9]+mb[10]+mb[11];
			
		
		for(int i=0; i<4; i++) {
			System.out.println("Q"+Integer.toString(i+1)+" -> "+mbQ[i]);
		}
		
		
		
		//And now the combined quarterly sales figures are :
		double [] combined_sales_fig_qtr = new double[4];
		System.out.println("\nCombined Quarterly Sales Figures are : ");
		for(int q=0; q<4; q++){
			combined_sales_fig_qtr[q] = hsbQ[q]+mbQ[q];
			System.out.println("Quarter "+Integer.toString(q+1)+" -> "+combined_sales_fig_qtr[q]);
		}
		
		
		//Annual shop sales figures : 
		double hsbT = 0;
		for(int m=0; m<12; m++){
			hsbT += hsb[m];
		}
		System.out.println("\nHigh Street Branch Annual Sales : "+String.valueOf(hsbT));
		
		double mbT = 0;
		for(int m=0; m<12; m++){
			mbT += mb[m];
		}
		System.out.println("\nMall Branch Annual Sales : "+String.valueOf(mbT));
		
		
		//Grand Annual Combined Sales Figure : 
		System.out.println("\nGrand Annual Combined Sales : "+String.valueOf(hsbT+mbT));
		
		
	}

}
