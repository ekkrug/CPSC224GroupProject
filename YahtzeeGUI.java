/**
* This program will implement the game Lizard Spock Yahtzee.
* It uses static methods from the classes Calculations and has an 
* instance ofScorecard to record the score for a certain roll.  It 
* stores objects of class Die in an ArrayList to allow for more 
* flexibility in coding.  This program will also allow users to 
* change the number of Die, number of Die sides, and number of rolls
* allowed and store those numbers in a .txt file.  After each 
* completed rolling sequence it will display the rows that are available 
* to place the value of the roll at.
*
* CPSC 224-02, Spring 2018
* Programming Assignment #5
* @author Benjamin Bladow -->converted code to java using Object Oriented Design
* @author Bruce Worobeck --> coded all of the c++ functions and yahtzee gameplay
* 
* @version v3.0 3/7/18
*/


import java.io.*;
import java.util.*;


public class yahtzee{

    /**
     * Main has a nested loop where the yahtzee Dice are rolled and manipulated
	 * It gets input from either a .txt file or from the user.  If input from the 
	 * user is included the .txt is overwritten with those values. It has two 
	 * scanners.  One to keep track if the user wants to continue playing and one 
	 * to keep track of the dice to roll. Then will output the potential 
	 * scores for the scoreCard
     * @param args the command line arguments
	 * @throws FileNotFoundException
     */
	public static void main(String[] args)
		throws FileNotFoundException{
		new YahtzeeGUI();
		Scanner scannerKeep = new Scanner(System.in);
		Scanner continuePlay = new Scanner(System.in);
        
        int numberOfPlayers = getNumberPlayers();
        String[] playerNames = new String[numberOfPlayers];
        playerNames = getPlayerNames(numberOfPlayers, playerNames);
        
		char playAgain = 'y';
        
		printRules();
        
//***************MAKING INTO AN ARRAY****************8
		Player[] players = new Player[numberOfPlayers];
        
        for(int i = 0; i < numberOfPlayers; i++)
        {
            players[i] = new Player();
            players[i].name = playerNames[i];
        }  
//******************************************8     
        int numberOfTurns = players[0].getNumberRows();
        
        
		while (playAgain == 'y')
		{
			for(int j = 0; j < numberOfTurns; j++)
			{
				for(int i = 0; i < numberOfPlayers; i++)
                {
                    System.out.println();
                    System.out.println("It is turn: " + (j+1) + " for player: " + players[i].name);
                    
                    char[] keep = keepString(Die.numberOfDie, "n").toCharArray(); //setup to roll all dice in the first roll
                    int turn = 1;
                    while (turn < (Die.numberOfRolls + 1) && !(Arrays.equals(keep, keepString(Die.numberOfDie, "y").toCharArray())))
                    {
                        //roll dice not kept
                        for (int dieNumber = 0; dieNumber < Die.numberOfDie; dieNumber++)
                        {
                            if (keep[dieNumber] != 'y')
                            {
                                if(turn == 1){
                                    players[i].hand.add(dieNumber, rollDie());
                                }
                                else{
                                    players[i].hand.set(dieNumber, rollDie());
                                }
                            }
                        }

                        //output roll
                        System.out.print("Roll " + turn + " was: ");
                        for (int dieNumber = 0; dieNumber < Die.numberOfDie; dieNumber++)
                        {
                            System.out.print(players[i].hand.get(dieNumber).getValue() + " ");
                        }
                        
                        System.out.println();
                        //if not the last roll of the hand prompt the user for dice to keep
                        if (turn < (Die.numberOfRolls))
                        {
                            System.out.print("enter dice to keep (y or n) ");
                            keep = scannerKeep.nextLine().toCharArray();
                        }
                        turn++;
                    }

                    //start scoring
                    //hand need to be sorted to check for straights
                    sortArray(players[i].hand, Die.numberOfDie);
                    System.out.print("Here is your sorted hand : ");
                    for (int dieNumber = 0; dieNumber < Die.numberOfDie; dieNumber++)
                    {
                        System.out.print(players[i].hand.get(dieNumber).getValue() + " ");
                    }
                    System.out.println();
                                
                    //upper ScoreCard choices
                    players[i].printUpperChoices(players[i].hand);
                    
                    //lower scorecard
                    players[i].printLowerChoices(players[i].hand);
                    
                    
                    players[i].scoreCardTracker(players[i].hand);
                    System.out.println();
                    
                    System.out.println("This is " + players[i].name + "'s current score: " + players[i].getCurrentScore());
                }
			}
            
            for(int i = 0; i < numberOfPlayers; i++)
            {
                System.out.println(players[i].name + " scored " + players[i].totalValue() + " for this round");
                players[i].printCompleted(players[i].hand);
            }
			
			System.out.print("Enter 'y' to play again ");
			playAgain = continuePlay.next().charAt(0);
            
            checkLeaderboard(players, numberOfPlayers);  //Gawwww
            
            if(playAgain == 'y')
            {
                for(int i = 0; i < numberOfPlayers; i++)
                {
                    players[i].resetScoreCard();
                }            
            }
		}
	}
	
