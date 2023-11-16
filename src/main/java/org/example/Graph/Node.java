package org.example.Graph;


import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter

public class Node<T> {

    private T value ;
    private Set<Node<T>> neighbor;

    public Node (T value){
        this.value = value;
        this.neighbor = new HashSet<>();
    }

    public void connect(Node<T> node){
        if (this == node ) throw new IllegalArgumentException("Can't connect node to itself");
        this.neighbor.add(node);
    }
    public static <t> Set<Node<t>> searchBfs(t value, Node<t> start){
        Set<Node<t>> alreadyVisited = new LinkedHashSet<>();
        Queue<Node<t>> queue = new ArrayDeque<>();
        queue.add(start);
        Node<t> currentNode;
        while(!queue.isEmpty()){
            currentNode = queue.remove();
            if ( currentNode.getValue().equals(value)) {
                alreadyVisited.add(currentNode);
                return alreadyVisited;
            }
            else {
                alreadyVisited.add(currentNode);
                queue.addAll(currentNode.getNeighbor());
                queue.removeAll(alreadyVisited);
            }
        }
        return null;
    }

    public static <X> Set<Node<X>> searchDfs(X value, Node<X> start){
        Set<Node<X>> visitedVertex = new LinkedHashSet<>();
        Stack<Node<X>> stack = new Stack<>();
        stack.add(start);
        Node<X> currentNode;

        while( !stack.isEmpty() ) {
            currentNode = stack.pop();
            if (value.equals(currentNode.value)){
                visitedVertex.add(currentNode);
                return visitedVertex;
            }
            else {
                visitedVertex.add(currentNode);
                stack.addAll(currentNode.neighbor);
            }
        }
        return null;
    }
    public static <T> Set<Node<T>> traversalDfs(Node<T> start){
        Set<Node<T>> visitedVertex = new LinkedHashSet<>();
        Stack<Node<T>> stack = new Stack<>();
        stack.add(start);
        Node<T> currentNode;

        while(!stack.isEmpty()){
            currentNode = stack.pop();
            visitedVertex.add(currentNode);
            stack.addAll(currentNode.neighbor);
        }
        return visitedVertex;
    }
}
