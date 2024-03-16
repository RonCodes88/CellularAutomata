package CALab.life;


import CALab.Cell;
import CALab.Grid;
import CALab.life.Agent;

import java.util.HashSet;
import java.util.Set;

public class Society extends Grid {
    public static Set<Integer> rebirth = new HashSet<Integer>();
    public static Set<Integer> death = new HashSet<Integer>();
    public static int percentAlive = 50;
    public static int numOfAgentsAlive;

    public Society(){
        super();
        numOfAgentsAlive = dim*dim*(percentAlive/100);

    }

    static {
        rebirth.add(3);
        death.add(0);
        death.add(1);
        death.add(4);
        death.add(5);
        death.add(6);
        death.add(7);
        death.add(8);
    }

    @Override
    public Cell makeCell(boolean uniform) {
        return new Agent(this);
    }
}
