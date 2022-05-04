import java.awt.*;

public class Krug extends Element{

    private Point p;
    private int radius;

    public Krug(Point p, int radius){
        this.p = p;
        this.radius = radius;
    }

    @Override
    void paint(Graphics g) {
        super.paint(g);
        g.drawOval(p.x,p.y,radius,radius);
    }
}
