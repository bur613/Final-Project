package com.mysnake.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class MainMenu implements Screen {

  private Snake game;
  private SpriteBatch sb;
  private Texture maton;

  private BitmapFont titleFont;
  private BitmapFont font;

  private final String title = "Snake 2.0";

  private int currentItem;
  private String[] menuItems;

  public MainMenu(Snake game) {
    this.game = game;

    maton = new Texture(Gdx.files.internal("maton.jpg"));

    sb = new SpriteBatch();

    FreeTypeFontGenerator x = new FreeTypeFontGenerator(
        Gdx.files.internal("fonts/BungeeShade-Regular.ttf"));
    FreeTypeFontParameter y = new FreeTypeFontParameter();

    currentItem = 0;

    y.size = 99;

    titleFont = x.generateFont(y);
    titleFont.setColor(Color.BLACK);

    y.size = 45;

    font = x.generateFont(y);

    menuItems = new String[]{
        "Slow",
        "Medium",
        "Fast",
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

    handleInput();

    sb.begin();

    sb.draw(maton, 510, 188);

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

  private void handleInput() {
    if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
      if (currentItem >= 0 && currentItem != menuItems.length - 1) {
        currentItem++;
      }
    }

    if (Gdx.input.isKeyJustPressed(Keys.UP)) {
      if (currentItem <= menuItems.length - 1 && currentItem != 0) {
        currentItem--;
      }
    }

    if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
      if (currentItem == 0) {
        game.difficulty = 0;
        game.setGameScreen();
      } else if (currentItem == 1) {
        game.difficulty = 1;
        game.setGameScreen();
      } else if (currentItem == 2) {
        game.difficulty = 2;
        game.setGameScreen();
      } else if (currentItem == 3) {
        Gdx.app.exit();
      }
    }
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
