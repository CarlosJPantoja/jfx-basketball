package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ControllerTest {
	
	Controller driver;
	
	public void setupScenary1() {
		driver = new Controller();
	}
	
	public void setupScenary2() throws IOException {
		driver = new Controller();
		driver.loadCSV("data/data.csv");
	}

	@Test
	public void testLoadCSV() throws IOException {
		setupScenary1();
		driver.loadCSV("data/data.csv");
	}
	
	@Test
	public void testFilter() throws IOException {
		setupScenary2();
		String[] current = {"FTr", ">=", "0", "", "AVL Tree"};
		driver.setCurrent(current);
		driver.filter();
		assertEquals(17263, driver.getList().size());
		
	}
	
	@Test
	public void testFilterABB() throws IOException {
		setupScenary2();
		String[] current = {"FTr", ">=", "0", "", "ABB Tree"};
		driver.setCurrent(current);
		driver.filter();
		assertEquals(17263, driver.getList().size());
		
	}

}
