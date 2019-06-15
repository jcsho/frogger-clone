/* Copyright 2019 Justin Ho

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */
package frogger.clone.Obstacles;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public abstract class Obstacle {

  protected PApplet sketch;
  protected PVector position;
  protected PImage image;
  protected int width;
  protected int height;
  protected int speed;
  protected int initialPosition;
  protected int resetPosition;

  public Obstacle(PApplet sketch) {
    this.sketch = sketch;
  }

  public Obstacle setPosition(int x, int y) {
    this.position = new PVector(x, y);
    this.initialPosition = x;
    return this;
  }

  public Obstacle setSize(int width, int height) {
    this.width = width;
    this.height = height;
    return this;
  }

  public Obstacle setSpeed(int speed) {
    this.speed = speed;
    return this;
  }

  public Obstacle setResetPosition(int position) {
    this.resetPosition = position;
    return this;
  }

  public Obstacle setImage(PImage image) {
    this.image = image;
    return this;
  }

  public int getX() {
    return (int) this.position.x;
  }

  public int getY() {
    return (int) this.position.y;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public void move() {
    this.position.x += this.speed;
    if (this.initialPosition > this.resetPosition && this.position.x < this.resetPosition) {
      this.position.x = this.initialPosition;
      return;
    } else if (this.initialPosition < this.resetPosition && this.position.x > this.resetPosition) {
      this.position.x = this.initialPosition;
    }
  }

  public abstract void render();
}
