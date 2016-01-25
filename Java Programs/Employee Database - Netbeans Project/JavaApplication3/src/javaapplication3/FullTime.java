/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

/**
 *
 * @author Farjad Abbas
 */
//Set subclass of employee
public class FullTime extends Employee {
    //Set variables that can only be accessed by this class or subclasses
	protected double annualSalary;

	//Initialize an employee object
	public FullTime(String first, String last, char gender,
			int ID, double salary, double deduct) {
		super(first, last, gender, ID, deduct);
		annualSalary=salary;
	}

	//Set variables values
	public void setAnnualSalary(double salary)
	{
		annualSalary=salary;
	}

	//Return variables values
	public double getAnnualSalary()
	{
		return annualSalary;
	}

	//Output all data
	public void showAll()
	{
		System.out.println("Full Time Employee ID: " + getEmpNumber());
		System.out.println("First Name: " + getFirstName());
		System.out.println("Last Name: " + getLastName());
		System.out.println("Gender: " + getSex());
		System.out.println("Annual Salary: " + getAnnualSalary());
		System.out.println("Deduction Rate: " + getDeductionRate() + "%");
	}

}
