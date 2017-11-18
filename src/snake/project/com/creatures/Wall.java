package snake.project.com.creatures;

import snake.project.com.architecture.Game;

import java.awt.*;
import java.util.ArrayList;

public class Wall implements ICreature {

  private Point coordinates;

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
