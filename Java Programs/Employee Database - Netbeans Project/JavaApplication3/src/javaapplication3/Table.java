/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;
import java.util.ArrayList;
import java.util.Hashtable;
/**
 *
 * @author Farjad Abbas
 */
public class Table {
    //Set the empty arraylists and hashtables
	private ArrayList<Employee> List0;
	private ArrayList<Employee> List1;
	private ArrayList<Employee> List2;
	private ArrayList<Employee> List3;
	private ArrayList<Employee> List4;
	private Hashtable<Integer, ArrayList<Employee>> Hash;

	//Initialize the arraylists and hashtables
	public Table ()
	{
		List0 = new ArrayList<Employee>(900000);
		List1 = new ArrayList<Employee>(900000);
		List2 = new ArrayList<Employee>(900000);
		List3 = new ArrayList<Employee>(900000);
		List4 = new ArrayList<Employee>(900000);
		Hash = new Hashtable<Integer, ArrayList<Employee>>();
		Hash.put(0, List0);
		Hash.put(1, List1);
		Hash.put(2, List2);
		Hash.put(3, List3);
		Hash.put(4, List4);
	}

	//Return the hash table
	public Hashtable<Integer, ArrayList<Employee>> getHash()
	{
		return Hash;
	}
}
