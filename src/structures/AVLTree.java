package structures;

import java.util.ArrayList;

public class AVLTree<T extends Number, U> {

	private AVLNode<T, U> root;

	public AVLTree() {
		root = null;
	}

	public AVLNode<T, U> getRoot(){
		return root;
	}
	
	public ArrayList<U> filter(AVLNode<T, U> current, T data, ArrayList<U> output, int rest, boolean incl){
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

	public AVLNode<T, U> search(T stat, AVLNode<T, U> root){
		if(root==null){
			return null;
		}else if(root.compareTo(stat)==0){
			return root;
		}else if(root.compareTo(stat)<0){
			return search(stat, root.getRight());
		}else{
			return search(stat, root.getLeft());
		}
	}

	private AVLNode<T, U> insertarAVL(AVLNode<T, U> newest, AVLNode<T, U> current){
		AVLNode<T, U> root = current;
		if(newest.compareTo(current.getStatistic())<0){
			if(current.getLeft()==null) {
				current.setLeft(newest);
				newest.setFather(current);
			} else
				current.setLeft(insertarAVL(newest, current.getLeft()));
			current.high();
			if(Math.abs(current.getBf())>=2){
				if(newest.compareTo(current.getLeft().getStatistic())<0){
					root=leftRotation(current);
				}else{
					root=doubleLeftRotation(current);
				}
			}
		}else if(newest.compareTo(current.getStatistic())>0){
			if(current.getRight()==null) {
				current.setRight(newest);
				newest.setFather(current);
			} else
				current.setRight(insertarAVL(newest, current.getRight()));
			current.high();
			if(Math.abs(current.getBf())>=2){
				if(newest.compareTo(current.getRight().getStatistic())>0){
					root=rightRotation(current);
				}else{
					root=doubleRightRotation(current);
				}
			}
		}else{
			current.addPlayer(newest.getPlayers().get(0));
		}
		return root;
	}

	private AVLNode<T, U> doubleRightRotation(AVLNode<T, U> current) {
		current.setRight(leftRotation(current.getRight()));
		return rightRotation(current);
	}

	private AVLNode<T, U> doubleLeftRotation(AVLNode<T, U> current) {
		current.setLeft(rightRotation(current.getLeft()));
		return leftRotation(current);
	}

	private AVLNode<T, U> leftRotation(AVLNode<T, U> subAr) {
		AVLNode<T, U> auxiliar = subAr.getLeft();
		auxiliar.setFather(subAr.getFather());
		subAr.setLeft(auxiliar.getRight());
		if(auxiliar.getRight()!=null) 
			auxiliar.getRight().setFather(subAr);
		auxiliar.setRight(subAr);
		subAr.setFather(auxiliar);
		auxiliar.high();
		return auxiliar;
	}

	private AVLNode<T, U> rightRotation(AVLNode<T, U> subAr) {
		AVLNode<T, U> auxiliar=subAr.getRight();
		auxiliar.setFather(subAr.getFather());
		subAr.setRight(auxiliar.getLeft());
		if(auxiliar.getLeft()!=null) 
			auxiliar.getLeft().setFather(subAr);
		auxiliar.setLeft(subAr);
		subAr.setFather(auxiliar);
		auxiliar.high();
		return auxiliar;
	}

	public void insertar(T d, U aux){
		AVLNode<T, U> nuevo = new AVLNode<T, U>(d, aux);
		if(root==null){
			root=nuevo;
		}else{
			root=insertarAVL(nuevo, root);
			root.setFather(null);
		}
	}

	public void preorden(AVLNode<T, U> r){
		if(r!=null){
			System.out.println
			(r.getStatistic());
			preorden(r.getLeft());
			preorden(r.getRight());
		}
	}

	public void inOrden(AVLNode<T, U> r){
		if(r!=null){
			inOrden(r.getLeft());
			for(int i=0; i<r.getPlayers().size(); i++) {
				System.out.println(r.getStatistic());
			}
			inOrden(r.getRight());
		}
	}
}
