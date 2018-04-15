package com.mysnake.snake;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Queue;

public class GameState {

  private int boardSize = 30; // How many squares are on the board
  private float mTimer = 0;

  private ShapeRenderer shapeRenderer = new ShapeRenderer();

  private Queue<SnakeBody> mBody = new Queue<SnakeBody>();

  public GameState() {
    mBody.addLast(new SnakeBody(15, 15, boardSize)); // Head of the Snake
    mBody.addLast(new SnakeBody(15, 14, boardSize));
    mBody.addLast(new SnakeBody(15, 13, boardSize)); // Tail
  }

  public void update(float delta) {
    mTimer += delta;

    if (mTimer > 0.13f) {
      mTimer = 0;
      advance();
    }
  }

  private void advance() {
    mBody.addFirst(new SnakeBody(mBody.first().getX(), mBody.first().getY() + 1, boardSize));
    mBody.removeLast();
  }

  public void draw(int width, int height, OrthographicCamera camera) {
    shapeRenderer.setProjectionMatrix(camera.combined);
    shapeRenderer.begin(ShapeType.Filled);

    shapeRenderer.setColor(1, 1, 1, 1);

    float scaleSnake = width / boardSize;
    for (SnakeBody sb : mBody) {
      shapeRenderer.rect(sb.getX() * scaleSnake, sb.getY() * scaleSnake, scaleSnake, scaleSnake);
    }

    shapeRenderer.end();
  }
}
