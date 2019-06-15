/* Copyright 2019 Justin Ho

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */
package frogger.clone;

import processing.core.PApplet;

public class App extends PApplet {

  private Player player;

  private Game game;

  // ! single vehicle for debugging
  // private Obstacle vehicle;

  public static void main(String[] args) {
    PApplet.main(App.class.getName());
  }

  public void settings() {
    size(1280, 1280);
  }

  public void setup() {
    surface.setResizable(true);

    // ! single vehicle for debugging
    // this.vehicle = new Vehicle(this).setPosition(this.width /2, this.height
    // /2).setSize(this.height/ 5, this.height / 10);

    this.player =
        new Player(this)
            .setPosition(this.width / 2, this.height - (this.height / 20))
            .setSize(this.width / 10, this.height / 10)
            .setColor(255);

    this.game = new Game(this).setNumOfRows(10).setObstaclePerRow(3).setupGame();
  }

  public void draw() {
    this.background(255);
    this.player.render();

    // ! single vehicle for debugging
    // this.vehicle.render();
    // if (this.player.collideWithObstacle(this.vehicle)) {
    //   this.player.setColor(0xFF00FF00);
    // }  else {
    //   this.player.setColor(255);
    // }

    this.game.drawMap(this.player);
  }

  public void keyPressed() {
    if (key == CODED) {
      switch (keyCode) {
        case UP:
          player.move(Player.Direction.UP);
          break;
        case DOWN:
          player.move(Player.Direction.DOWN);
          break;
        case LEFT:
          player.move(Player.Direction.LEFT);
          break;
        case RIGHT:
          player.move(Player.Direction.RIGHT);
          break;
      }
    }

    if (key == 'r' || key == 'R') {
      player.reset();
    }
  }
}
