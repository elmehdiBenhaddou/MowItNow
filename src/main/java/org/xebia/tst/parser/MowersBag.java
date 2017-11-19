package org.xebia.tst.parser;

import java.util.List;

import org.xebia.tst.bo.Point;

/**
 * 
 * 
 * @author elmehdi
 *
 */
public class MowersBag {

	private Point border;
	
	private List<MowerAndOrdersWrapper> mowerAndOrders;

	public Point getBorder() {
		return border;
	}

	public void setBorder(Point border) {
		this.border = border;
	}

	public List<MowerAndOrdersWrapper> getMowerAndOrders() {
		return mowerAndOrders;
	}

	public void setMowerAndOrders(List<MowerAndOrdersWrapper> mowerAndOrders) {
		this.mowerAndOrders = mowerAndOrders;
	}
	
	
	
}
