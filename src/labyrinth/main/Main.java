package labyrinth.main;

import labyrinth.controller.StringLabyrinthSolver;

/**
 * Created by cornelius on 3/19/15.
 */
public class Main {

    public static void main(String[] args) {

        // IntegerLabyrinthSolver ILS = new IntegerLabyrinthSolver();
        // ILS.updateView();
        // ILS.solve();

        StringLabyrinthSolver SLS = new StringLabyrinthSolver("src/labyrinth/resources/labyrinthIN.txt");
        SLS.updateView();
        SLS.solve();
        SLS.updateView();
    }
}
