package com.mysnake.snake;

import com.badlogic.gdx.math.MathUtils;

public class Portals {

  private int x;
  private int y;

  public Portals(int boardSize) {
    randPos(boardSize);
  }

  public void randPos(int boardSize) {
    x = MathUtils.random(boardSize - 2);
    y = MathUtils.random(boardSize - 34);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}

