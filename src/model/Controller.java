package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import structures.AVLNode;
import structures.AVLTree;

public class Controller {
	
	private ArrayList<AVLTree<Double, Player>> stats = new ArrayList<AVLTree<Double, Player>>();
	private ArrayList<Player> topStat1, topStat2, topStat3, topStat4, topStat5;
	
	private String[] output;
	private String[] signs = {"<", "<=", "=", ">=", ">"};
	private int[] values = {-1, -1, 0, 1, 1};
	
	public ArrayList<Player> getTopStat1() {
		return topStat1;
	}
	
	

	public ArrayList<AVLTree<Double, Player>> getStats() {
		return stats;
	}



	public void setStats(ArrayList<AVLTree<Double, Player>> stats) {
		this.stats = stats;
	}



	public void setTopStat1(ArrayList<Player> topStat1) {
		this.topStat1 = topStat1;
	}
	
	

	public ArrayList<Player> getTopStat2() {
		return topStat2;
	}

	public void setTopStat2(ArrayList<Player> topStat2) {
		this.topStat2 = topStat2;
	}

	public ArrayList<Player> getTopStat3() {
		return topStat3;
	}

	public void setTopStat3(ArrayList<Player> topStat3) {
		this.topStat3 = topStat3;
	}

	public ArrayList<Player> getTopStat4() {
		return topStat4;
	}

	public void setTopStat4(ArrayList<Player> topStat4) {
		this.topStat4 = topStat4;
	}

	public ArrayList<Player> getTopStat5() {
		return topStat5;
	}

	public void setTopStat5(ArrayList<Player> topStat5) {
		this.topStat5 = topStat5;
	}
	
	public Controller() {
		topStat1 = new ArrayList<>();
		topStat2 = new ArrayList<>();
		topStat3 = new ArrayList<>();
		topStat4 = new ArrayList<>();
		topStat5 = new ArrayList<>();
	}
	
	public String[] loadCSV(String URL) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(URL));
		stats.add(new AVLTree<>());
		stats.add(new AVLTree<>());
		stats.add(new AVLTree<>());
		stats.add(new AVLTree<>());
		stats.add(new AVLTree<>());
		String[] out = br.readLine().split(";");
		String[] output = {out[3],out[4],out[5],out[6],out[7]};
		this.output = output;
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
				stats.get(0).insertar(ts, aux);
				stats.get(1).insertar(ftr, aux);
				stats.get(2).insertar(trb, aux);
				stats.get(3).insertar(ast, aux);
				stats.get(4).insertar(stl, aux);
			}
		} catch(Exception e) {
			
		}
		
		topFive(stats.get(0).getRoot(), topStat1);
		topFive(stats.get(1).getRoot(), topStat2);
		topFive(stats.get(2).getRoot(), topStat3);
		topFive(stats.get(3).getRoot(), topStat4);
		topFive(stats.get(4).getRoot(), topStat5);
		
		br.close();
		return output;
	}
	
	
	
	public String[] getOutput() {
		return output;
	}

	public void setOutput(String[] output) {
		this.output = output;
	}

	public void topFive(AVLNode<Double, Player> r, ArrayList<Player> top){
		if(r!=null){
			topFive(r.getRight(), top);
			for(int i=0; i<r.getPlayers().size(); i++) {
				
				if(top.size()>=5) {
					return;
				}
				top.add(r.getPlayers().get(i));
			}
			topFive(r.getLeft(), top);
		}
		
	}

	public String[] getSigns() {
		return signs;
	}

	public void setSigns(String[] signs) {
		this.signs = signs;
	}

	public int[] getValues() {
		return values;
	}

	public void setValues(int[] values) {
		this.values = values;
	}
}

//C:\Users\Carlos\OneDrive\Documentos\Eclipse IDE\Workspaces\eclipse-workspace\jfx-basketball\data\data.csv
