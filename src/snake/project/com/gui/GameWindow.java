package snake.project.com.gui;

import snake.project.com.architecture.Game;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {


    public GameWindow(Game game, int cellSize) {
        setTitle("Змейка");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(game.getMap().Width * cellSize, game.getMap().Height * cellSize);
        setLocationRelativeTo(null);
        setResizable(false);
        add(new Layout(game, cellSize));
        addKeyListener(new KeyController(game));
        setVisible(true);
    }

}
