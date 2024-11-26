class Enemy extends GameObject 
{
  private int health = 50 ;

  Enemy (float x, float y) 
  {
    super(x, y);
  } 

  void update () 
  {
  }

  void display () 
  {
    fill (255, 0, 0) ;
    rectMode (CENTER) ;
    rect (x, y, 30, 30) ; // enemy rectangle red  
  }

  int getHealth () { return health ; }
  void takeDamage() { health -= 10 ; }
}
