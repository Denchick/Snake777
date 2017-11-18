package snake.project.com.architecture;

import snake.project.com.creatures.Snake;
import snake.project.com.creatures.Food;
import snake.project.com.creatures.ICreature;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Game
{
    private Map map;
    private boolean isOver;
    public Direction snakeDirection;
    public boolean isPause;

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
                break;
            }
        }
    }

    private void CreateSnakeOnMap()
    {
        Point center = new Point(map.Width / 2, map.Height / 2);
        Point leftDot = new Point(center.x - 1, center.y);
        Point rightDot = new Point(center.x + 1, center.y);
        List<Point> coordinates =  Arrays.asList(leftDot, center, rightDot);

        Snake snake = new Snake(rightDot, coordinates);
        map.SetCreatureOnMap(snake);
    }
    
    public Snake GetSnake() {
        for (ICreature creature : map.Creatures) {
            if (creature instanceof Snake)
                return (Snake) creature;
        }
        return null;
    }

    public Food GetFood() {
        for (ICreature creature : map.Creatures) {
            if (creature instanceof Food)
                return (Food) creature;
        }
        return null;
    }

    public boolean isOver() {
        return isOver;
    }

    public void oneStep() {
        if (isOver) return;
        Snake snake = GetSnake();
        if (!isFoodExists()) {
            CreateFoodOnMap();
        }
        if (CollisionsExists()) {
            isOver = true;
            return;
        }
        snake.makeMove(snakeDirection);
    }

    private boolean CollisionsExists() {
        List<Point> snakeCoordinates = GetSnake().getListCoordinates();
        for (int i = 0; i < snakeCoordinates.size(); i++) {
            for (int j = 0; j < snakeCoordinates.size(); j++) {
                if (i != j && snakeCoordinates.get(i) == snakeCoordinates.get(j))
                    return true;
            }
        }
        return false;
    }

    private boolean isFoodExists() {
        return GetFood() != null;
    }
}