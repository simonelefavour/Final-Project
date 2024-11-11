class Star 
{
  float x, y, speedX, speedY, size ;
  color starColor ;
  
  Star () 
  {
    // outside the center area where text is displayed
    x = random (width) ;
    y = random (height) ;
    
    while (y > height / 3 && y < 2 * height / 3) 
    {
      x = random (width) ;
      y = random (height) ;
    }

    speedX = random (-2, 2) ;
    speedY = random (-2, 2) ;
    size = random (3, 8) ; // smaller stars
    
    // random white and yellow
    if (random(1) > 0.5) 
    {
      starColor = color(255, 255, 0) ; // yellow
    } 
    else 
    {
      starColor = color(255) ; //white
    }
  }
  
  void update() {
    x += speedX;
    y += speedY;
    
    // Wrap around the screen
    if (x > width) x = 0;
    if (x < 0) x = width;
    if (y > height) y = 0;
    if (y < 0) y = height;
  }
  
  void display() 
  {
    noStroke () ;
    fill (starColor) ;
    drawStar(x, y, size / 2, size, 5) ; // star
  }
}

// draw a star
void drawStar(float x, float y, float radius1, float radius2, int npoints) 
{
  float angle = TWO_PI / npoints ;
  float halfAngle = angle / 2.0 ;
  beginShape () ;
  for (float a = -PI/2; a < TWO_PI; a += angle) 
  {
    float sx = x + cos(a) * radius2 ;
    float sy = y + sin(a) * radius2 ;
    vertex (sx, sy) ;
    sx = x + cos (a + halfAngle) * radius1 ;
    sy = y + sin (a + halfAngle) * radius1 ;
    vertex (sx, sy) ;
  }
  endShape (CLOSE) ;
}
