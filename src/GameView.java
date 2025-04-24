import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    // Instance Variables
    private Game game;
    public static final int WINDOW_WIDTH = 2*612;
    public static final int WINDOW_HEIGHT = 2*390;
    private Image background;

    public GameView(Game g){
        this.game = g;

        background = new ImageIcon("Resources/background.jpg").getImage();

        // Setup the window and the buffer strategy.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("PK Shootout");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g){
        paintBackground(g);
        if(game.getState()>=0) {
            paintSetup(g);
        }
    }

    public void paintBackground(Graphics g){
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

    public void paintSetup(Graphics g){
        game.getGoalie().draw(g);
        game.getBall().draw(g);

    }


}
