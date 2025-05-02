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


    public Goalie(GameView window){
        this.window = window;

        gkImage = new ImageIcon("Resources/goalie.png").getImage();
        gloves = new ImageIcon("Resources/glovesCopy.jpg").getImage();

        x = (int)(GameView.WINDOW_WIDTH/2 - (1.0/2)*(GKXSIZE));
        y = (int)(GameView.WINDOW_WIDTH/(4.5));

    }

    public void draw(Graphics g){
        g.drawImage(gkImage, x, y, GKXSIZE, GKYSIZE, window);
    }
}
