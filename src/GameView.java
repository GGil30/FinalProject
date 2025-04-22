import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    // Instance Variables
    private Game g;
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 1000;
    private Image background;

    public GameView(Game g){
        this.g = g;

        // Setup the window and the buffer strategy.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("PK Shootout");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }


}
