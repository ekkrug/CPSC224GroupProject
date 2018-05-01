/**
 * This class will construct a Player that will keep track
 * of a player's score and where a player has scored something.
 * It can print out the options a player has for placing values 
 * in the scoreCard. An ArrayList of Dice and the Calculations 
 * class is used to do so
 * 
 * CPSC 224-02, Spring 2018
 * Group Project: Gonzaga Men's Basketball Yahtzee (Group 1)
 
 * @author Ben Bladow
 * @author Eugene Krug
 * @author Brandon Niblock
 * @version v9.0 4/28/18
 */

import java.util.*;
import java.io.*;

public class Player {

	
	public int numberRows;
	public int[] scoringCard;
	public boolean[] canPlaceScoreCard;
    public String[] athleteNames;
	public int scoredRowNumber;
    private String name;
    public ArrayList<Die> hand;
    private int bonusLine;
    
    /**
    * Default constructor will create a game specific ScoreCard
	* by setting the numberOfRows and the arrays scoringCard (with 0s) and 
	* canPlaceScoreCard (with trues).
	* @param N/A
    * @returns N/A
    */
	public Player()
	{   
        hand = new ArrayList<Die>(Die.numberOfDie);
		// 7 for bottom scorecard.  15 for top.
		numberRows = 22;
        bonusLine = 22;
		scoringCard = new int[numberRows];
		canPlaceScoreCard = new boolean[numberRows];
        athleteNames = new String[Die.numberOfSides];
		
		for(int i = 0; i < numberRows; i++)
		{
			scoringCard[i] = 0;
			canPlaceScoreCard[i] = true;			
		}
        athleteNames[0] = "Joel Ayayi";
        athleteNames[1] = "Jack Beach";
        athleteNames[2] = "Brandon Clarke";
        athleteNames[3] = "Rui Hachimura";
        athleteNames[4] = "Jeremy Jones";
        athleteNames[5] = "Corey Kispert";
        athleteNames[6] = "Jacob Larson";
        athleteNames[7] = "Alex Martin";
        athleteNames[8] = "Silas Melson";
        athleteNames[9] = "Zach Norvell Jr.";
        athleteNames[10] = "Josh Perkins";
        athleteNames[11] = "Brian Pete";
        athleteNames[12] = "Killian Tillie";
        athleteNames[13] = "Jesse Wade";
        athleteNames[14] = "Jonathan Williams";
	}
	
    /**
    * Sets the name for a player object.
	* @param String: the name of the player
    * @returns N/A
	* @throws N/A
    */
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	/**
    * This method gets where the player would like to score their round at.
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
		if((scoredRowNumber) >= Die.numberOfSides + 1) //Plus one bc of bonus
		{
			placeLower(hand);
		}
		else
		{
			placeUpper(hand, scoredRowNumber);
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
	public void placeUpper(ArrayList<Die> hand, int scoredRowNumber)
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
	public void placeLower(ArrayList<Die> hand)
	{
		//if 3 of a Zag Row
		if(scoredRowNumber == Die.numberOfSides) 
		{
			if (Calculations.maxOfAZagFound(hand) >= 3)
				scoringCard[scoredRowNumber] = Calculations.totalAllDice(hand);
			else
				scoringCard[scoredRowNumber] = 0;
		}
		
		//if 4 of a Zag Row
		if(scoredRowNumber == Die.numberOfSides + 1)
		{
			if (Calculations.maxOfAZagFound(hand) >= 4)
				scoringCard[scoredRowNumber] = Calculations.totalAllDice(hand);
			else
				scoringCard[scoredRowNumber] = 0;
		}
		
		//if Full Team Row
		if(scoredRowNumber == Die.numberOfSides + 2)
		{
			if(Calculations.fullTeamFound(hand))
				scoringCard[scoredRowNumber] = 25;
			else
				scoringCard[scoredRowNumber] = 0;
		}

		//if bench brigade row
		if(scoredRowNumber == Die.numberOfSides + 3)
		{
			if(Calculations.benchBrigadeFound(hand))
				scoringCard[scoredRowNumber] = 30;
			else
				scoringCard[scoredRowNumber] = 0;
		}
		
		//if starting lineup row
		if(scoredRowNumber == Die.numberOfSides + 4)
		{
			if(Calculations.startingLineupFound(hand))
				scoringCard[scoredRowNumber] = 40;
			else
				scoringCard[scoredRowNumber] = 0;
		}
		
		//if Zombie Nation Row
		if(scoredRowNumber == Die.numberOfSides + 5)
		{
			if(Calculations.maxOfAZagFound(hand) >= 5)
				scoringCard[scoredRowNumber] = 100;
			else
				scoringCard[scoredRowNumber] = 0;
		}
		
		//if the kennel Row
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
				System.out.println(athleteNames[dieValue - 1] + " line");
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
			if (Calculations.maxOfAZagFound(hand) >= 3)
			{
				System.out.print("Row " + (Die.numberOfSides) + ": " + "Score " + Calculations.totalAllDice(hand) + " on the ");
				System.out.println("3 of a Zag line");
			}
			else 
				System.out.println("Row " + (Die.numberOfSides) + ": " + "Score 0 on the 3 of a Zag line");
		}
		
		if(canPrint(Die.numberOfSides + 1))
		{
			if (Calculations.maxOfAZagFound(hand) >= 4)
			{
				System.out.print("Row " + (Die.numberOfSides + 1) + ": " + "Score " + Calculations.totalAllDice(hand) + " on the ");
				System.out.println("4 of a Zag line");
			}
			else 
				System.out.println("Row " + (Die.numberOfSides + 1) + ": " + "Score 0 on the 4 of a Zag line");
		}
		
		if(canPrint(Die.numberOfSides + 2))
		{
			if (Calculations.fullTeamFound(hand))
				System.out.println("Row " + (Die.numberOfSides + 2) + ": " + "Score 25 on the Full Team line");
			else
				System.out.println("Row " + (Die.numberOfSides + 2) + ": " + "Score 0 on the Full House line");
		}
		
		if(canPrint(Die.numberOfSides + 3))
		{
			if (Calculations.benchBrigadeFound(hand))
				System.out.println("Row " + (Die.numberOfSides + 3) + ": " + "Score 30 on the Bench Brigade line");
			else
				System.out.println("Row " + (Die.numberOfSides + 3) + ": " + "Score 0 on the Bench Brigade line");
		}
		
		if(canPrint(Die.numberOfSides + 4))
		{
			if (Calculations.startingLineupFound(hand))
				System.out.println("Row " + (Die.numberOfSides + 4) + ": " + "Score 40 on the Starters line");
			else
				System.out.println("Row " + (Die.numberOfSides + 4) + ": " + "Score 0 on the Starters line");
		}
		if(canPrint(Die.numberOfSides + 5))
		{
			if (Calculations.maxOfAZagFound(hand) >= 5)
				System.out.println("Row " + (Die.numberOfSides + 5) + ": " + "Score 100 on the Zombie Nation line");
			else
				System.out.println("Row " + (Die.numberOfSides + 5) + ": " + "Score 0 on the Zombie Nation line");
		}
		if(canPrint(Die.numberOfSides + 6))
		{
			System.out.print("Row " + (Die.numberOfSides + 6) + ": " + "Score " + Calculations.totalAllDice(hand) + " on the ");
			System.out.println("Kennel line");
			System.out.println();
		}
		
	}
	
	/**
    * Checks to see if the ScoreCard is Full
	* @param N/A 
    * @returns true if scoreCard is true and false otherwise
	* @throws N/A
    */
	public boolean isFull()
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
	public boolean canPrint(int index)
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
        
