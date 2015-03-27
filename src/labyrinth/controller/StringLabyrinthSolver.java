package labyrinth.controller;

import labyrinth.model.StringLabyrinth;
import labyrinth.observer.LabyrinthObserver;
import labyrinth.observer.StringLabyrinthObserver;
import labyrinth.view.StringLabyrinthView;

import java.util.Map;

/**
 * Created by Cornelius on 27.03.2015.
 */
public class StringLabyrinthSolver implements LabyrinthSolver<StringLabyrinth> {

    private final LabyrinthObserver MUTEX = new StringLabyrinthObserver();
    private StringLabyrinth model;
    private StringLabyrinthView view;
    private boolean solved;
    private Map<Integer, Integer> solvingPath;
    private int[] startCell;
    private boolean changed;

    /**
     * Constructor with 2 params
     *
     * @param model=the labyrinth model
     * @param view=the  labyrinth view
     */
    public StringLabyrinthSolver(StringLabyrinth model, StringLabyrinthView view) {
        this.model = model;
        this.view = view;
        this.solved = false;
        this.startCell = new int[2];
    }

    /**
     * Constructor with no parameters
     */
    public StringLabyrinthSolver(String filename) {
        this.model = new StringLabyrinth(filename);
        this.view = new StringLabyrinthView();
        this.solved = false;
        this.startCell = new int[2];
    }

    /**
     * Constructor with one parameter
     *
     * @param model=the labyrinth model
     */
    public StringLabyrinthSolver(StringLabyrinth model) {
        this.model = model;
        this.view = new StringLabyrinthView();
        this.solved = false;
        this.startCell = new int[2];
    }

    /**
     * updates the view class with the model
     */
    @Override
    public void updateView() {
        view.show(model);
    }

    /**
     * test if the labyrinth has been solved
     *
     * @return true if so
     */
    @Override
    public boolean isSolved() {
        return solved;
    }

    /**
     * r
     *
     * @param move
     * @param cell
     * @return
     */
    @Override
    public int[] nextCellToExplore(char move, int[] cell) {
        return null;
    }

    @Override
    public char readNextMove() {
        return 0;
    }

    /**
     * @param cell      the cell from where we start
     * @param direction that we came from
     */
    public void solveRecursively(int[] cell, int direction) {

        for (int i = 0; i < 4 && !isSolved(); i++)
            if (i != direction)
                switch (i) {
                    // 0 = up, 1 = right, 2 = down, 3 = left
                    case 0:
                        if (this.model.isFreeAt(cell[1] - 1, cell[0]))
                            solveRecursively(up(cell), 2);
                        break;
                    case 1:
                        if (this.model.isFreeAt(cell[1], cell[0] + 1))
                            solveRecursively(right(cell), 3);
                        break;
                    case 2:
                        if (this.model.isFreeAt(cell[1] + 1, cell[0]))
                            solveRecursively(down(cell), 0);
                        break;
                    case 3:
                        if (this.model.isFreeAt(cell[1], cell[0] - 1))
                            solveRecursively(left(cell), 1);
                        break;
                }
        // check for end condition
        if (cell[0] == this.model.getFinishCell()[0] && cell[1] == this.model.getFinishCell()[1])
            solved = true;
        // once we have found a solution, draw it as we unwind the recursion
        if (isSolved()) {
            this.model.getLabyrinth()[cell[1]][cell[0]] = this.model.getPATH();
            switch (direction) {
                case 0:
                    this.model.getLabyrinth()[cell[1] - 1][cell[1]] = this.model.getPATH();
                    break;
                case 1:
                    this.model.getLabyrinth()[cell[1]][cell[0] + 1] = this.model.getPATH();
                    break;
                case 2:
                    this.model.getLabyrinth()[cell[1] + 1][cell[0]] = this.model.getPATH();
                    break;
                case 3:
                    this.model.getLabyrinth()[cell[1]][cell[0] - 1] = this.model.getPATH();
                    break;
            }
        }
    }

    @Override
    public void solve() {
        solveRecursively(model.getStartCell(), -1);
        if (isSolved()) updateView();

    }

    @Override
    public int[] down(int[] cell) {
        int[] newCell = new int[2];
        newCell[0] = cell[0];
        newCell[1] = cell[1] + 1;
        return newCell;
    }

    @Override
    public int[] up(int[] cell) {
        int[] newCell = new int[2];
        newCell[0] = cell[0];
        newCell[1] = cell[1] - 1;
        return newCell;
    }

    @Override
    public int[] right(int[] cell) {
        int[] newCell = new int[2];
        newCell[0] = cell[0] + 1;
        newCell[1] = cell[1];
        return newCell;
    }

    @Override
    public int[] left(int[] cell) {
        int[] newCell = new int[2];
        newCell[0] = cell[0] - 1;
        newCell[1] = cell[1];
        return newCell;
    }

    /**
     * method to register observer
     *
     * @param observer
     */
    @Override
    public void register(LabyrinthObserver observer) {

    }

    /**
     * method to unregister observer
     *
     * @param observer
     */
    @Override
    public void unregister(LabyrinthObserver observer) {

    }

    /**
     * method to unregister all observers
     */
    @Override
    public void unregister() {

    }

    /**
     * method to notify observers of change
     */
    @Override
    public void notifyObservers() {

    }


}
