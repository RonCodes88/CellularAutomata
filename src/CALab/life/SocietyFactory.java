
package CALab.life;

import CALab.*;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class SocietyFactory extends GridFactory {
    public Model makeModel(){
        return new Society();
    }

}

