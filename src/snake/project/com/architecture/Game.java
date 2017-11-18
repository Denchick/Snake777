package snake.project.com.architecture;

import snake.project.com.creatures.Snake;
import snake.project.com.creatures.Food;
import snake.project.com.creatures.ICreature;

import java.awt.*;
import java.util.Arrays;
import java.util.List;


public class Game {

  private Map map;
  private boolean isOver = false;
  public Direction snakeDirection = Direction.Right;
  private boolean isPause = false;

  public Map getMap() {
    return map;
  }

  public boolean isOver() {
    return isOver;
  }

  public boolean isPause() {
    return isPause;
  }

  public Snake getSnake() {
    for (ICreature creature : map.Creatures) {
      if (creature instanceof Snake) {
        return (Snake) creature;
      }
    }
    return null;
  }

  public Food getFood() {
    for (ICreature creature : map.Creatures) {
      if (creature instanceof Food) {
        return (Food) creature;
      }
    }
    return null;
  }


  public Game(int width, int height) {
    map = new Map(width, height);
    createSnakeOnMap();
    createFoodOnMap();
  }


  private void createFoodOnMap() {
    while (true) {
      Point coordinates = new Point(
          (int) (Math.random() * map.Width),
          (int) (Math.random() * map.Height));
      if (map.IsEmpty(coordinates)) {
        Food food = new Food(coordinates);
        map.SetCreatureOnMap(food);
        break;
      }
    }
  }

  private void createSnakeOnMap() {
    Point center = new Point(map.Width / 2, map.Height / 2);
    Point leftDot = new Point(center.x - 1, center.y);
    Point rightDot = new Point(center.x + 1, center.y);
    List<Point> coordinates = Arrays.asList(leftDot, center, rightDot);

    Snake snake = new Snake(coordinates);
    map.SetCreatureOnMap(snake);
  }

  private boolean checkFoodWasEaten() {
    Food food = getFood();
    if (food == null) {
      return true;
    }
    return getSnake().getHeadCoordinates() == food.getCoordinates();
  }

  private boolean checkFoodReachedTheEndOfSnake() {
    Food food = getFood();
    if (food == null) {
      return true;
    }
    return getSnake().getTailCoordinates() == food.getCoordinates();
  }

  private boolean checkSnakeCollisionsExists() {
    List<Point> snakeCoordinates = getSnake().getListCoordinates();
    for (int i = 0; i < snakeCoordinates.size(); i++) {
      for (int j = 0; j < snakeCoordinates.size(); j++) {
        if (i != j && snakeCoordinates.get(i) == snakeCoordinates.get(j)) {
          return true;
        }
      }
    }
    return false;
  }

  public void makeOneStep() {
    if (isOver) {
      return;
    }
    Snake snake = getSnake();
    if (checkFoodWasEaten()) {
      createFoodOnMap();
    }
    boolean needToIncreaseSnake = false;
    Point snakeTail = getSnake().getTailCoordinates();
    if (checkFoodReachedTheEndOfSnake()) {
      needToIncreaseSnake = true;
    }
    if (checkSnakeCollisionsExists()) {
      isOver = true;
      return;
    }
    snake.makeMove(snakeDirection);
    if (needToIncreaseSnake) {
      snake.getListCoordinates().add(0, snakeTail);
    }
  }
}