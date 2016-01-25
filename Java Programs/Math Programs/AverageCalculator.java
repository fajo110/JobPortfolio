import hsa.Console;
public class AverageCalculator
{

    public static void main (String[] args)
    {
	Console c = new Console ("Avergae Calculator");
	int number = 0;
	double totalvalue = 0;
	double totalmarks = 0, average = 0;
	while (number <= 0)
	{
	    c.println ("How many marks would you like to enter?");
	    number = c.readInt ();
	    if (number <= 0)
	    {
		c.println ("Please Enter an appropriate integer.\n");
	    }
	    if (number > 0)
	    {
		c.println ("Enter your " + number + " marks.\n");
	    }
	}

	double[] marks = new double [number];
	double[] markValue = new double [number];

	    for (int k = 0 ; k < number ; k++)
	{
	    c.println ("Please enter your mark number " + (k + 1));
	    marks [k] = c.readDouble ();
	    c.println ("Please enter the overall value of this mark");
		markValue [k] = c.readDouble ();
	    totalmarks = totalmarks + (marks [k] * markValue [k]);
	    totalvalue = totalvalue + markValue [k];
	}
	average = totalmarks / totalvalue;
	c.println ("\nYour average is " + average + ".");
    }
}


