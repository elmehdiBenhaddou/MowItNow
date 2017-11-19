package org.xebia.tst.bo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test de comportement d'un point
 *  
 * @author  elmehdi
 *
 */
public class PointTest {

	private Point coordinates;
	
	@Test
	public void test_creation(){
		coordinates=new Point(5, 7);
		assertEquals(coordinates.getX(), 5);
		assertEquals(coordinates.getY(), 7);
	}
	
	@Test
	public void test_moveToNorth(){
		coordinates=new Point(5, 7);
		coordinates.moveToNorth();
		assertEquals(coordinates.getX(), 5);
		assertEquals(coordinates.getY(), 8); 
	}
	
	@Test
	public void test_moveToWest(){
		coordinates=new Point(5, 7);
		coordinates.moveToWest();
		assertEquals(coordinates.getX(), 4);
		assertEquals(coordinates.getY(), 7);
	}
	
	@Test
	public void test_moveToSouth(){
		coordinates=new Point(5, 7);
		coordinates.moveToSouth();
		assertEquals(coordinates.getX(), 5);
		assertEquals(coordinates.getY(), 6);
	}
	
	@Test
	public void test_moveToEast(){
		coordinates=new Point(5, 7);
		coordinates.moveToEast();
		assertEquals(coordinates.getX(), 6);
		assertEquals(coordinates.getY(), 7);
	}
}
