class Projectile 
{
  float x, y ;
  float speedX, speedY ;
  
  // constructor for rocket position
  Projectile(float startX, float startY) 
    {
      x = startX ;
      y = startY ;
      speedX = random (-3, 3) ; 
      speedY = -5 ; 
    }
  
  
void update () 
{
   x += speedX ;
   y += speedY ;
}
  
// display the projectile
void display () 
{
  fill(255, 0, 0) ; // red 
    noStroke () ;
    ellipse (x, y, 10, 10) ; // red circles
}
}
