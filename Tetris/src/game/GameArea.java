package game;

import tetrisBlocks.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * The type Game area.
 */
public class GameArea extends JPanel {

    private final int gridRows;
    private final int gridColumns;
    private final int gridSize;
    private final Color[][] background;
    private final Tetrominos[] tetrominoes;
    private Tetrominos tetromino;

    /**
     * Instantiates a new Game area.
     *
     * @param columns the columns
     */
    public GameArea(int columns) {
        setBounds(175, 100, 400, 600);
        setBackground(Color.WHITE);
        setOpaque(true);
        setLayout(null);
        gridColumns = columns;
        gridSize = this.getWidth() / gridColumns;
        gridRows = this.getHeight() / gridSize;
        background = new Color[gridRows][gridColumns];
        tetrominoes = new Tetrominos[]{new LShape(), new JShape(), new SShape(), new ZShape(), new SquareShape(), new IShape(), new TShape()};
    }

    /**
     * Move block down boolean.
     *
     * @return the boolean
     * returns true if tetromino can't move down anymore
     */
    public boolean moveBlockDown() {
        if (touchedBottom()) {
            return true;
        } else {
            tetromino.moveDown();
            repaint();
            return false;
        }
    }


    /**
     * Touched bottom boolean.
     * checks if tetromino can move down
     *
     * @return the boolean
     * returns true if it can't move down anymore
     */
    public boolean touchedBottom() {
        if (tetromino.getHeight() + tetromino.getY() == gridRows) {
            return true;
        } else {
            int[][] shape = tetromino.getShape();
            for (int column = 0; column < tetromino.getWidth(); column++) {
                for (int row = tetromino.getHeight() - 1; row >= 0; row--) {
                    if (shape[row][column] != 0) {
                        int x = column + tetromino.getX();
                        int y = row + tetromino.getY() + 1;
                        if (y < 0) {
                            break;
                        }
                        if (background[y][x] != null) {
                            return true;
                        }
                        break;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Touched right border boolean.
     *checks if tetromino can move to right
     *
     * @return the boolean
     * returns true if it can't move right anymore
     */
    public boolean touchedRightBorder() {
        if (tetromino.getRightBorder() == gridColumns) {
            return true;
        } else {
            int[][] shape = tetromino.getShape();
            for (int row = 0; row < tetromino.getHeight(); row++) {
                for (int column = tetromino.getWidth() - 1; column >= 0; column--) {
                    if (shape[row][column] != 0) {
                        int x = column + tetromino.getX() + 1;
                        int y = row + tetromino.getY();
                        if (y < 0) {
                            break;
                        }
                        if (background[y][x] != null) {
                            return true;
                        }
                        break;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Touched left border boolean.
     *checks if tetromino can move to left
     *
     * @return the boolean
     * returns true if it can't move left anymore
     */
    public boolean touchedLeftBorder() {
        if (tetromino.getLeftBorder() == 0) {
            return true;
        } else {
            int[][] shape = tetromino.getShape();
            for (int row = 0; row < tetromino.getHeight(); row++) {
                for (int column = 0; column < tetromino.getWidth(); column++) {
                    if (shape[row][column] != 0) {
                        int x = column + tetromino.getX() - 1;
                        int y = row + tetromino.getY();
                        if (y < 0) {
                            break;
                        }
                        if (background[y][x] != null) {
                            return true;
                        }
                        break;
                    }
                }
            }
        }
        return false;
    }


    /**
     * Draw block.
     *
     * @param g the g
     */
    public void drawBlock(Graphics g) {
        for (int row = 0; row < tetromino.getHeight(); row++) {
            for (int column = 0; column < tetromino.getWidth(); column++) {
                if (tetromino.getShape()[row][column] == 1) {
                    int x = (tetromino.getX() + column) * gridSize;
                    int y = (tetromino.getY() + row) * gridSize;
                    drawSquare(g, tetromino.getTetrominoColor(), x, y);
                }
            }
        }

    }

    /**
     * Spawn tetromino.
     */
    public void spawnTetromino() {
        Random r = new Random();
        tetromino = tetrominoes[r.nextInt(tetrominoes.length)];
        tetromino.spawn(gridColumns);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i =0;i<gridRows;i++){
            for (int j =0;j<gridColumns;j++){
                g.drawRect(j*gridSize,i*gridSize,gridSize,gridSize);
            }
        }
        drawBackground(g);
        drawBlock(g);
    }


    /**
     * Draw background.
     *creates a space for storing placed tetrominoes
     *
     * @param g the g
     */
    public void drawBackground(Graphics g) {
        Color color;
        for (int row = 0; row < gridRows; row++) {
            for (int column = 0; column < gridColumns; column++) {
                color = background[row][column];
                if (color != null) {
                    int x = column * gridSize;
                    int y = row * gridSize;
                    drawSquare(g, color, x, y);
                }
            }
        }
    }

    /**
     * Draw square.
     *
     * @param g     the g
     * @param color the color
     * @param x     the x coordinate
     * @param y     the y coordinate
     */
    public void drawSquare(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, gridSize, gridSize);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, gridSize, gridSize);
    }

    /**
     * Move to background.
     *
     */
    public void moveToBackground() {
        for (int row = 0; row < tetromino.getHeight(); row++) {
            for (int column = 0; column < tetromino.getWidth(); column++) {
                if (tetromino.getShape()[row][column] == 1) {
                    background[row + tetromino.getY()][column + tetromino.getX()] = tetromino.getTetrominoColor();

                }
            }
        }
    }

    /**
     * Is out of bounds boolean.
     *
     * @return the boolean
     * returns true if spawned tetromino is above the game area
     */
    public boolean isOutOfBounds() {
        if (tetromino.getY() < 0) {
            tetromino = null;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (touchedBottom()) {
            return;
        }
        if (tetromino == null) {
            return;
        }
        if (touchedRightBorder()) {
            return;
        }
        tetromino.moveRight();
        repaint();
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (touchedBottom()) {
            return;
        }
        if (tetromino == null) {
            return;
        }
        if (touchedLeftBorder()) {
            return;
        }
        tetromino.moveLeft();
        repaint();
    }

    /**
     * Drop.
     */
    public void drop() {
        if (tetromino == null) {
            return;
        }
        while (!touchedBottom()) {
            moveBlockDown();
        }
        repaint();
    }

    /**
     * Rotate.
     */
    public void rotate() {
        if (touchedBottom()) {
            return;
        }
        if (tetromino == null) {
            return;
        }
        if (tetromino.getLeftBorder() < 0) {
            tetromino.setX(0);
        }
        if (tetromino.getRightBorder() >= gridColumns) {
            tetromino.setX(gridColumns - tetromino.getWidth());
        }
        if (tetromino.getBottomBorder() >= gridRows) {
            tetromino.setY(gridRows - tetromino.getHeight());
        }
        tetromino.rotate();
        repaint();

    }

    /**
     * Clear lines int.
     * removes full lines and moves everything down
     *
     * @return the int
     * returns number of clreared lines
     */
    public int clearLines() {
        boolean fullLine;
        int clearedLines = 0;
        for (int i = gridRows - 1; i >= 0; i--) {
            fullLine = true;
            for (int j = 0; j < gridColumns; j++) {
                if (background[i][j] == null) {
                    fullLine = false;
                    break;
                }
            }
            if (fullLine) {
                clearedLines++;
                clearLine(i);
                shiftDown(i);
                clearLine(0);
                i++;
                repaint();
            }
        }
        return clearedLines;
    }

    /**
     * Clear line.
     *
     * @param row the row
     */
    public void clearLine(int row) {
        for (int i = 0; i < gridColumns; i++) {
            background[row][i] = null;
        }

    }

    /**
     * Shift down.
     *Method for moving everything in game area down
     * @param row the row
     */
    public void shiftDown(int row) {
        for (int i = row; i > 0; i--) {
            if (gridColumns >= 0) System.arraycopy(background[i - 1], 0, background[i], 0, gridColumns);
        }
    }


}

