package vista;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Fondo
{
    public BufferedImage background;
    public int posicionX;
    public int posicionY;

    public Fondo()
    {
        posicionX = 0;
        posicionY = 0;
        try
        {
            background = ImageIO.read(getClass().getResource("img/fondo.png"));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}