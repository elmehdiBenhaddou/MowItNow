package org.xebia.tst.parser;

import org.xebia.tst.bo.Mower;


/**
 * classe englobe la tondeuse et l'int commandes au momment du chargement des tondeuses et les commandes
 * 
 * @author elmehdi
 *
 */
public class MowerAndOrdersWrapper {
	
	private Mower mower;
	
	private char[] orders;

	public Mower getMower() {
		return mower;
	}

	public void setMower(Mower mower) {
		this.mower = mower;
	}

	public char[] getOrders() {
		return orders;
	}

	public void setOrders(char[] orders) {
		this.orders = orders;
	}
}
