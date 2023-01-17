package modelo;

import vista.Jugador;

import java.net.*;
import java.util.ArrayList;
public class Servidor {
    public Servidor()
    {
        int portNumber = 1234;
        ArrayList<Despachador> escritores = new ArrayList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            while (true)
            {
                Socket clientSocket = serverSocket.accept();

                Despachador lector = new Despachador(clientSocket);
                escritores.add(lector);
                lector.escritores = escritores;
                lector.start();
                //si J1 y J2 son true se cierra el socket.
                if(Jugador.J1 == true && Jugador.J2 == true)
                {
                    clientSocket.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
