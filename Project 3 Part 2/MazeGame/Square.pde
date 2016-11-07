/*
Creates blueprint for Square
Ebrahim Feghhi 
CPE 102 Project 3
*/

public class Square {
  
  //Named Constants
  public static final int SQUARE_SIZE = 50;
  public static final int UP = 0;
  public static final int RIGHT = 1; 
  public static final int DOWN = 2; 
  public static final int LEFT = 3; 
  
  //Fields(Instance Variables)
  private boolean[] walls = new boolean[4];
  private boolean seen;
  private boolean InView; 
  private int row;
  private int col;
  private Treasure t; 
  
  //Constructors 
  public Square (boolean up, boolean right, boolean down, boolean left, int row, int col) {
    walls[UP] = up;
    walls[RIGHT] = right;
    walls[DOWN] = down;
    walls[LEFT] = left;
    this.row = row;
    this.col = col;
  }
  
  //what is a query method
  
  //returns if there is a wall in specificed direction
  //@param direction- specifies direction
  //@return- returns true if wall is there, false otherwise
  //@throws error when direction is not in range of Array 
  public boolean wall (int direction) {
    
    return walls[direction];
    
  }
  
  //returns seen value
  //@return- boolean seen

  public boolean seen () {
    
    return seen; 
  }
  
  //returns inView
  //@return- returns boolean inView
  public boolean inView() {
    
    return InView; 
    
  }
  
  //returns row
  //@returns integer row
  public int row() {
    
    return row; 
    
  }
  
  //returns col
  //@returns integer column
  public int col() {
    
    return col;
  }
  
  //returns tresuare reference
  //returns t of Type treasure
  public Treasure treasure() {
    
    return t; 
    
  }
  
  //returns x value by multiplying col by squaresize
  //@returns integer x
  public int x () {
    
    int x = col * SQUARE_SIZE;
    return x; 
    
  }
  
  //returns y value by multiplying row by squaresize
  //@returns integer y
  public int y () {
  
    int y = row * SQUARE_SIZE; 
    return y; 
    
  }
  
  //sets Inview variables and sets seen to true if InView is true
  //@param boolean InView 
  public void setInView (boolean InView) {
    
    this.InView = InView;
    
    if (InView == true) 
    {
      seen = true; 
    }
    
    
  }
  
  //sets the Treasure
  //@param Treasure t
  public void setTreasure (Treasure t) {
    
    this.t = t;
  }
  
  
  //notifies square when occupant enters it
  public void enter () {
    
    if (!(t == null)) {
      t.setFound();
    }
    
  }
  
  
}
    
    