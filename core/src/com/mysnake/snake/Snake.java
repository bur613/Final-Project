package com.mysnake.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Snake extends Game {
    private Texture mattoonFood;
	private SpriteBatch batch;
	int x = 0;
	
	@Override
	public void create () {
	    mattoonFood = new Texture(Gdx.files.internal("maton.jpg"));
		batch = new SpriteBatch();
		this.setScreen(new Screen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

  public void setMap1Screen() {
    this.setScreen(new Map1(this));
  }

  public void setMap2Screen() {
    this.setScreen(new Map2(this));
  }

  public void setMap3Screen() {
    this.setScreen(new Map3(this));
  }
}
