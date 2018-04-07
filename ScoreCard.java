/**
* This class will construct a ScoreCard that will keep track
* of which values are being placed into it and where.  It can print
* out the options a player has for placing values in the scoreCard. 
* An ArrayList of Dice and the Calculations class is used to do so
* 
* CPSC 224-02, Spring 2018
* Programming Assignment #5
* @author Benjamin Bladow
* 
* @version v2.0 3/7/18
*/
import java.util.*;
import java.io.*;

public class ScoreCard {

	
	public int numberRows;
	private int[] scoringCard;
	private boolean[] canPlaceScoreCard;
	private int scoredRowNumber;

    /**
    * Default constructor will create a game specific ScoreCard
	* by setting the numberOfRows and the arrays scoringCard (with 0s) and 
	* canPlaceScoreCard (with trues).
	* @param N/A
    * @returns N/A
    */
	public ScoreCard()
	{
		// 7 for bottom scorecard.  Die.numberOfSides for top
		numberRows = 7 + Die.numberOfSides;
		scoringCard = new int[numberRows];
		canPlaceScoreCard = new boolean[numberRows];
		
		for(int i = 0; i < numberRows; i++)
		{
			scoringCard[i] = 0;
			canPlaceScoreCard[i] = true;			
		}
	}
	
	/**
    * This method gets where the player would like to score her round at.
	* It will ask for a viable row.  Then it will place the value in the right 
	* location and update the boolean array canPlaceScoreCard at the scoredRowNumber.
	* Will output the number of points a player received for that round.
	* @param ArrayList<Die> has been filled with Dice. 
    * @returns N/A
	* @throws N/A
    */
	public void scoreCardTracker(ArrayList<Die> hand)
	{
		Scanner scoring = new Scanner(System.in);
		System.out.println("What row number would you like to score this round at? ");
		scoredRowNumber = scoring.nextInt();
		
		while(!canPlaceScoreCard[scoredRowNumber] && !isFull())
		{
			System.out.println("That row is filled. Please enter a different number ");
			scoredRowNumber = scoring.nextInt();
		}
		//Now we want to place the value!
		if((scoredRowNumber) >= Die.numberOfSides)
		{
			placeLower(hand);
		}
		else
		{
			placeUpper(hand);
		}
		canPlaceScoreCard[scoredRowNumber] = false;
	System.out.println("The rowValue is: " + scoringCard[scoredRowNumber]); 
	}
	
	/**
    * Places the value to be stored in the correct place in the Upper section
	* of the ScoreCard
	* @param ArrayList<Die> has been filled with Dice.  
    * @returns N/A
	* @throws N/A
    */
	private void placeUpper(ArrayList<Die> hand)
	{
		int dieValue = scoredRowNumber + 1;
		int rowValue;
		int currentCount = 0;
		for (int diePosition = 0; diePosition < Die.numberOfDie; diePosition++)
			{
				if (hand.get(diePosition).getValue() == dieValue)
					currentCount++;
			}
		rowValue = dieValue * currentCount;
		scoringCard[scoredRowNumber] = rowValue;
	}
	
