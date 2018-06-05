package com.mysnake.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
}
