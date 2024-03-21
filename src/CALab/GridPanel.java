package CALab;

import mvc.AppFactory;
import mvc.AppPanel;
import java.awt.*;

import javax.swing.*;

public class GridPanel extends AppPanel {
    private JButton run1;
    private JButton run50;
    private JButton populate;
    private JButton clear;

    public GridPanel(AppFactory factory) {
        super(factory);
        run1 = new JButton("RUN1");
        run50 = new JButton("RUN50");
        populate = new JButton("POPULATE");
        clear = new JButton("CLEAR");
        run1.addActionListener(this);
        run50.addActionListener(this);
        populate.addActionListener(this);
        clear.addActionListener(this);
        controlPanel.add(run1);
        controlPanel.add(run50);
        controlPanel.add(populate);
        controlPanel.add(clear);
    }

}
