//package yahtzee;

// TODD GUSE IS COOL
/**
* This class will create a Die that can be rolled. It also 
* keeps track of the number of Dice in  play the number of sides
* all dice have and the number of rolls per turn.  Objects of 
* this class can be cloned.
* 
* CPSC 224-02, Spring 2018
* Programming Assignment #5
* @author Benjamin Bladow
* 
* @version v2.1 3/7/18
*/


public class Die implements Cloneable{
    private int jerseyNumber;
    public static int numberOfSides = 15;
	public static int numberOfDie = 5;
	public static int numberOfRolls = 5;
	private String position;
    private String status;
    private String name;
    
	/**
    * "Rolls" the die by finding a random double between 0.0 and 1.0.
	* That result is multiplied by the number of sides + 1 and is
    * casted to an integer. A series of conditions are checked, such that
    * the result of the roll is matched with its corresponding player
    * from the roster. In addition, the player is assigned his corresponding
    * position and status.
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void roll(){
        jerseyNumber = (int)(Math.random() * numberOfSides + 1);
        if(jerseyNumber == 3 || jerseyNumber == 4 || jerseyNumber == 5 || jerseyNumber == 7 || jerseyNumber == 13 || jerseyNumber == 15)
            position = "FORWARD";
        else
            position = "GUARD";
        if(jerseyNumber == 9 || jerseyNumber == 10 || jerseyNumber == 11 || jerseyNumber == 13 || jerseyNumber == 15)
            status = "STARTER";
        else
            status = "BENCH";
        if(jerseyNumber == 1)
            name = "Joel Ayayi";
        else if(jerseyNumber == 2)
            name = "Jack Beach";
        else if(jerseyNumber == 3)
            name = "Brandon Clarke";
        else if(jerseyNumber == 4)
            name = "Rui Hachimura";
        else if(jerseyNumber == 5)
            name = "Jeremy Jones";
        else if(jerseyNumber == 6)
            name = "Corey Kispert";
        else if(jerseyNumber == 7)
            name = "Jacob Larson";
        else if(jerseyNumber == 8)
            name = "Alex Martin";
        else if(jerseyNumber == 9)
            name = "Silas Melson";
        else if(jerseyNumber == 10)
            name = "Zach Norvell Jr.";
        else if(jerseyNumber == 11)
            name = "Josh Perkins";
        else if(jerseyNumber == 12)
            name = "Brian Pete";
        else if(jerseyNumber == 13)
            name = "Killian Tillie";
        else if(jerseyNumber == 14)
            name = "Jesse Wade";
        else
            name = "Jonathan Williams";
    }

	/**
    * returns value of die.  Die must be rolled first otherwise will output 
	* nonsense
	* @param N/A
    * @returns the int of the die's value. 
	* @throws N/A
    */
    public int getValue(){
        return jerseyNumber;   
    }
    
//***************************************NEEDS DOCUMENTATION**************************************\\

	public String getPosition(){
        return position;
    }
    
    public String getStatus(){
        return status;
    }
    
    public String getName(){
        return name;
    }
    
	/**
    * Allows for a new object to be created identical to the current Die
	* @param N/A
    * @returns N/A
	* @throws CloneNotSupportedException 
    */
	public Die clone() throws 
		CloneNotSupportedException{
		//call Object.clone
		Die cloned = (Die) super.clone();
		
		return cloned;
	}
}
