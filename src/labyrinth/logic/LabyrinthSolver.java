package labyrinth.logic;

/**
 * Created by cornelius on 3/19/15.
 */
public interface LabyrinthSolver<T> {

    int[] nextCellToExplore(char move, int[] cell);

    char readNextMove();

    void solve();

    int[] down(int[] cell);

    int[] up(int[] cell);

    int[] right(int[] cell);

    int[] left(int[] cell);

    /**
     * method to register observer
     *
     * @param observer
     */
    void register(LabyrinthObserver observer);

    /**
     * method to unregister observer
     *
     * @param observer
     */
    void unregister(LabyrinthObserver observer);

    /**
     * method to unregister all observers
     */
    void unregister();

    /**
     * method to notify observers of change
     */
    void notifyObservers(int type);


    /**
     * updates the view class with the model
     */
    void updateView();

    /**
     * tests if the maze have been solved
     * @return
     */
    boolean isSolved();

    /**
     * method used to serialize
     */
    void save();

    /**
     * method used to deserialize
     */
    LabyrinthSolver load();

}
