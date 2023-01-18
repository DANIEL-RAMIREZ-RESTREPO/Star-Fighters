package controlador;

import modelo.Despachador;
import vista.ProgramaMulti;
import vista.VentanaMulti;
import vista.Jugador;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
public class Controlador implements KeyListener {

    VentanaMulti vista;
    public Despachador despachador;
    public HashMap<String, Jugador> jugadores = new HashMap<>();
    public static String jugadorPresente = "";
    public Controlador(VentanaMulti v) {
        vista = v;
        jugadores = vista.game.jugadores;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                ProgramaMulti.teclaIzq = true;
                break;
            case KeyEvent.VK_RIGHT:
                ProgramaMulti.teclaDer = true;
                break;
            case KeyEvent.VK_SPACE:
                ProgramaMulti.teclaEsp = true;
                break;

        }
        int _x = jugadores.get(jugadorPresente).x;
        int _y = jugadores.get(jugadorPresente).y;
        despachador.send("mover:"+jugadorPresente+","+_x+","+_y );
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch ( e.getKeyCode())
        {
            case KeyEvent.VK_LEFT: //Obtiene el valor de la tecla izquierda//
                ProgramaMulti.teclaIzq = false;
                break;
            case KeyEvent.VK_RIGHT: //Obtiene el valor de la tecla derecha//
                ProgramaMulti.teclaDer = false;
                break;
            case KeyEvent.VK_SPACE: //Obtiene el valor de la tecla espacio//
                ProgramaMulti.teclaEsp = false;
                break;

        }
        int _x = jugadores.get(jugadorPresente).x;
        int _y = jugadores.get(jugadorPresente).y;
        despachador.send("mover:"+jugadorPresente+","+_x+","+_y );
    }
    public void conectar(){
        despachador.send("login:"+jugadorPresente);
    }
}

