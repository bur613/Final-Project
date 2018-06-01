package com.mysnake.snake;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Queue;

public class GameState {

  private int boardSize = 75; // How many squares are on the board
  private float mTimer = 0;
  private ShapeRenderer shapeRenderer = new ShapeRenderer();
  private Queue<SnakeBody> mBody = new Queue<SnakeBody>();
  private Controls controls = new Controls();
  private Food food = new Food(boardSize);
  private ArrayList<Wall> walls = new ArrayList<Wall>();
  private int score = 0;
  private int snakeLength = 3;

  public GameState() {
    mBody.addLast(new SnakeBody(36, 21, boardSize)); // Head of the Snake
    mBody.addLast(new SnakeBody(36, 20, boardSize));
    mBody.addLast(new SnakeBody(36, 19, boardSize)); // Tail
  }

  public void update(float delta) {
    controls.update();
    mTimer += delta;

    if (mTimer > 0.13f) {
      mTimer = 0;
      advance();
    }
  }

  private void advance() {
    int headX = mBody.first().getX();
    int headY = mBody.first().getY();

    switch (controls.getDirection()) {
      case 0: // Up
        mBody.addFirst(new SnakeBody(headX, headY + 1, boardSize));
        break;

      case 1: // Right
        mBody.addFirst(new SnakeBody(headX + 1, headY, boardSize));
        break;

      case 2: // Down
        mBody.addFirst(new SnakeBody(headX, headY - 1, boardSize));
        break;

      case 3: // Left
        mBody.addFirst(new SnakeBody(headX - 1, headY, boardSize));
        break;

      default:
        mBody.addFirst(new SnakeBody(headX, headY + 1, boardSize));
        break;
    }

    if (mBody.first().getX() == food.getX() && mBody.first().getY() == food.getY()) {
      snakeLength++;
      score++;
      food.randPos(boardSize);
    }

    //Death Process
    for (int i = 1; i < mBody.size; i++) {
      if (mBody.get(i).getX() == mBody.first().getX() && mBody.get(i).getY() == mBody.first()
          .getY() || mBody.get(i).getX() == boardSize || mBody.get(i).getX() < 0
          || mBody.get(i).getY() == boardSize - 33 || mBody.get(i).getY() < 0) {
        snakeLength = 3;
        score = 0;
      }
    }

    while (mBody.size - 1 >= snakeLength) {
      mBody.removeLast();
    }
  }

  public void draw(int width, int height, OrthographicCamera camera) {
    shapeRenderer.setProjectionMatrix(camera.combined);
    shapeRenderer.begin(ShapeType.Filled);

    shapeRenderer.setColor(1, 1, 1, 1);
    shapeRenderer.rect(0, 0, width, height);

    shapeRenderer.setColor(0, 0, 0, 1);
    shapeRenderer.rect(0 + 5, 0 + 5, width - 5 * 2, height - 5 * 2);

    shapeRenderer.setColor(1, 1, 1, 1);
    float scaleSnake = width / boardSize;

    shapeRenderer.setColor(1, 0, 0, 1);
    shapeRenderer.rect(food.getX() * scaleSnake, food.getY() * scaleSnake, scaleSnake, scaleSnake);

    shapeRenderer.setColor(1, 1, 1, 1);
    for (SnakeBody sb : mBody) {
      shapeRenderer.rect(sb.getX() * scaleSnake, sb.getY() * scaleSnake, scaleSnake, scaleSnake);
    }

    shapeRenderer.end();
  }
}
