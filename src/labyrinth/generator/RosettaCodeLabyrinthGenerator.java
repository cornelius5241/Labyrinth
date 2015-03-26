package labyrinth.generator;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Cornelius on 25.03.2015.
 */
public class RosettaCodeLabyrinthGenerator implements LabyrinthGenerator {

    private final int height;
    private final int width;
    private final int[][] labyrinth;

    /**
     * Constructor that receives 2 parameters to generate a labyrinth
     *
     * @param height
     * @param width
     */
    public RosettaCodeLabyrinthGenerator(int height, int width) {
        this.height = height;
        this.width = width;
        labyrinth = new int[this.height][this.width];
        generateLabyrinth();
    }

    /**
     * test if value is between 0 and upper
     *
     * @param value
     * @param upper
     * @return boolean
     */
    private static boolean between(int value, int upper) {
        return (value >= 0) && (value < upper);
    }

    /**
     * takes the random generated int matrix by the Rosetta algorithm and parse it to a string
     *
     * @return string with 0-room 1-wall
     */
    public String parseToString() {
        StringBuilder stringLabyrinth = new StringBuilder();
        for (int i = 0; i < width; i++) {
            // draw the north edge
            if (i == 0) {
                for (int j = 0; j < height; j++) {
                    if (j == 0) {
                        stringLabyrinth.append((labyrinth[j][i] & 8) == 0 ? "0 " : "   ");
                    } else {
                        stringLabyrinth.append((labyrinth[j][i] & 8) == 0 ? "1 0 " : "0 0 ");
                    }
                }
            } else {
                for (int j = 0; j < height; j++) {
                    if (j == 0) {
                        stringLabyrinth.append((labyrinth[j][i] & 1) == 0 ? "1 " : "0 ");
                    } else {
                        stringLabyrinth.append((labyrinth[j][i] & 1) == 0 ? "1 1 " : "1 0 ");
                    }
                }
                stringLabyrinth.append("\n");
                // draw the west edge
                for (int j = 0; j < height; j++) {
                    if (j == 0) {
                        stringLabyrinth.append((labyrinth[j][i] & 8) == 0 ? "0 " : "0 0 ");
                    } else if (i == width - 1 && j == height - 1) {
                        stringLabyrinth.append((labyrinth[j][i] & 8) == 0 ? "1 0 " : "0 0 ");
                    } else {
                        stringLabyrinth.append((labyrinth[j][i] & 8) == 0 ? "1 0 " : "0 0 ");
                    }
                }
            }
            stringLabyrinth.append("\n");
        }
        return stringLabyrinth.toString();
    }

    public Integer[][] parseToInteger() {
        String aux = parseToString();
        Integer[][] integerLabyrinth = new Integer[2 * height - 1][2 * width - 1];
        int i = 0, j = 0;
        for (String line : aux.split("\n")) {
            j = 0;
            for (String element : line.split(" ")) {
                integerLabyrinth[i][j++] = Integer.parseInt(element);
            }
            i++;
        }/*
        for( i=0;i<integerLabyrinth.length;i++){
            for( j=0;j<integerLabyrinth[i].length;j++)
                System.out.print(integerLabyrinth[i][j] + " ");
            System.out.println();
            }*/
        return integerLabyrinth;
    }

    /**
     * the Rosetta algorithm for generating labyrinths
     * in a recursive way
     * Generate and show a maze, using the simple Depth-first search algorithm.
     * Start at a random cell.
     * Mark the current cell as visited, and get a list of its neighbors. For each neighbor, starting with a randomly selected neighbor:
     * If that neighbor hasn't been visited, remove the wall between this cell and that neighbor, and then recurse with that neighbor as the current cell.
     *
     * @param x
     * @param y
     */
    private void generateLabyrinth(int x, int y) {
        Directions[] dirs = Directions.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (Directions dir : dirs) {
            int newX = x + dir.getX();
            int newY = y + dir.getY();
            if (between(newX, height) && between(newY, width)
                    && (labyrinth[newX][newY] == 0)) {
                labyrinth[x][y] |= dir.getBit();
                labyrinth[newX][newY] |= dir.getOpposite().getBit();
                generateLabyrinth(newX, newY);
            }
        }
    }

    /**
     * basic generate method from interface
     */
    @Override
    public void generateLabyrinth() {
        generateLabyrinth(0, 0);
    }

    /**
     * method that prints on screen the representation of a labyrinth generated with Rosetta
     */
    @Override
    public void display() {
        for (int i = 0; i < width; i++) {
            // draw the north edge
            for (int j = 0; j < height; j++) {
                System.out.print((labyrinth[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            // draw the west edge
            for (int j = 0; j < height; j++) {
                System.out.print((labyrinth[j][i] & 8) == 0 ? "|   " : "    ");
            }
            System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < height; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

}
