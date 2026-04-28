package eze.example.Abstract;

import java.util.List;
import java.util.Set;

import eze.example.Solver;
import eze.example.State;

import java.util.HashSet;
import java.util.LinkedList;

public abstract class AbstractSolver implements Solver {
    private Set<State> visitedStates = new HashSet<State>();

    public List<State> solve(State initialState) {
        clearFrontier();
        visitedStates.clear();

        addState(initialState);
        while (hasElements()) {
            State demo = getNextState();
            if (demo.isSolution()) {
                return retracePath(demo);
            }

            visitedStates.add(demo);
            for (State s : demo.getPossibleMoves()) {
                if (!visitedStates.contains(s)) {
                    addState(s);
                }
            }
        }
        return null;
    }

    private List<State> retracePath(State goalState) {
        State current = goalState;
        if (current == null) {
            return new LinkedList<State>();
        }

        List<State> path = retracePath(current.getParent());
        path.add(current);
        return path;
    }

    protected abstract void addVisitedState(State state);

    protected abstract boolean hasElements();

    protected abstract State getNextState();

    protected abstract void clearFrontier();

    protected abstract void addState(State state);

}
