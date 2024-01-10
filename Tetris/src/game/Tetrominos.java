package game;

import java.awt.*;
import java.util.Random;

/**
 * The type Tetrominos.
 */
public class Tetrominos {

    private int[][] shape;
    private Color tetrominoColor;
    private int x;
    private int y;
    private int[][][] shapes;
    private int currentRotation;
    private final Color[] tetrominoColors = {Color.CYAN, Color.RED, Color.GREEN, Color.YELLOW};

    /**
     * Instantiates a new Tetrominos.
     *
     * @param shape the shape
     */
    public Tetrominos(int[][] shape) {
        this.shape = shape;
        Shapes();
    }

    /**
     * Shapes.
     * saves possible rotations of tetrominoes into a 3-dimensional array
     * first [] = rotation of the tetromino
     *
     */
    public void Shapes() {
        shapes = new int[4][][];
        for (int i = 0; i < 4; i++) {
            int row = shape[0].length;
            int column = shape.length;
            shapes[i] = new int[row][column];
            for (int y = 0; y < row; y++) {
                for (int x = 0; x < column; x++) {
                    shapes[i][y][x] = shape[column - x - 1][y];
                }
            }
            shape = shapes[i];
        }
    }

    /**
     * Spawn.
     * Spawns a tetromino in the middle of the game area
     *
     * @param gridColumns the grid columns
     */
    public void spawn(int gridColumns) {
        Random r = new Random();
        currentRotation = r.nextInt(4);
        shape = shapes[currentRotation];
        y = -getHeight();
        x = (gridColumns - getWidth()) / 2;
        tetrominoColor = tetrominoColors[r.nextInt(tetrominoColors.length)];
    }

    /**
     * Rotate tetromino
     *
     */
    public void rotate() {
        currentRotation++;
        if (currentRotation > 3) {
            currentRotation = 0;
        }
        shape = shapes[currentRotation];
    }



    /**
     * Get shape int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getShape() {
        return shape;
    }

    /**
     * Gets tetromino color.
     *
     * @return the tetromino color
     */
    public Color getTetrominoColor() {
        return tetrominoColor;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return shape.length;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return shape[0].length;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Move down.
     */
    public void moveDown() {
        y++;
    }


    /**
     * Move left.
     */
    public void moveLeft() {
        x--;
    }

    /**
     * Move right.
     */
    public void moveRight() {
        x++;
    }


    /**
     * Gets left border.
     *
     * @return the left border
     */
    public int getLeftBorder() {
        return x;
    }

    /**
     * Gets right border.
     *
     * @return the right border
     */
    public int getRightBorder() {
        return x + getWidth();
    }

    /**
     * Gets bottom border.
     *
     * @return the bottom border
     */
    public int getBottomBorder() {
        return y + getHeight();
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }
}
