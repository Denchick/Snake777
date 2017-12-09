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
}
