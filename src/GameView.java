import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

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
        createBufferStrategy(2);
    }



    public void paint(Graphics g){
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void myPaint(Graphics g){
        paintBackground(g);
        if(game.getState()>=0 && game.getState() < 6) {
            paintSetup(g);
        }
        else if(game.getState() == 6){
            paintEnding(g);
        }
    }

    public void paintBackground(Graphics g){
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

    public void paintSetup(Graphics g){
        game.getGoalie().draw(g);
        game.getBall().draw(g);
        game.getArrow().draw(g);
        game.getScoreboard().draw(g);
    }

    public void paintEnding(Graphics g){
        // to implement
    }

}
