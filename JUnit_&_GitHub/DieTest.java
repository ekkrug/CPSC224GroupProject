package yahtzee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DieTest {

	@Test
	void test() {
		Die testDie = new Die();
		testDie.roll();
		assertEquals(8,testDie.getValue(), 7);
		
        if(testDie.getValue() == 3 || testDie.getValue() == 4 || testDie.getValue() == 5 || testDie.getValue() == 7 || testDie.getValue() == 13 || testDie.getValue() == 15)
            assertTrue(testDie.getPosition() == "FORWARD");
        else
            assertTrue(testDie.getPosition() == "GUARD");
        if(testDie.getValue() == 9 || testDie.getValue() == 10 || testDie.getValue() == 11 || testDie.getValue() == 13 || testDie.getValue() == 15)
            assertTrue(testDie.getStatus() == "STARTER");
        else
            assertTrue(testDie.getStatus() == "BENCH");
		
		if(testDie.getValue() ==1) 
			assertTrue("Joel Ayayi" == testDie.getName());
		else if(testDie.getValue() == 2)
			assertTrue("Jack Beach" == testDie.getName());
	    else if(testDie.getValue()  == 3)
	    	assertTrue("Brandon Clarke" == testDie.getName());
	    else if(testDie.getValue()  == 4)
	    	assertTrue("Rui Hachimura" == testDie.getName());
	    else if(testDie.getValue()  == 5)
	    	assertTrue("Jeremy Jones" == testDie.getName());
	    else if(testDie.getValue()  == 6)
	    	assertTrue("Corey Kispert" == testDie.getName());
	    else if(testDie.getValue()  == 7)
	    	assertTrue("Jacob Larson" == testDie.getName());
	    else if(testDie.getValue()  == 8)
	    	assertTrue("Alex Martin" == testDie.getName());
	    else if(testDie.getValue()  == 9)
	    	assertTrue("Silas Melson" == testDie.getName());
	    else if(testDie.getValue()  == 10)
	    	assertTrue("Zach Norvell Jr." == testDie.getName());
	    else if(testDie.getValue()  == 11)
	    	assertTrue("Josh Perkins" == testDie.getName());
	    else if(testDie.getValue() == 12)
	    	assertTrue("Brian Pete" == testDie.getName());
	    else if(testDie.getValue() == 13)
	    	assertTrue("Killian Tillie" == testDie.getName());
	    else if(testDie.getValue() == 14)
	    	assertTrue("Jesse Wade" == testDie.getName());
	    else if(testDie.getValue() == 15)
	    	assertTrue("Jonathan Williams" == testDie.getName());
		
	}

}
