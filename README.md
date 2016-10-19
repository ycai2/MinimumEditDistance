#Virus Infection Problem

Author
===============
by Yisheng Cai

Implementation
====================
The minimum cost alignment (Minimum Edit Distance) that finds an O(mn)-time algorithm to find the best alignment of s and t and justify its correctness and running time for given DNA sequences s and t of lengths m and n, respectively, both defined over the alphabet {A,C,G,T}.
The algorithm prints out a minimum value of all optimal alignments and 

Usage
============
Prepare any input file under the same directory as the program files.
The format has to be:
  -first row is sigma, which is the cost of inserting a gap
  -in the next four rows, specify a 4x4 matrix with combinations of mismatches of {A, C, G, T}
  -after inputing the similarity matrix, in next two lines, input two strings consisting of {A, C, G, T}

example:
2
0 1 3 0
1 0 1 2
3 1 0 4
0 2 4 0
AAAGTCTGAC
AACGTTTAC


 Run program with $javac *.java
                  $java Main
                  $your_input_file_name



