// **********************************************************
// Assignment0: Syed Abbas
// UTOR user_name: abbass13
// UT Student #: 1000484157
// Author: Syed M Farjad Abbas
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************
package driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.*;

import a0.Cfiltering;

public class CfilteringDriver {

  /**
   * Reads user movie ratings from a text file, calculates similarity scores and
   * prints a score matrix.
   */
  public static void main(String[] args) {
    try {

      // open file to read
      String fileName;
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of input file? ");
      fileName = in.nextLine();
      FileInputStream fStream = new FileInputStream(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

      // Read dimensions: number of users and number of movies
      int numberOfUsers = Integer.parseInt(br.readLine());
      int numberOfMovies = Integer.parseInt(br.readLine());
      System.out.println("For debugging:#Users = " + numberOfUsers);
      System.out.println("For debugging:#Movies= " + numberOfMovies);

      /*
       * create a new Cfiltering object that contains: a) 2d matrix
       * i.e.userMovieMatrix (#users*#movies) b) 2d matrix i.e. userUserMatrix
       * (#users*#users)
       */
      Cfiltering cfObject = new Cfiltering(numberOfUsers, numberOfMovies);
      
      // this is a blankline being read
      br.readLine();

      // read each line of movie ratings and populate the
      // userMovieMatrix
      String row;
      	int rowCount=-1;
      while ((row = br.readLine()) != null) {
    	  rowCount +=1;
    	  String allRatings[] = row.split(" ");
        int columnCount = -1;
        for (String singleRating : allRatings) {
          // make the String number into an integer
          // populate userMovieMatrix
          columnCount += 1;
          cfObject.userMovieMatrix[rowCount][columnCount]
        		  =Integer.parseInt(singleRating);
      }
      }
      //Similarity score gets calculated
      cfObject.calculateSimilarityScore();
      //Prints the phrase of userUserMatrix
      System.out.println("\n\nuserUserMatrix is: ");
      //Asks for the input of the userUserMatrix
      cfObject.printUserUserMatrix();
      //Prints the phrase of most similar pairs of User in the required format
      System.out.println("\n\nThe most similar pairs of users from above" 
      + " userUserMatrix are: ");
      cfObject.findAndprintMostSimilarPairOfUsers();
      System.out.println(); //line break is required
    //Prints the most dissimilar pairs of User in the required format
      System.out.println("\n\nThe most dissimilar pairs of users from above" 
    	      + " userUserMatrix are: ");
      cfObject.findAndprintMostDissimilarPairOfUsers();
      System.out.println(); //line break is required
      // closes the file
      fStream.close();

     
    } catch (FileNotFoundException e) {
      System.err.println("Do you have the input file in the root folder "
          + "of your project?");
      System.err.print(e.getMessage());
    } catch (IOException e) {
      System.err.print(e.getMessage());
    }
  }

}