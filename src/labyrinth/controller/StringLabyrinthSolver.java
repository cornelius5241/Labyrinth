package labyrinth.controller;

import labyrinth.logic.LabyrinthObserver;
import labyrinth.logic.LabyrinthSolver;
import labyrinth.model.StringLabyrinth;
import labyrinth.observer.StringLabyrinthObserver;
import labyrinth.resources.MyComparator;
import labyrinth.view.StringLabyrinthView;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Cornelius on 27.03.2015.
 */
public class StringLabyrinthSolver implements LabyrinthSolver<StringLabyrinth>, Serializable {

    private List<LabyrinthObserver> observers = new ArrayList<LabyrinthObserver>();
    private StringLabyrinth model;
    private StringLabyrinthView view;
    private boolean solved;
    private List<char[]> solutions = new ArrayList<char[]>();
    private char[] path;
    private int position = 0;

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
        this.register(new StringLabyrinthObserver(this));
    }

    /**
     * Constructor with no parameters
     */
    public StringLabyrinthSolver(String filename) {
        this.model = new StringLabyrinth(filename);
        this.view = new StringLabyrinthView();
        this.solved = false;
        this.register(new StringLabyrinthObserver(this));
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
        this.register(new StringLabyrinthObserver(this));
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
     * method used to serialize
     */
    @Override
    public void save() {
        System.out.println("Starting serializing...");
        try {
            writeIntLabyrinth();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method used to deserialize
     */
    @Override
    public LabyrinthSolver load() {
        System.out.println("Starting deserializing...");
        try {
            return readIntLabirynth();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeIntLabyrinth() throws IOException {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("./src/labyrinth/resources/stringlabyrinthsolver.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.model);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in ./src/labyrinth/resources/stringlabyrinthsolver.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public LabyrinthSolver readIntLabirynth() throws IOException {
        try {
            FileInputStream fileIn =
                    new FileInputStream("./src/labyrinth/resources/stringlabyrinthsolver.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            return (LabyrinthSolver) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
        return null;
    }

    /**
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

    public boolean validCoordinates(int[] cell) {
        return (((cell[0] >= 0) && (cell[0] < this.model.getHeight())) && ((cell[1] >= 0) && (cell[1] < this.model.getWidth())));
    }

    public void savePath(char[] path, int startPos, int endPos) {
        char[] aux = new char[endPos - startPos + 1];
        int i = 0;
        System.out.println("Found path to the exit: ");
        for (int pos = startPos; pos <= endPos; pos++) {
            aux[i++] = path[pos];
        }
        solutions.add(aux);
    }

    /**
     * @param cell the cell from where we start
     */
    public void solveRecursively(int[] cell, char direction) {
        if (!validCoordinates(cell)) {
            return;
        }
        path[position++] = direction;
        if (this.model.isFinishAt(cell[0], cell[1])) {
            notifyObservers(2); //processSolution
            System.out.println("Solved");
            savePath(path, 0, position - 1);
        }
        if ((this.model.isFreeAt(cell[0], cell[1])) || (this.model.isStartAt(cell[0], cell[1]))) {
            this.model.getLabyrinth()[cell[0]][cell[1]] = '?';  // cell has been processed
            notifyObservers(1);
            solveRecursively(down(cell), 'd');
            solveRecursively(right(cell), 'r');
            solveRecursively(up(cell), 'u');
            solveRecursively(left(cell), 'l');
            this.model.getLabyrinth()[cell[0]][cell[1]] = this.model.getROOM();  // cell has been processed
        }
        position--;
    }

    @Override
    public void solve() {
        path = new char[this.model.getHeight() * this.model.getWidth()];
        solveRecursively(model.getStartCell(), 'S');
        Collections.sort(solutions, new MyComparator(""));
        for (char[] sol : solutions) {
            System.out.println(sol);
        }
    }

    @Override
    public int[] down(int[] cell) {
        int[] newCell = new int[2];
        newCell[0] = cell[0] + 1;
        newCell[1] = cell[1];
        return newCell;
    }

    @Override
    public int[] up(int[] cell) {
        int[] newCell = new int[2];
        newCell[0] = cell[0] - 1;
        newCell[1] = cell[1];
        return newCell;
    }

    @Override
    public int[] right(int[] cell) {
        int[] newCell = new int[2];
        newCell[0] = cell[0];
        newCell[1] = cell[1] + 1;
        return newCell;
    }

    @Override
    public int[] left(int[] cell) {
        int[] newCell = new int[2];
        newCell[0] = cell[0];
        newCell[1] = cell[1] - 1;
        return newCell;
    }

    /**
     * method to register an observer to the internal list of observers
     *
     * @param observer
     */
    @Override
    public void register(LabyrinthObserver observer) {
        if (!observers.contains(observer))
            observers.add(observer);
    }

    /**
     * method to unregister an observer from the internal list of observers
     *
     * @param observer
     */
    @Override
    public void unregister(LabyrinthObserver observer) {
        if (observers.contains(observer))
            observers.remove(observer);
    }

    /**
     * method to unregister all observers from the internal list of observers
     */
    @Override
    public void unregister() {
        observers.clear();
    }

    /**
     * method to notify observers of change
     */
    @Override
    public void notifyObservers(int type) {
        switch (type) {
            case 1: {
                for (LabyrinthObserver obs : observers)
                    obs.processCell();
            }
            break;
            case 2: {
                for (LabyrinthObserver obs : observers) {
                    obs.processSolution();
                }

            }
            break;
            default:
                break;

        }
    }


}
