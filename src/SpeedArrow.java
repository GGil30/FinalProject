import javax.swing.*;
import java.awt.*;

public class SpeedArrow {

    // Instance variables
    private GameView window;
    private Image arrowImage;
    private Image meter;

    public SpeedArrow(GameView window){
        this.window = window;

        arrowImage = new ImageIcon("Resources/arrow.png").getImage();
        meter = new ImageIcon("Resources/powerbar.webp").getImage();
    }
}
