import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.util.ArrayList;

public class MyPanel extends JPanel implements MouseListener, MouseMotionListener {

    private Figura selektovan = Figura.LINIJA;
    private Point start = null;
    private ArrayList<Element> figure;
    private Element elem = null;
    private Color boja = Color.BLACK;


    public MyPanel() {
        figure = new ArrayList<>();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void serializeArrayList() {

        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    new File("mojefugure")
                            )
                    )
            );

            out.writeObject(figure);
            out.close();

            System.out.println("namesList serialized");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void savetoFile(JPanel jpanel) {

        String filename = JOptionPane.showInputDialog("Name this file");
        JFileChooser savefile = new JFileChooser();
        savefile.setSelectedFile(new File(filename));

        int sf = savefile.showSaveDialog(jpanel);

        if (sf == JFileChooser.APPROVE_OPTION) {
            try {
                ObjectOutputStream out = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(
                                        new File(filename)
                                )
                        )
                );
                out.writeObject(figure);
                out.close();
                JOptionPane.showMessageDialog(jpanel, "File has been saved", "File Saved", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (sf == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(jpanel, "File save has been canceled");
        }
    }

    public void readFromFile(JPanel jPanel) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int rf = fileChooser.showOpenDialog(jPanel);

        if (rf == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                ObjectInputStream in = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream(
                                        new File(String.valueOf(selectedFile))
                                )
                        )
                );
                figure = (ArrayList) in.readObject();
                in.close();
                repaint();
                JOptionPane.showMessageDialog(jPanel, "File has been loaded ", "File Reader", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (rf == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(jPanel, "File read has been canceled");
        }
    }


    public void setBoja(String boja) {
        if (boja.equals("Crna"))
            this.boja = Color.BLACK;
        else if (boja.equals("Plava"))
            this.boja = Color.BLUE;
        else if (boja.equals("Crvena"))
            this.boja = Color.RED;
        else
            this.boja = Color.BLACK;
    }


    public void obrisi() {
        this.figure.clear();
        repaint();
    }


    public void setSelektovan(Figura selektovan) {
        this.selektovan = selektovan;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Element e : figure)
            e.paint(g);

        if (elem != null)
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
        Element fig = null;

        switch (this.selektovan) {
            case LINIJA:
                fig = new Linija(start, end);
                break;
            case KRUG:
                fig = new Krug(new Point(Math.min(start.x, end.x), Math.min(start.y, end.y)),
                        Math.abs(start.x - end.x));
                break;
            case PRAVOUGAONIK:
                Point startPoint = new Point(Math.min(start.x, end.x), Math.min(start.y, end.y));
                int w = Math.abs(start.x - end.x);
                int h = Math.abs(start.y - end.y);
                fig = new Pravougaonik(startPoint, h, w);
                break;
        }
        if (fig != null) {
            fig.setBoja(this.boja);
            this.figure.add(fig);
        }
        elem = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        Point end = e.getPoint();

        switch (this.selektovan) {
            case LINIJA:
                elem = new Linija(start, end);
                break;
            case KRUG:
                elem = new Krug(new Point(Math.min(start.x, end.x), Math.min(start.y, end.y)),
                        Math.abs(start.x - end.x));
                break;
            case PRAVOUGAONIK:
                Point startPoint = new Point(Math.min(start.x, end.x), Math.min(start.y, end.y));
                int w = Math.abs(start.x - end.x);
                int h = Math.abs(start.y - end.y);
                elem = new Pravougaonik(startPoint, h, w);
                break;
        }
        elem.setBoja(this.boja);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
