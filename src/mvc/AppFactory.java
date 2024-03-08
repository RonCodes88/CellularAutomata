package mvc;

/*
Edits:
   Ronald 3/4/24: created file
   Yogi 3/5/24: changed from class to interface
   Yogi 3/6/24: added abstract methods based on mvc framework
   Caden 3/8/24: updated methods to get stoplight tests working
*/

public interface AppFactory {

    public Model makeModel();
    public View makeView(Model m);
    public String getTitle();
    public String[] getHelp();
    public String about();
    public Command makeEditCommand(Model model, String type, Object source);
    public String[] getEditCommands();
}
