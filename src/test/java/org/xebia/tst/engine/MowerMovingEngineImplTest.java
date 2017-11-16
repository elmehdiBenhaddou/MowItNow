package org.xebia.tst.engine;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xebia.tst.bo.Coordonnees;
import org.xebia.tst.bo.Mower;
import org.xebia.tst.bo.Orientation;
import org.xebia.tst.loaders.rules.Rules;

/**
 * 
 * 
 * @author elmehdi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
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
	  Coordonnees  coordonneesBorder=new Coordonnees(5, 5);
	  Coordonnees coordonnees =new Coordonnees(1, 2);
	  Mower  mower = new Mower(coordonnees, Orientation.N, false);
	  mower = mowerMovingEngine.startMovingMower(rules, mower, "GAGAGAGAA".toCharArray(), coordonneesBorder);
	  assertEquals(mower.getCoordonnees().getX(), 1);
	  assertEquals(mower.getCoordonnees().getY(), 3);
	  assertEquals(mower.getOrientation(), Orientation.N);
	  assertEquals(mower.isStoped(), false);
  }
  
  @Test
  public void test_startMovingMower_mower_outside() {
	  Coordonnees  coordonneesBorder=new Coordonnees(5, 5);
	  Coordonnees coordonnees =new Coordonnees(1, 2);
	  Mower  mower = new Mower(coordonnees, Orientation.N, false);
	  mower = mowerMovingEngine.startMovingMower(rules, mower, "GAGAGAGAAAAA".toCharArray(), coordonneesBorder);
	  assertEquals(mower.getCoordonnees().getX(), 1);
	  assertEquals(mower.getCoordonnees().getY(), 6);
	  assertEquals(mower.getOrientation(), Orientation.N);
	  assertEquals(mower.isStoped(), true);
  }

}
