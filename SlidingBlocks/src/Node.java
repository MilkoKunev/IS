import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    private int map[][];
    private int h = 0;
    private int g = 0;
    private Node parent;
    private Tile blankTile;
    private static HashMap<Integer, Tile> solutionMap;

    public Node(int[][]map, Tile blankTile, int g, Node parent) {
        this.map = map;
        this.blankTile = blankTile;
        this.g = g;
        this.parent = parent;
    }

    public Node(int[][] map, Tile blankTile, HashMap<Integer, Tile> solutionMap) {
        this.map = map;
        this.blankTile = blankTile;
        Node.solutionMap = solutionMap;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node previous) {
        this.parent = previous;
    }

    public Tile getBlankTile() {
        return blankTile;
    }

    public void setBlankTile(Tile blankTile) {
        this.blankTile = blankTile;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getFunctionSum() {
        return h + g;
    }

    public void calculateHeuristic() {
        for(int row = 0; row < map.length; row++) {
            for(int column = 0; column < map.length; column++) {
                int number = map[row][column];
                int solutionMapRow = solutionMap.get(number).getRow();
                int solutionMapColumn = solutionMap.get(number).getColumn();
                if(number == 0) {
                    continue;
                }
                if(row != solutionMapRow || column != solutionMapColumn) {
                    this.h+= calculateMannhatanDistance(row, column, solutionMapRow, solutionMapColumn);
                }
            }
        }
    }

    public int calculateMannhatanDistance(int row1, int column1, int row2, int column2) {
        return Math.abs(row1 - row2) + Math.abs(column1 - column2);
    }

    //Get childs of current node
    public ArrayList<Node> nextStates() {
        int currentRow = blankTile.getRow();
        int currentColumn = blankTile.getColumn();
        ArrayList<Node> arr = new ArrayList<>();

        if (blankTile.canMove(currentRow + 1, currentColumn)) {
            populateArr(currentRow + 1, currentColumn, arr);
        }
        if (blankTile.canMove(currentRow , currentColumn + 1)) {
            populateArr(currentRow , currentColumn + 1, arr);
        }
        if (blankTile.canMove(currentRow - 1, currentColumn )) {
            populateArr(currentRow - 1, currentColumn, arr);
        }
        if (blankTile.canMove(currentRow , currentColumn - 1)) {
            populateArr(currentRow , currentColumn - 1, arr);
        }

        return arr;
    }

    public void populateArr(int row, int column, ArrayList<Node> arr) {
        int[][] newMap = this.moveBlankTile(row, column);
        Node newNode = new Node(newMap, new Tile(row, column), this.g + 1, this);
        newNode.calculateHeuristic();
        arr.add(newNode);
    }

    public int[][] moveBlankTile(int row, int column) {
        int temp = map[row][column];
        map[row][column] = 0;
        map[blankTile.getRow()][blankTile.getColumn()] = temp;

        return map;
    }
}
