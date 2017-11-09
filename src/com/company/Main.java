package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // create a graph, take user input for number of nodes to make, populate graph.
        System.out.println("Please enter a number of nodes to create in the graph.");
        Scanner sc = new Scanner(System.in);
        Graph g = new Graph();
        g.populateGraph(sc.nextInt());
        for (Vertex v : g.getNeighbourhood()) {
            for (Vertex v2 : g.getNeighbourhood()) {
                g.setNeighbourRelations(0.3, v, v2);
            }
            for (Vertex vertex : g.getNeighbourhood()) {
                System.out.println(vertex.getHashCode());
                System.out.println(vertex.getNeighbourhoodSize());
            }
        }
    }

}
