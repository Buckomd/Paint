import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPaint extends JFrame {

    private final String title = "MyPaint v1.0.0";
    private int width = 400, height = 400;
    private JPanel pToolbar;
    private MyPanel pMainPanel;
    private JRadioButton rbkrug, rblinija, rbpravougraonik;
    private ButtonGroup rbGrupa;
    private JButton btnObrisi;

    public MyPaint(){
        super();
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(new Rectangle(100,100,width,height));
        setLayout(new BorderLayout());

        initComponents();
        addComponents();
        addListeners();
    }

    private void addListeners(){
        rblinija.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pMainPanel.setSelektovan(Figura.LINIJA);
            }
        });

        rbpravougraonik.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pMainPanel.setSelektovan(Figura.PRAVOUGAONIK);
            }
        });

        rbkrug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pMainPanel.setSelektovan(Figura.KRUG);
            }
        });


        btnObrisi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pMainPanel.obrisi();
            }
        });
    }

    private void initComponents(){
        pToolbar = new JPanel();
        pMainPanel = new MyPanel();
        btnObrisi = new JButton("Obrisi");
        rbkrug = new JRadioButton("Krug");
        rblinija = new JRadioButton("Linija");
        rbpravougraonik = new JRadioButton("Pravougaonik");
        rbGrupa = new ButtonGroup();
    }

    private void addComponents(){
        add(pToolbar, BorderLayout.NORTH);
        add(pMainPanel, BorderLayout.CENTER);
        pToolbar.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        pToolbar.add(rblinija);
        pToolbar.add(rbpravougraonik);
        pToolbar.add(rbkrug);
        pToolbar.add(btnObrisi);

        rbGrupa.add(rbkrug);
        rbGrupa.add(rblinija);
        rbGrupa.add(rbpravougraonik);
        rblinija.setSelected(true);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public static void main(String[] args) {
        MyPaint mp = new MyPaint();
        mp.setVisible(true);
    }


}
