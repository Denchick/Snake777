package snake.project.com.creatures;

import snake.project.com.architecture.Game;

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
  public boolean deadInConflict(ICreature conflictedObject, Game game) {
    return false;
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
