package com.mysnake.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Snake extends Game {

  int x = 0;
  private SpriteBatch batch;
  public static int difficulty = 1;

  @Override
  public void create() {
    batch = new SpriteBatch();
    this.setScreen(new MainMenu(this));
  }

  public void setGameScreen() {
    this.setScreen(new Screen(this));
  }

  @Override
  public void render() {
    super.render();
  }

  @Override
  public void dispose() {
    batch.dispose();
  }
}
