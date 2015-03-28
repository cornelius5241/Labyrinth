package labyrinth.logic;

/**
 * Created by cornelius on 3/19/15.
 */
public interface LabyrinthObserver<T extends LabyrinthSolver> {
    /**
     * method to update the observer, used by subject
     */
    void update();

    void processCell();

    void processSolution();

}
