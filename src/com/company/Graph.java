package com.company;


import java.util.ArrayList;

public class Graph {

    private ArrayList<Vertex> globalNeighbourhood;

    public Graph() {
        this.globalNeighbourhood = new ArrayList<Vertex>();
    }

    public boolean addVertex(Vertex v) {
        return globalNeighbourhood.add(v);
    }

    public boolean removeVertex(Vertex v) {
        return globalNeighbourhood.remove(v);
    }

    public boolean clearNeighbourhood() {
        globalNeighbourhood.clear();
        return true;
    }

    public int getNeighbourhoodSize() {
        return globalNeighbourhood.size();
    }

    public ArrayList<Vertex> getNeighbourhood() {
        return this.globalNeighbourhood;
    }

    public boolean populateGraph(int size) {
        long time = System.nanoTime();
        for (int i = 0; i < size; i++) {
            globalNeighbourhood.add(new Vertex());
        }
        System.out.println("Graph Population Cost"+(System.nanoTime()-time));
        return true;
    }
    public boolean setNeighbourhood(double probability) {
        long time = System.nanoTime();
        for (Vertex v : globalNeighbourhood) {
            for (Vertex v2 : globalNeighbourhood) {
                setNeighbourRelations(probability, v, v2);
            }
        }
        System.out.println("Generating Neighbourhood Of Entire Graph Cost: "+ (System.nanoTime()-time));
        return true;
    }

    public boolean setNeighbourRelations(double probability, Vertex a, Vertex b) {
        if (!a.equals(b)) {
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

    public int[][] generateMatrix() {
        long time = System.nanoTime();
        int[][] returnArr = new int[(int) Math.pow(globalNeighbourhood.size(), 2)][(int) Math.pow(globalNeighbourhood.size(), 2)];

        int i = 0;
        for (Vertex v1 : globalNeighbourhood) {
            for (Vertex v2 : globalNeighbourhood) {
                if (v1.containsNeighbour(v2)) {
                    returnArr[i / globalNeighbourhood.size()][i % globalNeighbourhood.size()] = 1;
                }
                i++;
            }
        }

        System.out.println("Generating Matrix Version Cost: "+(System.nanoTime()-time));

        return returnArr;

    }

    public int calculateDiagonal(int index, int size){
        int i = index/size;
        int j = index%size;
        return calculateIndex((size-j), (size-i), size);
    }

    public int calculateIndex(int i, int j, int length){
        return length*(i-1)+j-1;
    }

    //For every vertex, go through it's edge list and make the vector[m (or n) * vertex id + the neighbour's id.
    //By simply storing an incremental ID we can cut down a lot of computations as shown in the time performance VS matrix rendering.

    public int[] generateVector() {

        long time = System.nanoTime();


        int[] returnArr = new int[(int)Math.pow(globalNeighbourhood.size(),2)];
        System.out.println("Array Length" +returnArr.length);
        for(Vertex v : globalNeighbourhood) {

            for (Edge e : v.neighbourhood) {


                returnArr[(v.id*globalNeighbourhood.size())+e.getNeighbour().id]=1;
            }
        }
        System.out.println("Time Taken To Generate Vector:"+(System.nanoTime()-time));
        return returnArr;
    }

    public int[][] transpose(int[][] matrix){
        long time = System.nanoTime();
        int dimension = globalNeighbourhood.size();
        int[][] flipped = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                flipped[i][j] = matrix[j][i];
            }
        }
       // System.out.println("Matrix Transposing Cost: "+(System.nanoTime()-time));
        return flipped;
    }

    //verification that the output is consistently formatted when rendered as matrix


    public boolean isMatrixDiagonal(int[][] matrix) throws Exception {

        long time = System.nanoTime();

        for (int i = 0; i < globalNeighbourhood.size(); i++) {
            for (int j = 0; j < globalNeighbourhood.size(); j++) {

                if (matrix[i][j] != transpose(matrix)[i][j]) {
                    throw new Exception("Matrix was invalid when tested for its diagonal");
                }
            }
        }

        System.out.println("Checking Matrix Symmetric Cost: "+(System.nanoTime()-time));

        return true;

    }


    public void printMatrix() {

        try {
            long time = System.nanoTime();
            int[][] matrix = generateMatrix();
            for (int i = 0; i < Math.sqrt(matrix.length); i++) {
                for (int j = 0; j < Math.sqrt(matrix.length); j++) {
                    if (i == 0 && j == 0 || i == Math.sqrt(matrix.length) - 1 && j == 0) {
                        System.out.print("[ ");
                    } else if (i != 0 && j == 0) {
                        System.out.print("| ");
                    }

                    System.out.print(matrix[i][j]);

                    if (i == 0 && j == Math.sqrt(matrix.length) - 1 || i == Math.sqrt(matrix.length) - 1 && j == Math.sqrt(matrix.length) - 1) {
                        System.out.print(" ]");
                    } else if (i != 0 && j == Math.sqrt(matrix.length) - 1) {
                        System.out.print(" |");
                    }
                }
                System.out.print("\n");
            }
            System.out.println("Printing Matrix To System Out Cost: "+(System.nanoTime()-time));
            isMatrixDiagonal(matrix);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Stack traceback:");
            System.out.println(e.getStackTrace());
        }
    }

    //




}

