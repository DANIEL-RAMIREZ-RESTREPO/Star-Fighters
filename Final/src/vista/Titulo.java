package vista;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Titulo
{
    public BufferedImage title;
    public int posicionX;
    public int posicionY;

    public Titulo()
    {
        posicionX = 0;
        posicionY = 0;
        try
        {
            title = ImageIO.read(getClass().getResource("img/title.png"));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

