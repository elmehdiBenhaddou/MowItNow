package org.xebia.tst.bo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * Test de comportement des tondeuses
 * 
 * @author  elmehdi
 *
 */
public class MowerTest {

	private Mower mower;
	

	@Test
	public void test_creation(){
		Point coordinates =new Point(0, 5);
		mower = new Mower(coordinates, Orientation.E, false);
		assertEquals(mower.getCoordinates().getY(), 5);
		assertEquals(mower.getCoordinates().getX(), 0);
		assertEquals(mower.getOrientation(), Orientation.E);
		assertEquals(mower.isStoped(), false);
	}
	
	@Test
	public void test_moveMower(){
		Point coordinates =new Point(4, 5);
		mower = new Mower(coordinates, Orientation.E, false);
		mower.moveMower();
		assertEquals(mower.getCoordinates().getY(), 5);
		assertEquals(mower.getCoordinates().getX(), 5);
		assertEquals(mower.getOrientation(), Orientation.E);
	}
	
}
