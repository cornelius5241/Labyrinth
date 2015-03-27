package labyrinth.controller;

import labyrinth.observer.LabyrinthObserver;

/**
 * Created by cornelius on 3/19/15.
 */
public interface LabyrinthSolver<T> {

    public int[] nextCellToExplore(char move, int[] cell);

    public char readNextMove();

    public void solve();

    public int[] down(int[] cell);

    public int[] up(int[] cell);

    public int[] right(int[] cell);

    public int[] left(int[] cell);

    /**
     * method to register observer
     *
     * @param observer
     */
    public void register(LabyrinthObserver observer);

    /**
     * method to unregister observer
     *
     * @param observer
     */
    public void unregister(LabyrinthObserver observer);

    /**
     * method to unregister all observers
     */
    public void unregister();

    /**
     * method to notify observers of change
     */
    public void notifyObservers(int type);


    /**
     * updates the view class with the model
     */
    public void updateView();

    /**
     * tests if the maze have been solved
     * @return
     */
    public boolean isSolved();

    /**
     * method used to serialize
     */
    public void save();

    /**
     * method used to deserialize
     */
    public LabyrinthSolver load();

}