	/**
    * Places the value to be stored in the correct place in the Lower section
	* of the ScoreCard
	* @param ArrayList<Die> has been filled with Dice.  
    * @returns N/A
	* @throws N/A
    */
	private void placeLower(ArrayList<Die> hand)
	{
		//if 3 of a kind Row
		if(scoredRowNumber == Die.numberOfSides) 
		{
			if (Calculations.maxOfAKindFound(hand) >= 3)
				scoringCard[scoredRowNumber] = Calculations.totalAllDice(hand);
			else
				scoringCard[scoredRowNumber] = 0;
		}
		
		//if 4 of a kind Row
		if(scoredRowNumber == Die.numberOfSides + 1)
		{
			if (Calculations.maxOfAKindFound(hand) >= 4)
				scoringCard[scoredRowNumber] = Calculations.totalAllDice(hand);
			else
				scoringCard[scoredRowNumber] = 0;
		}
		
		//if Full House Row
		if(scoredRowNumber == Die.numberOfSides + 2)
		{
			if(Calculations.fullHouseFound(hand))
				scoringCard[scoredRowNumber] = 25;
			else
				scoringCard[scoredRowNumber] = 0;
		}

		//if Small straight Row
		if(scoredRowNumber == Die.numberOfSides + 3)
		{
			if(Calculations.maxStraightFound(hand) >= 4)
				scoringCard[scoredRowNumber] = 30;
			else
				scoringCard[scoredRowNumber] = 0;
		}
		
		//if Large Straight Row
		if(scoredRowNumber == Die.numberOfSides + 4)
		{
			if(Calculations.maxStraightFound(hand) >= 5)
				scoringCard[scoredRowNumber] = 40;
			else
				scoringCard[scoredRowNumber] = 0;
		}
		
		//if Yahtzee Row
		if(scoredRowNumber == Die.numberOfSides + 5)
		{
			if(Calculations.maxOfAKindFound(hand) >= 5)
				scoringCard[scoredRowNumber] = 50;
			else
				scoringCard[scoredRowNumber] = 0;
		}
		
		//if Chance Row
		if(scoredRowNumber == Die.numberOfSides + 6)
		{
			scoringCard[scoredRowNumber] = Calculations.totalAllDice(hand);
		}
	}
	
	
	/**
    * prints the upper part of the scorecard and the values associated with
	* each choice depending on the hand and whether the rows are still available
	* for the player to place a value in
	* @param ArrayList<Die> has been filled with Dice.  
    * @returns N/A
	* @throws N/A
    */
	public void printUpperChoices(ArrayList<Die> hand)
	{
		//upper scorecard
		for (int dieValue = 1; dieValue <= Die.numberOfSides; dieValue++)
		{
			int currentCount = 0;
			for (int diePosition = 0; diePosition < Die.numberOfDie; diePosition++)
			{
				if (hand.get(diePosition).getValue() == dieValue)
					currentCount++;
			}
			if(canPrint(dieValue - 1))
			{
				System.out.print("Row " + (dieValue - 1) + ": " + "Score is ");
				System.out.print(dieValue * currentCount + " on the ");
				System.out.println(dieValue + " line");
			}
		}
		
	}
	
	/**
    * prints the lower part of the scorecard and the values associated with
	* each choice depending on the hand and whether the rows are still available
	* for the player to place a value in
	* @param ArrayList<Die> has been filled with Dice. Must be sorted to work 
    * @returns N/A
	* @throws N/A
    */
	public void printLowerChoices(ArrayList<Die> hand)
	{
		if(canPrint(Die.numberOfSides))
		{
			if (Calculations.maxOfAKindFound(hand) >= 3)
			{
				System.out.print("Row " + (Die.numberOfSides) + ": " + "Score " + Calculations.totalAllDice(hand) + " on the ");
				System.out.println("3 of a Kind line");
			}
			else 
				System.out.println("Row " + (Die.numberOfSides) + ": " + "Score 0 on the 3 of a Kind line");
		}
		
		if(canPrint(Die.numberOfSides + 1))
		{
			if (Calculations.maxOfAKindFound(hand) >= 4)
			{
				System.out.print("Row " + (Die.numberOfSides + 1) + ": " + "Score " + Calculations.totalAllDice(hand) + " on the ");
				System.out.println("4 of a Kind line");
			}
			else 
				System.out.println("Row " + (Die.numberOfSides + 1) + ": " + "Score 0 on the 4 of a Kind line");
		}
		
		if(canPrint(Die.numberOfSides + 2))
		{
			if (Calculations.fullHouseFound(hand))
				System.out.println("Row " + (Die.numberOfSides + 2) + ": " + "Score 25 on the Full House line");
			else
				System.out.println("Row " + (Die.numberOfSides + 2) + ": " + "Score 0 on the Full House line");
		}
		
		if(canPrint(Die.numberOfSides + 3))
		{
			if (Calculations.maxStraightFound(hand) >= 4)
				System.out.println("Row " + (Die.numberOfSides + 3) + ": " + "Score 30 on the Small Straight line");
			else
				System.out.println("Row " + (Die.numberOfSides + 3) + ": " + "Score 0 on the Small Straight line");
		}
		
		if(canPrint(Die.numberOfSides + 4))
		{
			if (Calculations.maxStraightFound(hand) >= 5)
				System.out.println("Row " + (Die.numberOfSides + 4) + ": " + "Score 40 on the Large Straight line");
			else
				System.out.println("Row " + (Die.numberOfSides + 4) + ": " + "Score 0 on the Large Straight line");
		}
		if(canPrint(Die.numberOfSides + 5))
		{
			if (Calculations.maxOfAKindFound(hand) >= 5)
				System.out.println("Row " + (Die.numberOfSides + 5) + ": " + "Score 50 on the Yahtzee line");
			else
				System.out.println("Row " + (Die.numberOfSides + 5) + ": " + "Score 0 on the Yahtzee line");
		}
		if(canPrint(Die.numberOfSides + 6))
		{
			System.out.print("Row " + (Die.numberOfSides + 6) + ": " + "Score " + Calculations.totalAllDice(hand) + " on the ");
			System.out.println("Chance line");
			System.out.println();
		}
		
	}
	
