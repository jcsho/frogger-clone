/* Copyright 2019 Justin Ho

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */
package frogger.clone;

import frogger.clone.Obstacles.Obstacle;
import frogger.clone.Obstacles.Vehicle;
import processing.core.PApplet;

public class App extends PApplet {

  private Player player;

  private Obstacle truck;

  public static void main(String[] args) {
    PApplet.main(App.class.getName());
  }

  public void settings() {
    size(1000, 1000);
  }

  public void setup() {
    surface.setResizable(true);

    player =
        new Player(this)
            .setPosition(this.width / 2, this.height - (this.height / 10))
            .setSize(this.width / 10, this.height / 10);

    truck =
        new Vehicle(this)
            .setPosition(0, this.height / 2)
            .setSize(this.width / 10, this.height / 20)
            .setSpeed(3)
            .setResetPosition(width);
  }

  public void draw() {
    this.background(255);
    player.render();

    truck.render();
    truck.move();
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
  }
}
