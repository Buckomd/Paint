import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MyPanel extends JPanel implements MouseListener, MouseMotionListener {

    private Figura selektovan = Figura.LINIJA;
    private Point start = null;
    private ArrayList<Element> figure;
    private Element elem = null;


    public MyPanel(){
        figure = new ArrayList<>();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void obrisi(){
        this.figure.clear();
        repaint();
    }


    public void setSelektovan(Figura selektovan) {
        this.selektovan = selektovan;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for(Element e : figure)
            e.paint(g);

        if( elem != null)
            elem.paint(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        start = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point end = e.getPoint();

        switch (this.selektovan){
            case LINIJA:
                this.figure.add(new Linija(start,end));
                break;
            case KRUG:
                this.figure.add(new Krug(new Point(Math.min(start.x,end.x), Math.min(start.y,end.y)),
                                         Math.abs(start.x - end.x)));
                break;
            case PRAVOUGAONIK:
                Point startPoint = new Point(Math.min(start.x, end.x), Math.min(start.y, end.y));
                int w = Math.abs(start.x - end.x);
                int h = Math.abs(start.y - end.y);
                this.figure.add(new Pravougaonik(startPoint,h,w));
                break;
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        Point end = e.getPoint();

        switch (this.selektovan){
            case LINIJA:
                elem = new Linija(start,end);
                break;
            case KRUG:
                elem = new Krug(new Point(Math.min(start.x,end.x), Math.min(start.y,end.y)),
                        Math.abs(start.x - end.x));
                break;
            case PRAVOUGAONIK:
                Point startPoint = new Point(Math.min(start.x, end.x), Math.min(start.y, end.y));
                int w = Math.abs(start.x - end.x);
                int h = Math.abs(start.y - end.y);
                elem = new Pravougaonik(startPoint,h,w);
                break;
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
