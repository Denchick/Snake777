package snake.gui;

import snake.architecture.Direction;
import snake.architecture.Game;
import snake.creatures.GoodSnake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyController implements KeyListener {

  private Game game;
  private GoodSnake goodSnake;

  public KeyController(Game game) {
    this.game = game;
    this.goodSnake = game.getCreatureFromMap(GoodSnake.class);
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
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
