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
                cells[row][col].row = row; //added
                cells[row][col].col = col; //added
            }
        }

        for (int row = 0; row < dim; row++){
            for (int col = 0; col < dim; col++){
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

        }
        else {
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
        for (int i = -radius; i <= radius; i++){
            for (int j = -radius; j <= radius; j++){
                if (i == 0 && j == 0) continue;
                int row = (asker.row + i + dim) % dim;
                int col = (asker.col + j + dim) % dim;
                neighbors.add(cells[row][col]);
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
