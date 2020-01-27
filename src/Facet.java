import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.Observable;

public class Facet
{
    private R3Vector[] vertex;
    Color color;

    public Facet(R3Vector v1, R3Vector v2, R3Vector v3, R3Vector v4, Color color)
    {
        vertex = new R3Vector[4];
        vertex[0] = v1;
        vertex[1] = v2;
        vertex[2] = v3;
        vertex[3] = v4;
        this.color = color;
    }

    public void out()
    {
        for (int i = 0; i < 4; i++)
        {
            vertex[i].out();
        }
    }

    public void rotate(double ux, double uy, double uz)
    {
        for (int i = 0; i < 4; i++)
        {
            vertex[i].rotate(ux,uy,uz);
        }
    }

    public void translate(double dx, double dy, double dz)
    {
        for (int i = 0; i < 4; i++)
        {
            vertex[i].translate(dx,dy,dz);
        }
    }

    public R3Vector normal()
    {
        return R3Vector.vectPr(new R3Vector(vertex[1].getX() - vertex[0].getX(),
                                            vertex[1].getY() - vertex[0].getY(),
                                            vertex[1].getZ() - vertex[0].getZ()),
                               new R3Vector(vertex[3].getX() - vertex[0].getX(),
                                            vertex[3].getY() - vertex[0].getY(),
                                            vertex[3].getZ() - vertex[0].getZ()));
    }

    public R3Vector norm(R3Vector v)
    {
        return new R3Vector(v.getX()*0.03, v.getY()*0.03, v.getZ()*0.03);
    }

    public void scale(double m)
    {
        for (int i = 0; i < 4; i++)
        {
            vertex[i].scale(m);
        }
    }

    public double crossX(double a, double b, double c, double y)        // расчет точки пересечения
    {
        return (-b*y - c)/a;
    }

    public double crossY(double a, double b, double c,
                         double a1, double b1, double c1)
    {
        return ((((a1*c)/a) - c1)/(((-b*a1)/a) + b1));
    }

    public void draw(Graphics2D g)
    {

        Path2D p = new Path2D.Double();

        p.moveTo(vertex[0].getX(),vertex[0].getY());
        p.lineTo(vertex[1].getX(),vertex[1].getY());
        p.lineTo(vertex[2].getX(),vertex[2].getY());
        p.lineTo(vertex[3].getX(),vertex[3].getY());
        p.lineTo(vertex[0].getX(),vertex[0].getY());
        p.closePath();

        if(this.normal().getZ()>0)
        {
            g.setColor(color);
            g.fill(p);
        }

    }
    public void drawPers(Graphics2D g, double k)
    {

        Path2D p = new Path2D.Double();
        Path2D p1 = new Path2D.Double();
        Path2D p2 = new Path2D.Double();
        double t[] = new double[4];
        t[0] = -k/(vertex[0].getZ()-k);
        t[1] = -k/(vertex[1].getZ()-k);
        t[2] = -k/(vertex[2].getZ()-k);
        t[3] = -k/(vertex[3].getZ()-k);
        R3Vector vector[] = new R3Vector[4];
        for ( int i=0; i<4; i++)
        {
            vector[i] = new R3Vector (vertex[i].getX()*t[i], vertex[i].getY()*t[i],vertex[i].getZ());
        }
        p.moveTo(vector[0].getX(), vector[0].getY());
        p.lineTo(vector[1].getX(), vector[1].getY());
        p.lineTo(vector[2].getX(), vector[2].getY());
        p.lineTo(vector[3].getX(), vector[3].getY());
        p.lineTo(vector[0].getX(), vector[0].getY());
        p.closePath();

        Facet facet = new Facet(vector[0], vector[1], vector[2], vector[3], color);

        double a = vector[0].getY() - vector[2].getY(); // коэффициенты уравнения прямой  ax + by + c = 0
        double b = vector[2].getX() - vector[0].getX();
        double c = vector[0].getX() * vector[2].getY() - vector[2].getX() * vector[0].getY();
        double a1 = vector[1].getY() - vector[3].getY();
        double b1 = vector[3].getX() - vector[1].getX();
        double c1 = vector[1].getX() * vector[3].getY() - vector[3].getX() * vector[1].getY();
        double centerY = crossY(a,b,c,a1,b1,c1);    // центр грани
        double centerX = crossX(a,b,c,centerY);

        if (facet.normal().getZ()>0)
        {

            p1.moveTo(centerX,centerY);
            p1.lineTo((-facet.normal().getX()+centerX)*0.01+centerX,(-facet.normal().getY()+centerY)*0.01+centerY);
            p1.closePath();
            g.setColor(color);
            g.fill(p);
            g.setColor(Color.BLACK);
            //g.fillOval((int)centerX,(int)centerY,3,3);  принимал только int
            Ellipse2D.Double shape = new Ellipse2D.Double(centerX -1 , centerY -1, 2, 2);  // принимает double
            g.draw(shape);
            g.draw(p1);
        }
        else
        {
            p2.moveTo(centerX,centerY);
            p2.lineTo((-facet.normal().getX()+centerX)*0.01+centerX,(-facet.normal().getY()+centerY)*0.01+centerY);
            p2.closePath();
            g.setColor(Color.BLACK);
            g.draw(p2);
        }
    }
}
