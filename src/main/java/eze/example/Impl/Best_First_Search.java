package eze.example.Impl;

import java.util.Comparator;
import java.util.PriorityQueue;

import eze.example.Abstract.AbstractSolver;
import eze.example.State;

public class Best_First_Search extends AbstractSolver {
    private PriorityQueue<State> queue = null;

    public Best_First_Search() {
        queue = new PriorityQueue<State>(1, new Comparator<State>() {
            @Override
            public int compare(State s1, State s2) {
                return Double.compare(
                        s1.getDistance() + s1.getHeuristic(),
                        s2.getDistance() + s2.getHeuristic());
            }
        });
    }

    @Override
    protected void addVisitedState(State state) {
        queue.add(state);
    }

    @Override
    protected boolean hasElements() {
        return !queue.isEmpty();
    }

    @Override
    protected State getNextState() {
        return queue.poll();
    }

    @Override
    protected void clearFrontier() {
        queue.clear();
    }

    @Override
    protected void addState(State state) {
        if (!queue.contains(state))
            queue.add(state);
    }

}
