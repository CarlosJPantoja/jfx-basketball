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
	
	public int obtainB(AVLNode<T, U> node){
	    if(node==null){
	        return 0;
	    }else{
	        return node.getBf();
	    }
	}
	
	
}
