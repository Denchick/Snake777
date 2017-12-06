package snake.creatures;

import snake.architecture.Direction;
import snake.architecture.Point;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Snake implements IMovable, IPointSequence {

  private final SnakeHead head;
  private final SnakeBody body;
  public Direction snakeDirection = Direction.Right;
  public Queue<Point> eatenFood = new ArrayDeque<Point>();
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

  @Override
  public void setDirection(Direction direction) {
    snakeDirection = direction;
    head.SetDirection(direction);
  }

  @Override
  public Direction getDirection() {
    return snakeDirection;
  }

  public Snake(List<Point> snake) {
    int headIndex = snake.size() - 1;
    this.head = new SnakeHead(snake.get(headIndex));
    this.body = new SnakeBody(snake);
    this.body.coordinates.remove(headIndex);
  }


  public Point getHeadCoordinates() { return this.head.coordinates; }

  public SnakeHead getHead() { return this.head; }
  public SnakeBody getBody() { return this.body; }

  @Override
  public void makeMove() {
    body.coordinates.remove(0);
    body.coordinates.add(new Point(head.coordinates));

    if (snakeDirection == Direction.Left) {
      head.coordinates.setX(head.coordinates.getX() - 1);
    } else if (snakeDirection == Direction.Right) {
      head.coordinates.setX(head.coordinates.getX() + 1);
    } else if (snakeDirection == Direction.Down) {
      head.coordinates.setY(head.coordinates.getY() + 1);
    } else if (snakeDirection == Direction.Up){
      head.coordinates.setY(head.coordinates.getY() - 1);
    }
  }

  public void increase() {
    body.coordinates.add(0, new Point(getTailCoordinates()));
  }

  public void decrease(){
    body.coordinates.remove(0);
  }
}


