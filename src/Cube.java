import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Observable;

public class Cube extends Observable
{

    private Facet[] facets;
    private double k = -500;
    private boolean ort = false;

    public Cube()
    {
        facets = new Facet[6];
        facets[0] = new Facet(new R3Vector(0,0,0),
                new R3Vector(1,0,0),
                new R3Vector(1,1,0),
                new R3Vector(0,1,0), Color.PINK);
        facets[1] = new Facet(new R3Vector(1,0,1),
                new R3Vector(1,1,1),
                new R3Vector(1,1,0),
                new R3Vector(1,0,0), Color.BLUE);
        facets[2] = new Facet(new R3Vector(0,0,0),
                new R3Vector(0,1,0),
                new R3Vector(0,1,1),
                new R3Vector(0,0,1), Color.YELLOW);
        facets[3] = new Facet(new R3Vector(0,0,0),
                new R3Vector(0,0,1),
                new R3Vector(1,0,1),
                new R3Vector(1,0,0), Color.GRAY);
        facets[4] = new Facet(new R3Vector(0,1,0),
                new R3Vector(1,1,0),
                new R3Vector(1,1,1),
                new R3Vector(0,1,1), Color.GREEN);
        facets[5] = new Facet(new R3Vector(0,0,1),
                new R3Vector(0,1,1),
                new R3Vector(1,1,1),
                new R3Vector(1,0,1), Color.ORANGE);
    }

    public void rotate(double ux, double uy, double uz)
    {
        for (int i = 0; i < 6; i++)
        {
            facets[i].rotate(ux,uy,uz);
        }
        this.setChanged();
        this.notifyObservers();
    }

    public void translate(double dx, double dy, double dz)
    {
        for (int i = 0; i < 6; i++)
        {
            facets[i].translate(dx,dy,dz);
        }
    }

    public void scale(double m)
    {
        for (int i = 0; i < 6; i++)
        {
            facets[i].scale(m);
        }
    }

    public void setOrt(boolean ort)
    {
        this.ort = ort;
        this.setChanged();
        this.notifyObservers();
    }

    public void draw(Graphics2D g, int width, int height)
    {

        if (ort == true)
        {

            //g.setColor(Color.WHITE);
            //g.fillRect(-width/2, -height/2, width, height);
            //g.setColor(Color.LIGHT_GRAY);
            //g.drawLine(-width/2,0,width/2,0);
            //g.drawLine(0,-height/2,0,height/2);
            for (int i = 0; i < 6; i++)
            {
                if (facets[i].normal().getZ() <= 0)
                {
                    facets[i].draw(g);
                }
            }
            for (int i = 0; i < 6; i++)
            {
                if (facets[i].normal().getZ() > 0)
                {
                    facets[i].draw(g);
                }
            }
        }
        else
            {
                //g.setColor(Color.WHITE);
                //g.fillRect(-width/2, -height/2, width, width);
                //g.setColor(Color.LIGHT_GRAY);
                //g.drawLine(-width/2,0,width/2,0);
                //g.drawLine(0,-height/2,0,height/2);
                for (int i = 0; i < 6; i++)
                {
                   if (facets[i].normal().getZ() <= 0)
                   {
                        facets[i].drawPers(g, k);
                   }
                }
                for (int i = 0; i < 6; i++)
                {
                    if (facets[i].normal().getZ() > 0)
                    {
                        facets[i].drawPers(g, k);
                    }
                }
            }
    }
}
