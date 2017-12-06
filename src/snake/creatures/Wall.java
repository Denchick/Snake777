package snake.creatures;

import snake.architecture.Point;

import javax.swing.*;
import java.awt.*;

public class Wall implements ICreature {

  private Point coordinates;
  private Image image ;

  public Wall(int x, int y) {
    image = (new ImageIcon("images/wall.png")).getImage();
    this.coordinates = new Point(x, y);
  }

  public Wall(Point coordinates) {
    image = (new ImageIcon("images/wall.png")).getImage();
    this.coordinates = coordinates;
  }

  @Override
  public int getPriorityForGameHandle() {
    return 20;
  }

  @Override
  public int getDrawingPriority() {
    return 20;
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
