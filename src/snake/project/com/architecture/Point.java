package snake.project.com.architecture;

public class Point {
  private int x;
  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Point(Point coordinates) {
    this.x = coordinates.x;
    this.y = coordinates.y;
  }

  public int getX() {
    return x;
  }

  public void setX(int value) {
    x = value;
  }

  public int getY() {
    return y;
  }

  public void setY(int value) {
    y = value;
  }

  @Override
  public int hashCode() {
    return x * 1337 +  y;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Point)) return false;
    Point otherAsPoint = (Point) other;
    return x == otherAsPoint.x && y == otherAsPoint.y;
  }

  public void setCoordinates(Point coordinates) {
    this.x = coordinates.getX();
    this.y = coordinates.getY();
  }

  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
