package org.xebia.tst.bo;

/**
 * classe  des coordonnees
 * 
 * 
 * 
 * @author elmehdi
 *
 */
public class Point {

	/**
	 * axe des abscisses
	 */
	private int x;
	/**
	 * axe des ordonnées
	 * 
	 */
	private int y;
	
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public void moveToNorth(){
		this.y = this.y + 1;
	}

	public void moveToWest(){
		this.x = this.x - 1;
	}
	
	public void moveToSouth(){
		this.y = this.y - 1;
	}
	
	public void moveToEast(){
		this.x = this.x + 1;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
