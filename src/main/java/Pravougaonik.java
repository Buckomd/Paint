import java.awt.*;

public class Pravougaonik extends Element {

    private Point p;
    private int height, width;

    public Pravougaonik(Point p, int height, int width){
        this.p = p;
        this.height = height;
        this.width = width;
    }

    @Override
    void paint(Graphics g) {
        g.drawRect(p.x,p.y,width,height);
    }
}
