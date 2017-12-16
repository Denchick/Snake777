//package snake.tests;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import snake.architecture.Direction;
//import snake.architecture.Game;
//import snake.architecture.Point;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class GoodSnakeTest {
//
//  private Game game;
//
//  @BeforeEach
//  void setUp() {
//    game = new Game(4, 4);
//  }
//
//  @Test
//  void makeMove() {
//    Snake goodSnake = game.getCreatureFromMap(Snake.class);
//    List<Point> coordinates = goodSnake.getBodyCoordinates();
//    goodSnake.setDirection(Direction.Right);
//
//    goodSnake.makeMove();
//
//    List<Point> expected = new ArrayList<>();
//    assertTrue(coordinates.equals(coordinates));
//  }
//
//  @Test
//  void increase() {
//      Snake goodSnake = game.getCreatureFromMap(Snake.class);
//      int lenght = goodSnake.getBodyCoordinates().size();
//      goodSnake.increase();
//      assertEquals(lenght+1, goodSnake.getBodyCoordinates().size());
//  }
//
//    @Test
//    void decrease() {
//        Snake goodSnake = game.getCreatureFromMap(Snake.class);
//        int lenght = goodSnake.getBodyCoordinates().size();
//        goodSnake.decrease();
//        assertEquals(lenght-1, goodSnake.getBodyCoordinates().size());
//    }
//
//}