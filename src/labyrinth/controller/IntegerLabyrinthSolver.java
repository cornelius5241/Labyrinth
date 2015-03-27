package labyrinth.controller;

import labyrinth.model.IntegerLabyrinth;
import labyrinth.observer.IntegerLabyrinthObserver;
import labyrinth.observer.LabyrinthObserver;
import labyrinth.view.IntegerLabyrinthView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Cornelius on 24.03.2015.
 */
public class IntegerLabyrinthSolver implements LabyrinthSolver<Integer[]> {

    private final LabyrinthObserver MUTEX = new IntegerLabyrinthObserver();
    private IntegerLabyrinth model;
    private IntegerLabyrinthView view;
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
    public IntegerLabyrinthSolver(IntegerLabyrinth model, IntegerLabyrinthView view) {
        this.model = model;
        this.view = view;
        this.solved = false;
        this.startCell = new int[2];
    }

    /**
     * Constructor with no parameters
     */
    public IntegerLabyrinthSolver() {
        this.model = new IntegerLabyrinth();
        this.view = new IntegerLabyrinthView();
        this.solved = false;
        this.startCell = new int[2];
    }

    /**
     * Constructor with one parameter
     *
     * @param model=the labyrinth model
     */
    public IntegerLabyrinthSolver(IntegerLabyrinth model) {
        this.model = model;
        this.view = new IntegerLabyrinthView();
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
     * @return the next cell to be explored for the solving of the maze
     */
    @Override
    public int[] nextCellToExplore(char move, int[] previousCell) {
        switch (move) {
            case 'u': {
                return up(previousCell);
            }
            case 'r': {
                return right(previousCell);
            }
            case 'd': {
                return down(previousCell);
            }
            case 'l': {
                return left(previousCell);
            }
            default: {
                System.out.println("Wrong move;try again");
                return null;
            }
        }
    }

    /**
     * reads from the keyboard the next move from the user
     */
    @Override
    public char readNextMove() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Insert new move:");
        return keyboard.next().charAt(0);
    }

    public void addPath(int[] cell) {
        solvingPath.put(cell[0], cell[1]);
        this.model.setPathAt(cell);
    }

    public void removePath(int[] cell) {
        if (cell[0] == this.model.getStartCell()[0] && cell[1] == this.model.getStartCell()[1]) return;
        solvingPath.remove(cell[0], cell[1]);
        this.model.unsetPathAt(cell);
    }

    /**
     * interactive method to solve the maze
     */
    @Override
    public void solve() {
        this.startCell = this.model.getStartCell();
        this.solvingPath = new HashMap<Integer, Integer>();
        solvingPath.put(startCell[0], startCell[1]);
        int[] previousCell = new int[2];
        previousCell[0] = startCell[0];
        previousCell[1] = startCell[1];
        while (!isSolved()) {
            int[] currentCell = nextCellToExplore(readNextMove(), previousCell);
            if (currentCell != null) {
                if (currentCell[0] == this.model.getFinishCell()[0] && currentCell[1] == this.model.getFinishCell()[1]) {
                    solved = true;
                    continue;
                }
                if (solvingPath.containsKey(currentCell[0]) && (solvingPath.get(currentCell[0]) == currentCell[1])) {
                    removePath(currentCell);
                } else {
                    addPath(currentCell);
                }
                //notify observer ->print
                previousCell[0] = currentCell[0];
                previousCell[1] = currentCell[1];
                this.updateView();
            } else System.out.println("Wrong command ,try again");
        }
        System.out.println("Congratulations:You have finished the labyrinth. Zeus would be proud!");
    }

    /**
     * method used to solve interactive the labyrinth
     *
     * @param previousCell = cell given as parameter
     * @return the next cell that is just under the cell given as parameter
     */
    @Override
    public int[] down(int[] previousCell) {
        int[] newCell = new int[2];
        newCell[0] = previousCell[0] + 1;
        newCell[1] = previousCell[1];
        if (newCell[0] > this.model.getHeight()) {
            System.out.println("Maze row out of bound");
            return null;
        }
        if (this.model.isWallAt(newCell[0], newCell[1])) {
            System.out.println("You can't go there, there is a wall ahead !");
            return null;
        } else return newCell;
    }

    /**
     * method used to solve interactive the labyrinth
     *
     * @param previousCell = cell given as parameter
     * @return the next cell that is just above the cell given as parameter
     */
    @Override
    public int[] up(int[] previousCell) {
        int[] newCell = new int[2];
        newCell[0] = previousCell[0] - 1;
        newCell[1] = previousCell[1];
        if (newCell[0] < 0 || newCell[1] < 0) {
            System.out.println("Maze row out of bound");
            return null;
        }
        if (this.model.isWallAt(newCell[0], newCell[1])) {
            System.out.println("You can't go there, there is a wall ahead !");
            return null;
        } else return newCell;
    }

    /**
     * method used to solve interactive the labyrinth
     *
     * @param previousCell = cell given as parameter
     * @return the next cell that is just in the right of the cell given as parameter
     */
    @Override
    public int[] right(int[] previousCell) {
        int[] newCell = new int[2];
        newCell[0] = previousCell[0];
        newCell[1] = previousCell[1] + 1;
        if (newCell[1] > this.model.getHeight()) {
            System.out.println("Maze row out of bound");
            return null;
        }
        if (this.model.isWallAt(newCell[0], newCell[1])) {
            System.out.println("You can't go there, there is a wall ahead !");
            return null;
        } else return newCell;
    }

    /**
     * method used to solve interactive the labyrinth
     *
     * @param previousCell = cell given as parameter
     * @return the next cell that is just in the left of the cell given as parameter
     */
    @Override
    public int[] left(int[] previousCell) {
        int[] newCell = new int[2];
        newCell[0] = previousCell[0];
        newCell[1] = previousCell[1] - 1;
        if (newCell[1] < 0) {
            System.out.println("Maze row out of bound");
            return null;
        }
        if (this.model.isWallAt(newCell[0], newCell[1])) {
            System.out.println("You can't go there, there is a wall ahead !");
            return null;
        } else return newCell;
    }

    /**
     * method to register an observer to the internal list of observers
     *
     * @param observer
     */
    @Override
    public void register(LabyrinthObserver observer) {

    }

    /**
     * method to unregister an observer from the internal list of observers
     *
     * @param observer
     */
    @Override
    public void unregister(LabyrinthObserver observer) {

    }

    /**
     * method to unregister all observers from the internal list of observers
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
