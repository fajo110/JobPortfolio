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
package a0;
import java.text.DecimalFormat;
import java.util.*;

public class Cfiltering {
  // this is a 2d matrix i.e. user*movie
  public int userMovieMatrix[][];
  // this is a 2d matrix i.e. user*movie
  private float userUserMatrix[][];

  /**
   * Default Constructor.
   */
  public Cfiltering() {
    // this is 2d matrix of size 1*1
    userMovieMatrix = new int[1][1];
    // this is 2d matrix of size 1*1
    userUserMatrix = new float[1][1];
  }
  DecimalFormat dformat = new DecimalFormat("0.0000");
  /*
   * TODO:COMPLETE THIS I.E. APPROPRIATELY CREATE THE userMovieMatrix AND
   * userUserMatrix WITH CORRECT DIMENSIONS.
   */
  /**
   * Constructs an object which contains two 2d matrices, one of size
   * users*movies which will store integer movie ratings and one of size
   * users*users which will store float similarity scores between pairs of
   * users.
   * 
   * @param numberOfUsers Determines size of matrix variables.
   * @param numberOfMovies Determines size of matrix variables.
   */
  public Cfiltering(int numberOfUsers, int numberOfMovies) {
	  userMovieMatrix = new int[numberOfUsers][numberOfMovies];
	  userUserMatrix = new float[numberOfUsers][numberOfUsers];
	  
  }

  /**
   * The purpose of this method is to populate the UserMovieMatrix. As input
   * parameters it takes in a rowNumber, columnNumber and a rating value. The
   * rating value is then inserted in the UserMovieMatrix at the specified
   * rowNumber and the columnNumber.
   * 
   * @param rowNumber The row number of the userMovieMatrix.
   * @param columnNumber The column number of the userMovieMatrix.
   * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
   */
  public void populateUserMovieMatrix(int rowNumber, int columnNumber,
      int ratingValue) {

    userMovieMatrix[rowNumber][columnNumber] = ratingValue;
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC. Add/remove
   * 
   * @param AND
   * 
   * @return as required below.
   */
  /**
   * Determines how similar each pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */
  
  
  public void calculateSimilarityScore()
  {
	  //Nested while loop for iteration
	  //to compare users with each other
      int firstUser = 0;
	  while ( firstUser < userMovieMatrix.length){
	    int secondUser = 0;
		  while ( secondUser < userMovieMatrix.length){
			 double Distance=0;
			 //Distance formula is applied 
			 //in order to check the similarity score
			 //adds the difference, then squares the movie ratings
			 //lastly, finds the square root, to find the final distance
			 int Movie=0;
			 while (Movie < userMovieMatrix[0].length){
			   
			   Distance +=(double) Math.pow((userMovieMatrix[firstUser][Movie] 
			        - userMovieMatrix[secondUser][Movie]), 2);
				 Movie++;
			 }
			 
			 //Adds 1 and inverse of the distance is taken
			 //finds the sqaure root of distance
			 float SimilarityScore =(float)(1/(Math.sqrt(Distance)+1));
				 userUserMatrix[firstUser][secondUser]=SimilarityScore;
				 secondUser++;
		  }
		  firstUser++;
	  }
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and
   * column representing each/single user and the cell position (i,j)
   * representing the similarity score between user i and user j.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */

  public void printUserUserMatrix() {
	  
	  //compares it with all the users through the while loop
    int i=0;  
    while (i < (userUserMatrix.length))
      {
      
		  //an open bracket is included, where it runs through the whole loop
              System.out.printf("[");
              int j=0;
              while (j < (userUserMatrix[0].length - 1))
              {
                
                  System.out.printf(dformat.format(userUserMatrix[i][j])+", ");
                  j++;
              }
      System.out.printf(dformat.format(userUserMatrix[i][userUserMatrix.length
                                                    - 1]));
     //as it runs through the loop, there is a closed bracket at the end
      System.out.println(']');
      i++;
      }
  }
  /**
   * This function finds and prints the most similar pair of users in the
   * userUserMatrix.
   */

  public void findAndprintMostSimilarPairOfUsers() {
	  //In order to hold most similar users, array list is created
	  //for maximum similarity, a variable is also required
	  float maximum = (float) 0.0;
      ArrayList<String> simUser = new ArrayList<String>();
      
      //to find the most similar users, it runs through the loop
      int m = 0;
      while (m < userUserMatrix.length){
          
          int n = 0;
    	  while(n < userUserMatrix.length){
    	    
    		  //'if' statement checks whether if current value > maximum value
    		  if((m<n) && (this.userUserMatrix[m][n] > maximum))
    		  {
    			  maximum = userUserMatrix[m][n];
    			  simUser.clear();
    			  simUser.add("User" + (m + 1) + " and User" + (n + 1));
    		  }
    	//'else if' statement checks whether if current value = maximum value
    		  else if((m<n) && (userUserMatrix[m][n] == maximum))
    		  {
    			  simUser.add("User" + (m + 1) + " and User" + (n + 1));  
    		  }
    		  n++;
    	  }
    	  m++;
      }
      
      
      //a comma is required after every value
      int i =0;
      while(i < simUser.size() - 1){
        i++;
          System.out.println(simUser.get(i) + ",");
      }
      //prints an English statement, once it loops through the function
      System.out.println(simUser.get(simUser.size() - 1));
      System.out.println("With a similarity "
                      + "score of " + dformat.format(maximum));
 
  }

  /**
   * This function finds and prints the most dissimilar pair of users in the
   * userUserMatrix.
   * 
   */
  public void findAndprintMostDissimilarPairOfUsers() {
	  //minimum changes are made to it, including variable and symbols
	//In order to hold most dissimilar users, array list is created
	  //for minimum similarity, a variable is also required
	  float minimum = (float) 1.0;
      ArrayList<String> dissimUser = new ArrayList<String>();
      
      //to find the most dissimilar users, it runs through the loop
      int m = 0;
      while (m < userUserMatrix.length){
        
        int n = 0;
    	  while(n < userUserMatrix.length){
    		  //'if' statement checks whether if current value < value
    		  if((m<n) && (this.userUserMatrix[m][n] < minimum))
    		  {
    			  minimum = userUserMatrix[m][n];
    			  dissimUser.clear();
    			  dissimUser.add("User" + (m + 1) + " and User" + (n + 1));
    		  }
    	//'else if' statement checks whether if current value = maximum value
    		  else if((m<n) && (userUserMatrix[m][n] == minimum))
    		  {
    			  dissimUser.add("User" + (m + 1) + " and User" + (n + 1));  
    		  }
    		  n++;
    	  }
    	  m++;
      }
      
      //a comma is required after every value
      int j =0;
      while(j < dissimUser.size() - 1){
        j++;
          System.out.println(dissimUser.get(j) + ",");    	  
      }
      //prints an English statement, once it loops through the function
      System.out.println(dissimUser.get(dissimUser.size() - 1));
      System.out.println("With a similarity "
                      + "score of " + dformat.format(minimum));
    //dissimilarPairofUsers function is very similar to previous function
  }
}
