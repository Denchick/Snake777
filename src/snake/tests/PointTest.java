package snake.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snake.architecture.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PointTest {
  private Point point1;
  private Point point2;
  private Point point3;

  @BeforeEach
  void setUp() {
    point1 = new Point(1, 2);
    point2 = new Point(3, 4);
    point3 = new Point(1, 2);
  }

  @Test
  void getX() {
    assertEquals(1, point1.getX());
  }

  @Test
  void setX() {
    point1.setX(0);
    assertEquals(0, point1.getX());
  }

  @Test
  void getY() {
    assertEquals(2, point1.getY());
  }

  @Test
  void setY() {
    point1.setY(0);
    assertEquals(0, point1.getY());
  }

  @Test
  void equals() {
    assertEquals(point1, point3);
    assertNotEquals(point1, point2);
  }

  @Test
  void setCoordinates() {
    point1.setCoordinates(point2);
    assertEquals(point1, point2);
  }

}