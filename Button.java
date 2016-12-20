/* 
 * Button class will define a rectangle that will change color if mouse is in it
 */

import processing.core.PApplet;   //included the PApplet import

public class Button {
  /////////////////////////////fields////////////////////////////
  //x position of button
  private int xLocation;
  //y position of button
  private int yLocation;
  //width of button
  private int Width;
  //height of button
  private int Height;
  //boolean variable asking if the mouse is in the button
  private boolean inButton;
  //String variable labeling the button
  private String label;
  /////////////////////////////constructors/////////////////////

  /**
   *A constructor that takes the x, y, w, and h variables
   *PApplet a passed from main tab
   *
   */
  public Button(int xLocation, int yLocation, int Width, int Height, String label, PApplet p) {
    this.xLocation=xLocation;
    this.yLocation=yLocation;
    this.Width=Width;
    this.Height=Height;
    //If mouse is in the button, the boolean variable is true
    if (p.mouseX>xLocation-Width/2 && p.mouseX<xLocation+Width/2 && p.mouseY>yLocation-Height/2 && p.mouseY<yLocation+Height/2) {
      this.inButton=true;
    } 
    this.label=label;
  }

  //////////////////////////Methods//////////////////////////

  /**
   * A method that draws the button
   *@param a the PApplet object passed from main tab
   */

  public void drawButton(PApplet p) {
    p.rectMode(p.CENTER);
    p.stroke(0);
    p.strokeWeight(1);
    p.fill(255);
    if (this.inButton) {
      p.fill(0, 255, 0);
    }
    p.rect(this.xLocation, this.yLocation, this.Width, this.Height);
    p.textSize(16);
    p.fill(0);
    p.textAlign(p.CENTER, p.CENTER);
    p.text(this.label, this.xLocation, this.yLocation);
  }
 
  //get method for inButton
  public boolean getinButton() {
    return this.inButton;
  }
}