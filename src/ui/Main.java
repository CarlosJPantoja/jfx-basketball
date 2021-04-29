package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import structures.AVLTree;

public class Main {

	private static final String FILEIN = "data/data.csv";

	public static void main(String[] args) {
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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		AVLTree<Double, String> tree = new AVLTree<>();
		br.readLine();
		long length = 1;
		while(1<length) {
			String[] line = br.readLine().split(";");
			Double ts = Double.parseDouble(line[3]);
			
			
		}
		
		bw.write((System.currentTimeMillis()-time)+"\n");
		bw.close();
		br.close();
	}

}
