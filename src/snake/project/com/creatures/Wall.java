package snake.project.com.creatures;

import snake.project.com.architecture.Game;
import snake.project.com.architecture.Point;

import java.awt.*;

public class Wall implements ICreature {

  private Point coordinates;

  public Wall(int x, int y) {
    this.coordinates = new Point(x, y);
  }

  public Wall(Point coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public int getPriorityForGameHandle() {
    return 20;
  }

  @Override
  public int getDrawingPriority() {
    return 20;
  }

  @Override
  public Point getCoordinates() {
    return coordinates;
  }

  @Override
  public void setCoordinates(Point coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public Color getColor() {
    return Color.DARK_GRAY;
  }
}
