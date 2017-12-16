package snake.architecture;

import java.util.Arrays;
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

  public <T extends ICreature> T getCreatureFromMap(Class<T> creatureType, int count) {
    int curcount = 0;
    for (ICreature creature : map.creatures) {
      if (creatureType.isInstance(creature)) {
        if (curcount < count)
          curcount++;
        else
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

    ArrayList<String> snake1_pics = new ArrayList<String>(
        Arrays.asList(
      "images/headRight.png",
          "images/headDown.png",
          "images/headLeft.png",
          "images/headUp.png",
          "images/body.png"
        ));

    ArrayList<String> snake2_pics = new ArrayList<String>(
        Arrays.asList(
            "images/secondHeadRight.png",
            "images/secondHeadDown.png",
            "images/secondHeadLeft.png",
            "images/secondHeadUp.png",
            "images/secondBody.png"
        ));

    createGoodSnakeOnMap(snake1_pics, new Point(map.Width / 2, map.Height / 2));
    createGoodSnakeOnMap(snake2_pics, new Point(map.Width / 2, map.Height / 2 + 4));
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

  private void createGoodSnakeOnMap(ArrayList<String> snake_pics, Point center) {
    Point leftDot = new Point(center.getX() - 1, center.getY());
    Point rightDot = new Point(center.getX() + 1, center.getY());
    List<Point> coordinates = new ArrayList<>();
    coordinates.add(leftDot);
    coordinates.add(center);
    coordinates.add(rightDot);

    map.setCreatureOnMap(new Snake(coordinates, snake_pics.get(0), snake_pics.get(1),
        snake_pics.get(2), snake_pics.get(3), snake_pics.get(4)));
  }

  private boolean checkFoodReachedTheEndOfSnake(Snake snake, int count) {
    Point foodCoordinates = getCreatureFromMap(snake.getClass(), count).eatenFood.peek();
    return foodCoordinates != null
        && !snake.getHead().coordinates.equals(foodCoordinates)
        && !snake.getBody().coordinates.contains(foodCoordinates);
  }

  private boolean checkWallHitSnake(Snake snake, int count) {
    Wall wall = getCreatureFromMap(Wall.class, 0);
    return wall.getCoordinates().equals(getCreatureFromMap(snake.getClass(), count).getHead().coordinates);
  }

  public void makeOneStep() {
    if (isOver) return;

    List<Snake> goodSnakes = new ArrayList<Snake>();
    goodSnakes.add(getCreatureFromMap(Snake.class, 0));
    goodSnakes.add(getCreatureFromMap(Snake.class, 1));
    EnemySnake enemySnake = getCreatureFromMap(EnemySnake.class, 0);
    enemySnake.setDirectionForAI(getCreatureFromMap(Apple.class, 0).getCoordinates());

    int ind = 0;
    for (Snake goodSnake: goodSnakes) {
      MakeStepForSpecificSnake(goodSnake, ind);
      ind++;
    }
    MakeStepForSpecificSnake(enemySnake, 0);

    for (Snake goodSnake: goodSnakes) {
      if (GetFirstSnakeIntersectsSecond(goodSnake, enemySnake))
        isOver = true;
      if (GetFirstSnakeIntersectsSecond(enemySnake, goodSnake))
        goodSnake.Cut(enemySnake.getHead().coordinates);
    }
  }

  private void MakeStepForSpecificSnake(Snake snake, int count)
  {
    snake.makeMove();
    if (isOver) return;

    if (checkFoodCanBeEaten(snake, count)) {
      TryEatFood(snake, count);
    }

    if ((int) (Math.random()*25) == 0){
      putCreatureOnMap(new Mushroom());
    }

    if (checkFoodReachedTheEndOfSnake(snake, count)) {
      needToIncreaseSnake = true;
      snake.eatenFood.poll();
    }
    else needToIncreaseSnake = false;

    if ((snake.isCollisionsExists() || checkWallHitSnake(snake, count)) &&
        !snake.getClass().isInstance(EnemySnake.class)) {
      isOver = true;
      return;
    }

    if (needToIncreaseSnake) {
      snake.increase();
    }

    isOver = map.pointWithinMapBorders(getCreatureFromMap(snake.getClass(), count).getHead().coordinates);
  }

  private boolean GetFirstSnakeIntersectsSecond(Snake snake1, Snake snake2)
  {
    return snake2.getBody().coordinates.contains(snake1.getHead().coordinates)
        || snake2.getHead().coordinates.equals(snake1.getHead().coordinates);
  }

  private void TryEatFood(Snake snake, int count)
  {
    Boolean wasEaten = false;
    IFood food = getFoodByCoordinates(getCreatureFromMap(snake.getClass(), count).getHead().coordinates);
    if (food instanceof Apple) {
      Point coordinate = new Point(getCreatureFromMap(snake.getClass(), count).getHead().coordinates);
      snake.eatenFood.add(coordinate);
      putCreatureOnMap(new Apple());
      wasEaten = true;
    }
    if (food instanceof Mushroom) {
      food.ActionInConflict(getCreatureFromMap(snake.getClass(), count));
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

  private boolean checkFoodCanBeEaten(Snake snake, int count) {
    for (ICreature creature: map.creatures) {
      if (creature instanceof IFood &&
          creature.getCoordinates().equals(getCreatureFromMap(snake.getClass(), count).getCoordinates()))
        return true;
    }
    return false;
  }
}