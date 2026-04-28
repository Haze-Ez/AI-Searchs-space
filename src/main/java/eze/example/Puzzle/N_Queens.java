package eze.example.Puzzle;

import eze.example.Abstract.AbstractState;
import eze.example.State;

public class N_Queens extends AbstractState {
    int N = 8;
    int[] boardState = new int[N];

    public N_Queens() {
    }

    private int[] initialState = { -1, -1, -1, -1 };
    private int[] goalState = {};

    @Override
    public Iterable<State> getPossibleMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPossibleMoves'");
    }

    @Override
    public boolean isSolution() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isSolution'");
    }

    @Override
    public double getHeuristic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHeuristic'");
    }

}
