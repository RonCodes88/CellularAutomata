package mvc;

/*
Edits:
   Ronald 3/4/24: created file
   Ronald 3/5/24: modified file
*/

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public abstract class
Model extends Publisher implements Serializable, PropertyChangeListener {
    private boolean unsavedChanges = false;
    private String fileName = null;
    private PropertyChangeSupport support;

    public void setUnsavedChanges(boolean unsavedChanges) {
        this.unsavedChanges = unsavedChanges;
    }
    public boolean getUnsavedChanges() {
        return unsavedChanges;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public String getFileName(){
        return fileName;
    }
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        support.removePropertyChangeListener(listener);
    }

    public void changed(){
        boolean oldValue = getUnsavedChanges();
        setUnsavedChanges(true);
        support = new PropertyChangeSupport(this);
        support.firePropertyChange("unsavedChanges", oldValue, getUnsavedChanges());
    }


}
