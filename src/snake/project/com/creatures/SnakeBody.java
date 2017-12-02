package snake.project.com.creatures;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import snake.project.com.architecture.Point;

public class SnakeBody {
  public List<Point> coordinates;
  private Image image;

  public Image getImage() {
    return image;
  }

  public SnakeBody(List<Point> snake) {
    image = (new ImageIcon("images/body.png")).getImage();
    coordinates = snake;
  }
}
