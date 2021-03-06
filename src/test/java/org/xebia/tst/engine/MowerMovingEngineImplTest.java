package org.xebia.tst.engine;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.xebia.tst.bo.Point;
import org.xebia.tst.bo.Mower;
import org.xebia.tst.bo.Orientation;
import org.xebia.tst.engine.impl.MowerMovingEngineImpl;
import org.xebia.tst.loaders.rules.Rules;

/**
 * 
 * 
 * @author elmehdi
 *
 */
@RunWith(SpringRunner.class)
@Import(MowerMovingEngineImpl.class)
@TestPropertySource(locations="classpath:/application.properties")
public class MowerMovingEngineImplTest {
 
  @Autowired
  private MowerMovingEngine mowerMovingEngine;
  
  private  Rules rules;

  @Before
  public void init() {
	  rules =new Rules();
	  rules.addRule("NG", "W");
	  rules.addRule("ND", "E");
	  rules.addRule("WG", "S");
	  rules.addRule("WD", "N");
	  rules.addRule("SG", "E");
	  rules.addRule("SD", "W");
	  rules.addRule("EG", "N");
	  rules.addRule("ED", "S");
  }
  
  @Test
  public void test_startMovingMower() {
	  Point borderCoordinates=new Point(5, 5);
	  Point coordinates =new Point(1, 2);
	  Mower  mower = new Mower(coordinates, Orientation.N, false);
	  mower = mowerMovingEngine.startMovingMower(rules, mower, "GAGAGAGAA".toCharArray(), borderCoordinates);
	  assertEquals(mower.getCoordinates().getX(), 1);
	  assertEquals(mower.getCoordinates().getY(), 3);
	  assertEquals(mower.getOrientation(), Orientation.N);
	  assertEquals(mower.isStoped(), false);
  }
  
  @Test
  public void test_startMovingMower_mower_outside() {
	  Point  borderCoordinates=new Point(5, 5);
	  Point coordinates =new Point(1, 2);
	  Mower  mower = new Mower(coordinates, Orientation.N, false);
	  mower = mowerMovingEngine.startMovingMower(rules, mower, "GAGAGAGAAAAA".toCharArray(), borderCoordinates);
	  assertEquals(mower.getCoordinates().getX(), 1);
	  assertEquals(mower.getCoordinates().getY(), 6);
	  assertEquals(mower.getOrientation(), Orientation.N);
	  assertEquals(mower.isStoped(), true);
  }
  
  @Test
  public void test_startMovingMower_mower_outside_in_creation() {
	  Point  borderCoordinates=new Point(5, 5);
	  Point coordinates =new Point(6, 2);
	  Mower  mower = new Mower(coordinates, Orientation.N, false);
	  mower = mowerMovingEngine.startMovingMower(rules, mower, "GAGAGAGAAAAA".toCharArray(), borderCoordinates);
	  assertEquals(mower.getCoordinates().getX(), 6);
	  assertEquals(mower.getCoordinates().getY(), 2);
	  assertEquals(mower.getOrientation(), Orientation.N);
	  assertEquals(mower.isStoped(), true);
  }

}
