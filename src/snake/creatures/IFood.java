package snake.creatures;

public interface IFood {

  boolean WasEaten();

  void ActionInConflict(Snake Snake);
}

