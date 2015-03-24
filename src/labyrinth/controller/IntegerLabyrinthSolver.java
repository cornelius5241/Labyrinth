package labyrinth.controller;

import labyrinth.model.IntegerLabyrinth;
import labyrinth.view.IntegerLabyrinthView;

/**
 * Created by Cornelius on 24.03.2015.
 */
public class IntegerLabyrinthSolver implements LabyrinthSolver {

    private IntegerLabyrinth model;
    private IntegerLabyrinthView view;

    public IntegerLabyrinthSolver(IntegerLabyrinth model, IntegerLabyrinthView view) {
        this.model = model;
        this.view = view;
    }

    public IntegerLabyrinthSolver() {
        this.model = new IntegerLabyrinth();
        this.view = new IntegerLabyrinthView();
    }

    public IntegerLabyrinthSolver(IntegerLabyrinth model) {
        this.model = model;
        this.view = new IntegerLabyrinthView();
    }

    public void updateView() {
        view.show(model);
    }

    @Override
    public Object[][] nextCellToExplore() {
        return new Object[0][];
    }

    @Override
    public void readNextMove() {

    }

    @Override
    public void interactiveSolve() {

    }

    @Override
    public void solveDFS() {

    }

    @Override
    public void solveBFS() {

    }

    @Override
    public void solveRecursive() {

    }

    @Override
    public void down() {

    }

    @Override
    public void up() {

    }

    @Override
    public void right() {

    }

    @Override
    public void left() {

    }
}
