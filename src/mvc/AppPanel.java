package mvc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
Edits:
   Ronald 3/4/24: created file
   Yogi 3/6/24: created constructor, inner ControlPanel class and import statements
   Yogi 3/8/24 quick fix: implemented subscriber
   Caden 3/8/24: updated variable names and altered methods to get stoplight test working
   Ronald 3/8/24: slightly modified control panel
   Ronald 3/11/24: modified actionPerformed method to handle different use cases, added setModel method
*/



public class AppPanel extends JPanel implements ActionListener, Subscriber{
    protected ControlPanel controlPanel;
    protected Model model;
    protected View view;
    protected AppFactory factory;

    public AppPanel(AppFactory factory){
        this.factory = factory;
        this.model = factory.makeModel();
        this.view = factory.makeView(model);
        this.controlPanel = new ControlPanel();
    }
    public void setModel(Model model){

        this.model = model;
        view.setModel(model);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent action)
    {
        try
        {
            String cmd = action.getActionCommand();

            if (cmd.equals("Save")){
                Utilities.save(model, false);
            }
            else if (cmd.equals("SaveAs")){
                Utilities.save(model, true);
            }
            else if (cmd.equals("Open")){
                Model newModel = Utilities.open(model);
                if(newModel != null){
                    setModel(newModel);
                }
            }
            else if (cmd.equals("New")){
                Utilities.saveChanges(model);
                setModel(factory.makeModel());
                model.setUnsavedChanges(false);
            }
            else if(cmd.equals("Quit")){
                Utilities.saveChanges(model);
                System.exit(0);
            }
            else if (cmd.equals("About")){
                Utilities.inform(factory.about());
            }
            else if (cmd.equals("Help")){
                Utilities.inform(factory.getHelp());
            }
            else{
                factory.makeEditCommand(model, cmd, this).execute();
            }
            update();
        }

        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void update()
    {
        repaint();
    }

    public void display()
    {
        this.setLayout((new GridLayout(1, 2)));
        this.add(controlPanel);
        this.add(view);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("Cellular Automata");
        frame.setSize(700, 500);
        frame.setVisible(true);
    }
    public class ControlPanel extends JPanel{
        public ControlPanel(){
            setBackground(Color.PINK);
            JPanel p = new JPanel();
            add(p);
        }
    }

}
