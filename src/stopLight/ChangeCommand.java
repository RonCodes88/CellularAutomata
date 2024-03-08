package stopLight;
import mvc.*;

/*
Edits:
   Ronald 3/4/24: created file

*/

public class ChangeCommand extends Command {

    public ChangeCommand(Model model) {
        super(model);
    }

    public void execute() {
        Stoplight light = (Stoplight)model;
        light.change();
    }

}
