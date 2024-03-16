package CALab.life;

import CALab.Grid;
import CALab.GridFactory;
import mvc.Model;

public class SocietyFactory extends GridFactory {
    public Model makeModel(){
        return new Society();
    }
}
