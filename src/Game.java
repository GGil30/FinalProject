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
    private static final int DELAY_IN_MILLISEC = 30;
    Timer clock;
    public static final int SHOT_TIME = 2000;
    private int shotPower;

    public Game(){
        state = -1;
        this.window = new GameView(this);
        this.window.addMouseListener(this);
        this.window.addMouseMotionListener(this);

        this.ball = new Ball(window);
        this.arrow = new SpeedArrow(window);
        this.goalie = new Goalie(window);
        state = 0;

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
        state = 1;
//        getShotInfo();
//        // go through shot + action of saving
//        playShot();
    }

    public void getShotInfo(){
        state = 1;
        // print instructions
//        // get speed meter
//        while(hasClicked == false){
//        }
//        hasClicked = false;
//

    }

    public void playShot(){
        state = 2;
        // move ball

        // respond to keeper movements

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

            // call determine speed func
            ball.determineSpeeds(xShotPos, yShotPos, SHOT_TIME/DELAY_IN_MILLISEC);
            state = 3;
            System.out.println("clicked twice");
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

        if(state == 3) {
//            if(xShotPos < ball.getX()){
//                ball.setDx(-ball.getDx());
//            }
//
//            if(yShotPos < ball.getY()){
//                ball.setDy(-ball.getDy());
//            }
            ball.move();
            if (ball.getX() < xShotPos + 10 && ball.getX() > xShotPos - 10) {
                // CHANGE THIS TO BE DEPENDENT ON THE STATE
                ball.setDx(0);
            }
            if (ball.getY() < yShotPos + 10 && ball.getY() > yShotPos - 10) {
                // CHANGE THIS TO BE DEPENDENT ON THE STATE
                ball.setDy(0);
            }
            // some way to stop repainting
            window.repaint();
        }

    }
}
