package CALab;

import mvc.*;
import mvc.Model;

public class RunFiftyCommand extends Command {
    public RunFiftyCommand(Model model){
        super(model);
    }
    public void execute() throws Exception {
        if (!(model instanceof Grid)) {
            throw new Exception("Model must instantiate Grid");
        }
        Grid grid = (Grid)model;
        grid.updateLoop(50);
    }
}
