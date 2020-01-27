import com.sun.jdi.connect.Connector;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Cube c = new Cube();
        c.scale(120);
        c.translate(-50,-50,-50);
        Viewer v = new Viewer(c);
    }
}
