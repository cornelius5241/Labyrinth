package labyrinth.observer;

import labyrinth.controller.LabyrinthSolver;
import labyrinth.controller.StringLabyrinthSolver;

/**
 * Created by Cornelius on 27.03.2015.
 */
public class StringLabyrinthObserver implements LabyrinthObserver<StringLabyrinthSolver> {
    /**
     * method to update the observer, used by subject
     */
    @Override
    public void update() {

    }

    /**
     * attach with subject to observe
     *
     * @param subject
     */
    @Override
    public void setSubject(LabyrinthSolver subject) {

    }

    @Override
    public void processCell() {

    }

    @Override
    public void processSolution() {

    }
}
