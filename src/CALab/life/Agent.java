package CALab.life;

import CALab.Cell;
import mvc.Utilities;

import java.awt.*;

public class Agent extends Cell {
    private int status = 0;
    private int ambience = 0;
    private Color color = Color.RED;

    public Agent(Society society){
        super(society);

    }
    public int getStatus(){
        return status;
    }

    public int getAmbience(){
        return ambience;
    }
    public void update() {
        if (status == 1)
            if (Society.death.contains(ambience)) {
                this.status = 0;
                this.color = Color.RED;
            }
        else if (status == 0) {
                if (Society.rebirth.contains(ambience)) {
                    this.status = 1;
                    this.color = Color.GREEN;
                }
            }
        notifySubscribers();
    }

    public void observe(){
        int aliveNeighbors = 0;
        for (Cell c : neighbors){
            if (c != null && ((Agent)c).status == 1){
                aliveNeighbors++;
            }
        }
        this.ambience = aliveNeighbors;

    }

    public void interact(){
        /* leave as blank */
    }
    public void nextState(){
        if (this.status == 0 && Society.death.contains(status)){
            this.status = 1;
            this.color = Color.GREEN;
        }
        else if (this.status == 1 && Society.death.contains(status)){
            this.status = 0;
            this.color = Color.RED;
        }

    }
    // set status to a random or initial value
    public void reset(boolean randomly){
        if (randomly) {
            int factor = Utilities.rng.nextInt(100);
            if (factor < Society.percentAlive) {
                this.status = 1;
                this.color = Color.GREEN;
            } else {
                this.status = 0;
                this.color = Color.RED;
            }
        }
        else {
            this.status = 0;
            this.color = Color.RED;
            this.ambience = 0;
        }

    }

    public Color getColor(){
        return this.color;
    }



}
