package main

import (
	"fmt"
)

func varz(){//we had an error here earlier when 
	//all we just did was to copy and paste the 
	//code from the other file to this place.
	//the "main" as function was conflicting.
	//Please do not repeat this mistake.
	
	//so the intersting thing about getting this
	//file to run is/was that we basically had to
	//call the function "varz()" inside of helloWorld.go
	//and when ever the go project : "speedLearnsGo" is
	//ran, the code in this file also run perfectly.
	
	fmt.Println("In today's lesson we would be learning about variables")
	
	//How to work with variables in Go
	
	//Objectives :
	//1. Variable declaration
	//2. Redeclaration and shadowing
	//3. Visibility
	//4. Naming Conventions
	//5. Type conversions
	
	//fmt.Println(42)
	
	//the style above is hard coding : not recommended
	
	//declaring a variable : 
	var i int 
	i = 42
	i = 27//i is re-assigned
	
	//We can also delcare a variable as seen below : 
	//var i int = 42
	
	//We can also declare a variable as seen below : 
	//i:=42//faster and simpler way of declaring a variable
	
	//var j int = 27
	var j float32 = 27
	
	//k:=99 //this gives a warning : ie. when not used. Go 
	//makes sure that we are being not just efficient but 
	//also effective in our code and clear as well 
	
	k:=99.
	
	fmt.Println(i)
	
	fmt.Printf("%v, %T", j, j)
	
	fmt.Println("")
	
	fmt.Printf("%v, %T", k, k)
	
}