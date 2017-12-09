package snake.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snake.architecture.Direction;
import snake.architecture.Game;
import snake.architecture.Point;
import snake.creatures.GoodSnake;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GoodSnakeTest {

  private Game game;

  @BeforeEach
  void setUp() {
    game = new Game(4, 4);
  }

  @Test
  void makeMove() {
    GoodSnake goodSnake = game.getCreatureFromMap(GoodSnake.class);
    List<Point> coordinates = goodSnake.getBodyCoordinates();
    goodSnake.setDirection(Direction.Right);

    goodSnake.makeMove();

    List<Point> expected = new ArrayList<>();
    assertTrue(coordinates.equals(coordinates));
  }

  @Test
  void increase() {
      GoodSnake goodSnake = game.getCreatureFromMap(GoodSnake.class);
      int lenght = goodSnake.getBodyCoordinates().size();
      goodSnake.increase();
      assertEquals(lenght+1, goodSnake.getBodyCoordinates().size());
  }

    @Test
    void decrease() {
        GoodSnake goodSnake = game.getCreatureFromMap(GoodSnake.class);
        int lenght = goodSnake.getBodyCoordinates().size();
        goodSnake.decrease();
        assertEquals(lenght-1, goodSnake.getBodyCoordinates().size());
    }

}