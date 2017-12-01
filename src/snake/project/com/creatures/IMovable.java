package snake.project.com.creatures;

import snake.project.com.architecture.Direction;

public interface IMovable extends ICreature {
  void setDirection(Direction direction);
  Direction getDirection();
  void makeMove(Direction direction);
}
