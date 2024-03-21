
package CALab;

import mvc.*;

public class ClearCommand extends Command {
    public ClearCommand(Model model){
        super(model);
    }

    public void execute() throws Exception{
        if (!(model instanceof Grid)){
            throw new Exception("Model must instantiate Grid");
        }
        Grid grid = (Grid)model;
        grid.repopulate(false);
    }
}

