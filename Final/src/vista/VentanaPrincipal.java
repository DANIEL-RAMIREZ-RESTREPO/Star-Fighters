package vista;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.JPanel;


public class VentanaPrincipal extends JFrame
{
    ManejadorEventosBotones botonesListener;
    JPanel panelCard;
    JPanel panelMenu;
    JButton btnMultijugador;
    JButton btnSolitario;
    Titulo title;



    public VentanaPrincipal()
    {
        iniciarComponentes();
        setSize(500, 500);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("STAR FIGHTERS");
    }

    private void iniciarComponentes()
    {
        botonesListener = new ManejadorEventosBotones();
        title = new Titulo();

        panelCard = new JPanel();
        panelCard.setLayout(new CardLayout());

        panelMenu = new JPanel();
        panelMenu.setLayout(null);

        btnMultijugador = new JButton("Multijugador");
        panelMenu.add(btnMultijugador);
        btnMultijugador.setBounds(100, 250, 120, 40);
        btnMultijugador.addActionListener(botonesListener);

        btnSolitario = new JButton("Solo");
        panelMenu.add(btnSolitario);
        btnSolitario.setBounds(280, 250, 120, 40);
        btnSolitario.addActionListener(botonesListener);

        panelCard.add(panelMenu);

        add(panelCard);


    }
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(title.title, title.posicionX,title.posicionY , null);
    }

    private void iniciarPantallaSeleccion()
    {
        VentanaSeleccion ventanaPantallaSeleccion = new VentanaSeleccion();
    }
    private void iniciarJuegoSolo()
    {
        VentanaSolo ventanaJuegoSolo = new VentanaSolo();
    }

    public class ManejadorEventosBotones implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            if (arg0.getSource() == btnMultijugador)
            {
                dispose();
                iniciarPantallaSeleccion();
            }
            else if (arg0.getSource() == btnSolitario)
            {
                dispose();
                iniciarJuegoSolo();
            }
        }


    }
}
