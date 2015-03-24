package labyrinth.view;

import labyrinth.model.IntegerLabyrinth;

import java.util.Arrays;

/**
 * Created by Cornelius on 24.03.2015.
 */
public class IntegerLabyrinthView implements LabyrinthView<IntegerLabyrinth> {

    /**
     * @param model
     * @return the string reprezentation of the model
     */
    @Override
    public String toString(IntegerLabyrinth model) {
        return "IntegerLabyrinth{" +
                ", labyrinth=" + Arrays.toString(model.getLabyrinth()) +
                ", rowCount=" + model.getRowCount() +
                ", columnCount=" + model.getColumnCount() +
                ", startCell=" + Arrays.toString(model.getStartCell()) +
                ", finishCell=" + Arrays.toString(model.getFinishCell()) +
                '}';
    }

    /**
     * Prints the labyrinth with a certain modification of how it is saved in memory
     *
     * @param labyrinth
     */
    @Override
    public void show(IntegerLabyrinth labyrinth) {
        for (int j = 0; j < labyrinth.getRowCount(); j++)
            System.out.print("-~-");
        System.out.println();
        for (int i = 0; i < labyrinth.getRowCount(); i++) {
            System.out.print("|");
            for (int j = 0; j < labyrinth.getColumnCount(); j++) {
                if (labyrinth.getLabyrinth()[i][j] == labyrinth.getROOM())
                    System.out.print(" " + " |");
                else if (labyrinth.getLabyrinth()[i][j] == labyrinth.getWALL()) {
                    System.out.print("#" + " |");
                } else if (labyrinth.getLabyrinth()[i][j] == labyrinth.getSTART()) {
                    System.out.print("S" + " |");
                } else if (labyrinth.getLabyrinth()[i][j] == labyrinth.getFINISH()) {
                    System.out.print("F" + " |");
                }
            }
            System.out.println();
        }
        for (int j = 0; j < labyrinth.getRowCount(); j++)
            System.out.print("-~-");
    }

    /**
     * Prints the labyrinth how it is saved in memory
     *
     * @param labyrinth
     */
    @Override
    public void print(IntegerLabyrinth labyrinth) {
        for (int i = 0; i < labyrinth.getRowCount(); i++) {
            for (int j = 0; j < labyrinth.getColumnCount(); j++)
                System.out.print(labyrinth.getLabyrinth()[i][j].toString());
            System.out.println();
        }
    }
}
