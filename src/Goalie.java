// Gabriel Gil, 5/9/2025

// Import the necessary classes
import javax.swing.*;
import java.awt.*;

public class Goalie {

    // Instance variables to store the frontend and goalie image
    private GameView window;
    private Image gkImage;

    // Instance variables to hold info about the keeper's position, movement, and his saving area's position
    private int x;
    private int y;
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

    // Necessary constants
    private final int startX = (int)(GameView.WINDOW_WIDTH/2 - (1.0/2)*(GKXSIZE));
    private final int startY = (int)(GameView.WINDOW_WIDTH/(3.8));
    public static final int GKXSIZE = (int)((3.0/5)*(311));
    public static final int GKYSIZE = (int)((3.0/5)*341);
    private final int handsMinXStart = startX + (int)((0.6)*4);
    private final int handsMaxXStart = handsMinXStart + (int)((0.6)*(307));
    private final int handsMinYStart = startY + (int)(0.6*(6));
    private final int handsMaxYStart = handsMinYStart + (int)(0.6*84);
    private final int bodyMinXStart = startX + (int)(0.6*56);
    private final int bodyMaxXStart = bodyMinXStart + (int)(0.6*170);
    private final int bodyMinYStart = startY + (int)(0.6*90);
    private final int bodyMaxYStart = bodyMinYStart + (int)(0.6*247);

    // Constructor
    public Goalie(GameView window){
        // Store the frontend
        this.window = window;

        // Retrieve goalie image
        gkImage = new ImageIcon("Resources/goalie.png").getImage();

        // Set the start position for the goalie and its saving area
        x = startX;
        y = startY;
        handsMinX = handsMinXStart;
        handsMaxX = handsMaxXStart;
        handsMinY = handsMinYStart;
        handsMaxY = handsMaxYStart;
        bodyMinX = bodyMinXStart;
        bodyMaxX = bodyMaxXStart;
        bodyMinY = bodyMinYStart;
        bodyMaxY = bodyMaxYStart;

        // Set movement speed to 0
        dx = 0;
        dy = 0;

    }

    // Draw method
    public void draw(Graphics g){
        // Draw the goalkeeper using its position and size
        g.drawImage(gkImage, x, y, GKXSIZE, GKYSIZE, window);
    }

    // Reset method to rest the keeper's positioning and speed
    public void reset(){
        // Reset positioning to start positioning and dx and dy to 0
        x = startX;
        y = startY;
        dx = 0;
        dy = 0;
        handsMinX = handsMinXStart;
        handsMaxX = handsMaxXStart;
        handsMinY = handsMinYStart;
        handsMaxY = handsMaxYStart;
        bodyMinX = bodyMinXStart;
        bodyMaxX = bodyMaxXStart;
        bodyMinY = bodyMinYStart;
        bodyMaxY = bodyMaxYStart;
    }

    // Move method
    public void move(){
        // Move the keeper's x and y, along with its saving area, by dx and dy appropriately
        x += dx;
        y += dy;
        handsMinX += dx;
        handsMaxX += dx;
        handsMinY += dy;
        handsMaxY += dy;
        bodyMinX += dx;
        bodyMaxX += dx;
        bodyMinY += dy;
        bodyMaxY += dy;
    }

    // Necessary getters and setters
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
