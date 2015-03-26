package labyrinth.observer;

import labyrinth.controller.LabyrinthSolver;

/**
 * Created by cornelius on 3/19/15.
 */
public interface LabyrinthObserver<T extends LabyrinthSolver> {

    /**
     * method to update the observer, used by subject
     */
    public void update();

    /**
     * attach with subject to observe
     */
    public void setSubject(LabyrinthSolver subject);

    public void processCell();

    public void processSolution();

}
