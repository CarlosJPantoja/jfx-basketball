package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import structures.ABBTree;
import structures.AVLNode;
import structures.AVLTree;

public class Controller {

	private ArrayList<ABBTree<Double, Player>> statsABB;
	private ArrayList<AVLTree<Double, Player>> stats;
	private ArrayList<ArrayList<Player>> top;
	private ArrayList<Player> list;

	private String[] headers, current;
	private String[] signs = {"<", "<=", "=", ">=", ">"};
	private int[] values = {-1, -1, 0, 1, 1};
	private int index;

	public Controller() {
		statsABB = new ArrayList<>();
		stats = new ArrayList<>();
		top = new ArrayList<>();
		list = new ArrayList<>();
		headers = new String[5];
		for(int i=0; i<5; i++) {
			statsABB.add(new ABBTree<>());
			stats.add(new AVLTree<>());
			top.add(new ArrayList<>());
		}
	}

	public void loadCSV(String URL) throws IOException {
		BufferedReader lector = new BufferedReader(new FileReader(URL));
		String[] data = lector.readLine().split(";");
		for(int i=0; i<5; i++)
			headers[i] = data[i+3];
		String line = lector.readLine();
		while(line!=null) {
			data = line.split(";");
			Integer age = Integer.parseInt(data[1]);
			Double[] stat = new Double[5];
			for(int i=0; i<5; i++)
				stat[i] = Double.parseDouble(data[i+3]);
			Player player = new Player(data[0], age, data[2], stat);
			for(int i=0; i<5; i++) {
				statsABB.get(i).insert(stat[i], player);
				stats.get(i).insert(stat[i], player);
			}
			line = lector.readLine();
		}
		lector.close();
	}
	
	public void top() {
		for(int i=0; i<5; i++)
			top(stats.get(i).getRoot(), top.get(i));
	}
	
	private void top(AVLNode<Double, Player> root, ArrayList<Player> top){
		if(root!=null){
			top(root.getRight(), top);
			for(int i=0; i<root.getPlayers().size(); i++) {
				if(top.size()>=5)
					return;
				top.add(root.getPlayers().get(i));
			}
			top(root.getLeft(), top);
		}
	}

	public void filter() {
		list = new ArrayList<>();
		AVLTree<Double, Player> tree = null;
		int n = -2;
		for(int i=0; i<5; i++) {
			if(headers[i].equals(current[0])) {	
				tree = stats.get(i);
				index = i;
			}
			if(signs[i].equals(current[1]))
				n = values[i];
		}
		Double data = Double.parseDouble(current[2]);
		list = tree.filter(tree.getRoot(), data, list, n, current[1].length()>1);
	}
	
	public void filterABB() {
		list = new ArrayList<>();
		ABBTree<Double, Player> tree = null;
		int n = -2;
		for(int i=0; i<5; i++) {
			if(headers[i].equals(current[0])) {	
				tree = statsABB.get(i);
				index = i;
			}
			if(signs[i].equals(current[1]))
				n = values[i];
		}
		Double data = Double.parseDouble(current[2]);
		list = tree.filter(tree.getRoot(), data, list, n, current[1].length()>1);
	}

	//Getters
	public ArrayList<ArrayList<Player>> getTop() {
		return top;
	}
	
	public ArrayList<Player> getList() {
		return list;
	}

	public String[] getHeaders() {
		return headers;
	}
	
	public String[] getCurrent() {
		return current;
	}
	
	public String[] getSigns() {
		return signs;
	}

	public int getIndex() {
		return index;
	}

	//Setters
	public void setCurrent(String[] c) {
		current = c;
	}
}