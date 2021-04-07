package org.amalitech.javarecap;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;

public class Java_FindOpenShortestPath {

	public static void main(String[] args) {

		//{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		String [] arr = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
		
		Point startPt = new Point();
		
		startPt.x = 1;
		startPt.y = 0;
		
		visit(arr, startPt);
		
	}
	
	public static void visit(String []map , Point start){
        int []x = {0,0,1,-1};//This represent 4 directions right, left, down , up
        int []y = {1,-1,0,0};
        LinkedList<Point> pointLinkedList = new LinkedList();
        pointLinkedList.add(start);
        
        System.out.println("pointLinkedList : "+pointLinkedList.toString());
        
        int mapLnf = map.length;
        int m = map[0].length();
        
        int[][]dist = new int[mapLnf][m];
        
        for(int []a : dist){
            Arrays.fill(a,-1);
        }
        
        dist[start.x][start.y] = 0;
        
        while(!pointLinkedList.isEmpty()){
            Point p = pointLinkedList.removeFirst();
            System.out.println("pointLinkedList . removeFirst() : "+pointLinkedList.toString());
            for(int i = 0; i < 4; i++){
                int a = p.x + x[i];
                int b = p.y + y[i];
                if(a >= 0 
            		&& b >= 0 
            		&& a < mapLnf 
            		&& b < m 
            		&& dist[a][b] == -1 
            		&& map[a].charAt(b) != '*' 
                ){
                    dist[a][b] = 1 + dist[p.x][p.y];
                    pointLinkedList.add(new Point(a,b));
                    System.out.println("pointLinkedList . add(...) : "+pointLinkedList.toString());
                }
            }
            
        }
        System.out.println("pointLinkedList -> end of while : "+pointLinkedList.toString());
    }

}
