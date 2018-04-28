package yahtzee;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class CalculationsTest {

	@Test
	public void maxOfAZagFoundTest()
	{
		ArrayList<Die> hand = new ArrayList<Die>();
		
		assert(Calculations.maxOfAZagFound(hand) == 0);
		Die die0 = new Die();
		die0.jerseyNumber = 1;
		hand.add(0, die0);
		assert(Calculations.maxOfAZagFound(hand) == 1);
		
		Die die1 = new Die();
		die1.jerseyNumber = 1;
		hand.add(1, die1);
		assert(Calculations.maxOfAZagFound(hand) == 2);
	
		Die die2 = new Die();
		die2.jerseyNumber = 1;
		hand.add(2, die2);
		assert(Calculations.maxOfAZagFound(hand) == 3);
		
		Die die3 = new Die();
		die3.jerseyNumber = 1;
		hand.add(3, die3);
		assert(Calculations.maxOfAZagFound(hand) == 4);
		
		Die die4 = new Die();
		die4.jerseyNumber = 1;
		hand.add(4, die4);
		assert(Calculations.maxOfAZagFound(hand) == 5);
	}
	
	@Test
	public void totalAllDiceTest()
	{
		ArrayList<Die> hand = new ArrayList<Die>();
		
		assert(Calculations.totalAllDice(hand) == 0);
		Die die0 = new Die();
		die0.jerseyNumber = 1;
		hand.add(0, die0);
		assert(Calculations.totalAllDice(hand) == 1);
		
		Die die1 = new Die();
		die1.jerseyNumber = 5;
		hand.add(1, die1);
		assert(Calculations.totalAllDice(hand) == 6);
	
		Die die2 = new Die();
		die2.jerseyNumber = 10;
		hand.add(2, die2);
		assert(Calculations.totalAllDice(hand) == 16);
		
		Die die3 = new Die();
		die3.jerseyNumber = 14;
		hand.add(3, die3);
		assert(Calculations.totalAllDice(hand) == 30);
		
		Die die4 = new Die();
		die4.jerseyNumber = 1;
		hand.add(4, die4);
		assert(Calculations.totalAllDice(hand) == 31);
	}
	
	@Test
	public void fullTeamFoundTest()
	{
		ArrayList<Die> hand = new ArrayList<Die>();
		
		assert(Calculations.fullTeamFound(hand) == false);
		Die die0 = new Die();
		die0.jerseyNumber = 3;
		rollDie(die0);
		hand.add(0, die0);
		assert(Calculations.fullTeamFound(hand) == false);
		
		Die die1 = new Die();
		die1.jerseyNumber = 4;
		rollDie(die1);
		hand.add(1, die1);
		assert(Calculations.fullTeamFound(hand) == false);
	
		Die die2 = new Die();
		die2.jerseyNumber = 10;
		rollDie(die2);
		hand.add(2, die2);
		assert(Calculations.fullTeamFound(hand) == false);
		
		Die die3 = new Die();
		die3.jerseyNumber = 9;
		rollDie(die3);
		hand.add(3, die3);
		assert(Calculations.fullTeamFound(hand) == false);
		
		Die die4 = new Die();
		die4.jerseyNumber = 5;
		rollDie(die4);
		hand.add(4, die4);
		assert(Calculations.fullTeamFound(hand) == false);
		
		die4.jerseyNumber = 11;
		rollDie(die4);
		hand.set(4, die4);
		assert(Calculations.fullTeamFound(hand) == true);
	}
	
	public void benchBrigadeFoundTest()
	{
		ArrayList<Die> hand = new ArrayList<Die>();
		
		assert(Calculations.benchBrigadeFound(hand) == false);
		Die die0 = new Die();
		die0.jerseyNumber = 1;
		rollDie(die0);
		hand.add(0, die0);
		assert(Calculations.benchBrigadeFound(hand) == false);
		
		Die die1 = new Die();
		die1.jerseyNumber = 2;
		rollDie(die1);
		hand.add(1, die1);
		assert(Calculations.benchBrigadeFound(hand) == false);
	
		Die die2 = new Die();
		die2.jerseyNumber = 3;
		rollDie(die2);
		hand.add(2, die2);
		assert(Calculations.benchBrigadeFound(hand) == false);
		
		Die die3 = new Die();
		die3.jerseyNumber = 4;
		rollDie(die3);
		hand.add(3, die3);
		assert(Calculations.benchBrigadeFound(hand) == false);
		
		Die die4 = new Die();
		die4.jerseyNumber = 10;
		rollDie(die4);
		hand.add(4, die4);
		assert(Calculations.benchBrigadeFound(hand) == false);
		
		die4.jerseyNumber = 5;
		rollDie(die4);
		hand.set(4, die4);
		assert(Calculations.benchBrigadeFound(hand) == true);		
	}
	
	public void startingLineupFoundTest()
	{
		ArrayList<Die> hand = new ArrayList<Die>();
		
		assert(Calculations.benchBrigadeFound(hand) == false);
		Die die0 = new Die();
		die0.jerseyNumber = 9;
		rollDie(die0);
		hand.add(0, die0);
		assert(Calculations.startingLineupFound(hand) == false);
		
		Die die1 = new Die();
		die1.jerseyNumber = 10;
		rollDie(die1);
		hand.add(1, die1);
		assert(Calculations.startingLineupFound(hand) == false);
	
		Die die2 = new Die();
		die2.jerseyNumber = 11;
		rollDie(die2);
		hand.add(2, die2);
		assert(Calculations.startingLineupFound(hand) == false);
		
		Die die3 = new Die();
		die3.jerseyNumber = 13;
		rollDie(die3);
		hand.add(3, die3);
		assert(Calculations.startingLineupFound(hand) == false);
		
		Die die4 = new Die();
		die4.jerseyNumber = 8;
		rollDie(die4);
		hand.add(4, die4);
		assert(Calculations.startingLineupFound(hand) == false);
		
		die4.jerseyNumber = 15;
		rollDie(die4);
		hand.set(4, die4);
		assert(Calculations.startingLineupFound(hand) == true);	
	}
	
	public static Die rollDie()
	{
		Die newDie = new Die();
		newDie.roll();
		return newDie;
	}
	
	public void rollDie(Die die){
        if(die.jerseyNumber == 3 || die.jerseyNumber == 4 || die.jerseyNumber == 5 || die.jerseyNumber == 7 || die.jerseyNumber == 13 || die.jerseyNumber == 15)
        	die.position = "FORWARD";
        else
        	die.position = "GUARD";
        if(die.jerseyNumber == 9 || die.jerseyNumber == 10 || die.jerseyNumber == 11 || die.jerseyNumber == 13 || die.jerseyNumber == 15)
        	die.status = "STARTER";
        else
        	die.status = "BENCH";
        if(die.jerseyNumber == 1)
        	die.name = "Ayayi";
        else if(die.jerseyNumber == 2)
        	die.name = "Beach";
        else if(die.jerseyNumber == 3)
        	die.name = "Clarke";
        else if(die.jerseyNumber == 4)
        	die.name = "Hachimura";
        else if(die.jerseyNumber == 5)
        	die.name = "Jones";
        else if(die.jerseyNumber == 6)
        	die.name = "Kispert";
        else if(die.jerseyNumber == 7)
        	die.name = "Larsen";
        else if(die.jerseyNumber == 8)
        	die.name = "Martin";
        else if(die.jerseyNumber == 9)
        	die.name = "Melson";
        else if(die.jerseyNumber == 10)
        	die.name = "Norvell";
        else if(die.jerseyNumber == 11)
        	die.name = "Perkins";
        else if(die.jerseyNumber == 12)
        	die.name = "Pete";
        else if(die.jerseyNumber == 13)
        	die.name = "Tillie";
        else if(die.jerseyNumber == 14)
        	die.name = "Wade";
        else
        	die.name = "Williams";
    }
	
}
