package org.example;

import org.example.Graph.Node;


public class Main {


    public static void main(String[] args) {
        Node<Character> A = new Node<>('A');
        Node<Character> B = new Node<>('B');
        Node<Character> C = new Node<>('C');
        Node<Character> D = new Node<>('D');
        Node<Character> E = new Node<>('E');
        Node<Character> F = new Node<>('F');
        A.connect(B);
        A.connect(C);
        B.connect(D);
        D.connect(F);
        C.connect(E);
        D.searchBfs(D.getValue(),A).forEach(node -> System.out.println(node.getValue()));
//        Node.searchDfs(F.getValue(),A).forEach(node -> System.out.println(node.getValue()));
//        Node.traversalDfs(A).forEach(node -> System.out.println(node.getValue()));


    }
}