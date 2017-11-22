package snake.project.com.architecture;

import snake.project.com.creatures.Snake;
import snake.project.com.creatures.Food;
import snake.project.com.creatures.Wall;
import snake.project.com.creatures.ICreature;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Game {

  private Map map;
  private boolean isOver = false;
  public Direction snakeDirection = Direction.Right;
  public Direction lastDirection = snakeDirection;
  private boolean isPause = false;
  private boolean needToIncreaseSnake = false;
  public Map getMap() {
    return map;
  }

  public boolean isOver() {
    if (getSnake().getHeadCoordinates().x >= map.Width || getSnake().getHeadCoordinates().x < 0 ||
            getSnake().getHeadCoordinates().y >= map.Height || getSnake().getHeadCoordinates().y < 0){
      return true;
    }
    else{
      return isOver;
    }
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

  public Wall getWall() {
    for (ICreature creature : map.Creatures) {
      if (creature instanceof Wall) {
        return (Wall) creature;
      }
    }
    return null;
  }


  public Game(int width, int height) {
    map = new Map(width, height);
    createSnakeOnMap();
    createFoodOnMap();
    createWallOnMap();
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

  private void createWallOnMap() {
    while (true) {
      Point coordinates = new Point(
              (int) (Math.random() * map.Width),
              (int) (Math.random() * map.Height));
      if (map.IsEmpty(coordinates)) {
        Wall wall = new Wall(coordinates);
        map.SetCreatureOnMap(wall);
        break;
      }
    }
  }

  private void createSnakeOnMap() {
    Point center = new Point(map.Width / 2, map.Height / 2);
    Point leftDot = new Point(center.x - 1, center.y);
    Point rightDot = new Point(center.x + 1, center.y);
    List<Point> coordinates = new ArrayList<Point>();
    coordinates.add(leftDot);
    coordinates.add(center);
    coordinates.add(rightDot);

    Snake snake = new Snake(coordinates);
    map.SetCreatureOnMap(snake);
  }

  private boolean checkFoodWasEaten() {
    Food food = getFood();
    if (food == null) return true;
    return (getSnake().getHeadCoordinates().x == food.getCoordinates().x &&
            getSnake().getHeadCoordinates().y == food.getCoordinates().y);
  }

  private boolean checkFoodReachedTheEndOfSnake() {
    Food food = getFood();
    if (food == null) {
      return true;
    }
    else if (getSnake().getTailCoordinates().x == food.getCoordinates().x &&
            getSnake().getTailCoordinates().y == food.getCoordinates().y){
      map.DeleteCreatureFromMap(food.getCoordinates().x, food.getCoordinates().y, food);
      return true;
    }
    return false;
  }

  private boolean checkWallHitSnake() {
    Wall wall = getWall();
    return wall.getCoordinates().x == getSnake().getHeadCoordinates().x &&
            wall.getCoordinates().y == getSnake().getHeadCoordinates().y;
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
    if (isOver) return;
    Snake snake = getSnake();
    snake.makeMove(snakeDirection);
    lastDirection = snakeDirection;
    if (checkFoodWasEaten()) createFoodOnMap();

    if (needToIncreaseSnake) snake.increase();
    if (checkFoodReachedTheEndOfSnake()) needToIncreaseSnake = true;
    else needToIncreaseSnake = false;

    if (checkSnakeCollisionsExists()) {
      isOver = true;
      return;
    }
    if (checkWallHitSnake()){
      isOver = true;
      return;
    }

    if (needToIncreaseSnake) {
      snake.increase();
      needToIncreaseSnake = false;
    }
  }
}