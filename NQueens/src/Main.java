import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static int[] conflicts;
    public static int[] board = {3, 0, 3, 1};
    public static Random random = new Random();

    public static void main(String[] args) {
        findSolution();
    }

//    public int[][] generateBoard(int n) {
//
//    }
    public static void printBoard() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if(board[i] == j) {
                    System.out.print("* ");
                }
                else{
                    System.out.print("_");
                }
            }
            System.out.println();
        }
    }
    public static void findSolution() {
        calculateConflicts();

        for(int i = 0; i < board.length * 3; i++){
          int maxConflictsColumn = findColumnWithMaxConflicts();

          board[maxConflictsColumn] = findBestRow(maxConflictsColumn);

          calculateConflicts();

          if(!hasConflicts()){
              break;
          }
        }

        if(hasConflicts()) {
            findSolution();
        }
    }

    public static void calculateConflicts() {
        conflicts = new int[board.length];

        for(int i = 0; i < board.length; i++) {
            for(int j = i + 1; j < board.length; j++) {
                // check if conflict is in the same row
                if(board[i] == board[j]) {
                    conflicts[i]++;
                    conflicts[j]++;
                }
                // check if conflict is in diagonal
                // |x1-x2| == |y1-y2|
                if(Math.abs(board[i]-board[j]) == Math.abs(i - j)){
                    conflicts[i]++;
                    conflicts[j]++;
                }
            }
        }

    }

    public static int findColumnWithMaxConflicts() {
        int maxConfColumn = 0;
        int tempConflicts = conflicts[0];
        for(int i = 0; i < conflicts.length - 1; i++) {
           if(tempConflicts < conflicts[i + 1]){
               tempConflicts = conflicts[i + 1];
               maxConfColumn = i + 1;
           }
           if(tempConflicts == conflicts[i+1]){
               maxConfColumn = random.nextBoolean() ? maxConfColumn: i + 1;
           }
        }
        return maxConfColumn;
    }

    public static int findBestRow(int column) {
        int previousConflicts = 0;
        int tempBestRow = 0;
        for(int i = 0; i < board.length - 1; i++) {
            int currentConflicts = 0;
            if(board[column] + i == board[i + 1]) {
                previousConflicts++;
                currentConflicts++;
            }
            if(Math.abs(board[column] + i - board[i]) == Math.abs(i - column)){
                previousConflicts++;
                currentConflicts++;
            }
            if(previousConflicts > currentConflicts) {
                previousConflicts = currentConflicts;
                tempBestRow = i;
            }
        }

        return tempBestRow;
    }

    public static boolean hasConflicts() {
        for(int i = 0; i < conflicts.length; i++) {
            if(conflicts[i] > 0) {
                return true;
            }
        }
        return false;
    }
}
