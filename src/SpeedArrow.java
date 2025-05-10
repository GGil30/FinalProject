// Gabriel Gil, 5/9/2025

// Import the necessary classes
import javax.swing.*;
import java.awt.*;

public class SpeedArrow {

    // Instance variable to store frontend
    private GameView window;

    // Image variables
    private Image arrowImage;
    private Image meter;

    // Variables to keep track of the x position and x movement of the arrow
    private int arrowX;
    private int arrowDX;

    // Necessary constants
    private final int arrowY = (int)((0.77)*GameView.WINDOW_HEIGHT);
    private final int arrowStartX = (int)((0.025)* GameView.WINDOW_WIDTH);
    private final int arrowMaxX = (int)((0.3)* GameView.WINDOW_WIDTH);
    private final int arrowXSize = (int)((0.3)*253);
    private final int arrowYSize = (int)((0.3)*221);
    private final int meterX = (int)((1.0/19)* GameView.WINDOW_WIDTH);
    private final int meterY = (int)((0.85)*GameView.WINDOW_HEIGHT);
    private final int meterXSize = (int)((0.6)*571);
    private final int meterYSize = (int)((0.7)*128);
    private final double powerFactor = 0.01;
    private final int maxPower = 9;
    private final int minPower = 1;

    // Constructor
    public SpeedArrow(GameView window){
        // Save the frontend
        this.window = window;

        // Retrieve the images
        arrowImage = new ImageIcon("Resources/arrow.png").getImage();
        meter = new ImageIcon("Resources/meter.png").getImage();

        // Set the arrow start positioning and set its dx to 20
        arrowX = arrowStartX;
        arrowDX = 20;
    }

    // Move method to move the arrow
    public void move(){
        // If the arrow has reached one of its bounds, update its speed to move in the oppositve direction
        if(arrowX > arrowMaxX || arrowX < arrowStartX){
            arrowDX = - arrowDX;
        }
        // Move the arrow
        arrowX += arrowDX;
    }

    // Calculate power method to calculate the power of the shot based on where the arrow was stopped
    public int calcPower(){
        // Set power to a factor of the distance between the arrow's start and where it was stopped
        int power = (int) ((powerFactor)*(arrowX - arrowStartX));

        // Adjust the power to be capped with a minimum and maximum speed if it is outside that range
        if (power < 2){
            power = minPower;
        }
        else if(power > maxPower){
            power = maxPower;
        }
        // Return the power
        return power;
    }

    // Draw method
    public void draw(Graphics g){
        // Draw the speed meter
        g.drawImage(meter, meterX, meterY, meterXSize, meterYSize, window);

        // Draw the arrow
        g.drawImage(arrowImage, arrowX, arrowY, arrowXSize, arrowYSize, window);
    }
}
