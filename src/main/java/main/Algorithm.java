package main;

import java.util.ArrayList;

/**
 * Created by Andrey on 26.12.2014.
 */
class Algorithm {
    int[][] matrix;
    int vertex;
    int size_matrix;

    int[] straith;
    int[] reverse;
    int[] excluded;
    ArrayList<int[]> output;

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

        output=new ArrayList<int[]>();
        doThing(vertex);
    }


    private void doThing(int v){
        boolean check=false;
        for(int n=1; n<size_matrix+1; n++){
            if(excluded[n]==-1) check=true;
        }
        if(check){
            setSt1();
            fillSt(v);
            output.add(straith);

            setRv1();
            fillRv(v);
            output.add(reverse);

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
        output.add(temp);
        System.out.println("Intersection");
        for(int c: temp)
            System.out.print(c);
        System.out.println();

        for (int n=1; n<size_matrix+1; n++){
            if (temp[n]==0){
                excluded[n]=0;
            }
        }
        output.add(excluded);
        System.out.println("Excluded");
        for(int c: excluded)
            System.out.print(c);
        System.out.println();
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
