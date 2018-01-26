public class Tile {
    private int column;
    private int row;

    public Tile(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean canMove(int lastColumn, int lastRow) {
        if(this.row < 0 || this.column < 0) {
            return false;
        }
        if(this.row > lastRow || this.column > lastColumn) {
            return false;
        }

        return true;
    }

}
