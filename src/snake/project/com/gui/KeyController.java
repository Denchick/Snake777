package snake.project.com.gui;

import snake.project.com.architecture.Direction;
import snake.project.com.architecture.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyController implements KeyListener {

  private Game game;

  public KeyController(Game game) {
    this.game = game;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        if (game.lastDirection != Direction.Down){
          game.snakeDirection = Direction.Up;
        }
        break;
      case KeyEvent.VK_DOWN:
        if(game.lastDirection != Direction.Up) {
          game.snakeDirection = Direction.Down;
        }
        break;
      case KeyEvent.VK_LEFT:
        if (game.lastDirection != Direction.Right) {
          game.snakeDirection = Direction.Left;
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (game.lastDirection != Direction.Left){
          game.snakeDirection = Direction.Right;
        }
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
