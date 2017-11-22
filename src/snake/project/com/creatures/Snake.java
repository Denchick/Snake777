package snake.project.com.creatures;

import snake.project.com.architecture.Direction;
import snake.project.com.architecture.Game;
import snake.project.com.architecture.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake implements IMovable, IPointSequence {

  private Point coordinates;
  private List<Point> body;

  public Snake(List<Point> body) {
    coordinates = body.get(body.size() - 1);
    this.body = body;
  }

  @Override
  public int getPriorityForGameHandle() {
    return 10;
  }

  @Override
  public int getDrawingPriority() {
    return 10;
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

  @Override
  public List<Point> getListCoordinates() {
    return body;
  }

  public Point getHeadCoordinates() {
    int lastIndex = getListCoordinates().size() - 1;
    Point coordinates = getListCoordinates().get(lastIndex);
    return new Point(coordinates.x, coordinates.y);
  }

  public Point getTailCoordinates() {
    return new Point(getListCoordinates().get(0).x, getListCoordinates().get(0).y);
  }

  @Override
  public void makeMove(Direction direction) {
    for (int i = 0; i < getListCoordinates().size() - 1; i++) {
      // DO NOT TOUCH!
      Point currentCoordinate = getListCoordinates().get(i);
      Point nextCoordinate = getListCoordinates().get(i + 1);
      currentCoordinate.x = nextCoordinate.x;
      currentCoordinate.y = nextCoordinate.y;
    }

    if (direction == Direction.Left) {
      coordinates.x -= 1;
    } else if (direction == Direction.Right) {
      coordinates.x += 1;
    } else if (direction == Direction.Down) {
      coordinates.y += 1;
    } else if (direction == Direction.Up){
      coordinates.y -= 1;
    }
  }

  public void increase() {
    body.add(0, new Point(getTailCoordinates()));
  }
}
