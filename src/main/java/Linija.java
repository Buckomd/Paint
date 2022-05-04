import java.awt.*;

public class Linija extends Element {

    private Point p1, p2;

    public Linija(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    void paint(Graphics g) {
        super.paint(g);
        g.drawLine(p1.x,p1.y,p2.x,p2.y);
    }
}
