package snake.architecture;

import java.awt.Point;

public final class Utils {

  private Utils() {
    // типо бессмысленный статический класс
  }

  public static java.awt.Point GetCorrectCoordinates(Map map, int x, int y) {
    x = (x % map.Width + map.Width) % map.Width;
    y = (y % map.Height + map.Height) % map.Height;
    return new Point(x, y);
  }
}
