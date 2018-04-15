package com.mysnake.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Screen implements com.badlogic.gdx.Screen {
  private Snake game;
  private int width = 600;
  private int height = 1000;

  private OrthographicCamera camera = new OrthographicCamera(width, height);
  private Viewport viewport;

  public Screen(Snake game) {
    this.game = game;
    camera.setToOrtho(false, width, height);
    viewport = new FitViewport(width, height, camera);
    viewport.apply();
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    camera.update();
    viewport.apply();
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {

  }
}