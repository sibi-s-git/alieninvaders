import javafx.scene.image.Image;
import java.util.Arrays;

import javafx.scene.input.KeyCode;
public class AlienCore extends Actor
{
   private double dx, dy;
   double height;
   int rotation;
   boolean allowed;
   long last;
   int lives;
   long lastl;
   long lasth;
   int damage;
   double ogy;
   boolean down;
   double prevx;
   long lastla;
   AlienCore()
   {
      setImage(new Image(getClass().getClassLoader().getResource("resources/AlienCore.png").toString()));
      height = getY();
      rotation =1;
      allowed = true;
      dx =0;
      dy =0;
      last =0;
      lastl =0;
      lives = 51;
      damage = 1;
      ogy = getY();
      down = true;
      prevx =0;
      lastla =0;
   }
   
   @Override
   public void act(long now)
   {
      move(dx,-dy);
      
      if (getX()<300)
      {
         rotation *=-1;
      }
      if ((getX()-50<0)&&rotation==1&&allowed)
      {
         dx = 2;
      }
      if ((getX()+370>getWorld().getWidth()))
      {
         rotation *=-1;
      }
      
      if ((getX()+370>getWorld().getWidth())&&rotation==-1&&allowed)
      {
         dx = -2;
      }
      
      if(lives%5==0)
      {       
         if (down)
         {
            damage =0;
            allowed=false;
            prevx = dx;
            dx=0;
            dy=-6;
            down = false;
         }
         if (getY()>350)
         {
            dy =6;
            
         }
         if (getY()<ogy)
         {
            dy =0;
            setY(ogy);
            dx = prevx;
            damage =1; 
            allowed =true;
            down = true;
            takeHit();
         }
      }
      
      
      Ship ship = getOneIntersectingObject(Ship.class);
      if(now-lasth >1e9)
      {
         if (ship != null)
         {
            Explosion exp = new Explosion();       
            exp.setX(ship.getX());
            exp.setY(ship.getY());
            exp.setFitWidth(ship.getWidth());
            exp.setFitHeight(ship.getHeight());
            this.getWorld().add(exp);
            ship.takeHit();
            lasth=now;
            
         }
      }
      if(now-last >1e10/6&&allowed)
      {
        
         Bomb laser = new Bomb();   
         laser.setX(this.getX()+30);
         laser.setY(this.getY());
         laser.setFitWidth(this.getWidth()/4);
         laser.setFitHeight(this.getHeight()/3);
         this.getWorld().add(laser);
         
         last = now;
      }
      if(now-lastl >1e9&&allowed)
      {
        
         AlienLaser laserr = new AlienLaser();   
         laserr.setX(this.getX()+200);
         laserr.setY(this.getY());
         laserr.setFitWidth(this.getWidth()/4);
         laserr.setFitHeight(this.getHeight()/3);
         this.getWorld().add(laserr);
         AlienLaser laserrr = new AlienLaser();   
         laserrr.setX(this.getX()-200);
         laserrr.setY(this.getY());
         laserrr.setFitWidth(this.getWidth()/4);
         laserrr.setFitHeight(this.getHeight()/3);
         this.getWorld().add(laserrr);
         lastl = now;
      }
      
      if(now-lastla >1e8/3)
      {
        if((lives+3)%5==0||(lives+2)%5==0||(lives+1)%5==0)
        {
            AlienLaser lasers = new AlienLaser();   
            lasers.setX(this.getX()+120);
            lasers.setY(this.getY()+100);
            lasers.setFitWidth(this.getWidth()/4);
            lasers.setFitHeight(this.getHeight()/3);
            this.getWorld().add(lasers);
            lastla = now;
        }
      }
      
      
   }
   public void takeHit()
   {
      lives=lives-damage;
      if (lives==0)
      {
         this.getWorld().remove(this);
      }
   }
      
   
}