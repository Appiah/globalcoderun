package org.amalitech.javarecap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Java_2D_path_finding {

	public static void main(String[] args) {
		
		int [] trueStartPosition = new int[2];
		int [] trueTargetPosition = new int[2];
		
		//set start position
		trueStartPosition[0] = 3;
		trueStartPosition[1] = 0;
		
		//set destination position
		trueTargetPosition[0] = 0;
		trueTargetPosition[1] = 3;
		
		//trueStartPosition.equals(trueTargetPosition) they are different even with the same child values
		//trueStartPosition == trueTargetPosition they are different even with the same child values
		
		int arr_size_x = 4;
        int arr_size_y = 4;
		int map_x [][] = new int [arr_size_x][arr_size_y];
		int box = 1;
		for(int x=0; x<map_x.length; x++){
			for(int y=0; y<map_x.length; y++){
				map_x[x][y] = box;
				box++;
			}
		}
		
		if( Arrays.equals(trueStartPosition, trueTargetPosition) | movedNowhere(trueStartPosition, trueTargetPosition)) {
			System.out.println("Travelling is not needed. Start == Target");
		}else{
			//testMovements(trueStartPosition, trueTargetPosition);
			//move_from_start_to_target(trueStartPosition, trueTargetPosition);
			int totalPathSum = move_from_start_to_target(map_x, trueStartPosition, trueTargetPosition);
			
			System.out.println("Travelling from : "+Arrays.toString(trueStartPosition)+ ", to : "+Arrays.toString(trueTargetPosition));
			System.out.println("Path travelled : "+Arrays.deepToString(path.toArray()) );
			System.out.println("Total Path Value : "+Integer.toString(totalPathSum));
			
		}
	}
	
	static List<int []> path;
	public static int move_from_start_to_target(int [][] map_x, int [] trueStartPosition, int [] trueTargetPosition){

        int [] from_here = trueStartPosition;
        int [] to_there = trueTargetPosition;
        
        int rows = map_x.length;
        
        if(rows!=0){
        	
        	int cols = map_x[0].length;
        
	        //get the x and y values of from_here
	        int x = from_here[0];
	        int y = from_here[1];
	        
	        //get the quantitative value at x and y
	        int q_val = map_x[x][y];
	        
	        //note that, the starting point is actually the values inside of from_here, ie. x and y
	        
	        //Start a list to store the selected paths
	        path = new ArrayList<int []>();
	        
	        //add the source array, ie. the from_here array
	        path.add(from_here);
	        
	        System.out.println("path X : "+Arrays.deepToString(path.toArray()));
	        
	        if(Arrays.equals(from_here, to_there)) {
	        	return q_val;
	        }else {
	        	//int q_val_right_or_up = q_val;
	        	return move_addPath_n_sumVal(map_x, from_here, to_there, rows, cols, q_val/*, q_val_right_or_up*/);
	        }
	        //Get paths travelled
	        
	        
	        //Get the total value along travelled path
	        
	        
        }else {
        	return 0;
        }
		
	}
	
	private static int move_addPath_n_sumVal(int[][] map_x, int[] from_here, int[] to_there, int rows, int cols, int q_val/*, int q_val_right_or_up*/) {
		
		boolean arrived=false;
		while(!arrived){
		
			int [] x_y_up = {from_here[0]-1, from_here[1]};
			int q_val_up = 0; 
			//q_val_up += q_val_right_or_up;
				
			
			int [] x_y_right = {from_here[0], from_here[1]+1};
			int q_val_right = 0;
			//q_val_right += q_val_right_or_up;
			
			
			boolean rTM_R = okToMoveBool(x_y_right, rows, cols);
			boolean rTM_Up = okToMoveBool(x_y_up, rows, cols);
			
			
			if(rTM_Up) {
				q_val_up = map_x[x_y_up[0]][ x_y_up[1]];
				System.out.println("ok to move up");
			}
			
			if(rTM_R) {
				q_val_right = map_x[x_y_right[0]][x_y_right[1]];
				System.out.println("ok to move right");
			}
			
				
			if(rTM_Up | rTM_R) {
				
			
				if(q_val_up > q_val_right){//continue towards up
					
					System.out.println("move ^ Up");
					
					//store this path
					path.add(x_y_up);
					System.out.println("path Up : "+Arrays.deepToString(path.toArray()));
					
					q_val += q_val_up;
					
					//check if we have arrived at our destination
					if(Arrays.equals(x_y_up, to_there)) {//we have arrived stop all processes
						arrived = true;
						return q_val;
					}else{//use x_y_up as the next source to move from
						return move_addPath_n_sumVal(map_x, x_y_up, to_there, rows, cols, q_val);
					}
					
				}else{//continue towards right
					
					if(q_val_up < q_val_right){//continue towards up
					
						System.out.println("move > Right");
						
						//store this path
						path.add(x_y_right);
						System.out.println("path R : "+Arrays.deepToString(path.toArray()));
						
						q_val += q_val_right;
						
						//use x_y_right as the next source to move from
						//check if we have arrived at our destination
						if(Arrays.equals(x_y_right, to_there)) {//we have arrived stop all processes
							arrived = true;
							return q_val;
						}else{//use x_y_right as the next source to move from
							return move_addPath_n_sumVal(map_x, x_y_right, to_there, rows, cols, q_val);
						}
					
					}else{//they are equal : what next ?
						
						if(rTM_R) {
							//store this path
							path.add(x_y_right);
							System.out.println("path R| : "+Arrays.deepToString(path.toArray()));
							
							q_val += q_val_right;
							
							//use x_y_right as the next source to move from
							//check if we have arrived at our destination
							if(Arrays.equals(x_y_right, to_there)) {//we have arrived stop all processes
								arrived = true;
								return q_val;
							}else{//use x_y_right as the next source to move from
								return move_addPath_n_sumVal(map_x, x_y_right, to_there, rows, cols, q_val);
							}
						}
						
						if(rTM_Up) {
							//store this path
							path.add(x_y_up);
							System.out.println("path Up| : "+Arrays.deepToString(path.toArray()));
							
							q_val += q_val_up;
							
							//use x_y_right as the next source to move from
							//check if we have arrived at our destination
							if(Arrays.equals(x_y_up, to_there)) {//we have arrived stop all processes
								arrived = true;
								return q_val;
							}else{//use x_y_right as the next source to move from
								return move_addPath_n_sumVal(map_x, x_y_up, to_there, rows, cols, q_val);
							}
						}
						
					}
						
				}
				
			}else {
				return q_val;
			}
		
		}//END OF while
		return q_val;
	}

	public static boolean okToMoveBool (int [] xy_arr, int rows, int cols) {
		return xy_arr[0] >= 0 
			&& xy_arr[1] >= 0 
			&& xy_arr[0] < cols 
			&& xy_arr[1] < rows;
	}
	
/*	private static int move_addPath_n_sumVal(int[][] map_x, int[] from_here, int[] to_there, int rows, int cols, int x, int y, int q_val) {
		int q_val_SUM;
		boolean arrived=false;
		while(arrived){
		try {
			//up
			int [] x_y_up = move_up(from_here, cols-x, rows-y, false);
			int q_val_up = map_x[x_y_up[0]][ x_y_up[1]];
		
			//right
			int [] x_y_right = move_right(from_here, cols-x, rows-y, false);
			int q_val_right = map_x[x_y_right[0]][x_y_right[1]];
			
			//find the max q_val and use that to decide on the next path
			//int q_val_max_0 = Math.max(q_val_up, q_val_right);
			
			
			if(q_val_up > q_val_right){//continue towards up
				
				//store this path
				path.add(x_y_up);
				
				q_val_SUM = q_val + q_val_up;
				
				//check if we have arrived at our destination
				if(Arrays.equals(x_y_up, to_there)) {//we have arrived stop all processes
					arrived = true;
					return q_val_SUM;
				}else{//use x_y_up as the next source to move from
					return move_addPath_n_sumVal(map_x, x_y_up, to_there, rows, cols, x_y_up[0], x_y_up[1], q_val_SUM);
				}
				
			}else{//continue towards right
				
				if(q_val_up < q_val_right) {//continue towards up
				
					//store this path
					path.add(x_y_right);
					
					q_val_SUM = q_val + q_val_right;
					
					//use x_y_right as the next source to move from
					//check if we have arrived at our destination
					if(Arrays.equals(x_y_right, to_there)) {//we have arrived stop all processes
						arrived = true;
						return q_val_SUM;
					}else{//use x_y_right as the next source to move from
						return move_addPath_n_sumVal(map_x, x_y_up, to_there, rows, cols, x_y_up[0], x_y_up[1], q_val_SUM);
					}
				
				}else{//they are equal : what next ?
					
					//store this path
					path.add(x_y_right);
					
					q_val_SUM = q_val + q_val_right;
					
					//use x_y_right as the next source to move from
					//check if we have arrived at our destination
					if(Arrays.equals(x_y_right, to_there)) {//we have arrived stop all processes
						arrived = true;
						return q_val_SUM;
					}else{//use x_y_right as the next source to move from
						return move_addPath_n_sumVal(map_x, x_y_up, to_there, rows, cols, x_y_up[0], x_y_up[1], q_val_SUM);
					}
					
				}
					
			}
			
		} catch (Exception e) {
			return 0;
			//e.printStackTrace();
		} 
		}//END OF while
	}
*/	
	
	
	
	
	public static void move_from_start_to_target(int [] trueStartPosition, int [] trueTargetPosition){

        int arr_size_x = 4;
        int arr_size_y = 4;

        int [] from_here = trueStartPosition;
        int [] to_there = trueTargetPosition;

        try {
        	//move_right(trueStartPosition, arr_size_x, arr_size_y, false);
        	int totalBoxMoves = 0;
        	while(!Arrays.equals(from_here, trueTargetPosition)) {
        		System.out.println("Searching R ... ");
        		from_here = move_right(trueStartPosition, arr_size_x, arr_size_y, false);
        		totalBoxMoves++;
        	
        		System.out.println("Searching L ... ");
        		from_here = move_left(trueStartPosition, arr_size_x, arr_size_y, false);
        		totalBoxMoves++;
        	}
        	System.out.println("We have arrived. Total Moves : "+totalBoxMoves);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public static void testMovements(int [] trueStartPosition, int [] trueTargetPosition){

        int arr_size_x = 4;
        int arr_size_y = 4;

        int [][] arr = new int [arr_size_x][arr_size_y];

        int totalNumOfRightMovements = 0;

        int a = trueStartPosition[0];
        int b = trueStartPosition[1];

        int c = trueTargetPosition[0];
        int d = trueTargetPosition[1];

        System.out.println("Movement started : ");

        boolean destination_reached = false;

        try {
        
	        //move up : a-1, b
	        System.out.println("Moving from : "+Arrays.toString(trueStartPosition)+","
	        		+ " to Right : "+Arrays.toString(move_right(trueStartPosition, arr_size_x, arr_size_y, false)));
			
	        
	        //move right : a, b+1
	        
	        
	        //move down : a+1, b
	        
	        
	        //move left : a, b-1
	        
	        
	        //move right diagonal up : (move_right) then (move_up)  OR  (move_up) then (move_right)
	        	// (a, b+1) then (a-1, b) OR (a-1, b) then (a, b+1)
	        System.out.println("Moving from : "+Arrays.toString(trueStartPosition)+", "
	        		+ "to Right Diagonal Up : "+Arrays.toString(move_right_diagonal_up(trueStartPosition, arr_size_x, arr_size_y, false)));
	        
	        System.out.println("Moving from : "+Arrays.toString(trueStartPosition)+", "
	        		+ "to Right Diagonal Up : "+Arrays.toString(move_right_diagonal_up(trueStartPosition, arr_size_x, arr_size_y, false)));
	        
	        
	        
	        //move right diagonal down : (move_right) then (move_down) OR (move_down) then (move_right)
	        
	        
	        //move left diagonal down : (move_down) then (move_left) OR (move_left) then (move_down)
	        
	        
	        //move left diagonal up : (move_left) then (move_up) OR (move_up) then (move_left)
	        
	        
	        
	        
	
	        //int box = 1;//load , injection,
	        /*for(int x=0; x<arr.length; x++){
	            for(int y=0; y<arr.length; y++){
	            }
	        }*/
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public static String haltPrefixMsg() {
		return "**Program halted. ==> ";
	}
	
	public static String warningMsg(Directions dxn) {
		return "*Sorry cannot move "+dxn.toString()+"; a & b limits apply.";
	}
	
	private static int[] nextTrueStartPosition(int[] trueStartPosition, Directions dxn, int a_limit, int b_limit, boolean hault_app_on_limit_cap, int a, int b) throws Exception {
		if(a >= 0 && b >=0 && a < a_limit && b < b_limit){
    		trueStartPosition[0] = a;
    		trueStartPosition[1] = b;
    	}else{
    		if(hault_app_on_limit_cap) {
    			throw new Exception(haltPrefixMsg() + warningMsg(dxn));
    		}else {
    			System.out.println(warningMsg(dxn));
    		}
    	}
    	return trueStartPosition;
	}
	
	//move up : a-1, b
    public static int [] move_up(int [] trueStartPosition, int a_limit, int b_limit, boolean hault_app_on_limit_cap) throws Exception {
    	int a = trueStartPosition[0];
    	int b = trueStartPosition[1];
    	
    	a -= 1;
    	//b 
    	
    	return nextTrueStartPosition(trueStartPosition, Directions.UP, a_limit, b_limit, hault_app_on_limit_cap, a, b);
    }
        
    //move right : a, b+1
    public static int [] move_right(int [] trueStartPosition, int a_limit, int b_limit, boolean hault_app_on_limit_cap) throws Exception {
    	int a = trueStartPosition[0];
    	int b = trueStartPosition[1];
    	
    	//a
    	b += 1; 
    	
    	return nextTrueStartPosition(trueStartPosition, Directions.RIGHT, a_limit, b_limit, hault_app_on_limit_cap, a, b);
    }

	
    
    //move down : a+1, b
    public static int [] move_down(int [] trueStartPosition, int a_limit, int b_limit, boolean hault_app_on_limit_cap) throws Exception {
    	int a = trueStartPosition[0];
    	int b = trueStartPosition[1];
    	
    	a += 1; 
    	//b
    	
    	return nextTrueStartPosition(trueStartPosition, Directions.DOWN, a_limit, b_limit, hault_app_on_limit_cap, a, b);
    }
    
    //move left : a, b-1
    public static int [] move_left(int [] trueStartPosition, int a_limit, int b_limit, boolean hault_app_on_limit_cap) throws Exception {
    	int a = trueStartPosition[0];
    	int b = trueStartPosition[1];
    	
    	//a
    	b -= 1;
    	
    	return nextTrueStartPosition(trueStartPosition, Directions.LEFT, a_limit, b_limit, hault_app_on_limit_cap, a, b);
    }
    
    //move right diagonal up : (move_right) then (move_up)  OR  (move_up) then (move_right)
    	// (a, b+1) then (a-1, b) OR (a-1, b) then (a, b+1)
    public static int [] move_right_diagonal_up(int [] trueStartPosition, int a_limit, int b_limit, boolean hault_app_on_limit_cap) throws Exception {
    	move_right(trueStartPosition, a_limit, b_limit, hault_app_on_limit_cap);
    	move_up(trueStartPosition, a_limit, b_limit, hault_app_on_limit_cap);
    	return trueStartPosition;
    }
    
    //move right diagonal down : (move_right) then (move_down) OR (move_down) then (move_right)
    
    
    //move left diagonal down : (move_down) then (move_left) OR (move_left) then (move_down)
    
    
    //move left diagonal up : (move_left) then (move_up) OR (move_up) then (move_left)
	
	
	public static boolean movedNowhere(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0] == 0
                &&  trueStartPosition[1] - trueTargetPosition[1] == 0
        );
    }

    public static boolean movedUp(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0]== 1
                && trueStartPosition[1] - trueTargetPosition[1] == 0
        );
    }

    public static boolean movedRight(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0] == 0
                && trueStartPosition[1] - trueTargetPosition[1] == -1
        );
    }

    public static boolean movedDown(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0]== -1
                && trueStartPosition[1] - trueTargetPosition[1] == 0
        );
    }

    public static boolean movedLeft(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0] == 0
                && trueStartPosition[1] - trueTargetPosition[1] == 1
        );
    }

    public static boolean movedRight_DiagonalUp(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0] == 1
                &&  trueStartPosition[1] - trueTargetPosition[1] == -1
        );
    }

    public static boolean movedRight_DiagonalDown(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0] == -1
                && trueStartPosition[1] - trueTargetPosition[1] == -1
        );
    }

    public static boolean movedLeft_DiagonalUp(int [] trueStartPosition, int [] trueTargetPosition){
        return( trueStartPosition[0] - trueTargetPosition[0] == 1
                && trueStartPosition[1] - trueTargetPosition[1] == 1
        );
    }

    public static boolean movedLeft_DiagonalDown(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0] == -1
                && trueStartPosition[1] - trueTargetPosition[1] == 1
        );
    }
    
    public static enum Directions{
    	UP,
    	RIGHT,
    	DOWN,
    	LEFT,
    	RIGHT_DIAGONAL_UP,
    	RIGHT_DIAGONAL_DOWN,
    	LEFT_DIAGONAL_UP,
    	LEFT_DIAGONAL_DOWN
    }
	

}
