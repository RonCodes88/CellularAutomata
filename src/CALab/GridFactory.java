
package CALab;

import mvc.*;


public abstract class GridFactory implements AppFactory {

    public abstract Model makeModel();

    public View makeView(Model m) {
        return new GridView((Grid)m);
    }

    public String[] getEditCommands() { return new String[] {"RUN1", "RUN50", "POPULATE", "CLEAR"}; }

    public Command makeEditCommand(Model model, String type, Object source) {
        if (type == "RUN1")
            return new RunOneCommand(model);
        else if (type == "RUN50")
            return new RunFiftyCommand(model);
        else if (type == "POPULATE")
            return new PopulateCommand(model);
        else if (type == "CLEAR")
            return new ClearCommand(model);
        return null;
    }

    public String getTitle() { return "CA Lab"; }

    public String[] getHelp() {
        return new String[] {"click RUN1 to run 1 cell", "click RUN50 to run 50 cells",
                "click POPULATE to sets the state of each cell to a random value",
                "click CLEAR to set the state of each cell to an initial value"};
    }

    public String about() {
        return "Cellular Automata version 1.0. Copyright 2024 by Ronald, Yogi, and Caden";
    }

}

