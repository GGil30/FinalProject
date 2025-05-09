import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameView extends JFrame {

    // Instance Variables
    private Game game;
    public static final int WINDOW_WIDTH = 2*612;
    public static final int WINDOW_HEIGHT = 2*390;
    private Image background;
    private Image instructionsBackground;
    public static final Font font = new Font(Font.SERIF, Font.BOLD, 20);

    public GameView(Game g){
        this.game = g;

        background = new ImageIcon("Resources/background.jpg").getImage();
        instructionsBackground = new ImageIcon("Resources/instructionsbg.jpg").getImage();

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
        if(game.getState() == -1){
            paintInstructions(g);
        }
        if(game.getState()>=0 && game.getState() < 6) {
            paintBackground(g);
            paintSetup(g);
        }
        else if(game.getState() == 6){
            paintEnding(g);
        }
    }

    public void paintInstructions(Graphics g){
        g.drawImage(instructionsBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Welcome to Penalty Shootout! In this two player game, both of you will be competing in a " +
                "full, 5-shot penatly shootout. Player 1 shoots first, then player 2, and so on. The person with the " +
                "most goals at the end wins, and if the number of goals scored is the same, then the game ends in a tie. " +
                "As for gameplay instructions, each shooting turn consists of two clicks: the first click determines " +
                "where you shoot, so use your mouse to click the exact point that you want to shoot at. The second click " +
                "determines the speed of your shot, stopping the arrow on the powerbar after you click the screen and " +
                "determining the speed of your shot based on that. After the shooter has done their two clicks, then the " +
                "person whose turn it is to be the goalkeeper clicks the screen once, activating the shot. Once the shot" +
                "is activated, the person who is the goalkeeper will be able to control the keeper using the arrow keys. " +
                "Good luck! Please click anywhere on the screen to start the game.", 50, 100);
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
