import javax.swing.*;
import java.awt.event.*;

public class Game implements MouseListener, MouseMotionListener, ActionListener, KeyListener {

    // Variables
    private GameView window;
    private Ball ball;
    private SpeedArrow arrow;
    private Goalie goalie;
    private Scoreboard scoreboard;
    private int state;
    private int xShotPos;
    private int yShotPos;
    private static final int DELAY_IN_MILLISEC = 10;
    Timer clock;
    public static final int SHOT_TIME = 1000;
    private int shotPower;
    private boolean xReached;
    private boolean yReached;
    private boolean isGoal;
    private int p1ShotCounter;
    private int p2ShotCounter;
    private int totalShotCounter;

    public Game(){
        state = -1;
        this.window = new GameView(this);
        this.window.addMouseListener(this);
        this.window.addMouseMotionListener(this);
        window.addKeyListener(this);

        this.ball = new Ball(window);
        this.arrow = new SpeedArrow(window);
        this.goalie = new Goalie(window);
        this.scoreboard = new Scoreboard(window, this);
        state = 0;

        p1ShotCounter = 0;
        p2ShotCounter = 0;
        totalShotCounter = 0;

        clock = new Timer(DELAY_IN_MILLISEC, this);
        clock.start();

        window.repaint();
    }

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

    public void playGame(){
        // get shot information
        playShot();

//        // go through shot + action of saving
    }

    public void endGame(){
        state = 6;
        window.repaint();
    }

    public void playShot(){
        state = 1;
        xReached = false;
        yReached = false;
        isGoal = true;
        ball.reset();
        goalie.reset();
    }

    public void determineGoal(){
        if (ball.getX() < 224 || ball.getX() + Ball.BALLSIZE > 1000 || ball.getY() + Ball.BALLSIZE > 542 || ball.getY() <
                162){
            isGoal = false;
        }
        System.out.println(isGoal);
        totalShotCounter++;
        if(totalShotCounter%2 == 1){
            p1ShotCounter++;
            scoreboard.getPlayer1Score()[p1ShotCounter - 1] = isGoal;
            System.out.println(scoreboard.getPlayer1Score()[p1ShotCounter - 1]);
        }
        else{
            p2ShotCounter++;
            scoreboard.getPlayer2Score()[p2ShotCounter - 1] = isGoal;
        }
        if(totalShotCounter < 10){
            playShot();
        }
        else{
            endGame();
        }

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

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(state == 1){
            xShotPos = e.getX();
            yShotPos = e.getY();
            state = 2;
            System.out.println("clicked once");
            return;
        }
        if(state == 2){
            // get speed arrow position
            shotPower = arrow.calcPower();

            // call determine speed func
            ball.determineSpeeds(xShotPos, yShotPos, SHOT_TIME/DELAY_IN_MILLISEC, shotPower);
            state = 3;
            return;
        }
        if(state == 3){
            state = 4;
            System.out.println("clicked 3 times");
        }
    }

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state == 2){
            arrow.move();
            window.repaint();
        }
        if(state == 4) {
            ball.move();
            if (ball.getX() < xShotPos + 10 && ball.getX() > xShotPos - 10) {
                // CHANGE THIS TO BE DEPENDENT ON THE STATE
                ball.setDx(0);
                xReached = true;
            }
            if (ball.getY() < yShotPos + 10 && ball.getY() > yShotPos - 10) {
                // CHANGE THIS TO BE DEPENDENT ON THE STATE
                ball.setDy(0);
                yReached = true;
            }
            if(xReached && yReached){ // CHANGE THIS TO INCLUDE IF THE BALL IS SAVED
                state = 5;
                determineGoal();
            }
            // some way to stop repainting
            window.repaint();
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(state == 4){
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    goalie.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    goalie.moveRight();
                    break;
                case KeyEvent.VK_UP:
                    goalie.moveUp();
                    break;
                case KeyEvent.VK_DOWN:
                    goalie.moveDown();
                    break;
            }
            window.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
