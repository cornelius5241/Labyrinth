package labyrinth.model;

import java.io.*;
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
    private Integer[][] labyrinth;
    private int rowCount;
    private int columnCount;
    private Integer[] startCell;
    private Integer[] finishCell;

    /**
     * Constructor that uses 2 values inserted from the keyboard to automatically generate a new maze
     */
    public IntegerLabyrinth() {
        this.startCell = new Integer[2];
        this.finishCell = new Integer[2];
        System.out.println("Insert the minimum and the maximum values for the labyrinth dimensions:");
        Scanner keyboard = new Scanner(System.in);
        generateLabyrinth(keyboard.nextInt(), keyboard.nextInt());
    }

    /**
     * Constructor that reads a file and use it's input to create a new maze
     */
    public IntegerLabyrinth(String filename) {
        this.startCell = new Integer[2];
        this.finishCell = new Integer[2];
        generateLabyrinth(new File(filename));
    }

    /**
     * Basic constructor that uses the params to set the dimensions
     * it has random elements
     *
     * @param rowCount
     * @param columnCount
     */
    public IntegerLabyrinth(int rowCount, int columnCount) {
        this.labyrinth = new Integer[rowCount][columnCount];
        this.startCell = new Integer[2];
        this.finishCell = new Integer[2];
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        randomStartFinishCell();
        generator();
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
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @return the number of columns in the maze
     */
    @Override
    public int getColumnCount() {
        return columnCount;
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
     * @param x
     * @param y
     * @return true if the cell at coordinates (x,y) is a wall
     */
    @Override
    public boolean isWallAt(int x, int y) {
        if (labyrinth[x][y] == null ? WALL == null : labyrinth[x][y].equals(WALL)) return true;
        else return false;
    }

    /**
     * @return a 2 elements vector with the coordinates of the starting maze cell
     */
    @Override
    public Integer[] getStartCell() {
        return startCell;
    }

    /**
     * @return a 2 elements vector with the coordinates of the finnish maze cell
     */
    @Override
    public Integer[] getFinishCell() {
        return finishCell;
    }

    public File readFile() {
        Scanner keyboard = new Scanner(System.in);
        return new File(keyboard.nextLine());
    }

    /**
     * reads a file and generates a maze from it
     *
     * @param file
     */
    @Override
    public void generateLabyrinth(File file) {
        String maze[][] = new String[(int) Math.sqrt(file.length())][];
        int rows = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    maze[rows] = new String[line.split(" ").length];
                    maze[rows++] = line.split(" ");
                }
                br.close();
                this.rowCount = rows;
                this.columnCount = maze[0].length;
                this.labyrinth = new Integer[rowCount][columnCount];
                for (int i = 0; i < rows; i++)
                    for (int j = 0; j < maze[i].length; j++)
                        labyrinth[i][j] = Integer.parseInt(maze[i][j]);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found.Please insert a new filename path");
            generateLabyrinth(readFile());
        }
    }

    /**
     * set 2 random values as the width and height of the maze between the min and the max params
     *
     * @param min
     * @param max
     */
    public void randomSize(int min, int max) {
        this.columnCount = randomValue(min, max);
        this.rowCount = randomValue(min, max);
        this.labyrinth = new Integer[rowCount][columnCount];
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
     * it will be selected to be on the frontier of the maze and opposite one of each other
     */
    public void randomStartFinishCell() {
        int position = randomValue(0, 4);
        switch (position) {
            case 0: {//S-up F-down
                startCell[0] = 0;
                startCell[1] = randomValue(0, columnCount - 1);
                finishCell[0] = rowCount - 1;
                finishCell[1] = randomValue(0, columnCount - 1);
            }
            break;
            case 1: {//S-right F-left
                startCell[0] = randomValue(0, rowCount - 1);
                startCell[1] = columnCount - 1;
                finishCell[0] = randomValue(0, rowCount - 1);
                finishCell[1] = 0;
            }
            break;
            case 2: {//S-down F-up
                startCell[0] = rowCount - 1;
                startCell[1] = randomValue(0, columnCount - 1);
                finishCell[0] = 0;
                finishCell[1] = randomValue(0, columnCount - 1);
            }
            break;
            case 3: {//S-left F-right
                startCell[0] = randomValue(0, rowCount - 1);
                startCell[1] = 0;
                finishCell[0] = randomValue(0, rowCount - 1);
                finishCell[1] = columnCount - 1;
            }
            break;
            default: {
            }
        }
        labyrinth[startCell[0]][startCell[1]] = START;
        labyrinth[finishCell[0]][finishCell[1]] = FINISH;
    }

    /**
     * random generates the walls and the rooms of the labyrinth
     */
    public void generator() {
        for (int i = 0; i < this.rowCount; i++)
            for (int j = 0; j < this.columnCount; j++) {
                if (labyrinth[i][j] != START && labyrinth[i][j] != FINISH)
                    labyrinth[i][j] = randomValue(0, 2);
            }
    }

    /**
     * the random maze generator method
     *
     * @param min
     * @param max
     */
    @Override
    public void generateLabyrinth(int min, int max) {
        randomSize(min, max);
        randomStartFinishCell();
        generator();
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

}
