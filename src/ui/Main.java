package ui;

import structures.AVLTree;

public class Main {

	public static void main(String[] args) {
		Main m = new Main();
		m.menu();
	}

	private void menu() {
		AVLTree<Integer, String> tree = new AVLTree<>();
		tree.insertar(10);
		System.out.println(tree.getRoot().getStatistic()+"\n");
		tree.insertar(5);
		System.out.println(tree.getRoot().getStatistic());
		System.out.println(tree.getRoot().getLeft().getStatistic()+"\n");
		tree.insertar(13);
		System.out.println(tree.getRoot().getStatistic());
		System.out.println(tree.getRoot().getLeft().getStatistic()+" "+ tree.getRoot().getRight().getStatistic()+"\n");
		tree.insertar(1);
		tree.insertar(6); 
		tree.insertar(17);
		tree.insertar(16);
		//tree.preorden(tree.getRoot());
		System.out.println(tree.getRoot().getStatistic());
		System.out.println(tree.getRoot().getLeft().getStatistic() +" "+ tree.getRoot().getRight().getStatistic());
	}

}
