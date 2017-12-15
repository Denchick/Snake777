package snake.creatures;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import snake.architecture.Direction;
import snake.architecture.Point;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class SecondSnake extends Snake {

  public SecondSnake(List<Point> snake, String headImageRight, String headImageDown,
      String headImageLeft, String headImageUp, String bodyImage) {
    super(snake, headImageRight, headImageDown, headImageLeft, headImageUp, bodyImage);
  }

  public SecondSnake(List<Point> snake) {
    super(snake, "images/secondHeadRight.png",
        "images/secondHeadDown.png",
        "images/secondHeadLeft.png",
        "images/secondHeadUp.png",
        "images/secondBody.png");
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
  public Image getImage() {
    return null;
  }

  public void Cut(Point point)
  {
    List<Point> body = getBodyCoordinates();
    int index = body.indexOf(point);
    if (getHead().coordinates.equals(point) || index < 3) setBody(getBodyCoordinates().subList(0,1));
    else
      setBody(body.subList(0, index));
  }

}


