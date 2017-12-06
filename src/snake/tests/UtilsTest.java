package snake.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snake.architecture.Map;
import snake.architecture.Utils;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {

    private Map map;

    @BeforeEach
    void setUp() {
        map = new Map(4,4);
    }

    @Test
    void CheckCorrectPol(){
        Point point = Utils.GetCorrectCoordinates(map, 2,2);
        assertEquals(new Point(2,2), point);
    }
    @Test
    void CheckCorrectOtr(){
        Point point = Utils.GetCorrectCoordinates(map, -2,-2);
        assertEquals(new Point(2,2), point);
    }

}
