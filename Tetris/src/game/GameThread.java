package game;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Game thread.
 */
public class GameThread extends Thread {

    private final GameArea gameArea;
    private final Frame frame;
    private int score;
    private int difficulty = 1;
    private final int scoreForLevel = 3;
    private int speed = 800;
    private final int speedPerDifficulty = 10;

    /**
     * Instantiates a new Game thread.
     *
     * @param gameArea the game area
     * @param frame the game form
     */
    public GameThread(GameArea gameArea, Frame frame) {
        this.gameArea = gameArea;
        this.frame = frame;
    }

    @Override
    public void run() {
        while (true) {
            gameArea.spawnTetromino();
            while (!gameArea.moveBlockDown()) {
                try {
                    if (speed > 0) {
                        Thread.sleep(speed);
                    }else {
                        Thread.sleep(100);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (gameArea.isOutOfBounds()) {
                System.out.println("GAME OVER");
                break;
            }
            gameArea.moveToBackground();
            score += gameArea.clearLines();
            frame.setScoreText(score);
            int diff = score / scoreForLevel + 1;
            if (diff > difficulty) {
                difficulty = diff;
                frame.setDifficultyText(difficulty);
            }
            speed -= speedPerDifficulty;

        }
    }


}
