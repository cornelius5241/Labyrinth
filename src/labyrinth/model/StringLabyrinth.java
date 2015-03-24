package labyrinth.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cornelius on 3/22/15.
 */
public class StringLabyrinth {

    private String[][] labyrinth;
    private int rowCount;
    private int columnCount;
    private int[] startCell;
    private int[] finishCell;
    final String WALL = "#";
    final String ROOM = "0";
    final String START = "*";
    final String FINISH = "X";
    private List<Cell> frontier = new ArrayList<Cell>();

    /**
     * set 2 random values as the width and height of the maze between the min and the max params
     * @param min
     * @param max
     */
    public void randomSize(int min,int max){
        this.columnCount=randomValue(min,max);
        this.rowCount=randomValue(min,max);
        this.labyrinth = new String[rowCount][columnCount];
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

    /**
     * set all the cells from the maze to be walls
     * used in the Prime's algorithm for random maze generator
     */
    public void setAllWalls(){
     //   for(int i=0;i<rowCount;i++)
      //      for ( int j=0;j<columnCount;j++)


    }

    /**
     * adds the neighbour walls of the cell at the coordinates (x,y) in the list of walls
     * used in the Prime's algorithm for random maze generator
     * @param x
     * @param y
     */
    public void addWallOfCell(int x,int y){
        for(int i=-1;i<=1;i++)
            for(int j=-1;j<=1;j++){
                if(i==0&&j==0||i!=0&&j!=0)//ignore coordinates of the cell
                    continue;
                try{
                    if(labyrinth[x+i][y+j].equals(ROOM)) continue;
                }catch(Exception e){ // ignore ArrayIndexOutOfBounds
                    continue;
                }
                // add eligible walls to frontier
                //frontier.add(new Pair(x+i,y+j));
            }
    }
/*
    public int[] opposite(int x,int y){
        if(this.r.compareTo(parent.r)!=0)
            return new Point(this.r+this.r.compareTo(parent.r),this.c,this);
        if(this.c.compareTo(parent.c)!=0)
            return new Point(this.r,this.c+this.c.compareTo(parent.c),this);
        return null;
    }*/

    /**
     *Prime's algorithm for random maze generator
     */
    public void randomizedPrime(){
        setAllWalls();
        randomStartCell();
        addWallOfCell(startCell[0],startCell[1]);
        while(!frontier.isEmpty()){
            Cell wall=frontier.remove(randomValue(0,frontier.size()));
            //wall
        }
    }

    static class Cell{
        Integer row;
        Integer column;
        Cell parent;
        public Cell(int x, int y, Cell p){
            row=x;
            column=y;
            parent=p;
        }

        /**
         * compute opposite cell given that it is in the other direction from the parent
         * @return the opposite cell
         */
        public Cell opposite(){
            if(this.row.compareTo(parent.row)!=0)
                return new Cell(this.row+this.row.compareTo(parent.row),this.column,this);
            if(this.column.compareTo(parent.column)!=0)
                return new Cell(this.row,this.column+this.column.compareTo(parent.column),this);
            return null;
        }
    }

}
