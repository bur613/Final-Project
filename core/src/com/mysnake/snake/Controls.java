package com.mysnake.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

public class Controls {

  private int currentDirection; // 0,1,2,3 == U,R,L,D
  private int nextDirection;

  public int getDirection() {
    currentDirection = nextDirection;
    return nextDirection;
  }

  public void update() {
    if ((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Keys.W))
        && currentDirection != 2) {
      nextDirection = 0;
    } else if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D))
        && currentDirection != 3) {
      nextDirection = 1;
    } else if ((Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S))
        && currentDirection != 0) {
      nextDirection = 2;
    } else if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A))
        && currentDirection != 1) {
      nextDirection = 3;
    }
  }
}
