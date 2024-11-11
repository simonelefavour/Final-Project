int screenState = 0 ; 
boolean showText = true ; // blinking text
int lastToggleTime = 0 ;

ArrayList<Star> stars = new ArrayList<Star>() ;
Rocket rocket;
ArrayList<Projectile> projectiles = new ArrayList<Projectile>() ; // list to hold projectiles

void setup () 
{
  size(600, 600);
  // moving stars
  for (int i = 0; i < 100; i++) 
  {
    stars.add(new Star());
  }
  rocket = new Rocket () ;
}

void draw() {
  if (screenState == 0) 
  {
    // title screen
    background(0);
    for (Star s : stars) 
      {
        s.update();
        s.display();
      }

    // welcome to space shooter
    fill (255) ;
    textSize (32) ;
    textAlign (CENTER, CENTER) ;
    text("Welcome to Space Shooter!", width / 2, height / 2 - 40) ;

    // text blinking
    if (millis() - lastToggleTime > 500) 
    {
      showText = !showText;
      lastToggleTime = millis();
    }

    // blinking text
    if (showText) 
    {
      text("Press ENTER to Start", width / 2, height / 2 + 40);
    }
    
  } 
    else if (screenState == 1) 
    {
    // game screen
    background (0) ;
    
    // display rocket
    rocket.update ();
    rocket.display ();
    
    // display projectiles
    for (int i = projectiles.size() - 1; i >= 0; i--) 
    {
      Projectile p = projectiles.get (i) ;
      p.update () ;
      p.display () ;
      
    if (p.x > width || p.x < 0 || p.y > height || p.y < 0) 
      {
        projectiles.remove(i);
      }
    }
  }
}

void keyPressed () 
{
  if (key == ENTER && screenState == 0) 
    {
      screenState = 1;  // switch to game screen when ENTER is pressed
    }
}

void mousePressed () 
{
  // mouse is pressed, create a new projectile from the rocket's position
  projectiles.add(new Projectile(rocket.x, rocket.y)) ;
}
