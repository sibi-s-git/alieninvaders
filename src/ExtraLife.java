import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class ExtraLife extends Actor
{
   double last =0;
   boolean first = true;
   int dx;
   int dy;
   int rotation;
   boolean allowed;
   public ExtraLife()
   {
      setImage(new Image(getClass().getClassLoader().getResource("resources/life.png").toString()));
      dx = 4;
      dy =0;
      rotation =1;
      allowed = true;
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
         dx = 4;
      }
      if ((getX()+300>getWorld().getWidth()))
      {
         rotation *=-1;
      }
      
      if ((getX()+300>getWorld().getWidth())&&rotation==-1&&allowed)
      {
         dx = -4;
      }
   }

}
