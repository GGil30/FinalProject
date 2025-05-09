import javax.swing.*;
import java.awt.*;

public class Ball {

    // Instance variables
    private GameView window;
    private Image ballImage;
    public static final int BALLSIZE = 50;
    private int x;
    private int y;
    private final int startX = (int) (GameView.WINDOW_WIDTH/2 - (0.5)*(BALLSIZE));;
    private final int startY = (int) (GameView.WINDOW_HEIGHT*(7.5/8) - (0.5)*(BALLSIZE));
    private int dx;
    private int dy;

    public Ball(GameView window){
        this.window = window;

        dx = 15;
        dy = 15;
        ballImage = new ImageIcon("Resources/ball.png").getImage();
        x = startX;
        y = startY;
    }

    public void draw(Graphics g){
        g.drawImage(ballImage, x, y, BALLSIZE, BALLSIZE, window);
    }

    public void move(){
//            x += dx;
//            y += dy;
        x += 15;
        y -= 15;
    }

    public void reset(){
        x = startX;
        y = startY;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void determineSpeeds(int xFinal, int yFinal, int baseSpeed, int power){
        int xDiff = xFinal - x;
        int yDiff = yFinal - y;

        dx = (xDiff / baseSpeed * (power));
        System.out.println(dx);
        dy = (yDiff / baseSpeed * (power));
        System.out.println(dy);
    }
}
