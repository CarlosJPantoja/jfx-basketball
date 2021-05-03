package structures;

import java.util.ArrayList;

public class AVLNode<T, U> implements Comparable<T>{
	
    private AVLNode<T, U> left, right, father;
    private T statistic;
    private ArrayList<U> players;
    private int balance;
    private int h;

    public AVLNode(T stat, U aux) {
        statistic = stat;
        balance = 0;
        left = null;
        right = null;
        players = new ArrayList<>();
        players.add(aux);
    }
    
    public int high() {
    	return high(this);
    }
    
    private int high(AVLNode<T, U> current) {
    	if(current == null) {
    		return -1;
    	}else if(current.getLeft()==null && current.getRight()==null) {
    		current.setH(0);
    		current.setBf(0);
    		return 0;
    	} else {
    		int left = high(current.getLeft());
    		int right = high(current.getRight());
    		int temp = Math.max(left, right)+1;
    		current.setH(temp);
    		current.setBf(right-left);
    		return temp;
    	}
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

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public AVLNode<T, U> getFather() {
		return father;
	}

	public void setFather(AVLNode<T, U> father) {
		this.father = father;
	}
}
