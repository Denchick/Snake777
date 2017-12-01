package snake.project.com.creatures;

import snake.project.com.architecture.Game;
import snake.project.com.architecture.Point;

import java.awt.*;

public class Food implements ICreature, IFood {

  private Point coordinates;
  private Color color = Color.RED;
  private boolean isWasEaten = false;

  public Food(int x, int y) {
    this.coordinates = new Point(x, y);
  }

  public Food(Point coordinates) {
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
  public boolean getIsWasEaten() {
    return isWasEaten;
  }

  @Override
  public void setIsWasEaten() {
    isWasEaten = true;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public Color getColorWhenWasEaten() {
    return null;
  }
}
