package snake.creatures;

import snake.architecture.Point;

import java.util.List;


public interface IPointSequence extends ICreature {

  List<Point> getBodyCoordinates();
}
