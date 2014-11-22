import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws FileNotFoundException{
    int sigma=0;
    int[][] similarityMatrix = new int[4][4];
    String seq1 = "";
    String seq2 = "";

    Scanner inFile = new Scanner(System.in);
    System.out.println("Please enter a file name (under same directory & with extension): ");
    String fileName = inFile.next();
    inFile.close();
    Scanner sc = new Scanner(new FileReader(fileName));

    //Input sigma value
    if (sc.hasNextLine()){
      sigma = Integer.parseInt(sc.nextLine());
      System.out.println("Sigma is "+sigma+". ");
    }

    //Input Similarity Matrix
    for (int i=0; i<4; i++){
      for (int j=0; j<4; j++){
        if (sc.hasNextInt()){
          similarityMatrix[i][j] = sc.nextInt();
          System.out.print(similarityMatrix[i][j]+" ");
          if (j == 3){
            System.out.println();
          }
        }
        
      }
    }
    sc.nextLine();

    //Input sequences
    if (sc.hasNextLine()){
      seq1 = sc.nextLine();
      System.out.println(seq1);
    }
    if (sc.hasNextLine()){
      seq2 = sc.nextLine();
      System.out.println(seq2);
    }
    sc.close();

    MinEditDist ed = new MinEditDist(sigma, similarityMatrix, seq1, seq2);

    ed.printTable();

  }
}