package labyrinth.observer;

/**
 * Created by cornelius on 3/19/15.
 */
public interface LabyrinthObserver<T> {

    public void processCell();
    public void processSolution();

}
