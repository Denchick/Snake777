package snake.creatures;

import java.awt.Image;
import java.util.List;
import snake.architecture.Direction;
import snake.architecture.Point;

public class EnemySnake extends Snake {

  public EnemySnake(List<Point> snake, String headImageRight, String headImageDown,
      String headImageLeft, String headImageUp, String bodyImage) {
    super(snake, headImageRight, headImageDown, headImageLeft, headImageUp, bodyImage);
  }
  public EnemySnake(List<Point> snake) {
    super(snake, "images/enemyHeadRight.png",
        "images/enemyHeadDown.png",
        "images/enemyHeadLeft.png",
        "images/enemyHeadUp.png",
        "images/enemyBody.png");
  }

  @Override
  public int getPriorityForGameHandle() {
    return 5;
  }

  @Override
  public int getDrawingPriority() {
    return 20;
  }

  @Override
  public Image getImage() {
    return null;
  }

  public void setDirectionForAI(Point foodPoint)
  {
    Direction oldDir = getDirection();
    Point start = getHead().coordinates;
    if (start.getX() - foodPoint.getX() > 0)
      setDirection(Direction.Left);
    else if (start.getX() - foodPoint.getX() < 0)
      setDirection(Direction.Right);
    else if (start.getY() - foodPoint.getY() > 0)
      setDirection(Direction.Up);
    else
      setDirection(Direction.Down);

    if (isOppositeDirection(oldDir, getDirection()))
      setDirection(setOtherDir(getDirection()));
  }

  private Direction setOtherDir(Direction a)
  {
    if (a == Direction.Up || a == Direction.Down) return Direction.Right;
    return Direction.Up;
  }

  private boolean isOppositeDirection(Direction a, Direction b)
  {
    return a == Direction.Right && b== Direction.Left ||
        a == Direction.Left && b == Direction.Right ||
        a == Direction.Down && b == Direction.Up ||
        a == Direction.Up && b == Direction.Down;
  }
}
