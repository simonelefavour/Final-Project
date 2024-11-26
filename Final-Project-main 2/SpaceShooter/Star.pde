class Star 
{
  float x, y ;
  float speedX, speedY ;
  float size ;
  float brightness ;
  color starColor ;

  Star (float x, float y) 
    {
      this.x = x ;
      this.y = y ;
      this.size = random (3, 8); // random size 3-8
      this.brightness = random (150, 255) ; // random twinkling 
      speedX = random (-0.5, 0.5) ;
      speedY = random (-0.5, 0.5) ;
    
      // yellow or white
      starColor = (random(1) > 0.5) ? color(255, 255, 0) : color(255) ; 
    }

void update () 
  {
    // position update
    x += speedX ;
    y += speedY ;

    // bouncing off edges
    if (x < 0 || x > width) speedX *= -1 ;
    if (y < 0 || y > height) speedY *= -1 ;

    // brightness twinkling 
    brightness = 150 + 100 * sin(millis() * 0.005 + random(0, TWO_PI)) ; // sine wave oscillation, .005
  }

void display () 
  {
    fill (starColor, brightness); // star color for twinking 
    noStroke () ;

    // draw star
    pushMatrix () ;
    translate (x, y) ;
    beginShape () ;
    for (int i = 0; i < 10; i++) // loop 
      {
        float angle = TWO_PI / 10 * i ; // angle calculation 
        float radius = (i % 2 == 0) ? size : size / 2 ; 
        float xOuter = cos(angle) * radius ;
        float yOuter = sin(angle) * radius ;
        vertex(xOuter, yOuter) ;
      } // end for 
      
    endShape (CLOSE) ;
    popMatrix () ;
  }
}
