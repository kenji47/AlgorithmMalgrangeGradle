package main;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AlgorithmTest {

    int[][] standard11_matrix=new int[][]{
        {0,0,0,0,0,0,1,0,0,0,0},
        {1,1,0,0,0,0,0,1,0,0,1},
        {0,0,1,1,0,0,0,0,1,1,0},
        {0,0,0,1,1,0,0,0,0,0,0},
        {0,0,0,1,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,0,1,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,1},
        {0,0,0,0,0,0,0,0,0,0,0},
        {0,0,1,0,0,0,0,0,0,0,0},
        {1,0,0,1,1,0,0,0,1,0,0},
        {1,0,0,0,1,1,0,0,0,0,1}};


    @Test
    public void StraightTransitiveClosure_Refactor() {
        Algorithm alg=new Algorithm(standard11_matrix,1,11);
        alg.fillStraightTransitiveClosure(1,alg.excludedVertex);
        boolean[] result=alg.straightTC;
        Assert.assertTrue(Arrays.equals(result,new boolean[]{false,true,false,false,true,true,true,true,true,false,false,true}));
    }


    @Test
    public void ReverseTransitiveClosure_Refactor() {
        Algorithm alg=new Algorithm(standard11_matrix,1,11);
       // alg.fillReverseTransitiveClosure(1);
        boolean[] result=alg.reverseTC;
        //Assert.assertTrue(Arrays.equals(result,new boolean[]{false,true,true,true,false,false,false,true,false,true,true,true}));
    }
    @Test
    public void Intersection_Refactor() {
        Algorithm alg=new Algorithm(standard11_matrix,1,11);

        boolean[] result=alg.getIntersection(new boolean[]{false,true,false,false,true,true,true,true,true,false,false,true},new boolean[]{false,true,true,true,false,false,false,true,false,true,true,true});

        Assert.assertTrue(Arrays.equals(new boolean[]{false, true, false, false, false, false, false, true, false, false, false,true},result));
    }

    @Test
    public void ExcludedVertex(){
        Algorithm alg=new Algorithm(standard11_matrix,1,11);

        boolean[] result=alg.getExcludedVertex(new boolean[]{false, true, false, false, false, false, false, true, false, false, false,true},alg.excludedVertex);

        Assert.assertTrue(Arrays.equals(new boolean[]{true,false,true,true,true,true,true,false,true,true,true,false},result));


    }


}