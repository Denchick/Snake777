package snake.gui;

import snake.architecture.Direction;
import snake.architecture.Game;
import snake.creatures.Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyController implements KeyListener {

  private Game game;
  private Snake snake;

  public KeyController(Game game) {
    this.game = game;
    this.snake = game.getCreatureFromMap(Snake.class);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        if (snake.getDirection() != Direction.Down){
          snake.setDirection(Direction.Up);
        }
        break;
      case KeyEvent.VK_DOWN:
        if(snake.getDirection() != Direction.Up) {
          snake.setDirection(Direction.Down);
        }
        break;
      case KeyEvent.VK_LEFT:
        if (snake.getDirection() != Direction.Right) {
            snake.setDirection(Direction.Left);
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (snake.getDirection() != Direction.Left){
            snake.setDirection(Direction.Right);
        }
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
