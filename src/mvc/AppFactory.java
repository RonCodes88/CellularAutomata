package mvc;

/*
Edits:
   Ronald 3/4/24: created file
   Yogi 3/5/24: changed from class to interface
   Yogi 3/6/24: added abstract methods based on mvc framework

*/

public interface AppFactory {

    public Model makeModel();
    public View makeView();
    public String getTitle();
    public String getHelp();
    public String about();
    public String getEditCommands();
    public Command makeEditCommands(String name);
}
