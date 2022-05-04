import java.awt.*;

public abstract class Element {

   private Color boja = Color.BLACK;

   void paint(Graphics g) {
      g.setColor(boja);
   }

   public void setBoja(Color boja) {
      this.boja = boja;
   }

}