		return sum + sumBonus();
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
	public void printCompleted(ArrayList<Die> hand)
	{
		//Upper
		for (int dieValue = 1; dieValue <= Die.numberOfSides; dieValue++)
		{
			System.out.print("Row " + (dieValue - 1) + ": " + "Score is ");
			System.out.print(scoringCard[(dieValue - 1)] + " on the ");
			System.out.println(hand.get(dieValue - 1).getName() + " line");
		}
		
		//Lower
		System.out.print("Row " + (Die.numberOfSides) + ": " + "Score " + scoringCard[Die.numberOfSides] + " on the ");
		System.out.println("3 of a Zag line");

		
		System.out.print("Row " + (Die.numberOfSides + 1) + ": " + "Score " + scoringCard[Die.numberOfSides + 1] + " on the ");
		System.out.println("4 of a Zag line");
		
		System.out.println("Row " + (Die.numberOfSides + 2) + ": " + "Score " + scoringCard[Die.numberOfSides + 2] + " on the Full Team line");
		
		System.out.println("Row " + (Die.numberOfSides + 3) + ": " + "Score " + scoringCard[Die.numberOfSides + 3] + " on the Bench Brigade line");
		
		System.out.println("Row " + (Die.numberOfSides + 4) + ": " + "Score " + scoringCard[Die.numberOfSides + 4] + " on the Starters line");
		
		System.out.println("Row " + (Die.numberOfSides + 5) + ": " + "Score " + scoringCard[Die.numberOfSides + 5] + " on the Zombie Nation line");
			
		System.out.print("Row " + (Die.numberOfSides + 6) + ": " + "Score " + scoringCard[Die.numberOfSides + 6] + " on the ");
		System.out.println("Kennel line");
        
        System.out.println("Bonus Row " + bonusLine + ": Score is " + sumBonus());
        
		System.out.println();	
        
	}
    
    /**
    * returns a player's name. The name must be set first 
	* @param N/A
    * @returns the String of the player's name. 
	* @throws N/A
    */
    public String getName()
    {
        return name;
    }
    
    /**
    * returns a player's current Score.  Does not include bonus
	* @param N/A
    * @returns the int of player's total score
	* @throws N/A
    */
    public int getCurrentScore()
    {
        int sum = 0;
		for(int i = 0; i < numberRows; i++)
		{
            if(!canPlaceScoreCard[i]) //spot is filled.
            {
                sum = sum + scoringCard[i];
            }
		}
		return sum;
    }
    
    /**
    * Determines if bonus has been achieved. If so, return the bonus (35).
    * Else return 0.
	* @param N/A
    * @returns an integer value based on whether a bonus has been achieved. 
	* @throws N/A
    */
    private int sumBonus()
    {
        int sum = 0;
        int countUpper = 0;
        for (int i = 0; i < 15; i++) //Checking for values on upper scoreCard.
		{
			countUpper = countUpper + scoringCard[i];
		}
        
        if(countUpper >= 100)
        {
           sum = 35;
        }
        
        return sum;
    }
    
    /**
    * resets a the score card values for a player
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
    public void resetScoreCard()
    {
        for(int i = 0; i < numberRows; i++)
		{
			scoringCard[i] = 0;
			canPlaceScoreCard[i] = true;			
		}
    }
}









