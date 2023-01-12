package modelo;

import vista.VentanaMulti;
import vista.ProgramaMulti;
import java.net.*;

public class Cliente {
    public Despachador conectar(VentanaMulti gui, ProgramaMulti tablero) {
        String hostName = "localhost";
        int portNumber = 1234;

        try {
            Socket kkSocket = new Socket(hostName, portNumber);

            Despachador lector = new Despachador(kkSocket);
            lector.gui = gui;
            lector.start();

            return lector;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }
}
