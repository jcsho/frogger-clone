/* Licensed under MIT 2019 */
package frogger.clone;

import processing.core.PApplet;

public class App extends PApplet {
  public static void main(String[] args) {
    PApplet.main(App.class.getName());
  }

  public void settings() {
    size(1000, 1000);
  }

  public void setup() {
    surface.setResizable(true);
  }

  public void draw() {}
}
