package CALab;

import java.util.*;

import mvc.*;

public abstract class Grid extends Model {
    static private int time = 0;
    protected int dim = 20;
    protected Cell[][] cells;

    public int getDim() { return dim; }
    public int getTime() { return time; }
    public Cell getCell(int row, int col) { return cells[row][col]; }
    public abstract Cell makeCell(boolean uniform);


    public Grid(int dim) {
        this.dim = dim;
        cells = new Cell[dim][dim];
        populate();
    }
    public Grid() { this(20); }

    protected void populate() {
        // 1. use makeCell to fill in cells
        // 2. use getNeighbors to set the neighbors field of each cell
        boolean uniform = true;
        for (int row = 0; row < dim; row++){
            for (int col = 0; col < dim; col++){
                cells[row][col] = makeCell(uniform);
                cells[row][col].neighbors = getNeighbors(getCell(row, col), 1);
            }
        }

    }

    // called when Populate and clear buttons are clicked
    public void repopulate(boolean randomly) {
        if (randomly) {
            // randomly set the status of each cell
            for (int row = 0; row < dim; row++){
                for (int col = 0; col < dim; col++){
                    getCell(row, col).reset(true);
                }
            }

        } else {
            // set the status of each cell to 0 (dead)
            for (int row = 0; row < dim; row++){
                for (int col = 0; col < dim; col++){
                    getCell(row, col).reset(false);
                }
            }
        }
        // notify subscribers
        notifySubscribers();
    }


