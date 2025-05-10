// Gabriel Gil, 5/9/2025

// Import the necessary classes
import javax.swing.*;
import java.awt.event.*;

public class Game implements MouseListener, ActionListener, KeyListener {

    // Instance variables to implement the frontend, ball, speed arrow, goalie, and scoreboard for the game
    private GameView window;
    private Ball ball;
    private SpeedArrow arrow;
    private Goalie goalie;
    private Scoreboard scoreboard;

    // State instance variable
    private int state;

    // Variables to keep track of the desired shot position
    private int xShotPos;
    private int yShotPos;

    // Delay in milliseconds and timer variables for animation
    private static final int DELAY_IN_MILLISEC = 30;
    Timer clock;

    // Various other variables to keep track of necessary information for each shot
    private int shotPower;
    private boolean xReached;
    private boolean yReached;
    private boolean isGoal;
    private int p1ShotCounter;
    private int p2ShotCounter;
    private int totalShotCounter;
    private boolean saved;
    private int winner;

    // Constants to avoid magic numbers
    public static final int SHOT_TIME = 1000;
    private final int gkSpeed = 12;
    private final int shotMargin = 20;
    private final int goalMinX = 224;
    private final int goalMaxX = 1000;
    private final int goalMinY = 162;
    private final int goalMaxY = 542;
    private final int maxShots = 10;

    // Constructor
    public Game(){
        // Set the state to 0
        state = 0;

        // Create the frontend and pass in this as the backend for it
        this.window = new GameView(this);

        // Add this class as the listener for the mouse and key information
        this.window.addMouseListener(this);
        window.addKeyListener(this);

        // Create the ball, arrow, and goalie, passing in the frontend to each so they can draw themselves
        this.ball = new Ball(window);
        this.arrow = new SpeedArrow(window);
        this.goalie = new Goalie(window);

        // Pass in this to the scoreboard so it can access necessary info here to update the score
        this.scoreboard = new Scoreboard(this);

        // Set shot counters to 0
        p1ShotCounter = 0;
        p2ShotCounter = 0;
        totalShotCounter = 0;

        // Create the timer for animation and start it
        clock = new Timer(DELAY_IN_MILLISEC, this);
        clock.start();

        // Paint the window to display the instructions for the game
        window.repaint();
    }

    // Play game method that starts the game, however game mechanics respond to user mouse and key input
    public void playGame(){
    }

    // End game method to update the winner of the game and paint the end screen
    public void endGame(){
        // Update the state to say that the game is done
        state = 6;

        // Call the determine winner method in scoreboard to find the winner of the game
        winner = scoreboard.determineWinner();

        // Repaint the window to show the end screen
        window.repaint();
    }

    // Play shot method to reset everything to allow for the next shot
    public void playShot(){
        // Set state = 1, signifying that the game is waiting for the user input of where they want the shot to go
        state = 1;

        // Reset boolean variables to false or true respectively to baseline status to be changed as the shot occurs
        xReached = false;
        yReached = false;
        saved = false;
        isGoal = true;

        // Reset the ball and keeper positions in the frame and repaint the window to show their reset
        ball.reset();
        goalie.reset();
        window.repaint();
    }

    // Determine goal method to determine if a goal was scored
    public void determineGoal(){
        // If the ball was saved, then no goal
        if(saved){
            isGoal = false;
        }
        // If the ball, or part of the ball, is touching the bounds of the goal, then the ball did not go in, and no
        // goal
        else if (ball.getX() < goalMinX || ball.getX() + Ball.BALLSIZE > goalMaxX || ball.getY() + Ball.BALLSIZE >
                goalMaxY || ball.getY() < goalMinY){
            isGoal = false;
        }
        // Increase the total shot counter
        totalShotCounter++;

        // Since player one shoots first, if there has been an odd number of shots, update player 1's score accordingly
        if(totalShotCounter%2 == 1){
            // Add to the individual counter
            p1ShotCounter++;

            // Update player 1's score in scoreboard
            scoreboard.getPlayer1Score()[p1ShotCounter - 1] = isGoal;
        }
        // If it has been an even number of shots, update player 2's score accordingly
        else{
            // Add to the individual counter
            p2ShotCounter++;

            // Update player 2's score in scoreboard
            scoreboard.getPlayer2Score()[p2ShotCounter - 1] = isGoal;
        }
        // If not all shots have been taken, get ready for the next shot by calling playShot
        if(totalShotCounter < maxShots){
            playShot();
        }
        // End the game if all shots have been taken
        else{
            endGame();
        }

    }

