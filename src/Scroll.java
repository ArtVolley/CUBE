import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Observable;
import java.util.Observer;

public class Scroll extends JScrollBar implements Observer
{
    private static int num = 0;
    private int k;
    private Cube cube;
    private Viewer viewer;
    private int Xangle = 0;
    private int Yangle = 0;

    public Scroll(Cube cube, Viewer viewer)
    {
        num++;
        k = num;
        if (k == 1)
        {
            this.setBackground(Color.PINK);
            this.setOrientation(JScrollBar.VERTICAL);
            ScrollListener listener = new ScrollListener();
            this.addAdjustmentListener(listener);
        }
        if (k == 2)
        {
            this.setBackground(Color.PINK);
            this.setOrientation(JScrollBar.HORIZONTAL);
            ScrollListener listener1 = new ScrollListener();
            this.addAdjustmentListener(listener1);
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

    private class ScrollListener implements AdjustmentListener
    {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e)
        {
            if (getK() == 1)
            {
                if (Xangle < e.getValue())
                {
                    Xangle = e.getValue();
                    cube.rotate(e.getValue() / 10, 0, 0);
                }
                else
                {
                    Xangle = e.getValue();
                    cube.rotate(-e.getValue() / 10, 0, 0);
                }
            }
            if (getK() == 2)
            {
                if (Yangle < e.getValue())
                {
                    Yangle = e.getValue();
                    cube.rotate(0, -e.getValue() / 10, 0);
                }
                else
                {
                    Yangle = e.getValue();
                    cube.rotate(0, e.getValue() / 10, 0);
                }
            }
        }
    }
}
