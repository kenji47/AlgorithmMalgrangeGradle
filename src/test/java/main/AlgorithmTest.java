package main;

import org.junit.Assert;
import org.junit.Test;

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
    public void StraightTransitiveClosure_standard11_matrix(){

        Algorithm alg=new Algorithm(standard11_matrix,1,11);

        alg.fillSt(1);
        int[] result=alg.straith;

        Assert.assertArrayEquals(new int[]{0,0,-1,-1,0,0,0,0,0,-1,-1,0},result);
    }

    @Test
    public void ReverseTransitiveClosure_standard11_matrix(){

        Algorithm alg=new Algorithm(standard11_matrix,1,11);

        alg.fillRv(1);
        int[] result=alg.reverse;

        Assert.assertArrayEquals(new int[]{0,0,0,0,-1,-1,-1,0,-1,0,0,0},result);
    }




}