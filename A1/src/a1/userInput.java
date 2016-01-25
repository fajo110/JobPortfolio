package a1;
import java.io.IOException;
import java.util.Scanner;


public class userInput{

  public userInput() throws IOException{
      
   
     String uInput;
     int Startline = -1;
     int Endline = -1;
     
     

     Scanner in = new Scanner(System.in);
     // asks user to enter command after "/#: "
     System.out.print("/#: ");
     uInput = in.nextLine();


     String[] input = uInput.split(" +");
     // splits user_input in a list
     
    int i = 0;
    while (i <uInput.length()){
        if (uInput.charAt(i)=='"' && Startline<0){
          Startline = i;
        }
        else if (uInput.charAt(i)=='"' && Startline>=0){
          Endline=i;
        }
        i++; 
    }
    String content = null;
   //System.out.println(user_input.subSequence(StartQuote+1, EndQuote));
    if(Endline>Startline){
     content = (String) uInput.subSequence(Startline+1, Endline);
    }
  }

  }
