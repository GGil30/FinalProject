// Gabriel Gil, 5/9/2025

// Import the necessary classes
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameView extends JFrame {

    // Instance variable to save the backend
    private Game game;

    // Variables for the necessary images
    private Image background;
    private Image instructionsBackground;

    // Constants for fonts and necessary frontend numbers
    public static final int WINDOW_WIDTH = 2*612;
    public static final int WINDOW_HEIGHT = 2*390;
    public static final Font normalFont = new Font(Font.SERIF, Font.BOLD, 20);
    public static final Font bigFont = new Font(Font.SERIF, Font.BOLD, 50);
    private final int instructionsXStart = 50;
    private final int instructionsYStart = 245;
    private final int instructionsSpacing = 30;
    private final int winScreenOffset = 130;

    // Constructor
    public GameView(Game g){
        // Set the game equal to the passed in backend
        this.game = g;

        // Retrieve the images for the image variables
        background = new ImageIcon("Resources/background.jpg").getImage();
        instructionsBackground = new ImageIcon("Resources/instructionsbg3.jpg").getImage();

        // Setup the window and the buffer strategy.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("PK Shootout");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
    }

    // Paint method for the buffer strategy
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

    // myPaint method
    public void myPaint(Graphics g){
        // If in the instructions state, paint the instructions
        if(game.getState() == 0){
            paintInstructions(g);
        }
        // If in the game state, paint the background and setup with all the game's elements
        if(game.getState()>0 && game.getState() < 6) {
            paintBackground(g);
            paintSetup(g);
        }
        // If in the end state, paint the ending screen
        else if(game.getState() == 6){
            paintEnding(g);
        }
    }

    // Paint instructions method
    public void paintInstructions(Graphics g){
        // Draw the instructions background
        g.drawImage(instructionsBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        // Draw the instructions
        g.setFont(normalFont);
        g.setColor(Color.WHITE);
        g.drawString("Welcome to Penalty Shootout! In this two player game, both of you will be competing in a " +
                "full, 5-shot penalty shootout. Player 1", instructionsXStart, instructionsYStart);
        g.drawString("shoots first, then player 2, and so on. The person with the most goals at the end wins, and if the" +
                " number of goals scored is the same,", instructionsXStart, instructionsYStart +
                instructionsSpacing);
        g.drawString("then the game ends in a tie. As for gameplay instructions, each shooting turn consists of two " +
                "clicks: the first click determines", instructionsXStart, instructionsYStart +
                2 * instructionsSpacing);
        g.drawString("where you shoot, so use your mouse to click the exact point that you " +
                "want to shoot at. The second click determines the speed of your", instructionsXStart, instructionsYStart +
                3 * instructionsSpacing);
        g.drawString("shot, stopping the arrow on the powerbar after you click the screen and determining the speed of " +
                "your shot based on that.", instructionsXStart, instructionsYStart +
                4 * instructionsSpacing);
        g.drawString("After the shooter has done their two clicks, then the person whose turn it is to be the goalkeeper" +
                " tries to save the shot.", instructionsXStart, instructionsYStart +
                5 * instructionsSpacing);
        g.drawString("The goalkeeper can control the keeper using the arrow keys, and once they click a key to move " +
                "the keeper the shot will be activated.", instructionsXStart, instructionsYStart +
                6 * instructionsSpacing);
        g.drawString("Good luck! Please click anywhere on the screen to start the game.", instructionsXStart, instructionsYStart +
                7 * instructionsSpacing);
    }

    // Paint background method
    public void paintBackground(Graphics g){
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

    // Paint setup method to draw each element
    public void paintSetup(Graphics g){
        // Call each element's draw method
        game.getGoalie().draw(g);
        game.getBall().draw(g);
        game.getArrow().draw(g);
        game.getScoreboard().draw(g);
    }

    // Paint ending method to paint the endscreen
    public void paintEnding(Graphics g){
        // Draw the ending background
        g.drawImage(instructionsBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        // Draw the ending text, adjusting the text based on the outcome of the game
        g.setFont(bigFont);
        g.setColor(Color.WHITE);
        String winPrint;
        if(game.getWinner() == 1){
            winPrint = "Player 1 wins!";
        }
        else if(game.getWinner() == 2){
            winPrint = "Player 2 wins!";
        }
        else{
            winPrint = "Tie game!";
        }
        g.drawString(winPrint, WINDOW_WIDTH/2 - winScreenOffset, WINDOW_HEIGHT/2);
    }
}
