package labyrinth.logic;

/**
 * Interface labyrinth.model class for carrying data of a maze(labyrinth)
 * Created by cornelius on 3/19/15.
 */
public interface LabyrinthModel<T> {

    /**
     * @return the number of rows in the maze
     */
    int getHeight();

    /**
     * @return the number of columns in the maze
     */
    int getWidth();

    /**
     * tests if the cell at coordinates (x,y) is free (a room of the maze)
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    boolean isFreeAt(int x, int y);

    /**
     * tests if the cell at coordinates (x,y) is a the start position
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    boolean isStartAt(int x, int y);

    /**
     * tests if the cell at coordinates (x,y) is the finish position
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    boolean isFinishAt(int x, int y);

    /**
     * tests if the cell at coordinates (x,y) is a path for solver of the labyrinth
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    boolean isPathAt(int x, int y);

    /**
     * tests if the cell at coordinates (x,y) is a wall
     *
     * @param x
     * @param y
     * @return true if so , false if not
     */
    boolean isWallAt(int x, int y);

    /**
     * @return the coordinates of the starting cell of the maze
     */
    int[] getStartCell();

    /**
     * @return the coordinates of the finishing cell of the maze
     */
    int[] getFinishCell();


    /**
     * generates a  maze
     *
     */
    void generateLabyrinth();

}
