package CALab;

import mvc.*;


public class GridView extends View {

    private CellView cellViews[][];

    public GridView(Grid grid) {
        super(grid);
        cellViews = new CellView[grid.getDim()][grid.getDim()];
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
