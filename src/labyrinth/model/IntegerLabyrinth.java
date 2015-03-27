package labyrinth.model;

import labyrinth.generator.RosettaCodeLabyrinthGenerator;

import java.util.Scanner;

/**
 * A labyrinth.model class that uses Integers to carrying data of an implementation of a maze
 * Created by cornelius & anca on 3/22/15.
 */
public class IntegerLabyrinth implements LabyrinthModel<Integer> {

    final Integer WALL = 1;
    final Integer ROOM = 0;
    final Integer START = -1;
    final Integer FINISH = 2;
    final Integer PATH = 3;
    private Integer[][] labyrinth;
    private int height;
    private int width;
    private int[] startCell;
    private int[] finishCell;

    /**
     * Constructor that uses 2 values inserted from the keyboard to automatically generate a new maze
     */
    public IntegerLabyrinth() {
        this.startCell = new int[2];
        this.finishCell = new int[2];
        generateLabyrinth();
    }

    /**
     * Constructor that receive a matrix of integers as a param
     *
     * @param labyrinth
     */
    public IntegerLabyrinth(Integer[][] labyrinth) {
        this.labyrinth = labyrinth;
        this.height = labyrinth.length;
        this.width = labyrinth[0].length;
    }

    /**
     * Basic constructor that uses the params to set the dimensions
     * it has random elements
     *
     * @param height
     * @param width
     */
    public IntegerLabyrinth(int height, int width) {
        this.startCell = new int[2];
        this.finishCell = new int[2];
        this.height = height;
        this.width = width;
        generateLabyrinth();
    }

    /**
     * @return the matrix of the maze
     */
    public Integer[][] getLabyrinth() {
        return labyrinth;
    }

    /**
     * @return the number of rows in the maze
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * @return the number of columns in the maze
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * @param x
     * @param y
     * @return true if the cell at coordinates (x,y) is free (it is a room)
     */
    @Override
    public boolean isFreeAt(int x, int y) {//first true => 2nd || first false =>third
        if (labyrinth[x][y] == null ? ROOM == null : labyrinth[x][y].equals(ROOM)) return true;
        else return false;
    }

    /**
     * Unset the path and insert a room at the specified cell
     *
     * @param cell
     */
    public void unsetPathAt(int[] cell) {
        labyrinth[cell[0]][cell[1]] = ROOM;
    }

    /**
     * @param x
     * @param y
     * @return true if the cell at coordinates (x,y) is the start position
     */
    @Override
    public boolean isStartAt(int x, int y) {
        if (labyrinth[x][y] == null ? START == null : labyrinth[x][y].equals(START)) return true;
        else return false;
    }

    /**
     * @param x
     * @param y
     * @return true if the cell at coordinates (x,y) is a wall
     */
    @Override
    public boolean isWallAt(int x, int y) {
        try {
            if (labyrinth[x][y] == null ? WALL == null : labyrinth[x][y].equals(WALL)) return true;
            else return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Can't go there, you might fall");
            return true;
        }
    }

    /**
     * @param x
     * @param y
     * @return true if the cell at coordinates (x,y) is a path
     */
    @Override
    public boolean isPathAt(int x, int y) {
        try {
            if (labyrinth[x][y] == null ? PATH == null : labyrinth[x][y].equals(PATH)) return true;
            else return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Can't go there, you might fall");
            return false;
        }
    }

    /**
     * sets a path to the specified cell
     *
     * @param cell
     */
    public void setPathAt(int[] cell) {
        try {
            labyrinth[cell[0]][cell[1]] = PATH;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Imposible to reach path");
        }
    }

    /**
     * @param x
     * @param y
     * @return true if the cell at coordinates (x,y) is the finish position
     */
    @Override
    public boolean isFinishAt(int x, int y) {
        if (labyrinth[x][y] == null ? FINISH == null : labyrinth[x][y].equals(FINISH)) return true;
        else return false;
    }

    /**
     * @return a 2 elements vector with the coordinates of the starting maze cell
     */
    @Override
    public int[] getStartCell() {
        return startCell;
    }

    /**
     * @return a 2 elements vector with the coordinates of the finnish maze cell
     */
    @Override
    public int[] getFinishCell() {
        return finishCell;
    }


    /**
     * set 2 random values as the width and height of the maze between the min and the max params
     *
     * @param min
     * @param max
     */
    public void randomSize(int min, int max) {
        this.width = randomValue(min, max);
        this.height = randomValue(min, max);
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
     * the random maze generator method using Rosseta code
     */
    @Override
    public void generateLabyrinth() {
        System.out.println("Insert the minimum and the maximum values for the labyrinth dimensions:");
        Scanner keyboard = new Scanner(System.in);
        randomSize(keyboard.nextInt(), keyboard.nextInt());
        RosettaCodeLabyrinthGenerator RCLG = new RosettaCodeLabyrinthGenerator(height, width);
        this.labyrinth = RCLG.parseToInteger();
        this.height = labyrinth.length;
        this.width = labyrinth[0].length;
        randomStartFinishCell();
    }

    public Integer getFINISH() {
        return FINISH;
    }

    public Integer getWALL() {
        return WALL;
    }

    public Integer getROOM() {
        return ROOM;
    }

    public Integer getSTART() {
        return START;
    }

    public Integer getPATH() {
        return PATH;
    }

}
