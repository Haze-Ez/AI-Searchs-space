package eze.example.Impl;

import java.util.Stack;

import eze.example.State;
import eze.example.Abstract.AbstractSolver;

public class Depth_First_Search extends AbstractSolver{

    private Stack<State> stack = new Stack<>();
    @Override
    protected void addVisitedState(State state) {
        stack.push(state);
    }

    @Override
    protected boolean hasElements() {
        return !stack.isEmpty();
    }

    @Override
    protected State getNextState() {
        return stack.pop();
    }

    @Override
    protected void clearFrontier() {
        stack.clear();
    }

    @Override
    protected void addState(State state) {
        if(!stack.contains(state))
        stack.push(state);
    }

}