    // Main method to construct the game and start the game by calling playGame
    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }

    // Necessary getter methods
    public Ball getBall() {
        return ball;
    }

    public SpeedArrow getArrow() {
        return arrow;
    }

    public Goalie getGoalie() {
        return goalie;
    }

    public int getState() {
        return state;
    }

    public int getP1ShotCounter() {
        return p1ShotCounter;
    }

    public int getP2ShotCounter() {
        return p2ShotCounter;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public int getWinner() {
        return winner;
    }

    // mouseClicked method used to receive shot input + transition from start screen to actual game
    @Override
    public void mouseClicked(MouseEvent e) {
        // If the state = 0, start the game
        if(state == 0){
            playShot();
            return;
        }
        // If the state = 1, use the click's position to save where the shooter wants to shoot
        if(state == 1){
            xShotPos = e.getX();
            yShotPos = e.getY();

            // Set the state = 2, signifying that the game is waiting for user speed input
            state = 2;
            return;
        }
        // If the state = 2, this click stops the speed arrow and determines the shot power
        if(state == 2){
            // Get the shot power by calling calcPower in the arrow class
            shotPower = arrow.calcPower();

            // Call determine speed function in the ball class to find dx and dy for the ball and pass in necessary info
            // like the shot's final position, the baseSpeed, and the shot's power
            ball.determineSpeeds(xShotPos, yShotPos, SHOT_TIME/DELAY_IN_MILLISEC, shotPower);

            // Set the state = 3, signifying the game is waiting for the person playing goalie to move the goalie and
            // start the shot
            state = 3;
        }
    }

    // Other necessary mouseListener methods

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // ActionPerformed method to run the animation and do certain things based on the game's state
    @Override
    public void actionPerformed(ActionEvent e) {
        // If we are waiting for user to stop the speed arrow, keep moving the arrow
        if (state == 2){
            arrow.move();
        }
        // If the shot has been activated, move the ball and the goalie accordingly
        if(state == 4) {
            // Move the ball showing its motion to its final position
            ball.move();

            // Move the goalkeeper
            goalie.move();

            // If the ball is within the margin of its final position for either the x or y direction, stop its movement
            // in that direction and update that its reached the final position for that direction
            if (ball.getX() <= xShotPos + shotMargin && ball.getX() >= xShotPos - shotMargin) {
                ball.setDx(0);
                xReached = true;
            }
            if (ball.getY() <+ yShotPos + shotMargin && ball.getY() >= yShotPos - shotMargin) {
                ball.setDy(0);
                yReached = true;
            }
            // If the ball is within the boxes representing the goalie's body / area that he can save, update that the
            // ball has been saved, change the state to signify that it is being determined whether the ball was scored
            // or not, and call the determineGoal method
            if(((ball.getX() + Ball.BALLSIZE >= goalie.getHandsMinX() && ball.getX() <= goalie.getHandsMaxX()) &&
                    ((ball.getY() + Ball.BALLSIZE >= goalie.getHandsMinY()) && (ball.getY() <= goalie.getHandsMaxY()) ))
                    || (((ball.getX() + Ball.BALLSIZE >= goalie.getBodyMinX()) && (ball.getX() <= goalie.getBodyMaxX()))
                    && ((ball.getY() + Ball.BALLSIZE >= goalie.getBodyMinY()) && (ball.getY() <=
                    goalie.getBodyMaxY())))){
                saved = true;
                state = 5;
                determineGoal();
            }
            // If the ball has reached its final x and y positions within the margin, update the state accordingly and
            // call the determine goal method
            if(xReached && yReached){
                state = 5;
                determineGoal();
            }
        }
        // Repaint the window
        window.repaint();
    }

    // Necessary keyListener methods
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Use key pressed to change the goalie's movement speed
    @Override
    public void keyPressed(KeyEvent e) {
        // If we are waiting for the person playing goalie to be ready, and they start to move the keeper, update that
        // the shot has been activated
        if(state == 3){
            state = 4;
        }
        // If we are in an active shot, move the keeper up, down, left, and right by setting the dx or dy accordingly
        if(state == 4){
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    goalie.setDx(-gkSpeed);
                    break;
                case KeyEvent.VK_RIGHT:
                    goalie.setDx(gkSpeed);;
                    break;
                case KeyEvent.VK_UP:
                    goalie.setDy(-gkSpeed);
                    break;
                case KeyEvent.VK_DOWN:
                    goalie.setDy(gkSpeed);
                    break;
            }
            // Repaint the window
            window.repaint();
        }
    }

    // Key released method to stop the goalie's movement in that direction when that key is lifted
    @Override
    public void keyReleased(KeyEvent e) {
        // When an arrow key is lifted, set the movement speed in that direction to 0
        if(state == 4){
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    goalie.setDx(0);
                    break;
                case KeyEvent.VK_RIGHT:
                    goalie.setDx(0);;
                    break;
                case KeyEvent.VK_UP:
                    goalie.setDy(0);
                    break;
                case KeyEvent.VK_DOWN:
                    goalie.setDy(0);
                    break;
            }
            // Repaint the window
            window.repaint();
        }
    }
}
