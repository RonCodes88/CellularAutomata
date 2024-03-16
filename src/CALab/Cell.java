package CALab;

import java.awt.*;
import java.util.*;
import java.io.*;
import mvc.*;

public abstract class Cell extends Publisher implements Serializable {

    protected int row = 0, col = 0;
    protected Set<Cell> neighbors = new HashSet<Cell>();
    protected Grid myGrid = null;
    protected Cell partner = null;

    public Cell(Grid myGrid){
        this.myGrid = myGrid;
    }

    // choose a random neighbor as a partner
    public void choosePartner() {

        neighbors = myGrid.getNeighbors(this, 1);

        if (partner == null && neighbors != null) {
			/*
			Set partner to null
			Convert neighbors set to a local array
			Starting at a random position in the array search for a neighbor without a partner
			Make the first such neighbor (if any) the partner and set its partner field to this
			*/

            Cell[] neighborArr = new Cell[neighbors.size()];

            int i = 0;
            // Convert neighbors set to neighborArr array
            for (Cell c : neighbors){
                neighborArr[i++] = c;
            }

            int randIndex = Utilities.rng.nextInt(neighbors.size());

            while (neighborArr[randIndex].partner != null){
                randIndex = Utilities.rng.nextInt(neighbors.size());
            }

            if (neighborArr[randIndex].partner == null){
                neighborArr[randIndex].partner = this;
            }
            else {
                System.out.println("Cell's neighbors all already have partners");
            }

        }

    }

    public void unpartner() {
        if (partner != null) {
            if (partner.partner != null) {
                partner.partner = null;
            }
            partner = null;
        }
    }

    // observer neighbors' states
    public abstract void observe();
    // interact with a random neighbor
    public abstract void interact();
    // update my state
    public abstract void update();

    // set status to status + 1 mod whatever
    public abstract void nextState();
    // set status to a random or initial value
    public abstract void reset(boolean randomly);

    public abstract Color getColor();

    public abstract int getStatus();

}
