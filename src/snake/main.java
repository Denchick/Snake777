package snake;

import snake.gui.KeyController;
import snake.gui.Layout;
import snake.gui.GameWindow;
import snake.architecture.Game;

import java.awt.*;

public class main {

  public static final int WIDTH = 22;
  public static final int HEIGHT = 18;
  public static final int CELL = 30;
  public static int TimeSleep = 200;

  public static void main(String[] args) {
    Game game = new Game(WIDTH, HEIGHT);
    GameWindow window = new GameWindow(game, CELL);
    Layout layout = new Layout(game, CELL);
    window.add(BorderLayout.CENTER, layout);
    window.addKeyListener(new KeyController(game));

    while (true) {
      try {
        Thread.sleep(TimeSleep);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (game.isPause() || game.isOver()) {
        try {
          Thread.sleep(TimeSleep);
        } catch (InterruptedException exception) {
          Thread.currentThread().interrupt();
          return;
        } catch (Exception exception) {
          break;
        }
      }
      game.makeOneStep();
      layout.repaint();
    }
  }
}