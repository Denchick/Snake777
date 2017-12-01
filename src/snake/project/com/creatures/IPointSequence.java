package snake.project.com.creatures;

import java.awt.*;
import java.util.List;
import snake.project.com.architecture.Point;


public interface IPointSequence extends ICreature {

  List<Point> getBodyCoordinates();
}
