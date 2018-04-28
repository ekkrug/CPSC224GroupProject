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
 * Group Project: Gonzaga Men's Basketball Yahtzee (Group 1)

 * @author Ben Bladow
 * @author Eugene Krug
 * @author Brandon Niblock
 * @version v9.0 4/28/18 
 */

import java.io.*;
import java.util.*;


public class yahtzee{
	public static void main(String[] args)
		throws FileNotFoundException{
		new YahtzeeGUI();
	}
}