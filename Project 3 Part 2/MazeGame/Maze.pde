import java.util.*;
/**
 * Class that contains all the logic to model a Maze with Treasures, Monsters, and an Explorer.
 * 
 * @author (Ebrahim) 
 * @version (Project 3 Part 1 )
 */
 
 //comments for method mazes are already there, not  adding any
public class Maze
{
   // named constants
   public static final int ACTIVE = 0;
   public static final int EXPLORER_WIN = 1;
   public static final int MONSTER_WIN = 2;
    
    // instance variables
   private Square[][] squares;
   private ArrayList<RandomOccupant> randOccupants;
   private Explorer explorer;
   private int rows;
   private int cols;

   /**
    * Constructor for objects of class Maze
    */
   public Maze(Square[][] squares, int rows, int cols)
   {
     
     this.squares = squares;
     this.rows = rows;
     this.cols = cols; 
     
    randOccupants = new ArrayList<RandomOccupant>();
      // CHANGE - initialize the squares, rows, and cols instance variables to
      //          what is passed in to the constructor
    
      // CHANGE - create the empty ArrayList of RandomOccupants
    
   }
  
   // QUERIES
   public Square getSquare(int row, int col) { return squares[row][col]; }
   public int rows() {return rows;}
   public int cols() {return cols;}
   public String explorerName() {return explorer.name();}
   public Explorer getExplorer() {return explorer;}
    
   // CHANGE - Implement the following two methods.  I have them stubbed to return dummy values just so it will compile.
   //          Your getRandomOccupant should return the occupant from the ArrayList at the specified index.
   public RandomOccupant getRandomOccupant(int index) {
     
     return randOccupants.get(index); 
     
     
     }
   public int getNumRandOccupants() {
     
     int item = randOccupants.size();
     return item;
     
     }
   
  
   // COMMANDS
   // CHANGE - implement the following method
   public void addRandomOccupant(RandomOccupant ro) {  
     
     randOccupants.add(ro); 
     
     }
  
   public void setExplorer(Explorer e) {explorer = e;}
  
   public void explorerMove(int key)
   {
      explorer.move(key);
   }
  
   public void randMove()
   {
     
    for (RandomOccupant a : randOccupants) 
    {
      a.move(); 
    }
      // CHANGE - instruct each object in the RandomOccupant to move
   }
  
   /**
    * Returns the status of the game.
    *
    * If all treasures have been found, return EXPLORER_WIN.
    * If not, check each maze occupant, if it is a Monster and
    *    it is in the same location as the Explorer, return
    *    MONSTER_WIN.  Note that you can use == to check locations, do you know why?
    * Otherwise, return ACTIVE.
    */
   public int gameStatus()
   {
      int status = ACTIVE;
      
    boolean treasuresfound = foundAllTreasures(); 
    if (treasuresfound) 
    {
      status = EXPLORER_WIN;
    }
    else
    {
      for (RandomOccupant a : randOccupants) {
        if (a instanceof Monster)  {
        
          if (((Monster)a).location() == explorer.location()) {
          
          status = MONSTER_WIN;
          
          }
          else {
            status = ACTIVE;
          }
        }
      }
    }
          
    
        
      // CHANGE - implement
        
      return status;
   }
  
   private boolean foundAllTreasures()
   {
      boolean foundAll = true;
        
      // CHANGE - search through all the occupants to see if the Treasures have been found.  Return false if
      //        - there is a Treasure that hasn't been found.
      
    for (RandomOccupant ab : randOccupants)
      {
        if (ab instanceof Treasure)
        {
          
          boolean treasurefound = ((Treasure)ab).found(); 
          if (treasurefound == false)
            {
              foundAll = false; 
            }
        }
      }
              
      return foundAll;
   }
    
   public void lookAround(Square s)
   {
      int row = s.row();
      int col = s.col();
        
      // Clear what was previously in view
      resetInView();
        
      // Set the current square so that we are viewing it (obviously)
      s.setInView(true);
      
     
      
      
      
      
      // CHANGE - Check the adjacent squares.  If there isn't a wall in the way, set their inview to true.
      
  if (!squares[row][col].wall(Square.UP))
  {
    squares[row - 1][col].setInView(true); 
  }
  if (!squares[row][col].wall(Square.DOWN))
  {
    squares[row + 1][col].setInView(true); 
  }
  if (!squares[row][col].wall(Square.RIGHT))
  {
    squares[row][col + 1].setInView(true); 
  }
  if (!squares[row][col].wall(Square.LEFT))
  {
    squares[row][col - 1].setInView(true); 
  }
  
      
      
      
      //        - Check the diagonal squares.  If there isn't a wall in the way, set their inview to true.
      
      //Upper Diagnol
     if (!squares[row][col].wall(Square.UP)) {
     if (!squares[row - 1][col].wall(Square.RIGHT)){
       squares[row - 1][col + 1].setInView(true);
     }
   }
   
   if (!squares[row][col].wall(Square.RIGHT)) {
     if (!squares[row][col + 1].wall(Square.UP)){
       squares[row - 1][col + 1].setInView(true);
     }
   }
   
   //Upper Left
   if (!squares[row][col].wall(Square.UP)) {
     if (!squares[row - 1][col].wall(Square.LEFT)){
       squares[row  - 1 ][col - 1].setInView(true);
     }
   }
   
   if (!squares[row][col].wall(Square.LEFT)) {
     if (!squares[row][col - 1].wall(Square.UP)){
       squares[row - 1][col - 1].setInView(true);
     }
   }
   
   //Lower Right
   if (!squares[row][col].wall(Square.DOWN)) {
     if (!squares[row + 1][col].wall(Square.RIGHT)){
       squares[row + 1][col + 1].setInView(true);
     }
   }
   
   if (!squares[row][col].wall(Square.RIGHT)) {
     if (!squares[row][col + 1].wall(Square.DOWN)){
       squares[row + 1][col + 1].setInView(true);
     }
   }
   
   //Lower Left
   if (!squares[row][col].wall(Square.LEFT)) {
     if (!squares[row][col - 1].wall(Square.DOWN)){
       squares[row + 1][col - 1].setInView(true);
     }
   }
   
   if (!squares[row][col].wall(Square.DOWN)) {
     if (!squares[row + 1][col].wall(Square.LEFT)){
       squares[row + 1][col - 1].setInView(true);
     }
   }
}
    
   private void resetInView()
   {
      for (int i = 0; i<rows; i++) {
         for (int j = 0; j<cols; j++) {
            squares[i][j].setInView(false);
         }
      }
   }
}