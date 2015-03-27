package labyrinth.model;

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
    public int[] getStartCell();

    /**
     * @return the coordinates of the finishing cell of the maze
     */
    public int[] getFinishCell();


    /**
     * generates a  maze
     *
     */
    public void generateLabyrinth();

}
