/* Licensed under MIT 2019 */
package frogger.clone;

import processing.core.PApplet;

public class App extends PApplet {

  private Player player;

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
  }

  public void draw() {
    player.render();
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
