
/* BluePrint for DrawableTreasure 
 * Ebrahim Feghhi
 * CPE 102 Project 3
 * */

public class DrawableTreasure extends Treasure implements Drawable {

  public DrawableTreasure (Maze maze) 
  {
    super (maze);
  }

  public DrawableTreasure (Maze maze, long seed) 
  {
    super (maze, seed);
  }

  public DrawableTreasure (Maze maze, Square location) 
  {
    super (maze, location);
  }

  public void draw() 
  {

    Square loc = super.location();
    int cx = loc.x();
    int cy = loc.y();

    if (loc.seen()) {
      if (!super.found()) {

        PImage img;
        img = loadImage("Treasure.png");
        image(img, bx, by);
      }

      if (super.found()) {

        PImage img;
        img = loadImage("Coin.png");
        image(img, bx, by);
      }
    }
  }
}