// The "MathCompetition" class.
import java.awt.*;
import hsa.Console;

public class MathCompetition
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	c.println ("WELCOME TO THE ULTIMATE MATH CONTEST!\n");
	c.println ("Answer the ten math questions as quickly and correctly as you can to achieve the best score.");
	c.println ("Remember: time matters and you lose points for answering incorrectly.\n");
	c.println ("Press 'start' key to begin you math test");
	c.readString ();

	// Get current time
	long start = System.currentTimeMillis ();
	float points = 0;
	int number1 = 0, number2 = 0, correctAnswers = 0, answer = 0, userAnswer = 0;

	for (int k = 1 ; k < 11 ; k++)
	{

	    number1 = (int) (Math.random () * 99) + 1;
	    number2 = (int) (Math.random () * 9) + 1;
	    answer = number1 * number2;
	    c.println ("\n" + k + ") " + number1 + " * " + number2);
	    userAnswer = c.readInt ();
	    if (userAnswer == answer)
	    {
		points = points + 10;
		correctAnswers = correctAnswers + 1;
	    }
	    else
	    {
		points = points - 5;
	    }
	}

	// Get elapsed time in milliseconds
	long elapsedTimeMillis = System.currentTimeMillis () - start;

	// Get elapsed time in seconds
	float elapsedTimeSec = elapsedTimeMillis / 1000F;

	// Get elapsed time in minutes
	float elapsedTimeMin = elapsedTimeMillis / (60 * 1000F);

	// Get elapsed time in hours
	float elapsedTimeHour = elapsedTimeMillis / (60 * 60 * 1000F);

	// Get elapsed time in days
	float elapsedTimeDay = elapsedTimeMillis / (24 * 60 * 60 * 1000F);

	points = points / elapsedTimeMin;
	c.println ("\nYou took " + elapsedTimeSec + " seconds to complete this test.");
	c.println ("You answered " + correctAnswers + " correctly.");
	c.println ("Your final score is " + points);

    } // main method
} // MathCompetition class
