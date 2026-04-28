package eze.example;

public class Node {

    public State state;
    Node parent = null;
    int exploredChildren = 0;

    public Node(State state) {
        this.state = state;
    }

    public Node(State state, Node parent){
        this.state = state;
        this.parent = parent;
    }
}
