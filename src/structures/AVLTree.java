package structures;

public class AVLTree<T extends Number, U> {

	private AVLNode<T, U> root;

	public AVLTree() {
		root = null;
	}

	public AVLNode<T, U> getRoot(){
		return root;
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

	public int obtainB(AVLNode<T, U> current){
		if(current==null){
			return -1;
		}else{
			return current.getBf();
		}
	}

	private AVLNode<T, U> insertarAVL(AVLNode<T, U> newest, AVLNode<T, U> current){
		AVLNode<T, U> root = current;
		if(newest.compareTo(current.getStatistic())<0){
			if(current.getLeft()==null)
				current.setLeft(newest);
			else {
				current.setLeft(insertarAVL(newest, current.getLeft()));
				if((obtainB(current.getLeft())-obtainB(current.getRight())==2)){
					if(newest.compareTo(current.getLeft().getStatistic())<0){
						root=leftRotation(current);
					}else{
						root=doubleLeftRotation(current);
					}
				}
			}
		}else if(newest.compareTo(current.getStatistic())>0){
			if(current.getRight()==null){
				current.setRight(newest);
			}else{
				current.setRight(insertarAVL(newest, current.getRight()));
				if((obtainB(current.getRight())-obtainB(current.getLeft())==2)){
					if(newest.compareTo(current.getRight().getStatistic())>0){
						root=rightRotation(current);
					}else{
						root=doubleRightRotation(current);
					}
				}
			}
		}else{
			current.addPlayer(newest.getPlayers().get(0));
			return root;
		}
		if((current.getLeft()==null)&&(current.getRight()!=null)){
			current.setBf(current.getRight().getBf()+1);
		}else if((current.getRight()==null)&&(current.getLeft()!=null)){
			current.setBf(current.getLeft().getBf()+1);
		}else{
			current.setBf(Math.max(obtainB(current.getLeft()), obtainB(current.getRight()))+1);
		}
		return root;
	}
	
	private int obtainNodeBalance(AVLNode<T, U> current) {
		return obtainB(current.getRight())-obtainB(current.getLeft());
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
		subAr.setLeft(auxiliar.getRight());
		auxiliar.setRight(subAr);
		subAr.setBf(Math.max(obtainB(subAr.getLeft()), obtainB(subAr.getRight()))+1);
		auxiliar.setBf(Math.max(obtainB(auxiliar.getLeft()), obtainB(auxiliar.getRight()))+1);
		return auxiliar;
	}

	private AVLNode<T, U> rightRotation(AVLNode<T, U> subAr) {
		AVLNode<T, U> auxiliar=subAr.getRight();
		subAr.setRight(auxiliar.getLeft());
		auxiliar.setLeft(subAr);
		subAr.setBf(Math.max(obtainB(subAr.getLeft()), obtainB(subAr.getRight()))+1);
		auxiliar.setBf(Math.max(obtainB(auxiliar.getLeft()), obtainB(auxiliar.getRight()))+1);
		return auxiliar;
	}

	public void insertar(T d, U aux){
		AVLNode<T, U> nuevo = new AVLNode<T, U>(d, aux);
		if(root==null){
			root=nuevo;
		}else{
			root=insertarAVL(nuevo, root);
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
            	System.out.println(r.getPlayers().get(i).toString());
            }
            inOrden(r.getRight());
        }
        
        
    }
}
