package labyrinth.model;

import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A labyrinth.model class that uses Integers to carrying data of an implementation of a maze
 * Created by cornelius & anca on 3/22/15.
 */
public class IntegerLabyrinth implements LabyrinthModel {

    private Integer[][] labyrinth;
    private int rowCount;
    private int columnCount;
    private Integer[] startCell;
    private Integer[] finishCell;
    final Integer WALL = 1;
    final Integer ROOM = 0;
    final Integer START = -1;
    final Integer FINISH = 2;

    /**
     * Constructor that uses 2 values inserted from the keyboard to automatically generate a new maze
     */
    public IntegerLabyrinth(){
        this.startCell=new Integer[2];
        this.finishCell=new Integer[2];
        System.out.println("Insert the minimum and the maximum values for the labyrinth dimensions:");
        Scanner keyboard = new Scanner(System.in);
        generateLabyrinth(keyboard.nextInt(), keyboard.nextInt());
    }

    /**
     * Basic constructor that just initialize the attributes of the maze
      * @param rowCount
     * @param columnCount
     */
    public IntegerLabyrinth(int rowCount, int columnCount) {
        this.labyrinth = new Integer[rowCount][columnCount];
        this.startCell=new Integer[2];
        this.finishCell=new Integer[2];
        this.rowCount=rowCount;
        this.columnCount=columnCount;
    }

    /**
     * @return the number of rows in the maze
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @return the number of columns in the maze
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     *
     * @param x
     * @param y
     * @return true if the cell at coordinates (x,y) is free (it is a room)
     */
    public boolean isFreeAt(int x, int y) {//first true => 2nd || first false =>third
        if (labyrinth[x][y] == null ? ROOM == null : labyrinth[x][y].equals(ROOM))return true;
        else return false;
    }

    /**
     * @param x
     * @param y
     * @return true if the cell at coordinates (x,y) is a wall
     */
    public boolean isWallAt(int x, int y) {
        if (labyrinth[x][y] == null ? WALL == null : labyrinth[x][y].equals(WALL))return true;
        else return false;
    }

    /**
     * @return a 2 elements vector with the coordinates of the starting maze cell
     */
    public Integer[] getStartCell() {
        return startCell;
    }

    /**
     * @return a 2 elements vector with the coordinates of the finnish maze cell
     */
    public Integer[] getFinishCell() {
        return  finishCell;
    }

    /**
     * reads a file and generates a maze from it
     * @param filename
     */
    public void generateLabyrinth(File filename) {

    }

    /**
     * set 2 random values as the width and height of the maze between the min and the max params
     * @param min
     * @param max
     */
    public void randomSize(int min,int max){
        this.columnCount=randomValue(min,max);
        this.rowCount=randomValue(min,max);
        this.labyrinth = new Integer[rowCount][columnCount];
    }

    /**
     * uses the min and max params
     * @param min
     * @param max
     * @return a random int value between the params
     */
    public int randomValue(int min, int max) {
        return (int)(Math.random()*(max-min)+min) ;
    }

    /**
     * randomize the selection of the starting cell of the maze
     * it will be selected to be on the frontier of the maze
     */
    public void randomStartCell(){
        int position=randomValue(0,4);
        switch (position){
            case 0: {//up
                startCell[0] = 0;
                startCell[1] = randomValue(0, columnCount-1);
            } break;
            case 1:{//right
                startCell[0] = randomValue(0,rowCount-1);
                startCell[1] = columnCount-1;
            } break;
            case 2:{//down
                startCell[0] = rowCount-1;
                startCell[1] = randomValue(0, columnCount-1);
            } break;
            case 3:{//left
                startCell[0] = randomValue(0,rowCount-1);
                startCell[1] = 0;
            } break;
            default:{}
        }
        labyrinth[startCell[0]][startCell[1]]=START;
    }

//temporary
    public void show(){
        for (int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++)
                System.out.print(labyrinth[i][j]);
            System.out.println();
        }
    }

    /**
     * the random maze generator method
     * @param min
     * @param max
     */
    public void generateLabyrinth(int min,int max) {
        randomSize(min,max);
        //randomizedPrime();
    }
}
