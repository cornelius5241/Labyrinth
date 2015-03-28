package labyrinth.model;


import labyrinth.generator.FileLabyrinthGenerator;
import labyrinth.logic.LabyrinthModel;

import java.io.Serializable;

/**
 * Created by cornelius on 3/22/15.
 */
public class StringLabyrinth implements LabyrinthModel<String>, Serializable {

    final char WALL = '#';
    final char ROOM = 'o';
    final char START = 'X';
    final char FINISH = 'Y';
    final char PATH = '*';
    private char[][] labyrinth;
    private int height;
    private int width;
    private int[] startCell;
    private int[] finishCell;
    private String filename;

    public StringLabyrinth(String filename) {
        startCell = new int[2];
        finishCell = new int[2];
        this.filename = filename;
        generateLabyrinth();
    }


    public char[][] getLabyrinth() {
        return labyrinth;
    }


    public char getROOM() {
        return ROOM;
    }

    public char getPATH() {
        return PATH;
    }


    /**
     * uses the min and max params
     *
     * @param min
     * @param max
     * @return a random int value between the params
     */
    public int randomValue(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    /**
     * randomize the selection of the starting and finishing cell of the maze
     * it will be selected to be on the corner of the maze and opposite one of each other
     * edit!
     */
    public void randomStartFinishCell() {
        int position = randomValue(0, 4);
        switch (position) {
            case 0: {//S-NV F-SE
                startCell[0] = 0;
                startCell[1] = 0;
                finishCell[0] = height - 1;
                finishCell[1] = width - 1;
            }
            break;
            case 1: {//S-NE F-SV
                startCell[0] = 0;
                startCell[1] = width - 1;
                finishCell[0] = height - 1;
                finishCell[1] = 0;
            }
            break;
            case 2: {//S-SE F-NV
                startCell[0] = height - 1;
                startCell[1] = width - 1;
                finishCell[0] = 0;
                finishCell[1] = 0;
            }
            break;
            case 3: {//S-SV F-NE
                startCell[0] = height - 1;
                startCell[1] = 0;
                finishCell[0] = 0;
                finishCell[1] = width - 1;
            }
            break;
            default: {
            }
        }
        labyrinth[startCell[0]][startCell[1]] = START;
        labyrinth[finishCell[0]][finishCell[1]] = FINISH;
    }

    /**
     * @return the number of rows in the maze
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * @return the number of columns in the maze
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * tests if the cell at coordinates (x,y) is free (a room of the maze)
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    @Override
    public boolean isFreeAt(int x, int y) {
        try {
            return labyrinth[x][y] == ROOM;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect coordinates");
            return false;
        }
    }

    /**
     * tests if the cell at coordinates (x,y) is a the start position
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    @Override
    public boolean isStartAt(int x, int y) {
        try {
            return x == startCell[0] && y == startCell[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect coordinates");
            return false;
        }
    }

    /**
     * tests if the cell at coordinates (x,y) is the finish position
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    @Override
    public boolean isFinishAt(int x, int y) {
        try {
            return x == finishCell[0] && y == finishCell[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect coordinates");
            return false;
        }
    }

    /**
     * tests if the cell at coordinates (x,y) is a path for solver of the labyrinth
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    @Override
    public boolean isPathAt(int x, int y) {
        try {
            return Character.compare(labyrinth[x][y], PATH) == 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect coordinates");
            return false;
        }
    }

    /**
     * tests if the cell at coordinates (x,y) is a wall
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    @Override
    public boolean isWallAt(int x, int y) {
        try {
            return Character.compare(labyrinth[x][y], WALL) == 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect coordinates");
            return false;
        }
    }

    /**
     * @return the coordinates of the starting cell of the maze
     */
    @Override
    public int[] getStartCell() {
        return startCell;
    }

    /**
     * @return the coordinates of the finishing cell of the maze
     */
    @Override
    public int[] getFinishCell() {
        return finishCell;
    }

    /**
     * generates a  maze
     */
    @Override
    public void generateLabyrinth() {
        FileLabyrinthGenerator FLG = new FileLabyrinthGenerator(filename);
        this.labyrinth = FLG.getLabyrinth();
        this.height = labyrinth.length;
        this.width = labyrinth[0].length;
        randomStartFinishCell();
    }

}
