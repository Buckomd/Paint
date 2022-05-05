import java.awt.*;
import java.io.Serializable;

public abstract class Element implements Serializable {

   private Color boja = Color.BLACK;

   void paint(Graphics g) {
      g.setColor(boja);
   }

   public void setBoja(Color boja) {
      this.boja = boja;
   }

}
