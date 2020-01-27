import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Viewer extends JFrame
{
    private Cube cube;

    protected static int width = 700; //500
    protected static int height = 700;

    Viewer(Cube c)
    {
        super("CUBE");
        super.repaint();
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(null);
        //container.setLayout(new FlowLayout(FlowLayout.CENTER));
        //container.setLayout(new GridLayout(2, 2));
        //container.setLayout(new BorderLayout());

        Cube cub = c;
        Button orth = new Button(cub, this);
        Button pers = new Button(cub, this);
        Scroll vert = new Scroll(cub, this);
        Scroll hor = new Scroll(cub, this);

        orth.setBounds(0, 0, width/2 - 7, 30);
        pers.setBounds(width/2 - 7, 0, width/2 - 7, 30);
        vert.setBounds(width - 34, 30, 20, height - 87);
        vert.setMaximum(100);
        hor.setBounds(0, height - 57, width - 34, 20);
        hor.setMaximum(100);

        container.add(orth);
        container.add(pers);
        container.add(vert, BorderLayout.EAST);
        container.add(hor, BorderLayout.SOUTH);

        cub.addObserver(orth);
        cub.addObserver(pers);
        cub.addObserver(vert);
        cub.addObserver(hor);


        this.setVisible(true);
    }

    public void setCube(Cube c)
    {
        this.cube = c;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        Rectangle s = this.getBounds();

        g.translate(s.width/2, s.height/2);

        cube.draw(g2d, s.width, s.height);
    }
}
