package strutures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import structures.ABBTree;

public class ABBTreeTest {
	
	private ABBTree<Double, String> avl;

	public void setupScenary1() {
		avl = new ABBTree<>();
	}
	
	public void setupScenary2() {
		avl = new ABBTree<>();
		avl.insert(1.0, "Uno");
		avl.insert(2.0, "Dos");
		avl.insert(3.0, "Tres");
	}
	
	@Test
	public void testInsert() {
		setupScenary1();
		assertNull(avl.getRoot());
		avl.insert(1.0, "Uno");
		assertNotNull(avl.getRoot());
		assertEquals(1.0, avl.getRoot().getStatistic());
		
		setupScenary2();
		assertNotNull(avl.getRoot());
		assertEquals(1.0, avl.getRoot().getStatistic());
		avl.insert(4.0, "Cuatro");
		avl.insert(5.0, "Cinco");
		assertEquals(3.0, avl.getRoot().getRight().getRight().getStatistic());
		
		setupScenary1();
		assertNull(avl.getRoot());
		avl.insert(1.0, "Uno");
		avl.insert(1.0, "Uno");
		avl.insert(1.0, "Uno");
		assertNotNull(avl.getRoot());
		assertNull(avl.getRoot().getLeft());
		assertNull(avl.getRoot().getRight());
		
		setupScenary2();
		avl.insert(1.0, "Uno");
		avl.insert(2.0, "Dos");
		avl.insert(3.0, "Tres");
		assertNull(avl.getRoot().getLeft());
		assertNull(avl.getRoot().getRight().getLeft());
	}
	
	@Test
	public void TestFilter() {
		ArrayList<String> list;
		
		setupScenary1();
		list = new ArrayList<>();
		list = avl.filter(avl.getRoot(), 2.0, list, -1, true);
		assertEquals(0, list.size());
		
		setupScenary2();
		list = new ArrayList<>();
		list = avl.filter(avl.getRoot(), 2.0, list, -1, true);
		assertEquals(2, list.size());
		
		setupScenary2();
		list = new ArrayList<>();
		list = avl.filter(avl.getRoot(), 2.0, list, -1, false);
		assertEquals(1, list.size());
		
		setupScenary2();
		list = new ArrayList<>();
		list = avl.filter(avl.getRoot(), 2.0, list, 0, false);
		assertEquals(1, list.size());
		
		setupScenary2();
		list = new ArrayList<>();
		list = avl.filter(avl.getRoot(), 2.0, list, 1, true);
		assertEquals(2, list.size());
		
		setupScenary2();
		list = new ArrayList<>();
		list = avl.filter(avl.getRoot(), 2.0, list, 1, false);
		assertEquals(1, list.size());
	}

}
