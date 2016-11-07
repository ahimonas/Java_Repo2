/*Blueprint for Explorer
 * CPE 102 Project 3
 * Ebrahim Feghhi
 * */

import java.awt.event.KeyEvent;

public class Explorer extends Occupant {
  
  private String name; 
  private Maze maze;
  
  
  public Explorer (Square location, Maze maze, String name) {
    super(location);
    this.maze = maze;
    this.name = name;
    maze.lookAround(location); 
  }
  
  //returns String name 
  //@return name
  public String name() {
    
    return name;  
  }
  
  //moves Explorer based on input key
  //@param int key which correlates to key user pressed
  public void move (int key) {
    
     Square loc = super.location();
     int r = loc.row();
     int c = loc.col();
     int direction = 5;
     System.out.print(r);
     System.out.println(c);
     
     
     
     if (key == KeyEvent.VK_DOWN)
     {
         direction = loc.DOWN;
         r = r + 1; 
     }
     
     if (key == KeyEvent.VK_UP)
     {
       direction = loc.UP;
       r = r - 1;
       
     }
     
     if (key == KeyEvent.VK_LEFT)
     {
       direction = loc.LEFT;
       c = c - 1;
     }
     
     if (key == KeyEvent.VK_RIGHT)
     {
       direction = loc.RIGHT; 
       c = c + 1; 
     }
     
    if (key == KeyEvent.VK_KP_DOWN)
    {
      direction = loc.DOWN;
      r = r + 1;
    }
     
     if (key == KeyEvent.VK_KP_RIGHT)
     {
       direction = loc.RIGHT; 
       c = c + 1; 
     }
     
      if (key == KeyEvent.VK_KP_LEFT)
     {
       direction = loc.LEFT;
       c = c - 1;
     }
     
      if (key == KeyEvent.VK_KP_UP)
     {
       direction = loc.UP;
       r = r - 1;
       
     }
     
     
     
     
     
     
      
     if (!(direction == 5))
      {
        
    
        if (!loc.wall(direction))
        {
            Square newlocation = maze.getSquare(r,c);
            super.moveTo(newlocation);
        }
        
      }
  }
       
  
  //moves square using super, also notifies square when explorer enters and instructs maze to lookaround
  //@param Square object s 
  public void moveTo (Square s) {
    
    super.moveTo(s); 
    s.enter();
    maze.lookAround(s); 
    
  }
}