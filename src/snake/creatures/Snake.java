package snake.creatures;

import java.util.ArrayList;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import snake.architecture.Direction;
import snake.architecture.Point;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Snake implements IMovable, IPointSequence {

  private final SnakeHead head;
  private final SnakeBody body;

  public int getLength() { return 1 + body.coordinates.size(); }
  public Queue<Point> eatenFood = new ArrayDeque<>();
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
    return head.coordinates;
  }

  @Override
  public void setCoordinates(Point coordinates) {
    this.head.coordinates = coordinates;
  }

  @Override
  public Image getImage() {
    return null;
  }

  @Override
  public List<Point> getBodyCoordinates() { return body.coordinates; }

  public Point getTailCoordinates() {
    return new Point(getBodyCoordinates().get(0).getX(), getBodyCoordinates().get(0).getY());
  }

  public Direction snakeDirection = Direction.Right;
  @Override
  public void setDirection(Direction direction) {
    snakeDirection = direction;
    head.SetDirection(direction);
  }

  @Override
  public Direction getDirection() {
    return snakeDirection;
  }

  public Snake(List<Point> snake, String headImageRight, String headImageDown, String headImageLeft,
      String headImageUp, String bodyImage) {
    int headIndex = snake.size() - 1;
    head = new SnakeHead(snake.get(headIndex), headImageRight, headImageDown, headImageLeft, headImageUp);
    body = new SnakeBody(snake, bodyImage);
    this.body.coordinates.remove(headIndex);
  }

  public SnakeHead getHead() { return this.head; }
  public SnakeBody getBody() { return this.body; }

  public void setBody(List<Point> bodyCoordinates) {
    this.body.SetBody(bodyCoordinates);
  }

  @Override
  public void makeMove() {
    getBody().coordinates.remove(0);
    getBody().coordinates.add(new Point(getHead().coordinates));

    if (snakeDirection == Direction.Left) {
      getHead().coordinates.setX(getHead().coordinates.getX() - 1);
    } else if (snakeDirection == Direction.Right) {
      getHead().coordinates.setX(getHead().coordinates.getX() + 1);
    } else if (snakeDirection == Direction.Down) {
      getHead().coordinates.setY(getHead().coordinates.getY() + 1);
    } else if (snakeDirection == Direction.Up){
      getHead().coordinates.setY(getHead().coordinates.getY() - 1);
    }
  }

  public boolean isCollisionsExists() {
    List<Point> snakeCoordinates = this.getBodyCoordinates();
    for (int i = 0; i < snakeCoordinates.size(); i++) {
      for (int j = 0; j < snakeCoordinates.size(); j++) {
        if (i != j && snakeCoordinates.get(i).equals(snakeCoordinates.get(j))) {
          return true;
        }
      }
    }
    return false;
  }

  public void Cut(Point point)
  {
    List<Point> body = getBodyCoordinates();
    int index = body.indexOf(point);
    int last_ind = body.size()-1;
    if (getHead().coordinates.equals(point)) setBody(getBodyCoordinates()
        .subList(last_ind-1,last_ind));
    else
      setBody(body.subList(index, last_ind));
  }

  public void increase() {
    body.coordinates.add(0, new Point(getTailCoordinates()));
  }

  public void decrease(){
    body.coordinates.remove(0);
  }
}


