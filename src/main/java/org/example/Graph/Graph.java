package org.example.Graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.IntStream;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Graph {
    private int [][] edges ;
    private int vertices ;

    public static int findMinDistance(int [] distances, boolean [] visitedVertex){
//        return IntStream.range(0,distances.length).filter(i -> !visitedVertex[i]).min().getAsInt();
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0 ; i < distances.length ; i++)
                if(!visitedVertex[i] && distances[i] < minDistance){
                    minDistance = distances[i];
                    minIndex = i;
                }
        return minIndex;
    }


    public static void dijkstraAlgorithm(int beginVertex, Graph graph){
        int [] distances = new int[graph.getVertices()];
        boolean [] visitedVertices = new boolean [graph.getVertices()];
        IntStream.range(0, graph.getVertices()).forEach(i -> distances[i] = Integer.MAX_VALUE);
        IntStream.range(0, graph.getVertices()).forEach(i -> visitedVertices[i] = false);
        distances[beginVertex] = 0;
        for (int i = 0 ; i < graph.getVertices() ; i++ ){
            int u = Graph.findMinDistance(distances,visitedVertices);
            visitedVertices[u] = true;
            for( int v = 0 ; v < graph.getVertices() ; v ++){
                if(!visitedVertices[v] && graph.getEdges()[u][v] != 0 &&
                        (distances[u] + graph.getEdges()[u][v] < distances[v]) && distances[u] != Integer.MAX_VALUE)
                    distances[v] = distances[u] + graph.getEdges()[u][v];
            }
        }
        IntStream.range(0, graph.getVertices()).forEach(v -> System.out.println(String.format("Distance from %s to %s is %s", beginVertex, v, distances[v])));



    }
    public static void main (String [] args){
//        int [] distances = {0,2,8,9,4,5};
//        boolean [] visitedVertices = {true,true,false,false,false,false};
//        System.out.println(Graph.findMinDistance(distances,visitedVertices));
//        int [][] edges = new int[][] {
//                { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
//                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
//                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
//                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
//                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
//                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
//                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
//                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
//                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        int[][] edges2 = new int[][] { { 0, 0, 1, 2, 0, 0, 0 }, { 0, 0, 2, 0, 0, 3, 0 }, { 1, 2, 0, 1, 3, 0, 0 },
                { 2, 0, 1, 0, 0, 0, 1 }, { 0, 0, 3, 0, 0, 2, 0 }, { 0, 3, 0, 0, 2, 0, 1 }, { 0, 0, 0, 1, 0, 1, 0 } };
        Graph graph = Graph.builder().vertices(edges2.length).edges(edges2).build();
        Graph.dijkstraAlgorithm(0,graph);

    }
}
