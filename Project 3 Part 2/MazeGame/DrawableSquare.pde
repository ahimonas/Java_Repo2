/* BluePrint for DrawableSquare 
 * Ebrahim Feghhi
 * CPE 102 Project 3
 * */
public class DrawableSquare extends Square implements Drawable {

  public DrawableSquare (boolean up, boolean right, boolean down, boolean left, int row, int col) 
  {
    super(up, right, down, left, row, col);
  }

  public void draw ()
  {

    int sx = super.x();
    int sy = super.y();
    if (!super.seen()) {
    }

    if (super.seen()) {


      if (super.inView()) {

        noStroke();
        fill(173, 216, 230);
        rect (sx, sy, 55, 55); 

        if (super.wall(0)) {
          line(sx, sy, (sx + 55), sy);
        }

        if (super.wall(1)) {
          line(sx, sy, sx, (sy - 55));
        }

        if (super.wall(2)) {
          line(sx, (sy - 55), (sx + 55), (sy - 55));
        }

        if (super.wall(3)) {
          line((sx + 55), (sy - 55), (sx + 55), (sy));
        }
      }
    }

    if (!super.inView()) {


      noStroke();
      fill(0, 0, 139);
      rect (sx, sy, 55, 55); 

      if (super.wall(0)) {
        line(sx, sy, (sx + 55), sy);
      }

      if (super.wall(1)) {
        line(sx, sy, sx, (sy - 55));
      }

      if (super.wall(2)) {
        line(sx, (sy - 55), (sx + 55), (sy - 55));
      }

      if (super.wall(3)) {
        line((sx + 55), (sy - 55), (sx + 55), (sy));
      }
    }
  }
}