package snake.project.com.gui;

import snake.project.com.architecture.Direction;
import snake.project.com.architecture.Game;
import snake.project.com.creatures.Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyController implements KeyListener {

  private Game game;
  private Snake snake;

  public KeyController(Game game) {
    this.game = game;
    this.snake = game.getSnake();
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        if (snake.getDirection() != Direction.Down){
          //game.snakeDirection = Direction.Up;
          snake.setDirection(Direction.Up);
        }
        break;
      case KeyEvent.VK_DOWN:
        if(snake.getDirection() != Direction.Up) {
          //game.snakeDirection = Direction.Down;
          snake.setDirection(Direction.Down);
        }
        break;
      case KeyEvent.VK_LEFT:
        if (snake.getDirection() != Direction.Right) {
          //game.snakeDirection = Direction.Left;
            snake.setDirection(Direction.Left);
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (snake.getDirection() != Direction.Left){
          //game.snakeDirection = Direction.Right;
            snake.setDirection(Direction.Right);
        }
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
