package mvc;

/*
Edits:
   Ronald 3/4/24: created file
   Ronald 3/5/24: modified file
*/

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JPanel implements Subscriber, PropertyChangeListener {
    protected Model model;

    public View(Model model){
        this.model = model;
        this.model.addPropertyChangeListener(this);
        model.subscribe(this);
    }

    public void setModel(Model newModel){
        if (model != null){
            model.removePropertyChangeListener(this);
        }
        this.model = newModel;
        if (model != null){
            model.addPropertyChangeListener(this);
        }
    }
    public void update(){
        repaint();
    }

    public void propertyChange(PropertyChangeEvent evt){
        if ("unsavedChanges".equals(evt.getPropertyName()))
            System.out.println("Unsaved Changes status: " + evt.getNewValue());
    }








}
