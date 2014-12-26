package main;

//import sun.plugin.javascript.navig.Array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andrey on 26.12.2014.
 */
class Algorithm {
    int[][] matrix;
    int vertex;
    int size_matrix;

    int[] straith;
    boolean[] straightTC;
    boolean[] reverseTC;
    boolean[] excludedVertex;
    int[] reverse;
    int[] excluded;
    ArrayList<StringBuilder> output;

    public Algorithm(int[][] m, int v, int s){
        matrix=m;
        vertex=v;
        size_matrix=s;


        straith= new int[size_matrix+1];
        straith[1]=-1;
        straith[2]=-1;
        straith[3]=-1;
        straith[4]=-1;
        straith[5]=-1;
        straith[6]=-1;
        straith[7]=-1;
        straith[8]=-1;
        straith[9]=-1;
        straith[10]=-1;
        straith[11]=-1;
        straith[vertex]=0;

        straightTC=new boolean[size_matrix+1];
        reverseTC=new boolean[size_matrix+1];
        excludedVertex=new boolean[size_matrix+1];
        for (int n=0; n<size_matrix+1; n++){
            straightTC[n]=false;
            reverseTC[n]=false;
            excludedVertex[n]=true;
        }
        //excludedVertex[v]=false;

        reverse=new int[size_matrix+1];
        reverse[1]=-1;
        reverse[2]=-1;
        reverse[3]=-1;
        reverse[4]=-1;
        reverse[5]=-1;
        reverse[6]=-1;
        reverse[7]=-1;
        reverse[8]=-1;
        reverse[9]=-1;
        reverse[10]=-1;
        reverse[11]=-1;
        reverse[vertex]=0;

        excluded=new int[size_matrix+1];
        excluded[1]=-1;
        excluded[2]=-1;
        excluded[3]=-1;
        excluded[4]=-1;
        excluded[5]=-1;
        excluded[6]=-1;
        excluded[7]=-1;
        excluded[8]=-1;
        excluded[9]=-1;
        excluded[10]=-1;
        excluded[11]=-1;
        excluded[0]=0;

        output=new ArrayList<StringBuilder>();
       // doThing(vertex);
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
    private void doThing(int v){
        boolean check=false;
        for(int n=1; n<size_matrix+1; n++){
            if(excluded[n]==-1) check=true;
        }
        if(check){
            setSt1();
            fillSt(v);
            //output.add(straith);

            setRv1();
            fillRv(v);
           //output.add(reverse);

            setIntersection();


            int tempInt=-1;
            for(int n=1; n<size_matrix+1; n++){
                if(excluded[n]==-1) {tempInt=n; break;}
            }
            if(tempInt!=-1)
                doThing(tempInt);

        }


    }

     void fillSt(int v){
        straith[v]=0;
        for (int n=0; n<size_matrix; n++){
            if(excluded[v]==-1 && excluded[n+1]==-1) {
                if (matrix[v - 1][n] == 1 && straith[n + 1] == -1) {
                    straith[n + 1] = 0;
                    fillSt(n + 1);
                    //System.out.println("yes: "+(n+1));
                }
            }
            //System.out.print(matrix[v-1][n]);
        }

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

     void fillRv(int v){
        reverse[v]=0;
        for (int n=0; n<size_matrix; n++) {
            if (excluded[v] == -1 && excluded[n + 1] == -1) {
                if (matrix[n][v - 1] == 1 && reverse[n + 1] == -1) {
                    reverse[n + 1] = 0;
                    fillRv(n + 1);
                    //System.out.println("yes: "+(n+1));
                }
                //System.out.print(matrix[v-1][n]);
            }
        }
    }

    private void setIntersection(){
        int[] temp=new int[size_matrix+1];
        temp[1]=-1;
        temp[2]=-1;
        temp[3]=-1;
        temp[4]=-1;
        temp[5]=-1;
        temp[6]=-1;
        temp[7]=-1;
        temp[8]=-1;
        temp[9]=-1;
        temp[10]=-1;
        temp[11]=-1;

        for (int n=1; n<size_matrix+1;n++){
            if(reverse[n]==0 && straith[n]==0){
                temp[n]=0;
            }
        }
       // output.add(temp);
        System.out.println("Intersection");
        for(int c: temp)
            System.out.print(c);
        System.out.println();

        for (int n=1; n<size_matrix+1; n++){
            if (temp[n]==0){
                excluded[n]=0;
            }
        }
        //output.add(excluded);
        System.out.println("Excluded");
        for(int c: excluded)
            System.out.print(c);
        System.out.println();
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

    private boolean checkArray(int[] a){

        return false;
    }
    private void setSt1(){
        straith[1]=-1;
        straith[2]=-1;
        straith[3]=-1;
        straith[4]=-1;
        straith[5]=-1;
        straith[6]=-1;
        straith[7]=-1;
        straith[8]=-1;
        straith[9]=-1;
        straith[10]=-1;
        straith[11]=-1;
    }
    private void setRv1(){
        reverse[1]=-1;
        reverse[2]=-1;
        reverse[3]=-1;
        reverse[4]=-1;
        reverse[5]=-1;
        reverse[6]=-1;
        reverse[7]=-1;
        reverse[8]=-1;
        reverse[9]=-1;
        reverse[10]=-1;
        reverse[11]=-1;
    }


}

