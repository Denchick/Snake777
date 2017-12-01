package snake.project.com.creatures;

import snake.project.com.architecture.Game;
import snake.project.com.architecture.Point;

import java.awt.*;

public interface ICreature {

  int getPriorityForGameHandle();

  int getDrawingPriority();

  Point getCoordinates();



  void setCoordinates(Point coordinates);

  Color getColor();
}
