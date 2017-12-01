package snake.project.com.creatures;

import java.awt.Color;

public interface IFood {
  boolean getIsWasEaten();
  void setIsWasEaten();
  Color getColor();
  Color getColorWhenWasEaten();

}
