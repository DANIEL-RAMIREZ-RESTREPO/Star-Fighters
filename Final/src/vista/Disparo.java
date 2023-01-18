package vista;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Disparo
{
    public static int posicionX;
    public static int posicionY;
    public int velocidadY;
    public int tamanoX;
    public int tamanoY;
    public BufferedImage disparo;
    boolean active ;
    public Disparo(int vel, String imagenDis)
    {
        tamanoX = 1;
        tamanoY = 5;
        posicionX = 0;
        posicionY = 0;
        velocidadY = vel;
        active = true;
        try
        {
            disparo = ImageIO.read(getClass().getResource(imagenDis));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}