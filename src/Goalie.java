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
    private int dx;
    private int dy;
    private int handsMinX;
    private int handsMaxX;
    private int handsMaxY;
    private int handsMinY;
    private int bodyMinX;
    private int bodyMaxX;
    private int bodyMinY;
    private int bodyMaxY;




    public Goalie(GameView window){
        this.window = window;

        gkImage = new ImageIcon("Resources/goalie.png").getImage();
        gloves = new ImageIcon("Resources/glovesCopy.jpg").getImage();

        x = startX;
        y = startY;

        handsMinX = startX + (int)((0.8)*4);
        handsMaxX = handsMinX + (int)((0.8)*(307));
        handsMinY = startY + (int)(0.8*(6));
        handsMaxY = handsMinY + (int)(0.8*84);
        bodyMinX = startX + (int)(0.8*56);
        bodyMaxX = bodyMinX + (int)(0.8*170);
        bodyMinY = startY + (int)(0.8*90);
        bodyMaxY = bodyMinY + (int)(0.8*247);

        dx = 0;
        dy = 0;

    }

    public void draw(Graphics g){
        g.drawImage(gkImage, x, y, GKXSIZE, GKYSIZE, window);
    }

    public void reset(){
        x = startX;
        y = startY;
        dx = 0;
        dy = 0;
    }

    public void move(){
        x += dx;
        y += dy;
        handsMinX += dx;
        handsMaxX += dx;
        handsMinY += dy;
        handsMaxY += dy;
        bodyMinX += dx;
        bodyMaxX += dy;
        bodyMinY += dy;
        bodyMaxY += dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getHandsMinX() {
        return handsMinX;
    }

    public int getHandsMaxX() {
        return handsMaxX;
    }

    public int getHandsMaxY() {
        return handsMaxY;
    }

    public int getHandsMinY() {
        return handsMinY;
    }

    public int getBodyMinX() {
        return bodyMinX;
    }

    public int getBodyMaxX() {
        return bodyMaxX;
    }

    public int getBodyMinY() {
        return bodyMinY;
    }

    public int getBodyMaxY() {
        return bodyMaxY;
    }
}
