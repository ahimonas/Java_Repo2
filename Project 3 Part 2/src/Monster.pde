/*BluePrint for Monster Occupant
 * CPE 102 Project 3
 * Ebrahim Feghhi
 * */


public class Monster extends RandomOccupant {
  
  public Monster (Maze maze) {
    super(maze);
  }
  
  public Monster (Maze maze, long seed) {
    
    super(maze,seed);
  }
  
  public Monster (Maze maze, Square location) {
    
    super(maze,location);
    
  }
  
}