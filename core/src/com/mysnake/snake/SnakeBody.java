package com.mysnake.snake;

public class SnakeBody {

  private int x;
  private int y;

  public SnakeBody(int x, int y, int boardSize) {
    if (x == boardSize) {
      x = 36;
      y = 21;
    }

    if (y == boardSize - 33) {
      y = 21;
      x = 36;
    }

    this.x = x % boardSize;
    this.y = y % boardSize;

    if (this.x < 0) {
      this.x = 36;
      this.y = 21;
    }

    if (this.y < 0) {
      this.y = 21;
      this.x = 36;
    }
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
