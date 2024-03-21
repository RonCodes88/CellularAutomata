
package CALab;
import mvc.*;


public class RunOneCommand extends Command{
    public RunOneCommand(Model model){
        super(model);
    }

    public void execute() throws Exception {
        if (!(model instanceof Grid)) {
            throw new Exception("Model must instantiate Grid");
        }
        Grid grid = (Grid)model;
        grid.updateLoop(1);
    }

}

