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

	private Coordonnees coordonnees;
	
	private Orientation orientation;
	
	private boolean stoped;


	public Mower(Coordonnees coordonnees, Orientation orientation, boolean stoped) {
		super();
		this.coordonnees = coordonnees;
		this.orientation = orientation;
		this.stoped = stoped;
	}

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
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
	public void moveMower(Coordonnees limits){
	
	  switch (this.orientation) {
	         case  N :
	            coordonnees.moveToNorth();
	            break;
	         case  W :
		            coordonnees.moveToWest();
		            break;
	         case  S :
		            coordonnees.moveToSouth();
		            break;
	         case  E :
		            coordonnees.moveToEast();
		            break;       
	  }
		//test si la tondeuse est dehors du rectangle
		if(this.coordonnees.getX() > limits.getX() || this.coordonnees.getY() > limits.getY() 
				 || this.coordonnees.getX() < 0 || this.coordonnees.getY() < 0){
			this.stoped = true;
		}
	}

	@Override
	public String toString() {
		return this.coordonnees.getX()+" "+this.coordonnees.getY()+" "+this.orientation.toString();
	}
	
	
}
