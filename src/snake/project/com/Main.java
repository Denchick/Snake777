package snake.project.com;

import snake.project.com.gui.GameWindow;
import snake.project.com.architecture.Game;
import snake.project.com.gui.Layout;

import java.awt.*;

public class Main {

    private static final int WIDTH = 32;
    private static final int HEIGHT = 24;
    private static final int CELL = 20;

    public static void main(String[] args) {
        Game game = new Game(WIDTH, HEIGHT);
        GameWindow window = new GameWindow(game, CELL);
        Layout layout = new Layout(game, CELL);
        window.getContentPane().add(BorderLayout.CENTER, layout);

        while (true) {
            if (game.isPause || game.isOver())
                game.oneStep();
            layout.repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                return;
            } catch (Exception exception) {
                break;
            }
        }
    }
}