/*
Creates blueprint for Square
Ebrahim Feghhi 
CPE 102 Project 3
*/


public abstract class Occupant {
  private Square location; 
  
  public Occupant () {
    
  }
  
  //constructor sets location to start
  public Occupant(Square start) {
    
    
    location = start; 
    
  }
  
  //returns location
  //@returns location of type Square
  
  public Square location() {
    
    
    return location;
  
    
  }
  
  //moves squares
  //@param newlocation for Square
  public void moveTo (Square newLoc) {
    
    location = newLoc;
    
  }
  