// Gabriel Gil, 5/9/2025

// Import the necessary classes
import java.awt.*;

public class Scoreboard {
    // Instance variable for backend
    private Game game;

    // Arrays to keep track of each player's shot result
    private boolean[] player1Score;
    private boolean[] player2Score;

    // Constants
    private final int textY = (int) ((0.1)*(GameView.WINDOW_HEIGHT));
    private final int circlesY = (int) ((0.12)*(GameView.WINDOW_HEIGHT));
    private final int p1X = (int) ((0.05)*(GameView.WINDOW_WIDTH));
    private final int p2X = (int) ((0.8)*(GameView.WINDOW_WIDTH));
    private final int circleSize = 25;
    private final int circleSpacing = 10;
    private final int individualShotNumber = 5;

    // Constructor
    public Scoreboard(Game g){
        // Create the score arrays
        player1Score = new boolean[individualShotNumber];
        player2Score = new boolean[individualShotNumber];

        // Save the backend
        this.game = g;
    }

    // Draw method
    public void draw(Graphics g){
        // Draw the player labels
        g.setFont(GameView.normalFont);
        g.setColor(Color.WHITE);
        g.drawString("Player One:", p1X, textY);
        g.drawString("Player Two:", p2X, textY);

        // Call the draw circles method to draw the circles with the shot results
        drawCircles(g);
    }

    // Draw circles method
    public void drawCircles(Graphics g){
        // Draw five circles for each player
        for(int i = 0; i < individualShotNumber; i++){
            // Draw a white outline for the circle
            g.setColor(Color.white);
            g.drawOval(p1X + (i*circleSize) + i*(circleSpacing), circlesY, circleSize, circleSize);
            g.drawOval(p2X + (i*circleSize) + i*(circleSpacing), circlesY, circleSize, circleSize);

            // If the shot has been taken, fill in the circle
            if(i < game.getP1ShotCounter()){
                // If the shot was missed or saved, fill it in red
                if(player1Score[i] == false){
                    g.setColor(Color.RED);
                }
                // If it was scored, fill in the circle green
                else if (player1Score[i] == true){
                    g.setColor(Color.GREEN);
                }
                g.fillOval(p1X + (i*circleSize) + i*(circleSpacing), circlesY, circleSize, circleSize);
            }

            // Do the same color coordination for player 2, given the shot has been taken
            if(i < game.getP2ShotCounter()){
                if(player2Score[i] == false){
                    g.setColor(Color.RED);
                } else if (player2Score[i] == true){
                    g.setColor(Color.GREEN);
                }
                g.fillOval(p2X + (i*circleSize) + i*(circleSpacing), circlesY, circleSize, circleSize);
            }
        }
    }

    // DetermineWinner method to determine the winner of the game
    public int determineWinner(){
        // Variables to track shots scored
        int p1Score = 0;
        int p2Score = 0;

        // Iterate through the scores arrays, and if the shot was scored, add to the score counter
        for(int i = 0; i < individualShotNumber; i++){
            if(player1Score[i]){
                p1Score++;
            }
            if(player2Score[i]){
                p2Score++;
            }
        }

        // Return the player number of the winner if there was one. If it was a tie, return 0
        if(p1Score > p2Score){
            return 1;
        }
        else if (p2Score > p1Score) {
            return 2;
        }
        else{
            return 0;
        }
    }

    // Necessary getters
    public boolean[] getPlayer1Score() {
        return player1Score;
    }

    public boolean[] getPlayer2Score() {
        return player2Score;
    }
}
