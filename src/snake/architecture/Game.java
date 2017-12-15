package snake.architecture;

import snake.creatures.*;

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

  public <T extends ICreature> T getCreatureFromMap(Class<T> creatureType) {
    for (ICreature creature : map.creatures) {
      if (creatureType.isInstance(creature)) {
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
    createSecondSnakeOnMap();
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

  private void createSecondSnakeOnMap() {
    Point center = new Point(map.Width / 2, map.Height / 2 + 4);
    Point leftDot = new Point(center.getX() - 1, center.getY());
    Point rightDot = new Point(center.getX() + 1, center.getY());
    List<Point> coordinates = new ArrayList<>();
    coordinates.add(leftDot);
    coordinates.add(center);
    coordinates.add(rightDot);

    map.setCreatureOnMap(new SecondSnake(coordinates));
  }

  private boolean checkFoodReachedTheEndOfSnake(Snake snake) {
    Point foodCoordinates = getCreatureFromMap(snake.getClass()).eatenFood.peek();
    return foodCoordinates != null
        && !snake.getHead().coordinates.equals(foodCoordinates)
        && !snake.getBody().coordinates.contains(foodCoordinates);
  }

  private boolean checkWallHitSnake(Snake snake) {
    Wall wall = getCreatureFromMap(Wall.class);
    return wall.getCoordinates().equals(getCreatureFromMap(snake.getClass()).getHead().coordinates);
  }

  public void makeOneStep() {
    if (isOver) return;

    GoodSnake goodSnake = getCreatureFromMap(GoodSnake.class);
    EnemySnake enemySnake = getCreatureFromMap(EnemySnake.class);
    SecondSnake secondSnake = getCreatureFromMap(SecondSnake.class);
    enemySnake.setDirectionForAI(getCreatureFromMap(Apple.class).getCoordinates());

    MakeStepForSpecificSnake(goodSnake);
    MakeStepForSpecificSnake(enemySnake);
    MakeStepForSpecificSnake(secondSnake);

    if (GetFirstSnakeIntersectsSecond(goodSnake, enemySnake))
      isOver = true;
    if (GetFirstSnakeIntersectsSecond(enemySnake, goodSnake))
      goodSnake.Cut(enemySnake.getHead().coordinates);
  }

  private void MakeStepForSpecificSnake(Snake snake)
  {
    snake.makeMove();
    if (isOver) return;

    if (checkFoodCanBeEaten(snake)) {
      TryEatFood(snake);
    }

    if ((int) (Math.random()*25) == 0){
      putCreatureOnMap(new Mushroom());
    }

    if (checkFoodReachedTheEndOfSnake(snake)) {
      needToIncreaseSnake = true;
      snake.eatenFood.poll();
    }
    else needToIncreaseSnake = false;

    if ((snake.isCollisionsExists() || checkWallHitSnake(snake)) &&
        !snake.getClass().isInstance(EnemySnake.class)) {
      isOver = true;
      return;
    }

    if (needToIncreaseSnake) {
      snake.increase();
    }

    isOver = map.pointWithinMapBorders(getCreatureFromMap(snake.getClass()).getHead().coordinates);
  }

  private boolean GetFirstSnakeIntersectsSecond(Snake snake1, Snake snake2)
  {
    return snake2.getBody().coordinates.contains(snake1.getHead().coordinates)
        || snake2.getHead().coordinates.equals(snake1.getHead().coordinates);
  }

  private void TryEatFood(Snake snake)
  {
    Boolean wasEaten = false;
    IFood food = getFoodByCoordinates(getCreatureFromMap(snake.getClass()).getHead().coordinates);
    if (food instanceof Apple) {
      Point coordinate = new Point(getCreatureFromMap(snake.getClass()).getHead().coordinates);
      snake.eatenFood.add(coordinate);
      putCreatureOnMap(new Apple());
      wasEaten = true;
    }
    if (food instanceof Mushroom) {
      food.ActionInConflict(getCreatureFromMap(snake.getClass()));
      wasEaten = true;
    }
    if (wasEaten)
      map.DeleteCreatureFromMap(snake.getHead().coordinates, (ICreature) food);
  }

  private IFood getFoodByCoordinates(Point coordinates) {
    for (ICreature creature: getMap().creatures) {
      if (creature instanceof IFood && coordinates.equals(creature.getCoordinates()))
        return (IFood) creature;
    }
    return null;
  }

  private boolean checkFoodCanBeEaten(Snake snake) {
    for (ICreature creature: map.creatures) {
      if (creature instanceof IFood &&
          creature.getCoordinates().equals(getCreatureFromMap(snake.getClass()).getCoordinates()))
        return true;
    }
    return false;
  }
}