    public Set<Cell> getNeighbors(Cell asker, int radius) {
        /*
        return the set of all cells that can be reached from the asker in radius steps.
        If radius = 1 this is just the 8 cells touching the asker.
        Tricky part: cells in row/col 0 or dim - 1.
        The asker is not a neighbor of itself.
        */
        Set<Cell> neighbors = new HashSet<>();
        if (radius == 1 && asker != null) {
            //handles cells[0][0]
            if (asker.row == 0 && asker.col == 0) {
                neighbors.add(cells[dim - 1][dim - 1]);
                neighbors.add(cells[dim - 1][asker.col]);
                neighbors.add(cells[dim - 1][asker.col + 1]);
                neighbors.add(cells[asker.row][dim - 1]);
                neighbors.add(cells[asker.row][asker.col + 1]);
                neighbors.add(cells[asker.row + 1][dim - 1]);
                neighbors.add(cells[asker.row + 1][asker.col]);
                neighbors.add(cells[asker.row + 1][asker.col + 1]);
            }
            //handles cells[0][dim-1]
            else if (asker.row == 0 && asker.col == (dim - 1)) {
                neighbors.add(cells[dim - 1][asker.col - 1]);
                neighbors.add(cells[dim - 1][asker.col]);
                neighbors.add(cells[dim - 1][0]);
                neighbors.add(cells[asker.row][asker.col - 1]);
                neighbors.add(cells[asker.row][0]);
                neighbors.add(cells[asker.row + 1][asker.col - 1]);
                neighbors.add(cells[asker.row + 1][asker.col]);
                neighbors.add(cells[asker.row + 1][0]);
            }
            //handles cells[dim-1][0]
            else if (asker.row == (dim - 1) && asker.col == 0) {
                neighbors.add(cells[asker.row - 1][dim-1]);
                neighbors.add(cells[asker.row - 1][asker.col]);
                neighbors.add(cells[asker.row - 1][asker.col + 1]);
                neighbors.add(cells[asker.row][dim-1]);
                neighbors.add(cells[asker.row][asker.col + 1]);
                neighbors.add(cells[0][dim-1]);
                neighbors.add(cells[0][asker.col]);
                neighbors.add(cells[0][asker.col + 1]);
            }
            //handles cells[dim-1][dim-1]
            else if (asker.row == (dim-1) && asker.col == (dim-1)){
                neighbors.add(cells[asker.row - 1][asker.col - 1]);
                neighbors.add(cells[asker.row - 1][asker.col]);
                neighbors.add(cells[asker.row - 1][0]);
                neighbors.add(cells[asker.row][asker.col - 1]);
                neighbors.add(cells[asker.row][0]);
                neighbors.add(cells[0][asker.col - 1]);
                neighbors.add(cells[0][asker.col]);
                neighbors.add(cells[0][0]);
            }
            //top row cells excluding cells[0][0] and cells[0][dim-1]
            else if (asker.row == 0) {
                neighbors.add(cells[dim - 1][asker.col - 1]);
                neighbors.add(cells[dim - 1][asker.col]);
                neighbors.add(cells[dim - 1][asker.col + 1]);
                neighbors.add(cells[asker.row][asker.col - 1]);
                neighbors.add(cells[asker.row][asker.col + 1]);
                neighbors.add(cells[asker.row + 1][asker.col - 1]);
                neighbors.add(cells[asker.row + 1][asker.col]);
                neighbors.add(cells[asker.row + 1][asker.col + 1]);
            }
            //bottom row cells excluding cells[dim-1][0] and cells[dim-1][dim-1]
            else if (asker.row == (dim - 1)) {
                neighbors.add(cells[asker.row - 1][asker.col - 1]);
                neighbors.add(cells[asker.row - 1][asker.col]);
                neighbors.add(cells[asker.row - 1][asker.col + 1]);
                neighbors.add(cells[asker.row][asker.col - 1]);
                neighbors.add(cells[asker.row][asker.col + 1]);
                neighbors.add(cells[0][asker.col - 1]);
                neighbors.add(cells[0][asker.col]);
                neighbors.add(cells[0][asker.col + 1]);
            }
            //left most row cells excluding cells[0][0] and cells[dim-1][0]
            else if (asker.col == 0) {
                neighbors.add(cells[asker.row - 1][dim - 1]);
                neighbors.add(cells[asker.row - 1][asker.col]);
                neighbors.add(cells[asker.row - 1][asker.col + 1]);
                neighbors.add(cells[asker.row][dim - 1]);
                neighbors.add(cells[asker.row][asker.col + 1]);
                neighbors.add(cells[asker.row + 1][dim - 1]);
                neighbors.add(cells[asker.row + 1][asker.col]);
                neighbors.add(cells[asker.row + 1][asker.col + 1]);

            }
            //right most row cells excluding cells[0][dim-1] and cells[dim-1][dim-1]
            else if (asker.col == (dim - 1)) {
                neighbors.add(cells[asker.row - 1][asker.col - 1]);
                neighbors.add(cells[asker.row - 1][asker.col]);
                neighbors.add(cells[asker.row - 1][0]);
                neighbors.add(cells[asker.row][asker.col - 1]);
                neighbors.add(cells[asker.row][0]);
                neighbors.add(cells[asker.row + 1][asker.col - 1]);
                neighbors.add(cells[asker.row + 1][asker.col]);
                neighbors.add(cells[asker.row + 1][0]);
            }
            //handles all other cells not on the sides of grid
            else {
                neighbors.add(cells[asker.row - 1][asker.col - 1]);
                neighbors.add(cells[asker.row - 1][asker.col]);
                neighbors.add(cells[asker.row - 1][asker.col + 1]);
                neighbors.add(cells[asker.row][asker.col - 1]);
                neighbors.add(cells[asker.row][asker.col + 1]);
                neighbors.add(cells[asker.row + 1][asker.col - 1]);
                neighbors.add(cells[asker.row + 1][asker.col]);
                neighbors.add(cells[asker.row + 1][asker.col + 1]);
            }
        }
        return neighbors;
    }


    // cell phases:

    public void observe() {
        // call each cell's observe method and notify subscribers
        for (int row = 0; row < dim; row++){
            for (int col = 0; col < dim; col++){
                getCell(row, col).observe();
                notifySubscribers();
            }
        }

    }

    public void interact() {
        for (int row = 0; row < dim; row++){
            for (int col = 0; col < dim; col++){
                getCell(row, col).interact();
            }
        }
    }

    public void update() {
        for (int row = 0; row < dim; row++){
            for (int col = 0; col < dim; col++){
                getCell(row, col).update();
            }
        }
    }

    public void updateLoop(int cycles) {
        observe();
        for(int cycle = 0; cycle < cycles; cycle++) {
            interact();
            update();
            observe();
            time++;
            System.out.println("time = " + time);
        }
    }
}
