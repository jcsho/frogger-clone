/* Copyright 2019 Justin Ho

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */
package frogger.clone;

import frogger.clone.Obstacles.Obstacle;
import frogger.clone.Obstacles.Vehicle;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class Game {
  private PApplet sketch;
  private ArrayList<Obstacle> obstacles;
  private int numOfRows;
  private int obstaclePerRow;
  private int obstacleHeight;
  private int obstacleWidth;

  private PImage truck;
  private PImage map;

  private static int SECTION = 3;

  public Game(PApplet sketch) {
    this.sketch = sketch;
  }

  public Game setMap(PImage image) {
    this.map = image;
    return this;
  }

  public Game setNumOfRows(int amount) {
    this.numOfRows = amount;
    this.obstacleHeight = this.sketch.height / this.numOfRows;
    this.obstacleWidth = 2 * this.obstacleHeight;
    return this;
  }

  public Game setObstaclePerRow(int amount) {
    this.obstaclePerRow = amount;
    return this;
  }

  public Game setupGame() {
    this.truck = this.sketch.loadImage("truck-left.png");
    this.obstacles = new ArrayList<Obstacle>(this.numOfRows * this.obstaclePerRow);
    for (int row = 1; row < numOfRows - 1; row++) {
      if ((row % SECTION) != 0) {
        int rand = (int) this.sketch.random(1, 3);
        int startPosition = 0 - (this.obstacleWidth);
        int resetPosition = this.sketch.width + this.obstacleWidth;
        int direction = rand;
        int interval = this.obstacleWidth * 3;

        if ((row % 2) == 0) {
          startPosition = this.sketch.width + this.obstacleWidth;
          resetPosition = 0 - (this.obstacleWidth);
          direction = -direction;
          interval = -interval;
        }

        for (int obstacle = 0; obstacle < obstaclePerRow; obstacle++) {
          this.obstacles.add(
              new Vehicle(this.sketch)
                  .setPosition(
                      startPosition - (interval * obstacle),
                      (row * this.obstacleHeight) + (this.obstacleHeight / 2))
                  .setSize(this.truck.width, this.truck.height)
                  .setImage(this.truck)
                  .setResetPosition(resetPosition)
                  .setSpeed(direction));
        }
      }
    }

    return this;
  }

  public void render(Player player) {
    
    this.sketch.imageMode(PApplet.CENTER);
    this.sketch.image(map, this.sketch.width / 2, this.sketch.height / 2);

    for (int obstacle = 0; obstacle < obstacles.size(); obstacle++) {
      Obstacle o = this.obstacles.get(obstacle);
      o.render();
      o.move();
      if (player.collideWithObstacle(o)) {
        player.reset();
      }
    }
  }
}
