/* Copyright 2019 Justin Ho

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */
package frogger.clone;

import processing.core.PApplet;
import processing.core.PVector;

/** User controllable character */
public class Player {
  /** Provides access to processing functions */
  private PApplet sketch;
  /** X and Y coordinate of the player */
  private PVector position;
  /** X and Y coordinate of new position for linear interpolation */
  private PVector movement;
  /** Amount of lerp */
  private static float lerpAmt = 0.6f;
  /** Horizontal dimension */
  private int width;
  /** Vertical dimension */
  private int height;

  /** The directions that the player can move in */
  public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }

  /**
   * Default Constructor
   *
   * @param sketch - main Processing instance
   */
  public Player(PApplet sketch) {
    this.sketch = sketch;
  }

  /**
   * Set the position of the center of the player
   *
   * @param x - horizontal dimension
   * @param y - vertical dimension
   * @return an instance of {@link Player}
   */
  public Player setPosition(int x, int y) {
    this.position = new PVector(x, y);
    this.movement = this.position.copy();
    return this;
  }

  /**
   * Set the bounds of the player shape
   *
   * @param width - horizontal size
   * @param height - vertical size
   * @return an instance of {@link Player}
   */
  public Player setSize(int width, int height) {
    this.width = width;
    this.height = height;
    return this;
  }

  /**
   * Draws player graphic onto sketch frame
   *
   * @return an instance of {@link Player}
   */
  public Player render() {
    sketch.ellipse(
        this.position.x,
        this.position.y,
        this.width,
        this.height); // use a circle to represent the player
    this.position.lerp(
        this.movement, lerpAmt); // move the player to the new position if there's a difference
    return this;
  }

  /**
   * Moves player through lerping
   *
   * @param direction - where its going to move to
   */
  public void move(Direction direction) {
    switch (direction) {
      case DOWN:
        this.movement.add(0, this.height);
        break;
      case UP:
        this.movement.sub(0, this.height);
        break;
      case RIGHT:
        this.movement.add(this.width, 0);
        break;
      case LEFT:
        this.movement.sub(this.width, 0);
        break;
      default:
        break;
    }
  }

  // ==================================================
  // ACCESSORS
  // ==================================================

  public int getX() {
    return (int) this.position.x;
  }

  public int getY() {
    return (int) this.position.y;
  }
}
