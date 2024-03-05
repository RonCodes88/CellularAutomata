package mvc;

/*
Edits:
   Ronald 3/4/24: created file
   Caden 3/5/24: implemented class for mvc framework
*/

public abstract class Command {
    public Model model;
    public Command(Model model)
    {
        this.model = model;
    }
    public abstract void execute();
}
