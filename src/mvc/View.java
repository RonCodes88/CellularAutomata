package mvc;

/*
Edits:
   Ronald 3/4/24: created file
   Ronald 3/5/24: modified file
   Ronald 3/13/24: updated View to only implement Subscriber
*/

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class View extends JPanel implements Subscriber {
    protected Model model;

    public View(Model model){
        this.model = model;
        model.subscribe(this);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        setBorder(blackLine);
        setBackground(Color.LIGHT_GRAY);
    }

    public void setModel(Model newModel){
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        repaint();
    }

    public void update(){
        repaint();
    }


}
