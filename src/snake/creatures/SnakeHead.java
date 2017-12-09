package snake.creatures;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import snake.architecture.Direction;
import snake.architecture.Point;

import javax.swing.*;
import java.awt.*;

public class SnakeHead {
  private final Map<Direction, Image> imagesByDirection = new HashMap<>();
  private Direction snakeDirection;
  public Point coordinates;

  public Image getImage() {
    return imagesByDirection.get(snakeDirection);
  }

  public SnakeHead(Point point, String headImageRight,
      String headImageDown, String headImageLeft, String headImageUp) {
    coordinates = new Point(point);
    imagesByDirection.put(Direction.Up, (new ImageIcon(headImageUp)).getImage());
    imagesByDirection.put(Direction.Down, (new ImageIcon(headImageDown)).getImage());
    imagesByDirection.put(Direction.Left, (new ImageIcon(headImageLeft)).getImage());
    imagesByDirection.put(Direction.Right, (new ImageIcon(headImageRight)).getImage());
  }

  public void SetDirection(Direction direction){
    snakeDirection = direction;
  }
}
