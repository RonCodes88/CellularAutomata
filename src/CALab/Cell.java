package CALab;

/*
Edits:
   Caden 3/14/24: Created class
*/

import mvc.*;

import java.awt.*;
import java.io.Serializable;
import java.util.*;

public abstract class Cell extends Publisher implements Serializable {
    protected int row = 0;
    protected int col = 0;
    protected Set<Cell> neighbors = new HashSet<Cell>();
    protected Cell partner = null;
    protected Grid myGrid = null;

    public abstract void observe();
    public abstract void interact();
    public abstract void reset(boolean isRandom);
    public abstract int getStatus();
    public abstract void nextState();

    public void choosePartner() {
        if (partner == null && neighbors.size() != 0)
        {
            Cell[] neighborArr = new Cell[neighbors.size()];

            // Converts neighbors set to neighborArr array
            int i = 0;
            for (Cell c : neighbors)
            {
                neighborArr[i++] = c;
            }

            int randIndex = Utilities.rng.nextInt(neighbors.size());
            for (int offset = 0; offset < neighbors.size(); offset++)
            {
                int index = (randIndex + offset) % neighbors.size(); // Increments starting at randIndex, wrapping to 0 when reaches end of array
                if (neighborArr[index].partner == null)
                {
                    partner = neighborArr[index];
                    neighborArr[index].partner = this;
                    return;
                }
            }
        }
    }

    public void unPartner()
    {
        if (partner != null)
        {
            if (partner.partner != null) partner.partner = null;
            partner = null;
        }
    }

    public Color getColor()
    {
        return Color.DARK_GRAY;
    }
}
