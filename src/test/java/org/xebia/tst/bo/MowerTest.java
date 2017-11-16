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
	
	private Coordonnees  coordonneesBorder=new Coordonnees(5, 5);

	@Test
	public void test_creation(){
		Coordonnees coordonnees =new Coordonnees(0, 5);
		mower = new Mower(coordonnees, Orientation.E, false);
		assertEquals(mower.getCoordonnees().getY(), 5);
		assertEquals(mower.getCoordonnees().getX(), 0);
		assertEquals(mower.getOrientation(), Orientation.E);
		assertEquals(mower.isStoped(), false);
	}
	
	@Test
	public void test_moveMower(){
		Coordonnees coordonnees =new Coordonnees(4, 5);
		mower = new Mower(coordonnees, Orientation.E, false);
		mower.moveMower(coordonneesBorder);
		assertEquals(mower.getCoordonnees().getY(), 5);
		assertEquals(mower.getCoordonnees().getX(), 5);
		assertEquals(mower.getOrientation(), Orientation.E);
		assertEquals(mower.isStoped(), false);
	}
	@Test
	public void test_moveMower_outside(){
		Coordonnees coordonnees =new Coordonnees(6, 5);
		mower = new Mower(coordonnees, Orientation.E, false);
		mower.moveMower(coordonneesBorder);
		assertEquals(mower.getCoordonnees().getY(), 5);
		assertEquals(mower.getCoordonnees().getX(), 6);
		assertEquals(mower.getOrientation(), Orientation.E);
		assertEquals(mower.isStoped(), true);
		
	}
}
