/* Licensed under MIT 2019 */
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
    sketch.fill(128);
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
