package com.kenji47.AlgoruthmMalgrange.algorithms;

//import sun.plugin.javascript.navig.Array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andrey on 26.12.2014.
 */
public class MalgrangeAlgorithm {
    int[][] matrix;
    int vertex;
    int size_matrix;

    boolean[] straightTC;
    boolean[] reverseTC;
    boolean[] excludedVertex;

    int iteration=0;
    public ArrayList<StringBuilder> output;

    public MalgrangeAlgorithm(int[][] m, int v, int s){
        matrix=m;
        vertex=v;
        size_matrix=s;

        straightTC=new boolean[size_matrix+1];
        reverseTC=new boolean[size_matrix+1];
        excludedVertex=new boolean[size_matrix+1];
        for (int n=0; n<size_matrix+1; n++){
            straightTC[n]=false;
            reverseTC[n]=false;
            excludedVertex[n]=true;
        }
        //excludedVertex[v]=false;

        output=new ArrayList<StringBuilder>();

        solveAlgorithm(vertex,excludedVertex);
    }

    private void resetTransitiveClosure(){
        for (int n=0; n<size_matrix+1; n++){
            straightTC[n]=false;
            reverseTC[n]=false;
        }
    }
    void solveAlgorithm(int v, boolean[] excl){
        boolean check=false;
        for(int n=1; n<size_matrix+1; n++){
            if(excl[n]==true) check=true;
        }
        if(check) {
            iteration++;
            output.add(new StringBuilder("ITERATION: "+iteration));
            output.add(new StringBuilder(("Variable: "+"x"+v)));
            resetTransitiveClosure();
            fillStraightTransitiveClosure(v,excl);
            addOutput("STC", straightTC);

            fillReverseTransitiveClosure(v,excl);
            addOutput("RTC", reverseTC);

            boolean[] in = getIntersection(straightTC, reverseTC);
            addOutput("Intersection", in);

            boolean[] ex = getExcludedVertex(in, excl);
            addOutput("Excluded", ex);

            int tempInt = -1;
            for (int n = 1; n < size_matrix + 1; n++) {
                if (ex[n] == true) {
                    tempInt = n;
                    break;
                }
            }
            if (tempInt != -1)
                solveAlgorithm(tempInt, ex);
        }

    }
    private void addOutput(String msg,boolean[] b){
        StringBuilder string=new StringBuilder();
        string.append(msg+": ");

        for(int n=1; n<size_matrix+1; n++){
            if (b[n]==true) string.append("x"+n+" ");
        }
        output.add(string);
    }


    void fillStraightTransitiveClosure(int v, boolean[] ex){
        straightTC[v]=true;
        for (int n=0; n<size_matrix; n++){
            if(ex[v]==true && ex[n+1]==true) {
                if (matrix[v - 1][n] == 1 && straightTC[n + 1] == false) {
                    straightTC[n+1] = false;
                    fillStraightTransitiveClosure(n + 1,ex);
                }
            }

        }
    }

    void fillReverseTransitiveClosure(int v,boolean[] ex){
        reverseTC[v]=true;
        for (int n=0; n<size_matrix; n++) {
            if (ex[v] == true && ex[n + 1] == true) {
                if (matrix[n][v - 1] == 1 && reverseTC[n + 1] == false) {
                    reverseTC[n + 1] = false;
                    fillReverseTransitiveClosure(n + 1,ex);
                }

            }
        }

    }

     boolean[] getIntersection(boolean[] b1, boolean[] b2) {
         boolean[] intersection=new boolean[size_matrix+1];


         for (int n = 1; n < size_matrix + 1; n++) {
             if (b1[n] == true && b2[n] == true)
                 intersection[n] = true;
             else intersection[n] = false;
         }

        return intersection;
     }

    boolean[] getExcludedVertex(boolean[] intr,boolean[] excld){
        boolean[] excluded= Arrays.copyOf(excld,size_matrix+1);
        for (int n=1; n<size_matrix+1; n++){
            if (intr[n]==true){
                excluded[n]=false;
            }
        }
        return excluded;
    }




}

