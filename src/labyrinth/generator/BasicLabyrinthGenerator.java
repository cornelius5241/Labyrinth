package labyrinth.generator;

/**
 * Created by Cornelius on 25.03.2015.
 */
public class BasicLabyrinthGenerator implements LabyrinthGenerator {

    private int height;
    private int width;
    private Integer[][] labyrinth;

    /**
     * random generates the walls and the rooms of the labyrinth
     */
    @Override
    public void generateLabyrinth() {
        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++) {
                labyrinth[i][j] = randomValue(0, 2);
            }
    }


    @Override
    public void display() {

    }

    /**
     * set 2 random values as the width and height of the maze between the min and the max params
     *
     * @param min
     * @param max
     */
    public void randomSize(int min, int max) {
        this.width = randomValue(min, max);
        this.height = randomValue(min, max);
        this.labyrinth = new Integer[height][width];
    }

    /**
     * uses the min and max params
     *
     * @param min
     * @param max
     * @return a random int value between the params
     */
    public int randomValue(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

}
