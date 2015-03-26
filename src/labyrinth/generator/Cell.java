package labyrinth.generator;

/**
 * An auxiliary class ;
 * contains one cell's coordinates
 */
public class Cell {

    private int x;
    private int y;
    private Cell parent;

    protected Cell(int x, int y) {
        this.setX(x);
        this.setY(y);
        parent = null;
    }

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
