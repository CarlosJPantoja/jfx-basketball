package structures;

import java.util.ArrayList;

public class AVLNode<T, U> {
	
    private AVLNode<T, U> left, right;
    private T statistic;
    private ArrayList<U> players;
    private int balance;

    public AVLNode(T stat) {
        statistic = stat;
        balance = 0;
        left = null;
        right = null;
        setPlayers(new ArrayList<>());
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

	public T getStatistic() {
		return statistic;
	}
}
