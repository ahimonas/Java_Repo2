/*Blueprint for RandomOccupant
 * CPE 102 Project 3
 * Ebrahim Feghhi
 * */

import java.util.*;

public abstract class RandomOccupant extends Occupant {
  
  private Random r; 
  private Maze maze;
  
  public RandomOccupant(Maze maze) {
    r = new Random();
    int ro = r.nextInt(maze.rows()); 
    int co = r.nextInt(maze.cols()); 
    Square newsquare = maze.getSquare(ro,co);
    //super(newsquare); 
    super.moveTo(newsquare);
    this.maze = maze;  
    
    
  }
  
  public RandomOccupant (Maze maze, long seed) {
    r = new Random(seed);
    int ro = r.nextInt(maze.rows()); 
    int co = r.nextInt(maze.cols()); 
    Square newsquare = maze.getSquare(ro,co);
    //super(newsquare); 
    super.moveTo(newsquare);
    this.maze = maze;   
    //this.seed = seed;
    
  }
  
  public RandomOccupant (Maze maze, Square location) {
    
    super(location); 
    r = new Random();
    //super(newsquare)
    this.maze = maze; 
    
  }
  
  //moves occupant randomly by generating random integer 
  
  public void move() {
       
     Square loc = super.location();
     int ro = loc.row();
     int co = loc.col();
     int direction;
     direction = r.nextInt(4);
     
     
     while (loc.wall(direction)) {
       direction = r.nextInt(4);
    }
       
     
     if (!loc.wall(direction)) 
     {
       if (direction == Square.UP) ro = ro - 1; 
       
       if (direction == Square.DOWN) ro = ro + 1; 
       
       if (direction == Square.LEFT) co = co - 1; 
       
       if (direction == Square.RIGHT) co = co + 1;
       
     }
     

     Square newlocation2 = maze.getSquare(ro,co);
     super.moveTo(newlocation2);
     
  }
  