package snake.creatures;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import snake.architecture.Direction;

public interface IMovable extends ICreature {
  void setDirection(Direction direction);
  Direction getDirection();
  void makeMove() throws NotImplementedException;
}
