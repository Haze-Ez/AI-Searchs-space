package eze.example.Puzzle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eze.example.Abstract.AbstractState;
import eze.example.Impl.Best_First_Search;
import eze.example.Solver;
import eze.example.State;

public class Eight_Puzzle extends AbstractState {
    private int[][] board = new int[3][3];
    private int blankRow;
    private int blankCol;
    private int blankIndex = board[blankRow][blankCol];
    private Action action;
    private static final int BLANK_SPACE = 0;

    public static final Action UP = Action.UP;
    public static final Action DOWN = Action.DOWN;
    public static final Action LEFT = Action.LEFT;
    public static final Action RIGHT = Action.RIGHT;

    int[][] initialState = { { 1, 8, 4 }, { 0, 2, 3 }, { 7, 6, 5 } };
    int[][] goalState = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };

    // Parent constructor
    public Eight_Puzzle() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = initialState[i][j];
            }
        }
        mapZero(initialState);
    }

    // Child constructor
    public Eight_Puzzle(Eight_Puzzle parent, int[][] board, Action action) {
        super(parent);
        this.board = board;
        this.action = action;
        mapZero(board);
    }

    @Override
    public Iterable<State> getPossibleMoves() {
        Set<State> moves = new HashSet<>();

        addIfValid(moves, UP); // UP
        addIfValid(moves, DOWN); // DOWN
        addIfValid(moves, LEFT); // LEFT
        addIfValid(moves, RIGHT); // RIGHT

        return moves;
    }

    public void addIfValid(Set<State> moves, Action action) {
        // copy board
        int[][] newBoard = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        // check if the move is valid
        if (blankRow + action.getDr() >= 0 && blankRow + action.getDr() < 3
                && blankCol + action.getDc() >= 0 && blankCol + action.getDc() < 3) {
            // move the adjacent tile to the blank spot
            blankIndex = newBoard[blankRow + action.getDr()][blankCol + action.getDc()];
            newBoard[blankRow + action.getDr()][blankCol + action.getDc()] = 0;
            moves.add(new Eight_Puzzle(this, newBoard, action));
        }
    }

    public void swap(int r1, int c1, int r2, int c2) {
        int temp = board[r1][c1];
        board[r1][c1] = board[r2][c2];
        board[r2][c2] = temp;
    }

    @Override
    public boolean isSolution() {
        return Arrays.deepEquals(board, goalState);
    }

    @Override
    public double getHeuristic() {
        int score = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = board[i][j];
                if (value != 0) {
                    int targetRow = (value) / 3;
                    int targetCol = (value) % 3;
                    // Distance between current and target position
                    score += Math.abs(i - targetRow) + Math.abs(j - targetCol);
                }
            }
        }
        return score;
    }

    public void mapZero(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break;
                }
            }
        }
    }

    public boolean equals(Object o) {
        if (o instanceof Eight_Puzzle) {
            Eight_Puzzle other = (Eight_Puzzle) o;
            return Arrays.deepEquals(board, other.board);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    public Action getAction() {
        return action;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (action != null) {
            sb.append("Action Taken: ").append(action).append("\n");
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]).append(" | ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Starting board:");
        Eight_Puzzle puzzle = new Eight_Puzzle();
        System.out.println(puzzle);

        System.out.println("Solving...");
        Solver solver = new Best_First_Search();
        List<State> solution = solver.solve(puzzle);

        if (solution != null) {
            System.out.println("=========================================");
            System.out.println("Solution found in " + (solution.size() - 1) + " steps:");
            for (State state : solution) {
                if (((Eight_Puzzle) state).getAction() != null) {
                    System.out.println("Moved: " + ((Eight_Puzzle) state).getAction());
                } else {
                    System.out.println("Initial State:");
                }
                System.out.println(state);
            }
        } else {
            System.out.println("No solution found.");
        }
    }

}
