package labyrinth.generator;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Cornelius on 27.03.2015.
 */
public class FileLabyrinthGenerator implements LabyrinthGenerator {

    private int[] startCell;
    private int[] finishCell;
    private File file;
    private int height;
    private int width;
    private char[][] labyrinth;

    /**
     * Constructor that reads a file and use it's input to create a new maze
     *
     * @param filename
     */
    public FileLabyrinthGenerator(String filename) {
        this.startCell = new int[2];
        this.finishCell = new int[2];
        this.file = new File(filename);
        generateLabyrinth();
    }

    public char[][] getLabyrinth() {
        return labyrinth;
    }

    public File readFile() {
        Scanner keyboard = new Scanner(System.in);
        return new File(keyboard.nextLine());
    }

    /**
     * reads a file and generates a maze from it
     *
     * @param file
     */
    public void generateLabyrinth(File file) {
        String maze[][] = new String[(int) Math.sqrt(file.length())][];
        int rows = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    maze[rows] = new String[line.split(" ").length];
                    maze[rows++] = line.split(" ");
                }
                br.close();
                this.height = rows;
                this.width = maze[0].length;
                this.labyrinth = new char[rows][width];
                for (int i = 0; i < rows; i++)
                    for (int j = 0; j < maze[i].length; j++)
                        this.labyrinth[i][j] = maze[i][j].charAt(0);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found.Please insert a new filename path");
            generateLabyrinth(readFile());
        }
    }

    @Override
    public void generateLabyrinth() {
        generateLabyrinth(this.file);
    }


    @Override
    public void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                System.out.print(labyrinth[i][j] + " ");
            System.out.println();
        }
    }
}
