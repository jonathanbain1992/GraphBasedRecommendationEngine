package com.company;

import java.util.ArrayList;

public class Vertex {

    private ArrayList<Edge> neighbourhood;

    public Vertex(){
        this.neighbourhood = new ArrayList<Edge>();
    }

    //if we want to give it some neighbours by default for whatever reason.
    public Vertex(ArrayList<Edge> neighbourhood){
        this.neighbourhood = neighbourhood;
    }

    public long getHashCode(){
        return this.hashCode();
    }

    //edge contains vertex object which is identified by its hashcode.

    public boolean containsNeighbour(Vertex v){
        for(Edge e : neighbourhood){
            if(e.getNeighbour().getHashCode() == v.getHashCode()) {
                return true;
            }
        }
        return false;
    }

    // only call addNeighbour if NOT contains Neighbour

    public boolean addNeighbour(Vertex v){
        neighbourhood.add(new Edge(v));
        return true;
    }

    public boolean removeVertex(Vertex v){
        return neighbourhood.remove(v);
    }

    public boolean clearNeighbourhood(){
        neighbourhood.clear();
        return true;
    }

    public int getNeighbourhoodSize(){
        return neighbourhood.size();
    }
}
