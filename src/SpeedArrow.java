import javax.swing.*;
import java.awt.*;

public class SpeedArrow {

    // Instance variables
    private GameView window;
    private Image arrowImage;
    private Image meter;
    private final int arrowY = (int)((0.77)*GameView.WINDOW_HEIGHT);
    private int arrowX;
    private final int arrowXSize = (int)((0.3)*253);
    private final int arrowYSize = (int)((0.3)*221);
    private final int meterX = (int)((1.0/19)* GameView.WINDOW_WIDTH);
    private final int meterY = (int)((0.85)*GameView.WINDOW_HEIGHT);
    private final int meterXSize = (int)((0.6)*571);
    private final int meterYSize = (int)((0.7)*128);
    private int arrowDX;

    public SpeedArrow(GameView window){
        this.window = window;

        arrowImage = new ImageIcon("Resources/arrow.png").getImage();
        meter = new ImageIcon("Resources/meter.png").getImage();
        arrowX = (int)((1.0/21)* GameView.WINDOW_WIDTH);
    }

    public void draw(Graphics g){
        g.drawImage(meter, meterX, meterY, meterXSize, meterYSize, window);
        g.drawImage(arrowImage, arrowX, arrowY, arrowXSize, arrowYSize, window);
        System.out.println("meter printed");
    }



}
