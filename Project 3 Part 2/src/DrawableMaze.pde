
/* BluePrint for DrawableMaze 
 * Ebrahim Feghhi
 * CPE 102 Project 3
 * */

public class DrawableMaze extends Maze implements Drawable {
  
  public DrawableMaze (DrawableSquare[][] maze, int rows, int cols) {
    
    super (maze,rows,cols);
    
  
  }
  
  public void draw () {
    
    int height = super.rows() * 50;
    int width = super.cols() * 50; 
    
    fill(0,0,139);
    rect(0,0,width,height); 
    
    DrawableSquare.draw(super.squares);
    DrawableMonster.draw(super.randOccupants);
    DrawableTreasure.draw(super.randOccupants);
    
    DrawableExplorer.draw(super.explorer);
    
    
    
    
    
    
    
  }
  
}