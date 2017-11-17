package snake.project.com;

import snake.project.com.gui.GameWindow;
import snake.project.com.gui.Gui;
import snake.project.com.architecture.Game;
import snake.project.com.gui.KeyController;

import javax.swing.*;

public class Main {

    private static final int MAIN_FRAME_WIDTH = 640;
    private static final int MAIN_FRAME_HEIGHT = 480;

    public static void main(String[] args) {
        Game game = new Game(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        GameWindow window = new GameWindow(game);
        game.Start();
    }
}