package snake.project.com.creatures;

import snake.project.com.architecture.Direction;
import snake.project.com.architecture.Game;
import snake.project.com.architecture.Map;
import snake.project.com.architecture.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake implements IMovable, IPointSequence {

  private Point coordinates;
  private List<Point> body;
  public Direction snakeDirection = Direction.Right;

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
    return new Point(coordinates.getX(), coordinates.getY());
  }

  public Point getTailCoordinates() {
    return new Point(getListCoordinates().get(0).getX(), getListCoordinates().get(0).getY());
  }

  @Override
  public void setDirection(Direction direction) {
    snakeDirection = direction;
  }

  @Override
  public Direction getDirection() {
    return snakeDirection;
  }

  @Override
  public void makeMove(Direction direction) {
    for (int i = 0; i < getListCoordinates().size() - 1; i++) {
      Point currentCoordinate = getListCoordinates().get(i);
      currentCoordinate.setCoordinates(getListCoordinates().get(i + 1));
    }

    if (direction == Direction.Left) {
      coordinates.setX(coordinates.getX() - 1);
    } else if (direction == Direction.Right) {
      coordinates.setX(coordinates.getX() + 1);
    } else if (direction == Direction.Down) {
      coordinates.setY(coordinates.getY() + 1);
    } else if (direction == Direction.Up){
      coordinates.setY(coordinates.getY() - 1);
    }
  }

  public void increase() {
    body.add(0, new Point(getTailCoordinates()));
  }
}
