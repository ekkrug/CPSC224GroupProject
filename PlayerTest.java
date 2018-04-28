package yahtzee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

	@Test
	public void testPlayersetup() {
		Player testPlayer = new Player();
			
		Assert.assertNotNull(testPlayer.hand);
		assertEquals(testPlayer.numberRows, 22);
		assertEquals(testPlayer.bonusLine, 22);
		assertEquals(testPlayer.scoringCard.length, testPlayer.numberRows);
		assertEquals(testPlayer.canPlaceScoreCard.length, testPlayer.numberRows);
		assertEquals(testPlayer.athleteNames.length, Die.numberOfSides);
		
			
		for(int i = 0; i < testPlayer.numberRows; i++)
		{
			assert(testPlayer.scoringCard[i] == 0);
			assert(testPlayer.canPlaceScoreCard[i] == true);			
		}
		
	    assert(testPlayer.athleteNames[0] == "Joel Ayayi");
	    assert(testPlayer.athleteNames[1] == "Jack Beach");
	    assert(testPlayer.athleteNames[2] == "Brandon Clarke");
	    assert(testPlayer.athleteNames[3] == "Rui Hachimura");
	    assert(testPlayer.athleteNames[4] == "Jeremy Jones");
	    assert(testPlayer.athleteNames[5] == "Corey Kispert");
	    assert(testPlayer.athleteNames[6] == "Jacob Larson");
	    assert(testPlayer.athleteNames[7] == "Alex Martin");
	    assert(testPlayer.athleteNames[8] == "Silas Melson");
	    assert(testPlayer.athleteNames[9] == "Zach Norvell Jr.");
	    assert(testPlayer.athleteNames[10] == "Josh Perkins");
	    assert(testPlayer.athleteNames[11] == "Brian Pete");
	    assert(testPlayer.athleteNames[12] == "Killian Tillie");
	    assert(testPlayer.athleteNames[13] == "Jesse Wade");
	    assert(testPlayer.athleteNames[14] == "Jonathan Williams");
	}
	@Test
	public void testPlayerName() {
		Player testPlayer = new Player();
		testPlayer.setName("bob");
		assert("bob" == testPlayer.getName());
	}
	
	@Test
	public void canPrintTest() {
		Player testPlayer = new Player();

		assert(testPlayer.canPrint(5) == true);
		
		testPlayer.scoringCard[5] = 50;
		testPlayer.canPlaceScoreCard[5] = false;
		
		assert(testPlayer.canPrint(5) == false);
	}
	
	@Test
	public void totalValueTest() {
		Player testPlayer = new Player();

		assert(testPlayer.totalValue() == 0);
		testPlayer.scoringCard[0] = 10;
		testPlayer.canPlaceScoreCard[0] = false;
		assert(testPlayer.totalValue() == 10);
		
		testPlayer.scoringCard[1] = 10;
		testPlayer.canPlaceScoreCard[1] = false;
		assert(testPlayer.totalValue() == 20);
		
		testPlayer.scoringCard[2] = 10;
		testPlayer.canPlaceScoreCard[2] = false;
		assert(testPlayer.totalValue() == 30);
		
		testPlayer.scoringCard[3] = 10;
		testPlayer.canPlaceScoreCard[3] = false;
		assert(testPlayer.totalValue() == 40);
		
		testPlayer.scoringCard[4] = 10;
		testPlayer.canPlaceScoreCard[4] = false;
		assert(testPlayer.totalValue() == 50);
		
		testPlayer.scoringCard[5] = 10;
		testPlayer.canPlaceScoreCard[5] = false;
		assert(testPlayer.totalValue() == 60);
		
		testPlayer.scoringCard[6] = 10;
		testPlayer.canPlaceScoreCard[6] = false;
		assert(testPlayer.totalValue() == 70);
		
		testPlayer.scoringCard[7] = 10;
		testPlayer.canPlaceScoreCard[7] = false;
		assert(testPlayer.totalValue() == 80);
		
		testPlayer.scoringCard[8] = 10;
		testPlayer.canPlaceScoreCard[8] = false;
		assert(testPlayer.totalValue() == 90);
		
		testPlayer.scoringCard[9] = 10;
		testPlayer.canPlaceScoreCard[9] = false;
		assert(testPlayer.sumBonus() == 35);
		assert(testPlayer.totalValue() == 135);
	}
	@Test
	public void getCurrentScoreTest() {
		Player testPlayer = new Player();

		assert(testPlayer.getCurrentScore() == 0);
		testPlayer.scoringCard[0] = 10;
		testPlayer.canPlaceScoreCard[0] = false;
		assert(testPlayer.getCurrentScore() == 10);
		
		testPlayer.scoringCard[1] = 10;
		testPlayer.canPlaceScoreCard[1] = false;
		assert(testPlayer.getCurrentScore() == 20);
		
		testPlayer.scoringCard[2] = 10;
		testPlayer.canPlaceScoreCard[2] = false;
		assert(testPlayer.getCurrentScore() == 30);
		
		testPlayer.scoringCard[3] = 10;
		testPlayer.canPlaceScoreCard[3] = false;
		assert(testPlayer.getCurrentScore() == 40);
		
		testPlayer.scoringCard[4] = 10;
		testPlayer.canPlaceScoreCard[4] = false;
		assert(testPlayer.getCurrentScore() == 50);
		
		testPlayer.scoringCard[5] = 10;
		testPlayer.canPlaceScoreCard[5] = false;
		assert(testPlayer.getCurrentScore() == 60);
		
		testPlayer.scoringCard[6] = 10;
		testPlayer.canPlaceScoreCard[6] = false;
		assert(testPlayer.getCurrentScore() == 70);
		
		testPlayer.scoringCard[7] = 10;
		testPlayer.canPlaceScoreCard[7] = false;
		assert(testPlayer.getCurrentScore() == 80);
		
		testPlayer.scoringCard[8] = 10;
		testPlayer.canPlaceScoreCard[8] = false;
		assert(testPlayer.getCurrentScore() == 90);
		
		testPlayer.scoringCard[9] = 10;
		testPlayer.canPlaceScoreCard[9] = false;
		//Bonus not included until the very end
		assert(testPlayer.getCurrentScore() == 100);
	}
}
