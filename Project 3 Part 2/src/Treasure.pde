/*Blueprint for Treasure 
 * Ebrahim Feghhi
 * CPE 102 Project 3 */


public class Treasure extends RandomOccupant {

  private boolean found;

  public Treasure (Maze maze) {

    super(maze); 
    found = false; 
    Square loc = super.location();
    loc.setTreasure(this);
  }

  public Treasure (Maze maze, long seed) {

    super(maze, seed); 
    found = false; 
    Square loc = super.location();
    loc.setTreasure(this); 
    //this.seed = seed;
  }

  public Treasure (Maze maze, Square location) {

    super(maze, location); 
    found = false; 
    Treasure t;
    Square loc = super.location();
    loc.setTreasure(this);
  }

  //returns boolean found
  //@return found
  public boolean found () {

    return found;
  }

  //sets found to true
  public void setFound () {

    found = true;
  }
  //overrides old move method
  public void move () {
  }
}