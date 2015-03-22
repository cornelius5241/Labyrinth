package labyrinth;

import java.io.File;

/**
 * Created by cornelius on 3/19/15.
 */
public interface LabyrinthModel<T> {

    public int getRowCount();
    public int getColumnCount();

    public boolean isFreeAt(int x,int y);
    public boolean isWallAt(int x,int y);

    public T[] getStartCell();
    public T[] getFinnishCell();

    public void generateLabyrinth(File filename);
    public void generateLabyrinth(int min,int max);

}
