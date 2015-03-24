package labyrinth.main;

import labyrinth.controller.IntegerLabyrinthSolver;
import labyrinth.model.IntegerLabyrinth;

import java.util.Scanner;

/**
 * Created by cornelius on 3/19/15.
 */
public class Main {

    public static void main(String[] args) {

        IntegerLabyrinth X = new IntegerLabyrinth("D:\\UAIC - Computer Science\\UAIC\\AnII-Sem2\\PA-Java\\Labyrinth\\src\\labyrinth\\model\\input.txt");
        IntegerLabyrinth Z = new IntegerLabyrinth();
        int ans = 1;

        while (ans != 0) {
            IntegerLabyrinth Y = new IntegerLabyrinth(15, 15);
            IntegerLabyrinthSolver ILS = new IntegerLabyrinthSolver(Y);
            ILS.updateView();
            Scanner keyboard = new Scanner(System.in);
            ans = keyboard.nextInt();
        }
    }
}