	/**
    * this method simulates the rolling of a single die
	* @param N/A
    * @returns an object of class Die
	* @throws N/A
    */
	public static Die rollDie()
	{
		Die  newDie = new Die();
		newDie.roll();
		return newDie;
	}
	
	/**
    * This method will sort an ArrayList of Die using the bubble sort 
	* algorithm
	* @param ArrayList<Die> --> must take in an ArrayList of type Die.
	*		 all of the dice must have their faceValue's set to a value
	* @param int size is the size of the ArrayList
    * @returns N/A
	* @throws N/A
    */
	public static void sortArray(ArrayList<Die> hand, int size)
	{
	   boolean swap;
	   Die temp = new Die();

	   do
	   {
		  swap = false;
		  for (int count = 0; count < (size - 1); count++)
		  {
			 if (hand.get(count).getValue() > hand.get(count + 1).getValue())
			 {
				try{
					temp = hand.get(count).clone();		// temp = array[count]
				}
				catch(CloneNotSupportedException e){
					e.printStackTrace();
				}
					
				hand.set(count, hand.get(count + 1)); 		// array[count] = array[count + 1]
				hand.set(count + 1, temp);
				swap = true;
			 }
		  }
	   } while (swap);
	}
	
	/**
    * This method will return a String full of multiples of a single String
	* In the context of this program, the single String only has a single character
	* stored in it.
	* @param int numberOfDie is the number of Die in play for a game.  
	* @param String initializeVal is the single character string, should be 
	*        either 'n' or 'y'
    * @returns a String keeps that has only multiples of a single character
	*		   Should be multiples of either 'n' or 'y'
	* @throws N/A
    */
	public static String keepString(int numberOfDie, String initializeVal)
	{
		String keeps = "";
		for(int i = 0; i < numberOfDie; i++)
		{
			keeps = keeps + initializeVal;
		}
		return keeps;
	}
	
    /**
    * This method sets the values Die.numberOfSides/numberOfDie/numberOfRolls
	* based on numbers stored in "yahtzeeConfig.txt".  It then asks the user if
	* he wants to change them and if so, will change the values in the class 
	* Die and then overwrite "yahtzeeConfig.txt" with the values.
	* @param N/A
    * @returns N/A
	* @throws FileNotFoundException if "yahtzeeConfig.txt" cannot be found
    */
	public static int getNumberPlayers()
    {
		
		Scanner configuration = new Scanner(System.in);
		
        //Getting values from "yahtzeeConfig.txt"
		int numberOfPlayers = 1;
		
		char changeConfig = 'y';
		
		System.out.println("There are  " + numberOfPlayers + " " + " people playing");
		System.out.print("enter 'y' if you would like to change the number of players ");
		changeConfig = configuration.next().charAt(0);

		//Getting User Input
		while(changeConfig == 'y')
		{
			System.out.print("how many people are playing this game? ");
			numberOfPlayers = configuration.nextInt();
            
            System.out.print("enter 'y' if you would like to change the number of players ");
            changeConfig = configuration.next().charAt(0);
            System.out.println();
		}
        return numberOfPlayers;
    }
    
