package labyrinth.controller;

import labyrinth.model.IntegerLabyrinth;
import labyrinth.observer.IntegerLabyrinthObserver;
import labyrinth.observer.LabyrinthObserver;
import labyrinth.view.IntegerLabyrinthView;

import java.util.*;

/**
 * Created by Cornelius on 24.03.2015.
 */
public class IntegerLabyrinthSolver implements LabyrinthSolver<Integer[]> {

    private final LabyrinthObserver MUTEX = new IntegerLabyrinthObserver();
    private IntegerLabyrinth model;
    private IntegerLabyrinthView view;
    private List<LabyrinthObserver> observers;
    private boolean solved;
    private Map<Integer, Integer> solvingPath;
    private Integer[] startCell;
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
        this.startCell = new Integer[2];
    }

    /**
     * Constructor with no parameters
     */
    public IntegerLabyrinthSolver() {
        this.model = new IntegerLabyrinth();
        this.view = new IntegerLabyrinthView();
        this.solved = false;
        this.startCell = new Integer[2];
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
        this.startCell = new Integer[2];
    }

    /**
     * updates the view class with the model
     */
    public void updateView() {
        view.show(model);
    }

    /**
     * test if the labyrinth has been solved
     *
     * @return true if so
     */
    public boolean isSolved() {
        return solved;
    }

    /**
     * @return the next cell to be explored for the solving of the maze
     */
    @Override
    public Integer[] nextCellToExplore(char move, Integer[] previousCell) {
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

    public void addPath(Integer[] cell) {
        solvingPath.put(cell[0], cell[1]);
        this.model.setPathAt(cell);
    }

    public void removePath(Integer[] cell) {
        solvingPath.remove(cell[0], cell[1]);
        this.model.unsetPathAt(cell);
    }

    @Override
    public void interactiveSolve() {
        this.startCell = this.model.getStartCell();
        this.solvingPath = new HashMap<Integer, Integer>();
        solvingPath.put(startCell[0], startCell[1]);
        Integer[] previousCell = new Integer[2];
        previousCell[0] = startCell[0];
        previousCell[1] = startCell[1];
        while (!isSolved()) {
            Integer[] currentCell = nextCellToExplore(readNextMove(), previousCell);
            if (currentCell != null) {
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

    /**
     * method used to solve interactive the labyrinth
     *
     * @param previousCell = cell given as parameter
     * @return the next cell that is just under the cell given as parameter
     */
    @Override
    public Integer[] down(Integer[] previousCell) {
        Integer[] newCell = new Integer[2];
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
    public Integer[] up(Integer[] previousCell) {
        Integer[] newCell = new Integer[2];
        newCell[0] = previousCell[0] - 1;
        newCell[1] = previousCell[1];
        if (newCell[0] < 0) {
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
    public Integer[] right(Integer[] previousCell) {
        Integer[] newCell = new Integer[2];
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
    public Integer[] left(Integer[] previousCell) {
        Integer[] newCell = new Integer[2];
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
        if (observer == null) throw new NullPointerException("Null Observer");
        synchronized (MUTEX) {
            if (!observers.contains(observer)) observers.add(observer);
        }
    }

    /**
     * method to unregister an observer from the internal list of observers
     *
     * @param observer
     */
    @Override
    public void unregister(LabyrinthObserver observer) {
        synchronized (MUTEX) {
            observers.remove(observer);
        }
    }

    /**
     * method to unregister all observers from the internal list of observers
     */
    @Override
    public void unregister() {
        synchronized (MUTEX) {
            observers.clear();
        }
    }

    /**
     * method to notify observers of change
     */
    @Override
    public void notifyObservers() {
        List<LabyrinthObserver> observersLocal = null;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed)
                return;
            observersLocal = new ArrayList<LabyrinthObserver>(this.observers);
            this.changed = false;
        }
        for (LabyrinthObserver observer : observersLocal) {
            observer.update();
        }
    }

    /**
     * method to get updates from subject
     *
     * @param observer
     * @return
     */
    @Override
    public Object getUpdate(LabyrinthObserver observer) {
        return null;
    }
}
