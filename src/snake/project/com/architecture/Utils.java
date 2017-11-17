package snake.project.com.architecture;

import java.awt.*;

public final class Utils {

    private Utils() {
        // типо статический класс
    }

    public static Point GetCorrectCoordinates(Map map, int x, int y)
    {
        x = (x % map.Width + map.Width) % map.Width;
        y = (y % map.Height + map.Height) % map.Height;
        return new Point(x, y);
    }
}
