class Projectile extends GameObject 
{
  private float speedY = -5 ;

  Projectile (float x, float y) 
  {
    super (x, y) ;
  }

void update () 
  {
    y += speedY ;
  }

void display () 
  {
    fill (255, 255, 0) ;
    ellipse (x, y, 10, 10) ;
  }

boolean isOutOfBounds() 
  {
    return y < 0 ;
  }

boolean collidesWith (GameObject obj) 
  {
    return dist (x, y, obj.getX(), obj.getY()) < 20 ;
  }
}
