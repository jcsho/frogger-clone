/* Copyright 2019 Justin Ho

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */
package frogger.clone.Obstacles;

import static processing.core.PConstants.CENTER;

import processing.core.PApplet;

public class Vehicle extends Obstacle {

  public Vehicle(PApplet sketch) {
    super(sketch);
  }

  @Override
  public void render() {
    // this.sketch.rectMode(CENTER);
    // this.sketch.rect(this.position.x, this.position.y, this.width, this.height);

    this.sketch.pushMatrix();
    this.sketch.translate(this.position.x, this.position.y);
    if (this.speed > 0) {
      this.sketch.scale(-1, 1);
    } else {
      this.sketch.scale(1, 1);
    }
    this.sketch.imageMode(CENTER);
    this.sketch.image(this.image, 0, 0);
    this.sketch.popMatrix();
  }
}
