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
    private int faceValue;
    public static int numberOfSides = 15;
	public static int numberOfDie = 5;
	public static int numberOfRolls = 5;
	
	/**
    * 'rolls' the die by finding a random double between 0.0 and 1.0 -
	* Then multiplies that number by the number of sides + 1 and casts
	* to an integer
	* @param N/A
    * @returns N/A
	* @throws N/A
    */
	public void roll(){
        faceValue = (int)(Math.random() * numberOfSides + 1);
    }

	/**
    * returns value of die.  Die must be rolled first otherwise will output 
	* nonsense
	* @param N/A
    * @returns the int of the die's value. 
	* @throws N/A
    */
    public int getValue(){
        return faceValue;   
    }
	
	/**
    * sets the die's value to a different integer
	* @param takes in the integer that faceValue should become
    * @returns N/A
	* @throws N/A
    */
	public void setValue(int faceValue){
        this.faceValue = faceValue;   
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