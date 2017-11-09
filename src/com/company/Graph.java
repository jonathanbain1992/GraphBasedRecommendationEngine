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
        int[][] returnArr = new int[(int)Math.pow(globalNeighbourhood.size(), 2)][(int)Math.pow(globalNeighbourhood.size(), 2)];

        int i = 0;
        for (Vertex v1: globalNeighbourhood){
            for (Vertex v2: globalNeighbourhood){
                if (v1.containsNeighbour(v2)){
                    returnArr[i/globalNeighbourhood.size()][i%globalNeighbourhood.size()] = 1;
                }
                i++;
            }
        }

        return returnArr;

    }

    public void printMatrix(){
        int[][] matrix = generateMatrix();
        for (int i= 0; i < Math.sqrt(matrix.length); i++){
            for (int j = 0; j < Math.sqrt(matrix.length); j++) {
                if (i==0 && j==0 || i==Math.sqrt(matrix.length)-1 && j==0){
                    System.out.print("[ ");
                } else if (i!=0 && j==0){
                    System.out.print("| ");
                }

                System.out.print(matrix[i][j]);

                if (i==0 && j==Math.sqrt(matrix.length)-1 || i== Math.sqrt(matrix.length)-1 &&j==Math.sqrt(matrix.length)-1){
                    System.out.print(" ]");
                } else if (i!=0 && j==Math.sqrt(matrix.length)-1){
                    System.out.print(" |");
                }
            }
            System.out.print("\n");
        }
        if (isMatrixDiagonal()){
            System.out.println("Matrix is diagonal.");
        }
    }

    public boolean isMatrixDiagonal(){
        int[][] matrix = generateMatrix();
        int[][] flipped = transpose(matrix);

        for (int i = 0; i < globalNeighbourhood.size(); i++){
            for (int j=0; j< globalNeighbourhood.size(); j++){
                if (matrix[i][j] != flipped[i][j]){
                    return false;
                }
            }
        }

        return true;

    }



    public int[][] transpose(int[][] matrix){
        int dimension = globalNeighbourhood.size();
        int[][] flipped = new int[dimension][dimension];

        for (int i=0; i < dimension; i++){
            for (int j=0; j< dimension; j++){
                flipped[i][j] = matrix[j][i];
            }
        }
        return flipped;
    }

}
