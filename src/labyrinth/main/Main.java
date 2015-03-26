package labyrinth.main;

import labyrinth.controller.IntegerLabyrinthSolver;
import labyrinth.model.IntegerLabyrinth;

/**
 * Created by cornelius on 3/19/15.
 */
public class Main {

    public static void main(String[] args) {

        // IntegerLabyrinth X = new IntegerLabyrinth("");
        // IntegerLabyrinth Z = new IntegerLabyrinth();

        IntegerLabyrinth Y = new IntegerLabyrinth(10, 10);
            IntegerLabyrinthSolver ILS = new IntegerLabyrinthSolver(Y);
            ILS.updateView();
        ILS.interactiveSolve();

       /* RosettaCodeLabyrinthGenerator RCLG = new RosettaCodeLabyrinthGenerator(5, 5);
        System.out.print(RCLG.parseToString());
        RCLG.display();
        RCLG.parseToInteger();
       // RCLG.print();*/
    }
}
