package snake.architecture;

import snake.creatures.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Game {

  private Map map;
  private boolean isOver = false;
  private boolean isPause = false;
  private boolean needToIncreaseSnake = false;
  public Map getMap() {
    return map;
  }

  public boolean isOver() { return isOver;}

  public boolean isPause() { return isPause; }

  public Snake getSnake() {
    for (ICreature creature : map.creatures) {
      if (creature instanceof Snake) {
        return (Snake) creature;
      }
    }
    return null;
  }

  public Apple getApple() {
    for (ICreature creature : map.creatures) {
      if (creature instanceof Apple) {
        return (Apple) creature;
      }
    }
    return null;
  }

  public Mushroom getMushroom() {
    for (ICreature creature : map.creatures) {
      if (creature instanceof Mushroom) {
        return (Mushroom) creature;
      }
    }
    return null;
  }

  public Type getFoodType()
  {
    for (ICreature creature : map.creatures) {
      if (creature instanceof Apple) {
        return creature.getClass();
      }
      if (creature instanceof Mushroom) {
        return creature.getClass();
      }
    }
    return null;
  }

  public Wall getWall() {
    for (ICreature creature : map.creatures) {
      if (creature instanceof Wall) {
        return (Wall) creature;
      }
    }
    return null;
  }


  public Game(int width, int height) {
    map = new Map(width, height);
    createSnakeOnMap();
    createMushroomOnMap();
    createAppleOnMap();
    createWallOnMap();
  }


  private void createAppleOnMap() {
    while (true) {
      Point appleCord = new Point(
          (int) (Math.random() * map.Width),
          (int) (Math.random() * map.Height));
      if (map.IsEmpty(appleCord)) {
        Apple apple = new Apple(appleCord);
        map.setCreatureOnMap(apple);
        break;
      }
    }
  }

  private void createMushroomOnMap() {
    while (true) {
      Point mushroomCord = new Point(
              (int) (Math.random() * map.Width),
              (int) (Math.random() * map.Height));
      if (map.IsEmpty(mushroomCord)) {
        Mushroom mushroom = new Mushroom(mushroomCord);
        map.setCreatureOnMap(mushroom);
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
        map.setCreatureOnMap(wall);
        break;
      }
    }
  }

  private void createSnakeOnMap() {
    Point center = new Point(map.Width / 2, map.Height / 2);
    Point leftDot = new Point(center.getX() - 1, center.getY());
    Point rightDot = new Point(center.getX() + 1, center.getY());
    List<Point> coordinates = new ArrayList<Point>();
    coordinates.add(leftDot);
    coordinates.add(center);
    coordinates.add(rightDot);

    Snake snake = new Snake(coordinates);
    map.setCreatureOnMap(snake);
  }

  private boolean checkAppleWasEaten() {
    Apple apple = getApple();
    //ICreature food = getFood();
    if (apple == null) return true;
    return getSnake().getHeadCoordinates().equals(apple.getCoordinates());
  }

  private boolean checkMushroomWasEaten() {
    Mushroom mushroom = getMushroom();
    if (mushroom == null) return true;
    return getSnake().getHeadCoordinates().equals(mushroom.getCoordinates());
  }

  private boolean checkFoodReachedTheEndOfSnake() {
    Point foodCoordinates = getSnake().eatenFood.peek();

    if (foodCoordinates != null)
    System.out.println("Head: " + getSnake().getHeadCoordinates() + "\nEat: " + foodCoordinates + "\n"
      );

    return foodCoordinates != null
        && !getSnake().getHeadCoordinates().equals(foodCoordinates)
        && !getSnake().getBodyCoordinates().contains(foodCoordinates);
  }

  private boolean checkWallHitSnake() {
    Wall wall = getWall();
    return wall.getCoordinates().equals(getSnake().getHeadCoordinates());
  }

  private boolean checkSnakeCollisionsExists() {
    List<Point> snakeCoordinates = getSnake().getBodyCoordinates();
    for (int i = 0; i < snakeCoordinates.size(); i++) {
      for (int j = 0; j < snakeCoordinates.size(); j++) {
        if (i != j && snakeCoordinates.get(i).equals(snakeCoordinates.get(j))) {
          return true;
        }
      }
    }
    return false;
  }

  public void makeOneStep() {
    if (isOver) return;

    Snake snake = getSnake();
    snake.makeMove();

    if (checkFoodWasEaten()) {
      IFood food = getFoodByCoordinates(getSnake().getHeadCoordinates());
      if (food instanceof Apple) {
        Point coordinate = new Point(getSnake().getHeadCoordinates());
        snake.eatenFood.add(coordinate);
        createAppleOnMap();
      }
      if (food instanceof Mushroom)
        food.ActionInConflict(getSnake());
      map.DeleteCreatureFromMap(snake.getHeadCoordinates(), (ICreature) food);

      if ((int) (Math.random()*4) == 0){
        createMushroomOnMap();
      }
    }

    if (checkFoodReachedTheEndOfSnake()) {
      needToIncreaseSnake = true;
      snake.eatenFood.poll();
    }
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
    }

    isOver = map.pointWithinMapBorders(getSnake().getHeadCoordinates());
  }

  private IFood getFoodByCoordinates(Point coordinates) {
    for (ICreature creature: getMap().creatures) {
      if (creature instanceof IFood && coordinates.equals(creature.getCoordinates()))
        return (IFood) creature;
    }
    return null;
  }

  private boolean checkFoodWasEaten() {
    for (ICreature creature: map.creatures) {
      if (creature instanceof IFood && creature.getCoordinates().equals(getSnake().getHeadCoordinates()))
        return true;
    }
    return false;
  }
}