//Program: TestStack
//By Farjad Abbas
//Date: 26th September, 2012
//Purpose: to display the whole list where it pushes it, and then pops it out where the first item is located in the reference.

//import java.awt.*;
import hsa.Console;

public class TestStack
{

//    static Console c;           // The output console
     
    //static Person myPerson_A;
    //static Person myPerson_B;
   
    public static void main (String[] args)
    {
//        c = new Console (); now we have called the previos console Name MyStack which is known as myS.
       
	MyStack myS = new MyStack();

	ListItem tempListItem = new ListItem("Bugs", "Bunny");        
	myS.push(tempListItem);

	tempListItem = new ListItem("Daffy", "Duck");        
	myS.push(tempListItem);

	tempListItem = new ListItem("Elmer", "Fudd");
	myS.push(tempListItem);
	
	tempListItem = new ListItem("Foghorn", "Leghorn");
	myS.push(tempListItem);
	 
	tempListItem = new ListItem("Marvin", "Martian");
	myS.push(tempListItem);
      
	myS.displayList(); // we called the display list for myS...
	
	tempListItem = myS.pop();//the temporary list item will now pop through myS
	
	myS.displayList();// this then pops out the final items

    } // main method
    
} // TestStack class
