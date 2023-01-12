package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaSeleccion extends JFrame
{
    ManejadorBotonesMulti botonesListener2;
    JPanel panelTransicion;
    JPanel panelMenu2;
    JButton btnJugador1;
    JButton btnJugador2;
    public VentanaSeleccion()
    {
        llamarComponentes();
        setSize(500, 500);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("STAR FIGHTERS (Multijugador)");

    }
    private void llamarComponentes()
    {
        botonesListener2 = new ManejadorBotonesMulti();

        panelTransicion = new JPanel();
        panelTransicion.setLayout(new CardLayout());


        panelMenu2 = new JPanel();
        panelMenu2.setLayout(null);
        panelMenu2.setBackground(Color.black);

        btnJugador1 = new JButton("Jugador 1");
        panelMenu2.add(btnJugador1);
        btnJugador1.setBounds(190, 200, 120, 60);
        btnJugador1.addActionListener(botonesListener2);

        btnJugador2 = new JButton("Jugador 2");
        panelMenu2.add(btnJugador2);
        btnJugador2.setBounds(190, 290, 120, 60);
        btnJugador2.addActionListener(botonesListener2);

        panelTransicion.add(panelMenu2);

        add(panelTransicion);


    }
    private void iniciarJuegoMulti()
    {
        VentanaMulti ventanaJuegoMulti = new VentanaMulti();
    }

    public class ManejadorBotonesMulti implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            if (arg0.getSource() == btnJugador1)
            {
                dispose();
                Jugador.J1 = true;
                iniciarJuegoMulti();
            }
            else if (arg0.getSource() == btnJugador2)
            {
                dispose();
                Jugador.J2 = true;
                iniciarJuegoMulti();
            }
        }

    }
}
