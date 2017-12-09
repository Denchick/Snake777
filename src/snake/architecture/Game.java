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

  private void putCreatureOnMap(ICreature creature) {
    while (true) {
      Point creatureCoordinates = new Point(
          (int) (Math.random() * map.Width),
          (int) (Math.random() * map.Height));
      if (map.IsEmpty(creatureCoordinates)) {
        creature.setCoordinates(creatureCoordinates);
        map.setCreatureOnMap(creature);
        break;
      }
    }
  }

  public Game(int width, int height) {
    map = new Map(width, height);
    createGoodSnakeOnMap();
    createEnemySnakeOnMap();
    putCreatureOnMap(new Mushroom());
    putCreatureOnMap(new Apple());
    putCreatureOnMap(new Wall());
  }

  private void createEnemySnakeOnMap() {
    Point center = new Point(map.Width / 2, map.Height / 2 - 4);
    Point leftDot = new Point(center.getX() - 1, center.getY());
    Point rightDot = new Point(center.getX() + 1, center.getY());
    List<Point> coordinates = new ArrayList<>();
    coordinates.add(leftDot);
    coordinates.add(center);
    coordinates.add(rightDot);

    map.setCreatureOnMap(new EnemySnake(coordinates));
  }

  private void createGoodSnakeOnMap() {
    Point center = new Point(map.Width / 2, map.Height / 2);
    Point leftDot = new Point(center.getX() - 1, center.getY());
    Point rightDot = new Point(center.getX() + 1, center.getY());
    List<Point> coordinates = new ArrayList<>();
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
        putCreatureOnMap(new Apple());
      }
      if (food instanceof Mushroom)
        food.ActionInConflict(getCreatureFromMap(GoodSnake.class));
      map.DeleteCreatureFromMap(goodSnake.getHead().coordinates, (ICreature) food);
    }

    if ((int) (Math.random()*25) == 0){
      putCreatureOnMap(new Mushroom());
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
      if (creature instanceof IFood && (
          creature.getCoordinates().equals(getCreatureFromMap(GoodSnake.class).getHead().coordinates) ||
          creature.getCoordinates().equals(getCreatureFromMap(EnemySnake.class).getHead().coordinates)))
        return true;
    }
    return false;
  }
}