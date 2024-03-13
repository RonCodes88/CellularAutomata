package mvc;

/*
Edits:
   Ronald 3/4/24: created file
   Ronald 3/5/24: modified file
   Ronald 3/13/24: updated Model to extend Publisher
*/


import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {
    private boolean unsavedChanges = false;
    private String fileName = null;


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


    public void changed(){
        setUnsavedChanges(true);
        notifySubscribers();
    }


}
