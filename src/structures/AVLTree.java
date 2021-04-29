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
			return -1;
		}else{
			return node.getBf();
		}
	}

	private AVLNode<T, U> insertarAVL(AVLNode<T, U> nuevo, AVLNode<T, U> subAr){
		AVLNode<T, U> nuevoPadre=subAr;
		if(nuevo.compareTo(subAr.getStatistic())<0){
			if(subAr.getLeft()==null){
				subAr.setLeft(nuevo);
			}else{
				subAr.setLeft(insertarAVL(nuevo, subAr.getLeft()));
				if((obtainB(subAr.getLeft())-obtainB(subAr.getRight())==2)){
					if(nuevo.compareTo(subAr.getLeft().getStatistic())<0){
						nuevoPadre=rotacionIzquierda(subAr);
					}else{
						nuevoPadre=rotacionDobleIzquierda(subAr);
					}
				}
			}
		}else if(nuevo.compareTo(subAr.getStatistic())>0){
			if(subAr.getRight()==null){
				subAr.setRight(nuevo);;
			}else{
				subAr.setLeft(insertarAVL(nuevo, subAr.getRight()));
				if((obtainB(subAr.getRight())-obtainB(subAr.getLeft())==2)){
					if(nuevo.compareTo(subAr.getRight().getStatistic())>0){
						nuevoPadre=rotacionDerecha(subAr);
					}else{
						nuevoPadre=rotacionDobleDerecha(subAr);
					}
				}
			}
		}else{
			// JOptionPane.showMessageDialog(null, "Nodo duplicado");
		}
		if((subAr.getLeft()==null)&&(subAr.getRight()!=null)){
			subAr.setBf(subAr.getRight().getBf()+1);
		}else if((subAr.getRight()==null)&&(subAr.getLeft()!=null)){
			subAr.setBf(subAr.getLeft().getBf()+1);
		}else{
			subAr.setBf(Math.max(obtainB(subAr.getLeft()), obtainB(subAr.getRight()))+1);
		}
		return nuevoPadre;
	}

	private AVLNode<T, U> rotacionDobleDerecha(AVLNode<T, U> subAr) {
		AVLNode<T, U> temporal;
		subAr.setLeft(rotacionDerecha(subAr.getLeft()));
		temporal=rotacionIzquierda(subAr);
		return temporal;
	}

	private AVLNode<T, U> rotacionDobleIzquierda(AVLNode<T, U> subAr) {
		AVLNode<T, U> temporal;
		subAr.setRight(rotacionIzquierda(subAr.getRight()));
		temporal=rotacionDerecha(subAr);
		return temporal;
	}

	private AVLNode<T, U> rotacionIzquierda(AVLNode<T, U> subAr) {
		AVLNode<T, U> auxiliar=subAr.getLeft();
		subAr.setLeft(auxiliar.getRight());
		auxiliar.setRight(subAr);;
		subAr.setBf(Math.max(obtainB(subAr.getLeft()), obtainB(subAr.getRight()))+1);  //obtiene el maximo
		auxiliar.setBf(Math.max(obtainB(auxiliar.getLeft()), obtainB(auxiliar.getRight()))+1);
		return auxiliar;
	}

	private AVLNode<T, U> rotacionDerecha(AVLNode<T, U> subAr) {
		AVLNode<T, U> auxiliar=subAr.getRight();
		subAr.setRight(auxiliar.getLeft());
		auxiliar.setLeft(subAr);;
		subAr.setBf(Math.max(obtainB(subAr.getLeft()), obtainB(subAr.getRight()))+1);  //obtiene el maximo
		auxiliar.setBf(Math.max(obtainB(auxiliar.getLeft()), obtainB(auxiliar.getRight()))+1);
		return auxiliar;
	}

	public void insertar(T d){
		AVLNode<T, U> nuevo = new AVLNode<T, U>(d);
		if(root==null){
			root=nuevo;
		}else{
			root=insertarAVL(nuevo, root);
		}
	}
	
	public void preorden(AVLNode<T, U> r){
        if(r!=null){
            System.out.println(r.getStatistic());
            preorden(r.getLeft());
            preorden(r.getRight());
        }
    }
}
