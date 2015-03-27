package labyrinth.observer;

import labyrinth.controller.StringLabyrinthSolver;

/**
 * Created by Cornelius on 27.03.2015.
 */
public class StringLabyrinthObserver implements LabyrinthObserver<StringLabyrinthSolver> {
    protected StringLabyrinthSolver subject;


    public StringLabyrinthObserver(StringLabyrinthSolver subject) {
        this.subject = subject;
        subject.register(this);
    }

    /**
     * method to update the observer, used by subject
     */
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
