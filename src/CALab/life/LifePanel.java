package CALab.life;

import CALab.GridPanel;
import mvc.*;

public class LifePanel extends GridPanel {
    public LifePanel(AppFactory factory){
        super(factory);
    }

    public static void main(String[] args) {
        AppFactory factory = new SocietyFactory();
        AppPanel panel = new LifePanel(factory);
        panel.display();
    }
}
