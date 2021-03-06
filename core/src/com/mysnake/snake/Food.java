package com.mysnake.snake;

import com.badlogic.gdx.math.MathUtils;

public class Food {

  private int x;
  private int y;

  public Food(int boardSize) {
    randPos(boardSize);
  }

  public void randPos(int boardSize) {
    x = MathUtils.random(1, boardSize - 2);
    y = MathUtils.random(1, boardSize - 34);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
