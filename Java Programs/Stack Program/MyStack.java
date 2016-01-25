//Program: MyStack
//By Farjad Abbas
//Date: 26th September, 2012
//Purpose: to stack the list items, where we remove the first item from the stack and then pop it a new one, where the first item is removed.

public class MyStack {
//We create a new public  cclass and name it MyStack where we set "ListItem topOfStack" as of private and "numItems" as private too.
    private ListItem topOfStack;
    private int numItems;
    
    
    public MyStack() {
	topOfStack = null;
	numItems = 0;
    }
    
    
    public void push (ListItem itemToAdd) { //In this public counsole "push" has been created
    // where you set numItems as 0 and then topOfStack is equal to itemToAdd and numsItem will grow higher.
       if (numItems == 0) {
	    topOfStack = itemToAdd;
	    numItems++;
	}
	else {
	    if (numItems > 0) { //the numItems are then greater than 0, where items that will get added are gonna be set it next to.
		itemToAdd.setNext(topOfStack);
		topOfStack = itemToAdd;
		numItems++;
	    }
	    else {
		// Should never have less than 0 items in the list!
	    }
	}
    } // end push
    
    
    public ListItem pop() {
	// Remove the item from the top of the stack, which then is equal to the current item, and then gets the next item from the top of the stack.
	numItems--;
	ListItem currentItem;
	currentItem = topOfStack;
	topOfStack = topOfStack.getNext();
	currentItem.setNext (null);
	return (null);
    } // end pop
    
    
    public void displayList() {
    // This shows up the display list where it takes out the first item and puts it in the reference list.
	ListItem currentItem;
    
	System.out.println("Here are the contents of the linked list.\n");
	
	currentItem = topOfStack;
	
	while (currentItem != null) { // at this case..while currentItem is set yo null, it will get the FirstName, LastName, and Next item.
	    System.out.println("firstName " + currentItem.getFirstName());
	    System.out.println("lastName " + currentItem.getLastName());
	    System.out.println("next " + currentItem.getNext());
	    System.out.println();
	    
	    currentItem = currentItem.getNext();
	}
	// the currentItem will get the next currentItem.
	System.out.println("End of the linked list.\n");

    } // end displayList

}
