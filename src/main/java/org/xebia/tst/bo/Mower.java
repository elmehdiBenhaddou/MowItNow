package org.xebia.tst.bo;



/**
 * 
 * classe qui modélise une tondeuse avec ses coordonnees et sa orientation
 * ainsi qu'un flag qui détérmine est ce que la tondeuse est en dehors de la pelouse ou pas
 * 
 * 
 * @author elmehdi
 *
 */

public class Mower {

	private Point coordinates;
	
	private Orientation orientation;
	
	private boolean stoped;


	public Mower(Point coordinates, Orientation orientation, boolean stoped) {
		super();
		this.coordinates = coordinates;
		this.orientation = orientation;
		this.stoped = stoped;
	}

	public Point getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public boolean isStoped() {
		return stoped;
	}

	public void setStoped(boolean stoped) {
		this.stoped = stoped;
	}
	
	/**
	 * methode pour faire bouger la tondeuse
	 * 
	 */
	public void moveMower(){
	
	  switch (this.orientation) {
	         case  N :
	            coordinates.moveToNorth();
	            break;
	         case  W :
		            coordinates.moveToWest();
		            break;
	         case  S :
		            coordinates.moveToSouth();
		            break;
	         case  E :
		            coordinates.moveToEast();
		            break;       
	  }
	}

	@Override
	public String toString() {
		return this.coordinates.getX()+" "+this.coordinates.getY()+" "+this.orientation.toString();
	}
	
	
}
