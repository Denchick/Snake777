package snake.project.com.gui;

import snake.project.com.architecture.Game;

import javax.swing.*;

public class GameWindow {


    public GameWindow(Game game) {
        JFrame window = new JFrame();
        window.setTitle("Змейка");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(game.getMap().Width, game.getMap().Height);
        window.setLocation(400, 400);
        window.setResizable(false);

        window.addKeyListener(new KeyController(game));
        window.setVisible(true);
    }
}
