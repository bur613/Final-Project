package com.mysnake.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Snake extends Game {
	SpriteBatch batch;
	int x = 0;
	
	@Override
	public void create () {
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
}
