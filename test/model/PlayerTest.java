package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {
	
	Player temp;
	
	public void setupScenary1(){
		temp = null;
	}

	@Test
	public void testPlayer() {
		setupScenary1();
		assertNull(temp);
		Double[] stats = {4.0, 5.0, 6.0, 1.0, 0.0};
		temp = new Player("Carlos", 18, "ICESI", stats);
		assertEquals("Carlos", temp.getName());
		assertEquals(18, temp.getAge());
		assertEquals("ICESI", temp.getTeam());
		assertEquals(stats, temp.getStats());
	}

}
