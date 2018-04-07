/**
* This class does the different calculations required to calculate 
* the different yahtzee scores.  Each method is static and takes in 
* a parameter of type ArrayList<Die>
* 
* CPSC 224-02, Spring 2018
* Group Project
* @author Benjamin Bladow
* @author Brandon Niblock
* @author Eugene Krug
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
	public static int maxOfAZagFound(ArrayList<Die> hand)
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
    * This method determines if the hand has Dice equivalent to a full Team.  
	* Full Team occurs when >= 5 of a kind occurs. And when, 3 (or 4) of a kind 
	* together with a pair or another >=3 of a kind.
	* @param ArrayList<Die> has been filled with Dice.  Must be sorted to work
    * @returns true if the hand has a full House. False otherwise
	* @throws N/A
    */
	public static boolean fullTeamFound(ArrayList<Die> hand)
	{
		boolean foundFH = false;
		boolean found3K = false;
		boolean found2K = false;
		int guardCount = 0;
		int forwardCount = 0;
		for (int diePosition = 0; diePosition < Die.numberOfDie; diePosition++)
		{
			if (hand.get(diePosition).getPosition().equals("GUARD"))
				guardCount++;
			if(guardCount == 3 && !found3K)
			{
				found3K = true;
			}
			if (hand.get(diePosition).getPosition().equals("FORWARD"))
				forwardCount++;
			if(forwardCount == 3 && !found2K)
			{
				found2K = true;
			}
		}	
		if (found2K && found3K)
			foundFH = true;
		return foundFH;
	} 
	/**
	* This method determines if the hand has Dice equivalent to a full bench.  
	* Full Team occurs when >= 5 of a kind occurs. And when, all five dice 
	* together have the status of bench.
	* @param ArrayList<Die> has been filled with Dice.  Must be sorted to work
	* @returns true if the hand has a full bench. False otherwise
	* @throws N/A
	*/
	public static boolean benchBrigadeFound(ArrayList<Die> hand)
	{
		boolean foundBB = false;
		int benchCount = 0;
		for (int diePosition = 0; diePosition < Die.numberOfDie; diePosition++)
		{
			if (hand.get(diePosition).getStatus().equals("BENCH"))
				benchCount++;
			if(benchCount == 5 && !foundBB)
			{
				foundBB = true;
			}
		}	
		return foundBB;
	} 
	/**
	* This method determines if the hand has Dice equivalent to a full Starting lineup.  
	* Full Team occurs when >= 5 of a kind occurs. And when, all five dice 
	* together have the status of Starter.
	* @param ArrayList<Die> has been filled with Dice.  Must be sorted to work
	* @returns true if the hand has a full Starting lineup. False otherwise
	* @throws N/A
	*/
	public static boolean startingLineupFound(ArrayList<Die> hand)
	{
		boolean foundST = false;
		int starterCount = 0;
		for (int diePosition = 0; diePosition < Die.numberOfDie; diePosition++)
		{
			if (hand.get(diePosition).getStatus().equals("STARTER"))
				benchCount++;
			if(benchCount == 5 && !foundBB)
			{
				foundST = true;
			}
		}	
		return foundST;
	} 
}