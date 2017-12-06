package snake.creatures;

import snake.architecture.Direction;

public interface IMovable extends ICreature {
  void setDirection(Direction direction);
  Direction getDirection();
  void makeMove();
}
