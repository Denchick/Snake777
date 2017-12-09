package snake.creatures;

import snake.architecture.Point;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SnakeBody {
  public List<Point> coordinates;
  private Image image;
  public Image getImage() {
    return image;
  }

  public SnakeBody(List<Point> snake, String imageFilename) {
    image = (new ImageIcon(imageFilename)).getImage();
    coordinates = snake;
  }
}
