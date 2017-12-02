package snake.project.com.gui;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import snake.project.com.Main;
import snake.project.com.architecture.Game;
import snake.project.com.architecture.Point;
import snake.project.com.creatures.ICreature;


import javax.swing.*;
import java.awt.*;
import snake.project.com.creatures.Snake;

public class Layout extends JPanel {

  private final Game game;
  private final int cellSize;

  public Layout(Game game, int cellSize) {
    this.game = game;
    this.cellSize = cellSize;
    setBackground(Color.BLACK);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    for (ICreature creature: game.getMap().creatures) {
     paintCreature(creature, g);
    }

    if (game.isOver()) {
      String str = "Game Over";
      g.setColor(Color.white);
      g.drawString(str, game.getMap().Width * cellSize / 2 - 10 , game.getMap().Height * cellSize / 2 + 10);
    }
  }

  private void paintCreature(ICreature creature, Graphics g) {
    if (creature instanceof Snake) {
      paintSnake((Snake) creature, g);
    } else {
      g.drawImage(creature.getImage(),
          creature.getCoordinates().getX() * Main.CELL,
          creature.getCoordinates().getY() * Main.CELL,
          Main.CELL, Main.CELL, this);
    }
  }

  private void paintSnake(Snake snake, Graphics g) {
    g.drawImage(snake.getHead().getImage(),
        snake.getHeadCoordinates().getX() * Main.CELL,
        snake.getHeadCoordinates().getY() * Main.CELL,
        Main.CELL, Main.CELL, this);
    for (Point bodyCoordinates: snake.getBodyCoordinates() ) {
      g.drawImage(snake.getBody().getImage(),
          bodyCoordinates.getX() * Main.CELL,
          bodyCoordinates.getY() * Main.CELL,
          Main.CELL, Main.CELL, this);
    }
  }

  private BufferedImage rotateImage (BufferedImage imag, int n) { //n rotation in gradians

    double rotationRequired = Math.toRadians (n);
    double locationX = imag.getWidth() / 2;
    double locationY = imag.getHeight() / 2;
    AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
    BufferedImage newImage =new BufferedImage(imag.getWidth(), imag.getHeight(), imag.getType()); //20, 20 is a height and width of imag ofc
    op.filter(imag, newImage);

    //this.img = newImage;
    return(newImage);
  }
}
