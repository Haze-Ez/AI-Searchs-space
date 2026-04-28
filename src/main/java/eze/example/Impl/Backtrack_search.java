package eze.example.Impl;

import java.util.ArrayList;
import java.util.List;

import eze.example.Node;
import eze.example.Solver;
import eze.example.State;

public class Backtrack_search implements Solver{

    @Override
    public List<State> solve(State initialState) {
      ArrayList<State> children = new ArrayList<>();
      Node node = new Node(initialState);
      while(true){
        if(node.state == null) break;
        if(node.state.isSolution()) break;

        
        
      }
      
      
      
      
      
      return children;
      
      
    }

}
