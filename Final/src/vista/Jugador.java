package vista;
import java.awt.*;
public class Jugador {
    public String login;
    public int x;
    public int y;
    public static boolean J1;
    public static boolean J2;
    public Jugador(String l, int x, int y)
    {
        login = l;
        this.x = x;
        this.y = y;
        J1 = false;
        J2 = false;
    }

}
