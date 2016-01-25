//**********************************************************
//Assignment3:Syed Abbas
//UTORID user_name: Syed M Farjad Abbas
//1000484157
//Author:Syed Abbas
//
//
//Honor Code: I pledge that this program represents my own
//program code and that I have coded on my own. I received
//help from no one in designing and debugging my program.
//*********************************************************
package driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

import Methods.AuthorsName;
import Methods.CoAuthors;
import Methods.FirstFivePub;
import Methods.FirstThreePubs;
import Methods.ITenIndex;
import Methods.NumAllCitations;
public class MyParser {

/**
* @param args
*/
public static void main(String[] args) {
 StarterCode(args);
}


/*
* This is a debug/helper method to help you get started. Once you understand
* how this method is being used, you are free to refactor it, modify it, or
* change it, or remove it entirely in any way you like.
*/
private static void StarterCode(String[] args) {
 try {
   System.out.println("URLS are " + args[0]);
   System.out.println("FileName is " + args[1]);
 } catch (Exception e) {
   System.out.println("Did you change the run configuration in"
       + "Eclipse for argument0 and argument 1?");
 }
 
 
 // TODO Auto-generated method stub
 String inputFiles[] = args[0].split(",");
 for (String inputFile : inputFiles) {
   System.out.println("------------------------------------------"
       + "-----------------------------");
   AuthorsName.extractAuthorsName(inputFile);
   NumAllCitations.extractNumAllCitations(inputFile);
   ITenIndex.extractITenIndex(inputFile);
   FirstThreePubs.extractFirstThreePubs(inputFile);
   FirstFivePub.extractFirstFivePub(inputFile);
   CoAuthors.extractCoAuthors(inputFile);
   //CoAuthorsSorted.extractCoAuthorsSorted(inputFile);
 }
}




}
