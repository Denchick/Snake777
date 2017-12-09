package snake.creatures;

import snake.architecture.Point;
import javax.swing.*;
import java.awt.*;

public class Apple implements ICreature, IFood {

  private Point coordinates;
  private Image image = (new ImageIcon("images/apple.png")).getImage();

  public Apple() {
    this.coordinates = new Point(0, 0);
  }

  public Apple(int x, int y) {
    this.coordinates = new Point(x, y);
  }

  public Apple(Point coordinates) {
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

  @Override
  public boolean WasEaten() {
    return false;
  }

  @Override
  public void ActionInConflict(GoodSnake goodSnake) {

  }

}
