package labyrinth.model;

import java.io.File;

/**
 * Interface labyrinth.model class for carrying data of a maze(labyrinth)
 * Created by cornelius on 3/19/15.
 */
public interface LabyrinthModel<T> {

    /**
     * @return the number of rows in the maze
     */
    public int getHeight();

    /**
     * @return the number of columns in the maze
     */
    public int getWidth();

    /**
     * tests if the cell at coordinates (x,y) is free (a room of the maze)
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    public boolean isFreeAt(int x, int y);

    /**
     * tests if the cell at coordinates (x,y) is a the start position
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    public boolean isStartAt(int x, int y);

    /**
     * tests if the cell at coordinates (x,y) is the finish position
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    public boolean isFinishAt(int x, int y);

    /**
     * tests if the cell at coordinates (x,y) is a path for solver of the labyrinth
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    public boolean isPathAt(int x, int y);

    /**
     * tests if the cell at coordinates (x,y) is a wall
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    public boolean isWallAt(int x, int y);

    /**
     * @return the coordinates of the starting cell of the maze
     */
    public T[] getStartCell();

    /**
     * @return the coordinates of the finishing cell of the maze
     */
    public T[] getFinishCell();

    /**
     * uses the file given as a parameter to generate a maze from the input of the file
     *
     * @param file
     */
    public void generateLabyrinth(File file);

    /**
     * generates a random maze with it's size between the min and max values
     *
     * @param min
     * @param max
     */
    public void generateLabyrinth(int min, int max);

}
