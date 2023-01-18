package vista;

public class Jugador {
    public static String login;
    public int x;
    public int y;
    public static boolean J1;
    public static boolean J2;
    public Jugador(String l, int x, int y)
    {
        login = l;
        this.x = x;
        this.y = y;
        //Nave.posicionX = x;
        //Nave.posicionY = y;
        J1 = true;
        J2 = true;
    }

}
