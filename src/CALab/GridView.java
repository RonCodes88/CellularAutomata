package CALab;

import mvc.*;

import java.awt.*;


public class GridView extends View {

    private CellView cellViews[][];

    public GridView(Model model) {
        super(model);
        cellViews = new CellView[((Grid)model).getDim()][((Grid)model).getDim()];
        this.setLayout(new GridLayout(((Grid)model).getDim(), ((Grid)model).getDim()));
        for (int i = 0; i < cellViews.length; i++){
            for (int j = 0; j < cellViews[0].length; j++){
                cellViews[i][j] = new CellView(((Grid)model).getCell(i,j));
                ((Grid)model).getCell(i,j).row = i;
                ((Grid)model).getCell(i,j).col = j;
                this.add(cellViews[i][j]);
            }
        }
    }

    public void update() {
        // call update method of each CellView
        for (int row = 0; row < cellViews.length; row++){
            for (int col = 0; col < cellViews[row].length; col++){
                cellViews[row][col].update();
            }
        }
    }

}
