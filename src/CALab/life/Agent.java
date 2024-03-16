package CALab.life;

import CALab.Cell;
import mvc.Utilities;

import java.awt.*;

public class Agent extends Cell {
    private int status = 0;
    private int ambience = 8;
    private Color color = Color.RED;

    public Agent(Society society){
        super(society);

    }
    public int getStatus(){
        return status;
    }
    public void update(){
        if (Society.death.contains(ambience)){
            this.status = 0;
            this.color = Color.RED;
        }
        else if (Society.rebirth.contains(ambience)){
            this.status = 1;
            this.color = Color.GREEN;
        }
    }

    public void observe(){
        int aliveNeighbors = 0;
        for (Cell c : neighbors){
            if (((Agent)c).status == 1){
                aliveNeighbors++;
            }
        }
        this.ambience = aliveNeighbors;

    }

    public void interact(){
        /* leave as blank */
    }
    public void nextState(){
        status = status+1;
    }
    // set status to a random or initial value
    public void reset(boolean randomly){
        if (randomly) {
            int randIndex = Utilities.rng.nextInt(2);
            if (randIndex == 1 && Society.numOfAgentsAlive > 0) {
                this.status = 1;
                this.color = Color.GREEN;
                Society.numOfAgentsAlive--;
            } else {
                this.status = 0;
                this.color = Color.RED;
            }
        }
        else {
            this.status = 0;
            this.color = Color.RED;
        }
    }

    public Color getColor(){
        return this.color;
    }



}
