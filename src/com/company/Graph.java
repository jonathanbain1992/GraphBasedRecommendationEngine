package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Graph {

    private ArrayList<Vertex> globalNeighbourhood;

    public Graph(){
        this.globalNeighbourhood = new ArrayList<Vertex>();
    }

    public boolean addVertex(Vertex v){
        return globalNeighbourhood.add(v);
    }

    public boolean removeVertex(Vertex v){
        return globalNeighbourhood.remove(v);
    }

    public boolean clearNeighbourhood(){
        globalNeighbourhood.clear();
        return true;
    }

    public int getNeighbourhoodSize(){
        return globalNeighbourhood.size();
    }

    public ArrayList<Vertex> getNeighbourhood(){
        return this.globalNeighbourhood;
    }

    public boolean populateGraph(int size){
        for(int i=0; i<size; i++){
            globalNeighbourhood.add(new Vertex());
        }
        return true;
    }
    public boolean setNeighbourRelations(double probability, Vertex a, Vertex b){
        if(!a.equals(b)) {
            if (Math.random() < probability) {
                if (!(a.containsNeighbour(b) || b.containsNeighbour(a))) {
                    //passes to vertex method which itself returns a boolean.
                    return a.addNeighbour(b) && b.addNeighbour(a);
                }
                return false;
            }
            return false;
        }
        return false;
    }


    public int[][] generateMatrix(){
        int[][] returnArr = new int[(int)Math.sqrt(globalNeighbourhood.size())][(int)Math.sqrt(globalNeighbourhood.size())];


            }


}
