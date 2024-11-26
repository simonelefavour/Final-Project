import java.util.LinkedList ;
import java.util.ArrayList ;

int screenState = 0 ; // title scree, countdown, game 
boolean showText = true ;
int lastToggleTime = 0 ;
int lastShotTime = 0 ;
int shotInterval = 200 ;
int score = 0 ;

int countdownStartTime ; // countdown start 
int countdownDuration = 3000 ; // 3 sec
String countdownText = "" ; // text display countdown 

Player rocket ;
Enemy enemy ;
LinkedList<Projectile> projectiles = new LinkedList<>() ; // shooter linked list
ArrayList<Star> stars = new ArrayList<>(); // star arraylist

void setup () 
  {
    fullScreen () ; // full screen 
    rocket = new Player (width / 2, height - 50) ;
    enemy = new Enemy (width / 2, 50) ;

    // stars displayed on main screen 
    for (int i = 0; i < 150; i++) 
      {
        stars.add(new Star(random(width), random(height)));
      }
  } // end set up

void draw () 
  {
    background (0) ; // black background 
    
    // display of different screens 
    if (screenState == 0) 
      { displayTitleScreen(); } 
    else if (screenState == 1) 
      { displayCountdown(); } 
    else if (screenState == 2) 
      { displayGameScreen(); }
  } // end draw 

void displayTitleScreen () 
  {
    // update star display
    for (Star star : stars) 
      {
        star.update() ;
        star.display() ;
      }
    
    // text 
    fill (255) ;
    textSize (32) ;
    textAlign (CENTER, CENTER) ;
    text ("Welcome to Space Shooter!", width / 2, height / 2 - 40) ;
    
    if (millis() - lastToggleTime > 500) 
      {
        showText = !showText;
        lastToggleTime = millis();
      }
    
    if (showText) 
      {
        text("Press ENTER to Start", width / 2, height / 2 + 40) ;
      }
  } // end title screen 

void displayCountdown () 
  {
    // countdown screen 
    int elapsed = millis() - countdownStartTime ;
    fill(255, 255, 0) ;
    textSize (48) ;
    textAlign (CENTER, CENTER);

    if (elapsed < 1000) 
      {
        countdownText = "Starting in 3" ;
      } 
    else if (elapsed < 2000) 
      {
        countdownText = "Starting in 2" ;
      } 
    else if (elapsed < 3000) 
      {
        countdownText = "Starting in 1" ;
      } 
    else if (elapsed < 4000) 
      { 
        countdownText = "GO!";
      } 
    else 
      {
        screenState = 2 ; // change to game screen 
      }
  
    text(countdownText, width / 2, height / 2);
  } // end displayCoundown 

void displayGameScreen () 
  {
    rocket.update() ;
    rocket.display() ;
    enemy.update() ;
    enemy.display() ;
    
    // shooting
    if (mousePressed && millis() - lastShotTime >= shotInterval) 
      {
        projectiles.add(new Projectile(rocket.getX(), rocket.getY())) ;
        lastShotTime = millis() ;
      }

    // projectile update
    for (int i = projectiles.size() - 1; i >= 0; i--)
      {
        Projectile p = projectiles.get (i) ;
        p.update() ;
        p.display() ;

        if (p.isOutOfBounds()) 
          {
            projectiles.remove(i) ;
          } 
        else if (p.collidesWith(enemy)) 
          {
            score += 10 ;
            enemy.takeDamage() ;
            projectiles.remove(i) ;
          }
      }

    // score and health 
    fill (255) ;
    textSize (16) ;
    text ("Score: " + score, 50, 30) ;
    text ("Health: " + rocket.getHealth(), width - 100, 30) ;

    if (enemy.getHealth() <= 0) 
    {
      text ("You Win!", width / 2, height / 2) ;
      noLoop () ;
    } // end if 

    if (rocket.getHealth() <= 0) 
      {
        text ("Game Over!", width / 2, height / 2) ;
        noLoop() ;
      } // end if
  } // end displayGameScreen 

void keyPressed () 
  {
    // moves to countdown when enter 
    if (key == ENTER && screenState == 0) 
      {
        screenState = 1 ; 
        countdownStartTime = millis () ; 
      }
  } 
