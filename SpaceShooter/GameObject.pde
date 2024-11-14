abstract class GameObject 
{
  protected float x, y ;

  GameObject(float x, float y) 
    {
      this.x = x ;
      this.y = y ;
    }

  abstract void update() ;
  abstract void display() ;

  float getX() { return x ; }
  float getY() { return y ; }
}
