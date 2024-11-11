class Rocket 
{
  float x, y ;
  
  Rocket() 
    {
      x = width / 2;
      y = height / 2;
    }
  
void update () 
{
    // move rocket with the mouse
    x = mouseX ;
    y = mouseY ;
}
  
void display () 
{
  fill(255) ;
  triangle (x - 10, y + 20, x + 10, y + 20, x, y - 20); // rocket shape
}
}
