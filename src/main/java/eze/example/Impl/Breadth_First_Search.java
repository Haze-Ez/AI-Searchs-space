package eze.example.Impl;

import java.util.LinkedList;
import java.util.Queue;

import eze.example.State;
import eze.example.Abstract.AbstractSolver;

public class Breadth_First_Search extends AbstractSolver{
    private Queue<State> queue = new LinkedList<>();

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
        if(!queue.contains(state))
        queue.add(state);
    }

}
