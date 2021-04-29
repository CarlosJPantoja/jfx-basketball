package structures;

public class AVLTree<T extends Number, U> {
	private AVLNode<T, U> root;

	public AVLTree() {
		root = null;
	}

	public AVLNode<T, U> getRoot(){
		return root;
	}
	
	
}
