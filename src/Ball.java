import javax.swing.*;
import java.awt.*;

public class Ball {

    // Instance variables
    private GameView window;
    private Image ballImage;
    public static final int BALLSIZE = 50;
    private int x;
    private int y;
    private int dx;
    private int dy;

    public Ball(GameView window){
        this.window = window;

        dx = 15;
        dy = 15;
        ballImage = new ImageIcon("Resources/ball.png").getImage();
        x = (int) (GameView.WINDOW_WIDTH/2 - (0.5)*(BALLSIZE));
        y = (int) (GameView.WINDOW_HEIGHT*(7.5/8) - (0.5)*(BALLSIZE));
    }

    public void draw(Graphics g){
        g.drawImage(ballImage, x, y, BALLSIZE, BALLSIZE, window);
    }

    public void move(){
            x += dx;
            y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
}
