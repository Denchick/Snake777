package snake.creatures;

import snake.architecture.Direction;
import snake.architecture.Point;

import javax.swing.*;
import java.awt.*;

public class SnakeHead {
  public Point coordinates;
  private Image image;
  private Snake snake;

  public Image getImage() {
    return image;
  }
  public SnakeHead(Point point) {
      coordinates = new Point(point);
      image = (new ImageIcon("images/headRight.png")).getImage();
  }

  public void SetDirection(Direction direction){
      if (direction == Direction.Up){
          image = (new ImageIcon("images/headUp.png")).getImage();
      }
      if (direction == Direction.Down){
          image = (new ImageIcon("images/headDown.png")).getImage();
      }
      if (direction == Direction.Right){
          image = (new ImageIcon("images/headRight.png")).getImage();
      }
      if (direction == Direction.Left){
          image = (new ImageIcon("images/headLeft.png")).getImage();
      }
  }
}
