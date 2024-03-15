package CALab;

/*
Edits:
   Caden 3/14/24: Created class
*/

import mvc.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CellView extends JButton implements Subscriber, ActionListener {
    protected Cell myCell;

    public CellView(Cell myCell)
    {
        this.myCell = myCell;
    }

    public void update()
    {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        myCell.nextState();
        setBackground(myCell.getColor());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setText("" + myCell.getStatus());
    }
}
