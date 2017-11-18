package snake.project.com.creatures;

import snake.project.com.architecture.Direction;

public interface IMovable extends ICreature {

  void makeMove(Direction direction);
}
