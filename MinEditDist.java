import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.lang.Math.*;

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

  //If sequence1 has m characters and sequence2 has n characters
  //The matrix is constructed m * n

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
        char a = seq1.charAt(i-1);
        char b = seq2.charAt(j-1);
        int ind_seq1 = findIndex(a);
        int ind_seq2 = findIndex(b); 

        System.out.println("penalty: " + similarityMatrix[ind_seq1][ind_seq2]);
        
        

        int calc = similarityMatrix[ind_seq1][ind_seq2] + table[i-1][j-1];
        //int minOfGaps = Math.min(topRight, botLeft);
        //table[i][j] = Math.min(calc, minOfGaps);
        table[i][j] = findMin(calc, topRight, botLeft);
        System.out.println("Min is " + table[i][j]);
        printTable();
        System.out.println();
      }
    }
  }

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

  private int findMin(int a, int b, int c){
    return Math.min(a, Math.min(b + gap, c + gap));
  }

  public void printTable(){
    for (int i=0; i<seq1.length()+1; i++){
      for (int j=0; j<seq2.length()+1; j++){
        System.out.print(table[i][j]+"\t");
      }
      System.out.println();
    }
  }

  /*private void printSolution(){
    Stack<char> trace = new Stack<char>();
    int i = seq1.length() + 1;
    int j = seq2.length() + 1;
    

    while ((i != 0) || (j != 0)){
      if (table[i][j] == mintable[])
    }

  }*/
}