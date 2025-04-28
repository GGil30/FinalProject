import javax.swing.*;
import java.awt.event.*;

public class Game implements MouseListener, MouseMotionListener, ActionListener {

    // Variables
    private GameView window;
    private Ball ball;
    private SpeedArrow arrow;
    private Goalie goalie;
    private Scoreboard scoreboard;
    private int state;
    private int xShotPos;
    private int yShotPos;
    private boolean hasClicked;
    private static final int DELAY_IN_MILLISEC = 20;
    Timer clock;

    public Game(){
        state = -1;
        this.window = new GameView(this);
        this.window.addMouseListener(this);
        this.window.addMouseMotionListener(this);

        this.ball = new Ball(window);
        this.arrow = new SpeedArrow(window);
        this.goalie = new Goalie(window);
        state = 0;
        hasClicked = false;

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
        // go through shot + action of saving
    }

    public void getShotInfo(){
        state = 1;
        // print instructions
        // get speed meter

    }

    public void playShot(){
        state = 2;
        // move ball
        if(xShotPos < ball.getX()){
            ball.setDx(-ball.getDx());
        }

        if(yShotPos < ball.getY()){
            ball.setDy(-ball.getDy());
        }

        while (ball.getX() + ball.getDx() < xShotPos)
        ball.move();
        // respond to keeper movements

    }

    public static void main(String[] args) {
        Game g = new Game();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(state == 1){
            xShotPos = e.getX();
            yShotPos = e.getY();
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
        if(state == 2){
            ball.move();
        }

    }
}
