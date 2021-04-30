package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.Player;
import structures.AVLTree;

public class Main {

	private static final String FILEIN = "data/data.csv";

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.menu();
	}

	private void menu() throws IOException {
		/*AVLTree<Integer, String> tree = new AVLTree<>();
		tree.insertar(10);
		System.out.println(tree.getRoot().getStatistic()+"\n");
		tree.insertar(5);
		System.out.println(tree.getRoot().getStatistic());
		System.out.println(tree.getRoot().getLeft().getStatistic()+"\n");
		tree.insertar(13);
		System.out.println(tree.getRoot().getStatistic());
		System.out.println(tree.getRoot().getLeft().getStatistic()+" "+ tree.getRoot().getRight().getStatistic()+"\n");
		tree.insertar(1);
		System.out.println(tree.getRoot().getStatistic());
		System.out.println(tree.getRoot().getLeft().getStatistic()+" "+ tree.getRoot().getRight().getStatistic());
		System.out.println(tree.getRoot().getLeft().getLeft().getStatistic()+"\n");
		tree.insertar(6); 
		System.out.println(tree.getRoot().getStatistic());
		System.out.println(tree.getRoot().getLeft().getStatistic()+" "+ tree.getRoot().getRight().getStatistic());
		System.out.println(tree.getRoot().getLeft().getLeft().getStatistic()+" "+tree.getRoot().getLeft().getRight().getStatistic()+"\n");
		tree.insertar(17);
		System.out.println(tree.getRoot().getStatistic());
		System.out.println(tree.getRoot().getLeft().getStatistic()+" "+ tree.getRoot().getRight().getStatistic());
		System.out.println(tree.getRoot().getLeft().getLeft().getStatistic()+" "+tree.getRoot().getLeft().getRight().getStatistic()+" null "+tree.getRoot().getRight().getRight().getStatistic()+"\n");
		tree.insertar(16);
		System.out.println(tree.getRoot().getStatistic());
		System.out.println(tree.getRoot().getLeft().getStatistic()+" "+ tree.getRoot().getRight().getStatistic());
		System.out.println(tree.getRoot().getLeft().getLeft().getStatistic()+" "+tree.getRoot().getLeft().getRight().getStatistic()+" "+tree.getRoot().getRight().getLeft().getStatistic()+" "+tree.getRoot().getRight().getRight().getStatistic()+"\n");
		*/
		long time = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new FileReader(FILEIN));
		AVLTree<Double, Player> tsTree = new AVLTree<>();
		AVLTree<Double, Player> ftrTree = new AVLTree<>();
		AVLTree<Double, Player> trbTree = new AVLTree<>();
		AVLTree<Double, Player> astTree = new AVLTree<>();
		AVLTree<Double, Player> stlTree = new AVLTree<>();
		br.readLine();
		try {
			while(true) {
				String[] line = br.readLine().split(";");
				String name = line[0];
				Integer age = Integer.parseInt(line[1]);
				String team = line[2];
				Double ts = Double.parseDouble(line[3]);
				Double ftr = Double.parseDouble(line[4]);
				Double trb = Double.parseDouble(line[5]);
				Double ast = Double.parseDouble(line[6]);
				Double stl = Double.parseDouble(line[7]);
				Player aux = new Player(name, age, team, ts, ftr, trb, ast, stl);
				tsTree.insertar(ts, aux);
				ftrTree.insertar(ftr, aux);
				trbTree.insertar(trb, aux);
				astTree.insertar(ast, aux);
				stlTree.insertar(stl, aux);
			}
		} catch(Exception e) {
			
		}
		System.out.println("\n"+(System.currentTimeMillis()-time)+"\n");
		
		br.close();
	}
}