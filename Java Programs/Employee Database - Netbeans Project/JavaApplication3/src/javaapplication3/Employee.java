/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

/**
 *
 * @author Farjad Abbas
 */
//Set as class that can only be accessed through subclasses
abstract class Employee {
    //Set variables that can only be accessed by this class or subclasses
	protected String firstName;
	protected String lastName;
	protected char sex;
	protected int empNumber;
	protected double deductionRate;

	//Initialize an employee object
	public Employee(String First, String Last, char gender, int ID, double deduct)
	{
		firstName=First;
		lastName=Last;
		sex=gender;
		empNumber=ID;
		deductionRate=deduct;
	}

	//Set variables values
	public void setFirstName(String First)
	{
		firstName=First;
	}

	public void setLastName(String Last)
	{
		lastName=Last;
	}

	public void setSex(char gender)
	{
		sex=gender;
	}

	public void setEmpNumber(int ID)
	{
		empNumber=ID;
	}

	public void setDeductionRate(double deduct)
	{
		deductionRate=deduct;
	}

	//Return variables values
	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public char getSex()
	{
		return sex;
	}

	public int getEmpNumber()
	{
		return empNumber;
	}

	public double getDeductionRate()
	{
		return deductionRate;
	}
}
