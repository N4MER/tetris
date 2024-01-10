package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The type Game form.
 */
public class Frame extends JFrame {

    /**
     * The Game area.
     */
    public GameArea gameArea;
    private JLabel score;
    private JLabel difficulty;

    /**
     * Instantiates a new Game form.
     */
    ImageIcon imageIcon = new ImageIcon("cover-8850287296478592.png");
    public Frame() {
        score = new JLabel("score: 0");
        difficulty = new JLabel("difficulty: 1");
        score.setForeground(Color.WHITE);
        difficulty.setForeground(Color.WHITE);
        gameArea = new GameArea(10);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 900);
        score.setBounds(585,100,100,150);
        difficulty.setBounds(585,400,200,300);
        setTitle("Tetris");
        setLayout(null);
        setResizable(false);
        setIconImage(imageIcon.getImage());
        getContentPane().setBackground(Color.decode("#1d1919"));
        add(gameArea);
        add(score);
        add(difficulty);
        setLocationRelativeTo(null);
        setVisible(true );
        Controls();
        startGame();
    }

    /**
     * Start game.
     */
    public void startGame() {
        new GameThread(gameArea,this).start();
    }

    /**
     * Controls.
     *
     */
    public void Controls() {
        InputMap inputMap = this.getRootPane().getInputMap();
        ActionMap actionMap = this.getRootPane().getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down");
        inputMap.put(KeyStroke.getKeyStroke("UP"), "up");


        actionMap.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveRight();
            }
        });
        actionMap.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveLeft();
            }
        });
        actionMap.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.drop();
            }
        });
        actionMap.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.rotate();
            }
        });

    }

    /**
     * Set score text.
     *
     * @param s the score
     */
    public void setScoreText(int s){
        score.setText("score: "+s);
    }

    /**
     * Set difficulty text.
     *
     * @param d the difficulty
     */
    public void setDifficultyText(int d){
        difficulty.setText("difficulty: "+d);
    }

}
