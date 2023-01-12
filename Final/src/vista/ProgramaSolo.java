package vista;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ProgramaSolo extends JPanel
{
    //atributos
    Nave nave1;
    boolean teclaIzq;
    boolean teclaDer;
    boolean teclaEsp;
    boolean disparar1;
    Fondo background;
    Marcianos[][] matrizMarcianos = new Marcianos[6][8];
    Disparo disparo1;

    boolean gameOver;
    int score;

    //constructor
    public ProgramaSolo()
    {
        int X = 165; //posicion inicial X en la pantalla para el marciano[1]//
        int Y = 20; //posicion inicial Y en la pantalla para la columna 1//
        score = 0;
        for (int i = 0; i < 6; i++)
        { //instanciacion de los marcianos en la matriz i, j//
            for (int j = 0; j < 8; j++)
            {
                Marcianos marciano = new Marcianos();
                marciano.posicionX = X; // Separacion horizontal de los marcianos por 60 pixeles//
                marciano.posicionY = Y;
                matrizMarcianos[i][j] = marciano;
                X += 60;
            }
            X = 165;
            Y += 45; // cada columna se separa por 45 pixeles.//
        }
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_LEFT:
                        teclaIzq = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        teclaDer = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        teclaEsp = true;
                        break;

                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                switch ( e.getKeyCode())
                {
                    case KeyEvent.VK_LEFT: //Obtiene el valor de la tecla izquierda//
                        teclaIzq = false;
                        break;
                    case KeyEvent.VK_RIGHT: //Obtiene el valor de la tecla derecha//
                        teclaDer = false;
                        break;
                    case KeyEvent.VK_SPACE: //Obtiene el valor de la tecla espacio//
                        teclaEsp = false;
                        break;

                }
            }
        });

        background = new Fondo(); //instancia fondo 1//
        nave1 = new Nave(200, 520, "img/nave1.png"); //instancia la nave//
        disparo1 = new Disparo(-10,"img/disparo1.png"); //instancia el disparo//
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
            if (gameOver == true)
            {
                JOptionPane.showMessageDialog(null, "Game Over " + "tu puntaje es: " + score);
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
    public void controladorEventos()
    { //Maneja las acciones realizadas por el jugador//
        nave1.velocidadX = 0;

        if(teclaIzq==true)
        { //tecla izquierda presionada//
            nave1.velocidadX = -5; //movimiento para la izquierda//
        } else if (teclaDer==true)
        { //tecla derecha presionada//
            nave1.velocidadX = 5; //movimiento para la derecha//
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

    }


    public void update()
    {

        nave1.posicionX += nave1.velocidadX; // actualiza el movimiento del jugador//
        if(disparar1 == true)
        { //al disparar la posicion y del disparo es añadido a la velocidad y del disparo//
            disparo1.posicionY+=disparo1.velocidadY;
        }

        for (int i = 0; i < 6; i++)
        { // mueve el array de marcianos horizontalmente//
            for(int j = 0; j < 8; j++)
            { // suma la posicion x de los marcianos con su velocidad en x//
                matrizMarcianos[i][j].posicionX += matrizMarcianos[i][j].velocidadX;
            }
        }
        detectorColision();
    }

    public void render()
    {
        repaint(); //es el redibujar
    }

    public void detectorColision()
    {
        if(nave1.posicionX+(nave1.tamanoX) > VentanaSolo.XPantalla || nave1.posicionX < 0)
        { // colision horizontal evita que la nave se salga de la ventana//
            nave1.posicionX -= nave1.velocidadX;
        }
        for (int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 8; j++)
            { // colision de los disparos a la matriz de marcianos//
                Marcianos actual = matrizMarcianos[i][j];
                if (actual.esVisible == false)
                {
                    continue;
                }
                if (disparo1.posicionX <= actual.posicionX + actual.tamanoX && disparo1.posicionX >= actual.posicionX && disparo1.posicionY <= actual.posicionY + actual.tamanoY && disparo1.posicionY >= actual.posicionY && disparo1.active == true)
                {
                    actual.esVisible = false; // hace que el proyectil no pase a traves de los enemigos//
                    actual.enemigo = null; // borra la imagen del enemigo//
                    disparo1.active = false; //disparo deja de estar activo//
                    disparo1.disparo = null; // imagen del disparo es borrada//
                    disparar1 = false; // permite disparar de nuevo//
                    score += 100;
                }
            }
        }

        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 8; j++)
            { // colision de los marcianos con la pared de la ventana//
                Marcianos actual = matrizMarcianos[i][j];
                if (actual.posicionX + actual.tamanoX > VentanaSolo.XPantalla)
                { //colision con el lado derecho//
                    actual.posicionY += actual.tamanoY; //desciende a la altura de un alien//
                    actual.velocidadX *= -1.2; //aumenta en 1.2 la velocidad de los marcianos cuando chocan con una pared//
                }
                if (actual.posicionX <= 0)
                { //colision con el lado izquierdo//
                    actual.posicionY += matrizMarcianos[i][j].tamanoY; //desciende a la altura de un alien//
                    actual.velocidadX *= -1.2; //aumenta en 1.2 la velocidad de los marcianos cuando chocan con una pared//
                }
                if (actual.posicionY + actual.tamanoY > nave1.posicionY && actual.esVisible == true)
                {
                    gameOver = true;
                }
            }
        }
        if (disparar1 == true && disparo1.posicionY < 0)
        {//si el disparo se sale de la pantalla pueda volver a disparar//
            disparar1 = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background.background, background.posicionX,background.posicionY , null);
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 8; j++)
            { // colision de los marcianos con la pared de la ventana//
                g.drawImage(matrizMarcianos[i][j].enemigo, matrizMarcianos[i][j].posicionX, matrizMarcianos[i][j].posicionY, null) ;
            }
        }
        g.drawImage(nave1.nave, nave1.posicionX, nave1.posicionY, null);
        if (disparar1 == true)
        {
            g.drawImage(disparo1.disparo, disparo1.posicionX, disparo1.posicionY, null);
        }
    }
}
