package snake.creatures;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import snake.architecture.Direction;
import snake.architecture.Point;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class GoodSnake extends Snake {

  public GoodSnake(List<Point> snake, String headImageRight, String headImageDown,
      String headImageLeft, String headImageUp, String bodyImage) {
    super(snake, headImageRight, headImageDown, headImageLeft, headImageUp, bodyImage);
  }

  public GoodSnake(List<Point> snake) {
    super(snake, "images/headRight.png",
        "images/headDown.png",
        "images/headLeft.png",
        "images/headUp.png",
        "images/body.png");
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

}


