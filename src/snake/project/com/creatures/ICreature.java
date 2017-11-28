package snake.project.com.creatures;

import snake.project.com.architecture.Game;

import java.awt.*;

public interface ICreature {

  int getPriorityForGameHandle();

  int getDrawingPriority();

  Point getCoordinates();

  void setCoordinates(Point coordinates);
}
