
/* BluePrint for DrawableMonster
 * Ebrahim Feghhi
 * CPE 102 Project 3
 * */

public class DrawableMonster extends Monster implements Drawable {
  
  public DrawableMonster (Maze maze)
  {
    super(maze);
  }
  
  public DrawableMonster (Maze maze, long seed)
  {
    super (maze,seed);
  }
  
  public DrawableMonster (Maze maze, Square location)
  {
    super (maze, location);
  }
  
  public void draw ()
  {
    
    Square loc = super.location();
    int bx = loc.x();
    int by = loc.y();
    
    if (loc.inView()){
    
      PImage img;
      img = loadImage("Monster.jpg");
      image(img, bx, by);  
      
    }
    
    
  }
  
}