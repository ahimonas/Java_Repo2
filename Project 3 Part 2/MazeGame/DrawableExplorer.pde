
/* BluePrint for DrawableExplorer
 * Ebrahim Feghhi
 * CPE 102 Project 3
 * */

public class DrawableExplorer extends Explorer implements Drawable {
  
  public DrawableExplorer (Square location, Maze maze, String name)
    {
      super(location,maze,name);
      
    }
  
  public void draw () 
  {
    Square loc = super.location();
    int bx = loc.x();
    int by = loc.y();
    
    PImage img;
    img = loadImage("AlienHead.gif");
    image(img, bx, by);  
    
  }
  
}
      