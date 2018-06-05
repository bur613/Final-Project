package com.mysnake.snake;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Queue;
import java.util.ArrayList;

public class GameState {

  private static int score = 0;
  private static int snakeLength = 3;
  private static int currentMap = 0;
  private int boardSize = 75; // How many squares are on the board
  private float mTimer = 0;
  private ShapeRenderer shapeRenderer = new ShapeRenderer();
  private Queue<SnakeBody> mBody = new Queue<SnakeBody>();
  private Controls controls = new Controls();
  private Food food = new Food(boardSize);
  private Portals portal = new Portals(boardSize);
  private ArrayList<Wall> map1 = new ArrayList<Wall>();
  private ArrayList<Wall> map2 = new ArrayList<Wall>();
  private ArrayList<Wall> map3 = new ArrayList<Wall>();
  private boolean spawnFood = true;


  public GameState() {
    mBody.addLast(new SnakeBody(36, 21, boardSize)); // Head of the Snake
    mBody.addLast(new SnakeBody(36, 20, boardSize));
    mBody.addLast(new SnakeBody(36, 19, boardSize)); // Tail

    for (int i = 5; i < 53; i++) { // Map 1
      map1.add(new Wall(i, 26));
    }

    for (int i = 5; i < 53; i++) { // Map 2
      map2.add(new Wall(i, 26));
    }

    for (int i = 5; i < 35; i++) { // Map 2
      map2.add(new Wall(54, i));
    }

    for (int i = 5; i < 53; i++) { // Map 3
      map3.add(new Wall(i, 26));
    }

    for (int i = 5; i < 35; i++) { // Map 3
      map3.add(new Wall(54, i));
    }

    for (int i = 7; i < 18; i++) { // Map 3
      map3.add(new Wall(1, i));
    }

  }

  public static void death() {
    snakeLength = 3;
    score = 0;
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

      if (currentMap == 1) {
        for (Wall x : map1) {
          if (x.getX() == food.getX() && x.getY() == food.getY()) {
            food.randPos(boardSize);
          }
        }
      } else if (currentMap == 2) {
        for (Wall x : map2) {
          if (x.getX() == food.getX() && x.getY() == food.getY()) {
            food.randPos(boardSize);
          }
        }
      } else if (currentMap == 3) {
        for (Wall x : map3) {
          if (x.getX() == food.getX() && x.getY() == food.getY()) {
            food.randPos(boardSize);
          }
        }
      }
    }

    if (mBody.first().getX() == portal.getX() && mBody.first().getY() == portal.getY()) {
      spawnFood = true;
      currentMap++;
    }

    for (Wall x : map1) {
      if (mBody.first().getX() == x.getX() && mBody.first().getY() == x.getY() && currentMap == 1) {
        spawnFood = true;
        score = 0;
        snakeLength = 3;
        mBody.first().deathPos();
      }
    }

    for (Wall x : map2) {
      if (mBody.first().getX() == x.getX() && mBody.first().getY() == x.getY() && currentMap == 2) {
        spawnFood = true;
        score = 0;
        snakeLength = 3;
        mBody.first().deathPos();
      }
    }

    for (Wall x : map3) {
      if (mBody.first().getX() == x.getX() && mBody.first().getY() == x.getY() && currentMap == 3) {
        spawnFood = true;
        score = 0;
        snakeLength = 3;
        mBody.first().deathPos();
      }
    }

    //Death Process
    for (int i = 1; i < mBody.size; i++) {
      if (mBody.get(i).getX() == mBody.first().getX() && mBody.get(i).getY() == mBody.first()
          .getY() || mBody.get(i).getX() == boardSize || mBody.get(i).getX() < 0
          || mBody.get(i).getY() == boardSize - 33 || mBody.get(i).getY() < 0) {
        snakeLength = 3;
        score = 0;
        mBody.first().deathPos();
      }
    }

    while (mBody.size - 1 >= snakeLength) {
      mBody.removeLast();
    }

    if (score == 2 && currentMap != 3) {
      portal.randPos(boardSize);

      if (currentMap == 1) {
        for (Wall x : map1) {
          if (x.getX() == portal.getX() && x.getY() == portal.getY()) {
            portal.randPos(boardSize);
          }
        }
      } else if (currentMap == 2) {
        for (Wall x : map2) {
          if (x.getX() == portal.getX() && x.getY() == portal.getY()) {
            portal.randPos(boardSize);
          }
        }
      } else if (currentMap == 3) {
        for (Wall x : map3) {
          if (x.getX() == portal.getX() && x.getY() == portal.getY()) {
            portal.randPos(boardSize);
          }
        }
      }

      spawnFood = false;
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

    if (spawnFood) {
      shapeRenderer.setColor(1, 0, 0, 1);
      shapeRenderer
          .rect(food.getX() * scaleSnake, food.getY() * scaleSnake, scaleSnake, scaleSnake);
    }

    if (currentMap == 1) {
      for (Wall x : map1) {
        shapeRenderer.setColor(0, 0, 1, 1);
        shapeRenderer
            .rect(x.getX() * scaleSnake, x.getY() * scaleSnake, scaleSnake,
                scaleSnake);
      }
    }

    if (currentMap == 2) {
      for (Wall x : map2) {
        shapeRenderer.setColor(0, 0, 1, 1);
        shapeRenderer
            .rect(x.getX() * scaleSnake, x.getY() * scaleSnake, scaleSnake,
                scaleSnake);
      }
    }

    if (currentMap == 3) {
      for (Wall x : map3) {
        shapeRenderer.setColor(0, 0, 1, 1);
        shapeRenderer
            .rect(x.getX() * scaleSnake, x.getY() * scaleSnake, scaleSnake,
                scaleSnake);
      }
    }

    if (!spawnFood) {
      shapeRenderer.setColor(0, 1, 0, 1);
      shapeRenderer
          .rect(portal.getX() * scaleSnake, portal.getY() * scaleSnake, scaleSnake, scaleSnake);
      score = 0;
    }

    shapeRenderer.setColor(1, 1, 1, 1);
    for (SnakeBody sb : mBody) {
      shapeRenderer.rect(sb.getX() * scaleSnake, sb.getY() * scaleSnake, scaleSnake, scaleSnake);
    }

    shapeRenderer.end();
  }
}
