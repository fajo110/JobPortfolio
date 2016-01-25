// **********************************************************
// Assignment1:
// Student1: Syed Muhammad Farjad Abbas
// UTOR user_name: abbass13
// UT Student #: 1000484157
// Author: Syed Abbas
//
// Student2: Conor O'Hara
// UTOR user_name: oharacon
// UT Student #: 992197083
// Author: Conor O'Hara
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************
package a1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class JShell {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
      String command="";
      String delims="[ ]+";
      int i=0;
      
      
      while(!command.equals("exit")){
        Scanner in = new Scanner(System.in);
        System.out.print("/# ");
        command = in.nextLine();
        String[] tokens = command.split(delims);
        
        // if spaces only restart command line
        if (tokens.length==0) continue;
        
        // remove spaces from start of input
        if (tokens[0].equals("")) i=1;
        else i=0;
        
        // accept valid input from user & error msg
        if (tokens[i].equals("exit") && tokens.length==1+i)
        {
          exit exit = new exit();
        }
        else if (tokens[i].equals("ls") && tokens.length==1+i)
        {
          System.out.println("ls");
        }
        else if (tokens[i].equals("pwd") && tokens.length==1+i)
        {
          System.out.println("pwd");
        }
        else if (tokens[i].equals("mkdir") && tokens.length==2+i)
        {
          System.out.println("mkdir");
          System.out.println(tokens[1+i]);
        }
        else if (tokens[i].equals("cd") && tokens.length==2+i)
        {
          System.out.println("cd");
          System.out.println(tokens[1+i]);
        }
        else if (tokens[i].equals("cat") && tokens.length==2+i)
        {
          System.out.println("cat");
          System.out.println(tokens[1+i]);
        }
        else if (tokens[i].equals("get") && tokens.length==2+i)
        {
          System.out.println("get");
          System.out.println(tokens[1+i]);
        }
        else if (tokens[i].equals("mv") && tokens.length==3+i)
        {
          System.out.println("mv");
          System.out.print(tokens[1+i]+" ");
          System.out.println(tokens[2+i]);
        }
        else if (tokens[i].equals("cp") && tokens.length==3+i)
        {
          System.out.println("cp");
          System.out.print(tokens[1+i]+" ");
          System.out.println(tokens[2+i]);
        }
        else if (tokens[i].equals("echo") && tokens[i + 2].equals(">")
            && tokens.length == 4 + i) {
          System.out.println("echo");
          System.out.print(tokens[1 + i] + " ");
          System.out.print(tokens[2 + i] + " ");
          System.out.println(tokens[3 + i]);
        } else if (tokens[i].equals("echo") && tokens[i + 2].equals(">>")
            && tokens.length == 4 + i) {
          System.out.println("echo");
          System.out.print(tokens[1 + i] + " ");
          System.out.print(tokens[2 + i] + " ");
          System.out.println(tokens[3 + i]);
        } else {
          System.out.println("Invalid command, please try again");
        }

      }
    }
  }





