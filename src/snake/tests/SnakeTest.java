package snake.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snake.architecture.Direction;
import snake.architecture.Game;
import snake.architecture.Point;
import snake.creatures.Snake;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SnakeTest {

  private Game game;

  @BeforeEach
  void setUp() {
    game = new Game(4, 4);
  }

  @Test
  void makeMove() {
    Snake snake = game.getSnake();
    List<Point> coordinates = snake.getBodyCoordinates();
    snake.setDirection(Direction.Right);

    snake.makeMove();

    List<Point> expected = new ArrayList<>();
    assertTrue(coordinates.equals(coordinates));
  }

  @Test
  void increase() {
      Snake snake = game.getSnake();
      int lenght = snake.getBodyCoordinates().size();
      snake.increase();
      assertEquals(lenght+1, snake.getBodyCoordinates().size());
  }

    @Test
    void decrease() {
        Snake snake = game.getSnake();
        int lenght = snake.getBodyCoordinates().size();
        snake.decrease();
        assertEquals(lenght-1, snake.getBodyCoordinates().size());
    }

}