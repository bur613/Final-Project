package com.mysnake.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class MainMenu implements Screen {

  private Game game;
  private int difficulty = 1;
  private SpriteBatch sb;
  private Controls keys = new Controls();

  private BitmapFont titleFont;
  private BitmapFont font;

  private final String title = "Snake 2.0";

  private int currentItem;
  private String[] menuItems;

  public MainMenu(Snake game) {
    this.game = game;

    sb = new SpriteBatch();

    FreeTypeFontGenerator x = new FreeTypeFontGenerator(
        Gdx.files.internal("fonts/BungeeShade-Regular.ttf"));
    FreeTypeFontParameter y = new FreeTypeFontParameter();

    y.size = 99;

    titleFont = x.generateFont(y);
    titleFont.setColor(Color.BLACK);

    y.size = 45;

    font = x.generateFont(y);

    menuItems = new String[]{
        "Play",
        "Difficulty",
        "Quit"
    };
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    sb.begin();

    titleFont.draw(sb, title, 720 / 2, 550);

    for (int i = 0; i < menuItems.length; i++) {
      if (currentItem == i) {
        font.setColor(Color.RED);
      } else {
        font.setColor(Color.BLACK);
      }
      font.draw(sb, menuItems[i], 0, 180 - 35 * i);
    }

    sb.end();
  }

  @Override
  public void resize(int width, int height) {

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
