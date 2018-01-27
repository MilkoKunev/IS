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
        start.calculateHeuristic();

        aStar(start);
     }

     public static void aStar(Node start) {
        int cost = 0;

        HashSet<Node> visited = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(10, new Comparator<Node>() {
            @Override
            public int compare(Node i, Node j) {
                return i.getFunctionSum() - j.getFunctionSum();
            }
        });

        queue.add(start);

        while(!queue.isEmpty()){

            Node current = queue.poll();

            visited.add(current);

            if(current.getH() == 0) {
                break;
            }
            ArrayList<Node> neighbours = current.nextStates();
            for(Node neighbour: neighbours) {
                if(visited.contains(neighbour) && current.getFunctionSum() <= neighbour.getFunctionSum()) {
                    continue;
                }
                if(!visited.contains(neighbour) || current.getFunctionSum() >= neighbour.getFunctionSum()) {
                    if(queue.contains(neighbour)) {
                        queue.remove(neighbour);
                    }

                    queue.add(neighbour);
                }
            }

        }

     }

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
