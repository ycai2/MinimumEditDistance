import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.lang.Math.*;

/**
* File: MinEditDist.java
* This class takes a gap penalty, a similarity matrix, and two sequences, to find the 
* minimum edit distance alignment of two sequences by using Dynamic Programming paradigm. 
* 
*
*
* @author Yisheng Cai
* @date 11/23/14
*
*/


public class MinEditDist
{
  protected int gap;
  //Assuming the similarity matrix is 4x4
  protected int[][] similarityMatrix = new int[4][4];
  public int[][] table;
  protected String seq1;
  protected String seq2;

  //Constructor of MinEditDist class
  public MinEditDist(int sigma, int[][] similarityMatrix, String seq1, String seq2){
    this.gap = sigma;
    this.similarityMatrix = similarityMatrix;
    this.seq1 = seq1;
    this.seq2 = seq2;
    table = new int[seq1.length()+1][seq2.length()+1];
    solve();
  }

  /** 
   *  If sequence1 has m characters and sequence2 has n characters
   *  The matrix is constructed m * n
   *  This function modifies the matrix 'table' with the optimal solutions 
   *  of each subproblem
   */
  private void solve(){
    int m = seq1.length();
    int n = seq2.length();
    for (int i=0; i<m+1; i++){
      table[i][0] = i * gap;
    }
    for (int i=0; i<n+1; i++){
      table[0][i] = i * gap;
    }
    System.out.println();
    for (int i=1; i<m+1; i++){
      for (int j=1; j<n+1; j++){
        int topRight = table[i-1][j];
        int botLeft = table[i][j-1];

        int calc = penaltyCost(i, j)+ table[i-1][j-1];
        table[i][j] = findMin(calc, topRight, botLeft);
      }
    }
  }

/**
* findIndex(x)
* The function finds the index of the input character in the similarity matrix
* @param  character x
* @return index of x in the similarity matrix 
*/
  private int findIndex(char x){
    int ind = 0;
    switch (x) {
      case 'A': ind = 0;
                break;
      case 'C': ind = 1;
                break;
      case 'G': ind = 2;
                break;
      case 'T': ind = 3;
                break;
      default:  break;
    }
    return ind;
  }

/**
* penaltyCost(ind1, ind2)
* The function finds the cost of mismatch of characters at index1 and index2
* 
* @param two integers index1 and index2
* @return the cost of mismatch from similarityMatrix[index1][index2]
*/
  private int penaltyCost(int ind1, int ind2){
    char a = seq1.charAt(ind1 - 1);
    char b = seq2.charAt(ind2 - 1);
    int ind_seq1 = findIndex(a);
    int ind_seq2 = findIndex(b);
    int sol = similarityMatrix[ind_seq1][ind_seq2];
    return sol;
  }

/**
* penaltyCost(ind1, ind2)
* The function finds the cost of mismatch of characters at index1 and index2
* 
* @param integers of diagonal cell, top cell, and left cell
* @return minimum value of the three cells with top and left modified.
*/
  private int findMin(int a, int topRight, int botLeft){
    return Math.min(a, Math.min(topRight + gap, botLeft + gap));
  }

/**
* printTable()
* The function prints the matrix 'table' of all possible optimal alignments
* 
* @param 
* @return
*/
  public void printTable(){
    for (int i=0; i<seq1.length()+1; i++){
      for (int j=0; j<seq2.length()+1; j++){
        System.out.print(table[i][j]+"\t");
      }
      System.out.println();
    }
  }

/**
* printSolution()
* The function performs backtrace to find optimal solutions in a bottomup manor. 
* Also, three stacks are created to keep track of optimal alignment of both sequences 
* the numerical cost of each alignment. 
* 
* @param 
* @return 
*/
  public void printSolution(){
    Stack seq1_trace = new Stack();
    Stack seq2_trace = new Stack();
    Stack cost = new Stack();
    int i = seq1.length();
    int j = seq2.length();
    

    while ((i > 0) && (j > 0)){
      int calc = penaltyCost(i, j) + table[i-1][j-1];
      int minimum = findMin(calc, table[i-1][j], table[i][j-1]);

      if (minimum == calc){
        seq1_trace.push(seq1.charAt(i-1));
        seq2_trace.push(seq2.charAt(j-1));
        cost.push(penaltyCost(i, j));
        i--;
        j--;
      }else if (minimum == (table[i-1][j] + gap)){
        seq2_trace.push('-');
        seq1_trace.push(seq1.charAt(i-1));
        cost.push(gap);
        i--;
      }else if (minimum == (table[i][j-1] + gap)){
        seq1_trace.push('-');
        seq2_trace.push(seq2.charAt(j-1));
        cost.push(gap);
        j--;
      }

      if (i == 0){
        while (j > 0){
          seq1_trace.push('-');
          seq2_trace.push(seq2.charAt(j-1));
          cost.push(gap);
          j--;
        }
      }

      if (j == 0){
        while (i > 0){
          seq2_trace.push('-');
          seq1_trace.push(seq1.charAt(i-1));
          cost.push(gap);
          i--;
        }
      }
    }

    while (!seq1_trace.empty()){
      System.out.print(seq1_trace.pop() + " ");
    }
    System.out.println();
    
    while (!seq2_trace.empty()){
      System.out.print(seq2_trace.pop() + " ");
    }
    System.out.println();

    while (!cost.empty()){
      System.out.print(cost.pop() + " ");
    }
    System.out.println();
    System.out.println("with the minimum edit distance of " + table[seq1.length()][seq2.length()]);
    System.out.println();
  }
}