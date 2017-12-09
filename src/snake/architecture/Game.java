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

  public <T extends ICreature> T getCreatureFromMap(Class<T> cretureType) {
    for (ICreature creature : map.creatures) {
      if (cretureType.isInstance(creature)) {
        return (T)creature;
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

  public Game(int width, int height) {
    map = new Map(width, height);
    createSnakeOnMap();
    createMushroomOnMap();
    createAppleOnMap();
    createWallOnMap();
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

    map.setCreatureOnMap(new GoodSnake(coordinates));
  }

  private boolean checkFoodReachedTheEndOfSnake() {
    Point foodCoordinates = getCreatureFromMap(GoodSnake.class).eatenFood.peek();
    GoodSnake goodSnake = getCreatureFromMap(GoodSnake.class);
    return foodCoordinates != null
        && !goodSnake.getHead().coordinates.equals(foodCoordinates)
        && !goodSnake.getBody().coordinates.contains(foodCoordinates);
  }

  private boolean checkWallHitSnake() {
    Wall wall = getCreatureFromMap(Wall.class);
    return wall.getCoordinates().equals(getCreatureFromMap(GoodSnake.class).getHead().coordinates);
  }



  public void makeOneStep() {
    if (isOver) return;

    GoodSnake goodSnake = getCreatureFromMap(GoodSnake.class);
    goodSnake.makeMove();

    if (checkFoodWasEaten()) {
      IFood food = getFoodByCoordinates(getCreatureFromMap(GoodSnake.class).getHead().coordinates);
      if (food instanceof Apple) {
        Point coordinate = new Point(getCreatureFromMap(GoodSnake.class).getHead().coordinates);
        goodSnake.eatenFood.add(coordinate);
        createAppleOnMap();
      }
      if (food instanceof Mushroom)
        food.ActionInConflict(getCreatureFromMap(GoodSnake.class));
      map.DeleteCreatureFromMap(goodSnake.getHead().coordinates, (ICreature) food);
    }

    if ((int) (Math.random()*25) == 0){
      createMushroomOnMap();
    }

    if (checkFoodReachedTheEndOfSnake()) {
      needToIncreaseSnake = true;
      goodSnake.eatenFood.poll();
    }
    else needToIncreaseSnake = false;

    if (goodSnake.isCollisionsExists() || checkWallHitSnake()) {
      isOver = true;
      return;
    }

    if (needToIncreaseSnake) {
      goodSnake.increase();
    }

    isOver = map.pointWithinMapBorders(getCreatureFromMap(GoodSnake.class).getHead().coordinates);
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
      if (creature instanceof IFood &&
          creature.getCoordinates().equals(getCreatureFromMap(GoodSnake.class).getHead().coordinates))
        return true;
    }
    return false;
  }
}