package labyrinth.view;

import labyrinth.logic.LabyrinthView;
import labyrinth.model.StringLabyrinth;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Cornelius on 27.03.2015.
 */
public class StringLabyrinthView implements LabyrinthView<StringLabyrinth>, Serializable {
    /**
     * @param model
     * @return the string representation of the model
     */
    @Override
    public String toString(StringLabyrinth model) {
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
     * @param model
     */
    @Override
    public void show(StringLabyrinth model) {
        for (int j = 0; j < model.getWidth(); j++)
            System.out.print("--");
        System.out.println();
        for (int i = 0; i < model.getHeight(); i++) {
            System.out.print("|");
            for (int j = 0; j < model.getWidth(); j++) {
                if (model.isStartAt(i, j)) {
                    System.out.print("S|");
                } else if (model.isWallAt(i, j)) {
                    System.out.print("#|");
                } else if (model.isFreeAt(i, j)) {
                    System.out.print(" |");
                } else if (model.isFinishAt(i, j)) {
                    System.out.print("F|");
                } else if (model.isPathAt(i, j)) {
                    System.out.print("*|");
                } else System.out.print("?|");
            }
            System.out.println();
        }
        for (int j = 0; j < model.getWidth(); j++)
            System.out.print("--");
        System.out.println();
    }

    /**
     * Prints the labyrinth how it is saved in memory
     *
     * @param model
     */
    @Override
    public void print(StringLabyrinth model) {
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++)
                System.out.print(model.getLabyrinth()[i][j]);
            System.out.println();
        }
    }
}