package snake.creatures;

import snake.architecture.Point;

import java.awt.*;

public interface ICreature {

  int getPriorityForGameHandle();

  int getDrawingPriority();

  Point getCoordinates();

  void setCoordinates(Point coordinates);

  Image getImage();
}
