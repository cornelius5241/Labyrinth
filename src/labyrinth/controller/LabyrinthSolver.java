package labyrinth.controller;

import labyrinth.observer.LabyrinthObserver;

/**
 * Created by cornelius on 3/19/15.
 */
public interface LabyrinthSolver<T> {

    public T nextCellToExplore(char move, T cell);

    public char readNextMove();

    public void interactiveSolve();
    public void solveDFS();
    public void solveBFS();
    public void solveRecursive();

    public T down(T cell);

    public T up(T cell);

    public T right(T cell);

    public T left(T cell);

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
    public void notifyObservers();

    /**
     * method to get updates from subject
     *
     * @param observer
     * @return
     */
    public Object getUpdate(LabyrinthObserver observer);

}
