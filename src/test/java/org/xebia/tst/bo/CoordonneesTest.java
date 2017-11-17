package org.xebia.tst.bo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test de comportement de coordonnees
 *  
 * @author  elmehdi
 *
 */
public class CoordonneesTest {

	private Coordonnees coordonnees;
	
	@Test
	public void test_creation(){
		coordonnees=new Coordonnees(5, 7);
		assertEquals(coordonnees.getX(), 5);
		assertEquals(coordonnees.getY(), 7);
	}
	
	@Test
	public void test_moveToNorth(){
		coordonnees=new Coordonnees(5, 7);
		coordonnees.moveToNorth();
		assertEquals(coordonnees.getX(), 5);
		assertEquals(coordonnees.getY(), 8); 
	}
	
	@Test
	public void test_moveToWest(){
		coordonnees=new Coordonnees(5, 7);
		coordonnees.moveToWest();
		assertEquals(coordonnees.getX(), 4);
		assertEquals(coordonnees.getY(), 7);
	}
	
	@Test
	public void test_moveToSouth(){
		coordonnees=new Coordonnees(5, 7);
		coordonnees.moveToSouth();
		assertEquals(coordonnees.getX(), 5);
		assertEquals(coordonnees.getY(), 6);
	}
	
	@Test
	public void test_moveToEast(){
		coordonnees=new Coordonnees(5, 7);
		coordonnees.moveToEast();
		assertEquals(coordonnees.getX(), 6);
		assertEquals(coordonnees.getY(), 7);
	}
}
