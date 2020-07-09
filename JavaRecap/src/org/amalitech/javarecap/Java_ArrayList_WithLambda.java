package org.amalitech.javarecap;

import java.util.ArrayList;

public class Java_ArrayList_WithLambda {
  public static void main(String[] args) {
    ArrayList<String> team = new ArrayList<String>();
    team.add("Developers");
    team.add("Sales Team");
    team.add("Marketing Team");
    team.add("Designers");
    //team.forEach( (t) -> { System.out.println(t); } );
    
    for(int x=0; x<team.size(); x++){
    	System.out.println(team.get(x));
    }
    
  }
}

