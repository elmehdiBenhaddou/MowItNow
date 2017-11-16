package org.xebia.tst.engine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.xebia.tst.engine.impl.MowerMovingEngineImpl;
import org.xebia.tst.parser.MowersFileParser;

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

  @Test
  public void exampleTest() {
	  mowerMovingEngine.startMovingMower(null, null, null, null);
  }

}
