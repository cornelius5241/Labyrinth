package labyrinth.observer;

import labyrinth.controller.IntegerLabyrinthSolver;

/**
 * Created by Cornelius on 24.03.2015.
 */
public class IntegerLabyrinthObserver implements LabyrinthObserver<IntegerLabyrinthSolver> {

    protected IntegerLabyrinthSolver subject;

    public IntegerLabyrinthObserver(IntegerLabyrinthSolver subject) {
        this.subject = subject;
        subject.register(this);
    }

    /**
     * method to update
     */
    @Override
    public void update() {
        this.subject.updateView();
    }

    @Override
    public void processCell() {
        this.update();
    }

    @Override
    public void processSolution() {
        this.update();
    }
}
