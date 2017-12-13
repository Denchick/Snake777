package snake.gui;

import snake.architecture.Direction;
import snake.architecture.Game;
import snake.creatures.EnemySnake;
import snake.creatures.GoodSnake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyController implements KeyListener {

  private Game game;
  private GoodSnake goodSnake;
  private EnemySnake enemySnake;

  public KeyController(Game game) {
    this.game = game;
    this.goodSnake = game.getCreatureFromMap(GoodSnake.class);
    this.enemySnake = game.getCreatureFromMap(EnemySnake.class);
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
      if (enemySnake.getDirection() != Direction.Down){
        enemySnake.setDirection(Direction.Up);
      }
      break;
      case KeyEvent.VK_S:
        if(enemySnake.getDirection() != Direction.Up) {
          enemySnake.setDirection(Direction.Down);
        }
        break;
      case KeyEvent.VK_A:
        if (enemySnake.getDirection() != Direction.Right) {
          enemySnake.setDirection(Direction.Left);
        }
        break;
      case KeyEvent.VK_D:
        if (enemySnake.getDirection() != Direction.Left){
          enemySnake.setDirection(Direction.Right);
        }
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
