package labyrinth.view;

import labyrinth.logic.LabyrinthView;
import labyrinth.model.IntegerLabyrinth;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Cornelius on 24.03.2015.
 */
public class IntegerLabyrinthView implements LabyrinthView<IntegerLabyrinth>, Serializable {

    /**
     * @param model
     * @return the string representation of the model
     */
    @Override
    public String toString(IntegerLabyrinth model) {
        return "IntegerLabyrinth{" +
                ", labyrinth=" + Arrays.toString(model.getLabyrinth()) +
                ", rowCount=" + model.getHeight() +
                ", columnCount=" + model.getWidth() +
                ", startCell=" + Arrays.toString(model.getStartCell()) +
                ", finishCell=" + Arrays.toString(model.getFinishCell()) +
                '}';
    }

    /**
     * Prints the labyrinth with a certain modification of how it is saved in memory
     *
     * @param labyrinthModel
     */
    @Override
    public void show(IntegerLabyrinth labyrinthModel) {
        for (int j = 0; j < labyrinthModel.getWidth(); j++)
            System.out.print("-~");
        System.out.println();
        for (int i = 0; i < labyrinthModel.getHeight(); i++) {
            System.out.print("|");
            for (int j = 0; j < labyrinthModel.getWidth(); j++) {
                if (labyrinthModel.isFreeAt(i, j))
                    System.out.print(" |");
                else if (labyrinthModel.isWallAt(i, j)) {
                    System.out.print("#|");
                } else if (labyrinthModel.isStartAt(i, j)) {
                    System.out.print("S|");
                } else if (labyrinthModel.isFinishAt(i, j)) {
                    System.out.print("F|");
                } else if (labyrinthModel.isPathAt(i, j)) {
                    System.out.print("X|");
                }
            }
            System.out.println();
        }
        for (int j = 0; j < labyrinthModel.getWidth(); j++)
            System.out.print("-~");
        System.out.println();
    }

    /**
     * Prints the labyrinth how it is saved in memory
     *
     * @param labyrinth
     */
    @Override
    public void print(IntegerLabyrinth labyrinth) {
        for (int i = 0; i < labyrinth.getHeight(); i++) {
            for (int j = 0; j < labyrinth.getWidth(); j++)
                System.out.print(labyrinth.getLabyrinth()[i][j].toString());
            System.out.println();
        }
    }
}
