import java.awt.*;

public class Scoreboard {

    // Instance variables
    private GameView window;
    private final int textY = (int) ((0.1)*(GameView.WINDOW_HEIGHT));
    private final int circlesY = (int) ((0.12)*(GameView.WINDOW_HEIGHT));
    private final int p1X = (int) ((0.05)*(GameView.WINDOW_WIDTH));
    private final int p2X = (int) ((0.8)*(GameView.WINDOW_WIDTH));
    private final int circleSize = 25;
    private final int circleSpacing = 10;
    private boolean[] player1Score;
    private boolean[] player2Score;
    private Game game;


    public Scoreboard(GameView window, Game g){
        this.window = window;
        player1Score = new boolean[5];
        player2Score = new boolean[5];
        this.game = g;
    }

    public void draw(Graphics g){
        g.setFont(GameView.font);
        g.setColor(Color.WHITE);
        g.drawString("Player One:", p1X, textY);
        g.drawString("Player Two:", p2X, textY);
        drawCircles(g);
    }

    public void drawCircles(Graphics g){
        for(int i = 0; i < 5; i++){
            g.setColor(Color.white);
            g.drawOval(p1X + (i*circleSize) + i*(circleSpacing), circlesY, circleSize, circleSize);
            if(i < game.getP1ShotCounter()){
                if(player1Score[i] == false){
                    g.setColor(Color.RED);
                } else if (player1Score[i] == true){
                    g.setColor(Color.GREEN);
                }
                g.fillOval(p1X + (i*circleSize) + i*(circleSpacing), circlesY, circleSize, circleSize);
            }
            g.setColor(Color.WHITE);
            g.drawOval(p2X + (i*circleSize) + i*(circleSpacing), circlesY, circleSize, circleSize);
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

//    public int determineWinner(){
//
//    }
    public boolean[] getPlayer1Score() {
        return player1Score;
    }

    public boolean[] getPlayer2Score() {
        return player2Score;
    }
}
