package labyrinth.controller;

/**
 * Created by cornelius on 3/19/15.
 */
public interface LabyrinthSolver<T> {

    public T[][] nextCellToExplore();
    public void readNextMove();

    public void interactiveSolve();
    public void solveDFS();
    public void solveBFS();
    public void solveRecursive();

    public void down();
    public void up();
    public void right();
    public void left();

}
