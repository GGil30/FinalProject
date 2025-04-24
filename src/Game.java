public class Game {

    // Variables
    private GameView window;
    private Ball ball;
    private SpeedArrow arrow;
    private Goalie goalie;
    private Scoreboard scoreboard;
    private int state;

    public Game(){
        state = -1;
        this.window = new GameView(this);

        this.ball = new Ball(window);
        this.arrow = new SpeedArrow(window);
        this.goalie = new Goalie(window);
        state = 0;

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

    public static void main(String[] args) {
        Game g = new Game();
    }
}
