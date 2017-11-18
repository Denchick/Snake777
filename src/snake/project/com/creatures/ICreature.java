package snake.project.com.creatures;

import snake.project.com.architecture.Game;

import java.awt.*;

public interface ICreature {

  int getPriorityForGameHandle();

  int getDrawingPriority();

  boolean deadInConflict(ICreature conflictedObject, Game game);

  Point getCoordinates();

  void setCoordinates(Point coordinates);
}
