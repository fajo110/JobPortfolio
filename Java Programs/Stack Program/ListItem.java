//Program: ListItem
//By Farjad Abbas
//Date: 26th September, 2012


public class ListItem {
// Make a public class named it ListItem
    private String firstName;
    private String lastName;
    public ListItem next;

    
    public ListItem(String fname, String lname) {
	firstName = fname;
	lastName = lname;
	next = null;
    }
    
    
    public String getFirstName() {
	return (firstName);
    }
    
    public String getLastName() {
	return (lastName);
    }
    
    public ListItem getNext() {
	return (next);
    }
    
    public void setNext(ListItem itemToBecomeNext) {
	next = itemToBecomeNext;
    }

}
