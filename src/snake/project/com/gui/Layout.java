package snake.project.com.gui;

import snake.project.com.architecture.Game;
import snake.project.com.creatures.Food;
import snake.project.com.creatures.ICreature;
import snake.project.com.creatures.Snake;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Layout extends JPanel{

    private final Game game;
    private final int cellSize;

    public Layout(Game game, int cellSize) {
        this.game = game;
        this.cellSize = cellSize;
        setBackground(Color.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(game.isOver()){
            String str = "Game Over";
            g.setColor(Color.white);
            g.drawString(str,125,game.getMap().Width / 2);
        } else {
            Food food = game.GetFood();
            List<Point> snakeCoordinates = game.GetSnake().getListCoordinates();
            g.setColor(Color.RED);
            g.fillRect(food.getCoordinates().x, food.getCoordinates().y, cellSize, cellSize);
            for (Point coordinate : snakeCoordinates) {
                g.fillRect(coordinate.x, coordinate.y, cellSize, cellSize);
            }
        }
    }
}
