package structures;

import java.util.ArrayList;

public class AVLNode<T, U> implements Comparable<T>{
	
    private AVLNode<T, U> left, right, father;
    private T statistic;
    private ArrayList<U> players;
    private int balance;

    public AVLNode(T stat, U aux) {
        statistic = stat;
        balance = 0;
        left = null;
        right = null;
        father = null;
        players = new ArrayList<>();
        players.add(aux);
    }

	public AVLNode<T, U> getLeft() {
		return left;
	}

	public void setLeft(AVLNode<T, U> l) {
		left = l;
	}

	public AVLNode<T, U> getRight() {
		return right;
	}

	public void setRight(AVLNode<T, U> r) {
		right = r;
	}

	public int getBf() {
		return balance;
	}

	public void setBf(int b) {
		balance = b;
	}

	public ArrayList<U> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<U> p) {
		players = p;
	}
	
	public void addPlayer(U p) {
		players.add(p);
	}

	public T getStatistic() {
		return statistic;
	}

	@Override
	public int compareTo(T o) {
		Double x = Double.parseDouble(o+"");
		Double y = Double.parseDouble(statistic+"");
		if(y>x) {
			return 1;
		} else if(x>y) {
			return -1;
		} else {
			return 0;
		}
	}

	public AVLNode<T, U> getFather() {
		return father;
	}

	public void setFather(AVLNode<T, U> father) {
		this.father = father;
	}
}
