package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {




    public static void main(String[] args) {
        // create a graph, take user input for number of nodes to make, populate graph.
        System.out.println("Please enter a number of nodes to create in the graph.");
        Scanner sc = new Scanner(System.in);

        Graph g = new Graph();
        g.populateGraph(sc.nextInt());
        g.setNeighbourhood(0.3);

        g.printMatrix();
        //just so we can see the array
       System.out.println(Arrays.toString(g.generateVector()));
    }

}
