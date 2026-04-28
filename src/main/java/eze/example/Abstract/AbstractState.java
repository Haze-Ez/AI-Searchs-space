package eze.example.Abstract;

import eze.example.State;

public abstract class AbstractState implements State {
    private State parent = null;
    private double distance = 0;

    //Parent constructor
    public AbstractState() {}

    //Child constructor
    public AbstractState(State parent) {
        this.parent = parent;
        if(parent != null) {
            this.distance = parent.getDistance() + 1;
        }
        else {
            this.distance = 0;
        }
    }

    public State getParent() {
        return parent;
    }

    public double getDistance() {
        return distance;
    }

    public abstract Iterable<State> getPossibleMoves();

    public abstract boolean isSolution();

    public abstract double getHeuristic();

}
