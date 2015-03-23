package labyrinth;

import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 *
 * Created by cornelius on 3/22/15.
 */
public class IntegerLabyrinth implements LabyrinthModel {

    private Integer[][] labyrinth;
    private int rowCount;
    private int columnCount;
    private Integer[] startCell;
    private Integer[] finnishCell;

    List<Pair<Integer,Integer>> wallList= new ArrayList<Pair<Integer,Integer>>();//for prime's algorithm

    final Integer WALL = 1;
    final Integer ROOM = 0;
    final Integer START = -1;
    final Integer FINNISH = 2;

    public IntegerLabyrinth(){
        System.out.println("Insert the minimum and the maximum values for the labyrinth dimensions:");
        Scanner keyboard = new Scanner(System.in);
        generateLabyrinth(keyboard.nextInt(), keyboard.nextInt());
    }

    public IntegerLabyrinth(int rowCount, int columnCount) {
        this.labyrinth = new Integer[rowCount][columnCount];
        this.startCell=new Integer[2];
        this.finnishCell=new Integer[2];
        this.rowCount=rowCount;
        this.columnCount=columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public boolean isFreeAt(int x, int y) {//first true => 2nd || first false =>third
        if (labyrinth[x][y] == null ? ROOM == null : labyrinth[x][y].equals(ROOM))return true;
        else return false;
    }

    public boolean isWallAt(int x, int y) {
        if (labyrinth[x][y] == null ? WALL == null : labyrinth[x][y].equals(WALL))return true;
        else return false;
    }

    public Integer[] getStartCell() {
        return startCell;
    }

    public Integer[] getFinnishCell() {
        return  finnishCell;
    }

    public void generateLabyrinth(File filename) {

    }

    public void randomSize(int min,int max){
        this.columnCount=randomValue(min,max);
        this.rowCount=randomValue(min,max);
    }

    public int randomValue(int min, int max) {
        return (int)(Math.random()*(max-min)+min) ;
    }

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

    public void setAllWalls(){
        for(int i=0;i<rowCount;i++)
            for ( int j=0;j<columnCount;j++)
                    labyrinth[i][j]=WALL;

    }

    public void addWallOfCell(int x,int y){
      //  wallList.add((x,y));
    }

    public void randomizedPrime(){
        setAllWalls();
        randomStartCell();

    }

    public void initialize(){
        this.labyrinth = new Integer[rowCount][columnCount];
        this.startCell=new Integer[2];
        this.finnishCell=new Integer[2];
    }

    public void show(){
        for (int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++)
                System.out.print(labyrinth[i][j]);
            System.out.println();
        }
    }

    public void generateLabyrinth(int min,int max) {
        randomSize(min,max);
        initialize();
        randomizedPrime();
    }
}
