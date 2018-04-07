/**
* This class does the different calculations required to calculate 
* the different yahtzee scores.  Each method is static and takes in 
* a parameter of type ArrayList<Die>
* 
* CPSC 224-02, Spring 2018
* Programming Assignment #5
* @author Benjamin Bladow
* 
* @version v1.1 3/7/18
*/
import java.util.*;

public class Calculations {

	/**
    * This method returns the count of the dice values occurring most in the hand
	* NOT the value itself.
	* @param ArrayList<Die> has been filled with Dice
    * @returns count of max number of dice values
	* @throws N/A
    */
	public static int maxOfAKindFound(ArrayList<Die> hand)
	{
		int maxCount = 0;
		int currentCount ;
		for (int dieValue = 1; dieValue <= Die.numberOfSides; dieValue++)
		{
			currentCount = 0;
			for (int diePosition = 0; diePosition < Die.numberOfDie; diePosition++)
			{
				// equivalent to hand[diePosition] == dieValue of c++ code
				if (hand.get(diePosition).getValue() == dieValue) 
					currentCount++;
			}
			if (currentCount > maxCount)
				maxCount = currentCount;
		}
		return maxCount;
	}

	/**
    * Finds the total value of all the dice added up
	* @param ArrayList<Die> has been filled with Dice
    * @returns integer of total value of all dice in hand
	* @throws N/A
    */
	public static int totalAllDice(ArrayList<Die> hand)
	{
		int total = 0;
		
		for (int diePosition = 0; diePosition < Die.numberOfDie; diePosition++)
		{
			total += hand.get(diePosition).getValue(); // hand[position] c++ code
		}
		return total;
	}

	/**
    * Finds the length of the longest straight found in a hand 
	* @param ArrayList<Die> has been filled with Dice. Must be sorted to work
    * @returns length of longest straight in hand
	* @throws N/A
    */
    public static int maxStraightFound(ArrayList<Die> hand)
	{
		int maxLength = 1;
		int curLength = 1;
		for(int counter = 0; counter < Die.numberOfDie - 1; counter++)
		{
			if (hand.get(counter).getValue() + 1 == hand.get(counter + 1).getValue()) //jump of 1
				curLength++;
			else if (hand.get(counter).getValue() + 1 < hand.get(counter + 1).getValue()) //jump of >= 2
				curLength = 1;
			if (curLength > maxLength)
				maxLength = curLength;
		}
		return maxLength;
	}

	/**
    * This method determines if the hand has Dice equivalent to a full house.  
	* Full House occurs when >= 5 of a kind occurs. And when, 3 (or 4) of a kind 
	* together with a pair or another >=3 of a kind.
	* @param ArrayList<Die> has been filled with Dice.  Must be sorted to work
    * @returns true if the hand has a full House. False otherwise
	* @throws N/A
    */
	public static boolean fullHouseFound(ArrayList<Die> hand)
	{
		boolean foundFH = false;
		boolean found3K = false;
		boolean found2K = false;
		int currentCount;
		for (int dieValue = 1; dieValue <= Die.numberOfSides; dieValue++)
		{
			currentCount = 0;
			for (int diePosition = 0; diePosition < Die.numberOfDie; diePosition++)
			{
				if (hand.get(diePosition).getValue() == dieValue)
					currentCount++;
				if(currentCount == 3 && !found3K)
				{
					found3K = true;
					currentCount = 0;
				}
			}
			
			if (currentCount >= 2)
			{
				found2K = true;
			}
		}
		if (found2K && found3K)
			foundFH = true;
		return foundFH;
	} 
	
}