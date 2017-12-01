package snake.project.com.creatures;

import snake.project.com.architecture.Game;
import snake.project.com.architecture.Point;

import java.awt.*;

public class Food implements ICreature {

  private Point coordinates;

  public Food(int x, int y) {
    this.coordinates = new Point(x, y);
  }

  public Food(Point coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public int getPriorityForGameHandle() {
    return 8;
  }

  @Override
  public int getDrawingPriority() {
    return 8;
  }

  @Override
  public Point getCoordinates() {
    return coordinates;
  }

  @Override
  public void setCoordinates(Point coordinates) {
    this.coordinates = coordinates;
  }
}
