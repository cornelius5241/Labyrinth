package labyrinth.view;

import labyrinth.model.LabyrinthModel;

/**
 * Created by cornelius on 3/19/15.
 */
public interface LabyrinthView<T extends LabyrinthModel> {

    /**
     * @param model
     * @return the string representation of the model
     */
    public String toString(T model);

    /**
     * Prints the labyrinth with a certain modification of how it is saved in memory
     *
     * @param model
     */
    public void show(T model);

    /**
     * Prints the labyrinth how it is saved in memory
     *
     * @param model
     */
    public void print(T model);
}
