package ui;

public class Menu {
	public static void main(String[] args) {
		int h = 6;
		int nodes = 0;
		for(int i=h-1; i>0; i--) {
			int mult = 2;
			for(int j=0; j<h-i-1; j++) {
				mult *= 2;
			}
			nodes += mult;
		}
		System.out.println(nodes+1);
	}
}