    public static String[] getPlayerNames(int numberOfPlayers, String[] playerNames)
    {
        Scanner names = new Scanner(System.in);
        
        for(int i = 0; i < numberOfPlayers; i++)
        {
            System.out.print("What is player number " + (i+1) + "'s name? ");
            playerNames[i] = names.next();
        }
        
        return playerNames;
    }
    
    public static void checkLeaderboard(Player[] players, int numberOfPlayers)
        throws FileNotFoundException{
        //1. Get numbers and names
        //2. Put at end of leaderboard
        //3. Sort leaderboard
        //4. Put in correct order
        //5. Print leaderboard 
        int numberOfLeaders;
        int iterator;
        int nextInt;
        String nextName;
        
        Map<Integer, String> gameScoreNames = new HashMap<Integer, String>();
        for(int i = 0; i < numberOfPlayers; i++)
        {
            gameScoreNames.put(players[i].totalValue(), players[i].name);
        }
       
		Scanner inputLeader = new Scanner(new File("leaderboard.txt"));
        numberOfLeaders = inputLeader.nextInt();
        iterator = numberOfLeaders;
        
        if(numberOfLeaders > 3) //Reset numberOfLeaders
        {
            numberOfLeaders = 0;
        }
        numberOfLeaders = numberOfLeaders + numberOfPlayers;
        
		while(iterator > 0)
        {
            nextInt = inputLeader.nextInt();
            nextName = inputLeader.nextLine();  //next?
            gameScoreNames.put(nextInt, nextName);
            
            iterator--;
        }    
        
		//SORT
        Map<Integer, String> sorted = new TreeMap<Integer, String>(gameScoreNames);
        Map<Integer,String> sortedLeaders = new TreeMap<Integer, String>(Collections.reverseOrder());
        for(Map.Entry<Integer, String> entry : sorted.entrySet()) {
        	sortedLeaders.put(entry.getKey(), entry.getValue());
        }
        
		PrintWriter writer = new PrintWriter("leaderboard.txt");
		
		//Overwriting values in "yahtzeeConfig.txt"
		writer.print("");
		writer.println(numberOfLeaders);
        
        for(Map.Entry<Integer, String> entry : sortedLeaders.entrySet())
        {
            writer.print(entry.getKey());
            writer.print(" ");
            writer.println(entry.getValue());
        }
        
        writer.close();
        
        int i = 1;
        for(Map.Entry<Integer, String> entry : sortedLeaders.entrySet())
        {
            System.out.print("This is place: " + i + " for player: " + entry.getValue());
            System.out.print(" total score is: ");
            System.out.println(entry.getKey());
            i++;
        }
    }
	

	public static void printRules(){
		System.out.println();
		System.out.println();
		System.out.println("Athlete's name|Yahtzee Number|Athlete's Position|Athlete's Status");
		System.out.println("Joel Ayayi    |       1      |      Guard       |      Bench     ");
		System.out.println("Jack Beach    |       2      |      Guard       |      Bench     ");
		System.out.println("Brandon Clarke|       3      |      Forward     |      Bench     ");
		System.out.println("Rui Hachimura |       4      |      Forward     |      Bench     ");
		System.out.println("Jeremy Jones  |       5      |      Forward     |      Bench     ");
		System.out.println("Corey Kispert |       6      |      Guard       |      Bench     ");
		System.out.println("Jacob Larsen  |       7      |      Forward     |      Bench     ");
		System.out.println("Alex Martin   |       8      |      Guard       |      Bench     ");
		System.out.println("Silas Melson  |       9      |      Guard       |      Starter   ");
		System.out.println("Zach Norvell  |       10     |      Guard       |      Starter   ");
		System.out.println("Josh Perkins  |       11     |      Guard       |      Starter   ");
		System.out.println("Brian Pete    |       12     |      Guard       |      Bench     ");
		System.out.println("Killian Tillie|       13     |      Forward     |      Starter   ");
		System.out.println("Jesse Wade    |       14     |      Guard       |      Bench     ");
		System.out.println("John Williams |       15     |      Forward     |      Starter   ");
		System.out.println();
		System.out.println();
	}
}