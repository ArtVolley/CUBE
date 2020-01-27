import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Button extends JButton implements Observer
{
    private static int num = 0;
    private int k;
    private Cube cube;
    private Viewer viewer;
    Graphics g;

    public Button(Cube cube, Viewer viewer)
    {
        num++;
        k = num;
        if (k == 1)
        {
            this.setText("<html><h2><tt>ORTHOGONAL");
            this.setBackground(Color.PINK);
            ButtonListener listener = new ButtonListener();
            this.addActionListener(listener);
        }
        if (k == 2)
        {
            this.setText("<html><h2><tt>PERSPECTIVE");
            this.setBackground(Color.PINK);
            ButtonListener listener1 = new ButtonListener();
            this.addActionListener(listener1);
        }
        this.cube = cube;
        this.viewer = viewer;
    }

    public double getK()
    {
        return k;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        cube = (Cube) o;
        viewer.setCube(cube);
        //viewer.clear(g);  dont work
        viewer.repaint();
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (getK() == 1)
            {
                cube.setOrt(true);
            }
            if (getK() == 2)
            {
                cube.setOrt(false);
            }
        }
    }
}