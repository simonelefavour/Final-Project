class Player extends GameObject 
{
  private int health = 100 ;
  private float size = 20 ; // rocket triangle 

  Player (float x, float y) 
    {
      super (x, y) ;
    }

void update () 
  {
    x = mouseX ;
    y = mouseY ; 
  }

void display () 
  {
    fill (255) ; // white rocket 
      noStroke() ;
      // triangle up 
      beginShape() ;
      vertex (x, y - size) ; // top 
      vertex (x - size / 2, y + size) ; // bottom left
      vertex (x + size / 2, y + size) ; // bottom right
      endShape (CLOSE) ;
  }

  int getHealth() { return health; }
  void takeDamage() { health -= 10; }
}
