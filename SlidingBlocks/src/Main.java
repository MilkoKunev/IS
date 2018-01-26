import java.util.*;

public class Main {

//    public static int[][] solution = {{ 1, 2, 3 },
//                                      { 4, 5, 6 },
//                                      { 7, 8, 0 }};



    public static void main(String[] args){
        HashMap<Integer, Tile> solutionMap = buildSolution();
//        for(Map.Entry<Integer, Tile> entry: solutionMap.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue().getRow() + " : " + entry.getValue().getColumn());
//        }
        int[][] map = {{ 1, 2, 3 },
                        { 0, 4, 6 },
                        { 7, 5, 8 }};
        Tile blankTile = new Tile(1, 0);
        Node start = new Node(map, blankTile, solutionMap);

        System.out.println(start.calculateHeuristic());

     }

//     public static void aStar() {
//        int cost = 0;
//        while(){
//            if(h == 0) {
//                break;
//            }
//        }
//
//     }

     public static HashMap<Integer, Tile> buildSolution() {
         HashMap<Integer, Tile> solutionMap = new HashMap<>();
         int number = 1;
         for(int row = 0; row < 3; row++){
             for(int column = 0; column < 3; column++) {
                 if(row == 2 && column == 2) {
                     solutionMap.put(0, new Tile(row, column));
                 }
                 else{
                     solutionMap.put(number, new Tile(row, column));
                 }
                 number++;
             }
         }
         return solutionMap;
     }

}
