package com.mysnake.snake;

public class SnakeBody {

  private int x;
  private int y;

  public SnakeBody(int x, int y, int boardSize) {
    if (x == boardSize) {
      x = 36;
      y = 21;
      GameState.death();
    }

    if (y == boardSize - 33) {
      y = 21;
      x = 36;
      GameState.death();
    }

    this.x = x % boardSize;
    this.y = y % boardSize;

    if (this.x < 0) {
      this.x = 36;
      this.y = 21;
      GameState.death();
    }

    if (this.y < 0) {
      this.y = 21;
      this.x = 36;
      GameState.death();
    }
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void deathPos() {
    this.y = 21;
    this.x = 36;
  }
}
