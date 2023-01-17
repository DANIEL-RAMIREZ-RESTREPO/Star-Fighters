package vista;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class ProgramaMulti extends JPanel
{

    //atributos
    Nave nave1;
    Nave nave2;
    public static boolean teclaIzq;
    public static boolean teclaDer;
    public static boolean teclaEsp;
    public static boolean disparar1;
    public static boolean disparar2;
    Fondo background;
    Disparo disparo1;
    Disparo disparo2;

    String ganador;

    boolean gameOver;

    public HashMap<String, Jugador> jugadores = new HashMap<>();

    //constructor
    public ProgramaMulti()
    {
        background = new Fondo(); //instancia imagen de fondo
        nave1 = new Nave(200, 520, "img/nave1.png"); //instancia la nave del jugador 1
        disparo1 = new Disparo(-10,"img/disparo1.png"); //instancia el disparo del jugador 1

        nave2 = new Nave(300,20, "img/nave2.png");//instancia nave del jugador 2
        disparo2 = new Disparo(+10, "img/disparo2.png");//instancia al disparo del jugador 2

        background.posicionX = 0;
        background.posicionY = 0;
        setFocusable(true); // tiene la capacidad de recibir foco//
        setLayout(null);

        new Thread(new Runnable() {
            @Override
            public void run()
            {
                cicloJuego(); // llamar a una nueva unidad de ejecucion//
            }//clase interna//
        }).start();
    }

    //metodo gameloop//

    public void cicloJuego()
    {
        gameOver = false;

        while(true)
        {//ciclo infinito del juego//
            controladorEventos();
            update();
            render();
            inspector();
            if (gameOver == true)
            {
                JOptionPane.showMessageDialog
                        (null, "Game Over " + "el ganador es: " + ganador);
                break;
            }
            try
            {
                Thread.sleep(17); //pausa el hilo//
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }
    public void inspector()
    {
        if(Jugador.J1 == false)
        {
            nave1.nave = null;
            disparo1.disparo = null;
            disparo1.active = false;
        }
        else if(Jugador.J2 == false)
        {
            nave2.nave = null;
            disparo2.disparo = null;
            disparo2.active = false;
        }
    }
    public void controladorEventos()
    { //Maneja las acciones realizadas por el jugador//
        Nave.velocidadX = 0;

        if(teclaIzq==true)
        { //tecla izquierda presionada//
            Nave.velocidadX = -5; //movimiento para la izquierda//
        } else if (teclaDer==true)
        { //tecla derecha presionada//
            Nave.velocidadX = 5; //movimiento para la derecha//
        }
        if (teclaEsp == true && disparar1 == false)
        {
            disparar1 = true;
            disparo1.posicionX = nave1.posicionX+ (nave1.tamanoX/2); // posiciona el disparo para que salga desde el centro de la nave//
            disparo1.posicionY = nave1.posicionY; //le da la posicion de salida en Y al disparo de la nave
            disparo1.active = true; // activa el disparo//
            try {
                disparo1.disparo = ImageIO.read(getClass().getResource("img/disparo1.png"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        //jugador 2

        if (teclaEsp == true && disparar2 == false)
        {
            disparar2 = true;
            disparo2.posicionX = nave2.posicionX+ (nave2.tamanoX/2); // posiciona el disparo para que salga desde el centro de la nave//
            disparo2.posicionY = nave2.posicionY; //le da la posicion de salida en Y al disparo de la nave
            disparo2.active = true; // activa el disparo//
            try {
                disparo2.disparo = ImageIO.read(getClass().getResource("img/disparo2.png"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    public void update()
    {
        nave1.posicionX += nave1.velocidadX; // actualiza el movimiento del jugador//
        nave2.posicionX += nave2.velocidadX; // actualiza el movimiento del jugador2//
        if(disparar1 == true)
        { //al disparar la posicion y del disparo es añadido a la velocidad y del disparo//
            disparo1.posicionY+=disparo1.velocidadY;
        }
        if(disparar2 == true)
        { //al disparar la posicion y del disparo es añadido a la velocidad y del disparo//
            disparo2.posicionY+=disparo2.velocidadY;
        }
        detectorColision();
    }

    public void render()
    {
        repaint(); //es el redibujar
    }

    public void detectorColision()
    {
        if(nave1.posicionX+(nave1.tamanoX) > VentanaMulti.XPantalla || nave1.posicionX < 0)
        { // colision horizontal evita que la nave se salga de la ventana//
            nave1.posicionX -= nave1.velocidadX;
        }
        if(nave2.posicionX+(nave2.tamanoX) > VentanaMulti.XPantalla || nave2.posicionX < 0)
        { // colision horizontal evita que la nave se salga de la ventana//
            nave2.posicionX -= nave2.velocidadX;
        }
        if (disparo1.posicionX <= nave2.posicionX + nave2.tamanoX &&
                disparo1.posicionX >= nave2.posicionX &&
                disparo1.posicionY <= nave2.posicionY + nave2.tamanoY &&
                disparo1.posicionY >= nave2.posicionY && disparo1.active == true)
        {
            nave2.nave = null; // borra la imagen del Jugador2//
            disparo1.active = false; //disparo deja de estar activo//
            disparo1.disparo = null; // imagen del disparo es borrada//
            disparar1 = false; // permite disparar de nuevo//
            gameOver = true;
            ganador = "Jugador 1";
        }
        if (disparo2.posicionX <= nave1.posicionX + nave1.tamanoX &&
                disparo2.posicionX >= nave1.posicionX &&
                disparo2.posicionY <= nave1.posicionY + nave1.tamanoY &&
                disparo2.posicionY >= nave1.posicionY && disparo2.active == true)
        {
            nave1.nave = null; // borra la imagen del Jugador1//
            disparo2.active = false; //disparo deja de estar activo//
            disparo2.disparo = null; // imagen del disparo es borrada//
            disparar2 = false; // permite disparar de nuevo//
            gameOver = true;
            ganador = "Jugador 2";
        }
        if (disparar1 == true && disparo1.posicionY < 0)
        {//si el disparo se sale de la pantalla pueda volver a disparar//
            disparar1 = false;
        }
        if (disparar2 == true && disparo2.posicionY > 600)
        {//si el disparo se sale de la pantalla pueda volver a disparar//
            disparar2 = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background.background, background.posicionX,background.posicionY , null);

        g.drawImage(nave1.nave, nave1.posicionX, nave1.posicionY, null);
        if (disparar1 == true)
        {
            g.drawImage(disparo1.disparo, disparo1.posicionX, disparo1.posicionY, null);
        }
        g.drawImage(nave2.nave, nave2.posicionX, nave2.posicionY, null);
        if (disparar2 == true)
        {
            g.drawImage(disparo2.disparo, disparo2.posicionX, disparo2.posicionY, null);
        }
    }
}
