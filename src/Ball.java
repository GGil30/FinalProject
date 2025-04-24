import javax.swing.*;
import java.awt.*;

public class Ball {

    // Instance variables
    private GameView window;
    private Image ballImage;
    public static final int BALLSIZE = 50;
    private int x;
    private int y;

    public Ball(GameView window){
        this.window = window;

        ballImage = new ImageIcon("Resources/ball.png").getImage();
        x = (int) (GameView.WINDOW_WIDTH/2 - (0.5)*(BALLSIZE));
        y = (int) (GameView.WINDOW_HEIGHT*(7.0/8));
    }

    public void draw(Graphics g){

        g.drawImage(ballImage, x, y, BALLSIZE, BALLSIZE, window);
    }
}
