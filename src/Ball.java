// Gabriel Gil, 5/9/2025

// Import the necessary classes
import javax.swing.*;
import java.awt.*;

public class Ball {

    // Instance variable for the frontend
    private GameView window;

    // Image variable
    private Image ballImage;

    // Variables for the ball's position and movement
    private int x;
    private int y;
    private int dx;
    private int dy;

    // Constants
    public static final int BALLSIZE = 50;
    private final int startX = (int) (GameView.WINDOW_WIDTH/2 - (0.5)*(BALLSIZE));;
    private final int startY = (int) (GameView.WINDOW_HEIGHT*(7.5/8) - (0.5)*(BALLSIZE));
    private final int maxSpeed = 20;

    // Constructor
    public Ball(GameView window){
        // Save the frontend
        this.window = window;

        // Retrieve the image
        ballImage = new ImageIcon("Resources/ball.png").getImage();

        // Set the ball's starting position
        x = startX;
        y = startY;
    }

    // Draw method to draw the ball
    public void draw(Graphics g){
        g.drawImage(ballImage, x, y, BALLSIZE, BALLSIZE, window);
    }

    // Move method
    public void move(){
        // Move the ball based on dx and dy
        x += dx;
        y += dy;
    }

    // Reset method
    public void reset(){
        // Reset the ball to its starting position
        x = startX;
        y = startY;
    }

    // Getters and setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    // Determine speed method
    public void determineSpeeds(int xFinal, int yFinal, int baseSpeed, int power){
        // Find the difference in pixels between the final shot position and the ball's current position
        int xDiff = xFinal - x;
        int yDiff = yFinal - y;

        // Calculate dx, and cap it at maxSpeed
        dx = (xDiff / baseSpeed * (power));
        if(Math.abs(dx) > maxSpeed){
            if(dx < 0){
                dx = -maxSpeed;
            }
            else {
                dx = maxSpeed;
            }
        }
        // Calculate dy, and cap it at maxSpeed
        dy = (yDiff / baseSpeed * (power));
        if(Math.abs(dy) > maxSpeed){
            if(dy < 0){
                dy = -maxSpeed;
            }
            else{
                dy = maxSpeed;
            }
        }
    }
}
