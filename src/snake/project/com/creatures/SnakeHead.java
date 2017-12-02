package snake.project.com.creatures;

import java.awt.Image;
import javax.swing.ImageIcon;
import snake.project.com.architecture.Point;

public class SnakeHead {
  public Point coordinates;
  private Image image;

  public Image getImage() {
    return image;
  }
  public SnakeHead(Point point) {
    image = (new ImageIcon("images/head.png")).getImage();
    coordinates = new Point(point);
  }
}
