package org.amalitech.javarecap;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Java_2D_Array_FindShortestPath {

	public static void main(String[] args) {
		
		char [] series_of_nodes = {
			 'A', 'A', 'A', 'B', 'A',  
			 'B', 'B', 'B', 'B', 'B',
	         'A', 'B', 'A', 'A', 'A',
	         'A', 'B', 'B', 'B', 'B',
	         'A', 'A', 'A', 'A', 'A' 
		};
		
		System.out.println("series_of_nodes : "+Arrays.toString(series_of_nodes));
		
		char [][] game_map = new char [5][5]; 
		
		/*[ [ 'A', 'A', 'A', 'B', 'A' ],
	        [ 'B', 'B', 'B', 'B', 'B' ],
	        [ 'A', 'B', 'A', 'A', 'A' ],
	        [ 'A', 'B', 'B', 'B', 'B' ],
	        [ 'A', 'A', 'A', 'A', 'A' ] ];
		*/
			
		Set<int []> visited = new HashSet<int []>();
		
		int rows = game_map.length;
		int columns = game_map[0].length;
		
		System.out.println("rows : "+rows);
		System.out.println("columns : "+columns);
		
		int [][] distance = new int [5][5]; //Arrays(rows).fill().map(Arrays(columns).fill(-1));
		for(int f=0; f<5; f++) {
			Arrays.fill(distance[f], -1);
		}
		
		System.out.println("distance : "+Arrays.deepToString(distance));
		
		distance[0][0]=0;
		
		//Queue<int []> Q = new PriorityQueue<int []>(); //Queue
		//PriorityQueue<int []> Q = new PriorityQueue<int []>();
		Queue<int []> Q = new ArrayDeque<int []>();
		
		System.out.println("PriorityQueue Q (before) : "+Arrays.deepToString(Q.toArray()));
		
		//Load the game_map
		int pos =0;
		for(int a=0; a<rows; a++) {
			for(int b=0; b<rows; b++) {
				game_map[a][b] = series_of_nodes[pos];
				pos++;
				
				int [] startPt = new int [2];
				startPt[0]=a;
				startPt[1]=b;
				Q.add(startPt);
				
				//Q.push([0,0]);
				
			}	
		}
		System.out.println("game_map : "+Arrays.deepToString(game_map));
		System.out.println("Queue Q (after) : "+Arrays.deepToString(Q.toArray()));
		
		//visited.add(startPt);
		//visited.add([0,0].toString());
		
		System.out.println("Visited (just before adding thisPt) : "+Arrays.deepToString(visited.toArray()));
		
		int []dr = {-1,1,0,0};
		int []dc = {0,0,-1,1};//This represent 4 directions right, left, down , up
	    
		//let dr = [-1,1,0,0]; 
		//let dc = [0,0,-1,1]; 
		
		while(Q.size() > 0){
			int [] cur = Q.peek();// shift();
			int row = cur[0];
			int col = cur[1];
			
			for(int k=0; k<4; k++){
				int newRow = row + dr[k];
				int newCol = col + dc[k];
				
				int [] thisPt = new int [2];
				thisPt[0] = newRow;
				thisPt[1] = newCol;
				
				if(!visited.contains(thisPt) 
					&& newRow>=0 
					&& newCol >=0 
					&& newRow < rows 
					&& newCol < columns 
					&& game_map[newRow][newCol] != game_map[row][col]
				){
					
					visited.add(thisPt);//[newRow,newCol]);
					distance[newRow][newCol] = distance[row][col] + 1;
					System.out.println("Queue Q (just before adding thisPt) : "+Arrays.deepToString(Q.toArray()));
					//Q.add(thisPt);// .push([newRow,newCol]);
					Q.remove(thisPt);
					System.out.println("Queue Q (just after adding thisPt) : "+Arrays.deepToString(Q.toArray()));
				
				}
			}
		}
		
		if(distance[rows-1][columns-1] == -1) {
			//console.log("Path does not exist");
			System.out.println("Path does not exist");
		} else {
			//console.log(distance[rows-1][columns-1]);
			System.out.println("Distance : "+distance[rows-1][columns-1]);
		}

	}
	
}