	/**
    * Checks to see if the ScoreCard is Full
	* @param N/A 
    * @returns true if scoreCard is true and false otherwise
	* @throws N/A
    */
	private boolean isFull()
	{
		boolean cardFull = true;
		for(int i = 0; i < numberRows; i++)
		{
			if(canPlaceScoreCard[i] == true)
				cardFull = false;
		}
		return cardFull;
	}

	/**
    * Checks to see if a place in the Scorecard is not filled.  Thus 
	* it can be printed as an option to the player
	* @param N/A  
    * @returns true if at canPlaceScoreCard[index] is true (is an option
	*          for the player to place a value). false otherwise
	* @throws N/A
    */
	private boolean canPrint(int index)
	{
		return canPlaceScoreCard[index];
	}
	
	/**
    * This method returns the total value of the scoreCard in play
	* @param N/A  
    * @returns The total value of the scoreCard
	* @throws N/A
    */
	public int totalValue()
	{
		int sum = 0;
		for(int i = 0; i < numberRows; i++)
		{
			sum = sum + scoringCard[i];
		}
		return sum;
	}
	
	/**
    * gets the number of rows in a scoreCard and returns that value
	* @param N/A
    * @returns int numberRows is the number of rows in a scoreCard.
	* @throws N/A
    */
	public int getNumberRows()
	{
		return numberRows;
	}
	
	/**
    * Displays the final Scorecard for a game of yahtzee
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void printCompleted()
	{
		//Upper
		for (int dieValue = 1; dieValue <= Die.numberOfSides; dieValue++)
		{
			System.out.print("Row " + (dieValue - 1) + ": " + "Score is ");
			System.out.print(scoringCard[(dieValue - 1)] + " on the ");
			System.out.println(dieValue + " line");
		}
		
		//Lower
		System.out.print("Row " + (Die.numberOfSides) + ": " + "Score " + scoringCard[Die.numberOfSides] + " on the ");
		System.out.println("3 of a Kind line");

		
		System.out.print("Row " + (Die.numberOfSides + 1) + ": " + "Score " + scoringCard[Die.numberOfSides + 1] + " on the ");
		System.out.println("4 of a Kind line");
		
		System.out.println("Row " + (Die.numberOfSides + 2) + ": " + "Score " + scoringCard[Die.numberOfSides + 2] + " on the Full House line");
		
		System.out.println("Row " + (Die.numberOfSides + 3) + ": " + "Score " + scoringCard[Die.numberOfSides + 3] + " on the Small Straight line");
		
		System.out.println("Row " + (Die.numberOfSides + 4) + ": " + "Score " + scoringCard[Die.numberOfSides + 4] + " on the Large Straight line");
		
		System.out.println("Row " + (Die.numberOfSides + 5) + ": " + "Score " + scoringCard[Die.numberOfSides + 5] + " on the Yahtzee line");
			
		System.out.print("Row " + (Die.numberOfSides + 6) + ": " + "Score " + scoringCard[Die.numberOfSides + 6] + " on the ");
		System.out.println("Chance line");
		System.out.println();		
	}
}