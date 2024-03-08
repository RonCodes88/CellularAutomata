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
*/



public class AppPanel extends JPanel implements ActionEventListener, Subscriber{
    private ControlPanel controls;
    private Model model;
    private View view;

    public AppPanel(){
        model = new Model();
        view = new View();
        controls = new ControlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controls);
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

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"RUN1", "RUN50", "POPULATE", "CLEAR"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }
    private class ControlPanel extends JPanel{

    }

}
