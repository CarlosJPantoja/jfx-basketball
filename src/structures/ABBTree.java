package structures;

import java.util.ArrayList;

public class ABBTree<T extends Number, U> {

	private ABBNode<T, U> root;

	public ABBTree() {
		root = null;
	}

	public void insert(T d, U aux){
		ABBNode<T, U> nuevo = new ABBNode<T, U>(d, aux);
		if(root==null){
			root=nuevo;
		}else{
			root=insertABB(nuevo, root);
		}
	}

	private ABBNode<T, U> insertABB(ABBNode<T, U> newest, ABBNode<T, U> current){
		ABBNode<T, U> root = current;
		if(newest.compareTo(current.getStatistic())<0){
			if(current.getLeft()==null)
				current.setLeft(newest);
			else
				current.setLeft(insertABB(newest, current.getLeft()));
		}else if(newest.compareTo(current.getStatistic())>0){
			if(current.getRight()==null)
				current.setRight(newest);
			else
				current.setRight(insertABB(newest, current.getRight()));
		}else{
			current.addPlayer(newest.getPlayers().get(0));
		}
		return root;
	}
	
	public ArrayList<U> filter(ABBNode<T, U> current, T data, ArrayList<U> output, int rest, boolean incl){
		if(current != null) {
			output = filter(current.getRight(), data, output, rest, incl);
			if(current.compareTo(data)==rest || (current.compareTo(data)==0 && incl)) {
				for(int i=0; i<current.getPlayers().size(); i++) {
					output.add(current.getPlayers().get(i));
				}
			}
			output = filter(current.getLeft(), data, output, rest, incl);
		}
		return output;
	}
	
	public ABBNode<T, U> getRoot(){
		return root;
	}
}