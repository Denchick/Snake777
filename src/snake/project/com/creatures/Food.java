package snake.project.com.creatures;

import javax.swing.ImageIcon;
import snake.project.com.architecture.Game;
import snake.project.com.architecture.Point;

import java.awt.*;

public class Food implements ICreature, IFood {

  private Point coordinates;
  private Image image;

  public Food(int x, int y) {
    this.coordinates = new Point(x, y);
    image = (new ImageIcon("images/apple.png")).getImage();
  }

  public Food(Point coordinates) {
    image = (new ImageIcon("images/apple.png")).getImage();
    this.coordinates = coordinates;
  }

  @Override
  public int getPriorityForGameHandle() {
    return 8;
  }

  @Override
  public int getDrawingPriority() {
    return 8;
  }

  @Override
  public Point getCoordinates() {
    return coordinates;
  }

  @Override
  public void setCoordinates(Point coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public Image getImage() {
    return image;
  }
}
