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
public class PartTime extends Employee{
    //Set variables that can only be accessed by this class or subclasses
	protected double hourlyWage;
	protected double hrsPerWeek;
	protected int weeksPerYear;

	//Initialize an employee object
	public PartTime(String first, String last, char gender,
			int ID, double wage, double hours, int weeks, double deduct) {
		super(first, last, gender, ID, deduct);
		hourlyWage=wage;
		hrsPerWeek=hours;
		weeksPerYear=weeks;
	}

	//Set variables values
	public void setHourlyWage(double wage)
	{
		hourlyWage=wage;
	}

	public void setHrsPerWeek(double hours)
	{
		hrsPerWeek=hours;
	}

	public void setWeeksPerYear(int weeks)
	{
		weeksPerYear=weeks;
	}

	//Return variables values
	public double getHourlyWage()
	{
		return hourlyWage;
	}

	public double getHrsPerWeek()
	{
		return hrsPerWeek;
	}

	public int getWeeksPerYear()
	{
		return weeksPerYear;
	}

	//Output all data
	public void showAll()
	{
		System.out.println("Part Time Employee ID: " + getEmpNumber());
		System.out.println("First Name: " + getFirstName());
		System.out.println("Last Name: " + getLastName());
		System.out.println("Gender: " + getSex());
		System.out.println("Hourly Wage: " + getHourlyWage());
		System.out.println("Work Hours Per Week: " + getHrsPerWeek());
		System.out.println("Wekks Per Year: " + getWeeksPerYear());
		System.out.println("Deduction Rate: " + getDeductionRate() + "%");
	}

}
