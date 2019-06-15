/* Copyright 2019 Justin Ho

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */
package frogger.clone;

import static processing.core.PConstants.CENTER;

import frogger.clone.Obstacles.Obstacle;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/** User controllable character */
public class Player {
  /** Provides access to processing functions */
  private PApplet sketch;
  private PImage characterLeft;
  private PImage characterRight;
  /** X and Y coordinate of the player */
  private PVector position;
  /** X and Y coordinate of new position for linear interpolation */
  private PVector movement;
  /** Copy of position from instantiation */
  private PVector initialPosition;
  /** Amount of lerp */
  private static float lerpAmt = 0.6f;
  /** Horizontal dimension */
  private int width;
  /** Vertical dimension */
  private int height;

  private int color;

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

  public Player setImage(PImage leftImage, PImage rightImage) {
    this.characterLeft = leftImage;
    this.characterRight = rightImage;
    return this;
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
    this.initialPosition = this.position.copy();
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
    // this.sketch.fill(color);
    // this.sketch.stroke(0);
    // this.sketch.ellipse(
    //     this.position.x, this.position.y, this.width, this.height); // use a circle to represent the
    // player
    int reflect = 1;

    if (this.movement.x > this.position.x) {
      reflect = -1;
    } else if (this.movement.x < this.position.x) {
      reflect = 1;
    }

    this.sketch.pushMatrix();
    this.sketch.translate(this.position.x, this.position.y);
    this.sketch.scale(reflect, 1);
    this.sketch.imageMode(CENTER);
    this.sketch.image(this.characterLeft, 0, 0);
    this.sketch.popMatrix();
    this.position.lerp(
        this.movement, lerpAmt); // move the player to the new position if there's a difference
    return this;
  }

  public Player setColor(int color) {
    this.color = color;
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
        if ((this.movement.y + this.height) >= this.sketch.height) return;
        this.movement.add(0, this.height);
        break;
      case UP:
        this.movement.sub(0, this.height);
        break;
      case RIGHT:
        if ((this.movement.x + this.width) >= this.sketch.width) return;
        this.movement.add(this.width, 0);
        break;
      case LEFT:
        if ((this.movement.x - this.width) <= 0) return;
        this.movement.sub(this.width, 0);
        break;
      default:
        break;
    }
  }

  /** Puts the player back at the starting position */
  public void reset() {
    this.movement.set(this.initialPosition);
    this.position.set(this.initialPosition);
  }

  /**
   * Basic box to box collision algorithm
   *
   * @param o - other object to collide against
   * @return true if boxes overlapping
   */
  public boolean collideWithObstacle(Obstacle o) {
    return ((this.position.x + (this.width / 2)) > (o.getX() - (o.getWidth() / 2))
        && (this.position.x - (this.width / 2)) < (o.getX() + (o.getWidth() / 2))
        && (this.position.y + (this.height / 2)) > (o.getY() - (o.getHeight() / 2))
        && (this.position.y - (this.height / 2)) < (o.getY() + (o.getHeight() / 2)));
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

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }
}
