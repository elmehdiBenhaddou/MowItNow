package org.xebia.tst.parser;

import java.util.List;

import org.xebia.tst.bo.Coordonnees;

/**
 * 
 * 
 * @author elmehdi
 *
 */
public class MowersBag {

	private Coordonnees border;
	
	private List<MowerAndOrdersWrapper> mowerAndOrders;

	public Coordonnees getBorder() {
		return border;
	}

	public void setBorder(Coordonnees border) {
		this.border = border;
	}

	public List<MowerAndOrdersWrapper> getMowerAndOrders() {
		return mowerAndOrders;
	}

	public void setMowerAndOrders(List<MowerAndOrdersWrapper> mowerAndOrders) {
		this.mowerAndOrders = mowerAndOrders;
	}
	
	
	
}
