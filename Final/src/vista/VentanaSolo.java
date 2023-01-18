package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaSolo
{
    public static final int XPantalla = 800;
    public static final int YPantalla = 600;
    public VentanaSolo()
    {

        JFrame ventana = new JFrame("Star Fighters"); // crea un nuevo panel//
        ProgramaSolo gameSolo = new ProgramaSolo();
        gameSolo.setPreferredSize( new Dimension(XPantalla, YPantalla));
        ventana.getContentPane().add(gameSolo);

        //configuracion aspectos de la ventana//
        ventana.setResizable(false); // La ventana no puede ser redimensionada//
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cierra al hacer clic en la X //
        ventana.setLocation(100, 100);
        ventana.setVisible(true);
        ventana.pack();

    }
}
