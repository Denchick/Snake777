package snake.gui;

import snake.architecture.Direction;
import snake.architecture.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import snake.creatures.Snake;


public class KeyController implements KeyListener {

  private Snake goodSnake;
  private Snake secondSnake;

  public KeyController(Game game) {
    this.goodSnake = game.getCreatureFromMap(Snake.class, 0);
    this.secondSnake = game.getCreatureFromMap(Snake.class, 1);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        if (goodSnake.getDirection() != Direction.Down){
          goodSnake.setDirection(Direction.Up);
        }
        break;
      case KeyEvent.VK_DOWN:
        if(goodSnake.getDirection() != Direction.Up) {
          goodSnake.setDirection(Direction.Down);
        }
        break;
      case KeyEvent.VK_LEFT:
        if (goodSnake.getDirection() != Direction.Right) {
            goodSnake.setDirection(Direction.Left);
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (goodSnake.getDirection() != Direction.Left){
            goodSnake.setDirection(Direction.Right);
        }
        break;
      case KeyEvent.VK_W:
      if (secondSnake.getDirection() != Direction.Down){
        secondSnake.setDirection(Direction.Up);
      }
      break;
      case KeyEvent.VK_S:
        if(secondSnake.getDirection() != Direction.Up) {
          secondSnake.setDirection(Direction.Down);
        }
        break;
      case KeyEvent.VK_A:
        if (secondSnake.getDirection() != Direction.Right) {
          secondSnake.setDirection(Direction.Left);
        }
        break;
      case KeyEvent.VK_D:
        if (secondSnake.getDirection() != Direction.Left){
          secondSnake.setDirection(Direction.Right);
        }
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
