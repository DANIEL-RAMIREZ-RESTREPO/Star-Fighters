package vista;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Nave
{
    public static int posicionX;
    public static int posicionY;
    public static int velocidadX;
    public int tamanoX;
    public int tamanoY;
    public BufferedImage nave;
    public String imagen;


    public Nave(int posX, int posY, String imagen)
    {
        tamanoX = 60;
        tamanoY = 60;
        velocidadX = 0;
        posicionY = posY;
        posicionX = posX;
        try
        {
            nave = ImageIO.read(getClass().getResource(imagen));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}