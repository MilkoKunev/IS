import java.util.*;

public class JumpingFrogs {

    private static LinkedList<char[]> stepsToSolution = new LinkedList<char[]>();
    private static char[] solutionBoard;

    public static void main(String[] args){
        int n = 22;
        char[] board = generateBoard(n, '>', '<');
        solutionBoard = generateBoard(n, '<', '>');
        stepsToSolution.addLast(board);
        dfs(0, board);

        for(char[] arr : stepsToSolution){
            printSteps(arr);
        }
     }

     public static void printSteps(char[] stepsToSolution)  {
         System.out.println(new String(stepsToSolution));
     }

     public static char[] generateBoard(int n, char first, char second) {
        char[] board = new char[n * 2 + 1];

        for(int i = 0; i < n; i++) {
            board[i] = first;
        }
        board[n] = '_';

        for(int i = n + 1; i < board.length; i++) {
            board[i] = second;
        }

        return board;
     }


    public static boolean dfs(int currentPosition, char[] board) {
        if(Arrays.equals(board, solutionBoard)){
            return true;
        }

        while(currentPosition < board.length) {
            char[] newBoard;

            if(canLeftFrogStepOrJump(1, board, currentPosition)) {
                newBoard = makeMoveLeftFrog(1, board, currentPosition);
                if(dfs(0, newBoard)) {
                    return true;
                }
                else {
                    stepsToSolution.removeLast();
                    board = stepsToSolution.getLast();
                }
            }

            if(canLeftFrogStepOrJump(2, board, currentPosition)) {
                newBoard = makeMoveLeftFrog(2, board, currentPosition);
                if(dfs(0, newBoard)) {
                    return true;
                }
                else {
                    stepsToSolution.removeLast();
                    board = stepsToSolution.getLast();
                }
            }

            if(canRightFrogStepOrJump(1, board, currentPosition)) {
                newBoard = makeMoveRightFrog(1, board, currentPosition);
                if(dfs(0, newBoard)) {
                    return true;
                }
                else {
                    stepsToSolution.removeLast();
                    board = stepsToSolution.getLast();
                }
            }

            if(canRightFrogStepOrJump(2, board, currentPosition)) {
                newBoard = makeMoveRightFrog(2, board, currentPosition);
                if(dfs(0, newBoard)) {
                    return true;
                }
                else {
                    stepsToSolution.removeLast();
                    board = stepsToSolution.getLast();
                }
            }

            currentPosition++;
        }

        return false;
    }

    public static char[] makeMoveRightFrog(int steps, char[] currentBoard, int position) {
        char[] newBoard;
        newBoard = Arrays.copyOfRange(currentBoard, 0, currentBoard.length);
        newBoard[position] = '_';
        newBoard[position - steps] = '<';
        stepsToSolution.addLast(newBoard);
        return newBoard;
    }

    public static char[] makeMoveLeftFrog(int steps, char[] currentBoard, int position) {
        char[] newBoard;
        newBoard = Arrays.copyOfRange(currentBoard, 0, currentBoard.length);
        newBoard[position] = '_';
        newBoard[position + steps] = '>';
        stepsToSolution.addLast(newBoard);
        return newBoard;
    }

    public static boolean canLeftFrogStepOrJump(int step, char[] currentBoard, int position){
        return  currentBoard[position] == '>' &&
                position + step <= currentBoard.length - 1 &&
                currentBoard[position + step] == '_';
    }

    public static boolean canRightFrogStepOrJump(int step, char[] currentBoard, int position) {
        return  currentBoard[position] == '<' &&
                position - step >= 0 &&
                currentBoard[position - step] == '_';
    }
}
