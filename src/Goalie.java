import javax.swing.*;
import java.awt.*;

public class Goalie {

    // Instance variables
    private GameView window;
    private Image gkImage;
    private Image gloves;
    public static final int GKXSIZE = (int)((4.0/5)*(311));
    public static final int GKYSIZE = (int)((4.0/5)*341);
    private int x;
    private int y;
    private final int startX = (int)(GameView.WINDOW_WIDTH/2 - (1.0/2)*(GKXSIZE));
    private final int startY = (int)(GameView.WINDOW_WIDTH/(4.5));
    private int speed = 15;
    private int dx;
    private int dy;


    public Goalie(GameView window){
        this.window = window;

        gkImage = new ImageIcon("Resources/goalie.png").getImage();
        gloves = new ImageIcon("Resources/glovesCopy.jpg").getImage();

        x = startX;
        y = startY;

        dx = 0;
        dy = 0;

    }

    public void draw(Graphics g){
        g.drawImage(gkImage, x, y, GKXSIZE, GKYSIZE, window);
    }

    public void reset(){
        x = startX;
        y = startY;
    }

    public void move(){
        x += dx;
        y += dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
}
