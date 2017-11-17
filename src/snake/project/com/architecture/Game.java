package snake.project.com.architecture;

import snake.project.com.creatures.Snake;
import snake.project.com.creatures.Food;
import snake.project.com.creatures.ICreature;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;


public class Game
{
    private Map map;
    private boolean GameIsOver;
    public Direction snakeDirection;

    public Game(int width, int height) {
        map = new Map(width, height);
        CreateSnakeOnMap();
        CreateFoodOnMap();

    }

    public Map getMap() {
        return map;
    }

    private void CreateFoodOnMap() {
        while (true) {
            Point coordinates = new Point(
                    (int) Math.random() * map.Width,
                    (int) Math.random() * map.Height);
            if (map.IsEmpty(coordinates)) {
                Food food = new Food(coordinates);
                map.SetCreatureOnMap(food);
            }
        }
    }

    private void CreateSnakeOnMap()
    {
        Point center = new Point(map.Width / 2, map.Height / 2);
        Point leftDot = new Point(center.x - 1, center.y);
        Point rightDot = new Point(center.x + 1, center.y);
        List<Point> coordinates = Arrays.asList(leftDot, center, rightDot);

        Snake snake = new Snake(rightDot, coordinates);
        map.SetCreatureOnMap(snake);
    }

    public void Start() {

    }
}