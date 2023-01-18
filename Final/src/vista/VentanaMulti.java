package vista;

import controlador.Controlador;
import modelo.Cliente;

import javax.swing.*;
import java.awt.*;

public class VentanaMulti extends JFrame
{
    public static final int XPantalla = 800;
    public static final int YPantalla = 600;

    Container panel;
    public ProgramaMulti game;

    public VentanaMulti()
    {

        JFrame ventanaJuegoMulti = new JFrame("Star Fighters(Multijugador)"); // crea un nuevo panel//
        game = new ProgramaMulti();
        game.setPreferredSize( new Dimension (XPantalla, YPantalla));
        game.addKeyListener(new Controlador(this));
        ventanaJuegoMulti.getContentPane().add(game);
        Cliente c = new Cliente();
        Controlador controlador = new Controlador(this);
        controlador.despachador = c.conectar(this, this.game);
        this.game.addKeyListener(controlador);
        if (controlador.despachador != null)
        {
            controlador.conectar();
        }

        //configuracion aspectos de la ventana//
        ventanaJuegoMulti.setResizable(false); // La ventana no puede ser redimensionada//
        ventanaJuegoMulti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cierra al hacer clic en la X //
        ventanaJuegoMulti.setLocation(100, 100);
        ventanaJuegoMulti.setVisible(true);
        ventanaJuegoMulti.pack();
        //ventanaJuegoMulti.addKeyListener(new Controlador(this));
    }
}
