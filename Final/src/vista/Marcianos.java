package vista;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Marcianos
{
    public int posicionX;
    public int posicionY;
    public int velocidadX;
    public int velocidadY;
    public int tamanoX;
    public int tamanoY;
    public BufferedImage enemigo;
    public boolean esVisible;

    public Marcianos()
    {
        tamanoX = 40;
        tamanoY = 40;
        posicionX = 50;
        posicionY = 10;
        velocidadX = 2;
        esVisible = true;
        try
        {
            enemigo = ImageIO.read(getClass().getResource("img/marciano.png"));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
