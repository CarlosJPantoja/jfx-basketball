package structures;

import java.util.ArrayList;

public class ABBNode<T, U> implements Comparable<T>{
	
    private ABBNode<T, U> left, right;
    private T statistic;
    private ArrayList<U> players;

    public ABBNode(T stat, U aux) {
        statistic = stat;
        left = null;
        right = null;
        players = new ArrayList<>();
        players.add(aux);
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
	
	public void addPlayer(U p) {
		players.add(p);
	}

	public ABBNode<T, U> getLeft() {
		return left;
	}

	public void setLeft(ABBNode<T, U> l) {
		left = l;
	}

	public ABBNode<T, U> getRight() {
		return right;
	}

	public void setRight(ABBNode<T, U> r) {
		right = r;
	}

	public ArrayList<U> getPlayers() {
		return players;
	}
	
	public T getStatistic() {
		return statistic;
	}
}
